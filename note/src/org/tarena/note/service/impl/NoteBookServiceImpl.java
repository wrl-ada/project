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
			result.setMsg("δ��ѯ������");
			return result;
		}
		//�����û�ID��ȡ�ʼǱ�
		List<NoteBook> list = 
			bookDao.findBooksByUser(userId);
		result.setStatus(0);
		result.setMsg("��ѯ�ɹ�");
		result.setData(list);
		return result;
	}

	public NoteResult addBook(
			String userId, String bookName) {
		NoteResult result = new NoteResult();
		//�������ݼ��
		if(userId==null || "".equals(userId.trim())){
			result.setStatus(2);
			result.setMsg("�û�IDΪ��");
			return result;
		}
		if(bookName==null || "".equals(bookName.trim())){
			result.setStatus(1);
			result.setMsg("�ʼǱ���Ϊ��");
			return result;
		}
		//����Ƿ�����
		Map<String,Object> params = 
				new HashMap<String, Object>();
		params.put("userId", userId);
		params.put("bookName", bookName);
		NoteBook has_book = 
			bookDao.findByNameAndUser(params);
		if(has_book != null){
			result.setStatus(1);
			result.setMsg("�ʼǱ�����");
			return result;
		}
		//��������߼�
		NoteBook book = new NoteBook();
		book.setCn_user_id(userId);//�����û�ID
		book.setCn_notebook_name(bookName);//���ñʼǱ���
		String bookId = NoteUtil.createId();
		book.setCn_notebook_id(bookId);//���ñʼǱ�ID
		book.setCn_notebook_type_id("5");//���ñʼǱ�����
		book.setCn_notebook_desc("");//���ñʼǱ�����
		Timestamp time = 
			new Timestamp(System.currentTimeMillis());
		book.setCn_notebook_createtime(time);//���ô���ʱ��
		bookDao.save(book);
		result.setStatus(0);
		result.setMsg("�����ɹ�");
		result.setData(book.getCn_notebook_id());
		return result;
	}
	
}
