<%@page import="bean.DeTai"%>
<%@page import="bean.Linhvuc"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="templates/public/inc/header.jsp"%>
<style>
label{
	display:block;
	margin-top:10px;
}
</style>
<div class="frm-dangky">
	<%
		int idCap = Integer.parseInt(request.getParameter("cap"));
		String msg = request.getParameter("msg");
		if (msg != null) {
			out.println("<h4 style = 'color:red'>" + msg + "</h4>");
		}
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
		DeTai detai = (DeTai) request.getAttribute("TamThoi");
		if (detai != null) {
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
		}
	%>
	<h2 class="tieudedk">ĐĂNG KÝ ĐỀ TÀI NGHIÊN CỨU KHOA HỌC</h2>
	<div class="frm-dktruong">
		<form name="frm-dktruong" method="post" action="thuc-hien-dang-ky?cap=<%=idCap%>">
			<div class="tendetai">
				<label class="">1. Tên đề tài:</label> <input type="text" name="tendetai" value="<%if(tendetai!=null){out.println(tendetai);}%>" required="required"/>
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
					value="<%=idLinhvuc%>" <%=checked %> required="required"/>
				<%
					}
					}
				%>
			</div>
			<div class="tinhcapthiet">
				<label class="">3. Tính cấp thiết:</label>
				<textarea name="tinhcapthiet" required="required"><%if(tinhcapthiet!=null){out.println(tinhcapthiet);}%></textarea>
			</div>
			<div class="muctieu">
				<label class="">4. Mục tiêu:</label>
				<textarea name="muctieu" required="required"><%if(muctieu!=null){out.println(muctieu);}%></textarea>
			</div>
			<div class="ndchinh">
				<label class="">5. Nội dung chính:</label>
				<textarea name="ndchinh" required="required"><%if(ndchinh!=null){out.println(ndchinh);}%></textarea>
			</div>
			<div class="spvakq">
				<label class="tentd">6. Sản phẩm và kết quả dự kiến:</label>
				<div class="ndspvakq">
					<div class="ketqua">
						<label class="">6.1 Kết quả dự kiến:</label>
						<textarea name="ketqua" required="required"><%if(ketqua!=null){out.println(ketqua);}%></textarea>
					</div>
					<div class="sanpham">
						<label class="">6.2 Sản phẩm</label>
						<ul class="ndsanpham">
							<li class="spkhoahoc"><label class="tentd2">Sản phẩm khoa học:</label> 
								<textarea name="spkhoahoc" placeholder="Số bài báo khoa học đăng trên tạp chí nước ngoài, trong nước, báo cáo khoa học" required="required"><%if(spkhoahoc!=null){out.println(spkhoahoc);}%></textarea>
							</li>
							<li class="spdaotao"><label class="tentd2">Sản phẩm đào tạo:</label> <textarea name="spdaotao" placeholder="Số lượng cao học, số lượng sinh viên tham gia" required="required"><%if(spdaotao!=null){out.println(spdaotao);} %></textarea>
							</li>
							<li class="spungdung">
								<label class="tentd2">Sản phẩm ứng dụng:</label> 
								<textarea name="spungdung" placeholder="Mô tả tóm tắt về sản phẩm dự kiến, phạm vi, khả năng và địa chỉ ứng dụng,..." required="required"><%if(spungdung!=null){out.println(spungdung);}%></textarea>
							</li>
						</ul>
					</div>
				</div>
			</div>
			<div class="hieuquadk">
				<label>7. Hiệu quả dự kiến:</label>
				<textarea name="hieuquadk" required="required"><%if(hieuquadk!=null){out.println(hieuquadk);}%></textarea>
			</div>
			<div class="yeucaukinhphi">
				<label>8. Nhu cầu kinh phí dự kiến:</label>
				<input type="number" name="yeucaukinhphi" value = "<%=yeucaukinhphi%>" required="required" style="width:140px" /> VNĐ
			</div>
			<div class="thoigian">
				<label>9. Thời gian nghiên cứu dự kiến:</label> 
				<input type="number" name="thoigian" value = "<%= thoigian%>" required="required"  style="width:60px" /> Ngày
			</div>
			<label class="tentd2">10. Danh sách nghiên cứu sinh</label>
			<label>Số lượng nghiên cứu sinh:</label> 
				<input type="number" name="slntv" value="<%=slntv%>" required="required" style="width:50px"/> Người 
				<br />
				<label>Nhập lần lượt số thẻ từng nghiên cứu sinh bao gồm người đăng ký:</label>
				<textarea name="danhsachtv" placeholder="102120252,102120258..." required="required"><%if(danhsachtv!=null){out.println(danhsachtv);}%></textarea>
			<div class="tuychon">
				<input type="submit" name="luulai" value="Lưu lại" />
				<input type="reset" name="nhaplai" value="Nhập lại" />
				<input type="submit" name="dangky" value="Đăng ký" />
			</div>
		</form>
	</div>
</div>
<%@include file="templates/public/inc/footer.jsp"%>