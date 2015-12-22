<%@page import="bean.Cap"%>
<%@page import="java.util.ArrayList"%>
<%@page import="bean.DeTai"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../templates/admin/inc/headerTruong.jsp"%>
<style>
	@media print
	{
		.notprint{ display:none; }
	} 
</style>
<div class="list-detai">
	<% 
		ArrayList<DeTai> listDeTai = (ArrayList<DeTai>) request.getAttribute("listDeTai");
		
		ArrayList<Cap> listCap = new ArrayList<Cap>();
		listCap = (ArrayList<Cap>) request.getAttribute("listCap");
		
		ArrayList<Users> listKhoa = new ArrayList<Users>();
		listKhoa = (ArrayList<Users>) request.getAttribute("listKhoa");
		
		DeTai deTaiSearch = (DeTai) request.getAttribute("deTaiSearch");
		int [] nam= (int []) request.getAttribute("nam");
		
		int tt=1;
		String sothe ="", fullname = "";
		int namSearch=0, idKhoa=0, idCap=0;
		if(deTaiSearch!=null){
			sothe = deTaiSearch.getUsers().getSoThe();
			fullname = deTaiSearch.getUsers().getFullname();
			namSearch = Integer.parseInt(deTaiSearch.getNgaydangky());
			idKhoa = deTaiSearch.getIdKhoa();
			idCap = deTaiSearch.getIdCap();
		}
		
	%>
	<h3>Danh sách đề tài:</h3>
	<form action="tim-kiem-truong" method="post" class = "notprint">
		
		<label>Số thẻ:</label> 
		<input type="text" name="sothe" value="<%if(sothe!="") out.print(sothe); %>" style="width:140px;"/>
		<label>Tên đầy đủ:</label> <input type="text" name="fullname" value="<%if(fullname!="") out.print(fullname); %>"/>
		<label>Năm:</label>
		
		<select name="nam">
			<%
				for(int i=0; i <nam.length;i++){
					if(nam[i]==0) break;
			%>
		  			<option value="<%=nam[i] %>" <% if(namSearch!=0 && namSearch == nam[i]) out.print("selected"); %>><%=nam[i] %></option>
			<%}%>
		  
		  <option value="0" <% if(namSearch==0) out.print("selected"); %>>Tất cả</option>
		</select>
		
		<label>Cấp: </label>
		<select name="cap">
			<%
				for(Cap eachCap : listCap){
			%>
				<option value="<%=eachCap.getIdCap()%>" <% if(idCap!=0 && idCap == eachCap.getIdCap()) out.print("selected"); %>><%= eachCap.getTenCap()%></option>
			<%} %>
			<option value="0" <% if(idCap==0) out.print("selected"); %>>Tất cả</option>
		</select>
		
		<label>Khoa: </label>
		<select name="idKhoa">
			<option value="<%=listKhoa.get(0).getIdKhoa() %>" <% if(idKhoa!=0 && idKhoa == listKhoa.get(0).getIdKhoa()) out.print("selected"); %>><%=listKhoa.get(0).getTenKhoa() %></option>
			<%
				for(int i=1; i <listKhoa.size();i++){
					if(!listKhoa.get(i).getTenKhoa().equals(listKhoa.get(i-1).getTenKhoa())){ 
			%>
				<option value="<%=listKhoa.get(i).getIdKhoa()%>" <% if(idKhoa!=0 && idKhoa == listKhoa.get(i).getIdKhoa()) out.print("selected"); %>><%=listKhoa.get(i).getTenKhoa() %></option>
			<%} }%>
			<option value="0" <% if(idKhoa==0) out.print("selected"); %>>Tất cả các khoa</option>
		</select>
		
		<input type="submit" value="Lọc" name="timkiem" class="btn btn-success" />
		</form>
		<table class="table table-hover">
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
			<input type = "button" onclick = "print();" value = "Print" class = "notprint" />
		</table>
			<% }else{out.println("<span style = 'color:red;'>Không có đề tài để hiển thị</span>");}%>
	
</div>
<%@include file="../templates/admin/inc/footerTruong.jsp"%>