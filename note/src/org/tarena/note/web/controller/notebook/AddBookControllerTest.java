package org.tarena.note.web.controller.notebook;
 
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
public class AddBookControllerTest {

	private MockMvc mockMvc;
	
	@Resource
	private AddBookController controller;
	
	@Before
	public void init(){
		mockMvc = MockMvcBuilders.
				standaloneSetup(controller).build();
	}
	
	@Test
	public void test1() throws Exception{
		
		//发送执行请求
		MvcResult mvcresult = mockMvc.perform(
			MockMvcRequestBuilders
			.post("/notebook/add.do")
			.param("userId", "6b8a2d44-9c2f-4771-b7a4-1151b1a23041")
			.param("bookName", "java web")
		).andReturn();
		//获取返回结果显示
		String content = mvcresult.getResponse()
					.getContentAsString();
		//采用断言比对实际与预期结果
		ObjectMapper mapper = new ObjectMapper();
		NoteResult noteResult = 
			mapper.readValue(content, NoteResult.class);
		Assert.assertEquals(1, noteResult.getStatus());
		Assert.assertEquals("笔记本重名", noteResult.getMsg());
	}
	
	@Test
	public void test2() throws Exception{
		
		//发送执行请求
		MvcResult mvcresult = mockMvc.perform(
			MockMvcRequestBuilders
			.post("/notebook/add.do")
			.param("userId", "6b8a2d44-9c2f-4771-b7a4-1151b1a23041")
			.param("bookName", "")
		).andReturn();
		//获取返回结果显示
		String content = mvcresult.getResponse()
					.getContentAsString();
		//采用断言比对实际与预期结果
		ObjectMapper mapper = new ObjectMapper();
		NoteResult noteResult = 
			mapper.readValue(content, NoteResult.class);
		Assert.assertEquals(1, noteResult.getStatus());
		Assert.assertEquals("笔记本名为空", noteResult.getMsg());
	}
	
	@Test
	public void test3() throws Exception{
		
		//发送执行请求
		MvcResult mvcresult = mockMvc.perform(
			MockMvcRequestBuilders
			.post("/notebook/add.do")
			.param("userId", "")
			.param("bookName", "")
		).andReturn();
		//获取返回结果显示
		String content = mvcresult.getResponse()
					.getContentAsString();
		//采用断言比对实际与预期结果
		ObjectMapper mapper = new ObjectMapper();
		NoteResult noteResult = 
			mapper.readValue(content, NoteResult.class);
		Assert.assertEquals(2, noteResult.getStatus());
		Assert.assertEquals("用户ID为空", noteResult.getMsg());
	}
	
	@Test
	public void test4() throws Exception{
		
		//发送执行请求
		MvcResult mvcresult = mockMvc.perform(
			MockMvcRequestBuilders
			.post("/notebook/add.do")
			.param("userId", "6b8a2d44-9c2f-4771-b7a4-1151b1a23041")
			.param("bookName", "Spring框架")
		).andReturn();
		//获取返回结果显示
		String content = mvcresult.getResponse()
					.getContentAsString();
		//采用断言比对实际与预期结果
		ObjectMapper mapper = new ObjectMapper();
		NoteResult noteResult = 
			mapper.readValue(content, NoteResult.class);
		Assert.assertEquals(0, noteResult.getStatus());
		Assert.assertEquals("创建成功", noteResult.getMsg());
		Assert.assertNotNull(noteResult.getData());
	}
	
}



