package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Users;
import bo.CheckTimeBO;

/**
 * Servlet implementation class CheckTimeAction
 */
public class PublicDangKyAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PublicDangKyAction() {
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
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		Users users = (Users) session.getAttribute("users");
		if (users == null || users.getRole() != 0) {
			session.invalidate();
			response.sendRedirect("login");
			return;
		}
		int idCap = Integer.parseInt(request.getParameter("cap"));
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		String DateTime = dateFormat.format(date);
		CheckTimeBO checkTimeBO = new CheckTimeBO();
		
		if (checkTimeBO.checkTime(DateTime, idCap)) {
			RequestDispatcher rd = request.getRequestDispatcher("load-form");
			rd.forward(request, response);
		} else {
			RequestDispatcher rd = request
					.getRequestDispatcher("welcome?msg=Thời gian đăng ký không hợp lệ");
			rd.forward(request, response);
		}

	}

}
