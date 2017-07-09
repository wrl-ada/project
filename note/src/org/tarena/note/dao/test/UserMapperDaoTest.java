package org.tarena.note.dao.test;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.tarena.note.dao.UserMapperDao;
import org.tarena.note.entity.User;

public class UserMapperDaoTest {
	@Test
	public void test1(){
		String conf = "applicationContext.xml";
		ApplicationContext ac =
			new ClassPathXmlApplicationContext(conf);
		UserMapperDao userDao = ac.getBean(
				"userMapperDao",UserMapperDao.class);
		User user = userDao.findByName("demo1");
		//断言
		Assert.assertNull(user);//预期user是null
	}
	
	@Test
	public void test2(){
		String conf = "applicationContext.xml";
		ApplicationContext ac =
			new ClassPathXmlApplicationContext(conf);
		UserMapperDao userDao = ac.getBean(
				"userMapperDao",UserMapperDao.class);
		User user = userDao.findByName("demo");
		//断言
		Assert.assertNotNull(user);//预期user不是null
	}
}




