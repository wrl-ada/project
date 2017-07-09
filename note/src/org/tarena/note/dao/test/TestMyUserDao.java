package org.tarena.note.dao.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.tarena.note.dao.MyUserDao;
import org.tarena.note.entity.MyUser;

public class TestMyUserDao {
	@Test
	public void test1(){
		String conf = "applicationContext.xml";
		ApplicationContext ac =
			new ClassPathXmlApplicationContext(conf);
		MyUserDao userDao = 
			ac.getBean("myUserDao",MyUserDao.class);
		MyUser user = new MyUser();
		user.setUser_name("SSH");
		userDao.save(user);
		//获取记录的ID
		System.out.println(user.getUser_id());
	}
}
