package org.tarena.note.dao.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestReturnKey {

	@Test
	public void test1() throws Exception{
		String conf = "applicationContext.xml";
		ApplicationContext ac =
			new ClassPathXmlApplicationContext(conf);
		DataSource ds = 
			ac.getBean("dbcp",DataSource.class);
		Connection conn = ds.getConnection();
		String sql = "insert into " +
				"t_user (user_name) values (?)";
		PreparedStatement pst = 
				conn.prepareStatement(sql,
						Statement.RETURN_GENERATED_KEYS);
		pst.setString(1, "mybatis");
		pst.executeUpdate();
		//获取自动生成的id
		ResultSet rs = pst.getGeneratedKeys();
		rs.next();
		int user_id = rs.getInt(1);
		System.out.println("user_id:"+user_id);
		conn.close();
	}
	
}
