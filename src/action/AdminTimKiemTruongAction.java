package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.tribes.group.Response;

import bean.Cap;
import bean.DeTai;
import bean.ThongBao;
import bean.Users;
import bo.CapBO;
import bo.DeTaiBO;
import bo.ThongBaoBO;
import bo.UserBO;

/**
 * Servlet implementation class AdminThemThongBaoRootAction
 */
//@WebServlet("/AdminXacNhanKhoaRootAction")
public class AdminTimKiemTruongAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminTimKiemTruongAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		Users userLogin = (Users) session.getAttribute("users");
		if (userLogin == null || userLogin.getRole() !=-1) {
			session.invalidate();
			response.sendRedirect(request.getContextPath() + "/login");
			
		}
		else{
//			PrintWriter pr = response.getWriter();
//			pr.println("da vao day");
			
			
			int idKhoa = 0;
			idKhoa = Integer.parseInt(request.getParameter("idKhoa"));
			
			System.out.println("id Khoa:"+idKhoa);
			
			String soThe =""; 
			soThe = request.getParameter("sothe");
			
			System.out.println("soThe:"+soThe);
			
			String fullname = ""; 
			fullname = request.getParameter("fullname");
			
			System.out.println("fullname:"+fullname);
			
			int nam =0;
			nam = Integer.parseInt(request.getParameter("nam"));
			
			System.out.println("nam:"+nam);
			
			String cap ="";
			cap = request.getParameter("cap");
			
			System.out.println("cap:"+cap);
			
			DeTaiBO detaiBO = new DeTaiBO();
			ArrayList<DeTai> listDeTai = new ArrayList<DeTai>();
			
			
			
			
			
			
			
			
			listDeTai = detaiBO.timKiemDeTaiTruong(idKhoa, soThe, fullname, cap, nam);
			
			CapBO capbo = new CapBO();
			ArrayList<Cap> listCap = new ArrayList<Cap>();
			listCap = capbo.getCapDeTai();
			
			UserBO userBO = new UserBO();
			ArrayList<Users> listKhoa = userBO.getListKhoa();
			
			request.setAttribute("listKhoa", listKhoa);
			request.setAttribute("listCap", listCap);
			request.setAttribute("listDeTai", listDeTai);
			RequestDispatcher rd = request.getRequestDispatcher("/admin/quan-tri-truong.jsp");
			rd.forward(request, response);
			
		}
	}

}

