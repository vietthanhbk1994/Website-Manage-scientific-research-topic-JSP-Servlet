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
}
