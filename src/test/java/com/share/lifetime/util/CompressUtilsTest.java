package com.share.lifetime.util;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

public class CompressUtilsTest {

	@Test
	public void testUnTar() throws FileNotFoundException, IOException {
		CompressUtils.unTar("E:\\", "E:\\httpcomponents-client-4.5.6.tar");
	}

	@Test
	public void testZip() throws FileNotFoundException, IOException {
		CompressUtils.zip("E:\\aa.zip", "E:\\aa");
	}

}
