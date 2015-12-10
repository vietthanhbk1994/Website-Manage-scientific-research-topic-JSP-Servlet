<%@page import="java.util.ArrayList"%>
<%@page import="bean.ThongBao"%>
<%@page import="java.sql.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@include file="../templates/admin/inc/headerTruong.jsp"%>
	
	<a href="<%=request.getContextPath()%>/admin/hieu-chinh-user?load=them" title="" class="themthongbao">Thêm từng User</a>
	<form action="<%=request.getContextPath()%>/admin/hieu-chinh-user?load=themtufile" method="post"  enctype="multipart/form-data">
		<label>Thêm User từ file:</label>
		<input type="file" name="files" id="file">
		
		<input type="submit" value="Thêm User" />
	</form>
	<br />
	<form action="<%=request.getContextPath()%>/admin/hieu-chinh-user" method="get">
	
	<table class="table table-hover">
		<tr>
			<th>STT</th>
			<th>Tên User</th>
			<th>Mã số thẻ</th>
			<th>Quyền</th>
			<th>Địa chỉ</th>
			<th>Số điện thoại</th>
			<th>Email</th>
			<th>Chuyên ngành</th>
			
			
			<th>Khoa</th>
			
			<th>Sửa</th>
			<th>
				<input type="submit" name="submit" value="Xóa"/>
				<br/>
				<input type="checkbox" name="xoatatca" value="0" id="selecctall"/>Xóa tất cả
			</th>
		</tr>
	<%
		ArrayList<Users> listUser = (ArrayList<Users>) request.getAttribute("listUser");
		int stt=0;
		for(Users eachUser : listUser){
			stt++;
			int idUser = eachUser.getIdUser();
			String tenUser = eachUser.getFullname();
			int role = eachUser.getRole();
			String quyen="";
			if(role==-1){
				quyen="Quản trị Trường";
			}
			else if(role==0){
				quyen="Người đăng kí";
			}
			else quyen="Quản trị Khoa";
			
			
			String soThe = eachUser.getSoThe();
			String address = eachUser.getAddress();
			String dienThoai = eachUser.getDienthoai();
			String email = eachUser.getEmail();
			String tenChuyenNganh = eachUser.getTenChuyenNganh();
			String tenKhoa = eachUser.getTenKhoa();
	%>
		<tr>
			<td><%= stt %></td>
			<td><%= tenUser %></td>
			<td><%= soThe %></td>
			<td><%= quyen %></td>
			<td><%= address %></td>
			<td><%= dienThoai %></td>
			<td><%= email %></td>
			<td><%= tenChuyenNganh %></td>
			<td><%= tenKhoa %></td>
			<td>
				<a href="<%=request.getContextPath()%>/admin/hieu-chinh-user?load=sua&id=<%=idUser%>">Sửa</a>
			</td>
			<td>
				<input type="checkbox" class="checkbox1" name="xoaUser" value="<%=idUser%>"/>
			</td>
			
		</tr>
		<%} %>
	</table>
	</form>
	<%@include file="../templates/admin/inc/footerTruong.jsp"%>
</body>
</html>