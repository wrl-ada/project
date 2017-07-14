package org.tarena.netctoss.dao;

import java.util.List;

import org.tarena.netctoss.entity.Role;
import org.tarena.netctoss.util.MyBatisDao;

@MyBatisDao
public interface RoleMapperDao {
	public List<Role> findAll();
	public void saveRole(Role role);
	public void deleteRole(int id);
}
