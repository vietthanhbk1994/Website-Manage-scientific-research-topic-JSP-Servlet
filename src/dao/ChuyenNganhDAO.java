package dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import bean.Cap;
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
}
