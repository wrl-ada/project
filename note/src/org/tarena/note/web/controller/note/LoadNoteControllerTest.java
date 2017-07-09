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
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.tarena.note.entity.NoteResult;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)//»ùÓÚjunitÆô¶¯
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class LoadNoteControllerTest {

	private MockMvc mockMvc;
	@Resource
	private LoadNoteController controller;
	
	@Before
	public void init(){
		mockMvc = MockMvcBuilders.
				standaloneSetup(controller).build();
	}
	
	@Test
	public void test1() throws Exception{
		MvcResult mvcResult = mockMvc.perform(
				MockMvcRequestBuilders
				.post("/note/loadnote.do")
		).andDo(MockMvcResultHandlers.print())
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andReturn();
		
		String content = 
			mvcResult.getResponse().getContentAsString();
		ObjectMapper mapper = new ObjectMapper();
		NoteResult result = 
			mapper.readValue(content, NoteResult.class);
		Assert.assertEquals(0, result.getStatus());
		Assert.assertNull(result.getData());
	}
	
	
	
	
	
	
}
