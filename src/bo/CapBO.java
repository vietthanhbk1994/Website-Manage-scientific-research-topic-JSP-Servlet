package bo;

import java.util.ArrayList;

import bean.Cap;
import dao.CapDAO;

public class CapBO {
	CapDAO capdao = new CapDAO();
	public ArrayList<Cap> getListCap(){
		return capdao.getListCap();
	}
	public boolean xoaCap(int idCap) {
		return capdao.xoaCap(idCap);
	}
	public boolean themCap(Cap cap) {
		return capdao.themCap(cap);
	}
	public boolean suaCap(Cap cap) {
		return capdao.suaCap(cap);
	}
}
