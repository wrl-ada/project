package org.tarena.note.service;

import org.tarena.note.entity.NoteResult;
import org.tarena.note.entity.User;

public interface UserService {
	public NoteResult addUser(User user);
	
	public NoteResult  checkLogin(
			String username,String password);
	
	public NoteResult checkLogin(String author)  throws Exception;
}
