package bean;

public class Linhvuc {
	private int idLinhvuc;
	String tenLinhVuc;
	public int getIdLinhvuc() {
		return idLinhvuc;
	}
	public void setIdLinhvuc(int idLinhvuc) {
		this.idLinhvuc = idLinhvuc;
	}
	public String getTenLinhVuc() {
		return tenLinhVuc;
	}
	public void setTenLinhVuc(String tenLinhVuc) {
		this.tenLinhVuc = tenLinhVuc;
	}
	public Linhvuc(int idLinhvuc, String tenLinhVuc) {
		super();
		this.idLinhvuc = idLinhvuc;
		this.tenLinhVuc = tenLinhVuc;
	}
	public Linhvuc() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
