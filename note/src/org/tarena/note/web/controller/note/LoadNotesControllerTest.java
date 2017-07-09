package org.tarena.note.web.controller.note;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.tarena.note.entity.NoteResult;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)//基于junit启动
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class LoadNotesControllerTest {

	private MockMvc mockMvc;
	@Resource
	private LoadNotesController controller;
	
	@Before
	public void init(){
		mockMvc = MockMvcBuilders.
				standaloneSetup(controller).build();
	}
	
	@Test//测试bookId有效的情况
	public void test1() throws Exception{
		MvcResult result = mockMvc.perform(
				MockMvcRequestBuilders
				.post("/note/loadnotes.do")
				.param("bookId", "d0e7ce0d-4893-4705-a51a-9a73d259bc70")
		).andReturn();
		
		String content  = result.getResponse().getContentAsString();
		ObjectMapper mapper = new ObjectMapper();
		NoteResult noteResult = 
			mapper.readValue(content, NoteResult.class);
		//断言
		Assert.assertEquals(0, noteResult.getStatus());
		Assert.assertNotNull( noteResult.getData());
	}
	
	@Test//测试bookId为空的情况
	public void test2() throws Exception{
		MvcResult result = mockMvc.perform(
				MockMvcRequestBuilders
				.post("/note/loadnotes.do")
				.param("bookId", "")
		).andReturn();
		
		String content  = result.getResponse().getContentAsString();
		ObjectMapper mapper = new ObjectMapper();
		NoteResult noteResult = 
			mapper.readValue(content, NoteResult.class);
		//断言
		Assert.assertEquals(0, noteResult.getStatus());
		Assert.assertNull( noteResult.getData());
	}
	
}


