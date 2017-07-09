package org.tarena.note.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tarena.note.dao.NoteMapperDao;
import org.tarena.note.dao.ShareMapperDao;
import org.tarena.note.entity.Note;
import org.tarena.note.entity.NoteResult;
import org.tarena.note.entity.SearchNote;
import org.tarena.note.entity.Share;
import org.tarena.note.service.NoteService;
import org.tarena.note.util.NoteUtil;

@Service
@Transactional
public class NoteServiceImpl implements NoteService{
	@Resource
	private NoteMapperDao noteDao;
	@Resource
	private ShareMapperDao shareDao;
	
	@Transactional(readOnly=true)//只读事务
	public NoteResult loadNotes(String bookId) {
		NoteResult result = new NoteResult();
		if(bookId != null && !"".equals(bookId.trim())){
			List<Map<String, Object>> list = 
				noteDao.findNotesByBookId(bookId);
			result.setData(list);
		}
		result.setStatus(0);
		result.setMsg("查询成功");
		return result;
	}

	public NoteResult addNote(Note note) {
		NoteResult result = new NoteResult();
		// 笔记名,用户ID,笔记本ID请求传递过来
		//TODO 笔记名称是否为空,是否重名
		String noteId = NoteUtil.createId();
		note.setCn_note_id(noteId);//设置笔记ID
		note.setCn_note_status_id("1");//设置normal状态
		note.setCn_note_type_id("1");//设置normal类型
		note.setCn_note_body("");//设置内容为空
		long time = System.currentTimeMillis();
		note.setCn_note_create_time(time);//设置创建时间
		note.setCn_note_last_modify_time(time);//设置最后修改时间
		noteDao.save(note);//保存操作
		result.setStatus(0);
		result.setMsg("创建笔记成功");
		result.setData(note.getCn_note_id());//返回noteId值
		return result;
	}

	@Transactional(readOnly=true)
	public NoteResult loadNote(String noteId) {
		NoteResult result = new NoteResult();
		if(noteId != null && !"".equals(noteId.trim())){
			Note note = noteDao.findById(noteId);
			result.setData(note);
		}
		result.setStatus(0);
		result.setMsg("查询成功");
		return result;
	}

	public NoteResult updateNote(Note note) {
		NoteResult result = new NoteResult();
		//传入笔记ID,笔记标题,笔记内容
		//TODO 检查数据格式，笔记标题是否重名
		long time = System.currentTimeMillis();
		note.setCn_note_last_modify_time(time);
		noteDao.updateNote(note);//更新笔记
		result.setStatus(0);
		result.setMsg("保存笔记成功");
		return result;
	}

	public NoteResult deleteNote(String noteId) {
		NoteResult result = new NoteResult();
		//将笔记状态设置为删除
		Note note = new Note();
		note.setCn_note_id(noteId);
		note.setCn_note_status_id("2");
		noteDao.updateNote(note);
		result.setStatus(0);
		//如果该笔记分享过，那取消分享
		shareDao.deleteByNoteId(noteId);
		result.setMsg("删除成功");
		return result;
	}

	public NoteResult shareNote(String noteId) {
		NoteResult result = new NoteResult();
		//检测是否被分享过
		Share has_share = shareDao.findByNoteId(noteId);
		if(has_share != null){
			result.setStatus(1);
			result.setMsg("该笔记已被分享过");
			return result;
		}
		//提取要分享笔记的信息
		Note note = noteDao.findById(noteId);
		Share share = new Share();
		String shareId = NoteUtil.createId();
		share.setCn_share_id(shareId);
		share.setCn_note_id(note.getCn_note_id());
		share.setCn_share_title(note.getCn_note_title());
		share.setCn_share_body(note.getCn_note_body());
		shareDao.save(share);//保存到cn_share表
		result.setStatus(0);
		result.setMsg("笔记分享成功");
		return result;
	}

	@Transactional(readOnly=true)
	public NoteResult searchShareNotes(String keyword) {
		NoteResult result = new NoteResult();
		
		String title = "";
		if(keyword==null || "".equals(keyword.trim())){
			title = "%";//如果关键词为空默认取所有
		}else{
			title = "%"+keyword.trim()+"%";
		}
		List<Share> list = shareDao.findLikeTitle(title);
		result.setStatus(0);
		result.setMsg("查询成功");
		result.setData(list);
		return result;
	}

	public NoteResult searchNotes(SearchNote search) {
		NoteResult result = new NoteResult();
		//设置标题参数
		if(search.getTitle() != null && !"".equals(search.getTitle())){
			search.setTitle("%"+search.getTitle()+"%");
		}
		//设置状态
		if("-1".equals(search.getStatus())){
			search.setStatus(null);
		}
		//传入SQL查询
		List<Note> list = noteDao.searchNotes(search);
		result.setStatus(0);
		result.setMsg("查询成功");
		result.setData(list);
		return result;
	}

	public NoteResult batchDelete(String[] ids) {
		NoteResult result = new NoteResult();
		
		if(ids != null && ids.length>0){
			noteDao.batchDeleteNotes(ids);
		}
		result.setStatus(0);
		result.setMsg("批量删除成功");
		return result;
	}

}
