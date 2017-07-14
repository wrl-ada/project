package org.tarena.netctoss.dao;

import org.tarena.netctoss.entity.AdminInfo;
import org.tarena.netctoss.util.MyBatisDao;

@MyBatisDao
public interface AdminInfoMapperDao {
	public AdminInfo findByAdminCodeAndPwd(AdminInfo admin);
}
