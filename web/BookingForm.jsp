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
        <title>Thông tin đặt phòng</title>
        <link rel="stylesheet" href="./css/CustomerStyle.css">
        <link rel="stylesheet" href="./css/reset.css">
    </head>
    <body>
        <jsp:include page ="NavBarKH.jsp"/>
        <div class="chen"></div>
        <div class="checkout-container">
            <div class="left-column-bookingform">
                <h2>Thông tin đặt phòng</h2>
                <div class="booking-card">
                    <div class="booking-img">
                        <img src="./img/jakub-dziubak-gj7BLlSzIFs-unsplash.jpg" alt="Hình phòng khách sạn">
                    </div>
                    <div class="booking-info">
                        <h2><strong>Mã KH:</strong> ${maKH}</h2>
                        <h3 class="room-id">${phong.maPhong}</h3>
                        <p><strong>Loại:</strong> ${phong.loaiPhong}</p>
                        <p><strong>Tầng:</strong> ${phong.tang}</p>
                        <div class="roominfo">
                            <div class="roominfo-item">
                                <i class="fa-solid fa-cube"></i>
                                <p><strong>Diện tích:</strong> ${phong.dienTich}m²</p>

                            </div>
                            <div class="roominfo-item">
                                <i class="fa-solid fa-bed"></i>
                                <p><strong>Số giường:</strong> ${phong.soGiuong}</p>

                            </div>
                            <div class="roominfo-item">
                                <i class="fa-solid fa-newspaper"></i>
                                <p><strong>Mô tả:</strong> ${phong.moTa}</p>

                            </div>
                        </div>
                    </div>
                </div>
                <form action="BookingForm" method="post">
                    <input type="hidden" name="maPhong" value="${phong.maPhong}">
                    <input type="hidden" name="checkin" value="${checkin}" onchange="updateTotalPrice()">
                    <input type="hidden" name="checkout" value="${checkout}" onchange="updateTotalPrice()">
                    <label>Ngày đặt</label>
                    <input id= "checkin" type="date" name="checkin" required value="${checkin != null ? checkin : ''}" />
                    <label>Ngày trả</label>
                    <input id ="checkout" type="date" name="checkout" required value="${checkout != null ? checkout : ''}" />
                    <button type="submit" >Xác nhận đặt phòng</button>
                </form>
            </div>
            <div class="right-column-bookingform">
                <h3>Chi tiết thanh toán</h3>
                <ul class="summary">
                    <li><span>Tổng tiền phòng</span><span id="tienPhong">-</span></li>
                    <li class="total-bookingform"><span>Tổng thanh toán</span><span id="tongTien">-</span></li>
                </ul>
            </div>
        </div>
                    <jsp:include page="footer.jsp"/>
        <script>
            document.addEventListener("DOMContentLoaded", function () {
                const checkinInput = document.querySelector("#checkin");
                const checkoutInput = document.querySelector("#checkout");
                const pricePerDay = Number("${phong.giaPhong}");
                const tongTienDiv = document.getElementById("tongTien");
                function updateTotalPrice() {
                    const checkin = new Date(checkinInput.value);
                    const checkout = new Date(checkoutInput.value);

                    if (!isNaN(checkin.getTime()) && !isNaN(checkout.getTime()) && checkout > checkin) {
                        const days = Math.floor((checkout - checkin) / (1000 * 60 * 60 * 24));
                        const total = days * pricePerDay;
                        tongTienDiv.textContent = "Tổng tiền: " + total.toLocaleString('vi-VN') + " VND (" + days + " ngày)";
                    } else {
                        tongTienDiv.textContent = "";
                    }
                }

                checkinInput.addEventListener("input", updateTotalPrice);
                checkoutInput.addEventListener("input", updateTotalPrice);

                checkinInput.addEventListener("click", updateTotalPrice);
                checkoutInput.addEventListener("click", updateTotalPrice);
                checkinInput.addEventListener("change", updateTotalPrice);
                checkoutInput.addEventListener("change", updateTotalPrice);
                updateTotalPrice();
            });
        </script>
    </body>
</html>
