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
        <style>/* Modal background */
            /* Modal background */
            .modal {
                display: none; /* Ẩn mặc định */
                position: fixed;
                z-index: 1000;
                left: 0;
                top: 0;
                width: 100%;
                height: 100%;
                overflow-y: auto; /* cho phép scroll nếu nội dung dài */
                background-color: rgba(0, 0, 0, 0.5);
                font-family: 'Inter', sans-serif;
            }

            /* Nội dung modal */
            .modal-content {
                background-color: #ffffff;
                margin: 5% auto;
                padding: 30px 40px;
                border-radius: 12px;
                width: 420px;
                max-width: 90%;
                box-shadow: 0 8px 24px rgba(0, 0, 0, 0.15);
                position: relative;
                animation: slideDown 0.3s ease-out;
            }

            /* Hiệu ứng popup */
            @keyframes slideDown {
                from {
                    opacity: 0;
                    transform: translateY(-15px);
                }
                to {
                    opacity: 1;
                    transform: translateY(0);
                }
            }

            /* Nút đóng modal (X) */
            .close-btn {
                color: #999999;
                float: right;
                font-size: 28px;
                font-weight: 700;
                cursor: pointer;
                transition: color 0.2s ease;
            }

            .close-btn:hover {
                color: #ff4d4f;
            }

            /* Tiêu đề form */
            .modal-content h2 {
                margin-top: 0;
                margin-bottom: 25px;
                font-weight: 600;
                font-size: 24px;
                color: #222222;
                text-align: center;
                letter-spacing: 0.03em;
            }

            /* Label cho input */
            .modal-content label {
                display: block;
                margin-bottom: 6px;
                font-weight: 600;
                font-size: 14px;
                color: #444444;
            }

            /* Input text, email, number */
            .modal-content input[type="text"],
            .modal-content input[type="email"],
            .modal-content input[type="number"] {
                width: 100%;
                padding: 10px 12px;
                margin-bottom: 18px;
                border: 1.8px solid #ddd;
                border-radius: 6px;
                font-size: 15px;
                transition: border-color 0.3s ease;
            }

            .modal-content input[type="text"]:focus,
            .modal-content input[type="email"]:focus,
            .modal-content input[type="number"]:focus {
                outline: none;
                border-color: #1877f2;
                box-shadow: 0 0 6px #1877f2aa;
            }

            /* Khu vực nút */
            .modal-actions {
                margin-top: 10px;
                text-align: right;
            }

            /* Nút lưu */
            .save-btn {
                background-color: #1877f2;
                color: white;
                border: none;
                padding: 10px 22px;
                font-weight: 700;
                font-size: 15px;
                border-radius: 8px;
                cursor: pointer;
                transition: background-color 0.25s ease;
                margin-left: 10px;
            }

            .save-btn:hover {
                background-color: #105ecb;
            }

            /* Nút hủy */
            .cancel-btn {
                background-color: #e0e0e0;
                color: #444444;
                border: none;
                padding: 10px 22px;
                font-weight: 600;
                font-size: 15px;
                border-radius: 8px;
                cursor: pointer;
                transition: background-color 0.25s ease;
            }

            .cancel-btn:hover {
                background-color: #cfcfcf;
            }

            /* Responsive cho màn hình nhỏ */
            @media screen and (max-width: 480px) {
                .modal-content {
                    width: 90%;
                    padding: 25px 20px;
                }
                .modal-content h2 {
                    font-size: 20px;
                }
                .save-btn, .cancel-btn {
                    padding: 10px 18px;
                    font-size: 14px;
                }
            }

        </style>
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


                            </tbody>
                        </table>
                    </div>
                </main>
            </div>
        </div>
        <!-- Modal thêm nhân viên -->
        <div id="addEmployeeModal" class="modal">
            <div class="modal-content">
                <span class="close-btn" id="closeModal">&times;</span>
                <h2>Thêm nhân viên mới</h2>
                <form id="addEmployeeForm" action="NhanVienControl" method="post">
                    <label>Mã nhân viên:</label>
                    <input type="text" name="maNV" required />
                    <label>Họ tên:</label>
                    <input type="text" name="hoTenNV" required />
                    <label>Chức vụ:</label>
                    <input type="text" name="chucVu" required />
                    <label>Mã quản lý:</label>
                    <input type="text" name="maQL" required />
                    <label>Số điện thoại:</label>
                    <input type="text" name="sdtnv" required />
                    <label>Email:</label>
                    <input type="email" name="emailNV" required />
                    <label>Lương:</label>
                    <input type="number" name="luong" required />
                    <div class="modal-actions">
                        <button type="submit" class="save-btn">Lưu</button>
                        <button type="button" class="cancel-btn" id="cancelAdd">Hủy</button>
                    </div>
                </form>
            </div>
        </div>

        <script src="js/nhanvien.js"></script>
    </body>
</html>
