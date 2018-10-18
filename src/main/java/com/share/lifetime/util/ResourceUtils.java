package com.share.lifetime.util;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * 
 * @author liaoxiang
 * @see org.springframework.util.ResourceUtils
 *
 */
public class ResourceUtils {

	private static final String CLASSPATH_PREFIX = "classpath";

	private static final String URL_PROTOCOL_FILE = "file";

	/**
	 * 
	 * @param resourceLocation support "classpath:";"file:";"D:"(资源文件全路径)
	 * @return
	 * @throws FileNotFoundException
	 */
	public static File getFile(String resourceLocation) throws FileNotFoundException {
		return org.springframework.util.ResourceUtils.getFile(resourceLocation);
	}

	public static String getPath(String resourceLocation) throws FileNotFoundException {
		File file = getFile(resourceLocation);
		return file != null ? file.getPath() : null;
	}


}
