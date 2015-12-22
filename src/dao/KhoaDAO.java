package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import bean.ChuyenNganh;
import bean.Khoa;

public class KhoaDAO {
	DataBase db = new DataBase();
	
	public boolean isExistKhoa(String tenKhoa) {
		int check = 0;
		String sql = "SELECT * FROM khoa WHERE tenKhoa = '"
				+ tenKhoa + "'";
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
	
	public Khoa getKhoa(int idKhoa){
		
		ArrayList<ChuyenNganh> listChuyenNganh = new ArrayList<ChuyenNganh>();
		Khoa khoa = new Khoa();
		String query = "SELECT * FROM khoa LEFT OUTER JOIN chuyennganh ON khoa.idKhoa=chuyennganh.idKhoa WHERE khoa.idKhoa!=9 AND khoa.idKhoa = "+idKhoa;
		try {
			
			Statement stm = db.connectDB().createStatement();
			ResultSet rs = stm.executeQuery(query);
			while(rs.next()){
				
				khoa.setIdKhoa(rs.getInt("idKhoa"));
				khoa.setTenKhoa(rs.getString("tenKhoa"));
		
				ChuyenNganh chuyenNganh = new ChuyenNganh();
				chuyenNganh.setIdChuyenNganh(rs.getInt("idChuyenNganh"));
				chuyenNganh.setIdKhoa(rs.getInt("idKhoa"));
				chuyenNganh.setTenChuyenNganh(rs.getString("tenChuyenNganh"));
				chuyenNganh.setTenKhoa(rs.getString("tenKhoa"));
				listChuyenNganh.add(chuyenNganh);
			}
			stm.close();
			db.connectDB().close();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		khoa.setListChuyenNganh(listChuyenNganh);
		return khoa;
	}
	public ArrayList<Khoa> getlistKhoaChuyenNganh(){
		ArrayList<Khoa> listKhoa = new ArrayList<Khoa>();
		String query = "SELECT idKhoa FROM khoa WHERE idKhoa !=9";
		try {
			Statement stm = db.connectDB().createStatement();
			ResultSet rs = stm.executeQuery(query);
			KhoaDAO khoadao = new KhoaDAO();
			while(rs.next()){
				int idKhoa = rs.getInt("idKhoa");
				Khoa khoa = new Khoa();
				khoa = khoadao.getKhoa(idKhoa);
				listKhoa.add(khoa);
			}
			stm.close();
			db.connectDB().close();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return listKhoa;
	}
	public boolean xoaKhoa(String[] listXoaKhoa) {
		String listKhoa="";
		if(listXoaKhoa.length==1){
			listKhoa += listXoaKhoa[listXoaKhoa.length-1];
		}
		else{
			for(int i=0;i<listXoaKhoa.length - 1;i++){
				listKhoa += listXoaKhoa[i] +",";
			}
			listKhoa += listXoaKhoa[listXoaKhoa.length-1];
		}
		String query1 = "DELETE FROM khoa WHERE idKhoa IN ("+listKhoa +")";
		String query2 = "DELETE FROM chuyennganh WHERE idKhoa IN ("+listKhoa +")";
		int check1 =0, check2=0;
		try {
			Statement st = db.connectDB().createStatement();
			check1 = st.executeUpdate(query1);
			check2 = st.executeUpdate(query2);
			st.close();
			db.connectDB().close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(check1 == 0) return false;
		return true;
	}
	public boolean suaKhoa(Khoa khoa) {
		String query = "UPDATE khoa SET tenKhoa = ? WHERE idKhoa = ?";
		int check = 0;
		try {
			PreparedStatement pstm = db.connectDB().prepareStatement(query);
			pstm.setString(1, khoa.getTenKhoa());
			pstm.setInt(2, khoa.getIdKhoa());
			check = pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (check !=0);
	}
	public boolean themKhoa(String tenKhoa) {
		String query = "INSERT INTO khoa(tenKhoa) VALUES (?)";
		//System.out.println(tenKhoa);
		int check = 0;
		try {
			PreparedStatement pstm = db.connectDB().prepareStatement(query);
			pstm.setString(1, tenKhoa);
			check = pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (check !=0);
	}
}
