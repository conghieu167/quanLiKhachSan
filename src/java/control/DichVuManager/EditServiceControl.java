/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package control.DichVuManager;

import dao.DAO;
import entity.DichVu;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "EditServiceControl", urlPatterns = {"/editService"})
public class EditServiceControl extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet EditServiceControl</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditServiceControl at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String maDV = request.getParameter("maDV");
    String giaTienStr = request.getParameter("giaTien");
    String chiTiet = request.getParameter("chiTiet");

    // Kiểm tra và xử lý dữ liệu nhập vào
    if (maDV != null && !maDV.isEmpty() && giaTienStr != null && !giaTienStr.isEmpty()) {
        try {
            int giaTien = Integer.parseInt(giaTienStr); // Chuyển giá sang int

            // Tạo đối tượng DichVu từ dữ liệu form
            DichVu dichVu = new DichVu(maDV, null, giaTien, chiTiet); // Tạo đối tượng dichVu

            // Gọi DAO để cập nhật thông tin dịch vụ
            DAO dao = new DAO();
            dao.editDichVu(dichVu);

            // Sau khi cập nhật thành công, chuyển hướng về trang danh sách dịch vụ
            response.sendRedirect("DichVu");
        } catch (NumberFormatException e) {
            // Nếu có lỗi khi chuyển đổi giá
            request.setAttribute("errorMessage", "Giá không hợp lệ!");
            request.getRequestDispatcher("DichVu").forward(request, response);
        }   catch (SQLException ex) {
                Logger.getLogger(EditServiceControl.class.getName()).log(Level.SEVERE, null, ex);
            }
    } else {
        // Nếu thiếu thông tin bắt buộc
        request.setAttribute("errorMessage", "Dữ liệu không hợp lệ!");
        request.getRequestDispatcher("DichVu").forward(request, response);
    }
    }
}

/**
 * Returns a short description of the servlet.
 *
 * @return a String containing servlet description
 */
