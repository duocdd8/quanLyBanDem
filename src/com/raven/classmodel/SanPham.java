package com.raven.classmodel;

import java.sql.Date;

/**
 *
 * @author Hoai Thu
 */
public class SanPham {
    private int id;
    private int idSPCT;
    private String tenSP;
    private ChiTietSanPham ctsp;
    private MauSac mauSac;
    private ThietKe thietKe;
    private ThuongHieu thuongHieu;
    private XuatXu xuatXu;
    private LoaiDem loaiDem;
    private KichThuoc kichThuoc;
    private DoCung doCung;
    private DoDay doDay;
    private Date ngayTao;
    private Date ngaySua;
    private boolean trangThai;

    public SanPham() {
    }

    public SanPham(String tenSP) {
        this.tenSP = tenSP;
    }

    public SanPham(String tenSP, boolean trangThai) {
        this.tenSP = tenSP;
        this.trangThai = trangThai;
    }
    
    

    public SanPham(int id, String tenSP, boolean trangThai) {
        this.id = id;
        this.tenSP = tenSP;
        this.trangThai = trangThai;
    }
    
    

    public SanPham(int id, String tenSP, Date ngayTao, Date ngaySua, boolean trangThai) {
        this.id = id;
        this.tenSP = tenSP;
        this.ngayTao = ngayTao;
        this.ngaySua = ngaySua;
        this.trangThai = trangThai;
    }

    public SanPham(int id, int idSPCT, String tenSP, ChiTietSanPham ctsp, MauSac mauSac, ThietKe thietKe, ThuongHieu thuongHieu, XuatXu xuatXu, LoaiDem loaiDem, KichThuoc kichThuoc, DoCung doCung, DoDay doDay, boolean trangThai) {
        this.id = id;
        this.idSPCT = idSPCT;
        this.tenSP = tenSP;
        this.ctsp = ctsp;
        this.mauSac = mauSac;
        this.thietKe = thietKe;
        this.thuongHieu = thuongHieu;
        this.xuatXu = xuatXu;
        this.loaiDem = loaiDem;
        this.kichThuoc = kichThuoc;
        this.doCung = doCung;
        this.doDay = doDay;
        this.trangThai = trangThai;
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

    public LoaiDem getLoaiDem() {
        return loaiDem;
    }

    public void setLoaiDem(LoaiDem loaiDem) {
        this.loaiDem = loaiDem;
    }

    public KichThuoc getKichThuoc() {
        return kichThuoc;
    }

    public void setKichThuoc(KichThuoc kichThuoc) {
        this.kichThuoc = kichThuoc;
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

    public ChiTietSanPham getCtsp() {
        return ctsp;
    }

    public void setCtsp(ChiTietSanPham ctsp) {
        this.ctsp = ctsp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdSPCT() {
        return idSPCT;
    }

    public void setIdSPCT(int idSPCT) {
        this.idSPCT = idSPCT;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }
}
