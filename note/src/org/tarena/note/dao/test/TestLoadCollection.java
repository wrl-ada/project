package org.tarena.note.dao.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.tarena.note.dao.NoteBookMapperDao;
import org.tarena.note.dao.NoteMapperDao;
import org.tarena.note.dao.UserMapperDao;
import org.tarena.note.entity.Note;
import org.tarena.note.entity.NoteBook;
import org.tarena.note.entity.User;

public class TestLoadCollection {
	@Test
	//���Բ�ѯĳ��User����ʼǱ���Ϣ
	public void test1(){
		String conf = "applicationContext.xml";
		ApplicationContext ac =
			new ClassPathXmlApplicationContext(conf);
		UserMapperDao userDao = 
			ac.getBean("userMapperDao",UserMapperDao.class);
		User user = userDao.findById(
				"48595f52-b22c-4485-9244-f4004255b972");
		System.out.println("-----�û���Ϣ----");
		System.out.println(user.getCn_user_name());
		System.out.println(user.getCn_user_password());
		System.out.println("----�ʼǱ���Ϣ---");
		for(NoteBook book : user.getBooks()){
			System.out.println(book.getCn_notebook_name());
		}
	}
	
	
}






