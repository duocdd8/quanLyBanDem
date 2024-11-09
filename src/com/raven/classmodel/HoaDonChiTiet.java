package com.raven.classmodel;

/**
 *
 * @author Hoai Thu
 */
public class HoaDonChiTiet {
    private int id;
    private String maHdct;
    private int soluong;
    private Double gia;
    private ChiTietSanPham spct;
    private int idCTSP;
    private String tenSP;
    private HoaDon hd;
    private SanPham sp;
    private MauSac ms;
    private KichThuoc kt;
    private ThuongHieu th;
    private int trangthai;

    public HoaDonChiTiet() {
    }

    public HoaDonChiTiet(int id, String maHdct, int soluong, Double gia, ChiTietSanPham spct, HoaDon hd, SanPham sp, MauSac ms, KichThuoc kt, ThuongHieu th, int trangthai) {
        this.id = id;
        this.maHdct = maHdct;
        this.soluong = soluong;
        this.gia = gia;
        this.spct = spct;
        this.hd = hd;
        this.sp = sp;
        this.ms = ms;
        this.kt = kt;
        this.th = th;
        this.trangthai = trangthai;
    }

    public SanPham getSp() {
        return sp;
    }

    public void setSp(SanPham sp) {
        this.sp = sp;
    }

    public MauSac getMs() {
        return ms;
    }

    public void setMs(MauSac ms) {
        this.ms = ms;
    }

    public KichThuoc getKt() {
        return kt;
    }

    public void setKt(KichThuoc kt) {
        this.kt = kt;
    }

    public ThuongHieu getTh() {
        return th;
    }

    public void setTh(ThuongHieu th) {
        this.th = th;
    }
   
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaHdct() {
        return maHdct;
    }

    public void setMaHdct(String maHdct) {
        this.maHdct = maHdct;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public Double getGia() {
        return gia;
    }

    public void setGia(Double gia) {
        this.gia = gia;
    }

    public ChiTietSanPham getSpct() {
        return spct;
    }

    public void setSpct(ChiTietSanPham spct) {
        this.spct = spct;
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

    public int getIdCTSP() {
        return idCTSP;
    }

    public void setIdCTSP(int idCTSP) {
        this.idCTSP = idCTSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }
    
}
