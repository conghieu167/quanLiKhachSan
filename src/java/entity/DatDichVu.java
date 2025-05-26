package entity;

import java.time.LocalDate;

public class DatDichVu {
    private int maDDV;
    private String maKH;
    private String maDV;
    private String tenDV;     // thêm
    private int giaTien;      // thêm
    private LocalDate ngayDat;

    // Constructor có đầy đủ tham số
    public DatDichVu(int maDDV, String maKH, String maDV, String tenDV, int giaTien, LocalDate ngayDat) {
        this.maDDV = maDDV;
        this.maKH = maKH;
        this.maDV = maDV;
        this.tenDV = tenDV;
        this.giaTien = giaTien;
        this.ngayDat = ngayDat;
    }

    // Constructor cũ nếu bạn vẫn cần dùng
    public DatDichVu(int maDDV, String maKH, String maDV, LocalDate ngayDat) {
        this.maDDV = maDDV;
        this.maKH = maKH;
        this.maDV = maDV;
        this.ngayDat = ngayDat;
    }

    // Getters and Setters
    public int getMaDDV() {
        return maDDV;
    }

    public void setMaDDV(int maDDV) {
        this.maDDV = maDDV;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getMaDV() {
        return maDV;
    }

    public void setMaDV(String maDV) {
        this.maDV = maDV;
    }

    public String getTenDV() {
        return tenDV;
    }

    public void setTenDV(String tenDV) {
        this.tenDV = tenDV;
    }

    public int getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(int giaTien) {
        this.giaTien = giaTien;
    }

    public LocalDate getNgayDat() {
        return ngayDat;
    }

    public void setNgayDat(LocalDate ngayDat) {
        this.ngayDat = ngayDat;
    }

    @Override
    public String toString() {
        return "DatDichVu{" +
                "maDDV=" + maDDV +
                ", maKH='" + maKH + '\'' +
                ", maDV='" + maDV + '\'' +
                ", tenDV='" + tenDV + '\'' +
                ", giaTien=" + giaTien +
                ", ngayDat=" + ngayDat +
                '}';
    }
}
