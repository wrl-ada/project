package org.tarena.netctoss.test;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.tarena.netctoss.dao.CostMapperDao;
import org.tarena.netctoss.entity.Cost;

public class TestCostDao {
	public static void main(String[] args) {
		String conf = "org/tarena/netctoss/config/applicationContext.xml";
		ApplicationContext context = new ClassPathXmlApplicationContext(conf);
		//利用MapperDao接口名首字母小写，获取Dao实例
		CostMapperDao dao = context.getBean("costMapperDao",CostMapperDao.class);
		List<Cost> list = dao.findAll();
		for(Cost cost:list){
			System.out.println(cost.getId()+" "+cost.getName());
		}
	}
	
}
