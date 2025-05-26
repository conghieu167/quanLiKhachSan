/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package control.Phong;

import dao.DAO;
import entity.Phong;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "AddRoomControl", urlPatterns = {"/addRoom"})
public class AddRoomControl extends HttpServlet {

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
        request.getRequestDispatcher("addRoom.jsp").forward(request, response);
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
         String maPhong = request.getParameter("maPhong");
        String loaiPhong = request.getParameter("loaiPhong");
        String trangThai = request.getParameter("trangThai");
        String giaPhongStr = request.getParameter("giaPhong");
        String tangStr = request.getParameter("tang");
        String dienTichStr = request.getParameter("dienTich");
        String soGiuongStr = request.getParameter("soGiuong");
        String moTa = request.getParameter("moTa");

        // Chuyển đổi dữ liệu sang kiểu phù hợp
        double giaPhong = Double.parseDouble(giaPhongStr);
        int tang = Integer.parseInt(tangStr);
        double dienTich = Double.parseDouble(dienTichStr);
        int soGiuong = Integer.parseInt(soGiuongStr);

        // Tạo đối tượng Phong
        Phong p = new Phong(maPhong, loaiPhong, trangThai, giaPhong, tang, dienTich, soGiuong, moTa);

        // Thêm phòng vào cơ sở dữ liệu thông qua DAO
        DAO dao = new DAO();
        dao.addPhong(p);

        // Sau khi thêm phòng thành công, chuyển hướng về danh sách phòng
        response.sendRedirect("phong"); // Chuyển hướng về trang phòng
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
