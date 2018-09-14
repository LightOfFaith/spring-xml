package com.share.lifetime.interceptor;
import java.util.Date;

import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.logging.Log;
import org.springframework.aop.interceptor.AbstractMonitoringInterceptor;

import com.share.lifetime.util.DateFormatUtils;

/**
 * 性能监控
 * @author liaoxiang
 *
 */
public class PerformanceMonitorInterceptor extends AbstractMonitoringInterceptor {

	private static final long serialVersionUID = 8605809544050236968L;

	@Override
	protected Object invokeUnderTrace(MethodInvocation invocation, Log logger) throws Throwable {
		String name = createInvocationTraceName(invocation);
        long start = System.currentTimeMillis();
        logger.info("Method " + name + " execution started at:" + DateFormatUtils.formatDate(DateFormatUtils.PATTERN_DEFAULT_ON_SECOND, new Date()));
        try {
            return invocation.proceed();
        }
        finally {
            long end = System.currentTimeMillis();
            long time = end - start;
            logger.info("Method "+name+" execution lasted:"+time+" ms");
            logger.info("Method "+name+" execution ended at:"+DateFormatUtils.formatDate(DateFormatUtils.PATTERN_DEFAULT_ON_SECOND, new Date()));
             
            if (time > 10){
            	logger.warn("Method execution longer than 10 ms!");
            }            
        }
	}

}
