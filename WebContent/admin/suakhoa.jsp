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
	Khoa khoa = (Khoa) request.getAttribute("khoa");
%>
	<div class="suaCap">
		<form action="<%=request.getContextPath()%>/admin/setting?chose=capdetai&load=sua&act=dasua&idSua=<%=khoa.getIdKhoa() %>" method="post">
			<label>Tên khoa:</label>
			<input type="text" name="tenSua" value="<%=khoa.getTenKhoa() %>" required />
			<input type="submit" name="submit" value="Chỉnh sửa khoa"/>
		</form>
	</div>
</body>
</html>