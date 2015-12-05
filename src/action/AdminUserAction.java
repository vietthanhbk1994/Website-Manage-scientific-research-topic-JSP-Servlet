package action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import bean.ChuyenNganh;
import bean.Khoa;
import bean.ThongBao;
import bean.Users;
import bo.KhoaBO;
import bo.ThongBaoBO;
import bo.UserBO;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

/**
 * Servlet implementation class AdminUserAction
 */
// @WebServlet("/AdminUserAction")
public class AdminUserAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminUserAction() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
		// System.out.println("0");
		Users userLogin = (Users) session.getAttribute("users");
		if (userLogin == null || userLogin.getRole() != -1) {
			session.invalidate();
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		PrintWriter write = response.getWriter();
		UserBO userBO = new UserBO();
		
		KhoaBO khoabo = new KhoaBO();
		if (request.getParameter("load") != null) {
			// kiem tra hanh dong them hoac sua
			if (request.getParameter("act") != null) {
				// them user
				String soThe = request.getParameter("soThe");
				// System.out.println("2");
				String fullname = request.getParameter("fullname");
				String password = request.getParameter("password");
				String quyen = request.getParameter("role");
				int role = Integer.parseInt(quyen);
				String address = request.getParameter("address");
				String dienThoai = request.getParameter("dienthoai");
				String email = request.getParameter("email");
				String idchuyennganh = request.getParameter("chuyennganh");
				String maKhoaNganh[] = idchuyennganh.split(" ");
				
				int idKhoa = Integer.parseInt(maKhoaNganh[0]);
				int idChuyenNganh = Integer.parseInt(maKhoaNganh[1]); 
				Users user = new Users();

				user.setFullname(fullname);
				user.setSoThe(soThe);
				user.setPassword(password);

				if (role != 0)
					role = idKhoa;
				user.setRole(role);
				user.setDienthoai(dienThoai);
				user.setEmail(email);
				user.setAddress(address);
				user.setIdChuyenNganh(idChuyenNganh);
				//System.out.println("khoa ngang"+idchuyennganh+"chuyen nganh:"+idChuyenNganh+"Khoa:"+idKhoa);
				
				// them user
				if (request.getParameter("act").equals("dathem") && request.getParameter("load").equals("them")) {
					if (userBO.issetUserBO(soThe)) {
						
						ArrayList<Khoa> listKhoaChuyenNganh = khoabo.getlistKhoaChuyenNganh();
						request.setAttribute("listKhoaChuyenNganh", listKhoaChuyenNganh);
						RequestDispatcher rd = request
								.getRequestDispatcher("/admin/themuser.jsp?msg=Mã số thẻ đã tồn tại");
						// System.out.println("1");
						rd.forward(request, response);
						return;
					}
					if (userBO.themUserBO(user)) {
						ArrayList<Users> listUser = new ArrayList<Users>();
						listUser = userBO.getListUser();
						request.setAttribute("listUser", listUser);
						RequestDispatcher rd = request.getRequestDispatcher("/admin/hieuchinhuser.jsp");
						rd.forward(request, response);
						return;
					} else {
						PrintWriter writer = response.getWriter();
						write.println("Quá trình thêm dữ liệu vào Database bị lỗi. Vui lòng thử lại");
					}
				}

				// sua user
				if (request.getParameter("act").equals("dasua") && request.getParameter("load").equals("sua")) {
					int idUser = Integer.parseInt(request.getParameter("id"));
					user.setIdUser(idUser);
					if (!userBO.getUserSua(idUser).getSoThe().equals(soThe)) {
						if (userBO.issetUserBO(soThe)) {
							ArrayList<Khoa> listKhoaChuyenNganh = khoabo.getlistKhoaChuyenNganh();
							request.setAttribute("listKhoaChuyenNganh", listKhoaChuyenNganh);
							RequestDispatcher rd = request.getRequestDispatcher("/admin/themuser.jsp?msg=Mã số thẻ đã tồn tại");
							// System.out.println("1");
							rd.forward(request, response);
							return;
						}
					}
					if (userBO.suaUserBO(user)) {
						ArrayList<Users> listUser = new ArrayList<Users>();
						listUser = userBO.getListUser();
						request.setAttribute("listUser", listUser);
						RequestDispatcher rd = request.getRequestDispatcher("/admin/hieuchinhuser.jsp");
						rd.forward(request, response);
						return;
					} else {
						PrintWriter writer = response.getWriter();
						write.println("Quá trình thêm dữ liệu vào Database bị lỗi. Vui lòng thử lại");
					}
				}
			}
			// them user tu file
			if (request.getParameter("load").equals("themtufile")) {
				DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
				ServletFileUpload upload = new ServletFileUpload(fileItemFactory);
				String tenFile = "", linkFile = "";
				String msg = "";
				try {
					List<FileItem> formitems = upload.parseRequest(request);
					for (FileItem fileItem : formitems) {
						if (fileItem.isFormField()) {// khĂ¡c file
							msg = "File không được chọn";
							break;
						} else { // lĂ  file
							String filename = fileItem.getName();
							// Ä‘á»•i tĂªn file
							if (fileItem != null) {
								String ext = FilenameUtils.getExtension(filename);
								if (!ext.equals("xls")) {
									msg = "File không đúng định dạng. Phải chọn file *.xls định dạng excel 97-2003 WorkBook";
									break;
								}
								long time = System.nanoTime();// láº¥y thá»�i
																// gian
								tenFile = "user-" + time + "." + ext;
								// táº¡o thÆ° má»¥c upload file
								String uploadDir = request.getServletContext().getRealPath("") + java.io.File.separator
										+ "files";
								File dir = new File(uploadDir);
								if (!dir.exists()) {
									dir.mkdirs();
								}
								// out.println(uploadDir);
								// táº¡o Ä‘Æ°á»�ng dáº«n thá»±c Ä‘áº¿n file
								// trĂªn Ä‘Ä©a.
								String RealPathFile = uploadDir + File.separator + tenFile;
								linkFile = RealPathFile;
								// upload file lĂªn á»• Ä‘Ä©a
								File file = new File(RealPathFile);
								fileItem.write(file);

							}
						}
					}
				} catch (FileUploadException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String linkFileExcel = linkFile.replace('\\', '/');
				// --------------------------------------------
				// neu ko co file excel dung dinh dang
				if (msg != "") {
					ArrayList<Users> listUser = new ArrayList<Users>();
					listUser = userBO.getListUser();
					request.setAttribute("listUser", listUser);
					RequestDispatcher rd = request.getRequestDispatcher("/admin/hieuchinhuser.jsp?msg=" + msg);
					rd.forward(request, response);
					return;
				}
				// neu co file excel dung dinh dang
				File ex = new File(linkFileExcel);
				Workbook w;
				
				UserBO userbo = new UserBO();
			
				try {
					w = Workbook.getWorkbook(ex);
					Sheet s = w.getSheet(0);
					for (int i = 1; i < s.getRows(); i++) {
						int j = 1;
						Users user = new Users();
						
						String soThe = s.getCell(j++, i).getContents();
						String password = s.getCell(j++, i).getContents();
						String fullname = s.getCell(j++, i).getContents();
						
						String khoa = s.getCell(j++, i).getContents();
						String chuyenNganh = s.getCell(j++, i).getContents();
						
						int idKhoa = 0,idChuyenNganh = 0;
						
						
						ArrayList<Khoa> listKhoaChuyenNganh = new ArrayList<Khoa>();
						listKhoaChuyenNganh = khoabo.getlistKhoaChuyenNganh();
						for(Khoa eachKhoa: listKhoaChuyenNganh){
							if(eachKhoa.getTenKhoa().equalsIgnoreCase(khoa)){
								idKhoa = eachKhoa.getIdKhoa();
								for(ChuyenNganh eachChuyenNganh: eachKhoa.getListChuyenNganh()){
									if(eachChuyenNganh.getTenChuyenNganh().equalsIgnoreCase(chuyenNganh)){
										idChuyenNganh = eachChuyenNganh.getIdChuyenNganh();
										break;
									}
								}
								break;
							}
						}
						//role
						int role=0;
						String quyen = s.getCell(j++, i).getContents();
						if(quyen.equalsIgnoreCase("Quản trị trường")){
							role=-1;
						}
						else if(quyen.equalsIgnoreCase("Quản trị khoa")){
							role=idKhoa;
						}
						String address = s.getCell(j++, i).getContents();
						String dienThoai = s.getCell(j++, i).getContents();
						String email = s.getCell(j++, i).getContents();
						user.setSoThe(soThe);
						user.setPassword(password);
						user.setFullname(fullname);
						user.setIdKhoa(idKhoa);
						user.setIdChuyenNganh(idChuyenNganh);
						user.setRole(role);
						user.setAddress(address);
						user.setDienthoai(dienThoai);
						user.setEmail(email);
						
						userbo.themUserBO(user);
					}
				} catch (BiffException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ArrayList<Users> listUser = new ArrayList<Users>();
				listUser = userBO.getListUser();
				request.setAttribute("listUser", listUser);
				RequestDispatcher rd = request.getRequestDispatcher("/admin/hieuchinhuser.jsp");
				rd.forward(request, response);
				return;
			}

			// chuyen qua trang them user
			if (request.getParameter("load").equals("them")) {
				// lay danh sach khoa
				ArrayList<Users> listKhoa = userBO.getListKhoa();
				
				ArrayList<Khoa> listKhoaChuyenNganh = khoabo.getlistKhoaChuyenNganh();
				request.setAttribute("listKhoaChuyenNganh", listKhoaChuyenNganh);
				//request.setAttribute("listKhoa", listKhoa);
				RequestDispatcher rd = request.getRequestDispatcher("/admin/themuser.jsp");
				rd.forward(request, response);
				return;
			}
			// chuyen qua trang sua user
			if (request.getParameter("load").equals("sua")) {

				String idUserSua = request.getParameter("id");
				if (idUserSua != null) {
					int idUser = Integer.parseInt(idUserSua);
					Users UserSua = userBO.getUserSua(idUser);
					request.setAttribute("NoiDungSua", UserSua);
					ArrayList<Khoa> listKhoaChuyenNganh = khoabo.getlistKhoaChuyenNganh();
					request.setAttribute("listKhoaChuyenNganh", listKhoaChuyenNganh);
					RequestDispatcher rd = request.getRequestDispatcher("/admin/suauser.jsp");
					rd.forward(request, response);
				} else {
					RequestDispatcher rd = request.getRequestDispatcher("/admin/hieu-chinh-user");
					rd.forward(request, response);
				}
				return;
			}
		}
		// xoa user
		if (request.getParameter("submit") != null) {
			String[] listXoaUser = request.getParameterValues("xoaUser");
			ArrayList<Users> listUser = new ArrayList<Users>();
			if (listXoaUser == null) {
				listUser = userBO.getListUser();
				request.setAttribute("listUser", listUser);
				RequestDispatcher rd = request.getRequestDispatcher("/admin/hieuchinhuser.jsp?msg=Chưa chọn user để xóa");
				rd.forward(request, response);
				return;
			} else {
				if (userBO.checkXoaUser(listXoaUser)) {
					//System.out.println("1. dung");
					listUser = userBO.getListUser();
					//System.out.println("1.1.");
					request.setAttribute("listUser", listUser);
					RequestDispatcher rd = request.getRequestDispatcher("/admin/hieuchinhuser.jsp");
					rd.forward(request, response);
					//System.out.println("ok");
					return;
				} else {
					//System.out.println("2. sai");
					listUser = userBO.getListUser();
					request.setAttribute("listUser", listUser);
					RequestDispatcher rd = request.getRequestDispatcher("/admin/hieuchinhuser.jsp?msg=Không xóa được");
					rd.forward(request, response);
					return;
				}
				
			}
		}
		ArrayList<Users> listUser = new ArrayList<Users>();
		listUser = userBO.getListUser();
		request.setAttribute("listUser", listUser);
		RequestDispatcher rd = request.getRequestDispatcher("/admin/hieuchinhuser.jsp");
		rd.forward(request, response);
	}

}
