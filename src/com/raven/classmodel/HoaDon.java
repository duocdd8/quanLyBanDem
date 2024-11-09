package com.raven.classmodel;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Hoai Thu
 */
public class HoaDon {
    private int id;
    private Date ngayThanhToan;
    private Date ngayTao;
    private Date ngaySua;
    private int idKH;
    private int idNV;
    private int idPGG;
    private int idHTTT;
    private int trangThai;
    private double tongTien;
    private int soLuong;
    private BigDecimal gia;
    
    private double thanhtien;
    private KhachHang kh;
    private NhanVien nv;
    private HinhThucThanhToan httt;
    private LichSuHoaDon lshd;
    private HoaDonChiTiet hdct;
    private SanPham sp;
    private ChiTietSanPham spct;
    private MauSac ms;
    private ThuongHieu th;
    private KichThuoc kt;
    private int trangthai;

    public HoaDon() {
    }

    public HoaDon(int id, BigDecimal thanhTien, Date ngayThanhToan, Date ngayTao, Date ngaySua, int idKH, int idNV, int idPGG, int idHTTT, int trangThai, double tongTien, int soLuong, BigDecimal gia) {
        this.id = id;
        this.ngayThanhToan = ngayThanhToan;
        this.ngayTao = ngayTao;
        this.ngaySua = ngaySua;
        this.idKH = idKH;
        this.idNV = idNV;
        this.idPGG = idPGG;
        this.idHTTT = idHTTT;
        this.trangThai = trangThai;
        this.tongTien = tongTien;
        this.soLuong = soLuong;
        this.gia = gia;
    }

    public HoaDon(int id, int soLuong, BigDecimal gia, double tongTien) {
        this.id = id;
        this.soLuong = soLuong;
        this.gia = gia;
        this.tongTien = tongTien;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getNgayThanhToan() {
        return ngayThanhToan;
    }

    public void setNgayThanhToan(Date ngayThanhToan) {
        this.ngayThanhToan = ngayThanhToan;
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

    public int getIdKH() {
        return idKH;
    }

    public void setIdKH(int idKH) {
        this.idKH = idKH;
    }

    public int getIdNV() {
        return idNV;
    }

    public void setIdNV(int idNV) {
        this.idNV = idNV;
    }

    public int getIdPGG() {
        return idPGG;
    }

    public void setIdPGG(int idPGG) {
        this.idPGG = idPGG;
    }

    public int getIdHTTT() {
        return idHTTT;
    }

    public void setIdHTTT(int idHTTT) {
        this.idHTTT = idHTTT;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public BigDecimal getGia() {
        return gia;
    }

    public void setGia(BigDecimal gia) {
        this.gia = gia;
    }

    public double getThanhtien() {
        return thanhtien;
    }

    public void setThanhtien(double thanhtien) {
        this.thanhtien = thanhtien;
    }

    public KhachHang getKh() {
        return kh;
    }

    public void setKh(KhachHang kh) {
        this.kh = kh;
    }

    public NhanVien getNv() {
        return nv;
    }

    public void setNv(NhanVien nv) {
        this.nv = nv;
    }

    public HinhThucThanhToan getHttt() {
        return httt;
    }

    public void setHttt(HinhThucThanhToan httt) {
        this.httt = httt;
    }

    public LichSuHoaDon getLshd() {
        return lshd;
    }

    public void setLshd(LichSuHoaDon lshd) {
        this.lshd = lshd;
    }

    public HoaDonChiTiet getHdct() {
        return hdct;
    }

    public void setHdct(HoaDonChiTiet hdct) {
        this.hdct = hdct;
    }

    public SanPham getSp() {
        return sp;
    }

    public void setSp(SanPham sp) {
        this.sp = sp;
    }

    public ChiTietSanPham getSpct() {
        return spct;
    }

    public void setSpct(ChiTietSanPham spct) {
        this.spct = spct;
    }

    public MauSac getMs() {
        return ms;
    }

    public void setMs(MauSac ms) {
        this.ms = ms;
    }

    public ThuongHieu getTh() {
        return th;
    }

    public void setTh(ThuongHieu th) {
        this.th = th;
    }

    public KichThuoc getKt() {
        return kt;
    }

    public void setKt(KichThuoc kt) {
        this.kt = kt;
    }

    @Override
    public String toString() {
        return "HoaDon{" + "id=" + id + ", ngayThanhToan=" + ngayThanhToan + ", ngayTao=" + ngayTao + ", ngaySua=" + ngaySua + ", idKH=" + idKH + ", idNV=" + idNV + ", idPGG=" + idPGG + ", idHTTT=" + idHTTT + ", trangThai=" + trangThai + ", tongTien=" + tongTien + ", soLuong=" + soLuong + ", gia=" + gia + ", thanhtien=" + thanhtien + ", kh=" + kh + ", nv=" + nv + ", httt=" + httt + ", lshd=" + lshd + ", hdct=" + hdct + ", sp=" + sp + ", spct=" + spct + ", ms=" + ms + ", th=" + th + ", kt=" + kt + ", trangthai=" + trangthai + '}';
    }
    
    
}
