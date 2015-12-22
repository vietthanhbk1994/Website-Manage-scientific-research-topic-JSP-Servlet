package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import bean.ChuyenNganh;

public class ChuyenNganhDAO {
	DataBase db = new DataBase();
	
	public ChuyenNganh getChuyenNganh(int idChuyenNganh){
		ChuyenNganh chuyenNganh = new ChuyenNganh();
		String query = "SELECT chuyennganh.*,khoa.tenKhoa FROM chuyennganh,khoa WHERE chuyennganh.idKhoa=khoa.idKhoa AND chuyennganh.idChuyenNganh = "+idChuyenNganh;
		try {
			
			Statement stm = db.connectDB().createStatement();
			ResultSet rs = stm.executeQuery(query);
			while(rs.next()){
				chuyenNganh.setIdChuyenNganh(rs.getInt("idChuyenNganh"));
				chuyenNganh.setIdKhoa(rs.getInt("idKhoa"));
				chuyenNganh.setTenChuyenNganh(rs.getString("tenChuyenNganh"));
				chuyenNganh.setTenKhoa(rs.getString("tenKhoa"));
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return chuyenNganh;
	}
	public ArrayList<ChuyenNganh> getlistChuyenNganh(){
		ArrayList<ChuyenNganh> listChuyenNganh = new ArrayList<ChuyenNganh>();
		String query = "SELECT chuyennganh.*,khoa.tenKhoa FROM chuyennganh,khoa WHERE chuyennganh.idKhoa=khoa.idKhoa";
		try {
			
			Statement stm = db.connectDB().createStatement();
			ResultSet rs = stm.executeQuery(query);
			while(rs.next()){
				ChuyenNganh chuyenNganh = new ChuyenNganh();
				chuyenNganh.setIdChuyenNganh(rs.getInt("idChuyenNganh"));
				chuyenNganh.setIdKhoa(rs.getInt("idKhoa"));
				chuyenNganh.setTenChuyenNganh(rs.getString("tenChuyenNganh"));
				chuyenNganh.setTenKhoa(rs.getString("tenKhoa"));
				listChuyenNganh.add(chuyenNganh);
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return listChuyenNganh;
	}
	public boolean isExistChuyenNganh(String tenChuyenNganh) {
		int check = 0;
		String sql = "SELECT * FROM chuyennganh WHERE tenChuyenNganh = '"
				+ tenChuyenNganh + "'";
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
	public boolean xoaChuyenNganh(String[] listXoaChuyenNganh) {
		String listChuyenNganh="";
		if(listXoaChuyenNganh.length==1){
			listChuyenNganh += listXoaChuyenNganh[listXoaChuyenNganh.length-1];
		}
		else{
			for(int i=0;i<listXoaChuyenNganh.length - 1;i++){
				listChuyenNganh += listXoaChuyenNganh[i] +",";
			}
			listChuyenNganh += listXoaChuyenNganh[listXoaChuyenNganh.length-1];
		}
		String query1 = "DELETE FROM chuyennganh WHERE idChuyenNganh IN ("+listChuyenNganh +")";
	
		int check1 =0;
		try {
			Statement st = db.connectDB().createStatement();
			check1 = st.executeUpdate(query1);
			
			st.close();
			db.connectDB().close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(check1 == 0) return false;
		return true;
	}
	public boolean suaChuyenNganh(ChuyenNganh ChuyenNganh) {
		String query = "UPDATE chuyennganh SET idKhoa = ? , tenChuyenNganh = ? WHERE idChuyenNganh = ?";
		int check = 0;
//		System.out.println("id khoa: "+ChuyenNganh.getIdKhoa());
//		System.out.println("id chuyennganh: "+ChuyenNganh.getIdChuyenNganh());
//		System.out.println("ten chuyen nganh: "+ChuyenNganh.getTenChuyenNganh());
		try {
			PreparedStatement pstm = db.connectDB().prepareStatement(query);
			pstm.setInt(1, ChuyenNganh.getIdKhoa());
			pstm.setString(2, ChuyenNganh.getTenChuyenNganh());
			pstm.setInt(3, ChuyenNganh.getIdChuyenNganh());
			check = pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (check !=0);
	}
	public boolean themChuyenNganh(int idKhoa, String tenChuyenNganh) {
		String query = "INSERT INTO chuyennganh(tenChuyenNganh, idKhoa) VALUES (?,?)";
		//System.out.println(tenChuyenNganh);
		int check = 0;
		try {
			PreparedStatement pstm = db.connectDB().prepareStatement(query);
			pstm.setString(1, tenChuyenNganh);
			pstm.setInt(2, idKhoa);
			check = pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (check !=0);
	}
}
