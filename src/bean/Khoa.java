package bean;

import java.util.ArrayList;

public class Khoa {
	private int idKhoa;
	private String tenKhoa;
	private ArrayList<ChuyenNganh> listChuyenNganh;
	public int getIdKhoa() {
		return idKhoa;
	}
	public void setIdKhoa(int idKhoa) {
		this.idKhoa = idKhoa;
	}
	public String getTenKhoa() {
		return tenKhoa;
	}
	public void setTenKhoa(String tenKhoa) {
		this.tenKhoa = tenKhoa;
	}
	public ArrayList<ChuyenNganh> getListChuyenNganh() {
		return listChuyenNganh;
	}
	public void setListChuyenNganh(ArrayList<ChuyenNganh> listChuyenNganh) {
		this.listChuyenNganh = listChuyenNganh;
	}
	public Khoa(int idKhoa, String tenKhoa, ArrayList<ChuyenNganh> listChuyenNganh) {
		super();
		this.idKhoa = idKhoa;
		this.tenKhoa = tenKhoa;
		this.listChuyenNganh = listChuyenNganh;
	}
	public Khoa() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
