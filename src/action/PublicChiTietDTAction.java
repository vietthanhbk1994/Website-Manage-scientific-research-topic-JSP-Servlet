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

import bean.DeTai;
import bean.Linhvuc;
import bean.Users;
import bo.DeTaiBO;
import bo.LinhVucBO;

/**
 * Servlet implementation class PublicChiTietDTAction
 */
public class PublicChiTietDTAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PublicChiTietDTAction() {
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
		int idDeTai = Integer.parseInt(request.getParameter("de-tai"));
		DeTaiBO deTaiBO = new DeTaiBO();
		LinhVucBO linhVucBO = new LinhVucBO();
		//lay danh sach linh vuc
		ArrayList<Linhvuc> ListLinhVuc = linhVucBO.getLinhVuc();
		request.setAttribute("ListLinhVuc", ListLinhVuc);
		//lay de tai chi tiet
		DeTai detaitamthoi = deTaiBO.getChiTiet(idDeTai);
		request.setAttribute("DeTaiChiTiet", detaitamthoi);
		RequestDispatcher rd =request.getRequestDispatcher("chitietdetai.jsp");
		rd.forward(request, response);
	}

}