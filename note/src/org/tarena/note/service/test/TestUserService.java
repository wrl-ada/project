package org.tarena.note.service.test;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.tarena.note.entity.NoteResult;
import org.tarena.note.service.UserService;

public class TestUserService {
	@Test
	public void test1(){
		String conf = "applicationContext.xml";
		ApplicationContext ac = 
			new ClassPathXmlApplicationContext(conf);
		UserService userService = 
			ac.getBean("userServiceImpl",UserService.class);
		NoteResult result = userService.checkLogin(
				"tom", "1234");
		Assert.assertEquals(1, result.getStatus());
		Assert.assertEquals("用户不存在", result.getMsg());
	}
	
	@Test
	public void test2(){
		String conf = "applicationContext.xml";
		ApplicationContext ac = 
			new ClassPathXmlApplicationContext(conf);
		UserService userService = 
			ac.getBean("userServiceImpl",UserService.class);
		NoteResult result = userService.checkLogin(
				"demo", "1234");
		Assert.assertEquals(2, result.getStatus());
		Assert.assertEquals("密码不正确", result.getMsg());
	}
	
	@Test
	public void test3(){
		String conf = "applicationContext.xml";
		ApplicationContext ac = 
			new ClassPathXmlApplicationContext(conf);
		UserService userService = 
			ac.getBean("userServiceImpl",UserService.class);
		NoteResult result = userService.checkLogin(
				"demo", "c8837b23ff8aaa8a2dde915473ce0991");
		Assert.assertEquals(0, result.getStatus());
		Assert.assertEquals("登录成功", result.getMsg());
	}
}



