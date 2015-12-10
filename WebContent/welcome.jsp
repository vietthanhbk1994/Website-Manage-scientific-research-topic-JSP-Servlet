<%@page import="bean.DeTai"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="templates/public/inc/header.jsp"%>
<script type="text/javascript">
$(document).ready(function() {
	$('#selecctall').click(function(event) {  //on toggle click 
		if(this.checked) { // check toggle status
			$('.checkbox1').each(function() { //select all checkboxes with class "checkbox1"
				this.checked = true;                        
			});
		}else{
			$('.checkbox1').each(function() { //disselect all checkboxes with class "checkbox1"
				this.checked = false;                        
			});			
		}
	});
	
});
</script>
<h4>CHÀO MỪNG ĐẾN VỚI HỆ THỐNG ĐĂNG KÝ ĐỀ TÀI NGHIÊN CỨU KHOA HỌC ONLINE</h4>
<div class="thongbao">
<form name = "frm-del" action = "delete">
<table class="table table-hover">

<%
Users userslogin = (Users)session.getAttribute("users");
String msg = request.getParameter("msg");
if(msg!=null){
	out.print("<h3 style = 'color:red;'>"+msg+"</h3>");
}
ArrayList<DeTai> ListDeTai = (ArrayList<DeTai>) request
.getAttribute("ListDeTai");
if(ListDeTai!=null && ListDeTai.size()==0){
	out.println("<h4 style = 'color:red'>Chưa có đề tài nào</h4");
}else{
%>
<tr>
	<th>Tên Đề Tài</th>
	<th>Cấp đề tài</th>
	<th>Xác nhận đăng ký</th>
	<th>Kiểm duyệt</th>
	<th>
		<input type = "submit" name = "del" value ="Xóa" />
		<br />
		<input type = "checkbox" id="selecctall" /> Chọn tất cả 
	</th>
</tr>

<%
	for(DeTai eachDeTai:ListDeTai){
%>
<tr>
	<td><a href = "chi-tiet?de-tai=<%=eachDeTai.getIdDeTai() %>"><%=eachDeTai.getTenDeTai() %></a></td>
	<td><%=eachDeTai.getTenCap() %></td>
	<td>
	<%if(eachDeTai.getXacnhandangky()==0){
		if(userslogin.getIdUser()==eachDeTai.getIdNguoiDK()){
		%>
	<a href = "xacnhandangky?detai=<%=eachDeTai.getIdDeTai()%>&&cap=<%=eachDeTai.getIdCap()%>">Đăng ký</a>	
	
	<%
		}else{
			out.println("<span style = 'color:red;'>Bạn không phải chủ đăng ký</span>");
		}
		}else out.println("<span style = 'color:red;'>Đã đăng ký</span>");
	%>
	</td>
	<td>
	<%
	if(eachDeTai.getKiemduyet()==3){
		out.println("Đã duyệt");
	}else{
	if(eachDeTai.getKiemduyet()==-1){
		out.println("Không duyệt");
	}else{
		out.println("Chưa duyệt");
	}
	}
	%>
	</td>
	<td>
		<input type="checkbox" name = "checkbox" class="checkbox1" value="<%=eachDeTai.getIdDeTai()%>" />
	</td>
</tr>

<%} }%>

</table>
</form>
</div>

<%@include file="templates/public/inc/footer.jsp" %>