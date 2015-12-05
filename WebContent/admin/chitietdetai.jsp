<%@page import="bean.DeTai"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../templates/admin/inc/headerTruong.jsp"%>
<style>
.chitiet {
	border: 1px solid;
	width: 800px;
	padding: 10px;
	margin: 40px auto;
}

.chitiet h3 {
	margin: 16px auto;
	width: 450px;
	font-size: 20px;
	font-weight: bold;
	text-transform: uppercase;
}

.chitiet h5 {
	margin-bottom: 5px;
	font-weight: bold;
}

.main-chitiet {
	width: 500px;
	margin-left: 35px;
}

.main-chitiet h6 {
	margin-top: 10px;
	font-weight: bold;
}
</style>

<%
DeTai detai = (DeTai)request.getAttribute("chitiet");
String ten = "";
String linhvuc="";
String muctieu="";
String noidung="";
String sanphamkhoahoc ="";
String sanphamungdung ="";
String sanphamdaotao ="";
String ketquadukien ="";
String hieuquadukien ="";
int kinhphi = 0;
int thoigian = 0;
String hoten ="";
String diachi  ="";
String email ="";
String dienthoai ="";
String capthiet = "";
int idDeTai = 0;
int kiemduyet = 0;
if(detai!=null){
	kiemduyet = detai.getKiemduyet();
	idDeTai = detai.getIdDeTai();
	ten = detai.getTenDeTai();
	linhvuc = detai.getTenLinhVuc();
	capthiet = detai.getTinhcapthiet();
	muctieu = detai.getMuctieu();
	noidung = detai.getNoidung();
	sanphamkhoahoc = detai.getSanphamkhoahoc();
	sanphamungdung = detai.getSanphamungdung();
	sanphamdaotao = detai.getSanphamdaotao();
	ketquadukien = detai.getKetquadukien();
	hieuquadukien = detai.getHieuquadukien();
	kinhphi = detai.getKinhphidukien();
	thoigian = detai.getThoigiandukien();
	hoten = detai.getUsers().getFullname();
	diachi  = detai.getUsers().getAddress();
	email = detai.getUsers().getEmail();
	dienthoai = detai.getUsers().getDienthoai();
}
%>
<div class="chitiet">
	<h3>Chi tiết đề tài nghiên cứu khoa học 19</h3>
	<div class="main-chitiet">
		<h5>1.Tên đề tài:<%=ten %></h5>
		<h5>2.Lĩnh vực nghiên cứu:<%=linhvuc %></h5>
		<h5>3.Tính cấp thiết:<%=capthiet %></h5>
		<h5>4.Mục tiêu:<%=muctieu %></h5>
		<h5>5.Nội dung chính:<%=noidung %></h5>
		<h5>6.Sản phẩm dự kiến:</h5>
		<h5>6.1.Sản phẩm khoa học:<%=sanphamkhoahoc %></h5>
		<h5>6.2.Sản phẩm đào tạo:<%=sanphamdaotao %></h5>
		<h5>6.3.Sản phẩm ứng dụng:<%=sanphamungdung %></h5>
		<h5>6.4.Kết quả dự kiến:<%=ketquadukien %></h5>
		<h5>7.Hiệu quả dự kiến:<%=hieuquadukien %></h5>
		<h5>8.Nhu cầu kinh phí dự kiến:<%=kinhphi %></h5>
		<h5>9.Thời gian nghiên cứu dự kiến:<%=thoigian %></h5>
		<h5>10.Thông tin liên lạc của người đề xuất:</h5>
		<h6>Họ và tên:<%=hoten %></h6>
		<h6>Địa chỉ:<%=diachi %></h6>
		<h6>Email:<%=email %></h6>
		<h6>Điện thoại:<%=dienthoai %></h6>
	</div>
	<form action = "kiemduyet?detai=<%=idDeTai%>" method = "post" >
		<%
		if(kiemduyet==1){
		%>	
				Thông qua: <input type = "radio" name = "kiemduyet" value = "2" /><br />
				Không thông qua: <input type = "radio" name = "kiemduyet" value = "3" />
				<input type = "submit" name = "submit" value = "KIỂM DUYỆT" />
		<%}else if(detai.getKiemduyet()==2){
			out.println("<h3 style='color:red;'>Đề tài đã được thông qua</h3>");
		}
		else if(detai.getKiemduyet()==3){
			out.println("<h3 style = 'color:red;'>Đề tài đã không được thông qua</h3>");
		}
		%>
	</form>
</div>
<%@include file="../templates/admin/inc/footerTruong.jsp"%>