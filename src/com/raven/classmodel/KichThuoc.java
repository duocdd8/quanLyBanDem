package com.raven.classmodel;

import java.sql.Date;

/**
 *
 * @author Hoai Thu
 */
public class KichThuoc {
    private int id;
    private String tenKT;
    private Date ngayTao;
    private Date ngaySua;
    private boolean trangThai;

    public KichThuoc() {
    }

    public KichThuoc(String tenKT, boolean trangThai) {
        this.tenKT = tenKT;
        this.trangThai = trangThai;
    }

    public KichThuoc(int id, String tenKT, boolean trangThai) {
        this.id = id;
        this.tenKT = tenKT;
        this.trangThai = trangThai;
    }

    public KichThuoc(int id, String tenKT, Date ngayTao, Date ngaySua, boolean trangThai) {
        this.id = id;
        this.tenKT = tenKT;
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

    public String getTenKT() {
        return tenKT;
    }

    public void setTenKT(String tenKT) {
        this.tenKT = tenKT;
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
