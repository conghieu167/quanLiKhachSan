package control;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet("/logout")
public class LogOutControl extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Hủy session đăng nhập
        HttpSession session = request.getSession(false); // tránh tạo session mới nếu chưa có
        if (session != null) {
            session.invalidate();
        }

        // Chuyển hướng về trang login
        response.sendRedirect("login.jsp");
    }
}
