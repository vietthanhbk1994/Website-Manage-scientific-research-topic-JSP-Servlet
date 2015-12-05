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
	
	<form action="khoa-dang-ky?load=tao&act=datao" method="post" enctype="multipart/form-data" >
		<label>Đăng ký cấp: </label>
		<select name="cap">
			<option value="Cấp trường">Cấp trường</option>
			<option value="Cấp đại học Đà Nẵng">Cấp đại học Đà Nẵng</option>
			<option value="Cấp bộ">Cấp bộ</option>
			<option value="Cấp nhà nước">Cấp nhà nước</option>
		</select>
		
		<label>Tên đợt đăng ký: </label>
		<input type="text" name="tenThongBao" value="" class="textbig"/>
						
		<label>Nội dung chi tiết: </label>
		<textarea name="noiDungThongBao"></textarea>
		
		<label>File đính kèm:</label>
		<input type="file" name="files" id="file">
		
		<label>Thời gian bắt đầu đợt đăng kí: </label>
		<input type="time" name="timeOpen0" value=""/>
		<input type="date" name="timeOpen1" value=""/>
		<label>Thời gian kết thúc đợt đăng kí: </label>
		<input type="time" name="timeClose0" value=""/>
		<input type="date" name="timeClose1" value=""/>
		<br/>
		<input type="submit" value="Tạo đợt đăng ký và thông báo">
	</form>
	<%@include file="../templates/admin/inc/footerTruong.jsp"%>
</body>
</html>