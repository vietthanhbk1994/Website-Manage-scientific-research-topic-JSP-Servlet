package dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import bean.ChuyenNganh;
import bean.Khoa;

public class KhoaDAO {
	DataBase db = new DataBase();
	
	public Khoa getKhoa(int idKhoa){
		
		ArrayList<ChuyenNganh> listChuyenNganh = new ArrayList<ChuyenNganh>();
		Khoa khoa = new Khoa();
		String query = "SELECT * FROM chuyennganh,khoa WHERE khoa.idKhoa=chuyennganh.idKhoa AND khoa.idKhoa!=9 AND khoa.idKhoa = "+idKhoa;
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
}
