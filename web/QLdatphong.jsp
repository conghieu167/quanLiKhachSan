<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
<html lang="vi">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>L'hotel Manager</title>
        <link rel="stylesheet" href="css/style.css" />
        <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css"
            />
        <link rel="preconnect" href="https://fonts.googleapis.com" />
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
        <link
            href="https://fonts.googleapis.com/css2?family=Inter:ital,opsz,wght@0,14..32,100..900;1,14..32,100..900&display=swap"
            rel="stylesheet"
            />
    </head>
    <body>
        <div class="wrapper">        
            <jsp:include page="NavBarQL.jsp"/>
            <div class="container">
                <jsp:include page="sidebar.jsp"/>
                <div class="main-content-QLDonDatPhong">
                    <h1 class="title-QLDonDatPhong">Các đơn đặt phòng</h1>

                    <div class="search-bar-QLDonDatPhong">
                        <form style="display:flex" action="SearchDP" method="get">
                            <div class="search-bar-QLDonDatPhong-left">
                                <input type="text" name="keyword" placeholder="Nhập Mã Đơn" value="${param.keyword}">

                                <input type="date" name="checkin" placeholder="Ngày đặt phòng" value="${param.checkin}">


                                <div class="loctheotrangthai">
                                    <label for="status-filter">Trạng thái:</label>
                                    <select id="status-filter" name="status">
                                        <option value="" ${empty param.status ? 'selected' : ''}>Tất cả</option>
                                        <option value="Đã đặt" ${param.status == 'Đã đặt' ? 'selected' : ''}>Đã đặt</option>
                                        <option value="Đã hủy" ${param.status == 'Đã hủy' ? 'selected' : ''}>Đã hủy</option>
                                        <option value="Đã trả phòng" ${param.status == 'Đã trả phòng' ? 'selected' : ''}>Đã trả phòng</option>
                                        <option value="Đã nhận phòng" ${param.status == 'Đã nhận phòng' ? 'selected' : ''}>Đã nhận phòng</option>
                                    </select>
                                </div>


                            </div>

                            <div class="search-bar-QLDonDatPhong-right">
                                <button type="submit" class="QLDonDatPhong-submit-button">
                                    Tìm kiếm
                                </button>
                            </div>
                        </form>
                    </div>

                    <div class="booking-list">        
                        <c:forEach var="booking" items="${bookings}">
                            <div class="booking-card">
                                <div class="booking-card-info">
                                    <h2 class="booking-card-info-title">Mã Đơn:<p class="sub-title">${booking.maDP}</p>
                                    </h2>
                                    <p><strong>Mã Phòng:</strong> ${booking.maPhong}</p>
                                    <p><strong>Mã KH:</strong> ${booking.maKH}</p>
                                    <div class="date">
                                        <p><strong>Ngày nhận:</strong> ${booking.ngayDat}0</p>
                                        <p><strong>Ngày trả:</strong>${booking.ngayTra}</p>
                                    </div>
                                    <p class="trangthai"><strong>Trạng thái:</strong> ${booking.trangThai}</p>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>

    </body>
</html>
