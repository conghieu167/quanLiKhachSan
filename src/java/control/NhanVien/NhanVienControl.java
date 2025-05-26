package control.NhanVien;

import dao.DAO;
import entity.nhanVien;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "NhanVienControl", urlPatterns = {"/NhanVienControl"})
public class NhanVienControl extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Đảm bảo sử dụng UTF-8 cho dữ liệu nhận từ form
        request.setCharacterEncoding("UTF-8");

        // Lấy các tham số từ form
        String maNV = request.getParameter("maNV");
        String hoTen = request.getParameter("hoTenNV");
        String chucVu = request.getParameter("chucVu");
        String maQL = request.getParameter("maQL");
        String sdtnv = request.getParameter("sdtnv");
        String email = request.getParameter("emailNV");
        String luongRaw = request.getParameter("luong");

        // Kiểm tra nếu các tham số không rỗng
        if (maNV == null || hoTen == null || chucVu == null || maQL == null || sdtnv == null || email == null || luongRaw == null) {
            request.setAttribute("error", "Thông tin không đầy đủ. Vui lòng nhập lại.");
            request.getRequestDispatcher("form.jsp").forward(request, response); // Chuyển đến form để người dùng nhập lại
            return;
        }

        // In ra các giá trị nhận được từ form (để debug)
        System.out.println("Mã NV: " + maNV);
        System.out.println("Họ tên: " + hoTen);
        System.out.println("Chức vụ: " + chucVu);
        System.out.println("Mã QL: " + maQL);
        System.out.println("SĐT: " + sdtnv);
        System.out.println("Email: " + email);
    

        // Chuyển giá trị lương từ String sang double và kiểm tra lỗi
        double luong = 0.0; // Giá trị mặc định nếu không có lỗi
        if (luongRaw != null && !luongRaw.trim().isEmpty()) {
            try {
                luong = Double.parseDouble(luongRaw.trim());
            } catch (NumberFormatException e) {
                request.setAttribute("error", "Lương không hợp lệ. Vui lòng nhập lại.");
                request.getRequestDispatcher("form.jsp").forward(request, response); // Quay lại form
                return;
            }
        }

        // Tạo đối tượng nhanVien và thêm vào cơ sở dữ liệu
        nhanVien nv = new nhanVien(maNV, hoTen, chucVu, maQL, sdtnv, email, luong);

        DAO dao = new DAO();
        System.out.print(nv);
        dao.insertNhanVien(nv);

        // Nếu chèn dữ liệu không thành công
        

        // Chuyển hướng về trang danh sách nhân viên sau khi thêm thành công
        response.sendRedirect("nhanvien");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Nếu có yêu cầu GET, trả về thông báo
        response.getWriter().println("GET not supported for this servlet.");
    }

    @Override
    public String getServletInfo() {
        return "Servlet dùng để thêm nhân viên vào CSDL";
    }
}
