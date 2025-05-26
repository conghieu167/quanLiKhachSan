package dao;

import context.DBContext;
import entity.DichVu;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import entity.nhanVien;
import entity.Phong;
import java.sql.SQLException;

public class DAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    //Nhan Vien--------------------------------------------------------------------------------------------------------------------------------------------
    public List<nhanVien> getAllnhanVien() {
        List<nhanVien> list = new ArrayList<>();
        String query = "select * from NhanVien";
        try {
            conn = new DBContext().getConnection(); //Connect SQL SV
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new nhanVien(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getDouble(7)
                ));
            }
        } catch (Exception e) {

        }

        return list;
    }

    public void insertNhanVien(nhanVien nv) {
        String sql = "INSERT INTO NhanVien (maNV, hoTenNV, chucVu, maQL, sdtNV, emailNV, luong) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            conn = new DBContext().getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nv.getMaNV());
            stmt.setString(2, nv.getHoTenNV());
            stmt.setString(3, nv.getChucVu());
            stmt.setString(4, nv.getMaQL());
            stmt.setString(5, nv.getsdtnv());
            stmt.setString(6, nv.getEmailNV());
            stmt.setDouble(7, nv.getLuong());

            int rowsInserted = stmt.executeUpdate(); // INSERT thì dùng executeUpdate()

            if (rowsInserted > 0) {
                System.out.println("Insert thành công " + rowsInserted + " dòng.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteNhanVien(String maNV) {
        String query = "DELETE from NhanVien Where MaNv = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, maNV);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public nhanVien getNhanVienByMaNV(String maNV) {
        String query = "select * from nhanvien\n" + "where manv = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, maNV);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new nhanVien(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getDouble(7)
                );
            }
        } catch (Exception e) {

        }
        return null;
    }

    public void editNhanVien(String maNV, String hoTenNV, String chucVu, String maQL, String sdtnv, String emailNV, double luong) throws SQLException {
        String sql = "UPDATE nhanVien\n"
                + "SET hoTenNV = ?,\n"
                + "    chucVu = ?,\n"
                + "    maQL = ?,\n"
                + "    sdtnv = ?,\n"
                + "    emailNV = ?,\n"
                + "    luong = ?\n"
                + "WHERE maNV = ?;";
        try {
            conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, hoTenNV);
            ps.setString(2, chucVu);
            ps.setString(3, maQL);
            ps.setString(4, sdtnv);
            ps.setString(5, emailNV);
            ps.setDouble(6, luong);
            ps.setString(7, maNV);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Phong--------------------------------------------------------------------------------------------------------------------------------------------
    public List<Phong> getAllPhong() {
        List<Phong> list = new ArrayList<>();
        String query = "select * from Phong";
        try {
            conn = new DBContext().getConnection(); //Connect SQL SV
            ps = conn.prepareStatement(query);
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

        }

        return list;
    }

    public void addPhong(Phong p) {
        String sql = "INSERT INTO Phong (MaPhong, LoaiPhong, TrangThaiPhong, GiaPhong, Tang, DienTich, SoGiuong, MoTa) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = new DBContext().getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, p.getMaPhong());
            ps.setString(2, p.getLoaiPhong());
            ps.setString(3, p.getTrangThaiPhong());
            ps.setDouble(4, p.getGiaPhong());
            ps.setInt(5, p.getTang());
            ps.setDouble(6, p.getDienTich());
            ps.setInt(7, p.getSoGiuong());
            ps.setString(8, p.getMoTa());
            ps.executeUpdate();
        } catch (Exception e) {

        }
    }

    public void deletePhong(String maPhong) {
        String query = "DELETE from Phong Where MaPhong = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, maPhong);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void editPhong(Phong p) throws SQLException {
        String sql = "UPDATE Phong\n"
                + "set LoaiPhong = ?,\n"
                + "    Trangthaiphong = ?,\n"
                + "    GiaPhong = ?,\n"
                + "    Tang = ?,\n"
                + "    DienTich = ?,\n"
                + "    SoGiuong = ?,\n"
                + "    Mota = ?\n"
                + "where MaPhong = ?";
        try (Connection conn = new DBContext().getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(8, p.getMaPhong());
            ps.setString(1, p.getLoaiPhong());
            ps.setString(2, p.getTrangThaiPhong());
            ps.setDouble(3, p.getGiaPhong());
            ps.setInt(4, p.getTang());
            ps.setDouble(5, p.getDienTich());
            ps.setInt(6, p.getSoGiuong());
            ps.setString(7, p.getMoTa());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Phong getPhongByMa(String maPhong) {
        String sql = "SELECT * FROM Phong WHERE MaPhong = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, maPhong);
            rs = ps.executeQuery();
            if (rs.next()) {
                return new Phong(
                        rs.getString(1), // maPhong
                        rs.getString(2), // loaiPhong
                        rs.getString(3), // moTa
                        rs.getDouble(4), // giaPhong
                        rs.getInt(5), // tang
                        rs.getDouble(6), // dienTich
                        rs.getInt(7), // soGiuong
                        rs.getString(8) // trangThai
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //Dich Vu----------------------------------------------------------------------------------------------------------------------------------------------------------------
    public List<DichVu> getAllDichVu() {
        List<DichVu> list = new ArrayList<>();
        String query = "select * from DichVu";
        try {
            conn = new DBContext().getConnection(); //Connect SQL SV
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new DichVu(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getString(4)
                ));
            }
        } catch (Exception e) {

        }

        return list;
    }

    public int editDichVu(DichVu dv) throws SQLException {
        String sql = "UPDATE DichVu\n"
                + "SET GiaTien = ?,\n"
                + "    ChiTiet = ?\n"
                + "WHERE MaDV = ?";

        try (Connection conn = new DBContext().getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            // Set tham số cho câu lệnh SQL
            ps.setDouble(1, dv.getGiaTien());   // GiaTien
            ps.setString(2, dv.getChiTiet());   // ChiTiet
            ps.setString(3, dv.getMaDV());      // MaDV (mã dịch vụ là khóa chính)

            // Thực thi câu lệnh update và nhận số dòng bị ảnh hưởng
            return ps.executeUpdate();
        } catch (SQLException e) {
            // In ra lỗi nếu có
            e.printStackTrace();
            return 0; // Trả về 0 nếu có lỗi
        }
    }

    public void insertKhachHang(String maKH, String tenKH, String email, String soDT, String soCCCD) {
        String insertKH = "INSERT INTO KhachHang (MaKH, TenKH, SoCCCD, EmailKH , SoDT) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = new DBContext().getConnection(); PreparedStatement psKH = conn.prepareStatement(insertKH)) {

            psKH.setString(1, maKH);
            psKH.setString(2, tenKH);
            psKH.setString(3, email);
            psKH.setString(4, soDT);
            psKH.setString(5, soCCCD);
            psKH.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertTaiKhoanKH(String maKH, String TaiKhoan, String MatKhau) {
        String sql = "INSERT INTO TaiKhoanKH (MaKH, TaiKhoan, MatKhau) VALUES (?, ?, ?)";
        try (Connection conn = new DBContext().getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, maKH);
            ps.setString(2, TaiKhoan);
            ps.setString(3, MatKhau); // Gợi ý: nên mã hóa trước khi lưu
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
public static boolean isExist(String taiKhoan) {
    String sql = "SELECT * FROM TaiKhoanKH WHERE TaiKhoan = ? " +
                 "UNION SELECT * FROM TaiKhoanQL WHERE TaiKhoan = ?";
    try (Connection conn = new DBContext().getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setString(1, taiKhoan);
        ps.setString(2, taiKhoan);
        ResultSet rs = ps.executeQuery();

        return rs.next();
    } catch (Exception e) {
        e.printStackTrace();
    }
    return false;
}
}
