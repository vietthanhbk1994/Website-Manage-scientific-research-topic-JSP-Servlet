package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Cap;
import bean.Linhvuc;
import bean.Users;
import bo.CapBO;
import bo.LinhVucBO;

/**
 * Servlet implementation class PublicLoadFormAction
 */
public class PublicLoadFormAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PublicLoadFormAction() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		LinhVucBO linhVucBO = new LinhVucBO();
		ArrayList<Linhvuc> ListLinhVuc = linhVucBO.getLinhVuc();
		
//		int idCap = (int) request.getAttribute("idCap");
		CapBO capbo = new CapBO();
		ArrayList<Cap> ListCap = capbo.getListCap();
		request.setAttribute("ListCap", ListCap);
//		request.setAttribute("idCap", idCap);
		int idCap = Integer.parseInt(request.getParameter("cap"));
		request.setAttribute("ListLinhVuc", ListLinhVuc);
		RequestDispatcher rd = request.getRequestDispatcher("dangky.jsp");
		rd.forward(request, response);
	}

}
