package org.tarena.note.dao.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.tarena.note.dao.NoteMapperDao;
import org.tarena.note.entity.Note;

public class TestUpdateNote {
	@Test
	//���Ը��±ʼ�����
	public void test1(){
		String conf = "applicationContext.xml";
		ApplicationContext ac =
			new ClassPathXmlApplicationContext(conf);
		NoteMapperDao noteDao = ac.getBean(
			"noteMapperDao",NoteMapperDao.class);
		//����note����
		Note note = new Note();
		note.setCn_note_id("32cb65bd-d3e8-4fcc-b300-eb4d228e5cd8");
		note.setCn_note_title("SpringAOP");
		note.setCn_note_body("SpringAOP���ʹ�÷���");
		note.setCn_note_last_modify_time(System.currentTimeMillis());
		noteDao.updateNote(note);
	}
	
	//����ɾ������
	@Test
	public void test2(){
		String conf = "applicationContext.xml";
		ApplicationContext ac =
			new ClassPathXmlApplicationContext(conf);
		NoteMapperDao noteDao = ac.getBean(
			"noteMapperDao",NoteMapperDao.class);
		//����note����
		Note note = new Note();
		note.setCn_note_id("32cb65bd-d3e8-4fcc-b300-eb4d228e5cd8");
		note.setCn_note_status_id("1");
		noteDao.updateNote(note);
	}
	
	@Test
	//�ʼ�ת��
	public void test3(){
		String conf = "applicationContext.xml";
		ApplicationContext ac =
			new ClassPathXmlApplicationContext(conf);
		NoteMapperDao noteDao = ac.getBean(
			"noteMapperDao",NoteMapperDao.class);
		//����note����
		Note note = new Note();
		note.setCn_note_id("32cb65bd-d3e8-4fcc-b300-eb4d228e5cd8");
		note.setCn_notebook_id("6fe94b5b-3c8c-480b-a8ca-bb71d9a4dedf");
		noteDao.updateNote(note);
	}
}






