/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package control.NhanVien;

import dao.DAO;
import entity.nhanVien;
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
@WebServlet(name = "EditControl", urlPatterns = {"/edit"})
public class EditControl extends HttpServlet {

    @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    String action = request.getParameter("action");

    if ("update".equals(action)) {
        String maNV = request.getParameter("maNV");
        String hoTenNV = request.getParameter("hoTenNV");
        String chucVu = request.getParameter("chucVu");
        String maQL = request.getParameter("maQL");
        String sdtnv = request.getParameter("sdtnv");
        String emailNV = request.getParameter("emailNV");
        double luong = Double.parseDouble(request.getParameter("luong"));

        DAO dao = new DAO();
        try {
            dao.editNhanVien(maNV, hoTenNV, chucVu, maQL, sdtnv, emailNV, luong);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Sau khi cập nhật, chuyển hướng về trang danh sách
        response.sendRedirect("nhanvien");
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
