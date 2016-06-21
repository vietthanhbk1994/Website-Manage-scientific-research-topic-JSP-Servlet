package bean;

public class ThongBao {
	private int idThongBao, cheDo;
	private String tenThongBao, noiDung;
	private String ngayDang;
	private String dinhKemFile;
	private String linkDownload;
	public int getIdThongBao() {
		return idThongBao;
	}
	public void setIdThongBao(int idThongBao) {
		this.idThongBao = idThongBao;
	}
	public int getCheDo() {
		return cheDo;
	}
	public void setCheDo(int cheDo) {
		this.cheDo = cheDo;
	}
	public String getTenThongBao() {
		return tenThongBao;
	}
	public void setTenThongBao(String tenThongBao) {
		this.tenThongBao = tenThongBao;
	}
	public String getNoiDung() {
		return noiDung;
	}
	public void setNoiDung(String noiDung) {
		this.noiDung = noiDung;
	}
	public String getNgayDang() {
		return ngayDang;
	}
	public void setNgayDang(String ngayDang) {
		this.ngayDang = ngayDang;
	}
	public String getDinhKemFile() {
		return dinhKemFile;
	}
	public void setDinhKemFile(String dinhKemFile) {
		this.dinhKemFile = dinhKemFile;
	}
	public String getLinkDownload() {
		return linkDownload;
	}
	public void setLinkDownload(String linkDownload) {
		this.linkDownload = linkDownload;
	}
	public ThongBao(int idThongBao, int cheDo, String tenThongBao, String noiDung, String ngayDang, String dinhKemFile,
			String linkDownload) {
		super();
		this.idThongBao = idThongBao;
		this.cheDo = cheDo;
		this.tenThongBao = tenThongBao;
		this.noiDung = noiDung;
		this.ngayDang = ngayDang;
		this.dinhKemFile = dinhKemFile;
		this.linkDownload = linkDownload;
	}
	public ThongBao() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
