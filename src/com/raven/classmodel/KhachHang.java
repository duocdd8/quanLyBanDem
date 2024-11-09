package com.raven.classmodel;

/**
 *
 * @author Hoai Thu
 */
public class KhachHang {
    private int id;
    private String hoTen;
    private boolean gioiTinh;
    private String soDT;
    private String email;
    private String diaChi;
    private String thanhPho;
    private boolean trangThai;

    public KhachHang() {
    }

    public KhachHang(int id, String hoTen, boolean gioiTinh, String soDT, String email, String diaChi, String thanhPho) {
        this.id = id;
        this.hoTen = hoTen;
        this.gioiTinh = gioiTinh;
        this.soDT = soDT;
        this.email = email;
        this.diaChi = diaChi;
        this.thanhPho = thanhPho;
    }

    public KhachHang(String hoTen, boolean gioiTinh, String soDT, String email, String diaChi, String thanhPho) {
        this.hoTen = hoTen;
        this.gioiTinh = gioiTinh;
        this.soDT = soDT;
        this.email = email;
        this.diaChi = diaChi;
        this.thanhPho = thanhPho;
    }

    public KhachHang(int id, String hoTen, boolean gioiTinh, String soDT, String email, String diaChi, String thanhPho, boolean trangThai) {
        this.id = id;
        this.hoTen = hoTen;
        this.gioiTinh = gioiTinh;
        this.soDT = soDT;
        this.email = email;
        this.diaChi = diaChi;
        this.thanhPho = thanhPho;
        this.trangThai = trangThai;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public boolean isGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getSoDT() {
        return soDT;
    }

    public void setSoDT(String soDT) {
        this.soDT = soDT;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getThanhPho() {
        return thanhPho;
    }

    public void setThanhPho(String thanhPho) {
        this.thanhPho = thanhPho;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
   
}
