package qldt.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import qldt.dao.DienThoaiDAO;
import qldt.dao.NhaCungCapDAO;
import qldt.daoImpl.DienThoaiDAOImpl;
import qldt.daoImpl.NhaCungCapDAOImpl;
import qldt.models.DienThoai;
import qldt.models.NhaCungCap;
import qldt.utils.EntityManagerFactoryUtil;

import java.io.File;
import java.io.IOException;
import java.util.List;

@MultipartConfig
public class DienThoaiFormServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private EntityManagerFactoryUtil entityManagerFactory;
    private DienThoaiDAO dienThoaiDao;
    private NhaCungCapDAO nhaCungCapDao;

    @Override
    public void init() throws ServletException {
        // Initialize EntityManagerFactory and DAO
        this.entityManagerFactory = new EntityManagerFactoryUtil();
        this.dienThoaiDao = new DienThoaiDAOImpl(entityManagerFactory.getEnManager());
        this.nhaCungCapDao = new NhaCungCapDAOImpl(entityManagerFactory.getEnManager());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the list of NhaCungCap entities from the database
        List<NhaCungCap> nhaCungCapList = nhaCungCapDao.findAll();

        // Set the list as a request attribute
        request.setAttribute("nhaCungCapList", nhaCungCapList);

        // Display the form to add a new phone
        request.getRequestDispatcher("views/DienThoaiForm.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve parameters from the form
        String maDT = request.getParameter("maDT");
        String tenDT = request.getParameter("tenDT");
        String namSanXuatStr = request.getParameter("namSanXuat");
        String cauHinh = request.getParameter("cauHinh");
        String mancc = request.getParameter("mancc"); 

        // Validate input data
        String errorMessage = validateInput(maDT, tenDT, namSanXuatStr, cauHinh);
        if (errorMessage != null) {
            request.setAttribute("error", errorMessage);
            request.getRequestDispatcher("views/DienThoaiForm.jsp").forward(request, response);
            return;
        }

        // Handle file upload
        String hinhAnh = handleFileUpload(request);

        // Retrieve NhaCungCap entity based on the provided ID
        NhaCungCap nhaCungCap = nhaCungCapDao.findById(mancc);

        // Create a new DienThoai object and save it to the database
        DienThoai dienThoai = new DienThoai(maDT, tenDT, Integer.parseInt(namSanXuatStr), cauHinh, nhaCungCap, hinhAnh);
        dienThoaiDao.save(dienThoai);

        // Redirect to the servlet to load the updated list of phones
        response.sendRedirect("dsdtncc");
    }


    // Validate the input data
    private String validateInput(String maDT, String tenDT, String namSanXuatStr, String cauHinh) {
        if (maDT == null || tenDT == null || namSanXuatStr == null || cauHinh == null ||
                maDT.isEmpty() || tenDT.isEmpty() || namSanXuatStr.isEmpty() || cauHinh.isEmpty()) {
            return "Các trường không được để trống.";
        }

        if (cauHinh.length() > 255) {
            return "Cấu hình không được quá 255 ký tự.";
        }

        if (!namSanXuatStr.matches("\\d{4}")) {
            return "Năm sản xuất phải là số nguyên 4 chữ số.";
        }

        return null; // No errors
    }

    // Handle file upload
    private String handleFileUpload(HttpServletRequest request) throws IOException, ServletException {
        Part filePart = request.getPart("hinhAnh"); // Get the file part
        String fileName = "";

        if (filePart != null && filePart.getSize() > 0) {
            fileName = filePart.getSubmittedFileName(); // Get the file name

            // Define upload directory
            String uploadDirPath = getServletContext().getRealPath("/uploads");

            // Create directory if it does not exist
            File uploadDir = new File(uploadDirPath);
            if (!uploadDir.exists() && !uploadDir.mkdirs()) {
                throw new IOException("Cannot create upload directory: " + uploadDirPath);
            }

            // Save the file
            String savePath = uploadDirPath + File.separator + fileName;
            filePart.write(savePath); // Save the file
        }

        return fileName; // Return the uploaded file name
    }

    @Override
    public void destroy() {
        entityManagerFactory.close(); // Close EntityManagerFactory
        super.destroy();
    }
}
