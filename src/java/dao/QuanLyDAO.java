/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import com.sun.jdi.connect.spi.Connection;
import context.DBContext;
import entity.QuanLy;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Legion
 */
public class QuanLyDAO {
    @SuppressWarnings("empty-statement")
    public QuanLy getQuanLyByUsername(String taiKhoan) {
    String sqlQuanLy = "SELECT q.MaQL, q.HotenQL, q.SDT, q.Email, tk.TaiKhoan, tk.MatKhau " +
                 "FROM QuanLy q JOIN TaiKhoanQL tk ON q.MaQL = tk.MaQL " +
                 "WHERE tk.TaiKhoan = ?";

    try (java.sql.Connection conn = new DBContext().getConnection()) {
        PreparedStatement psQuanLy = conn.prepareStatement(sqlQuanLy);
        psQuanLy.setString(1, taiKhoan);
       ResultSet rs = psQuanLy.executeQuery();
        if (rs.next()) {
            return new QuanLy(
            rs.getString("MaQL"),
            rs.getString("HotenQL"),
            rs.getString("SDT"),
            rs.getString("Email"),
            rs.getString("TaiKhoan"),
            rs.getString("MatKhau")
);
        }
        
        
    } catch (Exception e) {
        e.printStackTrace();
    }
    return null;
}

}
