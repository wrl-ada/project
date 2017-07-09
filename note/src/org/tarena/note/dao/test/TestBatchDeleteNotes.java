package org.tarena.note.dao.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.tarena.note.dao.NoteMapperDao;
import org.tarena.note.entity.Note;

public class TestBatchDeleteNotes {
	@Test
	//≤‚ ‘…æ≥˝± º«
	public void test1(){
		String conf = "applicationContext.xml";
		ApplicationContext ac =
			new ClassPathXmlApplicationContext(conf);
		NoteMapperDao noteDao = ac.getBean(
			"noteMapperDao",NoteMapperDao.class);
//		String[] ids = {"fed920a0-573c-46c8-ae4e-368397846efd",
//				"ffc2cf21-78ed-4647-adb4-3e545613ef26",
//				"ss19055-30e8-4cdc-bfac-97c6bad9518f"};
		List<String> ids = new ArrayList<String>();
		ids.add("f4cce0e5-ba14-4deb-bc04-e396f53c40f3");
		ids.add("f74d03aa-d01d-4989-95e8-4757d6ca8a2a");
		ids.add("f3ad58a7-eb14-4766-90b2-25d5fd22113b");
		noteDao.batchDeleteNotes1(ids);
	}
	
	
}






