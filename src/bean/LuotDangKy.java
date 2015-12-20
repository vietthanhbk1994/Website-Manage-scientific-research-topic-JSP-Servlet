package bean;

public class LuotDangKy {
	private int idCap, idLuotDangKy, nam;
	private String tenCap, timeOpen, timeClose;
	public LuotDangKy() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getIdCap() {
		return idCap;
	}
	public void setIdCap(int idCap) {
		this.idCap = idCap;
	}
	public int getIdLuotDangKy() {
		return idLuotDangKy;
	}
	public void setIdLuotDangKy(int idLuotDangKy) {
		this.idLuotDangKy = idLuotDangKy;
	}
	public String getTenCap() {
		return tenCap;
	}
	public void setTenCap(String tenCap) {
		this.tenCap = tenCap;
	}
	public String getTimeOpen() {
		return timeOpen;
	}
	public void setTimeOpen(String timeOpen) {
		this.timeOpen = timeOpen;
	}
	public String getTimeClose() {
		return timeClose;
	}
	public void setTimeClose(String timeClose) {
		this.timeClose = timeClose;
	}
	public int getNam() {
		return nam;
	}
	public void setNam(int nam) {
		this.nam = nam;
	}
	public LuotDangKy(int idCap, int idLuotDangKy, String tenCap, String timeOpen, String timeClose, int nam) {
		super();
		this.idCap = idCap;
		this.idLuotDangKy = idLuotDangKy;
		this.tenCap = tenCap;
		this.timeOpen = timeOpen;
		this.timeClose = timeClose;
		this.nam = nam;
	}
	
}
