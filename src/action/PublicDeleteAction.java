package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Users;
import bo.DeTaiBO;

/**
 * Servlet implementation class PublicDeleteAction
 */
public class PublicDeleteAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PublicDeleteAction() {
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
		String arrayDel[]=request.getParameterValues("checkbox");
		if(arrayDel==null){
			RequestDispatcher rd = request.getRequestDispatcher("welcome?msg=Chưa chọn đề tài");
			rd.forward(request, response);
			return;
		}
		DeTaiBO deTaiBO = new DeTaiBO();
		if(deTaiBO.delDeTai(arrayDel)){
			RequestDispatcher rd = request.getRequestDispatcher("welcome?msg=Thực hiện xong");
			rd.forward(request, response);
			return;
		}else{
			RequestDispatcher rd = request.getRequestDispatcher("welcome?msg=Có lỗi trong quá trình xóa");
			rd.forward(request, response);
			return;
		}
	}

}
