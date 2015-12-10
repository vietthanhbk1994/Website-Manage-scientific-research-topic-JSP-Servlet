<%@page import="bean.Cap"%>
<%@page import="bean.DeTai"%>
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
	<%@include file="../templates/admin/inc/headerKhoa.jsp"%>
	<% 
		ArrayList<DeTai> listDeTai = new ArrayList<DeTai>();
		listDeTai = (ArrayList<DeTai>) request.getAttribute("listDeTai");
		
		ArrayList<Cap> listCap = new ArrayList<Cap>();
		listCap = (ArrayList<Cap>) request.getAttribute("listCap");
		
		//ArrayList<Users> listKhoa = new ArrayList<Users>();
		//listKhoa = (ArrayList<Users>) request.getAttribute("listKhoa");
		int tt=1;
	%>
	
	<form action="tim-kiem-khoa" method="post">
		
		<label>Số thẻ:</label> <input type="text" name="sothe" value=""/>
		<label>Tên đầy đủ:</label> <input type="text" name="fullname" value=""/>
		<label>Năm:</label>
		
		<select name="nam">
		<option value="<%=listCap.get(0).getNam() %>"><%=listCap.get(0).getNam() %></option>
			<%
			if(listCap.size()>1)
				for(int i=1; i <listCap.size();i++){
					if(listCap.get(i).getNam()==listCap.get(i-1).getNam()) continue;
			%>
		  			<option value="<%=listCap.get(i).getNam() %>"><%=listCap.get(i).getNam() %></option>
				<%
				 }
				%>
		  
		  <option value="0">Tất cả các năm</option>
		</select>
		
		<label>Cấp: </label>
		<select name="cap">
			<option value="Cấp trường">Cấp trường</option>
			<option value="Cấp đại học Đà Nẵng">Cấp đại học Đà Nẵng</option>
			<option value="Cấp bộ">Cấp bộ</option>
			<option value="Cấp nhà nước">Cấp nhà nước</option>
			<option value="" selected="selected">Tất cả các cấp</option>
		</select>
		
		
		<input type="submit" value="Tìm kiếm" name="timkiem"/>
		<table>
			<tr>
				<th>TT</th>
				<th>Tên đề tài</th>
				<th>Chủ nhiệm</th>
				<th>Tính cấp thiết</th>
				<th>Mục tiêu và nội dung chính</th>
				<th>Kết quả, sản phầm dự kiến</th>
				<th>Thời gian</th>
				<th>Kinh phí</th>
				<th>Ghi chú</th>
			</tr>
			<%
				for(DeTai eachDeTai: listDeTai){
					
				
			%>
			<tr>
				<td><%=tt++ %></td>
				<td><%=eachDeTai.getTenDeTai() %></td>
				<td><%=eachDeTai.getUsers().getFullname() %></td>
				<td><%= eachDeTai.getTinhcapthiet() %></td>
				<td>Mục tiêu: <%= eachDeTai.getMuctieu() %><br/>Nội dung: <%= eachDeTai.getNoidung() %></td>
				<td>Kết quả: <%= eachDeTai.getKetquadukien() %><br/>Sản phẩm đào tạo: <%=eachDeTai.getSanphamdaotao() %> <br>Sản phẩm khoa học: <%=eachDeTai.getSanphamkhoahoc() %> <br/> Sản phẩm ứng dụng: <%=eachDeTai.getSanphamungdung() %></td>
				<td><%= eachDeTai.getThoigiandukien() %></td>
				<td><%= eachDeTai.getKinhphidukien() %></td>
				<td><%= eachDeTai.getPhanhoi() %></td>
			</tr>
			<%} %>
		</table>
	</form>
</body>
</html>