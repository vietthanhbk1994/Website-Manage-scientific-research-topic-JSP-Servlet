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
<h2>CHÀO MỪNG ĐẾN VỚI HỆ THỐNG ĐĂNG KÝ ĐỀ TÀI NGHIÊN CỨU KHOA HỌC ONLINE</h2>
<div class="thongbao">
<form name = "frm-del" action = "delete">
<h3>ĐỀ TÀI THAM GIA</h3>
<table  class="table table-hover">
	<%
		Users userslogin = (Users)session.getAttribute("users");
		String msg = request.getParameter("msg");
		if(msg!=null){
			out.print("<h3 style = 'color:red;'>"+msg+"</h3>");
		}
		ArrayList<DeTai> ListDeTai = (ArrayList<DeTai>) request.getAttribute("ListDeTai");
		ArrayList<DeTai> listDeTai2 = (ArrayList<DeTai>) request.getAttribute("listDeTai2");
		int stt=0;
		if(ListDeTai!=null && ListDeTai.size()==0){
			out.println("<h4 style = 'color:red'>Chưa có đề tài nào</h4");
		}else{
	%>
<tr>
	<th>STT</th>
	<th>Tên Đề Tài</th>
	<th>Cấp đề tài</th>
	<th>Xác nhận đăng ký</th>
	<th>Tình trạng</th>
	<th>
		<input type = "submit" name = "del" value ="Xóa" />
		<input type = "checkbox" id="selecctall" />
	</th>
</tr>

<%
	for(DeTai eachDeTai:ListDeTai){
%>
<tr>
	<td><%=++stt %></td>
	<td><a href = "chi-tiet?de-tai=<%=eachDeTai.getIdDeTai() %>"><%=eachDeTai.getTenDeTai() %></a></td>
	<td><%=eachDeTai.getTenCap() %></td>
	<td>
	<%if(eachDeTai.getXacnhandangky()==0){
		if(userslogin.getIdUser()==eachDeTai.getIdNguoiDK()){
		%>
	<a href = "xacnhandangky?detai=<%=eachDeTai.getIdDeTai()%>&&cap=<%=eachDeTai.getIdCap()%>">Đăng ký</a>	
	
	<%
		}else{
			out.println("<span style = 'color:red;'>Bạn không phải người đề xuất</span>");
		}
		}else out.println("<span style = 'color:red;'>Đã đăng ký</span>");
	%>
	</td>
	<td>
	<%
	if(eachDeTai.getKiemduyet()==2){
		out.println("Đã thông qua");
	}else{
	if(eachDeTai.getKiemduyet()==3){
		out.println("Không thông qua");
	}else{
		out.println("Chưa duyệt");
	}
	}
	%>
	</td>
	<td>
		<%if(eachDeTai.getXacnhandangky()==0){ %>
			<input type="checkbox" name = "checkbox" class="checkbox1" value="<%=eachDeTai.getIdDeTai()%>" />
		<%} %>
	</td>
</tr>

<%} }%>

</table><br />
<table class="table table-hover">
<h3>ĐỀ TÀI ĐỀ XUẤT NHƯNG KHÔNG THAM GIA</h3>
<%
if(listDeTai2!=null && listDeTai2.size()==0){
	out.println("<h4 style = 'color:red'>không có</h4");
}else{
%>
<tr>
	<th>Tên Đề Tài</th>
	<th>Cấp đề tài</th>
	<th>Xác nhận đăng ký</th>
	<th>Tình trạng</th>
	<th>
		<input type = "submit" name = "del" value ="Xóa" />
		<input type = "checkbox" id="selecctall" />
	</th>
</tr>

<%
	for(DeTai eachDeTai2:listDeTai2){
%>
<tr>
	<td><a href = "chi-tiet?de-tai=<%=eachDeTai2.getIdDeTai() %>"><%=eachDeTai2.getTenDeTai() %></a></td>
	<td><%=eachDeTai2.getTenCap() %></td>
	<td>
	<%if(eachDeTai2.getXacnhandangky()==0){
		if(userslogin.getIdUser()==eachDeTai2.getIdNguoiDK()){
		%>
	<a href = "xacnhandangky?detai=<%=eachDeTai2.getIdDeTai()%>&&cap=<%=eachDeTai2.getIdCap()%>">Đăng ký</a>	
	
	<%
		}else{
			out.println("<span style = 'color:red;'>Bạn không phải người đề xuất</span>");
		}
		}else out.println("<span style = 'color:red;'>Đã đăng ký</span>");
	%>
	</td>
	<td>
	<%
	if(eachDeTai2.getKiemduyet()==3){
		out.println("Đã duyệt");
	}else{
	if(eachDeTai2.getKiemduyet()==-1){
		out.println("Không duyệt");
	}else{
		out.println("Chưa duyệt");
	}
	}
	%>
	</td>
	<td>
		<%if(eachDeTai2.getXacnhandangky()==0){ %>
			<input type="checkbox" name = "checkbox" class="checkbox1" value="<%=eachDeTai2.getIdDeTai()%>" />
		<%} %>
	</td>
</tr>

<%} }%>

</table>
</form>
</div>

<%@include file="templates/public/inc/footer.jsp" %>