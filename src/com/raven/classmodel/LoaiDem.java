package com.raven.classmodel;

import java.sql.Date;

/**
 *
 * @author Hoai Thu
 */
public class LoaiDem {
    private int id;
    private String tenLD;
    private Date ngayTao;
    private Date ngaySua;
    private boolean trangThai;

    public LoaiDem() {
    }

    public LoaiDem(String tenLD, boolean trangThai) {
        this.tenLD = tenLD;
        this.trangThai = trangThai;
    }

    public LoaiDem(int id, String tenLD, boolean trangThai) {
        this.id = id;
        this.tenLD = tenLD;
        this.trangThai = trangThai;
    }

    public LoaiDem(int id, String tenLD, Date ngayTao, Date ngaySua, boolean trangThai) {
        this.id = id;
        this.tenLD = tenLD;
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

    public String getTenLD() {
        return tenLD;
    }

    public void setTenLD(String tenLD) {
        this.tenLD = tenLD;
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
