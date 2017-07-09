package org.tarena.note.dao.test;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.tarena.note.dao.NoteBookMapperDao;
import org.tarena.note.entity.NoteBook;

public class TestLoadAssociation {
//	@Test
	public void test1(){
		String conf = "applicationContext.xml";
		ApplicationContext ac =
			new ClassPathXmlApplicationContext(conf);
		NoteBookMapperDao bookDao = 
			ac.getBean("noteBookMapperDao",NoteBookMapperDao.class);
		NoteBook book = bookDao.findById(
				"a544447a-bc6c-4765-a3d8-2d8bce4bd571");
		System.out.println(book.getCn_notebook_name());
		System.out.println(book.getUser().getCn_user_name());
	}
	
	@Test
	public void test2(){
		String conf = "applicationContext.xml";
		ApplicationContext ac =
			new ClassPathXmlApplicationContext(conf);
		NoteBookMapperDao bookDao = 
			ac.getBean("noteBookMapperDao",NoteBookMapperDao.class);
		List<NoteBook> list = bookDao.findAll();
		for(NoteBook book : list){
			System.out.println(book.getCn_notebook_id()+" "
					+book.getCn_notebook_name()+" "
					+book.getUser().getCn_user_name());
		}
	}
}





