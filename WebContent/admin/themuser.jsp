<%@page import="bean.ChuyenNganh"%>
<%@page import="bean.Khoa"%>
<%@page import="java.util.ArrayList"%>
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
		String msg = request.getParameter("msg");
		if(msg!=null){
			out.print("<span style='color:red;'>"+msg+"</span><br />");
		}
	%>
	<form action="<%=request.getContextPath()%>/admin/hieu-chinh-user?load=them&act=dathem" method="post" class="themthongbao">
		
		<label>Tên đầy đủ: </label>
		<input type="text" name="fullname" value="" class="textbig" required />
								
		<label>Mã số thẻ: </label>
		<input type="number" name="soThe" value="" class="textbig" required />
		
		<label>Mật khẩu: </label>
		<input type="text" name="password" value="123456" class="textbig" required />
		
		<label>Địa chỉ: </label>
		<input type="text" name="address" value="" class="textbig" placeholder="Số nhà đường/Tổ - Xã/Phường/Trị trấn - Huyện/Quận - Tỉnh/Thành phố" required />
		<label>Số điện thoại: </label>
		<input type="text" name="dienthoai" value="" class="textbig" required />
		<label>Email: </label>
		<input type="email" name="email" value="" class="textbig" required />
		
		<label>Chuyên ngành-Khoa:</label>
		
		<div class="khoa">
			<select name="chuyennganh">
			<%
				ArrayList<Users> listKhoa = (ArrayList<Users>) request.getAttribute("listKhoa");
				ArrayList<Khoa> listKhoaChuyenNganh = (ArrayList<Khoa>) request.getAttribute("listKhoaChuyenNganh");
			
				for(Khoa eachKhoa: listKhoaChuyenNganh){	 
			 %>
				 	<optgroup label="<%= eachKhoa.getTenKhoa()%>">
					 <%
					 	for(ChuyenNganh eachChuyenNganh : eachKhoa.getListChuyenNganh()){
					 %>
			 				<option value="<%=eachKhoa.getIdKhoa()%> <%=eachChuyenNganh.getIdChuyenNganh()%>"><%= eachChuyenNganh.getTenChuyenNganh() %></option>
			        
			 			<%}%>
			    	</optgroup>
			    <%} %>
			</select>
		</div>
						
		<label>Phân quyền:</label>
		<div class="role">
			<select name="role">
			  <option value="0" selected>Người đăng ký</option>
			  <option value="1">Quản trị khoa</option>
			</select>
		</div>
		<div class="tuychon">
			<input type="submit" name="submit" value="Thêm User"/>
		</div>
	</form>
	<%@include file="../templates/admin/inc/footerTruong.jsp"%>
</body>
</html>