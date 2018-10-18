package com.share.lifetime.util;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

public class CompressUtilsTest {

	@Test
	public void testUnTar() throws FileNotFoundException, IOException {
		CompressUtils.unTar("E:\\", "E:\\original.tar");
	}

	@Test
	public void testZip() throws FileNotFoundException, IOException {
		CompressUtils.zip("E:\\original.zip", "E:\\original");
	}

	@Test
	public void testUnZip() throws FileNotFoundException, IOException {
		CompressUtils.unZip("E:\\", "E:\\original.zip");
	}

}
