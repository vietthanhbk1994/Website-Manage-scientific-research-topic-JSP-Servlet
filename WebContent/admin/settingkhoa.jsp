<%@page import="bean.ChuyenNganh"%>
<%@page import="bean.Khoa"%>
<%@page import="bean.Linhvuc"%>
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
		String msg = request.getParameter("msg");
		if(msg!=null){
			out.print("<span style='color:red;'>"+msg+"</span><br />");
		}
	%>
	<button type="button" class="btn btn-primary themButton" id="them">
				<span class="glyphicon glyphicon-plus" >Thêm khoa</span>
	</button>
	<div class="hieuchinh">
	
	<form action="<%=request.getContextPath()%>/admin/setting?chose=khoa&load=xoa" method="post">
		<h4>Danh sách các khoa</h4>
		<table class="table table-hover">
		<thead>
			<tr>
				<th>STT</th>
				<th>Tên khoa</th>
				<th>Sửa tên khoa</th>
				
				<th>
					<input type="submit" name="submit" value="Xóa"  class="btn btn-danger" />
					<br/>
					<input type="checkbox" name="xoatatca" value="0" id="selecctall"/>Xóa tất cả
				</th>
			</tr>
		</thead>
		<tbody>
			<%
				ArrayList<Khoa> listKhoaChuyenNganh = (ArrayList<Khoa>) request.getAttribute("listKhoaChuyenNganh");
				int stt=0;
				for(Khoa eachKhoa: listKhoaChuyenNganh){
			%>
			<tr>
				<td><%= ++stt %></td>
				<td><%= eachKhoa.getTenKhoa() %></td>
				<td>
					<a href="<%=request.getContextPath()%>/admin/setting?chose=khoa&load=sua&idSua=<%= eachKhoa.getIdKhoa()%>&tenSua=<%=eachKhoa.getTenKhoa()%>">Sửa</a>
				</td>
				<td>
					<input type="checkbox" class="checkbox1" name="xoathongbao" value="<%=eachKhoa.getIdKhoa()%>" class="btn btn-success" />
				</td>
			</tr>
			
			<%}%>
		</tbody>		
		</table>
	</form>
	</div>

	<script>
	$(document).ready(function(){
	    //$("#hieuchinh").click(function(){
	     //   $(".action_reminder").show();
	      //  $(".action_note").hide();
	   // });
	    $("#them").click(function(){
	    	$(".hieuchinh").hide();
	        $(".themCap").show();
	        $(".themButton").hide();
	    });
	});
	</script>
	<div class="themCap" hidden>
		<h4>Thêm khoa</h4>
		<form action="<%=request.getContextPath()%>/admin/setting?chose=khoa&load=them" method="post">
			<label>Tên khoa:</label>
			<input type="text" name="tenCapThem" required />
			<input type="submit" name="submit" value="Thêm khoa"/>
		</form>
	</div>
</body>
</html>