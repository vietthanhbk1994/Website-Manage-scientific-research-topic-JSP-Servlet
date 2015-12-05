package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.DeTai;
import bean.Users;
import dao.DeTaiDAO;

/**
 * Servlet implementation class AdminChiTietDTAction
 */
public class AdminChiTietDTAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminChiTietDTAction() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		Users userLogin = (Users) session.getAttribute("users");
		if (userLogin == null || userLogin.getRole() != -1) {
			session.invalidate();
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		int idDeTai = Integer.parseInt(request.getParameter("detai"));
		DeTaiDAO deTaiDAO = new DeTaiDAO();
		DeTai detai = deTaiDAO.getChiTiet(idDeTai);
		request.setAttribute("chitiet", detai);
		RequestDispatcher rd = request.getRequestDispatcher("chitietdetai.jsp");
		rd.forward(request, response);
	}

}
