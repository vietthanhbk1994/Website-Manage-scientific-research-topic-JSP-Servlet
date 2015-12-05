<%@page import="com.sun.jndi.cosnaming.IiopUrl.Address"%>
<%@page import="bean.DeTai"%>
<%@page import="bean.Linhvuc"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="templates/public/inc/header.jsp"%>
<div class="frm-dangky">
	<%
		Users userslogin = (Users)session.getAttribute("users");
		String msg = request.getParameter("msg");
		if (msg != null) {
			out.println("<h4 style = 'color:red'>" + msg + "</h4>");
		}
		int idCap = 0;
		String tendetai = null;
		int idLinhVucc = 0;
		String tinhcapthiet=null;
		String muctieu = null;
		String ndchinh = null;
		String ketqua =null;
		String spkhoahoc = null;
		String spdaotao = null;
		String spungdung = null;
		String hieuquadk = null;
		int yeucaukinhphi = 0;
		int thoigian = 0;
		String danhsachtv = null;
		int slntv = 0;
		String nguoidangky = null;
		String soThe = null;
		String dienthoai = null;
		String email = null;
		String diachi = null;
		int xacnhandangky=0;
		int kiemduyet = 0;
		int idDeTai = 0;
		int idNguoiDK = 0;
		DeTai detai = (DeTai) request.getAttribute("DeTaiChiTiet");
		//DeTai detaitamthoi = (DeTai) request.getAttribute("TamThoi");
		
		if (detai != null) {
			idDeTai = detai.getIdDeTai();
			idCap = detai.getIdCap();
			tendetai = detai.getTenDeTai();
			idLinhVucc = detai.getIdLinhVuc();
			tinhcapthiet = detai.getTinhcapthiet();
			muctieu = detai.getMuctieu();
			ndchinh = detai.getNoidung();
			ketqua = detai.getKetquadukien();
			spkhoahoc = detai.getSanphamkhoahoc();
			spdaotao = detai.getSanphamdaotao();
			spungdung = detai.getSanphamungdung();
			hieuquadk = detai.getHieuquadukien();
			yeucaukinhphi = detai.getKinhphidukien();
			thoigian = detai.getThoigiandukien();
			slntv = detai.getSoluongtv();
			danhsachtv = detai.getDanhsachtv();
			//lay thong tin nguoi dang ký
			Users usersDK = detai.getUsers();
			idNguoiDK =  usersDK.getIdUser();
			nguoidangky = usersDK.getFullname();
			diachi = usersDK.getAddress();
			dienthoai = usersDK.getDienthoai();
			email = usersDK.getEmail();
			soThe = usersDK.getSoThe();
			xacnhandangky = detai.getXacnhandangky();
			kiemduyet = detai.getKiemduyet();
			request.setAttribute("usersDK", usersDK);
		}
	%>
	<h2 class="tieudedk">CHI TIẾT ĐỀ TÀI</h2>
	<div class="frm-dktruong">
		<form name="frm-dktruong" method="post" action="cap-nhat?cap=<%=idCap%>&&detai=<%=idDeTai%>">
			<div class="tendetai">
				<label class="">1. Tên đề tài:</label> <input type="text"
					name="tendetai" value="<%if(tendetai!=null){out.println(tendetai);}%>" />
			</div>
			<label class="">2. Lĩnh vực nghiên cứu:</label>
			<div class="linhvuc">

				<%
					
					ArrayList<Linhvuc> ListLinhVuc = (ArrayList<Linhvuc>) request
							.getAttribute("ListLinhVuc");
					if (ListLinhVuc != null) {
						for (Linhvuc eachLV : ListLinhVuc) {
							int idLinhvuc = eachLV.getIdLinhvuc();
							String checked = "";
							String tenLinhvuc = eachLV.getTenLinhVuc();
							if(idLinhVucc!=0){
								if(idLinhVucc == idLinhvuc){
									checked = "checked";
								}
							}
				%>
				<label><%=tenLinhvuc%></label> <input type="radio" name="linhvuc"
					value="<%=idLinhvuc%>" <%=checked %> />
				<%
					}
					}
				%>
			</div>
			<div class="tinhcapthiet">
				<label class="">3. Tính cấp thiết:</label>

				<textarea name="tinhcapthiet"><%if(tinhcapthiet!=null){out.println(tinhcapthiet);}%></textarea>
			</div>
			<div class="muctieu">

				<label class="">4. Mục tiêu:</label>

				<textarea name="muctieu"><%if(muctieu!=null){out.println(muctieu);}%></textarea>
			</div>
			<div class="ndchinh">

				<label class="">5. Nội dung chính:</label>

				<textarea name="ndchinh"><%if(ndchinh!=null){out.println(ndchinh);}%></textarea>
			</div>
			<div class="spvakq">

				<label class="tentd">6. Sản phẩm và kết quả dự kiến:</label>

				<div class="ndspvakq">
					<div class="ketqua">
						<label class="">6.1 Kết quả dự kiến:</label>

						<textarea name="ketqua"><%if(ketqua!=null){out.println(ketqua);} %></textarea>
					</div>
					<div class="sanpham">
						<label class="">6.2 Sản phẩm</label>
						<ul class="ndsanpham">
							<li class="spkhoahoc"><label class="tentd2">Sản phẩm
									khoa học:</label> 
									<textarea name="spkhoahoc" placeholder="Số bài báo khoa học đăng trên tạp chí nước ngoài, trong nước, báo cáo khoa học"><%if(spkhoahoc!=null){out.println(spkhoahoc);}%></textarea>
							</li>

							<li class="spdaotao"><label class="tentd2">Sản phẩm
									đào tạo:</label> <textarea name="spdaotao"
									placeholder="Số lượng cao học, số lượng sinh viên tham gia"><%if(spdaotao!=null){out.println(spdaotao);} %></textarea>
							</li>
							<li class="spungdung"><label class="tentd2">Sản phẩm
									ứng dụng:</label> 
									<textarea name="spungdung" placeholder="Mô tả tóm tắt về sản phẩm dự kiến, phạm vi, khả năng và địa chỉ ứng dụng,..."><%if(spungdung!=null){out.println(spungdung);}%></textarea>
							</li>
						</ul>
					</div>
				</div>
			</div>
			<div class="hieuquadk">
				<label>7. Hiệu quả dự kiến:</label>

				<textarea name="hieuquadk"><%if(hieuquadk!=null){out.println(hieuquadk);}%></textarea>
			</div>
			<div class="yeucaukinhphi">
				<label>8. Nhu cầu kinh phí dự kiến:</label> <input type="text"
					name="yeucaukinhphi" value = "<%if(yeucaukinhphi!=0){out.println(yeucaukinhphi);}%>" /> VNĐ
			</div>
			<div class="thoigian">
				<label>9. Thời gian nghiên cứu dự kiến:</label> <input type="text"
					name="thoigian" value = "<%if(thoigian!=0){out.println(thoigian);}%>" /> Ngày
			</div>
			<label class="tentd2">10. Danh sách nghiên cứu sinh</label> <label>Số
				lượng nghiên cứu sinh:</label> <input type="text" name="slntv" value="<%if(slntv!=0){out.println(slntv);}%>" />
			Người <label>Nhập lần lượt số thẻ từng nghiên cứu sinh bao
				gồm người đăng ký:</label>
			<textarea name="danhsachtv" placeholder="102120252,102120258..."><%if(danhsachtv!=null){out.println(danhsachtv);}%></textarea>
				<h4>Thông tin người đăng ký</h4>
				<br />
				<p>
					Số thẻ:<h4><%=soThe %></h4><br />
					Họ tên:<h4><%=nguoidangky %></h4><br />
					Địa chỉ:<h4><%=diachi %></h4><br />
					email:<h4><%=email %></h4><br />
					Điện thoai:<h4><%=dienthoai %></h4><br />
				</p>
					
			<div class="tuychon">
				<%
				if(idNguoiDK==userslogin.getIdUser()){
					if(xacnhandangky==0){
					out.println("<h4 style = 'color:red'>Lưu ý:đề tài chưa được xác nhận đăng ký</h4>");
					
				%>
				 <input type="submit" name="luulai" value="Lưu lại">
				 <input type="reset" name="nhaplai" value="Nhập lại">
				 <input type="submit" name="dangky" value="Đăng ky">
				 <%}
				 else if(kiemduyet==2){
					 out.println("<h4 style = 'color:red'>Đề tài đã được phê duyệt</h4>");
				 }else if(kiemduyet==3){
					 out.println("<h4 style = 'color:red'>Đề tài không được phê duyệt</h4>");
				 }else{
					 out.println("<h4 style = 'color:red'>Đề tài đã xác nhận đăng ký</h4>");
				 }
				}
					else{
						out.println("<h4 style = 'color:red'>Bạn không phải là người đăng kýy</h4>");
					}
				 %>
			</div>
		</form>
	</div>
</div>
<%@include file="templates/public/inc/footer.jsp"%>