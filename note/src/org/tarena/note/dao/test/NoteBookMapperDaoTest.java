package org.tarena.note.dao.test;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.tarena.note.dao.NoteBookMapperDao;
import org.tarena.note.entity.NoteBook;

public class NoteBookMapperDaoTest {
	@Test
	public void test1(){
		String conf = "applicationContext.xml";
		ApplicationContext ac = 
			new ClassPathXmlApplicationContext(conf);
		NoteBookMapperDao bookDao = 
			ac.getBean("noteBookMapperDao",
					NoteBookMapperDao.class);
		List<NoteBook> books = bookDao.findBooksByUser("48595f52-b22c-4485-9244-f4004255b972");
		for(NoteBook book : books){
			System.out.println(book.getCn_notebook_id()+" "+book.getCn_notebook_name());
		}
	}
}



