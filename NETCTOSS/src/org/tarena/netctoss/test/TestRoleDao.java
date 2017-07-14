package org.tarena.netctoss.test;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.tarena.netctoss.dao.CostMapperDao;
import org.tarena.netctoss.dao.RoleMapperDao;
import org.tarena.netctoss.entity.Cost;
import org.tarena.netctoss.entity.Role;
import org.tarena.netctoss.entity.RolePrivilege;

public class TestRoleDao {
	public static void main(String[] args) {
		String conf = "org/tarena/netctoss/config/applicationContext.xml";
		ApplicationContext context = new ClassPathXmlApplicationContext(conf);
		//利用MapperDao接口名首字母小写，获取Dao实例
		RoleMapperDao dao = context.getBean("roleMapperDao",RoleMapperDao.class);
		List<Role> list = dao.findAll();
		for(Role role:list){
			System.out.println("-------");
			System.out.println(role.getId()+" "+role.getName()+" "+role.getPrisName());
			for(RolePrivilege rp : role.getPris()){
				System.out.println(rp.getRole_id()+" "+rp.getPrivilege_id());
			}
			
		}
//		Role role = new Role();
//		role.setName("管理员");
//		List<RolePrivilege> pris = new ArrayList<RolePrivilege>();
//		pris.add(new RolePrivilege(2,1));	
//		role.setPris(pris);
//		dao.saveRole(role);
	}
	
}
