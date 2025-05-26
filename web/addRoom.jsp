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
        <meta charset="UTF-8">
        <title>Thêm Phòng Mới</title>
        <style>
            /* Toàn trang */
            body {
                font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
                background: #f0f4f8;
                margin: 0;
                padding: 0;
                display: flex;
                justify-content: center;
                align-items: flex-start;
                min-height: 100vh;
                padding-top: 40px;
            }

            /* Khung chính */
            .main-content {
                background: #fff;
                padding: 30px 40px;
                border-radius: 12px;
                box-shadow: 0 8px 30px rgb(0 0 0 / 0.1);
                width: 420px;
                max-width: 95%;
            }

            /* Tiêu đề */
            .main-content h2 {
                text-align: center;
                margin-bottom: 25px;
                font-weight: 700;
                color: #222;
                letter-spacing: 0.05em;
            }

            /* Nhóm form */
            .form-group {
                margin-bottom: 18px;
            }

            /* Label */
            .form-group label {
                display: block;
                font-weight: 600;
                margin-bottom: 8px;
                color: #444;
            }

            /* Input, select, textarea */
            .form-group input[type="text"],
            .form-group input[type="number"],
            .form-group select,
            .form-group textarea {
                width: 100%;
                padding: 11px 14px;
                border: 1.5px solid #ccc;
                border-radius: 7px;
                font-size: 15px;
                font-family: inherit;
                transition: border-color 0.3s ease, box-shadow 0.3s ease;
                resize: vertical; /* cho textarea */
            }

            .form-group input[type="text"]:focus,
            .form-group input[type="number"]:focus,
            .form-group select:focus,
            .form-group textarea:focus {
                outline: none;
                border-color: #1e90ff;
                box-shadow: 0 0 6px #1e90ffaa;
            }

            /* Nút thêm phòng */
            .btn-add {
                display: block;
                width: 100%;
                padding: 14px 0;
                background-color: #1e90ff;
                color: white;
                font-weight: 700;
                font-size: 16px;
                border: none;
                border-radius: 10px;
                cursor: pointer;
                transition: background-color 0.25s ease;
                letter-spacing: 0.03em;
            }

            .btn-add:hover {
                background-color: #0f6fe0;
            }

            /* Responsive nhỏ */
            @media (max-width: 480px) {
                .main-content {
                    width: 90%;
                    padding: 25px 20px;
                }
                .btn-add {
                    font-size: 14px;
                    padding: 12px 0;
                }
            }

        </style>

    </head>
    <body>
        <div class="main-content">
            <h2>Thêm phòng mới</h2>
            <form class="add-room-form" action="addRoom" method="post">
                <div class="form-group">
                    <label for="maPhong">Mã phòng:</label>
                    <input type="text" id="maPhong" name="maPhong" required>
                </div>

                <div class="form-group">
                    <label for="loaiPhong">Loại phòng:</label>
                    <select id="loaiPhong" name="loaiPhong" required>
                        <option value="Standard">Standard</option>
                        <option value="Deluxe">Deluxe</option>
                        <option value="Suite">Suite</option>
                    </select>
                </div>

                <div class="form-group">
                    <label for="trangThai">Trạng thái:</label>
                    <select id="trangThai" name="trangThai" required>
                        <option value="Sẵn sàng sử dụng">Sẵn sàng sử dụng</option>
                        <option value="Bảo trì">Bảo trì</option>
                    </select>
                </div>

                <div class="form-group">
                    <label for="giaPhong">Giá phòng (VNĐ):</label>
                    <input type="number" id="giaPhong" name="giaPhong" required>
                </div>

                <div class="form-group">
                    <label for="tang">Tầng:</label>
                    <input type="number" id="tang" name="tang" required>
                </div>

                <div class="form-group">
                    <label for="dienTich">Diện tích (m²):</label>
                    <input type="number" id="dienTich" name="dienTich" required>
                </div>

                <div class="form-group">
                    <label for="soGiuong">Số giường:</label>
                    <input type="number" id="soGiuong" name="soGiuong" required>
                </div>

                <div class="form-group">
                    <label for="moTa">Mô tả:</label>
                    <textarea id="moTa" name="moTa" rows="4" placeholder="VD: Phòng yên tĩnh, đầy đủ tiện nghi..."></textarea>
                </div>

                <button type="submit" class="btn-add">Thêm phòng</button>
            </form>
        </div>

    </body>
</html>
