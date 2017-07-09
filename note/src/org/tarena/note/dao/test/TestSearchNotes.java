package org.tarena.note.dao.test;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.tarena.note.dao.NoteMapperDao;
import org.tarena.note.entity.Note;
import org.tarena.note.entity.SearchNote;

public class TestSearchNotes {
	@Test
	public void test1(){
		String conf = "applicationContext.xml";
		ApplicationContext ac =
			new ClassPathXmlApplicationContext(conf);
		NoteMapperDao dao = 
			ac.getBean("noteMapperDao",NoteMapperDao.class);
		//查询
		SearchNote search = new SearchNote();
//		search.setTitle("%java%");
		search.setStatus("1");
		long endTime = 
			java.sql.Date.valueOf("2015-08-18").getTime();
		long beginTime = java.sql.Date.valueOf("2015-06-06").getTime();
//		search.setBeginTime(beginTime);
//		search.setEndTime(endTime);
		List<Note> list = dao.searchNotes(search);
		//显示结果
		for(Note note : list){
			System.out.println(note.getCn_note_title());
		}
		System.out.println("一共有"+list.size()+"条记录");
	
	
	
	}
}
