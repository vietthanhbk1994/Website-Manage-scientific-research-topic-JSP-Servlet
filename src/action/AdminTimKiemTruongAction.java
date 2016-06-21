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
import bo.LuotDangKyBO;
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
			int idCap = 0;
			idCap = Integer.parseInt(request.getParameter("cap"));
			int nam = 0;
			nam = Integer.parseInt(request.getParameter("nam"));
			
			String soThe =""; 
			soThe = request.getParameter("sothe");
			
			String fullname = ""; 
			fullname = request.getParameter("fullname");
			
			String namSearch = String.valueOf(nam);
			
			DeTai deTaiSearch = new DeTai();
			Users userSearch = new Users();
			userSearch.setSoThe(soThe);
			userSearch.setFullname(fullname);
			
			deTaiSearch.setUsers(userSearch);
			deTaiSearch.setIdCap(idCap);
			deTaiSearch.setIdKhoa(idKhoa);
			deTaiSearch.setNgaydangky(namSearch);
			
			DeTaiBO detaiBO = new DeTaiBO();
			ArrayList<DeTai> listDeTai = new ArrayList<DeTai>();
			
			listDeTai = detaiBO.timKiemDeTaiTruong(idKhoa, soThe, fullname, idCap, nam);
			
			CapBO capbo = new CapBO();
			ArrayList<Cap> listCap = new ArrayList<Cap>();
			listCap = capbo.getListCap();
			//listCap = capbo.getCapDeTai();
			
			UserBO userBO = new UserBO();
			ArrayList<Users> listKhoa = userBO.getListKhoa();
			
			LuotDangKyBO luotDangKyBO = new LuotDangKyBO();
			int [] namShow= luotDangKyBO.getNam();
			
			request.setAttribute("deTaiSearch", deTaiSearch);
			request.setAttribute("nam", namShow);
			request.setAttribute("listKhoa", listKhoa);
			request.setAttribute("listCap", listCap);
			request.setAttribute("listDeTai", listDeTai);
			RequestDispatcher rd = request.getRequestDispatcher("/admin/quan-tri-truong.jsp");
			rd.forward(request, response);
		}
	}

}

