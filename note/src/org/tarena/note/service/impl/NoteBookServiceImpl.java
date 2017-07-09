package org.tarena.note.service.impl;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tarena.note.dao.NoteBookMapperDao;
import org.tarena.note.entity.NoteBook;
import org.tarena.note.entity.NoteResult;
import org.tarena.note.service.NoteBookService;
import org.tarena.note.util.NoteUtil;

@Service
@Transactional
public class NoteBookServiceImpl implements NoteBookService{
	@Resource
	private NoteBookMapperDao bookDao;
	
	@Transactional(readOnly=true)
	public NoteResult loadBooks(String userId) {
		NoteResult result = new NoteResult();
		if(userId==null || "".equals(userId)){
			result.setStatus(1);
			result.setMsg("未查询到数据");
			return result;
		}
		//根据用户ID提取笔记本
		List<NoteBook> list = 
			bookDao.findBooksByUser(userId);
		result.setStatus(0);
		result.setMsg("查询成功");
		result.setData(list);
		return result;
	}

	public NoteResult addBook(
			String userId, String bookName) {
		NoteResult result = new NoteResult();
		//参数数据检测
		if(userId==null || "".equals(userId.trim())){
			result.setStatus(2);
			result.setMsg("用户ID为空");
			return result;
		}
		if(bookName==null || "".equals(bookName.trim())){
			result.setStatus(1);
			result.setMsg("笔记本名为空");
			return result;
		}
		//检测是否重名
		Map<String,Object> params = 
				new HashMap<String, Object>();
		params.put("userId", userId);
		params.put("bookName", bookName);
		NoteBook has_book = 
			bookDao.findByNameAndUser(params);
		if(has_book != null){
			result.setStatus(1);
			result.setMsg("笔记本重名");
			return result;
		}
		//正常添加逻辑
		NoteBook book = new NoteBook();
		book.setCn_user_id(userId);//设置用户ID
		book.setCn_notebook_name(bookName);//设置笔记本名
		String bookId = NoteUtil.createId();
		book.setCn_notebook_id(bookId);//设置笔记本ID
		book.setCn_notebook_type_id("5");//设置笔记本类型
		book.setCn_notebook_desc("");//设置笔记本描述
		Timestamp time = 
			new Timestamp(System.currentTimeMillis());
		book.setCn_notebook_createtime(time);//设置创建时间
		bookDao.save(book);
		result.setStatus(0);
		result.setMsg("创建成功");
		result.setData(book.getCn_notebook_id());
		return result;
	}
	
}
