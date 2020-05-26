package com.epo.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Service;

@Service  
@Aspect
public class MyBook {
	/**
	 * service ���е�
	 */
	@Pointcut("execution(* com.epo.aop.Book.*(..))")
	public void servicePointcut() {
	}

	//�ڷ�������ʹ��ע���������
//	@Before(value="execution(* com.epo.aop.Book.*(..))")
	@Before(value = "servicePointcut()")
	public void before1() {
		System.out.println("ǰ����ǿ........");
	}
//	@After(value="execution(* com.epo.aop.Book.*(..))")
	@After(value = "servicePointcut()")
	public void after1() {
		System.out.println("������ǿ��������������");
	}
//	@Around(value="execution(* com.epo.aop.Book.*(..))")
	@Around(value = "servicePointcut()")
	public void around1(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
			//����֮ǰ
			System.out.println("����֮ǰ��������");
			//ִ�б���ǿ�ķ���
			proceedingJoinPoint.proceed();
			//����֮��
			System.out.println("����֮�󡣡�����");
	}
}
