<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.sql.Date"%>
<%@page import="bean.ThongBao"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="templates/public/inc/header.jsp"%>

<h4>THÔNG BÁO:</h4>
<div class="thongbao">
	<ul>
	<%
		ArrayList<ThongBao> listThongBao = (ArrayList<ThongBao>) request.getAttribute("listThongBao");
	
		for(ThongBao eachThongBao : listThongBao){
			//int idThongBao = eachThongBao.getIdThongBao();
			int cheDo = eachThongBao.getCheDo();
			if(cheDo==0) continue;
			String tenThongBao = eachThongBao.getTenThongBao();
			String noiDung = eachThongBao.getNoiDung();
			String dinhKemFile = eachThongBao.getDinhKemFile();
			String linkDownload = eachThongBao.getLinkDownload();
			
			String ngayDangTin = eachThongBao.getNgayDang();
						
	%>
		<li><span class="ngaydangtin"><%=ngayDangTin%>: </span>
			<h5><%=tenThongBao %></h5>
			<p><%=noiDung %></p>
			<%
				if(dinhKemFile!=""){
			%>
			<a href="<%=linkDownload%>" target="_blank">tại đây</a>
			<%}%>
		</li>
		<%} %>
	</ul>
</div>

<%@include file="templates/public/inc/footer.jsp" %>