package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DataBase {
	public Connection connectDB(){
		Connection cnn=null;
		try {
			// nạp driver
			Class.forName("com.mysql.jdbc.Driver");
			// lấy chuỗi kết nối
			cnn = DriverManager.getConnection("jdbc:mysql://localhost/cnpm4.0?characterEncoding=UTF-8","root","");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cnn;
	}
}
