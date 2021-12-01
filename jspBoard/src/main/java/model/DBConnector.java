package model;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DBConnector {

	//SingleTon ������������ Ȱ���Ͽ� ����� ����
	
	private static DBConnector instance = new DBConnector();
	
	private DBConnector() { }	
	
	public static DBConnector getInstance() {
		return instance;
	}
	
		public Connection getConnection() throws Exception {
			//data base�����ϱ� ���� ����̹� ȣ��
	     Context initCtx = new InitialContext();
		 DataSource ds = (DataSource)initCtx.lookup("java:comp/env/jdbc/iotdb");
		return ds.getConnection(); 
	}
}
