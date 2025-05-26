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
            <jsp:include page="NavBarQL.jsp" />
            <div class="container">
                <jsp:include page="sidebar.jsp" />

                <main class="main-content">
                    <div class="top" style="margin-top: 10px">
                        <h1 style="margin-left:30px">Quản lý dịch vụ</h1>
                    </div>
                        <div class="service-list">
                            <!-- Lặp qua danh sách dịch vụ -->
                            <c:forEach var="dichVu" items="${listDichVu}">
                                <div class="service-item">
                                    <!-- Chế độ xem -->
                                    <div class="view-mode">
                                        <h3 class="MaDV" style="color: #0066cc">${dichVu.maDV}</h3>
                                        <p class="TenDV"><strong>Tên:</strong> ${dichVu.tenDV}</p>
                                        <p class="GiaDV"><strong>Giá:</strong> ${dichVu.giaTien} VND</p>
                                        <p class="ChiTietDV"><strong>Chi tiết:</strong> ${dichVu.chiTiet}</p>
                                        <div class="action-buttons">
                                            <button class="edit-btn" onclick="showEditServiceForm('${dichVu.maDV}')">Sửa</button>
                                        </div>
                                    </div>

                                    <!-- Form chỉnh sửa -->
                                    <form id="editServiceForm-${dichVu.maDV}" class="edit-form" action="editService" method="post" style="display: none;">
                                        <input type="hidden" name="maDV" value="${dichVu.maDV}" />
                                        <p class="TenDV"><strong>Tên:</strong> ${dichVu.tenDV}</p>
                                        <p><strong>Giá:</strong> <input type="number" name="giaTien" value="${dichVu.giaTien}" required /></p>
                                        <p><strong>Chi tiết:</strong> <input type="text" name="chiTiet" value="${dichVu.chiTiet}" required /></p>
                                        <div class="action-buttons">
                                            <button type="submit" class="save-btn">Lưu</button>
                                            <button type="button" class="cancel-btn" onclick="cancelEditService('${dichVu.maDV}')">Hủy</button>
                                        </div>
                                    </form>
                                </div>
                            </c:forEach>
                        </div>
                </main>
            </div>
        </div>
        <script src="js/dichvu.js"></script>

    </body>
</html>
