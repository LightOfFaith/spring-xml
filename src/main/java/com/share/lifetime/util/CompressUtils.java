package com.share.lifetime.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;

import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.ArchiveOutputStream;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.compress.utils.IOUtils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * 
 * @author hasee
 * @see http://commons.apache.org/proper/commons-compress/
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CompressUtils {

	/**
	 * 
	 * @param targetPathname
	 * @param tarPathname
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static void unTar(String targetPathname, String tarPathname) throws FileNotFoundException, IOException {
		File targetDir = new File(targetPathname);
		File tarFile = new File(tarPathname);
		try (ArchiveInputStream tarInput = new TarArchiveInputStream(
				new BufferedInputStream(new FileInputStream(tarFile)))) {
			uncompress(targetDir, tarInput);
		}
	}

	private static void uncompress(File targetDir, ArchiveInputStream input) throws IOException {
		ArchiveEntry entry = null;
		while ((entry = input.getNextEntry()) != null) {
			if (!input.canReadEntryData(entry)) {
				// log something?
				continue;
			}
			String name = fileName(targetDir, entry);
			File f = new File(name);
			if (entry.isDirectory()) {
				if (!f.isDirectory() && !f.mkdirs()) {
					throw new IOException("failed to create directory " + f);
				}
			} else {
				File parent = f.getParentFile();
				if (!parent.isDirectory() && !parent.mkdirs()) {
					throw new IOException("failed to create directory " + parent);
				}
				try (OutputStream output = Files.newOutputStream(f.toPath())) {
					IOUtils.copy(input, output);
				}
			}
		}
	}

	private static String fileName(File targetDir, ArchiveEntry entry) {
		File file = new File(targetDir, entry.getName());
		return file.getPath();
	}

	public static void zip(String zipPathname, String targetPathname) throws IOException {
		File zipFile = new File(zipPathname);
		File targetDir = new File(targetPathname);
		try (ArchiveOutputStream zipOutput = new ZipArchiveOutputStream(zipFile)) {
			if (targetDir.exists()) {
				File[] ziplistFiles = targetDir.listFiles();
				ZipArchiveEntry entry = null;
				String entryName = null;
				for (File inputFile : ziplistFiles) {
					try (InputStream zipInput = Files.newInputStream(inputFile.toPath())) {
						entryName = inputFile.getName();
						entry = new ZipArchiveEntry(inputFile, entryName);
						zipOutput.putArchiveEntry(entry);
						IOUtils.copy(zipInput, zipOutput);
						zipOutput.closeArchiveEntry();
					}
				}
			} else {
				throw new FileNotFoundException(targetDir.toString());
			}
		}
	}

	public static void unZip(String targetPathname, String zipPathname) throws IOException {
		File targetDir = new File(targetPathname);
		File zipFile = new File(zipPathname);
		try (ArchiveInputStream zipInput = new ZipArchiveInputStream(
				new BufferedInputStream(new FileInputStream(zipFile)))) {
			uncompress(targetDir, zipInput);
		}

	}

}
