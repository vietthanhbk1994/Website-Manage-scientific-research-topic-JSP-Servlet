package bo;

import java.util.ArrayList;

import bean.ChuyenNganh;
import dao.ChuyenNganhDAO;

public class ChuyenNganhBO {
	ChuyenNganhDAO cndao = new ChuyenNganhDAO();
	public ChuyenNganh getChuyenNganh(int idChuyenNganh){
		return cndao.getChuyenNganh(idChuyenNganh);
	}
	public ArrayList<ChuyenNganh> getlistChuyenNganh(){
		return cndao.getlistChuyenNganh();
	}
	public boolean xoaChuyenNganh(String[] listXoaChuyenNganh) {
		// TODO Auto-generated method stub
		return cndao.xoaChuyenNganh(listXoaChuyenNganh);
	}
	public boolean suaChuyenNganh(ChuyenNganh ChuyenNganh) {
		// TODO Auto-generated method stub
		return cndao.suaChuyenNganh(ChuyenNganh);
	}
	public boolean themChuyenNganh(int idKhoa, String tenChuyenNganh) {
		if(cndao.isExistChuyenNganh(tenChuyenNganh)){
			return false;
		}
		return cndao.themChuyenNganh(idKhoa, tenChuyenNganh);
	}
}
