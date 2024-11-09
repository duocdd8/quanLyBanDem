package com.raven.classmodel;

import java.sql.Date;

/**
 *
 * @author Hoai Thu
 */
public class MauSac {
    private int id;
    private String tenMS;
    private Date ngayTao;
    private Date ngaySua;
    private boolean trangThai;

    public MauSac() {
    }

    public MauSac(String tenMS, boolean trangThai) {
        this.tenMS = tenMS;
        this.trangThai = trangThai;
    }

    public MauSac(int id, String tenMS, Date ngayTao, Date ngaySua, boolean trangThai) {
        this.id = id;
        this.tenMS = tenMS;
        this.ngayTao = ngayTao;
        this.ngaySua = ngaySua;
        this.trangThai = trangThai;
    }

    public MauSac(int id, String tenMS, boolean trangThai) {
        this.id = id;
        this.tenMS = tenMS;
        this.trangThai = trangThai;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenMS() {
        return tenMS;
    }

    public void setTenMS(String tenMS) {
        this.tenMS = tenMS;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
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
    
}
