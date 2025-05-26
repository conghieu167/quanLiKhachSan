package dao;

import context.DBContext;
import entity.DatPhong;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookingDAO {

    public boolean addBooking(DatPhong booking) {
    String sqlInsertBooking = "INSERT INTO DatPhong (MaPhong, MaKH, NgayDat, NgayTra, TrangThai) VALUES (?, ?, ?, ?, ?)";
    String sqlInsertHoaDon = "INSERT INTO HoaDon (MaDP, MaPhong, MaKH, NgayTao, TongTien, TrangThai) VALUES (?, ?, ?, ?, ?, ?)";

    try (Connection conn = new DBContext().getConnection();
         PreparedStatement psBooking = conn.prepareStatement(sqlInsertBooking, Statement.RETURN_GENERATED_KEYS)) {

        // Set dữ liệu đặt phòng
        psBooking.setString(1, booking.getMaPhong());
        psBooking.setString(2, booking.getMaKH());
        psBooking.setDate(3, java.sql.Date.valueOf(booking.getNgayDat()));
        psBooking.setDate(4, java.sql.Date.valueOf(booking.getNgayTra()));
        psBooking.setString(5, "Đã đặt");

        int rowsAffected = psBooking.executeUpdate();

        if (rowsAffected > 0) {
            // Lấy MaDP vừa sinh
            ResultSet rs = psBooking.getGeneratedKeys();
            if (rs.next()) {
                int maDP = rs.getInt(1);

                // Tính tổng tiền — ví dụ đơn giản: 1.000.000/ngày
                long days = java.time.temporal.ChronoUnit.DAYS.between(
                    booking.getNgayDat(), booking.getNgayTra());
                long tongTien = days * 1000000; // Giá cứng, có thể thay bằng truy vấn phòng

                // Thêm hóa đơn
                try (PreparedStatement psHoaDon = conn.prepareStatement(sqlInsertHoaDon)) {
                    psHoaDon.setInt(1, maDP);
                    psHoaDon.setString(2, booking.getMaPhong());
                    psHoaDon.setString(3, booking.getMaKH());
                    psHoaDon.setTimestamp(4, new java.sql.Timestamp(System.currentTimeMillis()));
                    psHoaDon.setLong(5, tongTien);
                    psHoaDon.setString(6, "Đã thanh toán");

                    psHoaDon.executeUpdate();
                }

                return true;
            }
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return false;
}


    public List<DatPhong> getBookingsByUser(String maKH) {
        List<DatPhong> list = new ArrayList<>();
        String sql = "select * from datphong\n"
                + "where MaKH = ? ORDER BY madp desc";

        try (Connection conn = new DBContext().getConnection(); PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, maKH);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                DatPhong b = new DatPhong(
                        rs.getInt("MaDP"),
                        rs.getString("MaPhong"),
                        rs.getString("MaKH"),
                        rs.getDate("NgayDat").toLocalDate(),
                        rs.getDate("NgayTra").toLocalDate(),
                        rs.getString("TrangThai")
                );
                list.add(b);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<DatPhong> getBookings() {
        List<DatPhong> list = new ArrayList<>();
        String sql = "select * from datphong\n"
                + "order by madp desc";
        try (Connection conn = new DBContext().getConnection(); PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                DatPhong b = new DatPhong(
                        rs.getInt("MaDP"),
                        rs.getString("MaPhong"),
                        rs.getString("MaKH"),
                        rs.getDate("NgayDat").toLocalDate(),
                        rs.getDate("NgayTra").toLocalDate(),
                        rs.getString("TrangThai")
                );
                list.add(b);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean cancelBooking(String maDP) {
        String sql = "UPDATE DatPhong SET trangThai = ? WHERE maDP = ?";
        try (Connection conn = new DBContext().getConnection(); PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, "Đã hủy");
            ps.setString(2, maDP);

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public List<DatPhong> getFilteredBookings(String keyword, String ngayDat, String trangThai) {
        List<DatPhong> list = new ArrayList<>();

        StringBuilder sql = new StringBuilder("SELECT * FROM DatPhong WHERE 1=1");
        List<Object> params = new ArrayList<>();

        if (keyword != null && !keyword.trim().isEmpty()) {
            sql.append(" AND (MaDP LIKE ? OR MaPhong LIKE ?)");
            params.add("%" + keyword.trim() + "%");
            params.add("%" + keyword.trim() + "%");
        }

        if (ngayDat != null && !ngayDat.trim().isEmpty()) {
            sql.append(" AND NgayDat = ?");
            params.add(java.sql.Date.valueOf(ngayDat.trim()));
        }

        if (trangThai != null && !trangThai.trim().isEmpty()) {
            sql.append(" AND TrangThai = ?");
            params.add(trangThai.trim());
        }
        sql.append(" ORDER BY MaDP DESC");
        try (Connection conn = new DBContext().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql.toString())) {

            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    DatPhong dp = new DatPhong();
                    dp.setMaDP(rs.getInt("MaDP"));
                    dp.setMaPhong(rs.getString("MaPhong"));
                    dp.setMaKH(rs.getString("MaKH"));
                    dp.setNgayDat(rs.getDate("NgayDat").toLocalDate());
                    dp.setNgayTra(rs.getDate("NgayTra").toLocalDate());
                    dp.setTrangThai(rs.getString("TrangThai"));
                    list.add(dp);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

}
