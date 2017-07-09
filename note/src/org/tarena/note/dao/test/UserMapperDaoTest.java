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
		//����
		Assert.assertNull(user);//Ԥ��user��null
	}
	
	@Test
	public void test2(){
		String conf = "applicationContext.xml";
		ApplicationContext ac =
			new ClassPathXmlApplicationContext(conf);
		UserMapperDao userDao = ac.getBean(
				"userMapperDao",UserMapperDao.class);
		User user = userDao.findByName("demo");
		//����
		Assert.assertNotNull(user);//Ԥ��user����null
	}
}




