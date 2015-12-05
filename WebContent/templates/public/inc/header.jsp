<%@page import="bean.Cap"%>
<%@page import="java.util.ArrayList"%>
<%@page import="bean.Users"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link
	href="<%=request.getContextPath()%>/templates/public/css/style.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/library/jquery-2.1.1.min.js"></script>
<title>Hệ thống đăng ký đề tài nghiên cứu khoa học</title>
</head>
<body>
	<div class="wrapper">
		<div class="container">
			<div class="header">
				<div class="banner">
					<h1>HỆ THỐNG ĐĂNG KÝ ĐỀ TÀI NGHIÊN CỨU KHOA HỌC - ĐH BÁCH KHOA
						ĐÀ NẴNG</h1>
					<div class="login right">
						<%
							Users users = (Users) session.getAttribute("users");
							if (users == null) {
						%>
						<form method="post" action="login" id="mh0">
							<div class="user">
								<label>Số thẻ: </label> <input type="text" class="lg0"
									name="username" id="u0" />
							</div>
							<div class="pass">
								<label>Mật khẩu: </label> <input type="password" class="lg1"
									name="password" id="p0" />
							</div>
							<input type="submit" class="lg2" name="dangnhap" id="s0"
								value="Đăng nhập" />
						</form>
						<%
							} else {
						%>
						<a href="<%=request.getContextPath()%>/thong-tin-ca-nhan"><span
							style="color: white;"><%=users.getFullname()%></span> <a
							class="dangxuat" href="<%=request.getContextPath()%>/dang-xuat">Logout</a>

							<%
								}
							%>
					</div>
					<div class="clear"></div>
				</div>
				<%
					if (users != null && users.getRole() == 0) {
				%>
				<div class="menundk">
					<ul>
						<!-- <li><a href="<%=request.getContextPath()%>/login" title="" target="_self">Thông báo</a></li> -->
						<%
						ArrayList<Cap> ListCap = (ArrayList<Cap>) request
						.getAttribute("ListCap");
						if (ListCap != null) {
						%>
						<li class="capdk"><span>Đăng ký đề tài</span>
							<ul class="dkdt">
								<%
									for (Cap eachCap : ListCap) {
								%>
								<li><a
									href="<%=request.getContextPath()%>/dang-ky?cap=<%=eachCap.getIdCap()%>"
									title="" target="_self"> <%=eachCap.getTenCap()%>
								</a></li>
								<%
									}
								%>
							</ul></li>
					</ul>
					<div class="clear"></div>
				</div>
				<%
					}
					}
				%>
			</div>
			<div class="main-body">