package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import bean.Users;
import bean.Users;

public class UserDAO {
	DataBase db = new DataBase();
	
	public ArrayList<Users> getListUser(){
			ArrayList<Users> listUsers = new ArrayList<Users>();
			String query= "SELECT idUsers,fullname,role,soThe,address,dienthoai,email,chuyennganh.tenChuyenNganh,khoa.tenKhoa "
					+ "FROM users,chuyennganh,khoa "
					+ "WHERE users.idChuyenNganh = chuyennganh.idChuyenNganh AND chuyennganh.idKhoa = khoa.idKhoa AND role!=-1 ";
			try {
				Statement st = db.connectDB().createStatement();
				ResultSet rs = st.executeQuery(query);
				while(rs.next()){
					Users users = new Users();
					
					users.setIdUser(rs.getInt("idUsers"));
					users.setFullname(rs.getString("fullname"));
					users.setRole(rs.getInt("role"));
					users.setSoThe(rs.getString("soThe"));
					users.setAddress(rs.getString("address"));
					users.setDienthoai(rs.getString("dienthoai"));
					users.setEmail(rs.getString("email"));
					users.setTenChuyenNganh(rs.getString("tenChuyenNganh"));
					users.setTenKhoa(rs.getString("tenKhoa"));
					
					System.out.println(rs.getInt("idUsers"));
					
					listUsers.add(users);
					
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return listUsers;
		}
	public boolean issetUserDAO(String soThe) {
		int check = 0;
		String sql = "SELECT * FROM users WHERE soThe = '"
				+ soThe + "'";
		try {
			Statement stm = db.connectDB().createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()){
				check = rs.getRow();
			}
			System.out.println("so hang la:"+check);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (check != 0);
	}

	public Users checkLogin(String sothe, String password) {
		// TODO Auto-generated method stub
		// String sql = "SELECT * FROM users WHERE soThe = '" + sothe
		//+ "' && password = '" + password + "'";
		String sql = "SELECT users.idUsers,users.soThe,users.password,users.fullname,users.address,users.role,users.idChuyenNganh,users.dienthoai,users.email,chuyennganh.tenChuyenNganh,chuyennganh.idKhoa,khoa.tenKhoa from users inner JOIN chuyennganh on users.idChuyenNganh = chuyennganh.idChuyenNganh INNER JOIN khoa ON khoa.idKhoa = chuyennganh.idKhoa WHERE users.soThe = '" + sothe
		+ "' && users.password = '" + password + "' ";
		Users users = new Users();
		try {
			Statement stm = db.connectDB().createStatement();
			ResultSet rs = stm.executeQuery(sql);
			System.out.println("this is rs:" + rs.getRow());
			while (rs.next()) {
				int idUsers = rs.getInt("idUsers");
				String soThe = rs.getString("soThe");
				String Password = rs.getString("password");
				String fullname = rs.getString("fullname");
				String address = rs.getString("address");
				int role = rs.getInt("role");
				int idChuyenNganh = rs.getInt("idChuyenNganh");
				String dienthoai = rs.getString("dienthoai");
				String email = rs.getString("email");
				String tenChuyenNganh = rs.getString("tenChuyenNganh");
				String tenKhoa = rs.getString("tenKhoa");
				int idKhoa = rs.getInt("idKhoa");
				users = new Users(idUsers, role, idChuyenNganh, idKhoa, soThe, Password, fullname, address, dienthoai, email, tenChuyenNganh, tenKhoa);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	}
	
	
	
	public ArrayList<Users> getListKhoa(){
		//public static void main(String args[]){
			ArrayList<Users> listKhoa = new ArrayList<Users>();
			
			String query= "SELECT chuyennganh.tenChuyenNganh,khoa.tenKhoa,chuyennganh.idChuyenNganh,khoa.idKhoa "
					+ "FROM chuyennganh,khoa "
					+ "WHERE chuyennganh.idKhoa = khoa.idKhoa AND khoa.idKhoa <> 9 "
					+ "ORDER BY khoa.tenKhoa,chuyennganh.tenChuyenNganh";
			try {
				Statement st = db.connectDB().createStatement();
				ResultSet rs = st.executeQuery(query);
				while(rs.next()){
					Users users = new Users();
					
					users.setTenChuyenNganh(rs.getString("tenChuyenNganh"));
					users.setTenKhoa(rs.getString("tenKhoa"));
					users.setIdChuyenNganh(rs.getInt("idChuyenNganh"));
					users.setIdKhoa(rs.getInt("idKhoa"));
					listKhoa.add(users);
					
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return listKhoa;
		}

	public boolean themUserDAO(Users user) {
		String fullname = user.getFullname();
		String soThe = user.getSoThe();
		String password = user.getPassword();
		int role = user.getRole();
		String address = user.getAddress();
		String dienThoai = user.getDienthoai();
		String email = user.getEmail();
		int idChuyenNganh = user.getIdChuyenNganh();
		
		String query = "INSERT INTO users(fullname,soThe,password,role,idChuyenNganh,address,dienthoai,email) VALUES (?,?,?,?,?,?,?,?)";
		
		int check =0;
		try {
			PreparedStatement ps = db.connectDB().prepareStatement(query);
			ps.setString(1, fullname);
			ps.setString(2, soThe);
			ps.setString(3, password);
			ps.setInt(4, role);
			ps.setInt(5, idChuyenNganh);
			ps.setString(6, address);
			ps.setString(7, dienThoai);
			ps.setString(8, email);
			check = ps.executeUpdate();
			ps.close();
			db.connectDB().close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(check == 0) return false;
		return true;
	}
	public boolean checkXoaUser(String[] listXoaUser) {
		String listUser="";
		if(listXoaUser.length==1){
			listUser += listXoaUser[listXoaUser.length-1];
		}
		else{
			for(int i=0;i<listXoaUser.length - 1;i++){
				listUser += listXoaUser[i] +",";
			}
			listUser += listXoaUser[listXoaUser.length-1];
		}
		
		String query = "DELETE FROM users WHERE idUsers IN ("+listUser +")";
		
		int check =0;
		try {
			Statement st = db.connectDB().createStatement();
			check = st.executeUpdate(query);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(check == 0) return false;
		return true;
	}
	public Users getUserSua(int idUser) {
//	public static void main(String args[]){
	//	DataBase db = new DataBase();
		Users userSua = new Users();
		
		String query= "SELECT * FROM users,chuyennganh WHERE idUsers="+idUser+" AND users.idChuyenNganh=chuyennganh.idChuyenNganh";
//		String query= "SELECT * FROM users,chuyennganh WHERE idUsers=3 AND users.idChuyenNganh=chuyennganh.idChuyenNganh";
		userSua.setIdUser(idUser);
		try {
			Statement st = db.connectDB().createStatement();
			ResultSet rs = st.executeQuery(query);
			while(rs.next()){
				userSua.setIdUser(idUser);
				userSua.setFullname(rs.getString("fullname"));
				userSua.setRole(rs.getInt("role"));
				userSua.setSoThe(rs.getString("soThe"));
				userSua.setPassword(rs.getString("password"));
				userSua.setAddress(rs.getString("address"));
				userSua.setDienthoai(rs.getString("dienthoai"));
				userSua.setEmail(rs.getString("email"));
				userSua.setTenChuyenNganh(rs.getString("tenChuyenNganh"));
				userSua.setIdChuyenNganh(rs.getInt(7));
				userSua.setIdKhoa(rs.getInt("idKhoa"));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return userSua;
	}
	public boolean suaUserBO(Users user) {
		int idUser = user.getIdUser();
		String fullname = user.getFullname();
		String soThe = user.getSoThe();
		String password = user.getPassword();
		int role = user.getRole();
		String address = user.getAddress();
		String dienThoai = user.getDienthoai();
		String email = user.getEmail();
		int idChuyenNganh = user.getIdChuyenNganh();
		
		String query = "UPDATE users SET fullname = ?, soThe = ?, password = ?, role = ?, idChuyenNganh=?, address=?, dienthoai=?, email=? WHERE idUsers = ?";
		
		int check =0;
		try {
			PreparedStatement ps = db.connectDB().prepareStatement(query);
			ps.setString(1, fullname);
			ps.setString(2, soThe);
			ps.setString(3, password);
			ps.setInt(4, role);
			ps.setInt(5, idChuyenNganh);
			ps.setString(6, address);
			ps.setString(7, dienThoai);
			ps.setString(8, email);
			ps.setInt(9, idUser);
			
			check = ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(check == 0) return false;
		return true;
	}
}
