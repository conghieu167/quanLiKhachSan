package entity;

import java.time.LocalDate;

public class HoaDon {
    private int maHoaDon;
    private int maDP;
    private String maKH;
    private LocalDate ngayTao;
    private String trangThai;

    // Thông tin phòng
    private String maPhong;
    private String loaiPhong;
    private double giaPhong;
    private int tang;
    private int soGiuong;
    private int soNgayThue;

    // Constructor
    public HoaDon(int maHoaDon, int maDP, String maKH, LocalDate ngayTao, String trangThai,
                  String maPhong, String loaiPhong, double giaPhong, int tang, int soGiuong, int soNgayThue) {
        this.maHoaDon = maHoaDon;
        this.maDP = maDP;
        this.maKH = maKH;
        this.ngayTao = ngayTao;
        this.trangThai = trangThai;
        this.maPhong = maPhong;
        this.loaiPhong = loaiPhong;
        this.giaPhong = giaPhong;
        this.tang = tang;
        this.soGiuong = soGiuong;
        this.soNgayThue = soNgayThue;
    }

    public double tinhTongTien() {
        return giaPhong * soNgayThue;
    }

    // Getters và Setters
    public int getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(int maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public int getMaDP() {
        return maDP;
    }

    public void setMaDP(int maDP) {
        this.maDP = maDP;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public LocalDate getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(LocalDate ngayTao) {
        this.ngayTao = ngayTao;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(String maPhong) {
        this.maPhong = maPhong;
    }

    public String getLoaiPhong() {
        return loaiPhong;
    }

    public void setLoaiPhong(String loaiPhong) {
        this.loaiPhong = loaiPhong;
    }

    public double getGiaPhong() {
        return giaPhong;
    }

    public void setGiaPhong(double giaPhong) {
        this.giaPhong = giaPhong;
    }

    public int getTang() {
        return tang;
    }

    public void setTang(int tang) {
        this.tang = tang;
    }

    public int getSoGiuong() {
        return soGiuong;
    }

    public void setSoGiuong(int soGiuong) {
        this.soGiuong = soGiuong;
    }

    public int getSoNgayThue() {
        return soNgayThue;
    }

    public void setSoNgayThue(int soNgayThue) {
        this.soNgayThue = soNgayThue;
    }

    @Override
    public String toString() {
        return "Mã Hóa Đơn: " + maHoaDon +
               "\nMã Đặt Phòng: " + maDP +
               "\nMã Khách Hàng: " + maKH +
               "\nNgày Tạo: " + ngayTao +
               "\nTrạng Thái: " + trangThai +
               "\n--- Thông tin phòng ---" +
               "\nMã Phòng: " + maPhong +
               "\nLoại Phòng: " + loaiPhong +
               "\nGiá Phòng: " + String.format("%,.0fđ", giaPhong) +
               "\nTầng: " + tang +
               "\nSố Giường: " + soGiuong +
               "\nSố Ngày Thuê: " + soNgayThue +
               "\nTổng Tiền: " + String.format("%,.0fđ", tinhTongTien());
    }
}
