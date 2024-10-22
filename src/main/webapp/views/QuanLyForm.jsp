<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<% 
    // Set response headers to prevent caching
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
    response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
    response.setDateHeader("Expires", 0); // Proxies.
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Quản Lý Điện Thoại</title>
    
    <!-- Prevent caching with meta tags -->
    <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
    <meta http-equiv="Pragma" content="no-cache" />
    <meta http-equiv="Expires" content="0" />

    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container my-4">
        <!-- Header -->
        <h1 class="my-4">Quản Lý Điện Thoại</h1>

        <!-- Navbar -->
        <nav class="mb-4">
            <a href="${pageContext.request.contextPath}/dsdtncc" class="btn btn-primary">Danh Sách Sản Phẩm</a>
            <a href="${pageContext.request.contextPath}/DienThoaiFormServlet" class="btn btn-secondary">Thêm Mới Sản Phẩm</a>
            <a href="${pageContext.request.contextPath}/QuanLyFormServlet" class="btn btn-info">Chức Năng Quản Lý</a>
        </nav>

        <!-- Search Form -->
        <form action="${pageContext.request.contextPath}/QuanLyFormServlet" method="get" class="form-inline mb-4 w-100">
            <input type="text" name="searchDT" class="form-control mr-2 col-8" placeholder="Tìm kiếm theo maDT, tenDT, namSX" />
            <input type="submit" value="Tìm Kiếm" class="btn btn-primary" />
            <a href="${pageContext.request.contextPath}/QuanLyFormServlet" class="btn btn-secondary ml-auto">Làm Mới</a>
        </form>

        <!-- Display messages -->
        <c:if test="${not empty message}">
            <div class="alert alert-success">${message}</div>
        </c:if>
        <c:if test="${not empty error}">
            <div class="alert alert-danger">${error}</div>
        </c:if>

        <!-- Table of phones -->
        <table class="table table-striped">
            <thead class="thead-dark">
                <tr>
                    <th>Mã ĐT</th>
                    <th>Tên Điện Thoại</th>
                    <th>Năm Sản Xuất</th>
                    <th>Thông Tin Cấu Hình</th>
                    <th>Hành Động</th>
                </tr>
            </thead>
            <tbody>
                <c:if test="${empty listDienThoai}">
                    <tr>
                        <td colspan="5" class="text-center">Không có điện thoại nào để hiển thị</td>
                    </tr>
                </c:if>
                <c:forEach var="dienThoai" items="${listDienThoai}">
                    <tr>
                        <td><c:out value="${dienThoai.maDT}" /></td>
                        <td><c:out value="${dienThoai.tenDT}" /></td>
                        <td><c:out value="${dienThoai.namSanXuat}" /></td>
                        <td><c:out value="${dienThoai.cauHinh}" /></td>
                        <td>
                            <form action="${pageContext.request.contextPath}/EditDienThoaiServlet" method="get" style="display: inline;">
                                <input type="hidden" name="maDT" value="${dienThoai.maDT}">
                                <input type="submit" value="Sửa" class="btn btn-warning">
                            </form>
                            <form action="${pageContext.request.contextPath}/QuanLyFormServlet" method="post" style="display: inline;" onsubmit="return confirm('Bạn có chắc chắn muốn xóa không?');">
                                <input type="hidden" name="maDT" value="${dienThoai.maDT}">
                                <input type="hidden" name="action" value="delete">
                                <!-- Important: specify the action -->
                                <input type="submit" value="Xóa" class="btn btn-danger">
                            </form>
                        </td>
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
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
