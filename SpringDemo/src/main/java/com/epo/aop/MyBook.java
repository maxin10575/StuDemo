package com.epo.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Service;

@Service  
@Aspect
public class MyBook {
	/**
	 * service 层切点
	 */
	@Pointcut("execution(* com.epo.aop.Book.*(..))")
	public void servicePointcut() {
	}

	//在方法上面使用注解完成配置
//	@Before(value="execution(* com.epo.aop.Book.*(..))")
	@Before(value = "servicePointcut()")
	public void before1() {
		System.out.println("前置增强........");
	}
//	@After(value="execution(* com.epo.aop.Book.*(..))")
	@After(value = "servicePointcut()")
	public void after1() {
		System.out.println("后置增强。。。。。。。");
	}
//	@Around(value="execution(* com.epo.aop.Book.*(..))")
	@Around(value = "servicePointcut()")
	public void around1(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
			//方法之前
			System.out.println("方法之前。。。。");
			//执行被增强的方法
			proceedingJoinPoint.proceed();
			//方法之后
			System.out.println("方法之后。。。。");
	}
}
