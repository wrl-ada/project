package org.tarena.note.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

//当做切面用
@Component//扫描到Spring容器
@Aspect//将该组件指定为方面
public class ControllerAspect {
	private Logger logger =
		Logger.getLogger(ControllerAspect.class);
	
	//定义切入点
//	@Pointcut("within(org.tarena.note.web.controller..*)")
//	public void mypoint(){}
	
//	@Before("mypoint()")//指定前置通知,引用mypoing()切入点
	@Before("within(org.tarena.note.web.controller..*)")
	public void mybefore(){
//		System.out.println("进入Controller处理");
		logger.debug("进入Controller处理");
	}
	
}
