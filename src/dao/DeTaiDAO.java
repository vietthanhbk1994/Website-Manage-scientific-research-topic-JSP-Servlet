package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import bean.DeTai;
import bean.Users;

public class DeTaiDAO {
	DataBase db = new DataBase();

	public ArrayList<DeTai> timKiemDeTaiTruong(int idKhoa, String soThe, String fullname, int idCap, int nam) {

		ArrayList<DeTai> listDeTai = new ArrayList<DeTai>();
		String dk0 = "", dk1 = "", dk2 = "", dk3 = "", dk4 = "";
		String sql = "SELECT * FROM danhsachdetai,users,capdetai,chuyennganh WHERE (users.idUsers=danhsachdetai.idUsers) AND (users.idChuyenNganh = chuyennganh.idChuyenNganh) AND (danhsachdetai.idCap = capdetai.idCap) AND (kiemduyet != 0)";

		if (idKhoa != 0) {
			dk0 = " AND chuyennganh.idKhoa =" + idKhoa;
		}
		if (soThe != "") {
			dk1 = " AND users.soThe= " + soThe;
		}

		if (fullname != "") {
			dk2 = " AND users.fullname LIKE '%" + fullname + "%'";
		}

		if (nam != 0) {
			dk3 = " AND danhsachdetai.ngaydangky LIKE '%" + nam +"%'";
		}
		if (idCap != 0) {
			dk4 = " AND capdetai.idCap = " + idCap;
		}

		sql += dk0 + dk1 + dk2 + dk3 + dk4;

		try {
			Statement stm = db.connectDB().createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				DeTai detai = new DeTai();

				detai.setIdDeTai(rs.getInt("idDeTai"));
				detai.setTenDeTai(rs.getString("tenDeTai"));
				detai.setIdLinhVuc(rs.getInt("idLinhVuc"));
				detai.setIdCap(rs.getInt("idCap"));
				detai.setSoluongtv(rs.getInt("soluongtv"));
				detai.setDanhsachtv(rs.getString("danhsachtv"));
				detai.setMuctieu(rs.getString("muctieu"));
				detai.setTinhcapthiet(rs.getString("tinhcapthiet"));
				detai.setNoidung(rs.getString("noidung"));
				detai.setKetquadukien(rs.getString("ketquadukien"));
				detai.setSanphamkhoahoc(rs.getString("sanphamkhoahoc"));
				detai.setSanphamungdung(rs.getString("sanphamungdung"));
				detai.setSanphamdaotao(rs.getString("sanphamdaotao"));
				detai.setHieuquadukien(rs.getString("hieuquadukien"));
				detai.setKinhphidukien(rs.getInt("kinhphidukien"));
				detai.setThoigiandukien(rs.getInt("thoigiandukien"));
				detai.setXacnhandangky(rs.getInt("xacnhandangky"));
				detai.setIdUsers(rs.getInt("idUsers"));
				detai.setKiemduyet(rs.getInt("kiemduyet"));
				detai.setNgaydangky(rs.getString("ngaydangky"));
				detai.setPhanhoi(rs.getString("phanhoi"));

				Users user = new Users();
				user.setIdUser(rs.getInt("idUsers"));
				user.setFullname(rs.getString("fullname"));
				// user.setAddress(rs.getString("address"));
				// user.setDienthoai(rs.getString("dienThoai"));
				// user.setEmail(rs.getString("email"));

				detai.setUsers(user);

				listDeTai.add(detai);
			}
			stm.close();
			db.connectDB().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listDeTai;
	}
	
	public ArrayList<DeTai> timKiemDeTaiKhoa(int idKhoa, String soThe, String fullname, int idCap, int nam) {

		ArrayList<DeTai> listDeTai = new ArrayList<DeTai>();
		String dk0 = "", dk1 = "", dk2 = "", dk3 = "", dk4 = "";
		String sql = "SELECT * FROM danhsachdetai,users,capdetai,chuyennganh WHERE (users.idUsers=danhsachdetai.idUsers) AND (users.idChuyenNganh = chuyennganh.idChuyenNganh) AND (danhsachdetai.idCap = capdetai.idCap) AND (danhsachdetai.xacnhandangky = 1)";

		if (idKhoa != 0) {
			dk0 = " AND chuyennganh.idKhoa =" + idKhoa;
		}
		if (soThe != "") {
			dk1 = " AND users.soThe= " + soThe;
		}

		if (fullname != "") {
			dk2 = " AND users.fullname LIKE '%" + fullname + "%'";
		}

		if (nam != 0) {
			dk3 = " AND danhsachdetai.ngaydangky LIKE '%" + nam +"%'";
		}
		if (idCap != 0) {
			dk4 = " AND capdetai.idCap = " + idCap;
		}

		sql += dk0 + dk1 + dk2 + dk3 + dk4;

		try {
			Statement stm = db.connectDB().createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				DeTai detai = new DeTai();

				detai.setIdDeTai(rs.getInt("idDeTai"));
				detai.setTenDeTai(rs.getString("tenDeTai"));
				detai.setIdLinhVuc(rs.getInt("idLinhVuc"));
				detai.setIdCap(rs.getInt("idCap"));
				detai.setSoluongtv(rs.getInt("soluongtv"));
				detai.setDanhsachtv(rs.getString("danhsachtv"));
				detai.setMuctieu(rs.getString("muctieu"));
				detai.setTinhcapthiet(rs.getString("tinhcapthiet"));
				detai.setNoidung(rs.getString("noidung"));
				detai.setKetquadukien(rs.getString("ketquadukien"));
				detai.setSanphamkhoahoc(rs.getString("sanphamkhoahoc"));
				detai.setSanphamungdung(rs.getString("sanphamungdung"));
				detai.setSanphamdaotao(rs.getString("sanphamdaotao"));
				detai.setHieuquadukien(rs.getString("hieuquadukien"));
				detai.setKinhphidukien(rs.getInt("kinhphidukien"));
				detai.setThoigiandukien(rs.getInt("thoigiandukien"));
				detai.setXacnhandangky(rs.getInt("xacnhandangky"));
				detai.setIdUsers(rs.getInt("idUsers"));
				detai.setKiemduyet(rs.getInt("kiemduyet"));
				detai.setNgaydangky(rs.getString("ngaydangky"));
				detai.setPhanhoi(rs.getString("phanhoi"));

				Users user = new Users();
				user.setIdUser(rs.getInt("idUsers"));
				user.setFullname(rs.getString("fullname"));
				// user.setAddress(rs.getString("address"));
				// user.setDienthoai(rs.getString("dienThoai"));
				// user.setEmail(rs.getString("email"));

				detai.setUsers(user);

				listDeTai.add(detai);
			}
			stm.close();
			db.connectDB().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listDeTai;
	}
	
	public ArrayList<DeTai> getListDeTaiKhoa(int idKhoa) {
		ArrayList<DeTai> listDeTai = new ArrayList<DeTai>();

		String sql = "SELECT * FROM danhsachdetai,users,chuyennganh WHERE xacnhandangky=1 AND (users.idUsers = danhsachdetai.idUsers) AND (users.idChuyenNganh=chuyennganh.idChuyenNganh) AND chuyennganh.idKhoa ="
				+ idKhoa;
		try {
			Statement stm = db.connectDB().createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				DeTai detai = new DeTai();
				detai.setIdDeTai(rs.getInt("idDeTai"));
				detai.setTenDeTai(rs.getString("tenDeTai"));
				detai.setIdLinhVuc(rs.getInt("idLinhVuc"));
				detai.setIdCap(rs.getInt("idCap"));
				detai.setSoluongtv(rs.getInt("soluongtv"));
				detai.setDanhsachtv(rs.getString("danhsachtv"));
				detai.setMuctieu(rs.getString("muctieu"));
				detai.setTinhcapthiet(rs.getString("tinhcapthiet"));
				detai.setNoidung(rs.getString("noidung"));
				detai.setKetquadukien(rs.getString("ketquadukien"));
				detai.setSanphamkhoahoc(rs.getString("sanphamkhoahoc"));
				detai.setSanphamungdung(rs.getString("sanphamungdung"));
				detai.setSanphamdaotao(rs.getString("sanphamdaotao"));
				detai.setHieuquadukien(rs.getString("hieuquadukien"));
				detai.setKinhphidukien(rs.getInt("kinhphidukien"));
				detai.setThoigiandukien(rs.getInt("thoigiandukien"));
				detai.setXacnhandangky(rs.getInt("xacnhandangky"));
				detai.setIdUsers(rs.getInt("idUsers"));
				detai.setKiemduyet(rs.getInt("kiemduyet"));
				detai.setNgaydangky(rs.getString("ngaydangky"));
				detai.setPhanhoi(rs.getString("phanhoi"));

				Users user = new Users();
				user.setIdUser(rs.getInt("idUsers"));
				user.setFullname(rs.getString("fullname"));
				// user.setAddress(rs.getString("address"));
				// user.setDienthoai(rs.getString("dienThoai"));
				// user.setEmail(rs.getString("email"));

				detai.setUsers(user);

				listDeTai.add(detai);
			}
			stm.close();
			db.connectDB().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listDeTai;
	}

	public ArrayList<DeTai> getListDeTaiTruong() {
		ArrayList<DeTai> listDeTai = new ArrayList<DeTai>();
		DataBase db = new DataBase();
		String sql = "SELECT * FROM danhsachdetai,users WHERE (kiemduyet != 0) AND users.idUsers = danhsachdetai.idUsers";
		try {
			Statement stm = db.connectDB().createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				DeTai detai = new DeTai();
				detai.setIdDeTai(rs.getInt("idDeTai"));
				detai.setTenDeTai(rs.getString("tenDeTai"));
				detai.setIdLinhVuc(rs.getInt("idLinhVuc"));
				detai.setIdCap(rs.getInt("idCap"));
				detai.setSoluongtv(rs.getInt("soluongtv"));
				detai.setDanhsachtv(rs.getString("danhsachtv"));
				detai.setMuctieu(rs.getString("muctieu"));
				detai.setTinhcapthiet(rs.getString("tinhcapthiet"));
				detai.setNoidung(rs.getString("noidung"));
				detai.setKetquadukien(rs.getString("ketquadukien"));
				detai.setSanphamkhoahoc(rs.getString("sanphamkhoahoc"));
				detai.setSanphamungdung(rs.getString("sanphamungdung"));
				detai.setSanphamdaotao(rs.getString("sanphamdaotao"));
				detai.setHieuquadukien(rs.getString("hieuquadukien"));
				detai.setKinhphidukien(rs.getInt("kinhphidukien"));
				detai.setThoigiandukien(rs.getInt("thoigiandukien"));
				detai.setXacnhandangky(rs.getInt("xacnhandangky"));
				detai.setIdUsers(rs.getInt("idUsers"));
				detai.setKiemduyet(rs.getInt("kiemduyet"));
				detai.setNgaydangky(rs.getString("ngaydangky"));
				detai.setPhanhoi(rs.getString("phanhoi"));

				Users user = new Users();
				user.setIdUser(rs.getInt("idUsers"));
				user.setFullname(rs.getString("fullname"));
				// user.setAddress(rs.getString("address"));
				// user.setDienthoai(rs.getString("dienThoai"));
				// user.setEmail(rs.getString("email"));

				detai.setUsers(user);

				listDeTai.add(detai);
			}
			stm.close();
			db.connectDB().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listDeTai;
	}

	public ArrayList<DeTai> getListDeTai(int idUsers) {
		String sql = "SELECT danhsachdetai.idDeTai,danhsachdetai.tenDeTai,danhsachdetai.idUsers AS idDK,danhsachdetai.kiemduyet,danhsachdetai.xacnhandangky,danhsachdetai.idCap,capdetai.tenCap FROM danhsachdetai INNER JOIN detaichitiet ON danhsachdetai.idDeTai = detaichitiet.idDeTai INNER JOIN capdetai ON capdetai.idCap = danhsachdetai.idCap WHERE detaichitiet.idUsers="
				+ idUsers;
		ArrayList<DeTai> ListDeTai = new ArrayList<DeTai>();
		try {
			Statement stm = db.connectDB().createStatement();
			ResultSet rs = stm.executeQuery(sql);
			// int i = 0;
			while (rs.next()) {
				DeTai detai = new DeTai();
				detai.setIdDeTai(rs.getInt("idDeTai"));
				detai.setTenDeTai(rs.getString("tenDeTai"));
				detai.setIdNguoiDK(rs.getInt("idDK"));
				detai.setXacnhandangky(rs.getInt("xacnhandangky"));
				detai.setKiemduyet(rs.getInt("kiemduyet"));
				detai.setIdCap(rs.getInt("idCap"));
				detai.setTenCap(rs.getString("tenCap"));
				ListDeTai.add(detai);
				// System.out.println("List in
				// dao"+ListDeTai.get(i++).getTenDeTai());
			}
			stm.close();
			db.connectDB().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ListDeTai;

	}

	public boolean checkThanhVien(String mangtv[]) {
		for (int i = 0; i < mangtv.length - 1; i++) {
			String sqlcheck = "SELECT idUsers FROM Users WHERE soThe=" + mangtv[i] + " LIMIT 1";
			try {
				Statement stmcheck = db.connectDB().createStatement();
				ResultSet rscheck = stmcheck.executeQuery(sqlcheck);
				if (rscheck.last() == false) {
					return false;
				}
				stmcheck.close();
				db.connectDB().close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return true;
	}

	public String convertIDThanhVien(String mangtv[]) {
		String dsIDThanhVien = "";
		for (int i = 0; i <= mangtv.length - 1; i++) {
			String sqlcheck = "SELECT idUsers FROM Users WHERE soThe=" + mangtv[i] + " LIMIT 1";
			try {
				Statement stmcheck = db.connectDB().createStatement();
				ResultSet rscheck = stmcheck.executeQuery(sqlcheck);
				if (rscheck.last() == false) {
					dsIDThanhVien = null;
					return dsIDThanhVien;
				} else {
					if (i != mangtv.length - 1) {
						dsIDThanhVien += rscheck.getInt(1) + ",";
					} else {
						dsIDThanhVien += rscheck.getInt(1);
					}
				}
				stmcheck.close();
				db.connectDB().close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return dsIDThanhVien;
	}

	public String convertToSoThe(String dsID) {
		String dsSoThe = "";
		String mangtv[] = dsID.split(",");
		for (int i = 0; i <= mangtv.length - 1; i++) {
			String sqlcheck = "SELECT soThe FROM Users WHERE idUsers=" + mangtv[i] + " LIMIT 1";
			try {
				Statement stmcheck = db.connectDB().createStatement();
				ResultSet rscheck = stmcheck.executeQuery(sqlcheck);
				if (rscheck.last() == false) {
					dsSoThe = null;
					return dsSoThe;
				} else {
					if (i != mangtv.length - 1) {
						dsSoThe += rscheck.getString(1) + ",";
					} else {
						dsSoThe += rscheck.getString(1);
					}
				}
				stmcheck.close();
				db.connectDB().close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return dsSoThe;
	}

	public boolean dangky(DeTai detai, String dsID) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO danhsachdetai(tenDeTai, idLinhVuc, idCap, soluongtv, danhsachtv,noidung,ketquadukien,sanphamkhoahoc,sanphamungdung,sanphamdaotao,hieuquadukien,kinhphidukien,thoigiandukien,xacnhandangky,idUsers,kiemduyet,ngaydangky,phanhoi,tinhcapthiet,muctieu) "
				+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		int check = 0;
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		String ngaydangky = dateFormat.format(date);
		int maxID = 0;
		try {
			PreparedStatement pstm = db.connectDB().prepareStatement(sql);
			pstm.setString(1, detai.getTenDeTai());
			pstm.setInt(2, detai.getIdLinhVuc());
			pstm.setInt(3, detai.getIdCap());
			pstm.setInt(4, detai.getSoluongtv());
			pstm.setString(5, dsID);
			pstm.setString(6, detai.getNoidung());
			pstm.setString(7, detai.getKetquadukien());
			pstm.setString(8, detai.getSanphamkhoahoc());
			pstm.setString(9, detai.getSanphamungdung());
			pstm.setString(10, detai.getSanphamdaotao());
			pstm.setString(11, detai.getHieuquadukien());
			pstm.setInt(12, detai.getKinhphidukien());
			pstm.setInt(13, detai.getThoigiandukien());
			pstm.setInt(14, detai.getXacnhandangky());
			pstm.setInt(15, detai.getIdUsers());
			pstm.setInt(16, 0);
			pstm.setString(17, ngaydangky);
			pstm.setString(18, "");
			pstm.setString(19, detai.getTinhcapthiet());
			pstm.setString(20, detai.getMuctieu());
			check = pstm.executeUpdate();
			pstm.close();
			db.connectDB().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (check == 0) {
			return false;
		} else {
			String arrayID[] = dsID.split(",");
			String maxIDDeTai = "SELECT MAX(idDeTai) FROM danhsachdetai";
			try {
				Statement stm2 = db.connectDB().createStatement();
				ResultSet rs2 = stm2.executeQuery(maxIDDeTai);
				while (rs2.next()) {
					maxID = rs2.getInt(1);
				}
				stm2.close();
				db.connectDB().close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			if (maxID == 0) {
				return false;
			}
			for (String id : arrayID) {
				check = 0;
				int ID = Integer.parseInt(id);
				sql = "INSERT INTO detaichitiet(idDeTai,idUsers)" + " VALUES(?,?)";
				try {
					PreparedStatement pstm2 = db.connectDB().prepareStatement(sql);
					pstm2.setInt(1, maxID);
					pstm2.setInt(2, ID);
					check = pstm2.executeUpdate();
					if (check == 0) {
						return false;
					}
					pstm2.close();
					db.connectDB().close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return true;
		}
	}

	public boolean xacNhanDK(int idDeTai) {
		// TODO Auto-generated method stub
		String query = "UPDATE danhsachdetai SET xacnhandangky=? WHERE idDeTai=" + idDeTai;
		int check = 0;
		try {
			PreparedStatement pstm = db.connectDB().prepareStatement(query);
			pstm.setInt(1, 1);
			check = pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (check > 0) {
			return true;
		} else {
			return false;
		}
	}

	public DeTai getChiTiet(int idDeTai) {
		// TODO Auto-generated method stub
		String sql = "SELECT users.*,linhvucnghiencuu.*,danhsachdetai.* FROM users INNER JOIN danhsachdetai ON users.idUsers = danhsachdetai.idUsers INNER JOIN linhvucnghiencuu ON linhvucnghiencuu.idLinhVuc = danhsachdetai.idLinhVuc WHERE idDeTai = "
				+ idDeTai;
		DeTai detai = new DeTai();
		Users users = new Users();
		try {
			Statement stm = db.connectDB().createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				String dsID = rs.getString("danhsachtv");
				String dsSoThe = convertToSoThe(dsID);
				if (dsSoThe == null) {
					return null;
				}
				detai.setIdDeTai(rs.getInt("idDeTai"));
				detai.setTenDeTai(rs.getString("tenDeTai"));
				detai.setIdLinhVuc(rs.getInt("idLinhVuc"));
				detai.setTinhcapthiet(rs.getString("tinhcapthiet"));
				detai.setMuctieu(rs.getString("muctieu"));
				detai.setNoidung(rs.getString("noidung"));
				detai.setKetquadukien(rs.getString("ketquadukien"));
				detai.setSanphamkhoahoc(rs.getString("sanphamkhoahoc"));
				detai.setSanphamdaotao(rs.getString("sanphamdaotao"));
				detai.setSanphamungdung(rs.getString("sanphamungdung"));
				detai.setHieuquadukien(rs.getString("ketquadukien"));
				detai.setKinhphidukien(rs.getInt("kinhphidukien"));
				detai.setThoigiandukien(rs.getInt("thoigiandukien"));
				detai.setIdCap(rs.getInt("idCap"));
				detai.setSoluongtv(rs.getInt("soluongtv"));
				detai.setKiemduyet(rs.getInt("kiemduyet"));
				detai.setNgaydangky(rs.getString("ngaydangky"));
				detai.setXacnhandangky(rs.getInt("xacnhandangky"));
				users.setIdUser(rs.getInt("idUsers"));
				users.setSoThe(rs.getString("soThe"));
				users.setFullname(rs.getString("fullname"));
				users.setAddress(rs.getString("address"));
				users.setDienthoai(rs.getString("dienthoai"));
				users.setEmail(rs.getString("email"));
				detai.setUsers(users);
				detai.setDanhsachtv(dsSoThe);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return detai;
	}

	public boolean editDeTai(DeTai detai) {
		String query = "UPDATE danhsachdetai SET tenDeTai=?,idLinhVuc=?,idCap=?,soluongtv=?,danhsachtv=?,muctieu=?,tinhcapthiet=?,noidung=?,ketquadukien=?"
				+ ",sanphamkhoahoc=?,sanphamungdung=?,sanphamdaotao=?,hieuquadukien=?,kinhphidukien=?"
				+ ",thoigiandukien=?,xacnhandangky=?,idUsers=?,kiemduyet=?,ngaydangky=?,phanhoi = ? WHERE idDeTai = ?";
		int check = 0;
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		String ngaydangky = dateFormat.format(date);
		try {
			PreparedStatement pstm = db.connectDB().prepareStatement(query);
			pstm.setString(1, detai.getTenDeTai());
			pstm.setInt(2, detai.getIdLinhVuc());
			pstm.setInt(3, detai.getIdCap());
			pstm.setInt(4, detai.getSoluongtv());
			pstm.setString(5, detai.getDanhsachtv());
			pstm.setString(6, detai.getMuctieu());
			pstm.setString(7, detai.getTinhcapthiet());
			pstm.setString(8, detai.getNoidung());
			pstm.setString(9, detai.getKetquadukien());
			pstm.setString(10, detai.getSanphamkhoahoc());
			pstm.setString(11, detai.getSanphamungdung());
			pstm.setString(12, detai.getSanphamdaotao());
			pstm.setString(13, detai.getHieuquadukien());
			pstm.setInt(14, detai.getKinhphidukien());
			pstm.setInt(15, detai.getThoigiandukien());
			pstm.setInt(16, detai.getXacnhandangky());
			pstm.setInt(17, detai.getIdUsers());
			pstm.setInt(18, 0);
			pstm.setString(19, ngaydangky);
			pstm.setString(20, "");
			pstm.setInt(21, detai.getIdDeTai());
			check = pstm.executeUpdate();
			if (check > 0) {
				String sql2 = "DELETE FROM detaichitiet WHERE idDeTai = ?";
				try {
					PreparedStatement pstmdel = db.connectDB().prepareStatement(sql2);
					pstmdel.setInt(1, detai.getIdDeTai());
					check = pstmdel.executeUpdate();
					String arrayID[] = detai.getDanhsachtv().split(",");
					for (String id : arrayID) {
						check = 0;
						int ID = Integer.parseInt(id);
						String sql3 = "INSERT INTO detaichitiet(idDeTai,idUsers)" + " VALUES(?,?)";
						try {
							PreparedStatement pstm2 = db.connectDB().prepareStatement(sql3);
							pstm2.setInt(1, detai.getIdDeTai());
							pstm2.setInt(2, ID);
							check = pstm2.executeUpdate();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (check > 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean delDeTai(String[] arrayDel) {
		int check1 = 0;
		int check2 = 0;
		StringBuilder delID = new StringBuilder("");
		for (String idDeTai : arrayDel) {
			int IidDeTai = Integer.parseInt(idDeTai);
			delID.append(IidDeTai + ",");
		}
		delID.deleteCharAt(delID.length() - 1);
		String sqldanhsachdetai = "DELETE  FROM danhsachdetai WHERE idDeTai IN (" + delID + ")";
		String sqldetaichitiet = "DELETE FROM detaichitiet WHERE idDeTai IN(" + delID + ")";
		try {
			PreparedStatement pstm1 = db.connectDB().prepareStatement(sqldanhsachdetai);
			check1 = pstm1.executeUpdate();
			if (check1 == 0) {
				return false;
			}
			PreparedStatement pstm2 = db.connectDB().prepareStatement(sqldetaichitiet);
			check2 = pstm2.executeUpdate();
			if (check2 == 0) {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return true;
	}

	public boolean kiemduyet(int idDeTai, int kiemduyet) {
		// TODO Auto-generated method stub
		String query = "UPDATE danhsachdetai SET kiemduyet=? WHERE idDeTai=?";
		int check = 0;
		try {
			PreparedStatement pstm = db.connectDB().prepareStatement(query);
			pstm.setInt(1, kiemduyet);
			pstm.setInt(2, idDeTai);
			check = pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (check > 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean xacnhanKhoa(StringBuilder listxacnhan) {
		// TODO Auto-generated method stub
		int check = 0;
		String sql = "UPDATE danhsachdetai SET kiemduyet = 1 WHERE idDeTai = " + listxacnhan;
		try {
			PreparedStatement pstm = db.connectDB().prepareStatement(sql);
			check = pstm.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return (check != 0);
	}

	public boolean notSameKhoa(int idKhoa, String dsID) {
		// TODO Auto-generated method stub
		String array[] = dsID.split(",");
		for(String id:array){
			String sql = "SELECT users.idUsers FROM users INNER JOIN chuyennganh ON users.idChuyenNganh = chuyennganh.idChuyenNganh WHERE chuyennganh.idKhoa = "+idKhoa+" && users.idUsers = "+id;
			try {
				Statement stm = db.connectDB().createStatement();
				ResultSet rs = stm.executeQuery(sql);
				if(!rs.last()){
					return true;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return false;
	}

	public ArrayList<DeTai> getListDeTai2(int idUsers) {
		// TODO Auto-generated method stub
		String sql = "SELECT danhsachdetai.idDeTai,danhsachdetai.tenDeTai,capdetai.idCap,danhsachdetai.xacnhandangky,danhsachdetai.kiemduyet,danhsachdetai.xacnhandangky,capdetai.tenCap FROM danhsachdetai INNER JOIN capdetai ON danhsachdetai.idCap = capdetai.idCap WHERE danhsachdetai.idUsers = "+idUsers+" and "+idUsers+" NOT IN (SELECT idUsers FROM detaichitiet WHERE idDeTai = danhsachdetai.idDeTai)";
		ArrayList<DeTai> listDeTai2 = new ArrayList<DeTai>();
		try {
			Statement stm = db.connectDB().createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()){
				DeTai detai = new DeTai();
				detai.setIdDeTai(rs.getInt("idDeTai"));
				detai.setTenDeTai(rs.getString("tenDeTai"));
				detai.setTenCap(rs.getString("tenCap"));
				detai.setXacnhandangky(rs.getInt("xacnhandangky"));
				detai.setKiemduyet(rs.getInt("kiemduyet"));
				detai.setIdCap(rs.getInt("idCap"));
				detai.setIdNguoiDK(idUsers);
				listDeTai2.add(detai);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return listDeTai2;
	}

}
