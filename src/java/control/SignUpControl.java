package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import context.DBContext;
import dao.DAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/signup")
public class SignUpControl extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String tenKH = request.getParameter("TenKH");
        String taikhoan = request.getParameter("TaiKhoan");
        String email = request.getParameter("Email");
        String soDT = request.getParameter("phone-number");
        String soCCCD = request.getParameter("SoCCCD");
        String matKhau = request.getParameter("MatKhau");

        try (java.sql.Connection conn = new DBContext().getConnection()) {
            // Kiểm tra xem username đã tồn tại hay chưa
            if (DAO.isExist(taikhoan)) {
                request.setAttribute("error", "Tài khoản đã tồn tại!");
                request.getRequestDispatcher("DangKy.jsp").forward(request, response);
                return;
            }
            // 1. Sinh mã khách hàng mới
            String maKH = taoMaKhachHangMoi((Connection) conn);

            DAO dao = new DAO();
            dao.insertKhachHang(maKH, tenKH, soCCCD, email, soDT);
            dao.insertTaiKhoanKH(maKH, taikhoan, matKhau);
            // 4. Chuyển hướng đến trang đăng nhập
            response.sendRedirect("login.jsp?success=true");

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Đăng ký thất bại: " + e.getMessage());
            request.getRequestDispatcher("DangKy.jsp").forward(request, response);
        }
    }

    private String taoMaKhachHangMoi(Connection conn) throws SQLException {
        String sql = "SELECT MAX(MaKH) FROM KhachHang";
        try (PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            String maKH = "KH001";
            if (rs.next() && rs.getString(1) != null) {
                String maxMa = rs.getString(1); // VD: KH005
                int num = Integer.parseInt(maxMa.substring(2)) + 1;
                maKH = String.format("KH%03d", num); // KH006
            }
            return maKH;
        }
    }

}
