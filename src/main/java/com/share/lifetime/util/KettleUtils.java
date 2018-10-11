package com.share.lifetime.util;

import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.Result;
import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.job.Job;
import org.pentaho.di.job.JobMeta;
import org.pentaho.di.trans.Trans;
import org.pentaho.di.trans.TransMeta;

public class KettleUtils {

	/**
	 * 运行kettle,transformation文件
	 * 
	 * @param filename
	 *            transformation文件路径(路径+名称+后缀)
	 * @throws KettleException
	 *             Kettle 异常
	 */
	public static void runTransformation(String filename) {
		runTransformation(filename, null);
	}

	/**
	 * 运行kettle,transformation文件
	 * 
	 * @param filename
	 *            transformation文件路径(路径+名称+后缀)
	 * @param arguments
	 *            transformation文件必要参数
	 * @throws KettleException
	 *             Kettle 异常
	 */
	public static void runTransformation(String filename, String[] arguments) {
		try {
			KettleEnvironment.init();
			TransMeta transMeta = new TransMeta(filename);
			Trans trans = new Trans(transMeta);
			trans.execute(arguments);
			trans.waitUntilFinished();
			if (trans.getErrors() > 0) {
				throw new RuntimeException("There were errors during transformation execution.");
			}
		} catch (KettleException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	/**
	 * 运行kettle,job文件
	 * 
	 * @param filename
	 *            job文件路径(路径+名称+后缀)
	 * @throws KettleException
	 *             Kettle 异常
	 */
	public static void runJob(String filename) {
		runJob(filename, null);
	}

	/**
	 * 运行kettle,job文件
	 * 
	 * @param filename
	 *            job文件路径(路径+名称+后缀)
	 * @param arguments
	 *            job文件所需参数
	 * @throws KettleException
	 *             Kettle 异常
	 */
	public static void runJob(String filename, String[] arguments) {
		try {
			KettleEnvironment.init();
			JobMeta jobMeta = new JobMeta(filename, null);
			Job job = new Job(null, jobMeta);
			if (arguments != null) {
				job.setArguments(arguments);
			}
			job.start();
			job.waitUntilFinished();
			Result result = job.getResult();
			if (result.getNrErrors() > 0) {
				throw new RuntimeException("There were errors during job execution.");
			}
		} catch (KettleException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	private KettleUtils() {

	}

}
