<%@page import="bean.Users"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link
	href="<%=request.getContextPath()%>/templates/public/css/style.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../library/jquery-2.1.1.min.js"></script>
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
						<span style="color: white;"><%=users.getFullname()%></span> <a
							href="<%=request.getContextPath()%>/dang-xuat">Logout</a>
						<%
							}
						%>
					</div>
					<div class="clear"></div>
				</div>
			</div>
			<div class="main-body">
				<h2>
					QUẢN TRỊ
					<%
					out.print(users.getTenKhoa());
				%>:
				</h2>
				<div class="thongbao">
					<div class="menu-truong">
						<ul>
							<li><a
								href="<%=request.getContextPath()%>/khoa-danh-sach?khoa=<%=users.getIdKhoa()%>">Xem
									danh sách đề tài</a></li>
							<li><a href="">Xem danh sách thành viên</a></li>
						</ul>
					</div>