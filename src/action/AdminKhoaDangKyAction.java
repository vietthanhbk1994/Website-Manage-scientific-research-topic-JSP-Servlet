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

import bean.Cap;
import bean.ThongBao;
import bean.Users;
import bo.CapBO;
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
			CapBO capbo = new CapBO();
			Cap cap = new Cap();
			ArrayList<Cap> listCapDeTai = new ArrayList<Cap>();
			if(request.getParameter("load")!=null){
				//kiem tra hanh dong tao hoac sua
				if(request.getParameter("act")!=null){
					String timeOpen = "";
					String timeClose = "";
					String timeOpen0 = "";
					String timeClose0 = "";
					String timeOpen1 = "";
					String timeClose1 = "";
					String tenCap = "";
					
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
							if (fileItem.isFormField()) {// khĂ¡c file
								String name = fileItem.getFieldName();
								String value = new String(fileItem.getString().getBytes("ISO-8859-1"), "UTF-8");
								//láº¥y tá»«ng giĂ¡ trá»‹ cá»§a form
								switch(name){
									case "tenThongBao": tenThongBao = value; break; 
									case "noiDungThongBao": noiDungThongBao = value; break;
									case "timeOpen1": timeOpen1 = value; break; 
									case "timeClose1": timeClose1 = value; break;
									case "timeOpen0": timeOpen0 = value; break; 
									case "timeClose0": timeClose0 = value; break; 
									case "cap": tenCap= value; break;
									
								}
							} else { // lĂ  file
								String filename = fileItem.getName();
								// Ä‘á»•i tĂªn file
								if(fileItem != null){
									String ext = FilenameUtils.getExtension(filename);
									if(ext=="") continue;
									
									long time = System.nanoTime();// láº¥y thá»�i gian
									dinhKemFile = "ThongBao-" + time + "." + ext;
									
									System.out.println("dinhKemFile"+dinhKemFile);

									// táº¡o thÆ° má»¥c upload file
									String uploadDir = request.getServletContext().getRealPath("") + java.io.File.separator
											+ "files";
									File dir = new File(uploadDir);
									if (!dir.exists()) {
										dir.mkdirs();
									}
									//out.println(uploadDir);
									// táº¡o Ä‘Æ°á»�ng dáº«n thá»±c Ä‘áº¿n file trĂªn Ä‘Ä©a.
									String RealPathFile = uploadDir + File.separator + dinhKemFile;
									linkDownload = RealPathFile;
									System.out.println("linkdownlaod-tao-dot-dk:"+linkDownload);
									// upload file lĂªn á»• Ä‘Ä©a
									File file = new File(RealPathFile);
									fileItem.write(file);
								} else {
									dinhKemFile = "";
								}
								
								//xĂ³a file
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
					
					CapBO capBO = new CapBO();
					timeOpen = timeOpen1 + " " + timeOpen0;
					timeClose = timeClose1 + " " + timeClose0;
					
					Cap capDK = new Cap();
					capDK.setTenCap(tenCap);
					capDK.setTimeOpen(timeOpen);
					capDK.setTimeClose(timeClose);
					
			        
			        //them thong tin vao doi tuong ThongBao roi update vao DB
			        ThongBao thongBao = new ThongBao();
			        thongBao.setTenThongBao(tenThongBao);
			        
			        DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			        LocalDate dateOpen = LocalDate.parse(timeOpen1, f);
			        
			        capDK.setNam(dateOpen.getYear());
			        
			        LocalDate dateClose = LocalDate.parse(timeClose1, f);
			        timeOpen1 = String.valueOf(dateOpen.getDayOfMonth()) + "/" + String.valueOf(dateOpen.getMonthValue()) +"/" +String.valueOf(dateOpen.getYear()); 
			        timeClose1 = String.valueOf(dateClose.getDayOfMonth()) + "/" + String.valueOf(dateClose.getMonthValue()) +"/" +String.valueOf(dateClose.getYear());
			        noiDungThongBao += " Thời gian đăng ký: Từ "+timeOpen0 +" "+ timeOpen1+" đến "+timeClose0+" "+ timeOpen1;
			        
			        thongBao.setNoiDung(noiDungThongBao);
			        thongBao.setNgayDang(ngayDangTin);
			        thongBao.setLinkDownload(linkDownload);
					thongBao.setDinhKemFile(dinhKemFile);
			        thongBao.setCheDo(1);
					
			        ThongBaoBO thongBaoBO = new ThongBaoBO();
					//tao dot dang ky
					if(request.getParameter("act").equals("datao")&&request.getParameter("load").equals("tao")){
						if(thongBaoBO.themThongBao(thongBao) && capBO.taoDotDK(capDK)){
							listCapDeTai = capbo.getCapDeTai();
							request.setAttribute("listCapDeTai", listCapDeTai);
							RequestDispatcher rd = request.getRequestDispatcher("/admin/khoadangky.jsp");
							rd.forward(request, response);
				        }
				        else{
				        	PrintWriter writer = response.getWriter();
				    		write.println("Quá trình thêm dữ liệu vào Database bị lỗi. Vui lòng thử lại");
				        }
						return;
					}
					//sua dot dang ky
					if(request.getParameter("act").equals("dasua")&&request.getParameter("load").equals("sua")){
						int idCap = Integer.parseInt(request.getParameter("id"));
						capDK.setIdCap(idCap);
						if(thongBaoBO.themThongBao(thongBao) && capBO.suaDotDK(capDK)){
							listCapDeTai = capbo.getCapDeTai();
							request.setAttribute("listCapDeTai", listCapDeTai);
				        	RequestDispatcher rd = request.getRequestDispatcher("/admin/khoadangky.jsp");
				        	rd.forward(request, response);
				        }
				        else{
				        	PrintWriter writer = response.getWriter();
				    		write.println("Quá trình thêm dữ liệu vào Database bị lỗi. Vui lòng thử lại");
				        }
						return;
					}
				}
				//chuyen sang trang tao dot dang ky
				if(request.getParameter("load").equals("tao")){
					RequestDispatcher rd = request.getRequestDispatcher("/admin/taodotdangky.jsp");
					rd.forward(request, response);
				}
				//chuyen sang tran sua dot dang ky
				if(request.getParameter("load").equals("sua")){
					int id = Integer.parseInt(request.getParameter("id"));
					cap = capbo.getCapDeTai(id);
					request.setAttribute("cap", cap);
					
					RequestDispatcher rd = request.getRequestDispatcher("/admin/suadotdangky.jsp");
					rd.forward(request, response);
				}
			}
			listCapDeTai = capbo.getCapDeTai();
			request.setAttribute("listCapDeTai", listCapDeTai);
			RequestDispatcher rd = request.getRequestDispatcher("/admin/khoadangky.jsp");
			rd.forward(request, response);
		}
	}

}
