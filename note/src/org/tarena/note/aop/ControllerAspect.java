package org.tarena.note.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

//����������
@Component//ɨ�赽Spring����
@Aspect//�������ָ��Ϊ����
public class ControllerAspect {
	private Logger logger =
		Logger.getLogger(ControllerAspect.class);
	
	//���������
//	@Pointcut("within(org.tarena.note.web.controller..*)")
//	public void mypoint(){}
	
//	@Before("mypoint()")//ָ��ǰ��֪ͨ,����mypoing()�����
	@Before("within(org.tarena.note.web.controller..*)")
	public void mybefore(){
//		System.out.println("����Controller����");
		logger.debug("����Controller����");
	}
	
}
