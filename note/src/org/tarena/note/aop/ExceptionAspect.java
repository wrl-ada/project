package org.tarena.note.aop;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ExceptionAspect {

	Logger logger = Logger.getLogger(ExceptionAspect.class);
	
//	@Pointcut("within(org.tarena.note.service..*)")
//	public void p1(){}
	
//	@AfterThrowing(pointcut="p1()",throwing="e")
	@AfterThrowing(pointcut="within(org.tarena.note.service..*)",throwing="e")
	public void execute(Exception e){
		
		//��¼Service�쳣��Ϣ����(�ļ���¼)
		logger.error("�����쳣��"+e);
		StackTraceElement[] st = e.getStackTrace();
		for(StackTraceElement el : st){
			logger.error(el);
		}
	}
	
	
}
