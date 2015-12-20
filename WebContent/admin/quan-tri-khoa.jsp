<%@page import="bean.Cap"%>
<%@page import="java.util.ArrayList"%>
<%@page import="bean.DeTai"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../templates/admin/inc/headerKhoa.jsp"%>
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
<div class="list-detai">
	<h3>Danh sách đề tài khoa công nghệ thông tin cấp trường</h3>
	<% 
		ArrayList<DeTai> listDeTai = new ArrayList<DeTai>();
		listDeTai = (ArrayList<DeTai>) request.getAttribute("listDeTai");
		
		ArrayList<Cap> listCap = new ArrayList<Cap>();
		listCap = (ArrayList<Cap>) request.getAttribute("listCap");
		
		int [] nam= (int []) request.getAttribute("nam");
		//ArrayList<Users> listKhoa = new ArrayList<Users>();
		//listKhoa = (ArrayList<Users>) request.getAttribute("listKhoa");
		int tt=1;
		Users usersLogin = (Users)session.getAttribute("users");
		
	%>
	<h3>Danh sách đề tài khoa <%= usersLogin.getFullname()%></h3>
	<form action="tim-kiem-khoa" method="post">
		
		<label>Số thẻ:</label> <input type="text" name="sothe" value=""/>
		<label>Tên đầy đủ:</label> <input type="text" name="fullname" value=""/>
		<label>Năm:</label>
		
		<select name="nam">
			<%
				for(int i=0; i <nam.length;i++){
					if(nam[i]==0) break;
			%>
		  			<option value="<%=nam[i] %>"><%=nam[i] %></option>
			<%}%>
		  <option value="0">Tất cả các năm</option>
		</select>
		
		<label>Cấp: </label>
		<select name="cap">
			<%
				for(Cap eachCap : listCap){
			%>
				<option value="<%=eachCap.getIdCap()%>"><%= eachCap.getTenCap()%></option>
			<%} %>
			<option value="" selected="selected">Tất cả</option>
		</select>
		
		
		<input type="submit" value="Tìm kiếm" name="timkiem"/>
		</form>
		
		<form action="quan-tri-khoa" method="post">
		<table>
			<tr>
				<th>TT</th>
				<th>Tên đề xuất chương trình</th>
				<th>Chủ nhiệm</th>
				<th>Tính cấp thiết</th>
				<th>Mục tiêu và nội dung chủ yếu</th>
				<th>Kết quả dự kiến</th>
				<th>Thời gian,nhu cầu kinh phí</th>
				<th>Ghi chú</th>
			<th>
				<input type = "submit" value = "Xác nhận" name = "xacnhan" /><br />
				<input type = "checkbox" name = "" id = "selecctall" />
			</th>
		</tr>
		<%
		if(listDeTai.size()!=0){
		for(DeTai eachDeTai:listDeTai){
			int idDeTai = eachDeTai.getIdDeTai();
			String tenDeTai = eachDeTai.getTenDeTai();
			String tinhcapthiet = eachDeTai.getTinhcapthiet();
			String muctieu = eachDeTai.getMuctieu();
			String noidung = eachDeTai.getNoidung();
			String ketquadukien = eachDeTai.getKetquadukien();
			int thoigian = eachDeTai.getThoigiandukien();
			int kinhphi = eachDeTai.getKinhphidukien();
			int kiemduyet = eachDeTai.getKiemduyet();
			%>
		<tr>
			<td><%=idDeTai %></td>
			<td><a href="#"><%=tenDeTai %></a></td>
			<td><a href="#"><%=eachDeTai.getUsers().getFullname() %></a></td>
			<td><%=tinhcapthiet %></td>
			<td>
				<span style='font-weight:bold;'>- Mục tiêu:</span><br />
				đây là mục tiêu của đề tài
				<br />
				<span style='font-weight:bold;'>- Nội dung chủ yếu:</span><br />
				đây là nội dung chủ yếu
				<br />
			</td>
			<td><%=ketquadukien %></td>
			<td>
				<span style='font-weight:bold;'>- Thời gian(ngày):</span>
				<%=thoigian %><br />
				<span style='font-weight:bold;'>- Nhu cầu kinh phí(VNĐ):</span>
				<%=kinhphi %><br />
			</td>
			<td>
				<%
					if(kiemduyet==0){
						out.println("Chưa xác nhận");
					}else if(kiemduyet == 1){
						out.println("Đã xác nhận");
					}else if(kiemduyet == 2){
						out.println("Đã duyệt");
					}else if(kiemduyet == 3){
						out.println("Không được duyệt");
					}
				%>
			</td>
			<td>
			<%if(kiemduyet==0){ %>
				<input type = "checkbox" value = "<%=idDeTai%>" name = "checkbox" class="checkbox1" />
			<%} %>
			</td>
		</tr>
			<%} }else{out.println("<span style = 'color:red;'>Không có đề tài để hiển thị</span>");}%>
			
		</table>
	</form>
</div>
<%@include file="../templates/admin/inc/footerKhoa.jsp"%>