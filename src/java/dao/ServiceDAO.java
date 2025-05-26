/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.ResultSet;

import context.DBContext;
import entity.DatDichVu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class ServiceDAO {

    public boolean datDichVu(String maKH, String maDV, LocalDate ngayDat) throws Exception {
        String sql = "INSERT INTO datdichvu (MaKH, MaDV, NgayDat) VALUES (?, ?, ?)";
        try (Connection conn = new DBContext().getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maKH);
            ps.setString(2, maDV);
            ps.setDate(3, java.sql.Date.valueOf(ngayDat));  // Chuyển LocalDate sang java.sql.Date
            int rows = ps.executeUpdate();
            return rows > 0;
        }
    }

    public List<DatDichVu> getBookingsByUser(String maKH) {
        List<DatDichVu> list = new ArrayList<>();
        String sql = "select * from DatDichVu dv \n"
                + "join dichvu on dv.MaDV = dichvu.MaDV\n"
                + "where makh=?"
                + "order by maddv desc";

        try (Connection conn = new DBContext().getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, maKH);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                DatDichVu booking = new DatDichVu(
                        rs.getInt("MaDDV"),
                        rs.getString("MaKH"),
                        rs.getString("MaDV"),
                        rs.getString("TenDV"),
                        rs.getInt("GiaTien"),
                        rs.getDate("NgayDat").toLocalDate()
                );
                list.add(booking);
            }

            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
