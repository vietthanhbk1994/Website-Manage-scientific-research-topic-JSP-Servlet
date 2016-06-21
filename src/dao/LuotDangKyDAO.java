package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import bean.LuotDangKy;

public class LuotDangKyDAO {
	DataBase db = new DataBase();
	
	public boolean themLuotDK(LuotDangKy luotDK) {
			String query = "INSERT INTO luotdangky(timeOpen,timeClose,nam,idCap) VALUES (?,?,?,?)";
			int check = 0;
			try {
				PreparedStatement pstm = db.connectDB().prepareStatement(query);
				pstm.setString(1, luotDK.getTimeOpen());
				pstm.setString(2, luotDK.getTimeClose());
				pstm.setInt(3, luotDK.getNam());
				pstm.setInt(4, luotDK.getIdCap());
				check = pstm.executeUpdate();
				pstm.close();
				db.connectDB().close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return (check !=0);
				
		}
	
	public boolean suaLuotDK(LuotDangKy luotDK) {
		
		String query = "UPDATE luotdangky SET timeOpen = ?, timeClose = ?, nam =?, idCap=? WHERE idLDK = ?";
		int check = 0;
		try {
			PreparedStatement pstm = db.connectDB().prepareStatement(query);
			
			pstm.setString(1, luotDK.getTimeOpen());
			pstm.setString(2, luotDK.getTimeClose());
			pstm.setInt(3, luotDK.getNam());
			pstm.setInt(4, luotDK.getIdCap());
			pstm.setInt(4, luotDK.getIdLuotDangKy());
			
			check = pstm.executeUpdate();
			pstm.close();
			db.connectDB().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (check !=0);
			
	}
	
	public boolean xoaLuotDK(int idLDK) {
		
		String query = "DELETE FROM luotdangky WHERE idLDK = ?";
		int check = 0;
		try {
			PreparedStatement pstm = db.connectDB().prepareStatement(query);
			
			pstm.setInt(1, idLDK);
			
			check = pstm.executeUpdate();
			pstm.close();
			db.connectDB().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (check !=0);
			
	}
	
	
	public LuotDangKy getLuotDangKy(int idLDK){
		
		LuotDangKy luotDangKy = new LuotDangKy();						
			String query = "SELECT * FROM luotdangky,capdetai WHERE luotdangky.idCap = capdetai.idCap AND nam = (SELECT MAX(nam) FROM luotdangky) AND luotdangky.idLDK = "+idLDK;
			try {
				
				Statement stm = db.connectDB().createStatement();
				ResultSet rs = stm.executeQuery(query);
				while(rs.next()){
					
					luotDangKy.setTenCap(rs.getString("tenCap"));
					luotDangKy.setIdCap(rs.getInt("idCap"));
					luotDangKy.setNam(rs.getInt("nam"));
					luotDangKy.setIdLuotDangKy(rs.getInt("idLDK"));
					
					String timeOpen = rs.getString("timeOpen");
					String arrayTimeOpen[] = timeOpen.split(" ");
					
					String date0 = arrayTimeOpen[0];
					String arrayDate0[] = date0.split("-");
					date0 = arrayDate0[2] +"/" +arrayDate0[1] +"/" +arrayDate0[0];
					timeOpen = arrayTimeOpen[1] + " " +date0;
					
					luotDangKy.setTimeOpen(timeOpen);
					
					String timeClose = rs.getString("timeClose");
					String arraytimeClose[] = timeClose.split(" ");
					String date1 = arraytimeClose[0];
					String arrayDate1[] = date1.split("-");
					date1 = arrayDate1[2] +"/" +arrayDate1[1] +"/" +arrayDate1[0];
					timeClose = arraytimeClose[1] + " " +date1;
					
					luotDangKy.setTimeClose(timeClose);
					
				}
				stm.close();
				db.connectDB().close();
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			
			return luotDangKy;
	}
	
	public int[] getNam(){
		int[] nam = new int [50];
		String query = "SELECT DISTINCT nam FROM luotdangky ORDER BY nam DESC";
		try {
			
			Statement stm = db.connectDB().createStatement();
			ResultSet rs = stm.executeQuery(query);
			int i=0;
			while(rs.next()){
				nam[i] = rs.getInt("nam");
				i++;
			}
			stm.close();
			db.connectDB().close();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return nam;
	}
	
public ArrayList<LuotDangKy> getListLuotDangKy(){
		
		ArrayList<LuotDangKy> listLuotDangKy = new ArrayList<LuotDangKy>();						
		// chỉ lấy ra những lượt đăng ký của năm gần nhất (lớn nhất)
			String query = "SELECT * FROM luotdangky,capdetai WHERE luotdangky.idCap = capdetai.idCap AND nam = (SELECT MAX(nam) FROM luotdangky)";
			try {
				
				Statement stm = db.connectDB().createStatement();
				ResultSet rs = stm.executeQuery(query);
				while(rs.next()){
					LuotDangKy luotDangKy = new LuotDangKy();
					
					luotDangKy.setTenCap(rs.getString("tenCap"));
					luotDangKy.setIdCap(rs.getInt("idCap"));
					luotDangKy.setNam(rs.getInt("nam"));
					luotDangKy.setIdLuotDangKy(rs.getInt("idLDK"));
					
					String timeOpen = rs.getString("timeOpen");
					String arrayTimeOpen[] = timeOpen.split(" ");
					
					String date0 = arrayTimeOpen[0];
					String arrayDate0[] = date0.split("-");
					date0 = arrayDate0[2] +"/" +arrayDate0[1] +"/" +arrayDate0[0];
					timeOpen = arrayTimeOpen[1] + " " +date0;
					
					luotDangKy.setTimeOpen(timeOpen);
					
					String timeClose = rs.getString("timeClose");
					String arraytimeClose[] = timeClose.split(" ");
					String date1 = arraytimeClose[0];
					String arrayDate1[] = date1.split("-");
					date1 = arrayDate1[2] +"/" +arrayDate1[1] +"/" +arrayDate1[0];
					timeClose = arraytimeClose[1] + " " +date1;
					
					luotDangKy.setTimeClose(timeClose);
					
					listLuotDangKy.add(luotDangKy);
				}
				stm.close();
				db.connectDB().close();
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			
			return listLuotDangKy;
	}

	public boolean checkXoaLuotDangKy(String[] listXoaLuotDangKy) {
			String listLuotDangKy="";
			if(listXoaLuotDangKy.length==1){
				listLuotDangKy += listXoaLuotDangKy[listXoaLuotDangKy.length-1];
			}
			else{
				for(int i=0;i<listXoaLuotDangKy.length - 1;i++){
					listLuotDangKy += listXoaLuotDangKy[i] +",";
				}
				listLuotDangKy += listXoaLuotDangKy[listXoaLuotDangKy.length-1];
			}
			
			String query = "DELETE FROM luotdangky WHERE idLDK IN ("+listLuotDangKy +")";
			
			int check =0;
			try {
				Statement st = db.connectDB().createStatement();
				check = st.executeUpdate(query);
				st.close();
				db.connectDB().close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(check == 0) return false;
			return true;
		
	}
}
