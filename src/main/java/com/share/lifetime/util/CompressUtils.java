package com.share.lifetime.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;

import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveInputStream;
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
		try (ArchiveInputStream i = new TarArchiveInputStream(new BufferedInputStream(new FileInputStream(tarFile)))) {
			ArchiveEntry entry = null;
			while ((entry = i.getNextEntry()) != null) {
				if (!i.canReadEntryData(entry)) {
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
					try (OutputStream o = Files.newOutputStream(f.toPath())) {
						IOUtils.copy(i, o);
					}
				}
			}
		}
	}

	private static String fileName(File targetDir, ArchiveEntry entry) {
		File file = new File(targetDir, entry.getName());
		return file.getPath();
	}

	public static void zip(String zipName, String zipPathname) throws IOException {
		File zip = new File(zipName);
		File zipFile = new File(zipPathname);
		ZipArchiveOutputStream zipOutput = new ZipArchiveOutputStream(zip);
		File[] ziplistFiles = zipFile.listFiles();
		ZipArchiveInputStream zipInput = null;
		ZipArchiveEntry entry = null;
		String entryName = null;
		byte[] contentOfEntry = new byte[1024];
		int length = 0;
		for (File inputFile : ziplistFiles) {
			zipInput = new ZipArchiveInputStream(new FileInputStream(inputFile));
			entryName = inputFile.getName();
			entry = new ZipArchiveEntry(inputFile, entryName);
			
			zipOutput.putArchiveEntry(entry);
			while ((length = zipInput.read(contentOfEntry)) != -1) {
				zipOutput.write(contentOfEntry);
			}
			zipOutput.closeArchiveEntry();
		}
	}

}
