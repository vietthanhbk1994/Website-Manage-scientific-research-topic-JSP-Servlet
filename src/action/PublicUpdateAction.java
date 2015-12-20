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
		if (submit.equals("Ä�Äƒng kĂ½")) {
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
		if (checkTimeBO.checkTime(DateTime, idCap)!=0) {
			String mangtv[] = danhsachtv.split(",");
			if (mangtv.length != slntv) {	//kiá»ƒm tra sá»‘ lÆ°á»£ng vá»›i danh sĂ¡ch
				RequestDispatcher rd = request
						.getRequestDispatcher("chi-tiet?msg=Sá»‘ lÆ°á»£ng thĂ nh viĂªn khĂ´ng khá»›p vá»›i sá»‘ lÆ°á»£ng danh sĂ¡ch nháº­p vĂ o&&de-tai="+idDeTai);
				rd.forward(request, response);
				return;
			}
			if (detai.checkExist(mangtv)) {		//kiá»ƒm tra sá»‘ tháº» nháº­p vĂ o cĂ³ cĂ³ láº·p
				RequestDispatcher rd = request
						.getRequestDispatcher("chi-tiet?msg=Sá»‘ tháº» bá»‹ láº·p&&de-tai="+idDeTai);
				rd.forward(request, response);
				return;
			}
			String dsID = deTaiBO.convertIDThanhVien(mangtv);	//chuyá»ƒn sá»‘ tháº» sang danh sĂ¡ch id
			detai.setDanhsachtv(dsID);
			if (dsID == null) {					//náº¿u danh sĂ¡ch = null khĂ´ng tá»“n táº¡i sá»‘ tháº»
				RequestDispatcher rd = request
						.getRequestDispatcher("chi-tiet?msg=ThĂ nh viĂªn trong danh sĂ¡ch khĂ´ng tá»“n táº¡i&&de-tai="+idDeTai);
				rd.forward(request, response);
				// response.sendRedirect("load-form?msg=ThĂ nh viĂªn trong danh sĂ¡ch khĂ´ng tá»“n táº¡i");
				return;
			} else {
				if (deTaiBO.editDeTai(detai)) {	//thá»±c hiá»‡n update
					response.sendRedirect("welcome");
				} else {
					RequestDispatcher rd = request
							.getRequestDispatcher("chi-tiet?msg=CĂ³ lá»—i trong quĂ¡ trĂ¬nh Ä‘Äƒng kĂ½&&de-tai="+idDeTai);
					rd.forward(request, response);
				}
			}
		}else{
			RequestDispatcher rd = request
					.getRequestDispatcher("chi-tiet?msg=Háº¿t háº¡n Ä‘Äƒng kĂ½&&de-tai="+idDeTai);
			rd.forward(request, response);
			return;
		}

	}

}
