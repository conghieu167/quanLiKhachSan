/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;
    public class QuanLy {
    private String maQL;
    private String hoTenQL;
    private String sdt;
    private String email;
    private String taiKhoan;
    private String matKhau;

    // Constructor
    public QuanLy(String maQL, String hoTenQL, String sdt, String email, String taiKhoan, String matKhau) {
        this.maQL = maQL;
        this.hoTenQL = hoTenQL;
        this.sdt = sdt;
        this.email = email;
        this.taiKhoan = taiKhoan;
        this.matKhau = matKhau;
    }

    // Getters và Setters
    public String getMaQL() {
        return maQL;
    }

    public void setMaQL(String maQL) {
        this.maQL = maQL;
    }

    public String getHoTenQL() {
        return hoTenQL;
    }

    public void setHoTenQL(String hoTenQL) {
        this.hoTenQL = hoTenQL;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(String taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    @Override
    public String toString() {
        return "QuanLy{" +
                "maQL='" + maQL + '\'' +
                ", hoTenQL='" + hoTenQL + '\'' +
                ", sdt='" + sdt + '\'' +
                ", email='" + email + '\'' +
                ", taiKhoan='" + taiKhoan + '\'' +
                ", matKhau='" + matKhau + '\'' +
                '}'; }
    }