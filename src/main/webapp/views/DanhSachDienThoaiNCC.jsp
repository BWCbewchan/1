<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Danh Sách Điện Thoại</title>
<!-- Bootstrap CSS -->
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
	<div class="container">
		<!-- Header and Navbar -->
		<h1 class="my-4">Quản Lý Sản Phẩm</h1>
		<nav class="mb-4">
			<a href="dsdtncc" class="btn btn-primary">Danh Sách Sản Phẩm</a> <a
				href="DienThoaiFormServlet" class="btn btn-secondary">Thêm Mới
				Sản Phẩm</a> <a href="QuanLyFormServlet" class="btn btn-info">Chức
				Năng Quản Lý</a>
		</nav>

		<!-- Tìm kiếm Nhà Cung Cấp -->
		<h3 class="my-4">Danh Sách Nhà Cung Cấp</h3>
		<form action="dsdtncc" method="get" class="form-inline mb-4 w-100">
			<input type="text" name="searchNCC" class="form-control mr-2 col-8"
				placeholder="Tìm kiếm theo Mã NCC, Tên NCC, Địa Chỉ, hoặc Số Điện Thoại" />
			<input type="submit" value="Tìm Kiếm" class="btn btn-primary" />
			<a href="dsdtncc" class="btn btn-secondary ml-auto">Làm Mới</a>
		</form>

		<table class="table table-striped">
			<thead class="thead-dark">
				<tr>
					<th>Mã NCC</th>
					<th>Tên Nhà Cung Cấp</th>
					<th>Địa Chỉ</th>
					<th>Số Điện Thoại</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="ncc" items="${listNCC}">
					<tr>
						<td><c:out value="${ncc.maNCC}" /></td>
						<td><c:out value="${ncc.tenNCC}" /></td>
						<td><c:out value="${ncc.diaChi}" /></td>
						<td><c:out value="${ncc.soDienThoai}" /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<!-- Tìm kiếm Điện Thoại -->
		<h3 class="my-4">Danh Sách Điện Thoại</h3>
		<form action="dsdtncc" method="get" class="form-inline mb-4 w-100">
			<input type="text" name="searchDT" class="form-control mr-2 col-8"
				placeholder="Tìm kiếm theo maDT, tenDT, namSX hoặc maNCC" />
			<input type="submit" value="Tìm Kiếm" class="btn btn-primary" />
			<a href="dsdtncc" class="btn btn-secondary ml-auto">Làm Mới</a>
		</form>

		<table class="table table-striped">
			<thead class="thead-dark">
				<tr>
					<th>Mã Điện Thoại</th>
					<th>Tên Điện Thoại</th>
					<th>Năm Sản Xuất</th>
					<th>Cấu Hình</th>
					<th>Mã NCC</th>
					<th>Hình Ảnh</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="dienThoai" items="${listDienThoai}">
					<tr>
						<td>${dienThoai.maDT}</td>
						<td>${dienThoai.tenDT}</td>
						<td>${dienThoai.namSanXuat}</td>
						<td>${dienThoai.cauHinh}</td>
						<td>${dienThoai.nhaCungCap.tenNCC}</td>
						<td><img
							src="${pageContext.request.contextPath}/uploads/${dienThoai.hinhAnh}"
							width="100" height="100" alt="Hình Ảnh" /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>


		<!-- Footer -->
		<footer class="mt-4 text-center">
			<small class="text-muted">Nguyễn Tiến Phát - 21032191</small>
		</footer>
	</div>


	<!-- Bootstrap JS and dependencies -->
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
