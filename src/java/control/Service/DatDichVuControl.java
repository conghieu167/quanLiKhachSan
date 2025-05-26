/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package control.Service;

import dao.ServiceDAO;
import entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "DatDichVuControl", urlPatterns = {"/DatDichVu"})
public class DatDichVuControl extends HttpServlet {

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
            out.println("<title>Servlet DatDichVuControl</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DatDichVuControl at " + request.getContextPath() + "</h1>");
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
        User user = (User) request.getSession().getAttribute("user");
        String maKH = user.getMaKH();
        String maDV = request.getParameter("maDV");

        if (maKH == null || maDV == null) {
            response.sendRedirect("login.jsp"); // hoặc trang lỗi
            return;
        }

        try {
            // Ngày đặt là ngày hiện tại
            LocalDate ngayDat = LocalDate.now();
            ServiceDAO serviceDAO = new ServiceDAO();
            boolean success = serviceDAO.datDichVu(maKH, maDV, ngayDat);
            if (success) {
                // Đặt dịch vụ thành công, gửi thông báo
                request.setAttribute("message", "Đặt dịch vụ thành công!");
            } else {
                request.setAttribute("message", "Đặt dịch vụ thất bại, vui lòng thử lại.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "Lỗi hệ thống: " + e.getMessage());
        }

        // Quay lại trang danh sách dịch vụ và hiển thị thông báo
        request.getRequestDispatcher("DichVuKH").forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
