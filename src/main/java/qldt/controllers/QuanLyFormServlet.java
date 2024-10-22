package qldt.controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import qldt.dao.DienThoaiDAO;
import qldt.daoImpl.DienThoaiDAOImpl;
import qldt.models.DienThoai;
import qldt.utils.EntityManagerFactoryUtil;

import java.io.IOException;
import java.util.List;

@WebServlet(name ="quanlyform", urlPatterns = {"/QuanLyForm"})
public class QuanLyFormServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private EntityManagerFactoryUtil entityManagerFactory;
    private DienThoaiDAO dienThoaiDao;

    @Override
    public void init() throws ServletException {
        this.entityManagerFactory = new EntityManagerFactoryUtil();
        this.dienThoaiDao = new DienThoaiDAOImpl(entityManagerFactory.getEnManager());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
        response.setDateHeader("Expires", 0);

        // Retrieve messages and errors from session
        String message = (String) request.getSession().getAttribute("message");
        String error = (String) request.getSession().getAttribute("error");

        if (message != null) {
            request.setAttribute("message", message);
            request.getSession().removeAttribute("message");
        }

        if (error != null) {
            request.setAttribute("error", error);
            request.getSession().removeAttribute("error");
        }

        // Get search term and retrieve all phones
        String searchDT = request.getParameter("searchDT");
        List<DienThoai> listDienThoai = dienThoaiDao.findAll();

        // Filter list based on search term
        if (searchDT != null && !searchDT.isEmpty()) {
            listDienThoai = listDienThoai.stream()
                .filter(dt -> dt.getMaDT().contains(searchDT) || 
                              dt.getTenDT().contains(searchDT) ||
                              Integer.toString(dt.getNamSanXuat()).contains(searchDT))
                .toList();
        }

        request.setAttribute("listDienThoai", listDienThoai);
        String maDT = request.getParameter("maDT");

        // Load edit form if maDT is provided
        if (maDT != null && !maDT.trim().isEmpty()) {
            DienThoai dienThoai = dienThoaiDao.findById(maDT);
            if (dienThoai != null) {
                request.setAttribute("dienThoai", dienThoai);
                RequestDispatcher dispatcher = request.getRequestDispatcher("views/editForm.jsp");
                dispatcher.forward(request, response);
                return;
            } else {
                request.setAttribute("error", "Không tìm thấy sản phẩm để chỉnh sửa!");
            }
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("views/QuanLyForm.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String maDT = request.getParameter("maDT");
        String action = request.getParameter("action");

        if ("delete".equalsIgnoreCase(action)) {
            if (maDT != null && !maDT.isEmpty()) {
                boolean deleted = dienThoaiDao.delete(maDT);
                if (deleted) {
                    request.getSession().setAttribute("message", "Xóa điện thoại thành công.");
                } else {
                    request.getSession().setAttribute("error", "Không tìm thấy điện thoại với mã: " + maDT);
                }
            } else {
                request.getSession().setAttribute("error", "Mã điện thoại không hợp lệ.");
            }
        } else if ("update".equalsIgnoreCase(action)) {
            DienThoai dienThoai = new DienThoai();
            dienThoai.setMaDT(maDT);
            dienThoai.setTenDT(request.getParameter("tenDT"));
            dienThoai.setNamSanXuat(Integer.parseInt(request.getParameter("namSanXuat")));
            dienThoai.setCauHinh(request.getParameter("cauHinh"));

            if (dienThoaiDao.update(dienThoai) != null) {
                request.getSession().setAttribute("message", "Cập nhật điện thoại thành công.");
            } else {
                request.getSession().setAttribute("error", "Cập nhật điện thoại thất bại.");
            }
        }

        // Redirect to the same page to refresh the list
        response.sendRedirect("QuanLyForm");
    }

    @Override
    public void destroy() {
        entityManagerFactory.close();
        super.destroy();
    }
}
