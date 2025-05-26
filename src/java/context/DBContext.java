package context;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBContext {

    public Connection getConnection() {
        // Thông tin kết nối
        String url = "jdbc:sqlserver://localhost:1433;databaseName=QuanLyKhachSan;encrypt=true;trustServerCertificate=true";
        String username = "sa";
        String password = "hieupro10xyz";

        try {
            // Nạp driver JDBC
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            // Tạo và trả về kết nối
            return DriverManager.getConnection(url, username, password);

        } catch (ClassNotFoundException e) {
            System.out.println("🚫 Không tìm thấy JDBC Driver: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("🚫 Lỗi kết nối: " + e.getMessage());
        }
        return null; // Trả về null nếu lỗi
    }
}
