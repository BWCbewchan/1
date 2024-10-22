package qldt.controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import qldt.dao.DienThoaiDAO;
import qldt.dao.NhaCungCapDAO;
import qldt.daoImpl.DienThoaiDAOImpl;
import qldt.daoImpl.NhaCungCapDAOImpl;
import qldt.models.DienThoai;
import qldt.models.NhaCungCap;
import qldt.utils.EntityManagerFactoryUtil;

import java.io.IOException;
import java.util.List;

public class DanhSachDienThoaiNCCServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private EntityManagerFactoryUtil entityManagerFactory;
    private DienThoaiDAO dienThoaiDao;
    private NhaCungCapDAO nhaCungCapDao;

    public DanhSachDienThoaiNCCServlet() {
        super();
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.entityManagerFactory = new EntityManagerFactoryUtil();
        this.dienThoaiDao = new DienThoaiDAOImpl(this.entityManagerFactory.getEnManager());
        this.nhaCungCapDao = new NhaCungCapDAOImpl(this.entityManagerFactory.getEnManager());
    }

    @Override
    public void destroy() {
        this.entityManagerFactory.close();
        super.destroy();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchNCC = request.getParameter("searchNCC");
        String searchDT = request.getParameter("searchDT");

        // Danh sách nhà cung cấp và điện thoại
        List<NhaCungCap> listNCC;
        List<DienThoai> listDienThoai = dienThoaiDao.findAll();

        // Tìm kiếm nhà cung cấp nếu có searchNCC
        if (searchNCC != null && !searchNCC.isEmpty()) {
            listNCC = nhaCungCapDao.findAll().stream()
                .filter(ncc -> ncc.getMaNCC().contains(searchNCC) ||
                               ncc.getTenNCC().contains(searchNCC) ||
                               ncc.getDiaChi().contains(searchNCC) ||
                               ncc.getSoDienThoai().contains(searchNCC))
                .toList();
        } else {
            listNCC = nhaCungCapDao.findAll();
        }

        // Tìm kiếm điện thoại nếu có searchDT
        if (searchDT != null && !searchDT.isEmpty()) {
            listDienThoai = listDienThoai.stream()
                .filter(dt -> dt.getMaDT().contains(searchDT) || 
                              dt.getTenDT().contains(searchDT) ||
                              Integer.toString(dt.getNamSanXuat()).contains(searchDT) || 
                              (dt.getNhaCungCap() != null && dt.getNhaCungCap().getMaNCC().contains(searchDT)))
                .toList();
        }

        // Set dữ liệu lên request
        request.setAttribute("listNCC", listNCC);
        request.setAttribute("listDienThoai", listDienThoai);

        // Forward dữ liệu tới JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("views/DanhSachDienThoaiNCC.jsp");
        dispatcher.forward(request, response);
    }




}
