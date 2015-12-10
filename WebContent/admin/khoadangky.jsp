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
		ArrayList<Cap> listCapDeTai = (ArrayList<Cap>)request.getAttribute("listCapDeTai");
		
	%>
	<a href="<%=request.getContextPath()%>/admin/khoa-dang-ky?load=tao" title="" class="themthongbao">Tạo đợt đăng ký</a>
	
	<%
	//<form action="them-cap-dang-ky" method="post" class="themthongbao">
	//<label>Thêm cấp đăng ký: </label>
	//<label>Tên cấp đăng ký: </label>
	//<input type="text" name="tenCapDangKy" value="" class="textbig"/>
	
	//<input type="submit" name="submit" value="Thêm cấp đăng ký"/>
	%>
		
	</form>
	
	
		<table class="table table-hover">
			<tr>
				<th>ID cấp đăng ký</th>
				<th>Tên cấp đăng ký</th>
				<th>Thời gian mở</th>
				<th>Thời gian đóng</th>
				<th>Hiệu chỉnh</th>
			</tr>
			<%
				for(Cap eachCap:listCapDeTai){
				
			%>
			<tr>
				<td>
					<%=eachCap.getIdCap() %>	
				</td>
				<td>
					<%=eachCap.getTenCap() %>	
				</td>
				<td>
					<%=eachCap.getTimeOpen() %>	
				</td>
				<td>
					<%=eachCap.getTimeClose() %>	
				</td>
				<td>
					<a href="<%=request.getContextPath()%>/admin/khoa-dang-ky?load=sua&id=<%=eachCap.getIdCap() %>" title="" class="themthongbao">Sửa</a>
				</td>
			</tr>
			<%} %>
		</table>
		<%@include file="../templates/admin/inc/footerTruong.jsp"%>
</body>
</html>
