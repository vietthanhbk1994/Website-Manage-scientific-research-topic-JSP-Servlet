package action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import bean.Cap;
import bean.LuotDangKy;
import bean.ThongBao;
import bean.Users;
import bo.CapBO;
import bo.LuotDangKyBO;
import bo.ThongBaoBO;

/**
 * Servlet implementation class AdminTaoKhoaDangKyAction
 */
//@WebServlet("/AdminTaoKhoaDangKyAction")
public class AdminKhoaDangKyAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminKhoaDangKyAction() {
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
		
		HttpSession session = request.getSession();
		Users userLogin = (Users) session.getAttribute("users");
		if (userLogin == null || userLogin.getRole() !=-1) {
			session.invalidate();
			response.sendRedirect(request.getContextPath() + "/login");
			
		}
		else{
			PrintWriter write = response.getWriter();
			LuotDangKyBO luotDangKyBO = new LuotDangKyBO();
			LuotDangKy luotDangKy = new LuotDangKy();
			
			CapBO capbo = new CapBO();
			ArrayList<Cap> listCap = new ArrayList<Cap>();
			listCap = capbo.getListCap();
			
			ArrayList<LuotDangKy> listLuotDangKy = new ArrayList<LuotDangKy>();
			if(request.getParameter("load")!=null){
				//kiem tra hanh dong tao hoac sua
				if(request.getParameter("act")!=null){
					String timeOpen = "";
					String timeClose = "";
					String timeOpen0 = "";
					String timeClose0 = "";
					String timeOpen1 = "";
					String timeClose1 = "";
					int idCap = 0;
					
					String linkDownload = "";
					String tenThongBao = null;
					String noiDungThongBao = null;
					String ngayDangTin="";
					String dinhKemFile = "";
					//File
					DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
					ServletFileUpload upload = new ServletFileUpload(fileItemFactory);
					try {
						List<FileItem> formitems = upload.parseRequest(request);
						for (FileItem fileItem : formitems) {
							if (fileItem.isFormField()) {// khÄ‚Â¡c file
								String name = fileItem.getFieldName();
								String value = new String(fileItem.getString().getBytes("ISO-8859-1"), "UTF-8");
								//lĂ¡ÂºÂ¥y tĂ¡Â»Â«ng giÄ‚Â¡ trĂ¡Â»â€¹ cĂ¡Â»Â§a form
								switch(name){
									case "tenThongBao": tenThongBao = value; break; 
									case "noiDungThongBao": noiDungThongBao = value; break;
									case "timeOpen1": timeOpen1 = value; break; 
									case "timeClose1": timeClose1 = value; break;
									case "timeOpen0": timeOpen0 = value; break; 
									case "timeClose0": timeClose0 = value; break; 
									case "cap": idCap = Integer.parseInt(value); break;
									
								}
							} else { // lÄ‚Â  file
								String filename = fileItem.getName();
								// Ă„â€˜Ă¡Â»â€¢i tÄ‚Âªn file
								if(fileItem != null){
									String ext = FilenameUtils.getExtension(filename);
									if(ext=="") continue;
									
									long time = System.nanoTime();// lĂ¡ÂºÂ¥y thĂ¡Â»ï¿½i gian
									dinhKemFile = "ThongBao-" + time + "." + ext;
									
									System.out.println("dinhKemFile"+dinhKemFile);

									// tĂ¡ÂºÂ¡o thĂ†Â° mĂ¡Â»Â¥c upload file
									String uploadDir = request.getServletContext().getRealPath("") + java.io.File.separator
											+ "files";
									File dir = new File(uploadDir);
									if (!dir.exists()) {
										dir.mkdirs();
									}
									//out.println(uploadDir);
									// tĂ¡ÂºÂ¡o Ă„â€˜Ă†Â°Ă¡Â»ï¿½ng dĂ¡ÂºÂ«n thĂ¡Â»Â±c Ă„â€˜Ă¡ÂºÂ¿n file trÄ‚Âªn Ă„â€˜Ă„Â©a.
									String RealPathFile = uploadDir + File.separator + dinhKemFile;
									linkDownload = RealPathFile;
									System.out.println("linkdownlaod-tao-dot-dk:"+linkDownload);
									// upload file lÄ‚Âªn Ă¡Â»â€¢ Ă„â€˜Ă„Â©a
									File file = new File(RealPathFile);
									fileItem.write(file);
								} else {
									dinhKemFile = "";
								}
								
								//xÄ‚Â³a file
							}
						}
					} catch (FileUploadException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					//ngay dang
					Date thoiGian = new Date();
			        //Khai bao dinh dang ngay thang
			        SimpleDateFormat dinhDangThoiGian = new SimpleDateFormat("yyyy-MM-dd");
			        //parse ngay thang sang dinh dang va chuyen thanh string.
			        ngayDangTin = dinhDangThoiGian.format(thoiGian.getTime());
			        //chuyen String sang Date
			       
					timeOpen = timeOpen1 + " " + timeOpen0;
					timeClose = timeClose1 + " " + timeClose0;
					
					
					luotDangKy.setIdCap(idCap);
					luotDangKy.setTimeOpen(timeOpen);
					luotDangKy.setTimeClose(timeClose);
					
			        
			        //them thong tin vao doi tuong ThongBao roi update vao DB
			        ThongBao thongBao = new ThongBao();
			        thongBao.setTenThongBao(tenThongBao);
			        
			        DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			        LocalDate dateOpen = LocalDate.parse(timeOpen1, f);
			        
			        luotDangKy.setNam(dateOpen.getYear());
			        
			        LocalDate dateClose = LocalDate.parse(timeClose1, f);
			        timeOpen1 = String.valueOf(dateOpen.getDayOfMonth()) + "/" + String.valueOf(dateOpen.getMonthValue()) +"/" +String.valueOf(dateOpen.getYear()); 
			        timeClose1 = String.valueOf(dateClose.getDayOfMonth()) + "/" + String.valueOf(dateClose.getMonthValue()) +"/" +String.valueOf(dateClose.getYear());
			        noiDungThongBao += " Thời gian đăng ký bắt đầu lúc: "+timeOpen0 +" "+ timeOpen1+" đến "+timeClose0+" "+ timeOpen1;
			        
			        thongBao.setNoiDung(noiDungThongBao);
			        thongBao.setNgayDang(ngayDangTin);
			        thongBao.setLinkDownload(linkDownload);
					thongBao.setDinhKemFile(dinhKemFile);
			        thongBao.setCheDo(1);
					
			        ThongBaoBO thongBaoBO = new ThongBaoBO();
					//tao dot dang ky
					if(request.getParameter("act").equals("datao")&&request.getParameter("load").equals("tao")){
						if(thongBaoBO.themThongBao(thongBao) && luotDangKyBO.suaLuotDK(luotDangKy)){
							listLuotDangKy = luotDangKyBO.getListLuotDangKy();
							request.setAttribute("listLuotDangKy", listLuotDangKy);
							RequestDispatcher rd = request.getRequestDispatcher("/admin/khoadangky.jsp");
							rd.forward(request, response);
				        }
				        else{
				        	PrintWriter writer = response.getWriter();
				    		write.println("QuĂ¡ trĂ¬nh thĂªm dá»¯ liá»‡u vĂ o Database bá»‹ lá»—i. Vui lĂ²ng thá»­ láº¡i");
				        }
						return;
					}
					//sua dot dang ky
					if(request.getParameter("act").equals("dasua")&&request.getParameter("load").equals("sua")){
						int idLDK = Integer.parseInt(request.getParameter("id"));
						luotDangKy.setIdLuotDangKy(idLDK);
						if(thongBaoBO.themThongBao(thongBao) && luotDangKyBO.suaLuotDK(luotDangKy)){
							listLuotDangKy = luotDangKyBO.getListLuotDangKy();
							request.setAttribute("listLuotDangKy", listLuotDangKy);
				        	RequestDispatcher rd = request.getRequestDispatcher("/admin/khoadangky.jsp");
				        	rd.forward(request, response);
				        }
				        else{
				        	PrintWriter writer = response.getWriter();
				    		write.println("QuĂ¡ trĂ¬nh thĂªm dá»¯ liá»‡u vĂ o Database bá»‹ lá»—i. Vui lĂ²ng thá»­ láº¡i");
				        }
						return;
					}
				}
				//chuyen sang trang tao dot dang ky
				if(request.getParameter("load").equals("tao")){
					request.setAttribute("listCap", listCap);
					RequestDispatcher rd = request.getRequestDispatcher("/admin/taodotdangky.jsp");
					rd.forward(request, response);
				}
				//chuyen sang trang sua dot dang ky
				if(request.getParameter("load").equals("sua")){
					int id = Integer.parseInt(request.getParameter("id"));
					luotDangKy = luotDangKyBO.getLuotDangKy(id);
					
					request.setAttribute("listCap", listCap);
					request.setAttribute("luotDangKy", luotDangKy);
					
					RequestDispatcher rd = request.getRequestDispatcher("/admin/suadotdangky.jsp");
					rd.forward(request, response);
				}
			}
			//xoa khoa dang ky
			//xoa thong bao
			if(request.getParameter("submit")!=null){
				String[] listXoaLuotDangKy = request.getParameterValues("xoaluotdangky");
				if(listXoaLuotDangKy == null) {
					listLuotDangKy = luotDangKyBO.getListLuotDangKy();
					request.setAttribute("listLuotDangKy", listLuotDangKy);
		        	RequestDispatcher rd = request.getRequestDispatcher("/admin/khoadangky.jsp?mgs=Chưa có mục nào được chọn để xóa");
		        	rd.forward(request, response);
					return;
				}
				else{
					if(luotDangKyBO.checkXoaLuotDangKy(listXoaLuotDangKy)){
			//			write.println("da xoa");
						listLuotDangKy = luotDangKyBO.getListLuotDangKy();
						request.setAttribute("listLuotDangKy", listLuotDangKy);
			        	RequestDispatcher rd = request.getRequestDispatcher("/admin/khoadangky.jsp");
			        	rd.forward(request, response);
						return;
					}
					else{
						write.println("Không thể xóa. Lỗi");
					}
				}
				
			}
			
			
			listLuotDangKy = luotDangKyBO.getListLuotDangKy();
			request.setAttribute("listLuotDangKy", listLuotDangKy);
        	RequestDispatcher rd = request.getRequestDispatcher("/admin/khoadangky.jsp");
        	rd.forward(request, response);
		}
	}

}
