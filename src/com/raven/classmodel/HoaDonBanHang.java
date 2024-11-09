package com.raven.classmodel;

import java.util.Date;

/**
 *
 * @author Hoai Thu
 */
public class HoaDonBanHang {
    private String maHD;
    private int id;
    private Date ngayTao;
    private Date ngaySua;
    private double tongTien;
    private double thanhTien;
    private int soLuong;
    private String tenSP;
    private double giaBan;
    private String maKH;
    private String tenKH;
    private String sdtKH;
    private double soTienKhachTra;
    private double tienThua;
    private int trangThai;
    private String tenPGG;
    private int idSPCT;

    public HoaDonBanHang() {
    }

    public HoaDonBanHang(String maHD, int id, Date ngayTao, Date ngaySua, double tongTien, double thanhTien, int soLuong, String tenSP, double giaBan, String maKH, String tenKH, String sdtKH, double soTienKhachTra, double tienThua, int trangThai, String tenPGG, int idSPCT) {
        this.maHD = maHD;
        this.id = id;
        this.ngayTao = ngayTao;
        this.ngaySua = ngaySua;
        this.tongTien = tongTien;
        this.thanhTien = thanhTien;
        this.soLuong = soLuong;
        this.tenSP = tenSP;
        this.giaBan = giaBan;
        this.maKH = maKH;
        this.tenKH = tenKH;
        this.sdtKH = sdtKH;
        this.soTienKhachTra = soTienKhachTra;
        this.tienThua = tienThua;
        this.trangThai = trangThai;
        this.tenPGG = tenPGG;
        this.idSPCT = idSPCT;
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public Date getNgaySua() {
        return ngaySua;
    }

    public void setNgaySua(Date ngaySua) {
        this.ngaySua = ngaySua;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    public double getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(double thanhTien) {
        this.thanhTien = thanhTien;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public double getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(double giaBan) {
        this.giaBan = giaBan;
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

    public String getSdtKH() {
        return sdtKH;
    }

    public void setSdtKH(String sdtKH) {
        this.sdtKH = sdtKH;
    }

    public double getSoTienKhachTra() {
        return soTienKhachTra;
    }

    public void setSoTienKhachTra(double soTienKhachTra) {
        this.soTienKhachTra = soTienKhachTra;
    }

    public double getTienThua() {
        return tienThua;
    }

    public void setTienThua(double tienThua) {
        this.tienThua = tienThua;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public String getTenPGG() {
        return tenPGG;
    }

    public void setTenPGG(String tenPGG) {
        this.tenPGG = tenPGG;
    }

    public int getIdSPCT() {
        return idSPCT;
    }

    public void setIdSPCT(int idSPCT) {
        this.idSPCT = idSPCT;
    }
    
}
