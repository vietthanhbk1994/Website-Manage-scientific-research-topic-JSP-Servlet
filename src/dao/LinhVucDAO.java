package dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import bean.Linhvuc;

public class LinhVucDAO {
	DataBase db = new DataBase();
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ListLinhVuc;
	}

}
