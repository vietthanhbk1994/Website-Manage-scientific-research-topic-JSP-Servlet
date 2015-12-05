package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import bean.ThongBao;

public class ThongBaoDAO {
	
	DataBase db = new DataBase();
	
	public boolean checkXoaThongBao(String[] listXoaThongBao){
		String listThongBao="";
		if(listXoaThongBao.length==1){
			listThongBao += listXoaThongBao[listXoaThongBao.length-1];
		}
		else{
			for(int i=0;i<listXoaThongBao.length - 1;i++){
				listThongBao += listXoaThongBao[i] +",";
			}
			listThongBao += listXoaThongBao[listXoaThongBao.length-1];
		}
		
		String query = "DELETE FROM thongbao WHERE idThongBao IN ("+listThongBao +")";
		
		int check =0;
		try {
			Statement st = db.connectDB().createStatement();
			check = st.executeUpdate(query);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(check == 0) return false;
		return true;
	}
	
	public boolean checkXoaTatCaThongBao(){
		
		String query = "DELETE FROM thongbao";
		
		int check =0;
		try {
			Statement st = db.connectDB().createStatement();
			check = st.executeUpdate(query);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(check == 0) return false;
		return true;
	}
	
	public boolean themThongBao(ThongBao thongBao){
		String tenThongBao = thongBao.getTenThongBao();
		String noiDungThongBao = thongBao.getNoiDung();
		int cheDo = thongBao.getCheDo();
		String ngayDang = thongBao.getNgayDang();
		String dinhKemFile = thongBao.getDinhKemFile();
		String linkDownload = thongBao.getLinkDownload();
		
		String query = "INSERT INTO thongbao(tenThongBao,noidung,ngaydang,chedo,dinhKemFile,linkDownload) VALUES (?,?,?,?,?,?)";
		
		int check =0;
		try {
			PreparedStatement ps = db.connectDB().prepareStatement(query);
			ps.setString(1, tenThongBao);
			ps.setString(2, noiDungThongBao);
			ps.setString(3, ngayDang);
			ps.setInt(4, cheDo);
			ps.setString(5, dinhKemFile);
			ps.setString(6, linkDownload);
			check = ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(check == 0) return false;
		return true;
	}
	public ArrayList<ThongBao> getListThongBao(){
	//public static void main(String args[]){
		ArrayList<ThongBao> listThongBao = new ArrayList<ThongBao>();
		
		String query= "SELECT * FROM thongbao ORDER BY idThongBao DESC";
		try {
			Statement st = db.connectDB().createStatement();
			ResultSet rs = st.executeQuery(query);
			while(rs.next()){
				int idThongBao = rs.getInt("idThongBao");
				int cheDo = rs.getInt("chedo");
				String tenThongBao = rs.getString("tenThongBao");
				String noiDung = rs.getString("noidung");
				Date ngay = rs.getDate("ngaydang");
				String ngayDang = ngay.toString(); 
				String dinhKemFile = rs.getString("dinhKemFile");
				String linkDownload = rs.getString("linkDownload");
				ThongBao thongBao = new ThongBao(idThongBao,cheDo,tenThongBao,noiDung,ngayDang,dinhKemFile,linkDownload);
				
				listThongBao.add(thongBao);
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return listThongBao;
	}
	public ThongBao getThongBaoSua(int idThongBao){
		//public static void main(String args[]){
			ThongBao thongBaoSua = new ThongBao();
			
			String query= "SELECT * FROM thongbao WHERE idThongBao="+idThongBao;
			try {
				Statement st = db.connectDB().createStatement();
				ResultSet rs = st.executeQuery(query);
				while(rs.next()){
					int cheDo = rs.getInt("chedo");
					String tenThongBao = rs.getString("tenThongBao");
					String noiDung = rs.getString("noidung");
					String ngayDang = rs.getString("ngaydang");
					String dinhKemFile = rs.getString("dinhKemFile");
					String linkDownload = rs.getString("linkDownload");
					thongBaoSua = new ThongBao(idThongBao,cheDo,tenThongBao,noiDung,ngayDang,dinhKemFile,linkDownload);
					
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return thongBaoSua;
		}
	public boolean checkSuaThongBao(ThongBao news) {
		String query = "UPDATE thongbao SET tenThongBao = ?, noidung = ?, ngaydang = ?, chedo = ?, dinhKemFile = ?, linkDownload = ? WHERE idThongBao = ?";
		int check = 0;
		try {
			PreparedStatement pstm = db.connectDB().prepareStatement(query);
			pstm.setString(1, news.getTenThongBao());
			pstm.setString(2, news.getNoiDung());
			pstm.setString(3, news.getNgayDang());
			pstm.setInt(4, news.getCheDo());
			
			pstm.setString(5, news.getDinhKemFile());
			pstm.setString(6, news.getLinkDownload());
			pstm.setInt(7, news.getIdThongBao());
			check = pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (check > 0){
			return true;
		} else {
			return false;
		}
	}
}
