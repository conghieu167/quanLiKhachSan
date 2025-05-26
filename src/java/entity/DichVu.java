/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

public class DichVu {
    private String maDV;
    private String tenDV;
    private int giaTien;
    private String chiTiet;

    // Constructor
    public DichVu(String maDV, String tenDV, int giaTien, String chiTiet) {
        this.maDV = maDV;
        this.tenDV = tenDV;
        this.giaTien = giaTien;
        this.chiTiet = chiTiet;
    }

    // Getter and Setter for maDV
    public String getMaDV() {
        return maDV;
    }

    public void setMaDV(String maDV) {
        this.maDV = maDV;
    }

    // Getter and Setter for tenDV
    public String getTenDV() {
        return tenDV;
    }

    public void setTenDV(String tenDV) {
        this.tenDV = tenDV;
    }

    // Getter and Setter for giaTien
    public int getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(int giaTien) {
        this.giaTien = giaTien;
    }

    // Getter and Setter for chiTiet
    public String getChiTiet() {
        return chiTiet;
    }

    public void setChiTiet(String chiTiet) {
        this.chiTiet = chiTiet;
    }

    // toString method
    @Override
    public String toString() {
        return "DichVu{" +
               "maDV='" + maDV + '\'' +
               ", tenDV='" + tenDV + '\'' +
               ", giaTien=" + giaTien +
               ", chiTiet='" + chiTiet + '\'' +
               '}';
    }
}
