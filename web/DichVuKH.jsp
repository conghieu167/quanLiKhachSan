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
        <style>
            .container {
                position: relative;
                top: 100px;
                width: 90%;
                max-width: 1200px;
                margin: 40px auto;
            }
        </style>
    </head>
    <body>

        <jsp:include page="NavBarKH.jsp" />
        <div class="container">    
            <h2 style="margin:0">Danh sách dịch vụ: </h2>
            <div class="service-list">
                <c:forEach var="dichVu" items="${listDichVu}">
                    <div class="service-item">
                        <h3 class="MaDV">${dichVu.maDV}</h3>
                        <p class="TenDV"><strong>Tên:</strong> ${dichVu.tenDV}</p>
                        <p class="GiaDV"><strong>Giá:</strong> ${dichVu.giaTien} VND</p>
                        <p class="ChiTietDV"><strong>Chi tiết:</strong> ${dichVu.chiTiet}</p>

                        <form action="DatDichVu" method="post" style="margin-top: 10px;" onsubmit="return confirmBooking()">
                            <input type="hidden" name="maDV" value="${dichVu.maDV}" />
                            <button type="submit" class="btn-dat-dv">Đặt dịch vụ</button>
                        </form>
                    </div>
                </c:forEach>
            </div>
        </div>
        <% String msg = (String) request.getAttribute("message"); %>
        <% if (msg != null) {%>
        <div id="popup" class="popup-overlay">
            <div class="popup-content">
                <p><%= msg%></p>
                <button onclick="closePopup()">Đóng</button>
            </div>
        </div>
        <% }%>
        <script>
            function confirmBooking() {
                return confirm("Bạn có chắc chắn muốn đặt dịch vụ này không?");
            }
            function closePopup() {
                document.getElementById("popup").style.display = "none";
            }
        </script>
        <jsp:include page="footer.jsp"/>
    </body>
</html>
