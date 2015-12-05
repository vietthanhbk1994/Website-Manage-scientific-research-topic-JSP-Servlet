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
}
