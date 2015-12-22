<%@page import="bean.Users"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link href="<%=request.getContextPath()%>/templates/public/css/style.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/library/js/jquery-2.1.3.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/library/js/jquery.validate.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/library/ckeditor/ckeditor.js"></script>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/library/css/bootstrap.min.css">
	<script src="<%=request.getContextPath()%>/library/js/bootstrap.min.js"></script>

<title>Hệ thống đăng ký đề tài nghiên cứu khoa học</title>
</head>
<body>
	<div class="wrapper">
		<div class="container" >
			<div class="header notprint" >
				<div class="banner">
					<h4>HỆ THỐNG ĐĂNG KÝ ĐỀ TÀI NGHIÊN CỨU KHOA HỌC - ĐH BÁCH KHOA ĐÀ NẴNG</h4>
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
						<span style="color: white;"><%=users.getFullname()%></span> 
						<a href="<%=request.getContextPath()%>/dang-xuat" role="button" class="dangxuat btn btn-danger">Logout</a>
						<%
							}
						%>
					</div>
					<div class="clear"></div>
				</div>
			</div>
			
				<div class="notprint">
					<ul class="nav nav-pills">
						<li role="presentation"><a href="<%=request.getContextPath()%>/quan-tri-khoa" class="btn btn-info" role="button">Danh sách đề tài Khoa</a></li>
						<li role="presentation"><a href="<%=request.getContextPath()%>/quan-tri-khoa" class="btn btn-info" role="button">Danh sách thành viên Khoa</a></li>
					</ul>
				</div>
			<div class="main-body">