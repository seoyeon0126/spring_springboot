package model;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DBConnector {

	//SingleTon 디자인패턴을 활용하여 사용할 것임
	
	private static DBConnector instance = new DBConnector();
	
	private DBConnector() { }	
	
	public static DBConnector getInstance() {
		return instance;
	}
	
		public Connection getConnection() throws Exception {
			//data base연결하기 위해 드라이버 호출
	     Context initCtx = new InitialContext();
		 DataSource ds = (DataSource)initCtx.lookup("java:comp/env/jdbc/iotdb");
		return ds.getConnection(); 
	}
}
