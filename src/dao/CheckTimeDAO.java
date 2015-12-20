package dao;

import java.sql.ResultSet;
import java.sql.Statement;

public class CheckTimeDAO {
	DataBase db = new DataBase();
	public int checkTime(String dateTime,int idCap) {
		String sql = "SELECT * FROM luotdangky WHERE idCap="+idCap+" AND ('"+dateTime+"' BETWEEN timeOpen AND timeClose)";
		int idLDK=0;
		try {
			Statement stm = db.connectDB().createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()){
				idLDK = rs.getInt("idLDK"); 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return idLDK;
	}

}
