<%@page import="bean.ChuyenNganh"%>
<%@page import="java.util.ArrayList"%>
<%@page import="bean.Khoa"%>
<%@page import="bean.Cap"%>
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
	ChuyenNganh cn = (ChuyenNganh) request.getAttribute("chuyenNganh");
	ArrayList<Khoa> listKhoaChuyenNganh = (ArrayList<Khoa>) request.getAttribute("listKhoaChuyenNganh");
%>
	<div class="suaCap">
		<form action="<%=request.getContextPath()%>/admin/setting?chose=chuyennganh&load=sua&act=dasua&idSua=<%=cn.getIdChuyenNganh() %>" method="post">
			<label>Chuyên ngành thuộc khoa:</label>
			<select name="idKhoa">
				<%
					for(Khoa eachKhoa: listKhoaChuyenNganh){
				%>
				<option value="<%= eachKhoa.getIdKhoa()%>" <%if(eachKhoa.getIdKhoa()==cn.getIdKhoa()) out.print("selected"); %>><%=eachKhoa.getTenKhoa() %></option>
				<% }%>
			</select>
			<br />
			<br />
			<label>Tên chuyên ngành:</label>
			<input type="text" name="tenSua" value="<%=cn.getTenChuyenNganh() %>" required />
			<br />
			<br />
			<input type="submit" name="submit" value="Sửa chuyên ngành"/>
		</form>
	</div>
</body>
</html>