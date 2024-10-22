package qldt.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import qldt.dao.DienThoaiDAO;
import qldt.daoImpl.DienThoaiDAOImpl;
import qldt.models.DienThoai;
import qldt.utils.EntityManagerFactoryUtil;

import java.io.IOException;

public class EditDienThoaiServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private EntityManagerFactoryUtil entityManagerFactory;
    private DienThoaiDAO dienThoaiDao;

    @Override
    public void init() throws ServletException {
        this.entityManagerFactory = new EntityManagerFactoryUtil();
        this.dienThoaiDao = new DienThoaiDAOImpl(entityManagerFactory.getEnManager());
    }

    // Handle GET request to load edit form with data
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String maDT = request.getParameter("maDT");

        if (maDT == null || maDT.trim().isEmpty()) {
            request.setAttribute("error", "Mã điện thoại không hợp lệ!");
            request.getRequestDispatcher("QuanLyForm").forward(request, response);
            return;
        }

        DienThoai dienThoai = dienThoaiDao.findById(maDT);
        if (dienThoai != null) {
            request.setAttribute("dienThoai", dienThoai);
            request.getRequestDispatcher("views/editForm.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "Không tìm thấy sản phẩm để chỉnh sửa!");
            request.getRequestDispatcher("QuanLyForm").forward(request, response);
        }
    }

    // Handle POST request to update phone information
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String maDT = request.getParameter("maDT");

        if (maDT == null || maDT.trim().isEmpty()) {
            request.getSession().setAttribute("error", "Mã điện thoại không hợp lệ!");
            response.sendRedirect("QuanLyForm");  // Redirect to QuanLyForm
            return;
        }

        String tenDT = request.getParameter("tenDT");
        int namSanXuat = Integer.parseInt(request.getParameter("namSanXuat"));
        String cauHinh = request.getParameter("cauHinh");

        DienThoai existingDienThoai = dienThoaiDao.findById(maDT);
        if (existingDienThoai != null) {
            existingDienThoai.setTenDT(tenDT);
            existingDienThoai.setNamSanXuat(namSanXuat);
            existingDienThoai.setCauHinh(cauHinh);

            if (dienThoaiDao.update(existingDienThoai) != null) {
                request.getSession().setAttribute("message", "Cập nhật thành công!");
            } else {
                request.getSession().setAttribute("error", "Cập nhật không thành công!");
            }
        } else {
            request.getSession().setAttribute("error", "Không tìm thấy sản phẩm để cập nhật!");
        }

        // Redirect back to QuanLyForm to refresh the list and display the message
        response.sendRedirect("QuanLyForm");
    }

    @Override
    public void destroy() {
        entityManagerFactory.close();
        super.destroy();
    }
}
