package bean;

public class DeTai {
	private int idDeTai, idLinhVuc, idLDK, idCap, soluongtv, kinhphidukien,
			thoigiandukien, idChiTiet, idUsers, kiemduyet, xacnhandangky,
			idKhoa, idChuyenNganh,idNguoiDK;
	public DeTai(int idNguoiDK) {
		super();
		this.idNguoiDK = idNguoiDK;
	}

	private String tenDeTai, ngaydangky, danhsachtv, noidung, ketquadukien,
			sanphamkhoahoc, sanphamungdung, sanphamdaotao, hieuquadukien,
			tenKhoa, tenCap, tenChuyenNganh, tenLinhVuc,tinhcapthiet,muctieu,phanhoi;
	private Users users;

	public String getMuctieu() {
		return muctieu;
	}

	public void setMuctieu(String muctieu) {
		this.muctieu = muctieu;
	}

	public int getIdDeTai() {
		return idDeTai;
	}

	public void setIdDeTai(int idDeTai) {
		this.idDeTai = idDeTai;
	}

	public int getIdLinhVuc() {
		return idLinhVuc;
	}

	public void setIdLinhVuc(int idLinhVuc) {
		this.idLinhVuc = idLinhVuc;
	}

	public int getIdLDK() {
		return idLDK;
	}
	
	public int getIdCap() {
		return idCap;
	}

	public DeTai() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DeTai(int idDeTai, int idLinhVuc, int idLDK, int soluongtv,
			int kinhphidukien, int thoigiandukien, int idChiTiet, int idUsers,
			int kiemduyet, int xacnhandangky, int idKhoa, int idChuyenNganh,
			String tenDeTai, String ngaydangky, String danhsachtv,
			String noidung, String ketquadukien, String sanphamkhoahoc,
			String sanphamungdung, String sanphamdaotao, String hieuquadukien,
			String tenKhoa, String tenCap, String tenChuyenNganh,
			String tenLinhVuc, int idCap) {
		super();
		this.idCap = idCap;
		this.idDeTai = idDeTai;
		this.idLinhVuc = idLinhVuc;
		this.idLDK = idLDK;
		this.soluongtv = soluongtv;
		this.kinhphidukien = kinhphidukien;
		this.thoigiandukien = thoigiandukien;
		this.idChiTiet = idChiTiet;
		this.idUsers = idUsers;
		this.kiemduyet = kiemduyet;
		this.xacnhandangky = xacnhandangky;
		this.idKhoa = idKhoa;
		this.idChuyenNganh = idChuyenNganh;
		this.tenDeTai = tenDeTai;
		this.ngaydangky = ngaydangky;
		this.danhsachtv = danhsachtv;
		this.noidung = noidung;
		this.ketquadukien = ketquadukien;
		this.sanphamkhoahoc = sanphamkhoahoc;
		this.sanphamungdung = sanphamungdung;
		this.sanphamdaotao = sanphamdaotao;
		this.hieuquadukien = hieuquadukien;
		this.tenKhoa = tenKhoa;
		this.tenCap = tenCap;
		this.tenChuyenNganh = tenChuyenNganh;
		this.tenLinhVuc = tenLinhVuc;
	}

	public void setIdLDK(int idLDK) {
		this.idLDK = idLDK;
	}
	
	public void setIdCap(int idCap) {
		this.idCap = idCap;
	}
	
	public int getSoluongtv() {
		return soluongtv;
	}

	public void setSoluongtv(int soluongtv) {
		this.soluongtv = soluongtv;
	}

	public int getKinhphidukien() {
		return kinhphidukien;
	}

	public void setKinhphidukien(int kinhphidukien) {
		this.kinhphidukien = kinhphidukien;
	}

	public int getThoigiandukien() {
		return thoigiandukien;
	}

	public void setThoigiandukien(int thoigiandukien) {
		this.thoigiandukien = thoigiandukien;
	}

	public int getIdChiTiet() {
		return idChiTiet;
	}

	public void setIdChiTiet(int idChiTiet) {
		this.idChiTiet = idChiTiet;
	}

	public int getIdUsers() {
		return idUsers;
	}

	public void setIdUsers(int idUsers) {
		this.idUsers = idUsers;
	}

	public int getKiemduyet() {
		return kiemduyet;
	}

	public void setKiemduyet(int kiemduyet) {
		this.kiemduyet = kiemduyet;
	}

	public int getXacnhandangky() {
		return xacnhandangky;
	}

	public void setXacnhandangky(int xacnhandangky) {
		this.xacnhandangky = xacnhandangky;
	}

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

	public String getTenDeTai() {
		return tenDeTai;
	}

	public void setTenDeTai(String tenDeTai) {
		this.tenDeTai = tenDeTai;
	}

	public String getNgaydangky() {
		return ngaydangky;
	}

	public void setNgaydangky(String ngaydangky) {
		this.ngaydangky = ngaydangky;
	}

	public String getDanhsachtv() {
		return danhsachtv;
	}

	public void setDanhsachtv(String danhsachtv) {
		this.danhsachtv = danhsachtv;
	}

	public String getNoidung() {
		return noidung;
	}

	public void setNoidung(String noidung) {
		this.noidung = noidung;
	}

	public String getKetquadukien() {
		return ketquadukien;
	}

	public void setKetquadukien(String ketquadukien) {
		this.ketquadukien = ketquadukien;
	}

	public String getSanphamkhoahoc() {
		return sanphamkhoahoc;
	}

	public void setSanphamkhoahoc(String sanphamkhoahoc) {
		this.sanphamkhoahoc = sanphamkhoahoc;
	}

	public String getSanphamungdung() {
		return sanphamungdung;
	}

	public void setSanphamungdung(String sanphamungdung) {
		this.sanphamungdung = sanphamungdung;
	}

	public String getSanphamdaotao() {
		return sanphamdaotao;
	}

	public void setSanphamdaotao(String sanphamdaotao) {
		this.sanphamdaotao = sanphamdaotao;
	}

	public String getHieuquadukien() {
		return hieuquadukien;
	}

	public void setHieuquadukien(String hieuquadukien) {
		this.hieuquadukien = hieuquadukien;
	}

	public String getTenKhoa() {
		return tenKhoa;
	}

	public void setTenKhoa(String tenKhoa) {
		this.tenKhoa = tenKhoa;
	}

	public String getTenCap() {
		return tenCap;
	}

	public void setTenCap(String tenCap) {
		this.tenCap = tenCap;
	}

	public String getTenChuyenNganh() {
		return tenChuyenNganh;
	}

	public void setTenChuyenNganh(String tenChuyenNganh) {
		this.tenChuyenNganh = tenChuyenNganh;
	}

	public String getTenLinhVuc() {
		return tenLinhVuc;
	}

	public void setTenLinhVuc(String tenLinhVuc) {
		this.tenLinhVuc = tenLinhVuc;
	}

	public String getTinhcapthiet() {
		return tinhcapthiet;
	}

	public void setTinhcapthiet(String tinhcapthiet) {
		this.tinhcapthiet = tinhcapthiet;
	}
	public boolean checkExist(String mangtv[]){
		int n = mangtv.length;
		for(int i =0;i<n-1;i++){
			for(int j = i+1;j<n;j++){
				if(mangtv[j].equals(mangtv[i])){
					return true;
				}
			}
		}
		return false;
	}

	public int getIdNguoiDK() {
		return idNguoiDK;
	}

	public void setIdNguoiDK(int idNguoiDK) {
		this.idNguoiDK = idNguoiDK;
	}

	public String getPhanhoi() {
		return phanhoi;
	}

	public void setPhanhoi(String phanhoi) {
		this.phanhoi = phanhoi;
	}
	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

}
