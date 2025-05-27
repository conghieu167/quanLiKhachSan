<%-- 
    Document   : UserInfo
    Created on : May 16, 2025, 4:50:56 PM
    Author     : ADMIN
--%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Thông tin người dùng</title>
        <link rel="stylesheet" href="css/reset.css">
        <link rel="stylesheet" href="css/CustomerStyle.css">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap&subset=vietnamese" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    </head>
    <body>
        <jsp:include page="NavBarKH.jsp"/>
        <div class="main-wrapper">
            <div class="user-content">
                <div class="user-information">
                    <div class="info-content">
                        <div class="user-info-box">
                            <div class="user-info-header">
                                <i class="fa-solid fa-user fa-lg"></i>
                                <h3>Thông tin người dùng</h3>
                            </div>
                            <div class="user-info-body">
                                <p><strong>Họ tên:</strong> ${user.tenKH}</p>
                            </div>
                        </div>
                        <div class="account-menu">
                            <ul>
                                <li class="active" onclick="showSection('info')">
                                    <i class="fa-solid fa-user"></i> Thông tin cá nhân
                                </li>
                                <li onclick="showSection('booking')">
                                    <i class="fa-solid fa-bed"></i> Lịch sử đặt phòng
                                </li>
                                <li onclick="showSection('services')">
                                    <i class="fa-solid fa-concierge-bell"></i> Lịch sử dịch vụ
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="user-maincontent" >
                    <div class="user-content">
                        <div id="info" class="menu-content" style="height:auto">
                            <div class="user-info" id="viewMode">
                                <h2>Thông tin cá nhân</h2>
                                <div class="info-row">
                                    <span class="label">Mã khách hàng:</span>
                                    <span class="value">${user.maKH}</span>
                                </div>
                                <div class="info-row">
                                    <span class="label">Họ và tên:</span>
                                    <span class="value">${user.tenKH}</span>
                                </div>
                                <div class="info-row">
                                    <span class="label">Số CCCD:</span>
                                    <span class="value">${user.soCCCD}</span>
                                </div>
                                <div class="info-row">
                                    <span class="label">Email:</span>
                                    <span class="value">${user.emailKH}</span>
                                </div>
                                <div class="info-row">
                                    <span class="label">Số điện thoại:</span>
                                    <span class="value">${user.soDT}</span>
                                </div>
                                <button class="edit-button" onclick="toggleEdit(true)">Chỉnh sửa thông tin</button>
                            </div>

                            <!-- Edit Mode -->
                            <form id="editForm" action="UpdateUser" method="post" style="display: none;">
                                <div class="user-info">
                                    <h2>Chỉnh sửa thông tin</h2>
                                    <div class="info-row">
                                        <span class="label">Mã khách hàng:</span>
                                        <input type="hidden" name="maKH" value="${user.maKH}" />
                                        <span class="value">${user.maKH}</span>
                                    </div>
                                    <div class="info-row">
                                        <span class="label">Họ và tên:</span>
                                        <input type="text" maxlength="50" name="tenKH" value="${user.tenKH}" class="input" required />
                                    </div>
                                    <div class="info-row">
                                        <span class="label">Số CCCD:</span>
                                        <input type="hidden" name="soCCCD" value="${user.soCCCD}" />
                                        <span class="value">${user.soCCCD}</span>
                                    </div>
                                    <div class="info-row">
                                        <span class="label">Email:</span>
                                        <input type="email" name="emailKH" value="${user.emailKH}" class="input" required />
                                    </div>
                                    <div class="info-row">
                                        <span class="label">Số điện thoại:</span>
                                        <input type="text" name="soDT" value="${user.soDT}" class="input" required />
                                    </div>
                                    <button type="submit" class="edit-btn">Lưu thay đổi</button>
                                    <button type="button" class="edit-btn" style="background-color: gray;" onclick="toggleEdit(false)">Hủy</button>
                                </div>
                            </form>
                        </div>



                        <div class="menu-content" id="booking" style="display: none">
                            <h2>Lịch sử đặt phòng</h2>
                            <c:forEach var="bookings" items="${bookings}">
                                <div class="booking-card">
                                    <div class="booking-left">
                                        <img src="img/Hotel.jpg" alt="Phòng A101" />
                                    </div>
                                    <div class="booking-right">
                                        <h2>Mã Đặt Phòng: ${bookings.maDP}</h2>
                                        <h3>Phòng: ${bookings.maPhong}</h3>

                                        <p><strong>Ngày đặt:</strong> ${bookings.ngayDat}</p>
                                        <p><strong>Ngày trả:</strong> ${bookings.ngayTra}</p>
                                        <p>
                                            <strong>Trạng thái:</strong>
                                            <span class="status confirmed">${bookings.trangThai}</span>
                                        </p>
                                        <div class="buttonss">
                                            <form method="get" action="invoice">
                                                <input type="hidden" name="maDP" value="${bookings.maDP}">
                                                <button class="edit-btn" onclick="openInvoice('${bookings.maDP}')">Xem hóa đơn</button>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>

                        <div class="menu-content" id="services" style="display: none">
                            <h2>Lịch sử dịch vụ</h2>
                            <div class="service-history">
                                <c:forEach var="sv" items="${svs}">
                                    <div class="service-card">
                                        <div class="service-left">
                                            <img style="margin-top: 1rem" src="img/${sv.maDV}.jpg" alt="Dịch vụ ${sv.tenDV}" />
                                        </div>
                                        <div class="service-right">
                                            <h3>Dịch vụ: ${sv.tenDV} </h3>
                                            <p><strong>Giá tiền:</strong> ${sv.giaTien}</p>
                                            <p><strong>Ngày đặt:</strong> ${sv.ngayDat}</p>
                                            <p>
                                                <strong>Trạng thái:</strong>
                                                <span class="status confirmed">Đã hoàn thành</span>
                                            </p>


                                        </div>
                                    </div>   
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="footer.jsp"/>
        <script src="js/UserInfor.js"></script>
    </body>
</html>
