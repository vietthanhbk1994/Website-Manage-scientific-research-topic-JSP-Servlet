package bo;

import java.util.ArrayList;

import bean.Cap;
import dao.ListCapDAO;

public class ListCapBO {
	ListCapDAO listCapDAO = new ListCapDAO();
	public ArrayList<Cap> getListCap(){
		return listCapDAO.getListCap();
	}
	
}
