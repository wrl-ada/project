package org.tarena.note.dao.test;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.tarena.note.dao.NoteBookMapperDao;
import org.tarena.note.dao.NoteMapperDao;
import org.tarena.note.entity.NoteBook;

public class NoteMapperDaoTest {
	@Test
	public void test1(){
		String conf = "applicationContext.xml";
		ApplicationContext ac = 
			new ClassPathXmlApplicationContext(conf);
		NoteMapperDao noteDao = 
			ac.getBean("noteMapperDao",
					NoteMapperDao.class);
		List<Map<String,Object>> list = 
			noteDao.findNotesByBookId("d0e7ce0d-4893-4705-a51a-9a73d259bc70");
		for(Map<String,Object> note : list){
			System.out.println(note.get("cn_note_id")+" "+note.get("cn_note_title"));
		}
	}
}



