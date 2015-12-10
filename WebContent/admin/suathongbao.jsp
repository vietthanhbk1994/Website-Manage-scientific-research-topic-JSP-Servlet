<%@page import="bean.ThongBao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		ThongBao thongBaoSua = (ThongBao) request.getAttribute("NoiDungSua");
		String tenThongBao = thongBaoSua.getTenThongBao();
		String noiDungThongBao = thongBaoSua.getNoiDung();
		int cheDoDang = thongBaoSua.getCheDo();
		int idThongBao = thongBaoSua.getIdThongBao();
	%>
	<%@include file="../templates/admin/inc/headerTruong.jsp"%>
	<form
		action="<%=request.getContextPath()%>/admin/hieu-chinh-thong-bao?load=sua&act=dasua&id=<%=idThongBao%>"
		method="post" class="themthongbao" enctype="multipart/form-data" >
		<label>Tên thông báo: </label> 
		<input type="text" name="tenThongBao" value="<%=tenThongBao%>" class="textbig" required /> 
		<label>Nội dung thông báo: </label>
		<textarea name="noiDungThongBao" required class="ckeditor" > <%=noiDungThongBao%> </textarea>

		<label>Chế độ đăng:</label>
		<div class="chedodang">

			<%
				String hienthi = null;
				String khonghienthi = null;
				if (cheDoDang == 1) {
					hienthi = "checked";
				} else {
					khonghienthi = "checked";
				}
			%>
			<label>Hiển thị</label> <input type="radio" name="cheDoDang"
				<%=hienthi%> value="1" /> <label>Không hiển thị</label> <input
				type="radio" name="cheDoDang" <%=khonghienthi%> value="0" />
		</div>
		
		<label>File đính kèm:</label>
		<input type="file" name="files" id="file">
		
		<div class="tuychon">
			<input type="submit" name="submit" value="Sửa thông báo" />
		</div>
	</form>
	<%@include file="../templates/admin/inc/footerTruong.jsp"%>
</body>
</html>