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
import bean.ThongBao;
import bean.Users;
import bo.CapBO;
import bo.ThongBaoBO;

/**
 * Servlet implementation class AdminSettingAction
 */
//@WebServlet("/AdminSettingAction")
public class AdminSettingAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminSettingAction() {
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
		response.setContentType("text/html");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter write = response.getWriter() ;
		HttpSession session = request.getSession();
		Users userLogin = (Users) session.getAttribute("users");
		if (userLogin == null || userLogin.getRole() !=-1) {
			session.invalidate();
			response.sendRedirect(request.getContextPath() + "/login");
		}
		else{
			//them sua xoa cap de tai
			CapBO capbo = new CapBO();
			String msg="";
			if(request.getParameter("load")!=null){
				String load = request.getParameter("load");
				//xoa cap
				if(load.equals("xoa")){
					String[] listXoaCap = request.getParameterValues("xoathongbao");
					if(listXoaCap == null) {
						msg="Chưa chọn mục để xóa";
					}
					else{
						if(capbo.xoaCap(listXoaCap)){
							msg="Xóa thành công";
						}
						else{
							write.println("Không thể xóa. Lỗi");
							return;
						}
					}
				}
				//them cap
				if(load.equals("them")){
					String tenCap = request.getParameter("tenCapThem");
					
					if(capbo.themCap(tenCap)){
						msg="Thêm thành công";
					}
					else{
						write.println("Không thể thêm. Lỗi");
						return;
					}
					
				}
				//sua cap
				if(load.equals("sua")){
					if(request.getParameter("act")!=null){
						int idSua = Integer.parseInt(request.getParameter("idSua"));
						String tenSua = request.getParameter("tenSua");
						Cap cap = new Cap(idSua, tenSua);
						if(capbo.suaCap(cap)){
							request.setAttribute("cap", cap);
							ArrayList<Cap> listCap = capbo.getListCap();
							request.setAttribute("listCap", listCap);
							RequestDispatcher rd = request.getRequestDispatcher("/admin/settingcapdetai.jsp");
							rd.forward(request, response);
							return;
						}
						write.print("Lỗi. Ko sửa dc");
						return;
					}
					else{
						//load ra trang sua
						int idSua = Integer.parseInt(request.getParameter("idSua"));
						String tenSua = request.getParameter("tenSua");
						Cap cap = new Cap(idSua, tenSua);
						request.setAttribute("cap", cap);
						RequestDispatcher rd = request.getRequestDispatcher("/admin/suacap.jsp");
						rd.forward(request, response);
						return;
						
					}					
				}
				
			}
				//load trang danh cap de tai
				ArrayList<Cap> listCap = capbo.getListCap();
				request.setAttribute("listCap", listCap);
				RequestDispatcher rd = request.getRequestDispatcher("/admin/settingcapdetai.jsp");
				rd.forward(request, response);
			
		}
	}

}
