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
 * Servlet implementation class PublicXacNhanDKAction
 */
public class PublicXacNhanDKAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PublicXacNhanDKAction() {
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
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		// /
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		Users users = (Users) session.getAttribute("users");
		if (users == null || users.getRole() != 0) {
			session.invalidate();
			response.sendRedirect("login");
			return;
		}
		DeTaiBO deTaiBO = new DeTaiBO();
		int idCap = Integer.parseInt(request.getParameter("cap"));
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		String DateTime = dateFormat.format(date);
		CheckTimeBO checkTimeBO = new CheckTimeBO();
		if (checkTimeBO.checkTime(DateTime, idCap)) {
			int idDeTai = Integer.parseInt(request.getParameter("detai"));
//			out.println("done");
			if(deTaiBO.xacNhanDK(idDeTai)){
				response.sendRedirect("welcome");
			}else{
				RequestDispatcher rd = request
						.getRequestDispatcher("welcome?msg=xảy ra lỗi");
				rd.forward(request, response);
			}
			return;
		} else {
			response.sendRedirect("welcome?msg=Het han dang ky");
//			RequestDispatcher rd = request
//					.getRequestDispatcher("welcome?msg=Hết hạn đăng ký");
//			rd.forward(request, response);
		}
		
	}

}
