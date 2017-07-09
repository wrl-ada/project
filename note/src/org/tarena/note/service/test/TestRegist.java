package org.tarena.note.service.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.tarena.note.entity.NoteResult;
import org.tarena.note.entity.User;
import org.tarena.note.service.UserService;

public class TestRegist {
	private UserService userService;
	
	@Before//��ִ��ÿһ��Test����ǰ������һ��
	public void init(){
		String conf = "applicationContext.xml";
		ApplicationContext ac = 
			new ClassPathXmlApplicationContext(conf);
		userService = 
			ac.getBean("userServiceImpl",UserService.class);
	}
	
	@Test
	public void test1(){
		User user = new User();
		System.out.println("userService=="
				+userService.getClass().getName());
		NoteResult result = userService.addUser(user);
		//����
		Assert.assertEquals(1, result.getStatus());
		Assert.assertEquals("�û�������Ϊ��", result.getMsg());
	}
	
	@Test
	public void test2(){
		User user = new User();
		user.setCn_user_name("demo");
		user.setCn_user_password("1234");
		user.setCn_user_desc("��ë");
		NoteResult result = userService.addUser(user);
		//����
		Assert.assertEquals(1, result.getStatus());
		Assert.assertEquals("�û����ѱ�ռ��", result.getMsg());
	}
	
	@Test
	public void test3(){
		User user = new User();
		user.setCn_user_name("damao");
		user.setCn_user_password("1234");
		user.setCn_user_desc("��ë");
		NoteResult result = userService.addUser(user);
		//����
		Assert.assertEquals(0, result.getStatus());
		Assert.assertEquals("ע��ɹ�", result.getMsg());
	}
}




