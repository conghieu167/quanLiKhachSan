package dao;

import context.DBContext;
import entity.Phong;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SearchDAO {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<Phong> searchRooms(String keyword, String checkin, String checkout, int soGiuong, int tang, String loaiPhong) {
        List<Phong> list = new ArrayList<>();

        String sql = "SELECT * FROM Phong " +
                "WHERE TrangThaiPhong = N'Sẵn sàng sử dụng' " +
                "AND (MaPhong LIKE ? OR LoaiPhong LIKE ?) " +
                "AND (? = 0 OR SoGiuong = ?) " +
                "AND (? = 0 OR Tang = ?) " +
                "AND (? = '' OR LoaiPhong = ?) " +
                "AND MaPhong NOT IN ( " +
                "   SELECT MaPhong FROM DatPhong " +
                "   WHERE NOT (NgayTra <= ? OR NgayDat >= ?) " +
                ")";

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);

            String key = "%" + (keyword == null ? "" : keyword) + "%";
            String loai = (loaiPhong == null) ? "" : loaiPhong;

            ps.setString(1, key); // MaPhong LIKE ?
            ps.setString(2, key); // LoaiPhong LIKE ?
            ps.setInt(3, soGiuong); // ? = 0 OR SoGiuong = ?
            ps.setInt(4, soGiuong);
            ps.setInt(5, tang); // ? = 0 OR Tang = ?
            ps.setInt(6, tang);
            ps.setString(7, loai); // ? = '' OR LoaiPhong = ?
            ps.setString(8, loai);
            ps.setDate(9, java.sql.Date.valueOf(checkin));
            ps.setDate(10, java.sql.Date.valueOf(checkout));

            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Phong(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getInt(5),
                        rs.getDouble(6),
                        rs.getInt(7),
                        rs.getString(8)
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Đóng tài nguyên để tránh rò rỉ kết nối
            try { if (rs != null) rs.close(); } catch (Exception e) {}
            try { if (ps != null) ps.close(); } catch (Exception e) {}
            try { if (conn != null) conn.close(); } catch (Exception e) {}
        }

        return list;
    }
    
}

