package dao;

import java.sql.ResultSet;
import java.sql.Statement;

public class CheckTimeDAO {
	DataBase db = new DataBase();
	public boolean checkTime(String dateTime,int idCap) {
		String sql = "SELECT * FROM capdetai WHERE idCap="+idCap+" AND ('"+dateTime+"' BETWEEN timeOpen AND timeClose)";
		boolean check=false;
		try {
			Statement stm = db.connectDB().createStatement();
			ResultSet rs = stm.executeQuery(sql);
			check=rs.last();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return check;
	}

}
