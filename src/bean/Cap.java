package bean;

public class Cap {
	private int idCap,nam;
	private String tenCap, timeOpen, timeClose;
	public Cap() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getIdCap() {
		return idCap;
	}
	public void setIdCap(int idCap) {
		this.idCap = idCap;
	}
	public int getNam() {
		return nam;
	}
	public void setNam(int nam) {
		this.nam = nam;
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
	public Cap(int idCap, int nam, String tenCap, String timeOpen, String timeClose) {
		super();
		this.idCap = idCap;
		this.nam = nam;
		this.tenCap = tenCap;
		this.timeOpen = timeOpen;
		this.timeClose = timeClose;
	}
	
	
}
