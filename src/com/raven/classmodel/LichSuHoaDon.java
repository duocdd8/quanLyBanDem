package com.raven.classmodel;

/**
 *
 * @author Hoai Thu
 */
public class LichSuHoaDon {
    private int id;
    private String maLichsuhoadon;
    private String liDohuy;
    private HoaDon hd;
    private int trangthai;

    public LichSuHoaDon() {
    }

    public LichSuHoaDon(int id, String maLichsuhoadon, String liDohuy, HoaDon hd, int trangthai) {
        this.id = id;
        this.maLichsuhoadon = maLichsuhoadon;
        this.liDohuy = liDohuy;
        this.hd = hd;
        this.trangthai = trangthai;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaLichsuhoadon() {
        return maLichsuhoadon;
    }

    public void setMaLichsuhoadon(String maLichsuhoadon) {
        this.maLichsuhoadon = maLichsuhoadon;
    }

    public String getLiDohuy() {
        return liDohuy;
    }

    public void setLiDohuy(String liDohuy) {
        this.liDohuy = liDohuy;
    }

    public HoaDon getHd() {
        return hd;
    }

    public void setHd(HoaDon hd) {
        this.hd = hd;
    }

    public int getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }
}
