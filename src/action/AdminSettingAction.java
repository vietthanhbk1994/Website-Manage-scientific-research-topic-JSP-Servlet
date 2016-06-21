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
import bean.ChuyenNganh;
import bean.Khoa;
import bean.Linhvuc;
import bean.ThongBao;
import bean.Users;
import bo.CapBO;
import bo.ChuyenNganhBO;
import bo.KhoaBO;
import bo.LinhVucBO;
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
			KhoaBO khoabo = new KhoaBO();
			ChuyenNganhBO chuyennganhbo = new ChuyenNganhBO();
			CapBO capbo = new CapBO();
			LinhVucBO linhvucbo = new LinhVucBO();
			String msg="";
			String chose = request.getParameter("chose");
			//-------------------------hieu chinh Cap de tai ,them sua xoa cap de tai-----------------------------------------------------------------
			if(chose.equals("capdetai")){
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
								//request.setAttribute("cap", cap);
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
			//-------------------------Them sua xoa linh vuc---------------------------------------
			if(chose.equals("linhvuc")){
				if(request.getParameter("load")!=null){
					String load = request.getParameter("load");
					//xoa linh vuc
					if(load.equals("xoa")){
						String[] listXoaLinhVuc = request.getParameterValues("xoathongbao");
						if(listXoaLinhVuc == null) {
							msg="Chưa chọn mục để xóa";
						}
						else{
							if(linhvucbo.xoaLinhVuc(listXoaLinhVuc)){
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
						String tenLinhVuc = request.getParameter("tenCapThem");
						
						if(linhvucbo.themLinhVuc(tenLinhVuc)){
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
							Linhvuc linhVuc = new Linhvuc(idSua, tenSua);
							if(linhvucbo.suaLinhVuc(linhVuc)){
								//request.setAttribute("linhVuc", linhVuc);
								ArrayList<Linhvuc> listLinhVuc = linhvucbo.getLinhVuc();
								request.setAttribute("listLinhVuc", listLinhVuc);
								RequestDispatcher rd = request.getRequestDispatcher("/admin/settinglinhvuc.jsp");
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
							Linhvuc linhVuc = new Linhvuc(idSua, tenSua);
							request.setAttribute("linhVuc", linhVuc);
							RequestDispatcher rd = request.getRequestDispatcher("/admin/sualinhvuc.jsp");
							rd.forward(request, response);
							return;
							
						}					
					}
					
				}
					//load trang danh linh vuc
					ArrayList<Linhvuc> listLinhVuc = linhvucbo.getLinhVuc();
					request.setAttribute("listLinhVuc", listLinhVuc);
					RequestDispatcher rd = request.getRequestDispatcher("/admin/settinglinhvuc.jsp");
					rd.forward(request, response);
			}
			//-------------------------Them sua xoa khoa ---------------------------------------
			if(chose.equals("khoa")){
				if(request.getParameter("load")!=null){
					String load = request.getParameter("load");
					//xoa khoa
					if(load.equals("xoa")){
						String[] listXoaKhoa = request.getParameterValues("xoathongbao");
						if(listXoaKhoa == null) {
							msg="Chưa chọn mục để xóa";
						}
						else{
							if(khoabo.xoaKhoa(listXoaKhoa)){
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
						String tenKhoa = request.getParameter("tenCapThem");
						
						if(khoabo.themKhoa(tenKhoa)){
							msg="Thêm thành công";
						}
						else{
							write.println("Không thể thêm. Lỗi");
							return;
						}
						
					}
					//sua khoa
					if(load.equals("sua")){
						if(request.getParameter("act")!=null){
							int idSua = Integer.parseInt(request.getParameter("idSua"));
							String tenSua = request.getParameter("tenSua");
							Khoa khoa = new Khoa();
							khoa.setIdKhoa(idSua);
							khoa.setTenKhoa(tenSua);
							
							if(khoabo.suaKhoa(khoa)){
								ArrayList<Khoa> listKhoaChuyenNganh = khoabo.getlistKhoaChuyenNganh();
								request.setAttribute("listKhoaChuyenNganh", listKhoaChuyenNganh);
								RequestDispatcher rd = request.getRequestDispatcher("/admin/settingkhoa.jsp");
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
							Khoa khoa = new Khoa();
							khoa.setIdKhoa(idSua);
							khoa.setTenKhoa(tenSua);
							
							request.setAttribute("khoa", khoa);
							RequestDispatcher rd = request.getRequestDispatcher("/admin/suakhoa.jsp");
							rd.forward(request, response);
							return;
							
						}					
					}
					
				}
					//load trang danh sach khoa
					ArrayList<Khoa> listKhoaChuyenNganh = khoabo.getlistKhoaChuyenNganh();
					request.setAttribute("listKhoaChuyenNganh", listKhoaChuyenNganh);
					RequestDispatcher rd = request.getRequestDispatcher("/admin/settingkhoa.jsp");
					rd.forward(request, response);
			}
			//-------------------------Them sua xoa chuyen nganh ---------------------------------------
			if(chose.equals("chuyennganh")){
				if(request.getParameter("load")!=null){
					String load = request.getParameter("load");
					//xoa  chuyen nganh
					if(load.equals("xoa")){
						String[] listXoaChuyenNganh = request.getParameterValues("xoathongbao");
						if(listXoaChuyenNganh == null) {
							msg="Chưa chọn mục để xóa";
						}
						else{
							if(chuyennganhbo.xoaChuyenNganh(listXoaChuyenNganh)){
								msg="Xóa thành công";
							}
							else{
								write.println("Không thể xóa. Lỗi");
								return;
							}
						}
					}
					//them  chuyen nganh
					if(load.equals("them")){
						String tenChuyenNganh = request.getParameter("tenCapThem");
						int idKhoa = Integer.parseInt(request.getParameter("idKhoa"));
						if(chuyennganhbo.themChuyenNganh(idKhoa,tenChuyenNganh)){
							msg="Thêm thành công";
						}
						else{
							write.println("Không thể thêm. Lỗi");
							return;
						}
						
					}
					//sua  chuyen nganh
					if(load.equals("sua")){
						if(request.getParameter("act")!=null){
							int idSua = Integer.parseInt(request.getParameter("idSua"));
							String tenSua = request.getParameter("tenSua");
							int idKhoa = Integer.parseInt(request.getParameter("idKhoa"));
							ChuyenNganh cn = new ChuyenNganh();
							cn.setIdKhoa(idKhoa);
							cn.setIdChuyenNganh(idSua);
							cn.setTenChuyenNganh(tenSua);
							
							if(chuyennganhbo.suaChuyenNganh(cn)){
								ArrayList<Khoa> listKhoaChuyenNganh = khoabo.getlistKhoaChuyenNganh();
								request.setAttribute("listKhoaChuyenNganh", listKhoaChuyenNganh);
								RequestDispatcher rd = request.getRequestDispatcher("/admin/settingchuyennganh.jsp");
								rd.forward(request, response);
								return;
							}
							write.print("Lỗi. Ko sửa dc");
							return;
						}
						else{
							//load ra trang sua
							int idSua = Integer.parseInt(request.getParameter("idSua"));
							int idKhoa = Integer.parseInt(request.getParameter("idKhoa"));
							String tenSua = request.getParameter("tenSua");
							
							ChuyenNganh cn = new ChuyenNganh();
							cn.setIdKhoa(idKhoa);
							cn.setIdChuyenNganh(idSua);
							cn.setTenChuyenNganh(tenSua);
							ArrayList<Khoa> listKhoaChuyenNganh = khoabo.getlistKhoaChuyenNganh();
							
							request.setAttribute("listKhoaChuyenNganh", listKhoaChuyenNganh);
							request.setAttribute("chuyenNganh", cn);
							RequestDispatcher rd = request.getRequestDispatcher("/admin/suachuyennganh.jsp");
							rd.forward(request, response);
							return;
							
						}					
					}
					
				}
					//load trang danh sach  chuyen nganh
					ArrayList<Khoa> listKhoaChuyenNganh = khoabo.getlistKhoaChuyenNganh();
					request.setAttribute("listKhoaChuyenNganh", listKhoaChuyenNganh);
					RequestDispatcher rd = request.getRequestDispatcher("/admin/settingchuyennganh.jsp");
					rd.forward(request, response);
			}
		}
	}

}
