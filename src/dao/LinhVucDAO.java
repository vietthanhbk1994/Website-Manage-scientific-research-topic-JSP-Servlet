package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import bean.Linhvuc;

public class LinhVucDAO {
	DataBase db = new DataBase();
	
	public boolean isExistLinhVuc(String tenLinhVuc) {
		int check = 0;
		String sql = "SELECT * FROM linhvucnghiencuu WHERE tenLinhVuc = '"
				+ tenLinhVuc + "'";
		try {
			Statement stm = db.connectDB().createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()){
				check = rs.getRow();
			}
			stm.close();
			db.connectDB().close();
			//System.out.println("so hang la:"+check);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (check != 0);
	}
	
	public boolean themLinhVuc(String tenLinhVuc) {
			String query = "INSERT INTO linhvucnghiencuu(tenLinhVuc) VALUES (?)";
			int check = 0;
			try {
				PreparedStatement pstm = db.connectDB().prepareStatement(query);
				pstm.setString(1, tenLinhVuc);
				check = pstm.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return (check !=0);
		}
	
	public boolean suaLinhVuc(Linhvuc LinhVuc) {
		String query = "UPDATE linhvucnghiencuu SET tenLinhVuc = ? WHERE idLinhvuc = ?";
		int check = 0;
		try {
			PreparedStatement pstm = db.connectDB().prepareStatement(query);
			pstm.setString(1, LinhVuc.getTenLinhVuc());
			pstm.setInt(2, LinhVuc.getIdLinhvuc());
			check = pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (check !=0);
			
	}
	
	public boolean xoaLinhVuc(String[] listXoaLinhVuc) {
		String listLinhVuc="";
		if(listXoaLinhVuc.length==1){
			listLinhVuc += listXoaLinhVuc[listXoaLinhVuc.length-1];
		}
		else{
			for(int i=0;i<listXoaLinhVuc.length - 1;i++){
				listLinhVuc += listXoaLinhVuc[i] +",";
			}
			listLinhVuc += listXoaLinhVuc[listXoaLinhVuc.length-1];
		}
		String query = "DELETE FROM linhvucnghiencuu WHERE idLinhvuc IN ("+listLinhVuc +")";
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
	
	public ArrayList<Linhvuc> getLinhVuc() {
		String sql = "SELECT * FROM linhvucnghiencuu";
		ArrayList<Linhvuc> ListLinhVuc = new ArrayList<Linhvuc>();
		try {
			Statement stm = db.connectDB().createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()){
				int idLinhvuc = rs.getInt(1);
				String tenLinhVuc = rs.getString(2);
				Linhvuc lv = new Linhvuc(idLinhvuc,tenLinhVuc);
				ListLinhVuc.add(lv);
			}
			stm.close();
			db.connectDB().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ListLinhVuc;
	}

}
