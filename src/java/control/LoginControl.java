package control;

import dao.QuanLyDAO;
import dao.UserDAO;
import entity.QuanLy;
import entity.User;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginControl extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("TaiKhoan");
        String password = request.getParameter("MatKhau");

        UserDAO dao = new UserDAO();
        String role = dao.checkLogin(username, password); // trả về "Nhân Viên", "Khách hàng", hoặc null

        if (role != null) {
            HttpSession session = request.getSession();
            session.setAttribute("TaiKhoan", username);
            session.setAttribute("role", role);
            UserDAO userDAO = new UserDAO();

            switch (role) {
                case "Quản lý" -> {
                    QuanLyDAO qlDAO = new QuanLyDAO();
                    QuanLy ql = qlDAO.getQuanLyByUsername(username);
                    session.setAttribute("user", ql);
                    response.sendRedirect("ThongKe");
                }
                case "Khách hàng" -> {
                    User user = userDAO.getUserByTaiKhoan(username);
                    session.setAttribute("user", user);
                    session.setAttribute("username", user.getUsername());
                    response.sendRedirect("dashboardKH");
                }
                default -> {
                    request.setAttribute("errorMessage", "Role không hợp lệ.");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }
            }
        } else {
            request.setAttribute("errorMessage", "Sai tên đăng nhập hoặc mật khẩu.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
}
