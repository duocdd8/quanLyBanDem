package com.raven.viewmodel;

import java.sql.Date;

/**
 *
 * @author Hoai Thu
 */
public class ViewLichSuHoaDon {
    private int id;
    private String nv;
    private String khachhang;
    private Date ngaythanhtoan;
    private Date ngaytao;
    
    public ViewLichSuHoaDon() {
    }

    public ViewLichSuHoaDon(int id, String nv, String khachhang, Date ngaythanhtoan, Date ngaytao) {
        this.id = id;
        this.nv = nv;
        this.khachhang = khachhang;
        this.ngaythanhtoan = ngaythanhtoan;
        this.ngaytao = ngaytao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNv() {
        return nv;
    }

    public void setNv(String nv) {
        this.nv = nv;
    }

    public String getKhachhang() {
        return khachhang;
    }

    public void setKhachhang(String khachhang) {
        this.khachhang = khachhang;
    }

    public Date getNgaythanhtoan() {
        return ngaythanhtoan;
    }

    public void setNgaythanhtoan(Date ngaythanhtoan) {
        this.ngaythanhtoan = ngaythanhtoan;
    }

    public Date getNgaytao() {
        return ngaytao;
    }

    public void setNgaytao(Date ngaytao) {
        this.ngaytao = ngaytao;
    }
}
