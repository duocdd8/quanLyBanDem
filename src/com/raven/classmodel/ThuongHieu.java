package com.raven.classmodel;

import java.sql.Date;

/**
 *
 * @author Hoai Thu
 */
public class ThuongHieu {
    private int id;
    private String tenTH;
    private Date ngayTao;
    private Date ngaySua;
    private boolean trangThai;

    public ThuongHieu() {
    }

    public ThuongHieu(String tenTH, boolean trangThai) {
        this.tenTH = tenTH;
        this.trangThai = trangThai;
    }

    public ThuongHieu(int id, String tenTH, boolean trangThai) {
        this.id = id;
        this.tenTH = tenTH;
        this.trangThai = trangThai;
    }

    public ThuongHieu(int id, String tenTH, Date ngayTao, Date ngaySua, boolean trangThai) {
        this.id = id;
        this.tenTH = tenTH;
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
    
    public String getTenTH() {
        return tenTH;
    }

    public void setTenTH(String tenTH) {
        this.tenTH = tenTH;
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
