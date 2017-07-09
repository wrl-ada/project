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
	
	@Transactional(readOnly=true)//ֻ������
	public NoteResult loadNotes(String bookId) {
		NoteResult result = new NoteResult();
		if(bookId != null && !"".equals(bookId.trim())){
			List<Map<String, Object>> list = 
				noteDao.findNotesByBookId(bookId);
			result.setData(list);
		}
		result.setStatus(0);
		result.setMsg("��ѯ�ɹ�");
		return result;
	}

	public NoteResult addNote(Note note) {
		NoteResult result = new NoteResult();
		// �ʼ���,�û�ID,�ʼǱ�ID���󴫵ݹ���
		//TODO �ʼ������Ƿ�Ϊ��,�Ƿ�����
		String noteId = NoteUtil.createId();
		note.setCn_note_id(noteId);//���ñʼ�ID
		note.setCn_note_status_id("1");//����normal״̬
		note.setCn_note_type_id("1");//����normal����
		note.setCn_note_body("");//��������Ϊ��
		long time = System.currentTimeMillis();
		note.setCn_note_create_time(time);//���ô���ʱ��
		note.setCn_note_last_modify_time(time);//��������޸�ʱ��
		noteDao.save(note);//�������
		result.setStatus(0);
		result.setMsg("�����ʼǳɹ�");
		result.setData(note.getCn_note_id());//����noteIdֵ
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
		result.setMsg("��ѯ�ɹ�");
		return result;
	}

	public NoteResult updateNote(Note note) {
		NoteResult result = new NoteResult();
		//����ʼ�ID,�ʼǱ���,�ʼ�����
		//TODO ������ݸ�ʽ���ʼǱ����Ƿ�����
		long time = System.currentTimeMillis();
		note.setCn_note_last_modify_time(time);
		noteDao.updateNote(note);//���±ʼ�
		result.setStatus(0);
		result.setMsg("����ʼǳɹ�");
		return result;
	}

	public NoteResult deleteNote(String noteId) {
		NoteResult result = new NoteResult();
		//���ʼ�״̬����Ϊɾ��
		Note note = new Note();
		note.setCn_note_id(noteId);
		note.setCn_note_status_id("2");
		noteDao.updateNote(note);
		result.setStatus(0);
		//����ñʼǷ��������ȡ������
		shareDao.deleteByNoteId(noteId);
		result.setMsg("ɾ���ɹ�");
		return result;
	}

	public NoteResult shareNote(String noteId) {
		NoteResult result = new NoteResult();
		//����Ƿ񱻷����
		Share has_share = shareDao.findByNoteId(noteId);
		if(has_share != null){
			result.setStatus(1);
			result.setMsg("�ñʼ��ѱ������");
			return result;
		}
		//��ȡҪ����ʼǵ���Ϣ
		Note note = noteDao.findById(noteId);
		Share share = new Share();
		String shareId = NoteUtil.createId();
		share.setCn_share_id(shareId);
		share.setCn_note_id(note.getCn_note_id());
		share.setCn_share_title(note.getCn_note_title());
		share.setCn_share_body(note.getCn_note_body());
		shareDao.save(share);//���浽cn_share��
		result.setStatus(0);
		result.setMsg("�ʼǷ���ɹ�");
		return result;
	}

	@Transactional(readOnly=true)
	public NoteResult searchShareNotes(String keyword) {
		NoteResult result = new NoteResult();
		
		String title = "";
		if(keyword==null || "".equals(keyword.trim())){
			title = "%";//����ؼ���Ϊ��Ĭ��ȡ����
		}else{
			title = "%"+keyword.trim()+"%";
		}
		List<Share> list = shareDao.findLikeTitle(title);
		result.setStatus(0);
		result.setMsg("��ѯ�ɹ�");
		result.setData(list);
		return result;
	}

	public NoteResult searchNotes(SearchNote search) {
		NoteResult result = new NoteResult();
		//���ñ������
		if(search.getTitle() != null && !"".equals(search.getTitle())){
			search.setTitle("%"+search.getTitle()+"%");
		}
		//����״̬
		if("-1".equals(search.getStatus())){
			search.setStatus(null);
		}
		//����SQL��ѯ
		List<Note> list = noteDao.searchNotes(search);
		result.setStatus(0);
		result.setMsg("��ѯ�ɹ�");
		result.setData(list);
		return result;
	}

	public NoteResult batchDelete(String[] ids) {
		NoteResult result = new NoteResult();
		
		if(ids != null && ids.length>0){
			noteDao.batchDeleteNotes(ids);
		}
		result.setStatus(0);
		result.setMsg("����ɾ���ɹ�");
		return result;
	}

}
