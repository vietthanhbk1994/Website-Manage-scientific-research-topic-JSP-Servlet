<%@page import="bean.ChuyenNganh"%>
<%@page import="bean.Khoa"%>
<%@page import="java.util.ArrayList"%>
<%@page import="bean.Users"%>
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
	<%
		Users userSua = (Users) request.getAttribute("NoiDungSua");
		int idUser = userSua.getIdUser();
		int role = userSua.getRole();
		String fullname = userSua.getFullname();
		String soThe = userSua.getSoThe();
		String password = userSua.getPassword();
		String address = userSua.getAddress();
		String dienThoai = userSua.getDienthoai();
		String email = userSua.getEmail();
		int idKhoa = userSua.getIdKhoa();
		int idChuyenNganh = userSua.getIdChuyenNganh();
		
		String idKhoaNganh = idKhoa+"."+idChuyenNganh; 
	%>
	
	<form action="<%=request.getContextPath()%>/admin/hieu-chinh-user?load=sua&act=dasua&id=<%=idUser%>" method="post" class="themthongbao">
	
		<label>Tên đầy đủ: </label>
		<input type="text" name="fullname" value="<%=fullname%>" class="textbig" required />
								
		<label>Mã số thẻ: </label>
		<input type="number" name="soThe" value="<%=soThe %>" class="textbig" required />
		
		<label>Mật khẩu: </label>
		<input type="text" name="password" value="<%=password %>" class="textbig" required />
		
		<label>Địa chỉ: </label>
		<input type="text" name="address" value="<%=address %>" class="textbig" placeholder="Số nhà đường/Tổ - Xã/Phường/Trị trấn - Huyện/Quận - Tỉnh/Thành phố" required />
		<label>Số điện thoại: </label>
		<input type="text" name="dienthoai" value="<%=dienThoai %>" class="textbig" required />
		<label>Email: </label>
		<input type="email" name="email" value="<%=email %>" class="textbig" required />
		
		<label>Chuyên ngành-Khoa:</label>
		
		<div class="khoa">
			<select name="chuyennganh">
			<%
				
				ArrayList<Khoa> listKhoaChuyenNganh = (ArrayList<Khoa>) request.getAttribute("listKhoaChuyenNganh");
				for(Khoa eachKhoa: listKhoaChuyenNganh){	 
			 %>
			 	<optgroup label="<%= eachKhoa.getTenKhoa()%>">
				 <%
				 	for(ChuyenNganh eachChuyenNganh : eachKhoa.getListChuyenNganh()){
				 		String hienThi="";
				 		if(eachKhoa.getIdKhoa()== idKhoa && eachChuyenNganh.getIdChuyenNganh() == idChuyenNganh){
				 			hienThi = "selected";
				 		}
				 %>
					 <option value="<%=eachKhoa.getIdKhoa()%> <%=eachChuyenNganh.getIdChuyenNganh()%>" <%= hienThi%>><%= eachChuyenNganh.getTenChuyenNganh() %></option>
		 			<%}%>
		    	</optgroup>
		    <%} %>
			</select>
		</div>
						
		<label>Phân quyền:</label>
		<div class="role">
			<%
				String nguoiDangKy = null,quanTriKhoa = null;
				if(role==0) nguoiDangKy = "selected";
				else quanTriKhoa = "selected";// !=0
			%>
			<select name="role">
			  <option value="0" <%=nguoiDangKy %>>Người đăng ký</option>
			  <option value="1" <%=quanTriKhoa %>>Quản trị khoa</option>
			</select>
		</div>
		<div class="tuychon">
			<input type="submit" name="submit" value="Thêm User"/>
		</div>
	</form>
	<%@include file="../templates/admin/inc/footerTruong.jsp"%>
</body>
</html>