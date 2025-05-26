/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import context.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author ADMIN
 */
public class TKDAO {

    public int getSoPhong(String loaiPhong) {
        String sql = "SELECT COUNT(*)\n"
                + "FROM datphong\n"
                + "JOIN phong ON datphong.maphong = phong.maphong\n"
                + "WHERE loaiphong = ?";

        try (Connection conn = new DBContext().getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, loaiPhong);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1); // lấy theo alias
            }
        } catch (Exception e) {

        }
        return 0;
    }

    public Map<String, Double> getRevenueByDate() {
        Map<String, Double> revenueMap = new LinkedHashMap<>();
        String sql = "SELECT CAST(NgayTao AS DATE) AS Ngay, SUM(TongTien) AS DoanhThu "
                + "FROM hoadon "
                + "WHERE Trangthai = N'Đã thanh toán' "
                + "AND NgayTao BETWEEN ? AND ? "
                + "GROUP BY CAST(NgayTao AS DATE) "
                + "ORDER BY Ngay";

        try (Connection conn = new DBContext().getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDate(1, java.sql.Date.valueOf("2025-05-01"));
            ps.setDate(2, java.sql.Date.valueOf("2025-05-31"));

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                revenueMap.put(rs.getDate("Ngay").toString(), rs.getDouble("DoanhThu"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return revenueMap;
    }

    public Map<String, Double> getOccupancyRateInMay() {
        Map<String, Double> occupancyMap = new LinkedHashMap<>();
        String sql = """
        WITH DateRange AS (
            SELECT CAST('2025-05-01' AS DATE) AS Ngay
            UNION ALL
            SELECT DATEADD(DAY, 1, Ngay)
            FROM DateRange
            WHERE Ngay < '2025-05-31'
        ),
        AvailableRooms AS (
            SELECT COUNT(*) AS TotalRooms
            FROM phong
            WHERE TrangThaiPhong IN (N'Sẵn sàng sử dụng', N'Đang sử dụng')
        ),
        RoomsUsed AS (
            SELECT DISTINCT MaPhong, Ngay
            FROM (
                SELECT MaPhong, DATEADD(DAY, v.number, NgayDat) AS Ngay
                FROM datphong
                JOIN master..spt_values v ON v.type = 'P' AND v.number BETWEEN 0 AND DATEDIFF(DAY, NgayDat, NgayTra)
                WHERE TrangThai IN (N'Đã đặt', N'Đã nhận phòng')
                  AND NgayDat <= '2025-05-31' AND NgayTra >= '2025-05-01'
            ) AS RoomDays
        )
        SELECT 
            d.Ngay,
            ISNULL(CAST(COUNT(DISTINCT r.MaPhong) AS FLOAT) / a.TotalRooms * 100, 0) AS TyLeLapDay
        FROM DateRange d
        CROSS JOIN AvailableRooms a
        LEFT JOIN RoomsUsed r ON r.Ngay = d.Ngay
        GROUP BY d.Ngay, a.TotalRooms
        ORDER BY d.Ngay
        OPTION (MAXRECURSION 0);
        """;

        try (Connection conn = new DBContext().getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String date = rs.getDate("Ngay").toString();
                double rate = rs.getDouble("TyLeLapDay");
                occupancyMap.put(date, rate);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return occupancyMap;
    }

    public int getCount(String tableName) {
    String sql = "SELECT COUNT(*) FROM " + tableName;

    try (Connection conn = new DBContext().getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt(1);
        }
    } catch (Exception e) {
        e.printStackTrace(); // nên log lỗi để dễ debug
    }
    return 0;
}
    public int getDoanhThu() {
        String sql = "select sum(tongtien) as tongtien from hoadon";

        try (Connection conn = new DBContext().getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1); // lấy theo alias
            }
        } catch (Exception e) {

        }
        return 0;
    }

}
