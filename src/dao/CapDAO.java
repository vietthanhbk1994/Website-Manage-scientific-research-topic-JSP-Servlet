package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import bean.Cap;

public class CapDAO {
	DataBase db = new DataBase();
	
	public boolean isExistCap(String tenCap) {
		int check = 0;
		String sql = "SELECT * FROM cap WHERE tenCap = '"
				+ tenCap + "'";
		try {
			Statement stm = db.connectDB().createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()){
				check = rs.getRow();
			}
			stm.close();
			db.connectDB().close();
			//System.out.println("so hang la:"+check);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (check != 0);
	}
	
	public boolean themCap(String tenCap) {
			String query = "INSERT INTO capdetai(tenCap) VALUES (?)";
			int check = 0;
			try {
				PreparedStatement pstm = db.connectDB().prepareStatement(query);
				pstm.setString(1, tenCap);
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
	
	public boolean xoaCap(String[] listXoaCap) {
		String listCap="";
		if(listXoaCap.length==1){
			listCap += listXoaCap[listXoaCap.length-1];
		}
		else{
			for(int i=0;i<listXoaCap.length - 1;i++){
				listCap += listXoaCap[i] +",";
			}
			listCap += listXoaCap[listXoaCap.length-1];
		}
		String query = "DELETE FROM capdetai WHERE idCap IN ("+listCap +")";
		int check =0;
		try {
			Statement st = db.connectDB().createStatement();
			check = st.executeUpdate(query);
			st.close();
			db.connectDB().close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(check == 0) return false;
		return true;
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
