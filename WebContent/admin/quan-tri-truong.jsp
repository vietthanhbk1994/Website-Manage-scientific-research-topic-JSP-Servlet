<%@page import="bean.Cap"%>
<%@page import="java.util.ArrayList"%>
<%@page import="bean.DeTai"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../templates/admin/inc/headerTruong.jsp"%>
<div class="list-detai">
	<h3>Danh sách đề tài khoa công nghệ thông tin cấp trường</h3>
	<% 
		ArrayList<DeTai> listDeTai = new ArrayList<DeTai>();
		listDeTai = (ArrayList<DeTai>) request.getAttribute("listDeTai");
		
		ArrayList<Cap> listCap = new ArrayList<Cap>();
		listCap = (ArrayList<Cap>) request.getAttribute("listCap");
		
		ArrayList<Users> listKhoa = new ArrayList<Users>();
		listKhoa = (ArrayList<Users>) request.getAttribute("listKhoa");
		int tt=1;
		if(listDeTai.size()!=0){
	%>
	
	<form action="tim-kiem-truong" method="post">
		
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
		
		<label>Khoa: </label>
		<select name="idKhoa">
			<option value="<%=listKhoa.get(0).getIdKhoa() %>"><%=listKhoa.get(0).getTenKhoa() %></option>
			<%
				for(int i=1; i <listKhoa.size();i++){
					if(!listKhoa.get(i).getTenKhoa().equals(listKhoa.get(i-1).getTenKhoa())){ 
			%>
				<option value="<%=listKhoa.get(i).getIdKhoa()%>"><%=listKhoa.get(i).getTenKhoa() %></option>
			<%} }%>
			<option value="0" selected="selected">Tất cả các khoa</option>
		</select>
		
		<input type="submit" value="Tìm kiếm" name="timkiem"/>
		<br />
		<table>
			<tr>
				<th>TT</th>
				<th>Tên đề xuất chương trình</th>
				<th>Tên chủ nhiệm</th>
				<th>Tính cấp thiết</th>
				<th>Mục tiêu và nội dung chủ yếu</th>
				<th>Kết quả dự kiến</th>
				<th>Thời gian,nhu cầu kinh phí</th>
				<th>Ghi chú</th>
			</tr>
			<%
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
			<td><%=tt++ %></td>
			<td><a href="chitiet-detai-truong?detai=<%=idDeTai%>"><%=tenDeTai %></a></td>
			<td><%=eachDeTai.getUsers().getFullname() %></td>
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
					if(kiemduyet==1){
						out.println("<span style = 'color:red;'>Chưa kiểm duyệt</span>");
					}else if(kiemduyet==2){
						out.println("<span style = 'color:red;'>Được thông qua</span>");
					}else if(kiemduyet==3){
						out.println("<span style = 'color:red;'>Không được thông qua</span>");
					}
				%>
			</td>
		</tr>
			<%} %>
			<% }else{out.println("<span style = 'color:red;'>Không có đề tài để hiển thị</span>");}%>
		</table>
	</form>
</div>
<%@include file="../templates/admin/inc/footerTruong.jsp"%>