package entity;

public class Phong {
    private String maPhong;          // MaPhong - Mã phòng
    private String loaiPhong;        // LoaiPhong - Loại phòng
    private String trangThaiPhong;   // TrangThaiPhong - Trạng thái phòng
    private double giaPhong;         // GiaPhong - Giá phòng
    private int tang;                // Tang - Tầng phòng
    private double dienTich;         // DienTich - Diện tích phòng
    private int soGiuong;            // SoGiuong - Số giường trong phòng
    private String moTa;             // MoTa - Mô tả phòng

    // Constructor
    public Phong(String maPhong, String loaiPhong, String trangThaiPhong, 
                 double giaPhong, int tang, double dienTich, int soGiuong, String moTa) {
        this.maPhong = maPhong;
        this.loaiPhong = loaiPhong;
        this.trangThaiPhong = trangThaiPhong;
        this.giaPhong = giaPhong;
        this.tang = tang;
        this.dienTich = dienTich;
        this.soGiuong = soGiuong;
        this.moTa = moTa;
    }

    // Getter và Setter
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

    public String getTrangThaiPhong() {
        return trangThaiPhong;
    }

    public void setTrangThaiPhong(String trangThaiPhong) {
        this.trangThaiPhong = trangThaiPhong;
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

    public double getDienTich() {
        return dienTich;
    }

    public void setDienTich(double dienTich) {
        this.dienTich = dienTich;
    }

    public int getSoGiuong() {
        return soGiuong;
    }

    public void setSoGiuong(int soGiuong) {
        this.soGiuong = soGiuong;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    // Optional: Override toString for better output formatting
    @Override
    public String toString() {
        return "Phong{" +
               "maPhong='" + maPhong + '\'' +
               ", loaiPhong='" + loaiPhong + '\'' +
               ", trangThaiPhong='" + trangThaiPhong + '\'' +
               ", giaPhong=" + giaPhong +
               ", tang=" + tang +
               ", dienTich=" + dienTich +
               ", soGiuong=" + soGiuong +
               ", moTa='" + moTa + '\'' +
               '}';
    }
}
