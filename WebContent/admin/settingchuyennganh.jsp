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
				<span class="glyphicon glyphicon-plus" > Thêm chuyên ngành</span>
	</button>
	<div class="hieuchinh">
	
	<form action="<%=request.getContextPath()%>/admin/setting?chose=chuyennganh&load=xoa" method="post">
		<h4>Danh sách các khoa và chuyên ngành</h4>
		<table class="table table-hover">
		<thead>
			<tr>
				<th>STT</th>
				<th>Tên khoa/ chuyên ngành</th>
				<th>Sửa</th>
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
				
				for(Khoa eachKhoa: listKhoaChuyenNganh){
					int stt=0;
			%>
			<tr>
				<th style="text-align: left;" colspan="4"><%= eachKhoa.getTenKhoa() %></th>
			</tr>
			<%
			
				for(ChuyenNganh eachChuyenNganh: eachKhoa.getListChuyenNganh()){
					if(eachChuyenNganh.getTenChuyenNganh()==null) continue;
			%>
			<tr>
				<td><%= ++stt %></td>
				<td><%= eachChuyenNganh.getTenChuyenNganh() %></td>
				<td>
					<a href="<%=request.getContextPath()%>/admin/setting?chose=chuyennganh&load=sua&idSua=<%= eachChuyenNganh.getIdChuyenNganh()%>&tenSua=<%=eachChuyenNganh.getTenChuyenNganh()%>&idKhoa=<%=eachKhoa.getIdKhoa()%>">Sửa</a>
				</td>
				<td>
					<input type="checkbox" class="checkbox1" name="xoathongbao" value="<%=eachChuyenNganh.getIdChuyenNganh()%>" />
				</td>
			</tr>
			
			<%}}%>
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
		<h4>Thêm chuyên ngành</h4>
		<form action="<%=request.getContextPath()%>/admin/setting?chose=chuyennganh&load=them" method="post">
			<label>Chuyên ngành thuộc khoa:</label>
			<select name="idKhoa">
				<%
					for(Khoa eachKhoa: listKhoaChuyenNganh){
				%>
				<option value="<%= eachKhoa.getIdKhoa()%>"><%=eachKhoa.getTenKhoa() %></option>
				<% }%>
			</select>
			<br />
			<br />
			<label>Tên chuyên ngành:</label>
			<input type="text" name="tenCapThem" required />
			<br />
			<br />
			<input type="submit" name="submit" value="Thêm chuyên ngành"  class="btn btn-success"/>
		</form>
	</div>
</body>
</html>