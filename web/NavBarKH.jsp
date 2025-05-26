<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
<header class="customer-header">
    <div class="customer-content">
        <nav class="customer-navbar">
            <!-- Logo -->
            <img class="Logo" src="./img/L_Hotel-removebg-preview.png" alt="L_Hotel" />
            <!-- Navigation -->
            <ul>
                <li><a href="dashboardKH">Home</a></li>
                <li><a href="booking">Booking</a></li>
                <li><a href="DichVuKH">Service</a></li>
                <li><a href="#Contact">Contact</a></li>
            </ul>
            <div class="UserInfo">
                <a href="UserInfo" >
                    <i class="fa fa-user-circle"></i>
                </a>
                <a href="logout">
                    <i class="fa-solid fa-door-open"></i>
                </a>
            </div>
        </nav>
    </div>
</header>
