/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.time.LocalDate;

/**
 *
 * @author ADMIN
 */

public class DatPhong {
    private int maDP;
    private String maPhong;
    private String maKH;
    private LocalDate ngayDat;
    private LocalDate ngayTra;
    private String trangThai;

    public DatPhong() {}
    public DatPhong(int maDP, String maPhong, String maKH, LocalDate ngayDat, LocalDate ngayTra, String trangThai) {
        this.maDP = maDP;
        this.maPhong = maPhong;
        this.maKH = maKH;
        this.ngayDat = ngayDat;
        this.ngayTra = ngayTra;
        this.trangThai = trangThai;
    }

    public DatPhong(String maPhong, String maKH, LocalDate ngayDat, LocalDate ngayTra, String trangThai) {
        this.maPhong = maPhong;
        this.maKH = maKH;
        this.ngayDat = ngayDat;
        this.ngayTra = ngayTra;
        this.trangThai = trangThai;
    }

    public int getMaDP() {
        return maDP;
    }

    public void setMaDP(int maDP) {
        this.maDP = maDP;
    }

    public String getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(String maPhong) {
        this.maPhong = maPhong;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public LocalDate getNgayDat() {
        return ngayDat;
    }

    public void setNgayDat(LocalDate ngayDat) {
        this.ngayDat = ngayDat;
    }

    public LocalDate getNgayTra() {
        return ngayTra;
    }

    public void setNgayTra(LocalDate ngayTra) {
        this.ngayTra = ngayTra;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
}