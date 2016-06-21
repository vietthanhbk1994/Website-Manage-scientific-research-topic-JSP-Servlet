package bo;

import java.util.ArrayList;

import bean.Khoa;
import dao.KhoaDAO;

public class KhoaBO {
	KhoaDAO khoadao = new KhoaDAO();
	public Khoa getKhoa(int idKhoa){
		return khoadao.getKhoa(idKhoa);
	}
	public ArrayList<Khoa> getlistKhoaChuyenNganh(){
		return khoadao.getlistKhoaChuyenNganh();
	}
	public boolean xoaKhoa(String[] listXoaKhoa) {
		// TODO Auto-generated method stub
		return khoadao.xoaKhoa(listXoaKhoa);
	}
	public boolean suaKhoa(Khoa khoa) {
		// TODO Auto-generated method stub
		return khoadao.suaKhoa(khoa);
	}
	public boolean themKhoa(String tenKhoa) {
		if(khoadao.isExistKhoa(tenKhoa)){
			return false;
		}
		return khoadao.themKhoa(tenKhoa);
	}
}
