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
<html lang="vi">

<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <title>Dashboard Người Dùng</title>
  <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;600&display=swap" rel="stylesheet" />
  <link rel="stylesheet" href="css/CustomerStyle.css">
  <link rel="stylesheet" href="css/reset.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
</head>
<body>
    <jsp:include page="NavBarKH.jsp"/>
  <div class="chen"></div>
  <div class="container">
    <!-- Chào mừng -->
    <div class="welcome">Chào mừng bạn trở lại, ${tenKH}!</div>

    <!-- Thống kê -->
    <div class="section-title">Thống kê nhanh</div>
    <div class="grid">
      <div class="card">
        <div class="card-title-grid">
          <i class="fa-solid fa-door-open" style=" color: #0095f8;"></i>
          <p>Phòng đã đặt</p>

        </div>
        <h3>${sdp}</h3>
      </div>
      <div class="card">
        <div class="card-title-grid">
          <i class="fa-regular fa-chart-bar" style=" color: #0095f8;"></i>
          <p>Dịch vụ đã dùng</p>

        </div>
        <h3>${sdv}</h3>
      </div>
      <div class="card">
        <div class="card-title-grid">
          <i class="fa-solid fa-money-bill-wave" style=" color: #0095f8;"></i>
          <p>Tổng chi tiêu</p>

        </div>
        <h3>${chitieu}đ</h3>
      </div>
    </div>

    <div class="splitout"></div>

    <!-- Điều hướng -->
    <div class="section-title" style="margin-top: 40px">Chức năng</div>
    <div class="grid">
      <a href="booking" class="card nav-card">
        <h3>🛏️</h3>
        <p>Đặt phòng</p>
      </a>
      <a href="DichVuKH" class="card nav-card">
        <h3>🛎️</h3>
        <p>Đặt dịch vụ</p>
      </a>
      <a href="UserInfo" class="card nav-card">
        <h3>👤</h3>
        <p>Thông tin cá nhân</p>
      </a>
      <a href="logout" class="card nav-card">
        <h3>🚪</h3>
        <p>Đăng xuất</p>
      </a>
    </div>

    <div class="splitout"></div>

    <div class="section-title">Phòng đặt gần nhất</div>
    <!-- =========================list========================= -->



    <div class="booking-card">
      <div class="booking-img">
        <img src="./img/jakub-dziubak-gj7BLlSzIFs-unsplash.jpg" alt="Hình phòng khách sạn">
        <!-- <div class="booking-label">3N3Đ VMB + Ăn uống | 17tr990</div> -->
      </div>
      <div class="booking-info">
        <h3 class="room-id">Phòng ${phong.maPhong}</h3>
        <p><strong>Loại:</strong> Phòng ${phong.loaiPhong}</p>
        <p><strong>Trạng thái:</strong> Đã đặt</p>
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
      <div class="booking-status">
        <span> ${phong.giaPhong} VNĐ / Đêm</span>
      </div>
    </div>
  </div>



  <jsp:include page="footer.jsp"/>
</body>

</html>