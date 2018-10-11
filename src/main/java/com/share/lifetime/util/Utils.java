package com.share.lifetime.util;

import java.io.File;

public class Utils {
	
	/**
	 * 获取项目名称
	 * @return 项目名称
	 */
	public static String getProjectName() {
		String projectDir = System.getProperty("user.dir");
		return projectDir.substring(projectDir.lastIndexOf(File.separator), projectDir.length());
	}
	
	private Utils() {
		
	}

}
