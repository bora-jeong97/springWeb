package org.zerock.persistence;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

import lombok.extern.log4j.Log4j;

@Log4j
public class JDBCTests {

	// jdbc 정상 작동 test
	@Test
	public void testConnection() throws Exception {
		
		Class clz = Class.forName("oracle.jdbc.driver.OracleDriver");
		
		long start = System.currentTimeMillis();
		
		for(int i = 0; i <= 1; i++) { //2485
		
		// log.info(clz);
		Connection con =
				DriverManager.getConnection(
						"jdbc:oracle:thin:@localhost:1521:XE",
						"c##book_ex","book_ex");
		log.info(con);
		
		con.close(); //bad code 원래는 finally로 해주는게 좋다
		}
		
		long end = System.currentTimeMillis();
		
		log.info("--------------------------");
		log.info(end - start);
	}
}
