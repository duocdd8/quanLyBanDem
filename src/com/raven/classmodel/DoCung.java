package com.raven.classmodel;

import java.sql.Date;

/**
 *
 * @author Hoai Thu
 */
public class DoCung {
    private int id;
    private String tenDC;
    private Date ngayTao;
    private Date ngaySua;
    private boolean trangThai;

    public DoCung() {
    }

    public DoCung(String tenDC, boolean trangThai) {
        this.tenDC = tenDC;
        this.trangThai = trangThai;
    }

    public DoCung(int id, String tenDC, boolean trangThai) {
        this.id = id;
        this.tenDC = tenDC;
        this.trangThai = trangThai;
    }

    public DoCung(int id, String tenDC, Date ngayTao, Date ngaySua, boolean trangThai) {
        this.id = id;
        this.tenDC = tenDC;
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

    public String getTenDC() {
        return tenDC;
    }

    public void setTenDC(String tenDC) {
        this.tenDC = tenDC;
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
