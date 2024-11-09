package com.raven.classmodel;

import java.util.Date;

/**
 *
 * @author Hoai Thu
 */
public class SanPhamChiTiet {

    private int id;
    private String idSanPham;
    private SanPham sp;
    private int soLuong;
    private double giaBan;
    private int namBaoHanh;
    private Date ngaySanXuat;
    private String idLoaiDem;
    private String idXuatXu;
    private String idThuongHieu;
    private String idDoDay;
    private String idDoCung;
    private String idThietKe;
    private String idKichThuoc;
    private String idMauSac;
    private double thanhTien;

    public SanPhamChiTiet() {
    }

    public SanPhamChiTiet(String idSanPham, int soLuong, double giaBan, int namBaoHanh, Date ngaySanXuat, String idLoaiDem, String idXuatXu, String idThuongHieu, String idDoDay, String idDoCung, String idThietKe, String idKichThuoc, String idMauSac) {
        this.idSanPham = idSanPham;
        this.soLuong = soLuong;
        this.giaBan = giaBan;
        this.namBaoHanh = namBaoHanh;
        this.ngaySanXuat = ngaySanXuat;
        this.idLoaiDem = idLoaiDem;
        this.idXuatXu = idXuatXu;
        this.idThuongHieu = idThuongHieu;
        this.idDoDay = idDoDay;
        this.idDoCung = idDoCung;
        this.idThietKe = idThietKe;
        this.idKichThuoc = idKichThuoc;
        this.idMauSac = idMauSac;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdSanPham() {
        return idSanPham;
    }

    public void setIdSanPham(String idSanPham) {
        this.idSanPham = idSanPham;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(double giaBan) {
        this.giaBan = giaBan;
    }

    public int getNamBaoHanh() {
        return namBaoHanh;
    }

    public void setNamBaoHanh(int namBaoHanh) {
        this.namBaoHanh = namBaoHanh;
    }

    public Date getNgaySanXuat() {
        return ngaySanXuat;
    }

    public void setNgaySanXuat(Date ngaySanXuat) {
        this.ngaySanXuat = ngaySanXuat;
    }

    public String getIdLoaiDem() {
        return idLoaiDem;
    }

    public void setIdLoaiDem(String idLoaiDem) {
        this.idLoaiDem = idLoaiDem;
    }

    public String getIdXuatXu() {
        return idXuatXu;
    }

    public void setIdXuatXu(String idXuatXu) {
        this.idXuatXu = idXuatXu;
    }

    public String getIdThuongHieu() {
        return idThuongHieu;
    }

    public void setIdThuongHieu(String idThuongHieu) {
        this.idThuongHieu = idThuongHieu;
    }

    public String getIdDoDay() {
        return idDoDay;
    }

    public void setIdDoDay(String idDoDay) {
        this.idDoDay = idDoDay;
    }

    public String getIdDoCung() {
        return idDoCung;
    }

    public void setIdDoCung(String idDoCung) {
        this.idDoCung = idDoCung;
    }

    public String getIdThietKe() {
        return idThietKe;
    }

    public void setIdThietKe(String idThietKe) {
        this.idThietKe = idThietKe;
    }

    public String getIdKichThuoc() {
        return idKichThuoc;
    }

    public void setIdKichThuoc(String idKichThuoc) {
        this.idKichThuoc = idKichThuoc;
    }

    public String getIdMauSac() {
        return idMauSac;
    }

    public void setIdMauSac(String idMauSac) {
        this.idMauSac = idMauSac;
    }

    public SanPham getSp() {
        return sp;
    }

    public void setSp(SanPham sp) {
        this.sp = sp;
    }

    public double getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(double thanhTien) {
        this.thanhTien = thanhTien;
    }

    @Override
    public String toString() {
        return "SanPhamChiTiet{" + "idSanPham=" + idSanPham + ", soLuong=" + soLuong + ", giaBan=" + giaBan + ", namBaoHanh=" + namBaoHanh + ", ngaySanXuat=" + ngaySanXuat + ", idLoaiDem=" + idLoaiDem + ", idXuatXu=" + idXuatXu + ", idThuongHieu=" + idThuongHieu + ", idDoDay=" + idDoDay + ", idDoCung=" + idDoCung + ", idThietKe=" + idThietKe + ", idKichThuoc=" + idKichThuoc + ", idMauSac=" + idMauSac + '}';
    }
}
