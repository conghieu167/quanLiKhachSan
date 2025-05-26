/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package control.Booking;

import dao.SearchDAO;
import entity.Phong;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "SearchRoomControl", urlPatterns = {"/SearchRoom"})
public class SearchRoomControl extends HttpServlet {

    private SearchDAO searchDAO = new SearchDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SearchRoomControl</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SearchRoomControl at " + request.getContextPath() + "</h1>");
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

        String keyword = request.getParameter("keyword");
        String checkin = request.getParameter("checkin");
        String checkout = request.getParameter("checkout");
        String soGiuongStr = request.getParameter("soGiuong");
        String tangStr = request.getParameter("tang");
        String loaiPhong = request.getParameter("loaiPhong");

        int soGiuong = 0;
        int tang = 0;

        try {
            if (soGiuongStr != null && !soGiuongStr.isEmpty()) {
                soGiuong = Integer.parseInt(soGiuongStr);
            }
        } catch (NumberFormatException e) {
            soGiuong = 0;
        }

        try {
            if (tangStr != null && !tangStr.isEmpty()) {
                tang = Integer.parseInt(tangStr);
            }
        } catch (NumberFormatException e) {
            tang = 0;
        }

// Kiểm tra ngày
        if (checkin == null || checkin.isEmpty() || checkout == null || checkout.isEmpty()) {
            request.setAttribute("error", "Vui lòng nhập đầy đủ ngày nhận và trả phòng.");
            request.getRequestDispatcher("booking.jsp").forward(request, response);
            return;
        }

// Gọi DAO với đầy đủ tiêu chí
        SearchDAO searchDAO = new SearchDAO();
        List<Phong> listPhong = searchDAO.searchRooms(keyword, checkin, checkout, soGiuong, tang, loaiPhong);

// Truyền lại dữ liệu về JSP để giữ lại form tìm kiếm
        request.setAttribute("listPhong", listPhong);
        request.setAttribute("keyword", keyword);
        request.setAttribute("checkin", checkin);
        request.setAttribute("checkout", checkout);
        request.setAttribute("soGiuong", soGiuong);
        request.setAttribute("tang", tang);
        request.setAttribute("loaiPhong", loaiPhong);

        request.getRequestDispatcher("booking.jsp").forward(request, response);

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
        processRequest(request, response);
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
