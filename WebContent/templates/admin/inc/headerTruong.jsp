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

	<script type="text/javascript">
		$(document).ready(function() {
			$('#selecctall').click(function(event) {  //on toggle click 
				if(this.checked) { // check toggle status
					$('.checkbox1').each(function() { //select all checkboxes with class "checkbox1"
						this.checked = true;                        
					});
				}else{
					$('.checkbox1').each(function() { //disselect all checkboxes with class "checkbox1"
						this.checked = false;                        
					});			
				}
			});
		});
</script>
	<div class="wrapper">
		<div class="container">
			<div class="header">
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
			<div class="">
						<ul class="nav nav-pills">
							<li role="presentation"><a href="khoa-dang-ky" class="btn btn-info" role="button">Hiệu chỉnh khóa đăng ký</a></li>
							<li role="presentation"><a href="hieu-chinh-user" class="btn btn-info" role="button">Hiệu chỉnh thành viên</a></li>
							<li role="presentation"><a href="hieu-chinh-thong-bao" class="btn btn-info" role="button">Hiệu chỉnh thông báo</a></li>
							<li class="" role="presentation">
								<div class="dropdown">
									<button class="btn btn-info dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
									    Chức năng chỉnh sửa
										<span class="caret"></span>
									</button>
								<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
									<li><a href="">Danh sách các cấp đề tài</a></li>
									<li><a href="">Danh sách các lĩnh vực</a></li>
									<li><a href="">Danh sách các khoa</a></li>
									<li><a href="">Danh sách các chuyên ngành</a></li>
								</ul>
								</div>
							</li>
							
						</ul>
					</div>
			<div class="main-body">
				
					