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
import bean.Users;
import bo.CapBO;
import bo.DeTaiBO;
import bo.LuotDangKyBO;

/**
 * Servlet implementation class PublicWelcomeAction
 */
//@WebServlet("/PublicWelcomeAction")
public class PublicWelcomeAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PublicWelcomeAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		///
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		Users users = (Users) session.getAttribute("users");
		if(users==null||users.getRole()!=0){
			session.invalidate();
			response.sendRedirect("login");
			return;
		}
		//load de tai dang ky roi
		int idUsers = users.getIdUser();
		DeTaiBO deTaiBO = new DeTaiBO();
		CapBO capbo = new CapBO();
		
		request.setAttribute("ListCap", capbo.getListCap());
		request.setAttribute("ListDeTai", deTaiBO.getListDeTai(idUsers));
		request.setAttribute("listDeTai2", deTaiBO.getListDeTai2(idUsers));
		RequestDispatcher rd = request.getRequestDispatcher("welcome.jsp");
		rd.forward(request, response);
		
		
	}

}
