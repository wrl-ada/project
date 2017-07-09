package org.tarena.note.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.tarena.note.dao.UserMapperDao;
import org.tarena.note.entity.NoteResult;
import org.tarena.note.entity.User;
import org.tarena.note.service.UserService;
import org.tarena.note.util.NoteUtil;

@Service
@Transactional
public class UserServiceImpl implements UserService{
	@Resource
	private UserMapperDao userDao;
	
	@Transactional
	public NoteResult checkLogin(
			String username, String password) {
		NoteResult result = new NoteResult();
		//�ж��û���
		User user = userDao.findByName(username);
		if(user == null){
			result.setStatus(1);
			result.setMsg("�û�������");
			return result;
		}
		//�ж�����
		String md5_password = NoteUtil.md5(password);
		if(!user.getCn_user_password().equals(md5_password)){
			result.setStatus(2);
			result.setMsg("���벻��ȷ");
			return result;
		}
		//�ɹ�
		//����һ�����ƺ�
		String token = NoteUtil.createToken();
		Map<String,Object> params = 
				new HashMap<String, Object>();
		params.put("userToken", token);
		params.put("userId", user.getCn_user_id());
		userDao.updateToken(params);//����db�����ƺ�
		result.setStatus(0);//0��ȷ
		result.setMsg("��¼�ɹ�");
		result.setData(params);//���ݵ��ͻ���
		return result;
	}

	@Transactional
	public NoteResult checkLogin(String author) throws Exception {
		String base64_msg = author.substring(author.indexOf(' '));
		System.out.println("base64_msg:"+base64_msg);
		byte[] output = Base64.decodeBase64(base64_msg.getBytes());
		String msg = new String(output,"UTF-8");
		System.out.println("msg:"+msg);
		String[] msg_arr = msg.split(":");
		String username = msg_arr[0];
		String password = msg_arr[1];
		System.out.println("username:"+username);
		System.out.println("password:"+password);
		return checkLogin(username,password);
	}


//	addUser������һ������,û�쳣�ύ;���쳣�ع�
	public NoteResult addUser(User user) {
		NoteResult result = new NoteResult();
		//�û���,�ǳ�,������������
		//���ݼ��(��ʽ���,Ψһ�Լ��)
		if("".equals(user.getCn_user_name()) 
				||user.getCn_user_name()==null){
			result.setStatus(1);
			result.setMsg("�û�������Ϊ��");
			return result;
		}
		//TODO �������2���ǳ�3�Ƿ�Ϊ��
		
		//�û���Ψһ�Լ��
		User usr = userDao.findByName(
					user.getCn_user_name());
		if(usr != null){
			result.setStatus(1);
			result.setMsg("�û����ѱ�ռ��");
			return result;
		}
		//����ID
		String userId = NoteUtil.createId();
		user.setCn_user_id(userId);
		//�������
		String md5_pwd = NoteUtil.md5(
						user.getCn_user_password());
		user.setCn_user_password(md5_pwd);
		//ִ��cn_user��Ӳ���
		userDao.save(user);
		//ģ��һ���쳣
		String s = null;s.length();
		result.setStatus(0);
		result.setMsg("ע��ɹ�");
		return result;
	}

}
