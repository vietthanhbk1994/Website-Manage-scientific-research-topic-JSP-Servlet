package bo;

import java.util.ArrayList;

import bean.ThongBao;
import dao.ThongBaoDAO;

public class ThongBaoBO {
	
	ThongBaoDAO thongBaoDAO =new ThongBaoDAO();
	public boolean checkSuaThongBao(ThongBao news) {
		return thongBaoDAO.checkSuaThongBao(news);
	}
	public ArrayList<ThongBao> getListThongBao(){
		return thongBaoDAO.getListThongBao();
	}
	public ThongBao getThongBaoSua(int idThongBao){
		return thongBaoDAO.getThongBaoSua(idThongBao);
	}
	public boolean themThongBao(ThongBao thongBao){
		return thongBaoDAO.themThongBao(thongBao);
	}
	public boolean checkXoaThongBao(String[] listXoaThongBao){
		return thongBaoDAO.checkXoaThongBao(listXoaThongBao);
	}
	
	public boolean checkXoaTatCaThongBao(){
		return thongBaoDAO.checkXoaTatCaThongBao();
	}
}
