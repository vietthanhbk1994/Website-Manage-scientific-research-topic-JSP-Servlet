package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import bean.Cap;

public class CapDAO {
	DataBase db = new DataBase();
	
	public boolean themCap(Cap cap) {
			String query = "INSERT INTO capdetai(tenCap) VALUES (?)";
			int check = 0;
			try {
				PreparedStatement pstm = db.connectDB().prepareStatement(query);
				pstm.setString(1, cap.getTenCap());
				check = pstm.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return (check !=0);
		}
	
	public boolean suaCap(Cap cap) {
		String query = "UPDATE capdetai SET tenCap = ? WHERE idCap = ?";
		int check = 0;
		try {
			PreparedStatement pstm = db.connectDB().prepareStatement(query);
			pstm.setString(1, cap.getTenCap());
			pstm.setInt(2, cap.getIdCap());
			check = pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (check !=0);
			
	}
	
	public boolean xoaCap(int idCap) {
		String query = "DELETE FROM capdetai WHERE idCap = ?";
		int check = 0;
		try {
			PreparedStatement pstm = db.connectDB().prepareStatement(query);
			pstm.setInt(1, idCap);
			check = pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (check !=0);
			
	}
	
	
	public ArrayList<Cap> getListCap(){
	//	public static void main(String args[]){
	//	DataBase db = new DataBase();
			ArrayList<Cap> listCap = new ArrayList<Cap>();
			
			String query = "SELECT * FROM capdetai";
			try {
				Statement stm = db.connectDB().createStatement();
				ResultSet rs = stm.executeQuery(query);
				while(rs.next()){
					Cap cap = new Cap();
					cap.setTenCap(rs.getString("tenCap"));
					cap.setIdCap(rs.getInt("idCap"));
					
					listCap.add(cap);
				}
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			
			return listCap;
	}
}
