package bean;

public class Cap {
	private int idCap;
	private String tenCap;
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
	public String getTenCap() {
		return tenCap;
	}
	public void setTenCap(String tenCap) {
		this.tenCap = tenCap;
	}
	
	public Cap(int idCap, String tenCap) {
		super();
		this.idCap = idCap;
		this.tenCap = tenCap;
	}
	
	
}
