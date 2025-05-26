/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package control.ThongKe;

import dao.TKDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "ThongKeControl", urlPatterns = {"/ThongKe"})
public class ThongKeControl extends HttpServlet {

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
        Map<String, Integer> roomTypeStats = new LinkedHashMap<>();
        // Gọi DAO
        TKDAO dao = new TKDAO();

        // Liệt kê các loại phòng (nếu bạn có bảng riêng, có thể lấy từ DB)
        String[] loaiPhongs = {"Standard", "Deluxe", "Suite"};

        for (String loai : loaiPhongs) {
            int soLuong = dao.getSoPhong(loai);
            roomTypeStats.put(loai, soLuong);
        }
        request.setAttribute("roomTypeStats", roomTypeStats);
        
        Map<String, Double> revenueByDate = dao.getRevenueByDate();
        request.setAttribute("revenueByDate", revenueByDate);
        
        Map<String, Double> occupancyMap = dao.getOccupancyRateInMay();
        request.setAttribute("occupancyMap", occupancyMap);
        
        int soTongPhong = dao.getCount("Phong");
        request.setAttribute("soPhong", soTongPhong);
        
        int soKhach = dao.getCount("KhachHang");
        request.setAttribute("soKhach", soKhach);
        
        int soDonDat = dao.getCount("DatPhong");
        request.setAttribute("soDonDat", soDonDat);
        
        int tongDoanhThu = dao.getDoanhThu();
        request.setAttribute("doanhThu",tongDoanhThu);
        request.getRequestDispatcher("ThongKe.jsp").forward(request, response);
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
