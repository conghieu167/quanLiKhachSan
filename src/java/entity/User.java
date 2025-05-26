package entity;

public class User {
    private String username;
    private String password;
    private String role;

    private String maKH;     // Mã khách hàng
    private String tenKH;    // Tên khách hàng
    private String soCCCD;   // Số CCCD
    private String emailKH;  // Email
    private String soDT;     // Số điện thoại

    public User(String username, String password, String role,
                String maKH, String tenKH, String soCCCD, String emailKH, String soDT) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.maKH = maKH;
        this.tenKH = tenKH;
        this.soCCCD = soCCCD;
        this.emailKH = emailKH;
        this.soDT = soDT;
    }

    // Constructor rút gọn nếu chỉ cần tài khoản
    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getSoCCCD() {
        return soCCCD;
    }

    public void setSoCCCD(String soCCCD) {
        this.soCCCD = soCCCD;
    }

    public String getEmailKH() {
        return emailKH;
    }

    public void setEmailKH(String emailKH) {
        this.emailKH = emailKH;
    }

    public String getSoDT() {
        return soDT;
    }

    public void setSoDT(String soDT) {
        this.soDT = soDT;
    }
}
