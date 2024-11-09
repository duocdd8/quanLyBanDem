package com.raven.classmodel;

import java.sql.Date;

/**
 *
 * @author Hoai Thu
 */
public class ChiTietSanPham {
    private int id;
    private int soLuong;
    private double giaBan;
    private Date ngaySanXuat;
    private int baoHanh;
    private int idSP;
    private int idMS;
    private int idKT;
    private int idTK;
    private int idTH;
    private int idLD;
    private int idXX;
    private int idDC;
    private int idDD;
    private double thanhTien;
    private String tenMS;
    private String tenKT;
    private String tenTK;
    private String tenTH;
    private String tenLD;
    private String tenXX;
    private String tenDC;
    private String tenDD;
    private String tenTrangThai;
    private SanPham sanPham;
    private DoCung doCung;
    private DoDay doDay;
    private KichThuoc kichThuoc;
    private LoaiDem loaiDem;
    private MauSac mauSac;
    private ThietKe thietKe;
    private ThuongHieu thuongHieu;
    private XuatXu xuatXu;
    private boolean trangThai;

    public ChiTietSanPham() {
    }

    public ChiTietSanPham(int id, int soLuong, double giaBan, Date ngaySanXuat, int baoHanh, String tenMS, String tenKT, String tenTK, String tenTH, String tenLD, String tenXX, String tenDC, String tenDD, boolean trangThai) {
        this.id = id;
        this.soLuong = soLuong;
        this.giaBan = giaBan;
        this.ngaySanXuat = ngaySanXuat;
        this.baoHanh = baoHanh;
        this.tenMS = tenMS;
        this.tenKT = tenKT;
        this.tenTK = tenTK;
        this.tenTH = tenTH;
        this.tenLD = tenLD;
        this.tenXX = tenXX;
        this.tenDC = tenDC;
        this.tenDD = tenDD;
        this.trangThai = trangThai;
    }
    
    

    public ChiTietSanPham(int id, int soLuong, double giaBan, Date ngaySanXuat, int baoHanh, int idSP, int idMS, int idKT, int idTK, int idTH, int idLD, int idXX, int idDC, int idDD, boolean trangThai) {
        this.id = id;
        this.soLuong = soLuong;
        this.giaBan = giaBan;
        this.ngaySanXuat = ngaySanXuat;
        this.baoHanh = baoHanh;
        this.idSP = idSP;
        this.idMS = idMS;
        this.idKT = idKT;
        this.idTK = idTK;
        this.idTH = idTH;
        this.idLD = idLD;
        this.idXX = idXX;
        this.idDC = idDC;
        this.idDD = idDD;
        this.trangThai = trangThai;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Date getNgaySanXuat() {
        return ngaySanXuat;
    }

    public void setNgaySanXuat(Date ngaySanXuat) {
        this.ngaySanXuat = ngaySanXuat;
    }

    public int getBaoHanh() {
        return baoHanh;
    }

    public void setBaoHanh(int baoHanh) {
        this.baoHanh = baoHanh;
    }

    public int getIdSP() {
        return idSP;
    }

    public void setIdSP(int idSP) {
        this.idSP = idSP;
    }

    public int getIdMS() {
        return idMS;
    }

    public void setIdMS(int idMS) {
        this.idMS = idMS;
    }

    public int getIdKT() {
        return idKT;
    }

    public void setIdKT(int idKT) {
        this.idKT = idKT;
    }

    public int getIdTK() {
        return idTK;
    }

    public void setIdTK(int idTK) {
        this.idTK = idTK;
    }

    public int getIdTH() {
        return idTH;
    }

    public void setIdTH(int idTH) {
        this.idTH = idTH;
    }

    public int getIdLD() {
        return idLD;
    }

    public void setIdLD(int idLD) {
        this.idLD = idLD;
    }

    public int getIdXX() {
        return idXX;
    }

    public void setIdXX(int idXX) {
        this.idXX = idXX;
    }

    public int getIdDC() {
        return idDC;
    }

    public void setIdDC(int idDC) {
        this.idDC = idDC;
    }

    public int getIdDD() {
        return idDD;
    }

    public void setIdDD(int idDD) {
        this.idDD = idDD;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    public String getTenMS() {
        return tenMS;
    }

    public void setTenMS(String tenMS) {
        this.tenMS = tenMS;
    }

    public String getTenKT() {
        return tenKT;
    }

    public void setTenKT(String tenKT) {
        this.tenKT = tenKT;
    }

    public String getTenTK() {
        return tenTK;
    }

    public void setTenTK(String tenTK) {
        this.tenTK = tenTK;
    }

    public String getTenTH() {
        return tenTH;
    }

    public void setTenTH(String tenTH) {
        this.tenTH = tenTH;
    }

    public String getTenLD() {
        return tenLD;
    }

    public void setTenLD(String tenLD) {
        this.tenLD = tenLD;
    }

    public String getTenXX() {
        return tenXX;
    }

    public void setTenXX(String tenXX) {
        this.tenXX = tenXX;
    }

    public String getTenDC() {
        return tenDC;
    }

    public void setTenDC(String tenDC) {
        this.tenDC = tenDC;
    }

    public String getTenDD() {
        return tenDD;
    }

    public void setTenDD(String tenDD) {
        this.tenDD = tenDD;
    }

    public String getTenTrangThai() {
        return tenTrangThai;
    }

    public void setTenTrangThai(String tenTrangThai) {
        this.tenTrangThai = tenTrangThai;
    }

    public double getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(double thanhTien) {
        this.thanhTien = thanhTien;
    }

    public SanPham getSanPham() {
        return sanPham;
    }

    public void setSanPham(SanPham sanPham) {
        this.sanPham = sanPham;
    }

    public DoCung getDoCung() {
        return doCung;
    }

    public void setDoCung(DoCung doCung) {
        this.doCung = doCung;
    }

    public DoDay getDoDay() {
        return doDay;
    }

    public void setDoDay(DoDay doDay) {
        this.doDay = doDay;
    }

    public KichThuoc getKichThuoc() {
        return kichThuoc;
    }

    public void setKichThuoc(KichThuoc kichThuoc) {
        this.kichThuoc = kichThuoc;
    }

    public LoaiDem getLoaiDem() {
        return loaiDem;
    }

    public void setLoaiDem(LoaiDem loaiDem) {
        this.loaiDem = loaiDem;
    }

    public MauSac getMauSac() {
        return mauSac;
    }

    public void setMauSac(MauSac mauSac) {
        this.mauSac = mauSac;
    }

    public ThietKe getThietKe() {
        return thietKe;
    }

    public void setThietKe(ThietKe thietKe) {
        this.thietKe = thietKe;
    }

    public ThuongHieu getThuongHieu() {
        return thuongHieu;
    }

    public void setThuongHieu(ThuongHieu thuongHieu) {
        this.thuongHieu = thuongHieu;
    }

    public XuatXu getXuatXu() {
        return xuatXu;
    }

    public void setXuatXu(XuatXu xuatXu) {
        this.xuatXu = xuatXu;
    }
}
