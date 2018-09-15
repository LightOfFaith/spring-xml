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
        logger.info("方法 " + name + " 开始执行:" + DateFormatUtils.formatDate(DateFormatUtils.PATTERN_DEFAULT, new Date()));
        try {
            return invocation.proceed();
        }
        finally {
            long end = System.currentTimeMillis();
            long time = end - start;
            logger.info("方法 "+name+" 执行持续了:"+time+" ms");
            logger.info("方法 "+name+" 执行结束:"+DateFormatUtils.formatDate(DateFormatUtils.PATTERN_DEFAULT, new Date()));
             
            if (time > 10){
            	logger.warn("方法执行时间长于10ms!");
            }            
        }
	}

}
