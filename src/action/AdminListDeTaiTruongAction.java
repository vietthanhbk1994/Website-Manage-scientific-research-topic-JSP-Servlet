package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Cap;
import bean.DeTai;
import bean.Users;
import bo.CapBO;
import bo.DeTaiBO;
import bo.LuotDangKyBO;
import bo.UserBO;

/**
 * Servlet implementation class AdminListDeTai
 */
public class AdminListDeTaiTruongAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminListDeTaiTruongAction() {
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
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		// kiểm tra đăng nhập
		HttpSession session = request.getSession();
		Users userLogin = (Users) session.getAttribute("users");
		if (userLogin == null || userLogin.getRole() !=-1) {
			session.invalidate();
			response.sendRedirect(request.getContextPath() + "/login");
		}
		else{
			
			UserBO userBO = new UserBO();
			ArrayList<Users> listKhoa = userBO.getListKhoa();
			
			DeTaiBO detaiBO = new DeTaiBO();
			ArrayList<DeTai> listDeTai = new ArrayList<DeTai>();
			listDeTai = detaiBO.getListDeTaiTruong();
			
			CapBO capbo = new CapBO();
			ArrayList<Cap> listCap = new ArrayList<Cap>();
			listCap = capbo.getListCap();
			
			LuotDangKyBO luotDangKyBO = new LuotDangKyBO();
			int [] nam= luotDangKyBO.getNam();
			
			request.setAttribute("nam", nam);
			request.setAttribute("listKhoa", listKhoa);
			request.setAttribute("listCap", listCap);
			request.setAttribute("listDeTai", listDeTai);
			
			RequestDispatcher rd = request.getRequestDispatcher("/admin/quan-tri-truong.jsp");
			rd.forward(request, response);
		}
	}

}
