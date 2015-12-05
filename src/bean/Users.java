package bean;

public class Users {
	private int idUsers,role,idChuyenNganh,idKhoa;
	private String SoThe,password,fullname,address,dienthoai,email,tenChuyenNganh,tenKhoa;
	public int getIdUser() {
		return idUsers;
	}
	public void setIdUser(int idUser) {
		this.idUsers = idUser;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	public int getIdChuyenNganh() {
		return idChuyenNganh;
	}
	public void setIdChuyenNganh(int idChuyenNganh) {
		this.idChuyenNganh = idChuyenNganh;
	}
	public int getIdKhoa() {
		return idKhoa;
	}
	public void setIdKhoa(int idKhoa) {
		this.idKhoa = idKhoa;
	}
	public String getSoThe() {
		return SoThe;
	}
	public void setSoThe(String soThe) {
		SoThe = soThe;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDienthoai() {
		return dienthoai;
	}
	public void setDienthoai(String dienthoai) {
		this.dienthoai = dienthoai;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Users(int idUser, int role, int idChuyenNganh, int idKhoa,
			String soThe, String password, String fullname, String address,
			String dienthoai, String email, String tenChuyenNganh,
			String tenKhoa) {
		super();
		this.idUsers = idUser;
		this.role = role;
		this.idChuyenNganh = idChuyenNganh;
		this.idKhoa = idKhoa;
		SoThe = soThe;
		this.password = password;
		this.fullname = fullname;
		this.address = address;
		this.dienthoai = dienthoai;
		this.email = email;
		this.tenChuyenNganh = tenChuyenNganh;
		this.tenKhoa = tenKhoa;
	}
	public String getTenChuyenNganh() {
		return tenChuyenNganh;
	}
	public void setTenChuyenNganh(String tenChuyenNganh) {
		this.tenChuyenNganh = tenChuyenNganh;
	}
	public String getTenKhoa() {
		return tenKhoa;
	}
	public void setTenKhoa(String tenKhoa) {
		this.tenKhoa = tenKhoa;
	}
}