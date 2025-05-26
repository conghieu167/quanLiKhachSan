<%-- 
    Document   : HoaDon
    Created on : May 20, 2025, 12:34:46 PM
    Author     : ADMIN
--%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Thông tin người dùng</title>
        <link rel="stylesheet" href="./css/reset.css">
        <link rel="stylesheet" href="./css/CustomerStyle.css">
        <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;700;900&display=swap" rel="stylesheet">

        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    </head>
    <body>
        <div id="invoiceModal" class="modal">
            <div class="modal-content">
                <span class="close-btn" onclick="goBack()">&times;</a></span>
                <h2 style ="font-weight: bold; margin-bottom: 10px">HÓA ĐƠN THANH TOÁN</h2>

                <div class="section">
                    <h3>Thông tin hóa đơn</h3>
                    <table class="info-table">
                        <tr><td><strong>Mã Hóa Đơn:</strong></td><td>${invoice.maHoaDon}</td></tr>
                        <tr><td><strong>Mã Đặt Phòng:</strong></td><td>${invoice.maDP}</td></tr>
                        <tr><td><strong>Mã Khách Hàng:</strong></td><td>${invoice.maKH}</td></tr>
                        <tr><td><strong>Ngày Tạo:</strong></td><td>${invoice.ngayTao}</td></tr>
                        <tr><td><strong>Trạng Thái:</strong></td><td>${invoice.trangThai}</td></tr>
                    </table>
                </div>

                <div class="section">
                    <h3>Thông tin phòng</h3>
                    <table class="room-table">
                        <thead>
                            <tr>
                                <th>Mã Phòng</th>
                                <th>Loại Phòng</th>
                                <th>Giá Phòng</th>
                                <th>Tầng</th>
                                <th>Số Giường</th>
                                <th>Số ngày thuê</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>${invoice.maPhong}</td>
                                <td>${invoice.loaiPhong}</td>
                                <td>${invoice.giaPhong}</td>
                                <td>${invoice.tang}</td>
                                <td>${invoice.soGiuong}</td>
                                <td>${invoice.soNgayThue}</td>
                            </tr>
                        </tbody>
                    </table>
                </div>

                <div class="section total">
                    Tổng tiền: ${invoice.soNgayThue*invoice.giaPhong}₫ 
                </div>
            </div>
        </div>
                        <script>
  function goBack() {
    window.history.back();
  }
</script>
    </body>
</html>
