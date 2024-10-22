<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Thêm Điện Thoại</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <script>
        function validateForm() {
            const maDT = document.forms["dienThoaiForm"]["maDT"].value;
            const tenDT = document.forms["dienThoaiForm"]["tenDT"].value;
            const namSanXuat = document.forms["dienThoaiForm"]["namSanXuat"].value;
            const cauHinh = document.forms["dienThoaiForm"]["cauHinh"].value;
            const hinhAnh = document.forms["dienThoaiForm"]["hinhAnh"].value;
            const nhaCungCap = document.forms["dienThoaiForm"]["mancc"].value;

            const yearPattern = /^\d{4}$/;
            const configPattern = /^.{1,255}$/;
            const allowedExtensions = /(\.jpg|\.jpeg|\.png)$/i;

            if (!maDT || !tenDT || !namSanXuat || !cauHinh || !nhaCungCap) {
                alert("Mã ĐT, Tên điện thoại, Năm sản xuất, Cấu hình và Nhà cung cấp là bắt buộc!");
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

            if (hinhAnh && !allowedExtensions.exec(hinhAnh)) {
                alert("Vui lòng chọn một hình ảnh hợp lệ (jpg, jpeg, png).");
                return false;
            }

            return true;
        }
    </script>
</head>
<body>
    <div class="container my-4">
        <!-- Header -->
        <h1 class="my-4">Quản Lý Sản Phẩm</h1>

        <!-- Navbar -->
        <nav class="mb-4">
            <a href="dsdtncc" class="btn btn-primary">Danh Sách Sản Phẩm</a>
            <a href="DienThoaiFormServlet" class="btn btn-secondary">Thêm Mới Sản Phẩm</a>
            <a href="QuanLyFormServlet" class="btn btn-info">Chức Năng Quản Lý</a>
        </nav>

        <!-- Form Section -->
        <div class="card p-4">
            <h2>Thêm Điện Thoại</h2>

            <!-- Error Message -->
            <c:if test="${not empty error}">
                <div class="alert alert-danger">${error}</div>
            </c:if>

            <!-- Success Message -->
            <c:if test="${not empty successMessage}">
                <div class="alert alert-success">${successMessage}</div>
            </c:if>

            <form name="dienThoaiForm" action="DienThoaiFormServlet" method="post" enctype="multipart/form-data" onsubmit="return validateForm();">
                <div class="form-group">
                    <label for="maDT">Mã ĐT:</label>
                    <input type="text" id="maDT" name="maDT" class="form-control" value="${param.maDT}">
                </div>

                <div class="form-group">
                    <label for="tenDT">Tên Điện Thoại:</label>
                    <input type="text" id="tenDT" name="tenDT" class="form-control" value="${param.tenDT}">
                </div>

                <div class="form-group">
                    <label for="namSanXuat">Năm Sản Xuất:</label>
                    <input type="text" id="namSanXuat" name="namSanXuat" class="form-control" value="${param.namSanXuat}">
                </div>

                <div class="form-group">
                    <label for="cauHinh">Cấu Hình:</label>
                    <textarea id="cauHinh" name="cauHinh" class="form-control">${param.cauHinh}</textarea>
                </div>

                <div class="form-group">
                    <label for="nhaCungCap">Nhà Cung Cấp:</label>
                    <select id="nhaCungCap" name="mancc" class="form-control">
                        <option value="">-- Chọn Nhà Cung Cấp --</option>
                        <c:forEach var="nhaCungCap" items="${nhaCungCapList}">
                            <option value="${nhaCungCap.maNCC}" ${nhaCungCap.maNCC == param.mancc ? 'selected' : ''}>${nhaCungCap.tenNCC}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="form-group">
                    <label for="hinhAnh">Hình Ảnh (png, jpg, jpeg):</label>
                    <input type="file" id="hinhAnh" name="hinhAnh" accept=".png,.jpg,.jpeg" class="form-control-file">
                </div>

                <button type="submit" class="btn btn-success">Thêm</button>
            </form>
        </div>

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
