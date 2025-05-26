package dao;

import context.DBContext;
import entity.HoaDon;
import entity.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class UserDAO {

    public String checkLogin(String username, String password) {
        String sqlNhanVien = "SELECT * FROM TaiKhoanQL WHERE Taikhoan=? AND MatKhau=?";
        String sqlKhachHang = "SELECT * FROM TaiKhoanKH WHERE TaiKhoan=? AND MatKhau=?";

        try (Connection conn = new DBContext().getConnection()) {

            PreparedStatement psNhanVien = conn.prepareStatement(sqlNhanVien);
            psNhanVien.setString(1, username);
            psNhanVien.setString(2, password);
            ResultSet rsNhanVien = psNhanVien.executeQuery();
            if (rsNhanVien.next()) {
                return "Quản lý";
            }

            PreparedStatement psKhachHang = conn.prepareStatement(sqlKhachHang);
            psKhachHang.setString(1, username);
            psKhachHang.setString(2, password);
            ResultSet rsKhachHang = psKhachHang.executeQuery();
            if (rsKhachHang.next()) {
                return "Khách hàng";
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public User getUserByTaiKhoan(String taiKhoan) {
        String sql = "SELECT * \n"
                + "FROM TaiKhoanKH\n"
                + "JOIN KhachHang on TaiKhoanKH.MaKH = KhachHang.MaKH\n"
                + "WHERE TaiKhoan = ?";
        try (Connection conn = new DBContext().getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, taiKhoan);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String maKH = rs.getString("MaKH");
                String tenKH = rs.getString("TenKH");
                String soCCCD = rs.getString("SoCCCD");
                String emailKH = rs.getString("EmailKH");
                String soDT = rs.getString("SoDT");
                String username = rs.getString("TaiKhoan");
                String password = rs.getString("MatKhau");
                String role = "Khách hàng";
                return new User(username, password, role, maKH, tenKH, soCCCD, emailKH, soDT);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateUser(String maKH, String tenKH, String emailKH, String soDT) {
        String sql = "update khachhang set TenKH = ?, EmailKH = ?, SoDT = ? where MaKH = ?";
        try (Connection conn = new DBContext().getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, tenKH);
            ps.setString(2, emailKH);
            ps.setString(3, soDT);
            ps.setString(4, maKH);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public HoaDon getHoaDonByMaDP(int maDP) {
        String sql = "select * from hoadon\n"
                + "join phong on hoadon.MaPhong = phong.MaPhong\n"
                + "join datphong on hoadon.MaDP = datphong.MaDP\n"
                + "where datphong.madp = ?";
        try (Connection conn = new DBContext().getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maDP);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int maHoaDon = rs.getInt("maHoaDon");
                String maKH = rs.getString("maKH");
                LocalDate ngayTao = rs.getDate("ngayTao").toLocalDate();
                String trangThai = rs.getString("trangThai");
                String maPhong = rs.getString("maPhong");
                String loaiPhong = rs.getString("loaiPhong");
                double giaPhong = rs.getDouble("giaPhong");
                int tang = rs.getInt("tang");
                int soGiuong = rs.getInt("soGiuong");
                LocalDate ngayDat = rs.getDate("ngayDat").toLocalDate();
                LocalDate ngayTra = rs.getDate("ngayTra").toLocalDate();
                int soNgayThue = (int) ChronoUnit.DAYS.between(ngayDat, ngayTra);
                return new HoaDon(maHoaDon, maDP, maKH, ngayTao, trangThai,
                        maPhong, loaiPhong, giaPhong, tang, soGiuong, soNgayThue);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
