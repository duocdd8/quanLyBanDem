
package com.raven.classmodel;

import java.util.Date;


public class NhanVien {
    private int id;
    private String hoTen;
    private boolean gioiTinh;
    private Date ngaySinh;
    private String email;
    private String cccd;
    private String sdt;
    private String tenDN;
    private String matKhau;
    private String diaChi;
    private boolean chucVu;
    private boolean trangThai;

    public NhanVien() {
    }

    public NhanVien(int id, String hoTen, boolean gioiTinh, Date ngaySinh, String email, String cccd, String sdt, String tenDN, String matKhau, String diaChi, boolean chucVu) {
        this.id = id;
        this.hoTen = hoTen;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.email = email;
        this.cccd = cccd;
        this.sdt = sdt;
        this.tenDN = tenDN;
        this.matKhau = matKhau;
        this.diaChi = diaChi;
        this.chucVu = chucVu;
    }
    
    

    public NhanVien(String hoTen, boolean gioiTinh, Date ngaySinh, String email, String cccd, String sdt, String tenDN, String matKhau, String diaChi, boolean chucVu) {
        this.hoTen = hoTen;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.email = email;
        this.cccd = cccd;
        this.sdt = sdt;
        this.tenDN = tenDN;
        this.matKhau = matKhau;
        this.diaChi = diaChi;
        this.chucVu = chucVu;
    }
    
    

    public NhanVien(int id, String hoTen, boolean gioiTinh, Date ngaySinh, String email, String cccd, String sdt, String tenDN, String matKhau, String diaChi, boolean chucVu, boolean trangThai) {
        this.id = id;
        this.hoTen = hoTen;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.email = email;
        this.cccd = cccd;
        this.sdt = sdt;
        this.tenDN = tenDN;
        this.matKhau = matKhau;
        this.diaChi = diaChi;
        this.chucVu = chucVu;
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

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCccd() {
        return cccd;
    }

    public void setCccd(String cccd) {
        this.cccd = cccd;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getTenDN() {
        return tenDN;
    }

    public void setTenDN(String tenDN) {
        this.tenDN = tenDN;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public boolean isChucVu() {
        return chucVu;
    }

    public void setChucVu(boolean chucVu) {
        this.chucVu = chucVu;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }
    
    
}
