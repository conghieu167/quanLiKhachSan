package entity;

public class nhanVien {
    private String maNV;
    private String hoTenNV;
    private String chucVu;
    private String maQL;
    private String sdtnv;
    private String emailNV;
    private double luong;
    
    public nhanVien(){
        
    }

    public nhanVien(String maNV, String hoTenNV, String chucVu, String maQL, String sdtnv, String emailNV, double luong) {
        this.maNV = maNV;
        this.hoTenNV = hoTenNV;
        this.chucVu = chucVu;
        this.maQL = maQL;
        this.sdtnv = sdtnv;
        this.emailNV = emailNV;
        this.luong = luong;
    }

    public String getMaNV() {
        return maNV;
    }

    public String getHoTenNV() {
        return hoTenNV;
    }

    public String getChucVu() {
        return chucVu;
    }

    public String getMaQL() {
        return maQL;
    }

    public String getsdtnv() {
        return sdtnv;
    }

    public String getEmailNV() {
        return emailNV;
    }

    public double getLuong() {
        return luong;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public void setHoTenNV(String hoTenNV) {
        this.hoTenNV = hoTenNV;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }

    public void setMaQL(String maQL) {
        this.maQL = maQL;
    }

    public void setsdtnv(String sdtnv) {
        this.sdtnv = sdtnv;
    }

    public void setEmailNV(String emailNV) {
        this.emailNV = emailNV;
    }

    public void setLuong(double luong) {
        this.luong = luong;
    }

    @Override
    public String toString() {
        return "nhanVien{" + "maNV=" + maNV + ", hoTenNV=" + hoTenNV + ", chucVu=" + chucVu + ", maQL=" + maQL + ", sdtnv=" + sdtnv + ", emailNV=" + emailNV + ", luong=" + luong + '}';
    }
}


