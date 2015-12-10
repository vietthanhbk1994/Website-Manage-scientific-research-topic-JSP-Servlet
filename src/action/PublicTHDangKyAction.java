package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.DeTai;
import bean.Users;
import bo.DeTaiBO;

/**
 * Servlet implementation class PublicTHDangKyAction
 */
public class PublicTHDangKyAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PublicTHDangKyAction() {
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
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		Users users = (Users) session.getAttribute("users");
		if (users == null || users.getRole() != 0) {
			session.invalidate();
			response.sendRedirect("login");
			return;
		}
		String submit =null;
		submit = request.getParameter("dangky");
		if(submit==null){
			submit = request.getParameter("luulai");
		}
		int xacnhandangky = 0;
		if(submit.equals("Đăng ký")){
			xacnhandangky = 1;
		}
		String tendetai = new String(request.getParameter("tendetai").getBytes("ISO-8859-1"),"UTF-8");
		int idCap = Integer.parseInt(request.getParameter("cap"));
		int idLinhVuc = Integer.parseInt(request.getParameter("linhvuc"));
		String tinhcapthiet = request.getParameter("tinhcapthiet");
		String muctieu = request.getParameter("muctieu");
		String ndchinh = request.getParameter("ndchinh");
		String ketqua = request.getParameter("ketqua");
		String spkhoahoc = request.getParameter("spkhoahoc");
		String spdaotao = request.getParameter("spdaotao");
		String spungdung = request.getParameter("spungdung");
		String hieuquadk = request.getParameter("hieuquadk");
		int yeucaukinhphi = Integer.parseInt(request.getParameter("yeucaukinhphi"));
		int thoigian = Integer.parseInt(request.getParameter("thoigian"));
		int slntv = Integer.parseInt(request.getParameter("slntv"));
		String danhsachtv = request.getParameter("danhsachtv");
		DeTai detai = new DeTai();
		detai.setTenDeTai(tendetai);
		detai.setIdLinhVuc(idLinhVuc);
		detai.setTinhcapthiet(tinhcapthiet);
		detai.setMuctieu(muctieu);
		detai.setNoidung(ndchinh);
		
		detai.setKetquadukien(ketqua);
		detai.setSanphamkhoahoc(spkhoahoc);
		detai.setSanphamdaotao(spdaotao);
		detai.setSanphamungdung(spungdung);
		detai.setHieuquadukien(hieuquadk);
		detai.setKinhphidukien(yeucaukinhphi);
		detai.setThoigiandukien(thoigian);
		detai.setSoluongtv(slntv);
		detai.setIdUsers(users.getIdUser());
		detai.setXacnhandangky(xacnhandangky);
		detai.setDanhsachtv(danhsachtv);
		detai.setIdCap(idCap);
//		lưu lại dữ liệu tạm thời
		request.setAttribute("TamThoi", detai);
		
		DeTaiBO deTaiBO = new DeTaiBO();
		String mangtv[]=danhsachtv.split(",");
		if(mangtv.length!=slntv){
			//response.sendRedirect("load-form?msg=number of member is not suitable with list member");
			RequestDispatcher rd = request.getRequestDispatcher("load-form?msg=Số lượng thành viên không khớp với số lượng danh sách nhập vào");
			rd.forward(request, response);
			return;
		}
		if(detai.checkExist(mangtv)){
			//response.sendRedirect("load-form?msg=bi lap so the");
			RequestDispatcher rd = request.getRequestDispatcher("load-form?msg=Số thẻ bị lặp");
			rd.forward(request, response);
			return;
		}
		String dsID = deTaiBO.convertIDThanhVien(mangtv);
		if(deTaiBO.notSameKhoa(users.getIdKhoa(),dsID)){
			RequestDispatcher rd = request.getRequestDispatcher("load-form?msg=có thành viên không cùng khoa");
			rd.forward(request, response);
			return;
		}
		if(dsID==null){
			RequestDispatcher rd = request.getRequestDispatcher("load-form?msg=Thành viên trong danh sách không tồn tại");
			rd.forward(request, response);
			return;
		}else{
			if(deTaiBO.dangky(detai,dsID)){
				response.sendRedirect("welcome");
			}else{
				RequestDispatcher rd = request.getRequestDispatcher("load-form?msg=Có lỗi trong quá trình đăng ký");
				rd.forward(request, response);
			}
		}
		
		
	}

}
