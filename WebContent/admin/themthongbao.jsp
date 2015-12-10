<%@page import="java.sql.Date"%>
<%@page import="bean.ThongBao"%>
<%@page import="com.sun.org.apache.regexp.internal.recompile"%>
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
	<form action="<%=request.getContextPath()%>/admin/hieu-chinh-thong-bao?load=them&act=dathem" method="post" enctype="multipart/form-data" class="themthongbao">
	
		<label>Tên thông báo: </label>
		<input type="text" name="tenThongBao" value="" class="textbig" required />
						
		<label>Nội dung thông báo: </label>
		<textarea name="noiDungThongBao" required class="ckeditor" ></textarea>
						
		<label>Chế độ đăng:</label>
		<div class="chedodang">
			<label class="chedo">Hiển thị</label>	
			<input type="radio" name="cheDoDang" value="1" checked/>
			<label class="chedo">Không hiển thị</label>
			<input type="radio" name="cheDoDang" value="0" />
		</div>
		<label>File đính kèm:</label>
		<input type="file" name="files" id="file">
		
		<div class="tuychon">
			<input type="submit" name="submit" value="Thêm thông báo"/>
		</div>
		<input type="button" value="Print" onclick="window.print()" />
		
	</form>
	<%@include file="../templates/admin/inc/footerTruong.jsp"%>
</body>
</html>