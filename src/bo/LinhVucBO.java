package bo;

import java.util.ArrayList;

import dao.LinhVucDAO;
import bean.Linhvuc;

public class LinhVucBO {
	LinhVucDAO linhVucDAO = new LinhVucDAO();
	public ArrayList<Linhvuc> getLinhVuc(){
		return linhVucDAO.getLinhVuc();
	}
	public boolean xoaLinhVuc(String[] listXoaLinhVuc) {
		return linhVucDAO.xoaLinhVuc(listXoaLinhVuc);
	}
	public boolean themLinhVuc(String tenLinhVuc) {
		//neu co ton tai 1 LinhVuc giong LinhVuc do thi tra ve false
		if(linhVucDAO.isExistLinhVuc(tenLinhVuc)){
			return false;
		}
		return linhVucDAO.themLinhVuc(tenLinhVuc);
	}
	public boolean suaLinhVuc(Linhvuc LinhVuc) {
		return linhVucDAO.suaLinhVuc(LinhVuc);
	}
}
