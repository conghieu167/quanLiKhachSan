<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %> <%@
taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
        <link rel="stylesheet" href="./css/CustomerStyle.css">
        <link rel="stylesheet" href="./css/reset.css">
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
        <jsp:include page="NavBarKH.jsp"/>
        <div class="chen"></div>
        <div class="wrapper">
            <div class="container-booking">
                <main class="main-content">
                    <div class="booking-wrapper">
                        <h1>Đặt Phòng</h1>

                        <div class="search-bar">                      
                            <form action="SearchRoom" method="get" class="booking-form">
                                <div class="search-inputs">
                                    <input type="text" name="keyword" placeholder="Mã phòng" value="${keyword != null ? keyword : ''}" />

                                    <input type="date" name="checkin" id="checkin" required value="${checkin != null ? checkin : ''}" />
                                    <input type="date" name="checkout" id="checkout" required value="${checkout != null ? checkout : ''}" />

                                    <select name="soGiuong">
                                        <option value="0" ${soGiuong == 0 ? "selected" : ""}>Tất cả giường</option>
                                        <option value="1" ${soGiuong == 1 ? "selected" : ""}>1 Giường</option>
                                        <option value="2" ${soGiuong == 2 ? "selected" : ""}>2 Giường</option>
                                    </select>

                                    <select name="tang">
                                        <option value="0" ${tang == 0 ? "selected" : ""}>Tất cả tầng</option>
                                        <c:forEach begin="1" end="10" var="i">
                                            <option value="${i}" ${tang == i ? "selected" : ""}>Tầng ${i}</option>
                                        </c:forEach>
                                    </select>


                                    <select name="loaiPhong">
                                        <option value="" ${empty loaiPhong ? "selected" : ""}>Tất cả loại phòng</option>
                                        <option value="Standard" ${loaiPhong == 'Standard' ? 'selected' : ''}>Standard</option>
                                        <option value="Deluxe" ${loaiPhong == 'Deluxe' ? 'selected' : ''}>Deluxe</option>
                                        <option value="Suite" ${loaiPhong == 'Suite' ? 'selected' : ''}>Suite</option>
                                    </select>
                                </div>
                                <div class="search-button">
                                    <button type="submit"><i class="fas fa-search"></i> Tìm kiếm</button>
                                </div>
                            </form>
                        </div>
                    </div>
                    <div class="room-results">
                        <c:choose>
                            <c:when test="${not empty listPhong}">
                                <div class="room-list">
                                    <c:forEach var="phong" items="${listPhong}">
                                        <div class="room-card">
                                            <div class="room-image">
                                                <img src="https://images.unsplash.com/photo-1611892440504-42a792e24d32?q=80&w=200&auto=format&fit=crop" alt="Phòng 501">
                                            </div>
                                            <div class="room-info">
                                                <h3>Phòng ${phong.maPhong}</h3>
                                                <p><strong>Loại:</strong> ${phong.loaiPhong}</p>
                                                <p><strong>Tầng:</strong> ${phong.tang}</p>
                                                <p><strong>Diện tích:</strong> ${phong.dienTich} m²</p>
                                                <p><strong>Số giường:</strong> ${phong.soGiuong}</p>
                                                <p><strong>Mô tả:</strong> ${phong.moTa}</p>
                                            </div>
                                            <div class="room-price">${phong.giaPhong} VND</div>
                                            <form action="BookingForm" method="get">
                                                <input type="hidden" name="maPhong" value="${phong.maPhong}" />
                                                <input type="hidden" name="checkin" value="${checkin}" />
                                                <input type="hidden" name="checkout" value="${checkout}" />
                                                <button type="submit" class="booking-btn">Đặt phòng</button>
                                            </form>
                                        </div>
                                    </c:forEach>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <div class="no-rooms">
                                    <p>Hiện tại không có phòng nào phù hợp với yêu cầu của bạn.</p>
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </main>
            </div>
        </div>
        <jsp:include page="footer.jsp"/>
        <% if (request.getAttribute("message") != null) {%>
        <script>
            alert("<%= request.getAttribute("message")%>");
        </script>
        <% } else if (request.getAttribute("error") != null) {%>
        <script>
            alert("<%= request.getAttribute("error")%>");
        </script>
        <% }%>

        <script>
            const bookingForm = document.querySelector('.booking-form');
            if (bookingForm) {
                const checkin = document.getElementById('checkin');
                const checkout = document.getElementById('checkout');

                // Lắng nghe thay đổi ngày checkin
                checkin.addEventListener('change', function () {
                    checkout.min = this.value; // checkout phải >= checkin
                    if (checkout.value < this.value) {
                        checkout.value = this.value; // tự động chỉnh ngày nếu sai
                    }

                    // Nếu ngày hợp lệ thì submit
                    if (checkout.value && new Date(checkin.value) < new Date(checkout.value)) {
                        bookingForm.submit();
                    }
                });

                // Lắng nghe thay đổi ngày checkout
                checkout.addEventListener('change', function () {
                    if (checkout.value < checkin.value) {
                        alert("Ngày nhận phòng không được trước ngày đặt phòng!");
                        this.value = checkin.value;
                    } else {
                        // Nếu ngày hợp lệ thì submit
                        bookingForm.submit();
                    }
                });
            }
        </script>

    </body>
</html>
