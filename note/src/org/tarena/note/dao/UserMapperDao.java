package org.tarena.note.dao;

import java.util.Map;

import org.tarena.note.entity.User;

public interface UserMapperDao {
	public User findById(String userId);
	public int save(User user);
	public User findByName(String name);
	public int updateToken(Map<String,Object> params);
}


