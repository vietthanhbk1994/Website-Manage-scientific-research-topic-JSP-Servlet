package bo;

import java.util.ArrayList;

import dao.DeTaiDAO;
import bean.DeTai;

public class DeTaiBO {
	DeTaiDAO deTaiDAO = new DeTaiDAO();
	public boolean xacnhanKhoa(StringBuilder listxacnhan) {
		return deTaiDAO.xacnhanKhoa(listxacnhan);
	}
	public ArrayList<DeTai> timKiemDeTaiKhoa(int idKhoa, String soThe, String fullname, String cap, int nam) {
		return deTaiDAO.timKiemDeTaiKhoa(idKhoa, soThe, fullname, cap, nam);
	}
	public ArrayList<DeTai> timKiemDeTaiTruong(int idKhoa, String soThe, String fullname, int idCap, int nam) {
		return deTaiDAO.timKiemDeTaiTruong(idKhoa, soThe, fullname, idCap, nam);
	}
	public ArrayList<DeTai> getListDeTaiKhoa(int idKhoa){
		return deTaiDAO.getListDeTaiKhoa(idKhoa);
	}
	public ArrayList<DeTai> getListDeTaiTruong(){
		return deTaiDAO.getListDeTaiTruong();
	}
	public ArrayList<DeTai> getListDeTai(int idUsers){
		return deTaiDAO.getListDeTai(idUsers);
	}
	public boolean checkThanhVien(String mangtv[]) {
		// TODO Auto-generated method stub
		return deTaiDAO.checkThanhVien(mangtv);
	}
	public boolean dangky(DeTai detai, String dsID) {
		// TODO Auto-generated method stub
		return deTaiDAO.dangky(detai,dsID);
	}
	public String convertIDThanhVien(String mangtv[]){
		return deTaiDAO.convertIDThanhVien(mangtv);
	}
	public boolean xacNhanDK(int idDeTai) {
		return deTaiDAO.xacNhanDK(idDeTai);
	}
	public DeTai getChiTiet(int idDeTai) {
		// TODO Auto-generated method stub
		return deTaiDAO.getChiTiet(idDeTai);
	}
	public boolean editDeTai(DeTai detai, String dsID) {
		// TODO Auto-generated method stub
		return false;
	}
	public boolean editDeTai(DeTai detai) {
		// TODO Auto-generated method stub
		return deTaiDAO.editDeTai(detai);
	}
	public boolean delDeTai(String[] arrayDel) {
		return deTaiDAO.delDeTai(arrayDel);
	}
	public boolean kiemduyet(int idDeTai,int kiemduyet) {
		// TODO Auto-generated method stub
		return deTaiDAO.kiemduyet(idDeTai,kiemduyet);
	}
	public boolean notSameKhoa(int idKhoa, String dsID) {
		// TODO Auto-generated method stub
		return deTaiDAO.notSameKhoa(idKhoa,dsID);
	}
	public ArrayList<DeTai> getListDeTai2(int idUsers) {
		// TODO Auto-generated method stub
		return deTaiDAO.getListDeTai2(idUsers);
	}
}
