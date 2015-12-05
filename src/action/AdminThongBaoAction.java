package action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
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

import org.apache.catalina.User;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import bean.DeTai;
import bean.ThongBao;
import bean.Users;
import bo.DeTaiBO;
import bo.ThongBaoBO;
import bo.UserBO;

/**
 * Servlet implementation class AdminThongBaoAction
 */
//@WebServlet("/AdminThongBaoAction")
public class AdminThongBaoAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminThongBaoAction() {
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
		ThongBaoBO thongBaoBO = new ThongBaoBO();
		if (userLogin == null || userLogin.getRole() !=-1) {
			session.invalidate();
			response.sendRedirect(request.getContextPath() + "/login");
		}
		else{
			//chuc nang them + sua
			
			
			if(request.getParameter("load")!=null){
				//them thong bao
				if(request.getParameter("act")!=null){
					//lay thong tin
					String linkDownload = "";
					String tenThongBao = null;
					String noiDungThongBao = null;
					int cheDo=0;
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
									case "cheDoDang": cheDo = Integer.parseInt(value); break;
								}
							} else { // lĂ  file
								String filename = fileItem.getName();
								// Ä‘á»•i tĂªn file
								if(fileItem != null){
									String ext = FilenameUtils.getExtension(filename);
									if(ext=="") continue;
									
									long time = System.nanoTime();// láº¥y thá»�i gian
									dinhKemFile = "ThongBao-" + time + "." + ext;
									
									//System.out.println("dinhKemFile"+dinhKemFile);

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
									//System.out.println("linkdownlaod"+linkDownload);
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
			        String ngayDangTin = dinhDangThoiGian.format(thoiGian.getTime());
			        ThongBao thongBao = new ThongBao();
			        thongBao.setTenThongBao(tenThongBao);
			        thongBao.setNoiDung(noiDungThongBao);
			        thongBao.setNgayDang(ngayDangTin);
			        thongBao.setCheDo(cheDo);
			        thongBao.setLinkDownload(linkDownload);
					thongBao.setDinhKemFile(dinhKemFile);
					
					//neu la them thong bao
					if(request.getParameter("act").equals("dathem")&&request.getParameter("load").equals("them")){
				        if(thongBaoBO.themThongBao(thongBao)){
				        	ArrayList<ThongBao> listThongBao = new ArrayList<ThongBao>();
							listThongBao = thongBaoBO.getListThongBao();
							request.setAttribute("listThongBao", listThongBao);
				        	
				        	RequestDispatcher rd = request.getRequestDispatcher("hieuchinhthongbao.jsp");
							rd.forward(request, response);
				        	return;
				        }
				        else{
				        	PrintWriter writer = response.getWriter();
				    		write.println("Quá trình thêm dữ liệu vào Database bị lỗi. Vui lòng thử lại");
				        }
					}
					
					//neu la sua thong bao
					if(request.getParameter("act").equals("dasua")&&request.getParameter("load").equals("sua")){
						String id = request.getParameter("id");
						int idThongBao = Integer.parseInt(id);
						thongBao.setIdThongBao(idThongBao);
						
						if(thongBaoBO.checkSuaThongBao(thongBao)){
							ArrayList<ThongBao> listThongBao = new ArrayList<ThongBao>();
							listThongBao = thongBaoBO.getListThongBao();
							request.setAttribute("listThongBao", listThongBao);
							
				        	RequestDispatcher rd = request.getRequestDispatcher("hieuchinhthongbao.jsp");
				        	rd.forward(request, response);
				        	return;
				        }
				        else{
				        	PrintWriter writer = response.getWriter();
				    		write.println("Quá trình thêm dữ liệu vào Database bị lỗi. Vui lòng thử lại");
				        }
					}
				}
				if(request.getParameter("load").equals("them")){
					RequestDispatcher rd = request.getRequestDispatcher("/admin/themthongbao.jsp");
					rd.forward(request, response);
					return;
			     }
				//sua thong bao
				if(request.getParameter("load").equals("sua")){
					String idThongBaoSua = request.getParameter("id");
					if(idThongBaoSua != null){
						int idThongBao = Integer.parseInt(idThongBaoSua);
						ThongBao thongBaoSua = thongBaoBO.getThongBaoSua(idThongBao);
						request.setAttribute("NoiDungSua", thongBaoSua);
						RequestDispatcher rd = request.getRequestDispatcher("/admin/suathongbao.jsp");
						rd.forward(request, response);
						return;
					}
				}
			}
			//xoa thong bao
			if(request.getParameter("submit")!=null){
				String[] listXoaThongBao = request.getParameterValues("xoathongbao");
				if(listXoaThongBao == null) {
					ArrayList<ThongBao> listThongBao = new ArrayList<ThongBao>();
					listThongBao = thongBaoBO.getListThongBao();
					request.setAttribute("listThongBao", listThongBao);
					RequestDispatcher rd = request.getRequestDispatcher("/admin/hieuchinhthongbao.jsp?msg=Chưa chọn thông báo để xóa");
					
					rd.forward(request, response);
					return;
				}
				else{
					if(thongBaoBO.checkXoaThongBao(listXoaThongBao)){
			//			write.println("da xoa");
						RequestDispatcher rd = request.getRequestDispatcher("/admin/hieu-chinh-thong-bao");
						rd.forward(request, response);
						return;
					}
					else{
						write.println("Lỗi. Không thể xóa được thông báo");
						
					}
				}
				
			}
			//hien thong bao len bang
			ArrayList<ThongBao> listThongBao = new ArrayList<ThongBao>();
			listThongBao = thongBaoBO.getListThongBao();
			
			//HttpSession session = request.getSession();
			request.setAttribute("listThongBao", listThongBao);
			
			RequestDispatcher rd = request.getRequestDispatcher("/admin/hieuchinhthongbao.jsp");
			rd.forward(request, response);
			
		}
	}

}
