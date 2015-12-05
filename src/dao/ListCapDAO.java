package dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import bean.Cap;

public class ListCapDAO {
	DataBase db = new DataBase();
	public ArrayList<Cap>getListCap(){
		ArrayList<Cap> ListCap = new ArrayList<Cap>();
		String sql = "SELECT * FROM capdetai";
		try {
			Statement stm = db.connectDB().createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()){
				Cap cap = new Cap();
				
				cap.setIdCap(rs.getInt(1)); 
				cap.setTenCap(rs.getString(2));
				cap.setTimeOpen(rs.getString(3));
				cap.setTimeClose(rs.getString(4));
				cap.setNam(rs.getInt(5));
				
				
				ListCap.add(cap);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ListCap;
	}
}
