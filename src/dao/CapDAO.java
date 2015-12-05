package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import bean.Cap;
import bean.ThongBao;

public class CapDAO {
	DataBase db = new DataBase();
	
	public boolean taoDotDK(Cap cap) {
		//	public static void main(String args[]){
//				Cap cap = new Cap();
//				cap.setIdCap(1);
//				cap.setTimeClose("2015-1-03");
//				cap.setTimeOpen("2015-10-03");
//				DataBase db = new DataBase();
			String query = "INSERT INTO capdetai(tenCap,timeOpen,timeClose,nam) VALUES (?,?,?,?)";
			int check = 0;
			try {
				PreparedStatement pstm = db.connectDB().prepareStatement(query);
				pstm.setString(1, cap.getTenCap());
				pstm.setString(2, cap.getTimeOpen());
				pstm.setString(3, cap.getTimeClose());
				pstm.setInt(4, cap.getNam());
				
				check = pstm.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return (check !=0);
				
		}
	
	public boolean suaDotDK(Cap cap) {
	//	public static void main(String args[]){
//			Cap cap = new Cap();
//			cap.setIdCap(1);
//			cap.setTimeClose("2015-1-03");
//			cap.setTimeOpen("2015-10-03");
//			DataBase db = new DataBase();
		String query = "UPDATE capdetai SET timeOpen = ?, timeClose = ?, nam =? WHERE idCap = ?";
		int check = 0;
		try {
			PreparedStatement pstm = db.connectDB().prepareStatement(query);
			
			pstm.setString(1, cap.getTimeOpen());
			pstm.setString(2, cap.getTimeClose());
			pstm.setInt(3, cap.getNam());
			pstm.setInt(4, cap.getIdCap());
			
			check = pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (check !=0);
			
	}
	
	public Cap getCapDeTai(int id){
		//	public static void main(String args[]){
		//	DataBase db = new DataBase();
		Cap cap = new Cap();
				String query = "SELECT * FROM capdetai WHERE idCap = "+id;
				try {
					
					Statement stm = db.connectDB().createStatement();
					ResultSet rs = stm.executeQuery(query);
					while(rs.next()){
						
						cap.setTenCap(rs.getString("tenCap"));
						cap.setIdCap(rs.getInt("idCap"));
						cap.setNam(rs.getInt("nam"));
						
						String timeOpen = rs.getString("timeOpen");
						String arrayTimeOpen[] = timeOpen.split(" ");
						
						String date0 = arrayTimeOpen[0];
						String arrayDate0[] = date0.split("-");
						date0 = arrayDate0[2] +"/" +arrayDate0[1] +"/" +arrayDate0[0];
						timeOpen = arrayTimeOpen[1] + " " +date0;
						
						cap.setTimeOpen(timeOpen);
						
						String timeClose = rs.getString("timeClose");
						String arraytimeClose[] = timeClose.split(" ");
						String date1 = arraytimeClose[0];
						String arrayDate1[] = date1.split("-");
						date1 = arrayDate1[2] +"/" +arrayDate1[1] +"/" +arrayDate1[0];
						timeClose = arraytimeClose[1] + " " +date1;
						
						cap.setTimeClose(timeClose);
						
					}
				} catch (Exception e) {
					
					e.printStackTrace();
				}
				
				return cap;
		}
	
	public ArrayList<Cap> getCapDeTai(){
	//	public static void main(String args[]){
	//	DataBase db = new DataBase();
			ArrayList<Cap> listCapDeTai = new ArrayList<Cap>();
			
			String query = "SELECT * FROM capdetai";
			try {
				
				Statement stm = db.connectDB().createStatement();
				ResultSet rs = stm.executeQuery(query);
				while(rs.next()){
					Cap cap = new Cap();
					cap.setTenCap(rs.getString("tenCap"));
					cap.setIdCap(rs.getInt("idCap"));
					cap.setNam(rs.getInt("nam"));
					
					String timeOpen = rs.getString("timeOpen");
					String arrayTimeOpen[] = timeOpen.split(" ");
					
					String date0 = arrayTimeOpen[0];
					String arrayDate0[] = date0.split("-");
					date0 = arrayDate0[2] +"/" +arrayDate0[1] +"/" +arrayDate0[0];
					timeOpen = arrayTimeOpen[1] + " " +date0;
					
					cap.setTimeOpen(timeOpen);
					
					String timeClose = rs.getString("timeClose");
					String arraytimeClose[] = timeClose.split(" ");
					String date1 = arraytimeClose[0];
					String arrayDate1[] = date1.split("-");
					date1 = arrayDate1[2] +"/" +arrayDate1[1] +"/" +arrayDate1[0];
					timeClose = arraytimeClose[1] + " " +date1;
					
					cap.setTimeClose(timeClose);
					
					listCapDeTai.add(cap);
				}
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			
			return listCapDeTai;
	}
}
