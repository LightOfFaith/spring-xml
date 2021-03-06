package com.share.lifetime.util;

import java.io.File;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.Result;
import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.core.logging.FileLoggingEventListener;
import org.pentaho.di.core.logging.KettleLogStore;
import org.pentaho.di.core.logging.KettleLoggingEventListener;
import org.pentaho.di.job.Job;
import org.pentaho.di.job.JobMeta;
import org.pentaho.di.trans.Trans;
import org.pentaho.di.trans.TransMeta;

public class KettleUtils {

	private static final Map<String, TransMeta> TRANSMETA_CACHE = new ConcurrentHashMap<String, TransMeta>(16);

	private static final Map<String, JobMeta> JOBMETA_CACHE = new ConcurrentHashMap<String, JobMeta>(16);

	public static final String LOG_PATH = "logs" + File.separator;

	private static final String LOG_FILENAME = LOG_PATH + "kettle.log";

	private static final boolean APPEND = false;

	/**
	 * 运行kettle,transformation文件
	 * 
	 * @param filename transformation文件路径(路径+名称+后缀)
	 * @throws KettleException Kettle 异常
	 */
	public static void runTransformation(String filename) {
		runTransformation(filename, null, null, null);
	}

	/**
	 * 运行kettle,transformation文件
	 * 
	 * @param filename          transformation文件路径(路径+名称+后缀)
	 * @param variableMap       将指定变量的值设置为指定值。设置变量key=value
	 * @param parameterValueMap 设置指定参数的值。
	 * @param arguments         transformation文件必要参数
	 */
	public static void runTransformation(String filename, Map<String, String> variableMap,
			Map<String, String> parameterValueMap, String[] arguments) {
		runTransformation(filename, variableMap, parameterValueMap, arguments, null, true);
	}

	/**
	 * 运行kettle,transformation文件
	 * 
	 * @param filename          transformation文件路径(路径+名称+后缀)
	 * @param variableMap       将指定变量的值设置为指定值。设置变量key=value
	 * @param parameterValueMap 设置指定参数的值。
	 * @param arguments         transformation文件必要参数
	 * @param logFilename       日志文件存储名称(路径+名称+后缀)
	 * @param append            日志文件內容是否追加
	 * @throws KettleException Kettle 异常
	 */
	public static void runTransformation(String filename, Map<String, String> variableMap,
			Map<String, String> parameterValueMap, String[] arguments, String logFilename, boolean append) {
		try {
			boolean containsKey = TRANSMETA_CACHE.containsKey(filename);
			if (!containsKey) {
				KettleEnvironment.init();
				TransMeta transMeta = new TransMeta(filename);
				execute(variableMap, parameterValueMap, arguments, logFilename, append, transMeta);
				TRANSMETA_CACHE.put(filename, transMeta);
			} else {
				TransMeta transMeta = TRANSMETA_CACHE.get(filename);
				execute(variableMap, parameterValueMap, arguments, logFilename, append, transMeta);
			}
		} catch (KettleException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	private static void execute(Map<String, String> variableMap, Map<String, String> parameterValueMap,
			String[] arguments, String logFilename, boolean append, TransMeta transMeta) throws KettleException {
		Trans trans = new Trans(transMeta);
		String logChannelId = trans.getLogChannelId();
		KettleLoggingEventListener loggingEventListener = printLog(logFilename, logChannelId, append);
		if (variableMap != null && !variableMap.isEmpty()) {
			Set<Entry<String, String>> entrySet = variableMap.entrySet();
			for (Entry<String, String> entry : entrySet) {
				trans.setVariable(entry.getKey(), entry.getValue());
			}
		}
		if (parameterValueMap != null && !parameterValueMap.isEmpty()) {
			Set<Entry<String, String>> entrySet = parameterValueMap.entrySet();
			for (Entry<String, String> entry : entrySet) {
				trans.setParameterValue(entry.getKey(), entry.getValue());
			}
		}
		trans.execute(arguments);
		trans.waitUntilFinished();
		if (trans.getErrors() > 0) {
			throw new RuntimeException("There were errors during transformation execution.");
		}
		if (loggingEventListener != null) {
			destroy(loggingEventListener);
		}
	}

	/**
	 * 运行kettle,job文件
	 * 
	 * @param filename job文件路径(路径+名称+后缀)
	 * @throws KettleException Kettle 异常
	 */
	public static void runJob(String filename) {
		runJob(filename, null, null, null);
	}

	/**
	 * 运行kettle,job文件
	 * 
	 * @param filename          job文件路径(路径+名称+后缀)
	 * @param variableMap       将指定变量的值设置为指定值。设置变量key=value
	 * @param parameterValueMap 设置指定参数的值。
	 * @param arguments         job文件所需参数
	 */
	public static void runJob(String filename, Map<String, String> variableMap, Map<String, String> parameterValueMap,
			String[] arguments) {
		runJob(filename, variableMap, parameterValueMap, arguments, null, true);
	}

	/**
	 * 运行kettle,job文件
	 * 
	 * @param filename          job文件路径(路径+名称+后缀)
	 * @param variableMap       将指定变量的值设置为指定值。设置变量key=value
	 * @param parameterValueMap 设置指定参数的值。
	 * @param arguments         job文件所需参数
	 * @param logFilename       日志文件存储名称(路径+名称+后缀)
	 * @param append            日志文件內容是否追加
	 * @throws KettleException Kettle 异常
	 */
	public static void runJob(String filename, Map<String, String> variableMap, Map<String, String> parameterValueMap,
			String[] arguments, String logFilename, boolean append) {
		try {
			boolean containsKey = JOBMETA_CACHE.containsKey(filename);
			if (!containsKey) {
				KettleEnvironment.init();
				// KettleLogStore.getAppender()
				// .addLoggingEventListener(new
				// FileLoggingEventListener(LOG_FILENAME, APPEND));
				JobMeta jobMeta = new JobMeta(filename, null);
				start(jobMeta, variableMap, parameterValueMap, arguments, logFilename, append);
				JOBMETA_CACHE.put(filename, jobMeta);
			} else {
				JobMeta jobMeta = JOBMETA_CACHE.get(filename);
				start(jobMeta, variableMap, parameterValueMap, arguments, logFilename, append);
			}
		} catch (KettleException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	private static void start(JobMeta jobMeta, Map<String, String> variableMap, Map<String, String> parameterValueMap,
			String[] arguments, String logFilename, boolean append) throws KettleException {
		Job job = new Job(null, jobMeta);
		String logChannelId = job.getLogChannelId();
		KettleLoggingEventListener loggingEventListener = printLog(logFilename, logChannelId, append);
		if (arguments != null) {
			job.setArguments(arguments);
		}
		if (variableMap != null && !variableMap.isEmpty()) {
			Set<Entry<String, String>> entrySet = variableMap.entrySet();
			for (Entry<String, String> entry : entrySet) {
				job.setVariable(entry.getKey(), entry.getValue());
			}
		}
		if (parameterValueMap != null && !parameterValueMap.isEmpty()) {
			Set<Entry<String, String>> entrySet = parameterValueMap.entrySet();
			for (Entry<String, String> entry : entrySet) {
				job.setParameterValue(entry.getKey(), entry.getValue());
			}
		}
		job.start();
		job.waitUntilFinished();
		Result result = job.getResult();
		if (result.getNrErrors() > 0) {
			throw new RuntimeException("There were errors during job execution.");
		}
		if (loggingEventListener != null) {
			destroy(loggingEventListener);
		}
	}

	private static void destroy(KettleLoggingEventListener loggingEventListener) {
		KettleLogStore.getAppender().removeLoggingEventListener(loggingEventListener);
	}

	private static KettleLoggingEventListener printLog(String logFilename, String logChannelId, boolean append)
			throws KettleException {
		if (!StringUtils.isEmpty(logFilename) && !StringUtils.isEmpty(logChannelId)) {
			// JobEntryShell、ConsoleLoggingEventListener
			FileLoggingEventListener loggingEventListener = new FileLoggingEventListener(logChannelId, logFilename,
					append);
			// FileLoggingEventListener loggingEventListener = new
			// FileLoggingEventListener(logFilename, append);
			// LogChannel
			KettleLogStore.getAppender().addLoggingEventListener(loggingEventListener);
			return loggingEventListener;
		}
		return null;
	}

	private KettleUtils() {

	}

}
