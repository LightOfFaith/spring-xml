package com.share.lifetime.support.mybatis;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;

import javax.sql.DataSource;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.TypeHandlerRegistry;
//import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.mybatis.spring.transaction.SpringManagedTransaction;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.util.ReflectionUtils;

import com.alibaba.druid.pool.DruidDataSource;

import lombok.extern.slf4j.Slf4j;

/**
 * 对MyBatis进行拦截，添加监控 目前仅支持RoutingDataSource和Druid组合配置的数据源
 * 
 * @author
 * @see https://github.com/dianping/cat/blob/master/%E6%A1%86%E6%9E%B6%E5%9F%8B%E7%82%B9%E6%96%B9%E6%A1%88%E9%9B%86%E6%88%90/mybatis/CatMybatisPlugin.java
 *
 */
@Slf4j
@Intercepts({
		@Signature(method = "query", type = Executor.class, args = { MappedStatement.class, Object.class,
				RowBounds.class, ResultHandler.class }),
		@Signature(method = "update", type = Executor.class, args = { MappedStatement.class, Object.class }) })
public class MyBatisPlugin implements Interceptor {

	private static final String EMPTY_CONNECTION = "jdbc:mysql://unknown:3306/%s?useUnicode=true";

	private Executor target;

	private static final Map<String, String> sqlURLCache = new ConcurrentHashMap<String, String>(256);

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
		// 得到类名，方法
		String[] strArr = mappedStatement.getId().split("\\.");
		String methodName = strArr[strArr.length - 2] + "." + strArr[strArr.length - 1];

		log.info("type:SQL,name:{}", methodName);
		// 得到sql语句
		Object parameter = null;
		if (invocation.getArgs().length > 1) {
			parameter = invocation.getArgs()[1];
		}
		BoundSql boundSql = mappedStatement.getBoundSql(parameter);
		Configuration configuration = mappedStatement.getConfiguration();
		String sql = showSql(configuration, boundSql);

		// 获取SQL类型
		SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();
		log.info("type:SQL.Method,name:{},status:{},nameValuePairs:{}", sqlCommandType.name().toLowerCase(), "SUCCESS",
				sql);

		String s = this.getSQLDatabase();
		log.info("type:SQL.Database,name:{}", s);

		Object returnObj = null;
		try {
			returnObj = invocation.proceed();
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
		} finally {

		}

		return returnObj;
	}

	private String getSQLDatabase() {
		String dbName = null; // 根据设置的多数据源修改此处,获取dbname
		if (dbName == null) {
			dbName = "DEFAULT";
		}
		String url = sqlURLCache.get(dbName);
		if (url != null) {
			return url;
		}

		url = this.getSqlURL();// 目前监控只支持mysql ,其余数据库需要各自修改监控服务端
		if (url == null) {
			url = String.format(EMPTY_CONNECTION, dbName);
		}
		sqlURLCache.put(dbName, url);
		return url;
	}

	private String getSqlURL() {
		javax.sql.DataSource dataSource = this.getDataSource();

		if (dataSource == null) {
			return null;
		}

		if (dataSource instanceof AbstractRoutingDataSource) {
			String methodName = "determineTargetDataSource";
			Method method = ReflectionUtils.findMethod(AbstractRoutingDataSource.class, methodName);

			if (method == null) {
				log.error(String.format("---Could not find method [%s] on target [%s]", methodName, dataSource));
				return null;
			}

			ReflectionUtils.makeAccessible(method);
			javax.sql.DataSource dataSource1 = (javax.sql.DataSource) ReflectionUtils.invokeMethod(method, dataSource);
			if (dataSource1 instanceof DruidDataSource) {
				DruidDataSource druidDataSource = (DruidDataSource) dataSource1;
				return druidDataSource.getUrl();
			} else {
				log.error("---only surpport DruidDataSource:" + dataSource1.getClass().toString());
			}
		} // else if (dataSource instanceof BasicDataSource) {
//			return ((BasicDataSource) dataSource).getUrl();
//		}
		return null;
	}

	private DataSource getDataSource() {
		org.apache.ibatis.transaction.Transaction transaction = this.target.getTransaction();
		if (transaction == null) {
			log.error(String.format("Could not find transaction on target [%s]", this.target));
			return null;
		}
		if (transaction instanceof SpringManagedTransaction) {
			String fieldName = "dataSource";
			Field field = ReflectionUtils.findField(transaction.getClass(), fieldName, javax.sql.DataSource.class);

			if (field == null) {
				log.error(String.format("Could not find field [%s] of type [%s] on target [%s]", fieldName,
						javax.sql.DataSource.class, this.target));
				return null;
			}

			ReflectionUtils.makeAccessible(field);
			javax.sql.DataSource dataSource = (javax.sql.DataSource) ReflectionUtils.getField(field, transaction);
			return dataSource;
		}

		log.error(String.format("---the transaction is not SpringManagedTransaction:%s",
				transaction.getClass().toString()));

		return null;
	}

	/**
	 * 解析sql语句
	 * 
	 * @param configuration
	 * @param boundSql
	 * @return
	 */
	private String showSql(Configuration configuration, BoundSql boundSql) {
		Object parameterObject = boundSql.getParameterObject();
		List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
		String sql = boundSql.getSql().replaceAll("[\\s]+", " ");
		if (parameterMappings.size() > 0 && parameterObject != null) {
			TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
			if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
				sql = sql.replaceFirst("\\?", Matcher.quoteReplacement(getParameterValue(parameterObject)));

			} else {
				MetaObject metaObject = configuration.newMetaObject(parameterObject);
				for (ParameterMapping parameterMapping : parameterMappings) {
					String propertyName = parameterMapping.getProperty();
					if (metaObject.hasGetter(propertyName)) {
						Object obj = metaObject.getValue(propertyName);
						sql = sql.replaceFirst("\\?", Matcher.quoteReplacement(getParameterValue(obj)));
					} else if (boundSql.hasAdditionalParameter(propertyName)) {
						Object obj = boundSql.getAdditionalParameter(propertyName);
						sql = sql.replaceFirst("\\?", Matcher.quoteReplacement(getParameterValue(obj)));
					}
				}
			}
		}
		return sql;
	}

	/**
	 * 参数解析
	 * 
	 * @param parameterObject
	 * @return
	 */
	private String getParameterValue(Object parameterObject) {
		String value = null;
		if (parameterObject instanceof String) {
			value = "'" + parameterObject.toString() + "'";
		} else if (parameterObject instanceof Date) {
			DateFormat formatter = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, Locale.CHINA);
			value = "'" + formatter.format((Date) parameterObject) + "'";
		} else {
			if (parameterObject != null) {
				value = parameterObject.toString();
			} else {
				value = "";
			}

		}
		return value;
	}

	@Override
	public Object plugin(Object target) {
		if (target instanceof Executor) {
			this.target = (Executor) target;
			return Plugin.wrap(target, this);
		}
		return target;
	}

	@Override
	public void setProperties(Properties properties) {

	}

}
