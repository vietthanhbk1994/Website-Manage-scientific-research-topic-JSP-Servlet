<%@page import="bean.LuotDangKy"%>
<%@page import="bean.Cap"%>
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
		ArrayList<LuotDangKy> listLuotDangKy = (ArrayList<LuotDangKy>)request.getAttribute("listLuotDangKy");
		int stt=0;
	%>
	<a href="<%=request.getContextPath()%>/admin/khoa-dang-ky?load=tao" title="" class="themthongbao">Tạo đợt đăng ký</a>
	<form action="<%=request.getContextPath()%>/admin/khoa-dang-ky" method="get">
		<table class="table table-hover">
			<tr>
				<th>STT</th>
				<th>Tên cấp đăng ký</th>
				<th>Thời gian mở</th>
				<th>Thời gian đóng</th>
				<th>Hiệu chỉnh</th>
				<th>
					<input type="submit" name="submit" value="Xóa"/>
					<br/>
					<input type="checkbox" name="xoatatca" value="0" id="selecctall"/>Xóa tất cả
				</th>
			</tr>
			<%
				for(LuotDangKy eachLuotDangKy:listLuotDangKy){
				stt++;
			%>
			<tr>
				<td>
					<%= stt %>	
				</td>
				<td>
					<%=eachLuotDangKy.getTenCap() %>	
				</td>
				<td>
					<%=eachLuotDangKy.getTimeOpen() %>	
				</td>
				<td>
					<%=eachLuotDangKy.getTimeClose() %>	
				</td>
				<td>
					<a href="<%=request.getContextPath()%>/admin/khoa-dang-ky?load=sua&id=<%=eachLuotDangKy.getIdLuotDangKy() %>" title="" class="themthongbao">Sửa</a>
				</td>
				<td>
					<input type="checkbox" class="checkbox1" name="xoaluotdangky" value="<%=eachLuotDangKy.getIdLuotDangKy()%>" />
				</td>
			</tr>
			<%} %>
		</table>
		</form>
		<%@include file="../templates/admin/inc/footerTruong.jsp"%>
</body>
</html>
