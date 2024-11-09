package com.raven.classmodel;

import java.util.Date;

public class ThongKe {

    private int id;
    private int idKH;
    private int idNV;
    private int idPGG;
    private int idHTTT;
    private double tongTien;
    private Date ngayThanhToan;
    private Date ngayTao;
    private Date ngaySua;
    private int trangThai;

    public ThongKe() {
    }

    public ThongKe(int id, int idKH, int idNV, int idPGG, int idHTTT, double tongTien, Date ngayThanhToan, Date ngayTao, Date ngaySua, int trangThai) {
        this.id = id;
        this.idKH = idKH;
        this.idNV = idNV;
        this.idPGG = idPGG;
        this.idHTTT = idHTTT;
        this.tongTien = tongTien;
        this.ngayThanhToan = ngayThanhToan;
        this.ngayTao = ngayTao;
        this.ngaySua = ngaySua;
        this.trangThai = trangThai;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdKH() {
        return idKH;
    }

    public void setIdKH(int idKH) {
        this.idKH = idKH;
    }

    public int getIdNV() {
        return idNV;
    }

    public void setIdNV(int idNV) {
        this.idNV = idNV;
    }

    public int getIdPGG() {
        return idPGG;
    }

    public void setIdPGG(int idPGG) {
        this.idPGG = idPGG;
    }

    public int getIdHTTT() {
        return idHTTT;
    }

    public void setIdHTTT(int idHTTT) {
        this.idHTTT = idHTTT;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    public Date getNgayThanhToan() {
        return ngayThanhToan;
    }

    public void setNgayThanhToan(Date ngayThanhToan) {
        this.ngayThanhToan = ngayThanhToan;
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

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }
    
    

}
