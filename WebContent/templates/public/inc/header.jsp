<%@page import="bean.Cap"%>
<%@page import="java.util.ArrayList"%>
<%@page import="bean.Users"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="<%=request.getContextPath()%>/templates/public/css/style.css" rel="stylesheet" type="text/css" />

<meta name="viewport" content="width=device-width, initial-scale=1">
<script type="text/javascript" src="<%=request.getContextPath()%>/library/js/jquery-2.1.3.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/library/js/jquery.validate.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/library/ckeditor/ckeditor.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/library/css/bootstrap.min.css">
<script src="<%=request.getContextPath()%>/library/js/bootstrap.min.js"></script>

<title>Hệ thống đăng ký đề tài nghiên cứu khoa học</title>
</head>
<body>
	<div class="wrapper">
		<div class="container">
			<div class="header">
				<div class="banner">
				
					<h4>HỆ THỐNG ĐK đề tài NCKH - TRƯỜNG ĐẠI HỌC BÁCH KHOA - ĐH ĐÀ NẴNG</h4>
					<div class="login right">
						<%
							Users users = (Users) session.getAttribute("users");
							if (users == null) {
						%>
						<form method="post" action="login" id="mh0">
							<div class="user">
								<label>Số thẻ: </label> <input type="text" class="lg0" name="username" id="u0" />
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
						<a href="<%=request.getContextPath()%>/thong-tin-ca-nhan">
							<span style="color: white;"><%=users.getFullname()%></span>
						</a>
						<a href="<%=request.getContextPath()%>/dang-xuat" role="button" class="dangxuat btn btn-danger">Logout</a>

							<%
								}
							%>
					</div>
					<div class="clear"></div>
				</div>
				<%
					if (users != null && users.getRole() == 0) {
				%>
				<ul class="nav nav-pills">
					<li role="presentation" class="">
						<a  href="<%=request.getContextPath()%>/welcome" class="btn btn-info" role="button">Tình trạng đăng ký</a>
					</li>
					<li role="presentation" class="">
						<a  href="<%=request.getContextPath()%>/thong-tin-ca-nhan" class="btn btn-info" role="button">Thông tin cá nhân</a>
					</li>
					<li>
						<div class="dropdown">
							<button class="btn btn-info dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
							    Đăng ký đề tài
								<span class="caret"></span>
							</button>
							<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
							<%
							ArrayList<Cap> ListCap = (ArrayList<Cap>) request.getAttribute("ListCap");
							if (ListCap != null) {
								for (Cap eachCap : ListCap) {
							%>
								<li>
									<a href="<%=request.getContextPath()%>/dang-ky?cap=<%=eachCap.getIdCap()%>"
										title="" target="_self"> <%=eachCap.getTenCap()%>
									</a>
								</li>
							<%}%>
							
							</ul>
						</div>
					</li>
				</ul>
				<%
					}
					}
				%>
			</div>
			<div class="main-body">