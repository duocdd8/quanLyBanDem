package com.raven.classmodel;

import java.sql.Date;

/**
 *
 * @author Hoai Thu
 */
public class XuatXu {

    private int id;
    private String tenXX;
    private Date ngayTao;
    private Date ngaySua;
    private boolean trangThai;

    public XuatXu() {
    }

    public XuatXu(String tenXX, boolean trangThai) {
        this.tenXX = tenXX;
        this.trangThai = trangThai;
    }

    public XuatXu(int id, String tenXX, boolean trangThai) {
        this.id = id;
        this.tenXX = tenXX;
        this.trangThai = trangThai;
    }

    public XuatXu(int id, String tenXX, Date ngayTao, Date ngaySua, boolean trangThai) {
        this.id = id;
        this.tenXX = tenXX;
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

    public String getTenXX() {
        return tenXX;
    }

    public void setTenXX(String tenXX) {
        this.tenXX = tenXX;
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
