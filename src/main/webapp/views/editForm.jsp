<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Chỉnh Sửa Thông Tin Điện Thoại</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <script>
        function validateForm() {
            const tenDT = document.forms["editDienThoaiForm"]["tenDT"].value;
            const namSanXuat = document.forms["editDienThoaiForm"]["namSanXuat"].value;
            const cauHinh = document.forms["editDienThoaiForm"]["cauHinh"].value;

            const yearPattern = /^\d{4}$/;
            const configPattern = /^.{1,255}$/;

            if (!tenDT || !namSanXuat || !cauHinh) {
                alert("Tên điện thoại, Năm sản xuất và Cấu hình là bắt buộc!");
                return false;
            }

            if (!yearPattern.test(namSanXuat)) {
                alert("Năm sản xuất phải là số nguyên 4 chữ số!");
                return false;
            }

            if (!configPattern.test(cauHinh)) {
                alert("Cấu hình không được quá 255 ký tự!");
                return false;
            }

            return true;
        }
    </script>
</head>
<body>
    <div class="container my-4">
        <h1 class="my-4">Chỉnh Sửa Thông Tin Điện Thoại</h1>

        <!-- Display messages -->
        <c:if test="${not empty message}">
            <div class="alert alert-success">${message}</div>
        </c:if>
        <c:if test="${not empty error}">
            <div class="alert alert-danger">${error}</div>
        </c:if>

        <!-- Edit Form -->
        <form name="editDienThoaiForm" action="${pageContext.request.contextPath}/EditDienThoaiServlet" method="post" onsubmit="return validateForm();">
            <div class="form-group">
                <label for="maDT">Mã Điện Thoại</label>
                <input type="text" id="maDT" name="maDT" class="form-control" value="${dienThoai.maDT}" readonly />
            </div>

            <div class="form-group">
                <label for="tenDT">Tên Điện Thoại</label>
                <input type="text" id="tenDT" name="tenDT" class="form-control" value="${dienThoai.tenDT}" required />
            </div>

            <div class="form-group">
                <label for="namSanXuat">Năm Sản Xuất</label>
                <input type="text" id="namSanXuat" name="namSanXuat" class="form-control" value="${dienThoai.namSanXuat}" required />
            </div>

            <div class="form-group">
                <label for="cauHinh">Cấu Hình</label>
                <textarea id="cauHinh" name="cauHinh" class="form-control" required>${dienThoai.cauHinh}</textarea>
            </div>

            <button type="submit" class="btn btn-primary">Cập Nhật</button>
            <a href="${pageContext.request.contextPath}/QuanLyFormServlet" class="btn btn-secondary">Quay Lại</a>
        </form>
    </div>
</body>
</html>
