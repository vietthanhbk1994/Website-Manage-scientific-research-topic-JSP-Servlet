package bo;

import java.util.ArrayList;

import bean.LuotDangKy;
import dao.LuotDangKyDAO;

public class LuotDangKyBO {
	LuotDangKyDAO ldkdao = new LuotDangKyDAO();
	public boolean themLuotDK(LuotDangKy luotDK) {
		return ldkdao.themLuotDK(luotDK);
	}
	public boolean suaLuotDK(LuotDangKy luotDK) {
		return ldkdao.suaLuotDK(luotDK);
	}
	public boolean xoaLuotDK(int idLDK) {
		return ldkdao.xoaLuotDK(idLDK);
	}
	public LuotDangKy getLuotDangKy(int idLDK){
		return ldkdao.getLuotDangKy(idLDK);
	}
	public ArrayList<LuotDangKy> getListLuotDangKy(){
		return ldkdao.getListLuotDangKy();
	}
	public int[] getNam(){
		return ldkdao.getNam();
	}
	public boolean checkXoaLuotDangKy(String[] listXoaLuotDangKy) {
		// TODO Auto-generated method stub
		return ldkdao.checkXoaLuotDangKy(listXoaLuotDangKy);
	}
}
