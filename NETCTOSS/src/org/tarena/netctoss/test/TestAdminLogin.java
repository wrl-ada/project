package org.tarena.netctoss.test;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.tarena.netctoss.dao.AdminInfoMapperDao;
import org.tarena.netctoss.entity.AdminInfo;

public class TestAdminLogin {
	public static void main(String[] args) {
		String conf = "org/tarena/netctoss/config/applicationContext.xml";
		ApplicationContext context = new ClassPathXmlApplicationContext(conf);
		//利用MapperDao接口名首字母小写，获取Dao实例
		AdminInfoMapperDao dao = context.getBean("adminInfoMapperDao",AdminInfoMapperDao.class);
		AdminInfo admin = new AdminInfo();
		admin.setAdmin_code("zhangsan");
		admin.setPassword("111111");
		AdminInfo info = dao.findByAdminCodeAndPwd(admin);
		System.out.println(info.getName()+" "+info.getAdmin_code());
	}
}
