package bo;

import dao.UserDAO;

import java.util.ArrayList;

import bean.Users;

public class UserBO {
	UserDAO userdao = new UserDAO();
	
	public Users checkLogin(String sothe, String password) {
		// TODO Auto-generated method stub
		return userdao.checkLogin(sothe,password);
	}
	public ArrayList<Users> getListUser(){
		return userdao.getListUser();
	}
	public ArrayList<Users> getListKhoa(){
		return userdao.getListKhoa();
	}
	public boolean themUserBO(Users user) {
		// TODO Auto-generated method stub
		return userdao.themUserDAO(user);
	}
	public boolean checkXoaUser(String[] listXoaUser) {
		// TODO Auto-generated method stub
		return userdao.checkXoaUser(listXoaUser);
	}
	public Users getUserSua(int idUser) {
		// TODO Auto-generated method stub
		return userdao.getUserSua(idUser);
	}
	public boolean suaUserBO(Users user) {
		return userdao.suaUserBO(user);
	}
	public boolean issetUserBO(String soThe){
		return userdao.issetUserDAO(soThe);
	}
	public ArrayList<Users> getListThanhVien(String dsThanhVien) {
		// TODO Auto-generated method stub
		return userdao.getListThanhVien(dsThanhVien);
	}
}
