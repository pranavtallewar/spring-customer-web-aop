package com.springdemo.aspect;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CRMLoggingAspect {

	// setup logger
	private Logger logger = Logger.getLogger(getClass().getName());

	// setup pointcut declarations
	@Pointcut("execution(* com.springdemo.controller.*.*(..))")
	private void forControllerPackage() {
	}

	@Pointcut("execution(* com.springdemo.service.*.*(..))")
	private void forServicePackage() {
	}

	@Pointcut("execution(* com.springdemo.dao.*.*(..))")
	private void forDaoPackage() {
	}

	@Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage()")
	private void forAppFlow() {
	}

	// add @Before advice
	@Before("forAppFlow()")
	public void before(JoinPoint joinPoint) {
		// display method we are calling
		String method = joinPoint.getSignature().toShortString();
		logger.info("in @Before : calling method:" + method);
		// display args to method
		Object[] args = joinPoint.getArgs();
		int i = 1;
		for (Object arg : args) {
			logger.info("argument[" + i + "]" + arg);
			i++;
		}
	}

	// add @AfterReturning advice
	@AfterReturning(pointcut = "forAppFlow()", returning = "result")
	public void afterReturn(JoinPoint joinPoint, Object result) {
		// display method we are returning from
		String method = joinPoint.getSignature().toShortString();
		logger.info("in @AfterReturning: from method:" + method);

		// display returned data
		logger.info("Result:" + result);
	}
}
