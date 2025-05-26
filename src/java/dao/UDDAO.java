/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import context.DBContext;
import entity.DatDichVu;
import entity.Phong;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author ADMIN
 */
public class UDDAO {

    public int getPhongDaDat(String maKH) {
        String sql = "SELECT COUNT(*) AS sodatphong FROM datphong WHERE makh = ?";

        try (Connection conn = new DBContext().getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, maKH);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("sodatphong"); // lấy theo alias
            }
        }catch (Exception e){
            
        }
        return 0; // nếu không có kết quả thì trả về 0
    }

    public int getDichVuSuDung(String maKH) {
        String sql = "SELECT COUNT(*) AS sodichvu FROM datdichvu WHERE makh = ?";
        try (Connection conn = new DBContext().getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, maKH);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("sodichvu"); // lấy theo alias
            }
        }catch (Exception e){
            
        }
        return 0; // nếu không có kết quả thì trả về 0
    }

    public int getChiTieu(String maKH){
        String sql = "SELECT SUM(tongtien) AS tongtien\n"
                + "FROM hoadon\n"
                + "WHERE makh = ?";

        try (Connection conn = new DBContext().getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, maKH);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("tongtien"); // lấy theo alias
            }
        }catch (Exception e){
            
        }
        return 0; // nếu không có kết quả thì trả về 0
    }
    public Phong getLatestRoom(String maKH) {
    Phong phong = null;
    String sql = "SELECT TOP 1 " +
                 "phong.MaPhong, phong.LoaiPhong, phong.TrangThaiPhong, phong.GiaPhong, " +
                 "phong.Tang, phong.DienTich, phong.SoGiuong, phong.MoTa " +
                 "FROM datphong " +
                 "JOIN phong ON phong.MaPhong = datphong.MaPhong " +
                 "WHERE datphong.MaKH = ? " +
                 "ORDER BY datphong.MaDP DESC";

    try (Connection conn = new DBContext().getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setString(1, maKH);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            phong = new Phong(
                rs.getString("MaPhong"),
                rs.getString("LoaiPhong"),
                rs.getString("TrangThaiPhong"),
                rs.getDouble("GiaPhong"),
                rs.getInt("Tang"),
                rs.getDouble("DienTich"),
                rs.getInt("SoGiuong"),
                rs.getString("MoTa")
            );
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    return phong;
}

}
