package com.raven.classmodel;

import java.sql.Date;

/**
 *
 * @author Hoai Thu
 */
public class DoDay {
    private int id;
    private String tenDD;
    private Date ngayTao;
    private Date ngaySua;
    private boolean trangThai;

    public DoDay() {
    }

    public DoDay(String tenDD, boolean trangThai) {
        this.tenDD = tenDD;
        this.trangThai = trangThai;
    }

    public DoDay(int id, String tenDD, boolean trangThai) {
        this.id = id;
        this.tenDD = tenDD;
        this.trangThai = trangThai;
    }

    public DoDay(int id, String tenDD, Date ngayTao, Date ngaySua, boolean trangThai) {
        this.id = id;
        this.tenDD = tenDD;
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

    public String getTenDD() {
        return tenDD;
    }

    public void setTenDD(String tenDD) {
        this.tenDD = tenDD;
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
