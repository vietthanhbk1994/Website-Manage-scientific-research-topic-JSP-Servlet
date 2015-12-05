package action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Users;

/**
 * Servlet implementation class PublicFormAction
 */
//@WebServlet("/PublicFormAction")
public class PublicFormAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PublicFormAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		Users userLogin = (Users) session.getAttribute("users");
		if (userLogin == null || userLogin.getRole() !=-1) {
			session.invalidate();
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		
		//de tai
		String tenDeTai = request.getParameter("tendetai");
		//lay ngay dang ky
		
		
		String linhVuc = request.getParameter("linhvuc");
		String tinhCapThiet = request.getParameter("tinhcapthiet");
		String mucTieu = request.getParameter("muctieu");
		String ndChinh = request.getParameter("ndchinh");
		String ketQua = request.getParameter("ketqua");
		String spKhoaHoc = request.getParameter("spkhoahoc");
		String spDaoTao = request.getParameter("spdaotao");
		String spUngDung = request.getParameter("spungdung");
		String hieuQuadk = request.getParameter("hieuquadk");
		String yeuCauKinhPhi = request.getParameter("yeucaukinhphi");
		String thoiGian = request.getParameter("thoigian");
		String slncs = request.getParameter("slncs");
		
		
		
	}

}
