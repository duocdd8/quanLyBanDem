package com.raven.classmodel;

import java.sql.Date;

/**
 *
 * @author Hoai Thu
 */
public class ThietKe {
    private int id;
    private String tenTK;
    private Date ngayTao;
    private Date ngaySua;
    private boolean trangThai;

    public ThietKe() {
    }

    public ThietKe(String tenTK, boolean trangThai) {
        this.tenTK = tenTK;
        this.trangThai = trangThai;
    }

    public ThietKe(int id, String tenMS, boolean trangThai) {
        this.id = id;
        this.tenTK = tenMS;
        this.trangThai = trangThai;
    }

    public ThietKe(int id, String tenTK, Date ngayTao, Date ngaySua, boolean trangThai) {
        this.id = id;
        this.tenTK = tenTK;
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

    public String getTenTK() {
        return tenTK;
    }

    public void setTenTK(String tenTK) {
        this.tenTK = tenTK;
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
