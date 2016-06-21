package bo;

import dao.CheckTimeDAO;

public class CheckTimeBO {
	CheckTimeDAO checkTimeDAO = new CheckTimeDAO();
	public int checkTime(String DateTime,int idCap){
		return checkTimeDAO.checkTime(DateTime,idCap);
		
	}
}
