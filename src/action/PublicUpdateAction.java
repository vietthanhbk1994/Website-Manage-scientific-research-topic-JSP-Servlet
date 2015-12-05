package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.DeTai;
import bean.Users;
import bo.CheckTimeBO;
import bo.DeTaiBO;

/**
 * Servlet implementation class PublicUpdateAction
 */
public class PublicUpdateAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PublicUpdateAction() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		Users users = (Users) session.getAttribute("users");
		int idDeTai = Integer.parseInt(request.getParameter("detai"));
		if (users == null || users.getRole() != 0) {
			session.invalidate();
			response.sendRedirect("login");
			return;
		}
		String submit = null;
		submit = request.getParameter("dangky");
		if (submit == null) {
			submit = request.getParameter("luulai");
		}
		int xacnhandangky = 0;
		if (submit.equals("Đăng ký")) {
			xacnhandangky = 1;
		}
		String tendetai = new String(request.getParameter("tendetai").getBytes(
				"ISO-8859-1"), "UTF-8");
		int idCap = Integer.parseInt(request.getParameter("cap"));
		int idLinhVuc = Integer.parseInt(request.getParameter("linhvuc"));
		String tinhcapthiet = request.getParameter("tinhcapthiet");
		String muctieu = request.getParameter("muctieu");
		String ndchinh = request.getParameter("ndchinh");
		String ketqua = request.getParameter("ketqua");
		String spkhoahoc = request.getParameter("spkhoahoc");
		String spdaotao = request.getParameter("spdaotao");
		String spungdung = request.getParameter("spungdung");
		String hieuquadk = request.getParameter("hieuquadk");
		int yeucaukinhphi = Integer.parseInt(request
				.getParameter("yeucaukinhphi"));
		int thoigian = Integer.parseInt(request.getParameter("thoigian"));
		int slntv = Integer.parseInt(request.getParameter("slntv"));
		String danhsachtv = request.getParameter("danhsachtv");
		DeTai detai = new DeTai();
		detai.setIdDeTai(idDeTai);
		detai.setTenDeTai(tendetai);
		detai.setIdLinhVuc(idLinhVuc);
		detai.setTinhcapthiet(tinhcapthiet);
		detai.setMuctieu(muctieu);
		detai.setNoidung(ndchinh);

		detai.setKetquadukien(ketqua);
		detai.setSanphamkhoahoc(spkhoahoc);
		detai.setSanphamdaotao(spdaotao);
		detai.setSanphamungdung(spungdung);
		detai.setHieuquadukien(hieuquadk);
		detai.setKinhphidukien(yeucaukinhphi);
		detai.setThoigiandukien(thoigian);
		detai.setSoluongtv(slntv);
		detai.setIdUsers(users.getIdUser());
		detai.setXacnhandangky(xacnhandangky);
		detai.setDanhsachtv(danhsachtv);
		detai.setIdCap(idCap);
		DeTaiBO deTaiBO = new DeTaiBO();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		String DateTime = dateFormat.format(date);
		CheckTimeBO checkTimeBO = new CheckTimeBO();
		//kiem tra thoi gian dang ky hop le
		if (checkTimeBO.checkTime(DateTime, idCap)) {
			String mangtv[] = danhsachtv.split(",");
			if (mangtv.length != slntv) {	//kiểm tra số lượng với danh sách
				RequestDispatcher rd = request
						.getRequestDispatcher("chi-tiet?msg=Số lượng thành viên không khớp với số lượng danh sách nhập vào&&de-tai="+idDeTai);
				rd.forward(request, response);
				return;
			}
			if (detai.checkExist(mangtv)) {		//kiểm tra số thẻ nhập vào có có lặp
				RequestDispatcher rd = request
						.getRequestDispatcher("chi-tiet?msg=Số thẻ bị lặp&&de-tai="+idDeTai);
				rd.forward(request, response);
				return;
			}
			String dsID = deTaiBO.convertIDThanhVien(mangtv);	//chuyển số thẻ sang danh sách id
			detai.setDanhsachtv(dsID);
			if (dsID == null) {					//nếu danh sách = null không tồn tại số thẻ
				RequestDispatcher rd = request
						.getRequestDispatcher("chi-tiet?msg=Thành viên trong danh sách không tồn tại&&de-tai="+idDeTai);
				rd.forward(request, response);
				// response.sendRedirect("load-form?msg=Thành viên trong danh sách không tồn tại");
				return;
			} else {
				if (deTaiBO.editDeTai(detai)) {	//thực hiện update
					response.sendRedirect("welcome");
				} else {
					RequestDispatcher rd = request
							.getRequestDispatcher("chi-tiet?msg=Có lỗi trong quá trình đăng ký&&de-tai="+idDeTai);
					rd.forward(request, response);
				}
			}
		}else{
			RequestDispatcher rd = request
					.getRequestDispatcher("chi-tiet?msg=Hết hạn đăng ký&&de-tai="+idDeTai);
			rd.forward(request, response);
			return;
		}

	}

}
