<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<aside class="sidebar">
        <div class="logo">
            <img src="img/L_Hotel-removebg-preview.png" alt="L'Hotel Logo" />
            
        </div>
        <nav class="menu">
            <ul>
                <a href="nhanvien" class="active"><li><i class="fas fa-users"></i>Nhân viên<span class="tooltip">Quản lý nhân viên</span></li></a>
                <a href="phong"><li><i class="fas fa-bed"></i>Phòng<span class="tooltip">Quản lý phòng</span></li></a>
                <a href="xemdatphong"><li><i class="fas fa-calendar"></i>Xem lịch<span class="tooltip">Xem lịch đặt phòng</span></li></a>
                <a href="DichVu"><li><i class="fas fa-concierge-bell"></i>Dịch vụ<span class="tooltip">Quản lý dịch vụ</span></li></a>
                <a href="ThongKe"><li><i class="fas fa-chart-bar"></i>Báo cáo<span class="tooltip">Xem báo cáo</span></li></a>
            </ul>
        </nav>
        <hr class="divider">
        <div class="sidebar-footer">
            <div class="user-info">
                <img src="https://via.placeholder.com/32?text=User" alt="User Avatar" />
                <span>Admin</span>
            </div>
        </div>
    </aside>