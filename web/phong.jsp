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
                        <div class="top">
                            <h1 class="title">Danh Sách Phòng</h1>
                            <button class="themPhong"><a href="addRoom">+ Thêm</a></button>
                        </div>
                        <div class="room-list">
                            <c:forEach var="phong" items="${listPhong}">
                                <div class="room-card">
                                    <!-- Chế độ xem -->
                                    <div class="view-mode">
                                        <h3>Phòng ${phong.maPhong}</h3>
                                        <p><strong>Loại:</strong> ${phong.loaiPhong}</p>
                                        <p><strong>Trạng thái:</strong> ${phong.trangThaiPhong}</p>
                                        <p><strong>Giá:</strong> ${phong.giaPhong}</p>
                                        <p><strong>Tầng:</strong> ${phong.tang}</p>
                                        <p><strong>Diện tích:</strong> ${phong.dienTich}²</p>
                                        <p><strong>Số giường:</strong> ${phong.soGiuong}</p>
                                        <p><strong>Mô tả:</strong> ${phong.moTa}</p>
                                        <div class="action-buttons">
                                            <button class="edit-btn" onclick="showEditRoomForm('${phong.maPhong}')">Sửa</button>
                                            <button class="delete-btn" onclick="confirmDeleteRoom('${phong.maPhong}')">Xóa</button>
                                        </div>
                                    </div>

                                    <!-- Form chỉnh sửa -->
                                    <form
                                        id="editRoomForm-${phong.maPhong}"
                                        class="edit-form"
                                        action="editRoom"
                                        method="post"
                                        style="display: none;"
                                        >
                                        <input type="hidden" name="maPhong" value="${phong.maPhong}" />
                                        <p><strong>Loại:</strong> 
                                            <select id="loaiPhong" name="loaiPhong" required>
                                                <option value="Standard" ${phong.loaiPhong == 'Standard' ? 'selected' : ''}>Standard</option>
                                                <option value="Deluxe" ${phong.loaiPhong == 'Deluxe' ? 'selected' : ''}>Deluxe</option>
                                                <option value="Suite" ${phong.loaiPhong == 'Suite' ? 'selected' : ''}>Suite</option>
                                            </select>
                                        </p>
                                        <p><strong>Trạng thái:</strong> 
                                            <select id="trangThaiPhong" name="trangThaiPhong" required>
                                                <option value="Sẵn sàng sử dụng" ${phong.trangThaiPhong == 'Sẵn sàng sử dụng' ? 'selected' : ''}>Sẵn sàng sử dụng</option>
                                                <option value="Bảo trì" ${phong.trangThaiPhong == 'Bảo trì' ? 'selected' : ''}>Bảo trì</option>
                                            </select>
                                        </p>
                                        <p><strong>Giá:</strong> <input type="number" name="giaPhong" value="${phong.giaPhong}" required /></p>
                                        <p><strong>Tầng:</strong> <input type="number" name="tang" value="${phong.tang}" required /></p>
                                        <p><strong>Diện tích:</strong> <input type="number" name="dienTich" value="${phong.dienTich}" required /></p>
                                        <p><strong>Số giường:</strong> <input type="number" name="soGiuong" value="${phong.soGiuong}" required /></p>
                                        <p><strong>Mô tả:</strong> <input type="text" name="moTa" value="${phong.moTa}" /></p>

                                        <div class="action-buttons">
                                            <button type="submit" class="save-btn">Lưu</button>
                                            <button type="button" class="cancel-btn" onclick="cancelEditRoom()">Hủy</button>
                                        </div>
                                    </form>
                                </div>
                            </c:forEach>

                        </div>
                </main>
            </div>
        </div>
        <script src="js/phong.js"></script>
    </body>
</html>
