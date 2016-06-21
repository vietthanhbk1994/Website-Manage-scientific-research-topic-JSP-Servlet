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

import bean.ThongBao;
import bean.Users;
import bo.ThongBaoBO;
import bo.UserBO;

/**
 * Servlet implementation class PublicLoginAction
 */
public class PublicLoginAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PublicLoginAction() {
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
		
		String submit = request.getParameter("dangnhap");
		if (submit != null) {
			String sothe = request.getParameter("username");
			String password = request.getParameter("password");
			Users users = new Users();
			UserBO userBO = new UserBO();
			users = userBO.checkLogin(sothe, password);
			if (users.getFullname() != null) {
				HttpSession session = request.getSession();
				session.setAttribute("users", users);
				int role = users.getRole();
				if (role != 0) {
					if (role == -1) {
						response.sendRedirect("admin/quan-tri-truong");
					} else {
						response.sendRedirect("admin/quan-tri-khoa");
					}
				} else {
					response.sendRedirect("welcome");
				}
			} else {
				ThongBaoBO thongBaoBO = new ThongBaoBO();
				ArrayList<ThongBao> listThongBao = new ArrayList<ThongBao>();
				listThongBao = thongBaoBO.getListThongBao();
				request.setAttribute("listThongBao", listThongBao);
				RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
				rd.forward(request, response);
			}
		} else {
			ThongBaoBO thongBaoBO = new ThongBaoBO();
			ArrayList<ThongBao> listThongBao = new ArrayList<ThongBao>();
			listThongBao = thongBaoBO.getListThongBao();
			request.setAttribute("listThongBao", listThongBao);
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);
		}
	}

}
