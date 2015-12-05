package bo;

import java.util.ArrayList;

import dao.LinhVucDAO;
import bean.Linhvuc;

public class LinhVucBO {
	LinhVucDAO linhVucDAO = new LinhVucDAO();
	public ArrayList<Linhvuc> getLinhVuc(){
		return linhVucDAO.getLinhVuc();
	}
}
