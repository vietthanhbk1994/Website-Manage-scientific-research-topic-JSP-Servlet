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
				<span class="glyphicon glyphicon-plus" >Thêm lĩnh vực</span>
	</button>
	<div class="hieuchinh">
	<h4>Danh sách các lĩnh vực nghiên cứu</h4>
	<form action="<%=request.getContextPath()%>/admin/setting?chose=linhvuc&load=xoa" method="post">
		<table class="table table-hover">
		<tr>
			<th>STT</th>
			<th>Tên lĩnh vực</th>
			<th>Sửa tên lĩnh vực</th>
			<th>
				<input type="submit" name="submit" value="Xóa"  class="btn btn-danger" />
				<br/>
				<input type="checkbox" name="xoatatca" value="0" id="selecctall"/>Xóa tất cả
			</th>
		</tr>
		<%
			ArrayList<Linhvuc> listLinhVuc = (ArrayList<Linhvuc>) request.getAttribute("listLinhVuc");
			int stt=0;
			for(Linhvuc eachLinhVuc : listLinhVuc){
		%>
		<tr>
			<td><%= ++stt %></td>
			<td><%= eachLinhVuc.getTenLinhVuc() %></td>
			<td>
				<a href="<%=request.getContextPath()%>/admin/setting?chose=linhvuc&load=sua&idSua=<%=eachLinhVuc.getIdLinhvuc()%>&tenSua=<%=eachLinhVuc.getTenLinhVuc()%>">Sửa</a>
			</td>
			<td>
				<input type="checkbox" class="checkbox1" name="xoathongbao" value="<%=eachLinhVuc.getIdLinhvuc()%>" />
			</td>
		</tr>
		<%} %>		
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
		<h4>Thêm lĩnh vực nghiên cứu</h4>
		<form action="<%=request.getContextPath()%>/admin/setting?chose=linhvuc&load=them" method="post">
			<label>Tên lĩnh vực:</label>
			<input type="text" name="tenCapThem" required />
			<input type="submit" name="submit" value="Thêm lĩnh vực" class="btn btn-success" />
		</form>
	</div>
</body>
</html>