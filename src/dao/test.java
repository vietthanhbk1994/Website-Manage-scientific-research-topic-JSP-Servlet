package dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import bean.ChuyenNganh;
import bean.DeTai;
import bean.Khoa;
import bean.Users;

public class test {
	public Khoa getKhoa(int idKhoa){
		Khoa khoa = new Khoa();
		ArrayList<ChuyenNganh> listChuyenNganh = new ArrayList<ChuyenNganh>();
		DataBase db = new DataBase();
		String query = "SELECT * FROM chuyennganh,khoa WHERE khoa.idKhoa=chuyennganh.idKhoa AND khoa.idKhoa = "+idKhoa;
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
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		khoa.setListChuyenNganh(listChuyenNganh);
		return khoa;
	}
	public static void main(String args[]){
		
		DataBase db = new DataBase();
		ArrayList<Khoa> listKhoa = new ArrayList<Khoa>();
		String query = "SELECT idKhoa FROM khoa";
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
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		System.out.println("done");
//		String sql = "SELECT * FROM danhsachdetai,users WHERE kiemduyet = 0 AND users.idUsers = danhsachdetai.idUsers";
//		try {
//			Statement stm = db.connectDB().createStatement();
//			ResultSet rs = stm.executeQuery(sql);
//			while(rs.next()){
//				DeTai detai =  new DeTai();
//				detai.setIdDeTai(rs.getInt("idDeTai"));
//				detai.setTenDeTai(rs.getString("tenDeTai"));
//				detai.setIdUsers(rs.getInt("idUsers"));
//				
//				Users user = new Users();
//				
//				user.setIdUser(rs.getInt("idUsers"));
//				
//				detai.setUsers(user);
//				listDeTai.add(detai);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}
}
