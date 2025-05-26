<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // Không cho trình duyệt lưu cache
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
    response.setHeader("Pragma", "no-cache"); // HTTP 1.0
    response.setDateHeader("Expires", 0); // Proxies
%>

<%
    if (session == null || session.getAttribute("TaiKhoan") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Thêm Phòng Mới</title>
    </head>
    <body>
        <div class="main-content">
            <h2>Thêm phòng mới</h2>
            <form class="add-room-form" action="/addRoom" method="post">
                <div class="form-group">
                    <label for="maPhong">Mã phòng:</label>
                    <input type="text" id="maPhong" name="maPhong" required>
                </div>

                <div class="form-group">
                    <label for="loaiPhong">Loại phòng:</label>
                    <select id="loaiPhong" name="loaiPhong" required>
                        <option value="Standard">Standard</option>
                        <option value="Deluxe">Deluxe</option>
                        <option value="Suite">Suite</option>
                    </select>
                </div>

                <div class="form-group">
                    <label for="trangThai">Trạng thái:</label>
                    <select id="trangThai" name="trangThai" required>
                        <option value="Sẵn sàng sử dụng">Sẵn sàng sử dụng</option>
                        <option value="Bảo trì">Bảo trì</option>
                    </select>
                </div>

                <div class="form-group">
                    <label for="giaPhong">Giá phòng (VNĐ):</label>
                    <input type="number" id="giaPhong" name="giaPhong" required>
                </div>

                <div class="form-group">
                    <label for="tang">Tầng:</label>
                    <input type="number" id="tang" name="tang" required>
                </div>

                <div class="form-group">
                    <label for="dienTich">Diện tích (m²):</label>
                    <input type="number" id="dienTich" name="dienTich" required>
                </div>

                <div class="form-group">
                    <label for="soGiuong">Số giường:</label>
                    <input type="number" id="soGiuong" name="soGiuong" required>
                </div>

                <div class="form-group">
                    <label for="moTa">Mô tả:</label>
                    <textarea id="moTa" name="moTa" rows="4" placeholder="VD: Phòng yên tĩnh, đầy đủ tiện nghi..."></textarea>
                </div>

                <button type="submit" class="btn-add">Thêm phòng</button>
            </form>
        </div>

    </body>
</html>
