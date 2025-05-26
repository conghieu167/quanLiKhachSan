<%@page contentType="text/html" pageEncoding="UTF-8"%>
<header class="header">
     <div class="left-section">
    <div class="hamburger"><i class="fa fa-bars"></i></div>
     </div>
     <div class="right-section">
    <div class="icons">
        <div class="icon-container">
            <i class="fa fa-user-circle" id="userIcon"></i>
            <!-- Popup -->
            <div id="userPopup" class="user-popup">
                <p><strong>Mã QL:</strong> ${user.maQL}</p>
                <p><strong>Họ tên:</strong> ${user.hoTenQL}</p>
                <p><strong>SĐT:</strong> ${user.sdt}</p>
                <p><strong>Email:</strong> ${user.email}</p>
            </div>
        </div>
        <a href="logout" class="icon-link" title="Đăng xuất">
            <i class="fa-solid fa-door-open"></i>
        </a>
    </div>
     </div>
</header>
            <script src="js/quanly.js"></script>