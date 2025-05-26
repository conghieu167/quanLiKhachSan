/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package control.Booking;

import dao.BookingDAO;
import dao.DAO;
import dao.UserDAO;
import entity.DatPhong;
import entity.Phong;
import entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "BookingFormControl", urlPatterns = {"/BookingForm"})
public class BookingFormControl extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher("BookingForm.jsp").forward(request, response);
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
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String maKH = user.getMaKH();
        String maPhong = request.getParameter("maPhong");
        String checkin = request.getParameter("checkin");
        String checkout = request.getParameter("checkout");

        Phong phong = new DAO().getPhongByMa(maPhong);

        request.setAttribute("maKH", maKH);
        request.setAttribute("phong", phong);
        request.setAttribute("checkin", checkin);
        request.setAttribute("checkout", checkout);
        request.getRequestDispatcher("BookingForm.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        String maKH = user.getMaKH();

        // Lấy dữ liệu từ form
        String maPhong = request.getParameter("maPhong");
        String checkinStr = request.getParameter("checkin");
        String checkoutStr = request.getParameter("checkout");

        // Kiểm tra dữ liệu
        if (maPhong == null || maKH == null || checkinStr == null || checkoutStr == null) {
            response.sendRedirect("error.jsp");
            return;
        }

        try {
            LocalDate checkin = LocalDate.parse(checkinStr);
            LocalDate checkout = LocalDate.parse(checkoutStr);

            // Kiểm tra logic ngày
            if (checkout.isBefore(checkin)) {
                request.setAttribute("error", "Ngày trả phải sau ngày đặt!");
                request.getRequestDispatcher("bookingform.jsp").forward(request, response);
                return;
            }

            // Tạo đối tượng DatPhong
            DatPhong dp = new DatPhong();
            dp.setMaPhong(maPhong);
            dp.setMaKH(maKH);
            dp.setNgayDat(checkin);
            dp.setNgayTra(checkout);
            dp.setTrangThai("Đã đặt");

            // Thêm vào DB
            BookingDAO dao = new BookingDAO();
            boolean success = dao.addBooking(dp);
            if (success) {
                request.setAttribute("message", "Đặt phòng thành công!");
            } else {
                request.setAttribute("error", "Đặt phòng thất bại!");
            }
            request.getRequestDispatcher("booking").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
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
