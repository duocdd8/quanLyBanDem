
package com.raven.classmodel;

import java.math.BigDecimal;
import java.util.Date;
import java.util.logging.Logger;
import java.util.UUID;

/**
 *
 * @author damducduoc
 */
public class PhieuGiamGia {
    
    private int id;
//    private String ma;
    private String tenPhieuGiamGia;
    private float phanTramGiamGia;
    private Date ngayBatDau;
    private Date ngayKetThuc;
    private Double giaTriToiThieu;
    private int soHoaDonApDung;
    private String moTa;
    private int trangThai;

    public PhieuGiamGia() {
    }

    public PhieuGiamGia(String tenPhieuGiamGia, float phanTramGiamGia, Date ngayBatDau, Date ngayKetThuc, double giaTriToiThieu, int soHoaDonApDung, String moTa, int trangThai) {
        this.tenPhieuGiamGia = tenPhieuGiamGia;
        this.phanTramGiamGia = phanTramGiamGia;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.giaTriToiThieu = giaTriToiThieu;
        this.soHoaDonApDung = soHoaDonApDung;
        this.moTa = moTa;
        this.trangThai = trangThai;
    }

    public PhieuGiamGia(String tenPhieuGiamGia, float phanTramGiamGia, Date ngayBatDau, Date ngayKetThuc, double giaTriToiThieu, int soHoaDonApDung, String moTa) {
        this.tenPhieuGiamGia = tenPhieuGiamGia;
        this.phanTramGiamGia = phanTramGiamGia;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.giaTriToiThieu = giaTriToiThieu;
        this.soHoaDonApDung = soHoaDonApDung;
        this.moTa = moTa;
    }
    
    public PhieuGiamGia(int id, String tenPhieuGiamGia, float phanTramGiamGia, Date ngayBatDau, Date ngayKetThuc, double giaTriToiThieu, int soHoaDonApDung, String moTa, int trangThai) {
        this.id = id;
        this.tenPhieuGiamGia = tenPhieuGiamGia;
        this.phanTramGiamGia = phanTramGiamGia;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.giaTriToiThieu = giaTriToiThieu;
        this.soHoaDonApDung = soHoaDonApDung;
        this.moTa = moTa;
        this.trangThai = trangThai;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenPhieuGiamGia() {
        return tenPhieuGiamGia;
    }

    public void setTenPhieuGiamGia(String tenPhieuGiamGia) {
        this.tenPhieuGiamGia = tenPhieuGiamGia;
    }

    public float getPhanTramGiamGia() {
        return phanTramGiamGia;
    }

    public void setPhanTramGiamGia(float phanTramGiamGia) {
        this.phanTramGiamGia = phanTramGiamGia;
    }

    public Date getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(Date ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public Date getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(Date ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public double getGiaTriToiThieu() {
        return giaTriToiThieu;
    }

    public void setGiaTriToiThieu(double giaTriToiThieu) {
        this.giaTriToiThieu = giaTriToiThieu;
    }

    public int getSoHoaDonApDung() {
        return soHoaDonApDung;
    }

    public void setSoHoaDonApDung(int soHoaDonApDung) {
        this.soHoaDonApDung = soHoaDonApDung;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }



    @Override
    public String toString() {
        return "PhieuGiamGia{" + "id=" + id + ", tenPhieuGiamGia=" + tenPhieuGiamGia + ", phanTramGiamGia=" + phanTramGiamGia + ", ngayBatDau=" + ngayBatDau + ", ngayKetThuc=" + ngayKetThuc + ", giaTriToiThieu=" + giaTriToiThieu + ", soHoaDonApDung=" + soHoaDonApDung + ", moTa=" + moTa + ", trangThai=" + trangThai + '}';
    }

    public void capNhatTrangThai() {
        Date hienTai = new Date();
        if (hienTai.before(ngayBatDau)) {
            this.trangThai = 0;  // Sắp diễn ra
        } else if (!hienTai.after(ngayKetThuc)) {
            this.trangThai = 1;  // Đang hoạt động
        } else {
            this.trangThai = 2;  // Ngưng hoạt động
        }
    }
    
        public String getTrangThaiHienThi() {
        switch (this.trangThai) {
            case 0:
                return "Sắp diễn ra";
            case 1:
                return "Đang hoạt động";
            case 2:
                return "Ngưng hoạt động";
            default:
                return "Không xác định";
        }
    }
}
