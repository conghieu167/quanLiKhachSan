<%@page import="java.util.Map"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
        <link rel="stylesheet" href="./css/style.css" />
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

                <main class="main-content" style="padding: 20px 30px">
                    <h1 style="margin-bottom: 5px; color: var(--primary-color)">Thống kê</h1>
                    <div class="dashboard__welcome">
                        <p>Xin chào, <span class="user-name">Quản trị viên</span>! Quản lý khách sạn của bạn với L'hotel Manager một cách dễ dàng, hiệu quả và chuyên nghiệp.</p>
                    </div>
                    <!-- Tổng quát nhanh -->
                    <div class="tk-summary">
                        <div class="metric-card">
                            <i class="fas fa-bed"></i>
                            <h3>Tổng số phòng</h3>
                            <p>${soPhong}</p>
                        </div>
                        <div class="metric-card">
                            <i class="fas fa-calendar-check"></i>
                            <h3>Số khách hàng</h3>
                            <p>${soKhach}</p>
                        </div>
                        <div class="metric-card">
                            <i class="fa-solid fa-user"></i>
                            <h3>Số đơn dặt phòng</h3>
                            <p>${soDonDat}</p>
                        </div>
                        <div class="metric-card">
                            <i class="fas fa-dollar-sign"></i>
                            <h3>Doanh thu tháng này</h3>
                            <p><fmt:formatNumber value="${doanhThu}" type="number" groupingUsed="true"/> VND</p>
                        </div>
                    </div>

                    <h2 style="margin-bottom: 20px; color: var(--primary-color)">Biểu đồ thống kê</h2>
                    <div class="tk-charts-container tk-charts-row">
                        <!-- Doanh thu -->
                        <div class="tk-section tk-chart-box1">
                            <h4 class="tk-section-title">Doanh thu theo ngày</h4>
                            <canvas id="tk-revenueChart" class="tk-chart tk-chart-small"></canvas>
                        </div>

                        <!-- Tỷ lệ lấp đầy -->
                        <div class="tk-section tk-chart-box1">
                            <h4 class="tk-section-title">Tỷ lệ lấp đầy</h4>
                            <canvas id="tk-occupancyChart" class="tk-chart tk-chart-small"></canvas>
                        </div>

                        <!-- Tỷ lệ loại phòng -->
                        <div class="tk-section tk-chart-box2">
                            <h4 class="tk-section-title">Tỷ lệ loại phòng</h4>
                            <canvas id="tk-roomTypeChart" class="tk-chart tk-chart-small"></canvas>
                        </div>
                    </div>


                    <!-- Nút tải báo cáo -->
                    <div class="tk-section">
                        <div class="tk-button-group">
                            <button id="tk-downloadRoomType" class="tk-btn">📥 Tải biểu đồ loại phòng</button>
                            <button id="tk-downloadRevenue" class="tk-btn">📥 Tải biểu đồ doanh thu</button>
                            <button id="tk-downloadOccupancy" class="tk-btn">📥 Tải biểu đồ lấp đầy</button>
                        </div>
                    </div>

                    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

                </main>
            </div>
        </div>
        <%
            Map<String, Integer> roomTypeStats = (Map<String, Integer>) request.getAttribute("roomTypeStats");
            StringBuilder roomTypeLabels = new StringBuilder();
            StringBuilder roomTypeData = new StringBuilder();
            if (roomTypeStats != null) {
                for (Map.Entry<String, Integer> entry : roomTypeStats.entrySet()) {
                    roomTypeLabels.append("'").append(entry.getKey()).append("',");
                    roomTypeData.append(entry.getValue()).append(",");
                }
            }

            Map<String, Double> revenueByDate = (Map<String, Double>) request.getAttribute("revenueByDate");
            StringBuilder revenueLabels = new StringBuilder();
            StringBuilder revenueData = new StringBuilder();
            if (revenueByDate != null) {
                for (Map.Entry<String, Double> entry : revenueByDate.entrySet()) {
                    revenueLabels.append("'").append(entry.getKey()).append("',");
                    revenueData.append(entry.getValue()).append(",");
                }
            }

            Map<String, Double> occupancyRate = (Map<String, Double>) request.getAttribute("occupancyMap");
            StringBuilder occupancyLabels = new StringBuilder();
            StringBuilder occupancyData = new StringBuilder();
            if (occupancyRate != null) {
                for (Map.Entry<String, Double> entry : occupancyRate.entrySet()) {
                    occupancyLabels.append("'").append(entry.getKey()).append("',");
                    occupancyData.append(entry.getValue()).append(",");
                }
            }
        %>

        <script>
            const roomTypeLabels = <%= roomTypeLabels.length() > 0 ? "[" + roomTypeLabels.substring(0, roomTypeLabels.length() - 1) + "]" : "[]"%>;
            const roomTypeData = <%= roomTypeData.length() > 0 ? "[" + roomTypeData.substring(0, roomTypeData.length() - 1) + "]" : "[]"%>;

            const revenueLabels = <%= revenueLabels.length() > 0 ? "[" + revenueLabels.substring(0, revenueLabels.length() - 1) + "]" : "[]"%>;
            const revenueData = <%= revenueData.length() > 0 ? "[" + revenueData.substring(0, revenueData.length() - 1) + "]" : "[]"%>;

            const occupancyLabels = <%= occupancyLabels.length() > 0 ? "[" + occupancyLabels.substring(0, occupancyLabels.length() - 1) + "]" : "[]"%>;
            const occupancyData = <%= occupancyData.length() > 0 ? "[" + occupancyData.substring(0, occupancyData.length() - 1) + "]" : "[]"%>;
        </script>




        <script src="js/thongke.js"></script>
    </body>
</html>
