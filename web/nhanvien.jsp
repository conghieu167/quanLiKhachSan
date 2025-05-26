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
            <jsp:include page="NavBarQL.jsp"/>
            <div class="container">
                <jsp:include page="sidebar.jsp"/>
                <main class="main-content">
                    <div class="top">
                        <h1 class="title">Danh Sánh Nhân Viên</h1>
                        <button class="themNV" onclick="addEmployee()">+ Thêm</button>
                    </div>
                    <div class="table-wrapper">
                        <table id="employeeTable">
                            <thead>
                                <tr>
                                    <th>Mã nhân viên</th>
                                    <th>Họ tên</th>
                                    <th>Chức vụ</th>
                                    <th>Mã quản lý</th>
                                    <th>Số điện thoại</th>
                                    <th>Email</th>
                                    <th>Lương</th>
                                    <th>Hành Động</th>
                                </tr>
                            </thead>

                            <tbody>
                                <c:forEach var="nv" items="${listNhanVien}">
                                    <tr>
                                        <td>${nv.maNV}</td>
                                        <td>${nv.hoTenNV}</td>
                                        <td>${nv.chucVu}</td>
                                        <td>${nv.maQL}</td>
                                        <td>${nv.sdtnv}</td>
                                        <td>${nv.emailNV}</td>
                                        <td>${nv.luong}</td>
                                        <td class="action-buttons">
                                            <a onclick="showEditForm('${nv.maNV}')">
                                  <button class="edit-btn">Sửa</button></a>
                                            <a onclick="confirmDelete('${nv.maNV}')"
                                               ><button class="delete-btn">Xóa</button></a
                                            >
                                        </td>
                                    </tr>
                                    <tr class="edit-form" id="editForm-${nv.maNV}" style="display: none;">
                                    <form action="edit" method="post">
                                        <td><input type="text" name="maNV" value="${nv.maNV}" readonly /></td>
                                        <td><input type="text" name="hoTenNV" value="${nv.hoTenNV}" required /></td>
                                        <td><input type="text" name="chucVu" value="${nv.chucVu}" required /></td>
                                        <td><input type="text" name="maQL" value="${nv.maQL}" required /></td>
                                        <td><input type="text" name="sdtnv" value="${nv.sdtnv}" required /></td>
                                        <td><input type="email" name="emailNV" value="${nv.emailNV}" required /></td>
                                        <td><input type="number" name="luong" value="${nv.luong}" required /></td>
                                        <td class="action-buttons">
                                            <button type="submit" name="action" value="update" class="save-btn">Lưu</button>
                                            <button type="button" class="cancel-btn" onclick="cancelEdit()">Hủy</button>
                                        </td>
                                    </form>
                                </tr>
                                </c:forEach>
  
                                <tr id="addEmployeeRow" style="display: none">
                            <form
                                id="addEmployeeForm"
                                action="NhanVienControl"
                                method="post"
                                >
                                <td><input type="text" name="maNV" required /></td>
                                <td><input type="text" name="hoTenNV" required /></td>
                                <td><input type="text" name="chucVu" required /></td>
                                <td><input type="text" name="maQL" required /></td>
                                <td><input type="text" name="sdtnv" required /></td>
                                <td><input type="email" name="emailNV" required /></td>
                                <td><input type="number" name="luong" required /></td>
                                <td class="action-buttons">
                                    <button type="submit" class="save-btn">Lưu</button>
                                    <button
                                        type="button"
                                        class="cancel-btn"
                                        onclick="hideAddEmployee()"
                                        >
                                        Hủy
                                    </button>
                                </td>
                            </form>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </main>
            </div>
        </div>
        <script src="js/nhanvien.js"></script>
    </body>
</html>
