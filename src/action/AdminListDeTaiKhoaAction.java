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
 * Servlet implementation class AdminListDeTaiKhoaAction
 */
public class AdminListDeTaiKhoaAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminListDeTaiKhoaAction() {
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
		if (userLogin == null || userLogin.getRole() ==0 || userLogin.getRole() ==-1 ) {
			session.invalidate();
			response.sendRedirect(request.getContextPath() + "/login");
			
		}
		else{
			int idKhoa = userLogin.getIdKhoa();
			ArrayList<DeTai> listDeTai = new ArrayList<DeTai>();
			DeTaiBO detaiBO = new DeTaiBO();
			
			CapBO capbo = new CapBO();
			ArrayList<Cap> listCap = new ArrayList<Cap>();
			listCap = capbo.getListCap();
			
			LuotDangKyBO luotDangKyBO = new LuotDangKyBO();
			int [] nam= luotDangKyBO.getNam();
			
			
			String submit = request.getParameter("xacnhan");
			if(submit != null){
				String listXacNhan[] = request.getParameterValues("checkbox");
				if(listXacNhan!=null){
					StringBuilder listxacnhan = new StringBuilder("");
					for(String idDeTai:listXacNhan){
						listxacnhan.append(idDeTai+"||");
					}
					listxacnhan.deleteCharAt(listxacnhan.length()-1);
					listxacnhan.deleteCharAt(listxacnhan.length()-1);
					
					if(detaiBO.xacnhanKhoa(listxacnhan)){
						listDeTai = detaiBO.getListDeTaiKhoa(idKhoa);
						request.setAttribute("listDeTai", listDeTai);
						request.setAttribute("nam", nam);
						request.setAttribute("listCap", listCap);
						RequestDispatcher rd = request.getRequestDispatcher("quan-tri-khoa.jsp");
						rd.forward(request, response);
						return;
					}
				}
			}
			listDeTai = detaiBO.getListDeTaiKhoa(idKhoa);
			String tenKhoa = userLogin.getTenKhoa();
			request.setAttribute("tenKhoa", tenKhoa);
			request.setAttribute("listDeTai", listDeTai);
			request.setAttribute("nam", nam);
			request.setAttribute("listCap", listCap);
			RequestDispatcher rd = request.getRequestDispatcher("quan-tri-khoa.jsp");
			rd.forward(request, response);
		}
	}
}
