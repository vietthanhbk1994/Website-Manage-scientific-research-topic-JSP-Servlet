package bo;

import java.util.ArrayList;

import bean.Cap;
import dao.CapDAO;

public class CapBO {
	CapDAO capdao = new CapDAO();
	
	public boolean suaDotDK(Cap cap) {
		return capdao.suaDotDK(cap);
	}
	public ArrayList<Cap> getCapDeTai(){
		return capdao.getCapDeTai();
	}
	public boolean taoDotDK(Cap cap) {
		return capdao.taoDotDK(cap);
	}
	public Cap getCapDeTai(int id){
		return capdao.getCapDeTai(id);
	}
}
