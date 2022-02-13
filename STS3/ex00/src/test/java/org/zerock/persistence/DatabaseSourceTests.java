package org.zerock.persistence;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.sql.Connection;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.sample.SampleTests;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class DatabaseSourceTests {

	@Autowired
	private DataSource ds;
	
	@Autowired
	private SqlSessionFactory sessionFactory;

	//hikari  커넥션 풀
	@Test
	public void testConnection()  {
		long start = System.currentTimeMillis();
		for(int i = 0; i <= 1 ; i++) {
		
			try(Connection con = ds.getConnection()){
		
				log.info(con);
			
			}catch(Exception e) {
				
				e.printStackTrace(); //자세한 에러 메세지를 보여줌
				
				log.error(e.getMessage());
			}
		}
		
		long end = System.currentTimeMillis();
		
		log.info("--------------------------");
		log.info(end - start);


	}

	// mybatis
	@Test
	public void testConnection2() {
		
		try(SqlSession session = sessionFactory.openSession()) {
			
			Connection con = session.getConnection();
			
			log.info("=================");
			log.info(session);
			log.info(con);
		
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}

























