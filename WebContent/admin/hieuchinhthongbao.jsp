<%@page import="java.util.ArrayList"%>
<%@page import="bean.ThongBao"%>
<%@page import="java.sql.Date"%>
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
	<a href="<%=request.getContextPath()%>/admin/hieu-chinh-thong-bao?load=them" title="" class="themthongbao">Thêm thông báo</a>
	<form action="<%=request.getContextPath()%>/admin/hieu-chinh-thong-bao" method="get">
	
	<table class="table table-hover">
		<tr>
			<th>STT</th>
			<th>Ngày đăng</th>
			<th>Tên thông báo</th>
			<th>Nội dung thông báo</th>
			<th>Hiển thị</th>
			<th>File đính kèm</th>
			<th>Sửa</th>
			<th>
				<input type="submit" name="submit" value="Xóa"/>
				<br/>
				<input type="checkbox" name="xoatatca" value="0" id="selecctall"/>Xóa tất cả
			</th>
		</tr>
	<%
		ArrayList<ThongBao> listThongBao = (ArrayList<ThongBao>) request.getAttribute("listThongBao");
		int stt=0;
		for(ThongBao eachThongBao : listThongBao){
			int idThongBao = eachThongBao.getIdThongBao();
			int cheDo = eachThongBao.getCheDo();
			String cheDoDang="";
			cheDoDang=(cheDo==1)?"Cho phép":"Không cho phép";	
			String tenThongBao = eachThongBao.getTenThongBao();
			String noiDung = eachThongBao.getNoiDung();
			String ngayDang = eachThongBao.getNgayDang();
			String dinhKemFile = eachThongBao.getDinhKemFile();
			String linkDownload = eachThongBao.getLinkDownload();
			
			
	%>
		<tr>
			<td><%= ++stt %></td>
			<td><%= ngayDang %></td>
			<td><%= tenThongBao %></td>
			<td><%= noiDung %></td>
			
			<td>
				<%=cheDoDang %>
			</td>
			<td>
				<a href="<%=linkDownload%>" target="_blank"><%=dinhKemFile %></a>
			</td>
			<td>
				<a href="<%=request.getContextPath()%>/admin/hieu-chinh-thong-bao?load=sua&id=<%=idThongBao%>">Sửa</a>
			</td>
			<td>
				<input type="checkbox" class="checkbox1" name="xoathongbao" value="<%=idThongBao%>" />
			</td>
			
		</tr>
		<%} %>
	</table>
	</form>
	<%@include file="../templates/admin/inc/footerTruong.jsp"%>
</body>
</html>





