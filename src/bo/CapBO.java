package bo;

import java.util.ArrayList;

import bean.Cap;
import dao.CapDAO;

public class CapBO {
	CapDAO capdao = new CapDAO();
	public ArrayList<Cap> getListCap(){
		return capdao.getListCap();
	}
	public boolean xoaCap(String[] listXoaCap) {
		return capdao.xoaCap(listXoaCap);
	}
	public boolean themCap(String tenCap) {
		//neu co ton tai 1 cap giong cap do thi tra ve false
		if(capdao.isExistCap(tenCap)){
			return false;
		}
		return capdao.themCap(tenCap);
	}
	public boolean suaCap(Cap cap) {
		return capdao.suaCap(cap);
	}
}
