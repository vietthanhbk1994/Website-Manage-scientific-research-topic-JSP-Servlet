package bean;

public class ChuyenNganh {
	private int idKhoa;
	private int idChuyenNganh;
	private String tenChuyenNganh;
	private String tenKhoa;
	public int getIdKhoa() {
		return idKhoa;
	}
	public void setIdKhoa(int idKhoa) {
		this.idKhoa = idKhoa;
	}
	public int getIdChuyenNganh() {
		return idChuyenNganh;
	}
	public void setIdChuyenNganh(int idChuyenNganh) {
		this.idChuyenNganh = idChuyenNganh;
	}
	public String getTenChuyenNganh() {
		return tenChuyenNganh;
	}
	public void setTenChuyenNganh(String tenChuyenNganh) {
		this.tenChuyenNganh = tenChuyenNganh;
	}
	public ChuyenNganh(int idKhoa, String tenKhoa, int idChuyenNganh, String tenChuyenNganh) {
		super();
		this.idKhoa = idKhoa;
		this.tenKhoa = tenKhoa;
		this.idChuyenNganh = idChuyenNganh;
		this.tenChuyenNganh = tenChuyenNganh;
	}
	public ChuyenNganh() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getTenKhoa() {
		return tenKhoa;
	}
	public void setTenKhoa(String tenKhoa) {
		this.tenKhoa = tenKhoa;
	}
	
}
