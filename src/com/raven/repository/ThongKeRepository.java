package com.raven.repository;

import com.raven.classmodel.ChiTietSanPham;
import com.raven.classmodel.DoCung;
import com.raven.classmodel.DoDay;
import com.raven.classmodel.HoaDon;
import com.raven.classmodel.KichThuoc;
import com.raven.classmodel.LoaiDem;
import com.raven.classmodel.MauSac;
import com.raven.classmodel.SanPham;
import com.raven.classmodel.ThietKe;
import com.raven.classmodel.ThongKe;
import com.raven.classmodel.ThuongHieu;
import com.raven.classmodel.XuatXu;
import com.raven.connection.DBconnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ThongKeRepository {

    Connection con = null;
    PreparedStatement ps = null;
    String sql = null;
    ResultSet rs = null;
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public List<HoaDon> getAllHoaDon() {
        String sql = "select * from HoaDon order by ID desc";
        List<HoaDon> listHDTK = new ArrayList<>();
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                HoaDon hd = new HoaDon();
                hd.setId(rs.getInt(1));
                hd.setIdNV(rs.getInt(2));
                hd.setIdKH(rs.getInt(3));
                hd.setIdHTTT(rs.getInt(4));
                hd.setTongTien(rs.getDouble(5));
                hd.setNgayThanhToan(rs.getDate(6));
                hd.setNgayTao(rs.getDate(7));
                hd.setNgaySua(rs.getDate(8));
                hd.setTrangThai(rs.getInt(10));
                listHDTK.add(hd);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listHDTK;
    }

    public int getTongSoLuongDonHang() {
        int tongSoLuongDonHang = 0;
        String sql = "SELECT COUNT(*) FROM HoaDon";
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBconnect.getConnection();
            if (con == null) {
                System.out.println("Failed to make connection!");
                return 0;
            }
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                tongSoLuongDonHang = rs.getInt(1);
                System.out.println("Tong So Luong Don Hang: " + tongSoLuongDonHang);
            } else {
                System.out.println("No data found!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return tongSoLuongDonHang;
    }

    public int getHoaDonDaThanhToan() {
        int soHoaDon = 0;
        String sql = "SELECT COUNT(*) FROM HoaDon WHERE TrangThai = 0";
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBconnect.getConnection();
            if (con == null) {
                System.out.println("Failed to make connection!");
                return 0;
            }
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                soHoaDon = rs.getInt(1);
                System.out.println("Tong So Luong Don Hang: " + soHoaDon);
            } else {
                System.out.println("No data found!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return soHoaDon;
    }

    public int getHoaDonDaHuy() {
        int soHoaDonDaHuy = 0;
        String sql = "SELECT COUNT(*) FROM HoaDon WHERE TrangThai = 2";
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBconnect.getConnection();
            if (con == null) {
                System.out.println("Failed to make connection!");
                return 0;
            }
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                soHoaDonDaHuy = rs.getInt(1);
                System.out.println("Tong So Luong Don Hang: " + soHoaDonDaHuy);
            } else {
                System.out.println("No data found!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return soHoaDonDaHuy;
    }

    public List<HoaDon> getAllHoaDonTheoKhoangNgay(Date Begin, Date End) {
        String sql = "select * from HoaDon where NgayTao >= ? and NgaySua <= ? order by NgayTao desc";
        List<HoaDon> listHDTK = new ArrayList<>();
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            ps.setString(1, dateFormat.format(Begin));
            ps.setString(1, dateFormat.format(End));
            while (rs.next()) {
                HoaDon hd = new HoaDon();
                hd.setId(rs.getInt(1));
                hd.setIdNV(rs.getInt(2));
                hd.setIdKH(rs.getInt(3));
                hd.setIdHTTT(rs.getInt(4));
                hd.setTongTien(rs.getDouble(5));
                hd.setNgayThanhToan(rs.getDate(6));
                hd.setNgayTao(rs.getDate(7));
                hd.setNgaySua(rs.getDate(8));
                hd.setTrangThai(rs.getInt(10));
                listHDTK.add(hd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listHDTK;
    }

    public List<HoaDon> getAllHoaDonTheoThanhTien() {
        String sql = "select * from HoaDon order by ThanhTien desc";
        List<HoaDon> listHDTK = new ArrayList<>();
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                HoaDon hd = new HoaDon();
                hd.setId(rs.getInt(1));
                hd.setIdNV(rs.getInt(2));
                hd.setIdKH(rs.getInt(3));
                hd.setIdHTTT(rs.getInt(4));
                hd.setTongTien(rs.getDouble(5));
                hd.setNgayThanhToan(rs.getDate(6));
                hd.setNgayTao(rs.getDate(7));
                hd.setNgaySua(rs.getDate(8));
                hd.setTrangThai(rs.getInt(10));
                listHDTK.add(hd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listHDTK;
    }

    public List<HoaDon> getAllHoaDonTheoNgayTao() {
        String sql = "select * from HoaDon order by NgayTao desc";
        List<HoaDon> listHDTK = new ArrayList<>();
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                HoaDon tkhd = new HoaDon();
                tkhd.setId(rs.getInt(1));
                tkhd.setIdNV(rs.getInt(2));
                tkhd.setIdKH(rs.getInt(3));
                tkhd.setIdHTTT(rs.getInt(4));
                tkhd.setTongTien(rs.getDouble(5));
                tkhd.setNgayThanhToan(rs.getDate(6));
                tkhd.setNgayTao(rs.getDate(7));
                tkhd.setNgaySua(rs.getDate(8));
                tkhd.setTrangThai(rs.getInt(10));
                listHDTK.add(tkhd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listHDTK;
    }

    public List<HoaDon> getAllHoaDonTheoTrangThai(int TrangThai) {
        String sql = "select * from HoaDon where TrangThai = ? order by NgayTao desc";
        List<HoaDon> listHDTK = new ArrayList<>();
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            ps.setInt(1, TrangThai);
            while (rs.next()) {
                HoaDon tkhd = new HoaDon();
                tkhd.setId(rs.getInt(1));
                tkhd.setIdNV(rs.getInt(2));
                tkhd.setIdKH(rs.getInt(3));
                tkhd.setIdHTTT(rs.getInt(4));
                tkhd.setTongTien(rs.getDouble(5));
                tkhd.setNgayThanhToan(rs.getDate(6));
                tkhd.setNgayTao(rs.getDate(7));
                tkhd.setNgaySua(rs.getDate(8));
                tkhd.setTrangThai(rs.getInt(10));
                listHDTK.add(tkhd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listHDTK;
    }

    public List<HoaDon> getAllHoaDonTheoNgay_Tien_TrangThai(Date begin, Date end, int TrangThai) {
        String sql = "select * from HoaDon where NgayTao >= ? AND NgayTao <= ? AND TrangThai = ? order by ThanhTien desc";
        List<HoaDon> listHDTK = new ArrayList<>();
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            ps.setString(1, dateFormat.format(begin));
            ps.setString(2, dateFormat.format(end));
            ps.setInt(3, TrangThai);
            while (rs.next()) {
                HoaDon tkhd = new HoaDon();
                tkhd.setId(rs.getInt(1));
                tkhd.setIdNV(rs.getInt(2));
                tkhd.setIdKH(rs.getInt(3));
                tkhd.setIdHTTT(rs.getInt(4));
                tkhd.setTongTien(rs.getDouble(5));
                tkhd.setNgayThanhToan(rs.getDate(6));
                tkhd.setNgayTao(rs.getDate(7));
                tkhd.setNgaySua(rs.getDate(8));
                tkhd.setTrangThai(rs.getInt(10));
                listHDTK.add(tkhd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listHDTK;
    }

    public List<HoaDon> getAllHoaDonTheoNgay_NgayTao_TrangThai(Date begin, Date end, int TrangThai) {
        String sql = "select * from HoaDon where NgayTao >= ? AND NgayTao <= ? AND TrangThai = ? order by NgayTao asc";
        List<HoaDon> listHDTK = new ArrayList<>();
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            ps.setString(1, dateFormat.format(begin));
            ps.setString(2, dateFormat.format(end));
            ps.setInt(3, TrangThai);
            while (rs.next()) {
                HoaDon tkhd = new HoaDon();
                tkhd.setId(rs.getInt(1));
                tkhd.setIdNV(rs.getInt(2));
                tkhd.setIdKH(rs.getInt(3));
                tkhd.setIdHTTT(rs.getInt(4));
                tkhd.setTongTien(rs.getDouble(5));
                tkhd.setNgayThanhToan(rs.getDate(6));
                tkhd.setNgayTao(rs.getDate(7));
                tkhd.setNgaySua(rs.getDate(8));
                tkhd.setTrangThai(rs.getInt(10));
                listHDTK.add(tkhd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listHDTK;
    }

    public List<HoaDon> getAllHoaDonTheoNgay_TrangThai(Date begin, Date end, int TrangThai) {
        String sql = "select * from HoaDon where NgayTao >= ? and NgayTao <= > and TrangThai = ?";
        List<HoaDon> listHDTK = new ArrayList<>();
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            ps.setString(1, dateFormat.format(begin));
            ps.setString(2, dateFormat.format(end));
            ps.setInt(3, TrangThai);
            while (rs.next()) {
                HoaDon tkhd = new HoaDon();
                tkhd.setId(rs.getInt(1));
                tkhd.setIdNV(rs.getInt(2));
                tkhd.setIdKH(rs.getInt(3));
                tkhd.setIdHTTT(rs.getInt(4));
                tkhd.setTongTien(rs.getDouble(5));
                tkhd.setNgayThanhToan(rs.getDate(6));
                tkhd.setNgayTao(rs.getDate(7));
                tkhd.setNgaySua(rs.getDate(8));
                tkhd.setTrangThai(rs.getInt(10));
                listHDTK.add(tkhd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listHDTK;
    }

    public List<HoaDon> getAllHoaDonTheoNgayTao(Date begin, Date end) {
        String sql = "select * from HoaDon where NgayTao >= ? and NgayTao <= ? order by NgayTao asc";
        List<HoaDon> listHDTK = new ArrayList<>();
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            ps.setString(1, dateFormat.format(begin));
            ps.setString(2, dateFormat.format(end));
            while (rs.next()) {
                HoaDon tkhd = new HoaDon();
                tkhd.setId(rs.getInt(1));
                tkhd.setIdNV(rs.getInt(2));
                tkhd.setIdKH(rs.getInt(3));
                tkhd.setIdHTTT(rs.getInt(4));
                tkhd.setTongTien(rs.getDouble(5));
                tkhd.setNgayThanhToan(rs.getDate(6));
                tkhd.setNgayTao(rs.getDate(7));
                tkhd.setNgaySua(rs.getDate(8));
                tkhd.setTrangThai(rs.getInt(10));
                listHDTK.add(tkhd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listHDTK;
    }

    public List<HoaDon> getAllHoaDonTheoNgayTao_ThanhTien(Date begin, Date end) {
        String sql = "select * from HoaDon where NgayTao >= ? and NgayTao <= ? order by ThanhTien desc";
        List<HoaDon> listHDTK = new ArrayList<>();
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            ps.setString(1, dateFormat.format(begin));
            ps.setString(2, dateFormat.format(end));
            while (rs.next()) {
                HoaDon tkhd = new HoaDon();
                tkhd.setId(rs.getInt(1));
                tkhd.setIdNV(rs.getInt(2));
                tkhd.setIdKH(rs.getInt(3));
                tkhd.setIdHTTT(rs.getInt(4));
                tkhd.setTongTien(rs.getDouble(5));
                tkhd.setNgayThanhToan(rs.getDate(6));
                tkhd.setNgayTao(rs.getDate(7));
                tkhd.setNgaySua(rs.getDate(8));
                tkhd.setTrangThai(rs.getInt(10));
                listHDTK.add(tkhd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listHDTK;
    }

    public List<HoaDon> getAllHoaDonTheoThanhTien_TrangThai(int TrangThai) {
        String sql = "select * from HoaDon where TrangThai = ? order by ThanhTien desc";
        List<HoaDon> listHDTK = new ArrayList<>();
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            ps.setInt(1, TrangThai);
            while (rs.next()) {
                HoaDon tkhd = new HoaDon();
                tkhd.setId(rs.getInt(1));
                tkhd.setIdNV(rs.getInt(2));
                tkhd.setIdKH(rs.getInt(3));
                tkhd.setIdHTTT(rs.getInt(4));
                tkhd.setTongTien(rs.getDouble(5));
                tkhd.setNgayThanhToan(rs.getDate(6));
                tkhd.setNgayTao(rs.getDate(7));
                tkhd.setNgaySua(rs.getDate(8));
                tkhd.setTrangThai(rs.getInt(10));
                listHDTK.add(tkhd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listHDTK;
    }

    public List<HoaDon> getAllHoaDonTheoNgayTao_TrangThai(int TrangThai) {
        String sql = "select * from HoaDon where TrangThai = ? order by NgayTao asc";
        List<HoaDon> listHDTK = new ArrayList<>();
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            ps.setInt(1, TrangThai);
            while (rs.next()) {
                HoaDon tkhd = new HoaDon();
                tkhd.setId(rs.getInt(1));
                tkhd.setIdNV(rs.getInt(2));
                tkhd.setIdKH(rs.getInt(3));
                tkhd.setIdHTTT(rs.getInt(4));
                tkhd.setTongTien(rs.getDouble(5));
                tkhd.setNgayThanhToan(rs.getDate(6));
                tkhd.setNgayTao(rs.getDate(7));
                tkhd.setNgaySua(rs.getDate(8));
                tkhd.setTrangThai(rs.getInt(10));
                listHDTK.add(tkhd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listHDTK;
    }

    public List<SanPham> getAllSanPhamTheoGia() {
        String sql = "SELECT ChiTietSanPham.ID, SanPham.TenSP, MauSac.TenMS, KichThuoc.TenKT, DoDay.TenDD, LoaiDem.TenLD, "
                + "ThietKe.TenTK, XuatXu.TenXX, ThuongHieu.TenTH, ChiTietSanPham.SoLuong, ChiTietSanPham.GiaBan, "
                + "ChiTietSanPham.NgaySanXuat, ChiTietSanPham.BaoHanh "
                + "FROM ChiTietSanPham "
                + "JOIN SanPham ON SanPham.ID = ChiTietSanPham.ID_SanPham "
                + "JOIN MauSac ON MauSac.ID = ChiTietSanPham.ID_MauSac "
                + "JOIN KichThuoc ON KichThuoc.ID = ChiTietSanPham.ID_KichThuoc "
                + "JOIN DoDay ON DoDay.ID = ChiTietSanPham.ID_DoDay "
                + "JOIN LoaiDem ON LoaiDem.ID = ChiTietSanPham.ID_LoaiDem "
                + "JOIN ThietKe ON ThietKe.ID = ChiTietSanPham.ID_ThietKe "
                + "JOIN XuatXu ON XuatXu.ID = ChiTietSanPham.ID_XuatXu "
                + "JOIN ThuongHieu ON ThuongHieu.ID = ChiTietSanPham.ID_ThuongHieu "
                + "ORDER BY ChiTietSanPham.SoLuong DESC";
        List<SanPham> listCTSP = new ArrayList<>();
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                ChiTietSanPham ctsp = new ChiTietSanPham();
                SanPham sp = new SanPham();
                MauSac mauSac = new MauSac();
                LoaiDem loaiDem = new LoaiDem();
                XuatXu xuatXu = new XuatXu();
                DoCung doCung = new DoCung();
                DoDay doDay = new DoDay();
                ThuongHieu thuongHieu = new ThuongHieu();
                ThietKe thietKe = new ThietKe();
                KichThuoc kichThuoc = new KichThuoc();
                ctsp.setId(rs.getInt(1));
                sp.setTenSP(rs.getString(2));
                mauSac.setTenMS(rs.getString(3));
                kichThuoc.setTenKT(rs.getString(4));
                doDay.setTenDD(rs.getString(5));
                loaiDem.setTenLD(rs.getString(6));
                thietKe.setTenTK(rs.getString(7));
                xuatXu.setTenXX(rs.getString(8));
                thuongHieu.setTenTH(rs.getString(9));
                ctsp.setSoLuong(rs.getInt(10));
                ctsp.setGiaBan(rs.getFloat(11));
                ctsp.setNgaySanXuat(rs.getDate(12));
                ctsp.setBaoHanh(rs.getInt(13));
                sp.setCtsp(ctsp);
                sp.setMauSac(mauSac);
                sp.setKichThuoc(kichThuoc);
                sp.setXuatXu(xuatXu);
                sp.setDoDay(doDay);
                sp.setDoCung(doCung);
                sp.setThietKe(thietKe);
                sp.setLoaiDem(loaiDem);
                sp.setThuongHieu(thuongHieu);
                listCTSP.add(sp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listCTSP;
    }

    public List<SanPham> getAllSanPham() {
        String sql = "SELECT ChiTietSanPham.ID, SanPham.TenSP, MauSac.TenMS, KichThuoc.TenKT, DoDay.TenDD, LoaiDem.TenLD, "
                + "ThietKe.TenTK, XuatXu.TenXX, ThuongHieu.TenTH, ChiTietSanPham.SoLuong, ChiTietSanPham.GiaBan, "
                + "ChiTietSanPham.NgaySanXuat, ChiTietSanPham.BaoHanh "
                + "FROM ChiTietSanPham "
                + "JOIN SanPham ON SanPham.ID = ChiTietSanPham.ID_SanPham "
                + "JOIN MauSac ON MauSac.ID = ChiTietSanPham.ID_MauSac "
                + "JOIN KichThuoc ON KichThuoc.ID = ChiTietSanPham.ID_KichThuoc "
                + "JOIN DoDay ON DoDay.ID = ChiTietSanPham.ID_DoDay "
                + "JOIN LoaiDem ON LoaiDem.ID = ChiTietSanPham.ID_LoaiDem "
                + "JOIN ThietKe ON ThietKe.ID = ChiTietSanPham.ID_ThietKe "
                + "JOIN XuatXu ON XuatXu.ID = ChiTietSanPham.ID_XuatXu "
                + "JOIN ThuongHieu ON ThuongHieu.ID = ChiTietSanPham.ID_ThuongHieu";
        List<SanPham> listSP = new ArrayList<>();
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                ChiTietSanPham ctsp = new ChiTietSanPham();
                SanPham sp = new SanPham();
                MauSac mauSac = new MauSac();
                LoaiDem loaiDem = new LoaiDem();
                XuatXu xuatXu = new XuatXu();
                DoCung doCung = new DoCung();
                DoDay doDay = new DoDay();
                ThuongHieu thuongHieu = new ThuongHieu();
                ThietKe thietKe = new ThietKe();
                KichThuoc kichThuoc = new KichThuoc();
                ctsp.setId(rs.getInt(1));
                sp.setTenSP(rs.getString(2));
                mauSac.setTenMS(rs.getString(3));
                kichThuoc.setTenKT(rs.getString(4));
                doDay.setTenDD(rs.getString(5));
                loaiDem.setTenLD(rs.getString(6));
                thietKe.setTenTK(rs.getString(7));
                xuatXu.setTenXX(rs.getString(8));
                thuongHieu.setTenTH(rs.getString(9));
                ctsp.setSoLuong(rs.getInt(10));
                ctsp.setGiaBan(rs.getFloat(11));
                ctsp.setNgaySanXuat(rs.getDate(12));
                ctsp.setBaoHanh(rs.getInt(13));
                sp.setCtsp(ctsp);
                sp.setMauSac(mauSac);
                sp.setKichThuoc(kichThuoc);
                sp.setXuatXu(xuatXu);
                sp.setDoDay(doDay);
                sp.setDoCung(doCung);
                sp.setThietKe(thietKe);
                sp.setLoaiDem(loaiDem);
                sp.setThuongHieu(thuongHieu);
                listSP.add(sp);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listSP;
    }

    public class ThongKeRepo {

        public List<SanPham> getAllSanPhamTheoGia() {
            String sql = "SELECT ChiTietSanPham.ID, SanPham.TenSP, MauSac.TenMS, KichThuoc.TenKT, DoDay.TenDD, LoaiDem.TenLD, "
                    + "ThietKe.TenTK, XuatXu.TenXX, ThuongHieu.TenTH, ChiTietSanPham.SoLuong, ChiTietSanPham.GiaBan, "
                    + "ChiTietSanPham.NgaySanXuat, ChiTietSanPham.BaoHanh "
                    + "FROM ChiTietSanPham "
                    + "JOIN SanPham ON SanPham.ID = ChiTietSanPham.ID_SanPham "
                    + "JOIN MauSac ON MauSac.ID = ChiTietSanPham.ID_MauSac "
                    + "JOIN KichThuoc ON KichThuoc.ID = ChiTietSanPham.ID_KichThuoc "
                    + "JOIN DoDay ON DoDay.ID = ChiTietSanPham.ID_DoDay "
                    + "JOIN LoaiDem ON LoaiDem.ID = ChiTietSanPham.ID_LoaiDem "
                    + "JOIN ThietKe ON ThietKe.ID = ChiTietSanPham.ID_ThietKe "
                    + "JOIN XuatXu ON XuatXu.ID = ChiTietSanPham.ID_XuatXu "
                    + "JOIN ThuongHieu ON ThuongHieu.ID = ChiTietSanPham.ID_ThuongHieu "
                    + "ORDER BY ChiTietSanPham.GiaBan DESC";
            return executeQuery(sql);
        }

        public List<SanPham> getAllSanPhamTheoSoLuong() {
            String sql = "SELECT ChiTietSanPham.ID, SanPham.TenSP, MauSac.TenMS, KichThuoc.TenKT, DoDay.TenDD, LoaiDem.TenLD, "
                    + "ThietKe.TenTK, XuatXu.TenXX, ThuongHieu.TenTH, ChiTietSanPham.SoLuong, ChiTietSanPham.GiaBan, "
                    + "ChiTietSanPham.NgaySanXuat, ChiTietSanPham.BaoHanh "
                    + "FROM ChiTietSanPham "
                    + "JOIN SanPham ON SanPham.ID = ChiTietSanPham.ID_SanPham "
                    + "JOIN MauSac ON MauSac.ID = ChiTietSanPham.ID_MauSac "
                    + "JOIN KichThuoc ON KichThuoc.ID = ChiTietSanPham.ID_KichThuoc "
                    + "JOIN DoDay ON DoDay.ID = ChiTietSanPham.ID_DoDay "
                    + "JOIN LoaiDem ON LoaiDem.ID = ChiTietSanPham.ID_LoaiDem "
                    + "JOIN ThietKe ON ThietKe.ID = ChiTietSanPham.ID_ThietKe "
                    + "JOIN XuatXu ON XuatXu.ID = ChiTietSanPham.ID_XuatXu "
                    + "JOIN ThuongHieu ON ThuongHieu.ID = ChiTietSanPham.ID_ThuongHieu "
                    + "ORDER BY ChiTietSanPham.SoLuong DESC";
            return executeQuery(sql);
        }

        public List<SanPham> getAllSanPhamTheoGia_TrangThai(int trangThai) {
            String sql = "SELECT ChiTietSanPham.ID, SanPham.TenSP, MauSac.TenMS, KichThuoc.TenKT, DoDay.TenDD, LoaiDem.TenLD, "
                    + "ThietKe.TenTK, XuatXu.TenXX, ThuongHieu.TenTH, ChiTietSanPham.SoLuong, ChiTietSanPham.GiaBan, "
                    + "ChiTietSanPham.NgaySanXuat, ChiTietSanPham.BaoHanh "
                    + "FROM ChiTietSanPham "
                    + "JOIN SanPham ON SanPham.ID = ChiTietSanPham.ID_SanPham "
                    + "JOIN MauSac ON MauSac.ID = ChiTietSanPham.ID_MauSac "
                    + "JOIN KichThuoc ON KichThuoc.ID = ChiTietSanPham.ID_KichThuoc "
                    + "JOIN DoDay ON DoDay.ID = ChiTietSanPham.ID_DoDay "
                    + "JOIN LoaiDem ON LoaiDem.ID = ChiTietSanPham.ID_LoaiDem "
                    + "JOIN ThietKe ON ThietKe.ID = ChiTietSanPham.ID_ThietKe "
                    + "JOIN XuatXu ON XuatXu.ID = ChiTietSanPham.ID_XuatXu "
                    + "JOIN ThuongHieu ON ThuongHieu.ID = ChiTietSanPham.ID_ThuongHieu "
                    + "WHERE ChiTietSanPham.TrangThai = ? "
                    + "ORDER BY ChiTietSanPham.GiaBan DESC";
            return executeQueryWithTrangThai(sql, trangThai);
        }

        public List<SanPham> getAllSanPhamTheoSoLuong_TrangThai(int trangThai) {
            String sql = "SELECT ChiTietSanPham.ID, SanPham.TenSP, MauSac.TenMS, KichThuoc.TenKT, DoDay.TenDD, LoaiDem.TenLD, "
                    + "ThietKe.TenTK, XuatXu.TenXX, ThuongHieu.TenTH, ChiTietSanPham.SoLuong, ChiTietSanPham.GiaBan, "
                    + "ChiTietSanPham.NgaySanXuat, ChiTietSanPham.BaoHanh "
                    + "FROM ChiTietSanPham "
                    + "JOIN SanPham ON SanPham.ID = ChiTietSanPham.ID_SanPham "
                    + "JOIN MauSac ON MauSac.ID = ChiTietSanPham.ID_MauSac "
                    + "JOIN KichThuoc ON KichThuoc.ID = ChiTietSanPham.ID_KichThuoc "
                    + "JOIN DoDay ON DoDay.ID = ChiTietSanPham.ID_DoDay "
                    + "JOIN LoaiDem ON LoaiDem.ID = ChiTietSanPham.ID_LoaiDem "
                    + "JOIN ThietKe ON ThietKe.ID = ChiTietSanPham.ID_ThietKe "
                    + "JOIN XuatXu ON XuatXu.ID = ChiTietSanPham.ID_XuatXu "
                    + "JOIN ThuongHieu ON ThuongHieu.ID = ChiTietSanPham.ID_ThuongHieu "
                    + "WHERE ChiTietSanPham.TrangThai = ? "
                    + "ORDER BY ChiTietSanPham.SoLuong DESC";
            return executeQueryWithTrangThai(sql, trangThai);
        }

        private List<SanPham> executeQuery(String sql) {
            List<SanPham> listCTSP = new ArrayList<>();
            try {
                Connection con = DBconnect.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    listCTSP.add(mapResultSetToSanPham(rs));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return listCTSP;
        }

        private List<SanPham> executeQueryWithTrangThai(String sql, int trangThai) {
            List<SanPham> listCTSP = new ArrayList<>();
            try {
                Connection con = DBconnect.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, trangThai);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    listCTSP.add(mapResultSetToSanPham(rs));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return listCTSP;
        }

        private SanPham mapResultSetToSanPham(ResultSet rs) throws SQLException {
            ChiTietSanPham ctsp = new ChiTietSanPham();
            SanPham sp = new SanPham();
            MauSac mauSac = new MauSac();
            LoaiDem loaiDem = new LoaiDem();
            XuatXu xuatXu = new XuatXu();
            DoCung doCung = new DoCung();
            DoDay doDay = new DoDay();
            ThuongHieu thuongHieu = new ThuongHieu();
            ThietKe thietKe = new ThietKe();
            KichThuoc kichThuoc = new KichThuoc();
            ctsp.setId(rs.getInt(1));
            sp.setTenSP(rs.getString(2));
            mauSac.setTenMS(rs.getString(3));
            kichThuoc.setTenKT(rs.getString(4));
            doDay.setTenDD(rs.getString(5));
            loaiDem.setTenLD(rs.getString(6));
            thietKe.setTenTK(rs.getString(7));
            xuatXu.setTenXX(rs.getString(8));
            thuongHieu.setTenTH(rs.getString(9));
            ctsp.setSoLuong(rs.getInt(10));
            ctsp.setGiaBan(rs.getFloat(11));
            ctsp.setNgaySanXuat(rs.getDate(12));
            ctsp.setBaoHanh(rs.getInt(13));
            sp.setCtsp(ctsp);
            sp.setMauSac(mauSac);
            sp.setKichThuoc(kichThuoc);
            sp.setXuatXu(xuatXu);
            sp.setDoDay(doDay);
            sp.setDoCung(doCung);
            sp.setThietKe(thietKe);
            sp.setLoaiDem(loaiDem);
            sp.setThuongHieu(thuongHieu);
            return sp;
        }
    }

    public List<SanPham> getAllSanPhamTheoSoLuong() {
        String sql = "SELECT ChiTietSanPham.ID, SanPham.TenSP, MauSac.TenMS, KichThuoc.TenKT, DoDay.TenDD, LoaiDem.TenLD, "
                + "ThietKe.TenTK, XuatXu.TenXX, ThuongHieu.TenTH, ChiTietSanPham.SoLuong, ChiTietSanPham.GiaBan, "
                + "ChiTietSanPham.NgaySanXuat, ChiTietSanPham.BaoHanh "
                + "FROM ChiTietSanPham "
                + "JOIN SanPham ON SanPham.ID = ChiTietSanPham.ID_SanPham "
                + "JOIN MauSac ON MauSac.ID = ChiTietSanPham.ID_MauSac "
                + "JOIN KichThuoc ON KichThuoc.ID = ChiTietSanPham.ID_KichThuoc "
                + "JOIN DoDay ON DoDay.ID = ChiTietSanPham.ID_DoDay "
                + "JOIN LoaiDem ON LoaiDem.ID = ChiTietSanPham.ID_LoaiDem "
                + "JOIN ThietKe ON ThietKe.ID = ChiTietSanPham.ID_ThietKe "
                + "JOIN XuatXu ON XuatXu.ID = ChiTietSanPham.ID_XuatXu "
                + "JOIN ThuongHieu ON ThuongHieu.ID = ChiTietSanPham.ID_ThuongHieu "
                + "ORDER BY ChiTietSanPham.SoLuong DESC";
        List<SanPham> listCTSP = new ArrayList<>();
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                ChiTietSanPham ctsp = new ChiTietSanPham();
                SanPham sp = new SanPham();
                MauSac mauSac = new MauSac();
                LoaiDem loaiDem = new LoaiDem();
                XuatXu xuatXu = new XuatXu();
                DoCung doCung = new DoCung();
                DoDay doDay = new DoDay();
                ThuongHieu thuongHieu = new ThuongHieu();
                ThietKe thietKe = new ThietKe();
                KichThuoc kichThuoc = new KichThuoc();
                ctsp.setId(rs.getInt(1));
                sp.setTenSP(rs.getString(2));
                mauSac.setTenMS(rs.getString(3));
                kichThuoc.setTenKT(rs.getString(4));
                doDay.setTenDD(rs.getString(5));
                loaiDem.setTenLD(rs.getString(6));
                thietKe.setTenTK(rs.getString(7));
                xuatXu.setTenXX(rs.getString(8));
                thuongHieu.setTenTH(rs.getString(9));
                ctsp.setSoLuong(rs.getInt(10));
                ctsp.setGiaBan(rs.getFloat(11));
                ctsp.setNgaySanXuat(rs.getDate(12));
                ctsp.setBaoHanh(rs.getInt(13));
                sp.setCtsp(ctsp);
                sp.setMauSac(mauSac);
                sp.setKichThuoc(kichThuoc);
                sp.setXuatXu(xuatXu);
                sp.setDoDay(doDay);
                sp.setDoCung(doCung);
                sp.setThietKe(thietKe);
                sp.setLoaiDem(loaiDem);
                sp.setThuongHieu(thuongHieu);
                listCTSP.add(sp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listCTSP;
    }

    public List<SanPham> getAllSanPhamTheoTrangThai(int TrangThai) {
        String sql = "SELECT ChiTietSanPham.ID, SanPham.TenSP, MauSac.TenMS, KichThuoc.TenKT, DoDay.TenDD, LoaiDem.TenLD, "
                + "ThietKe.TenTK, XuatXu.TenXX, ThuongHieu.TenTH, ChiTietSanPham.SoLuong, ChiTietSanPham.GiaBan, "
                + "ChiTietSanPham.NgaySanXuat, ChiTietSanPham.BaoHanh "
                + "FROM ChiTietSanPham "
                + "JOIN SanPham ON SanPham.ID = ChiTietSanPham.ID_SanPham "
                + "JOIN MauSac ON MauSac.ID = ChiTietSanPham.ID_MauSac "
                + "JOIN KichThuoc ON KichThuoc.ID = ChiTietSanPham.ID_KichThuoc "
                + "JOIN DoDay ON DoDay.ID = ChiTietSanPham.ID_DoDay "
                + "JOIN LoaiDem ON LoaiDem.ID = ChiTietSanPham.ID_LoaiDem "
                + "JOIN ThietKe ON ThietKe.ID = ChiTietSanPham.ID_ThietKe "
                + "JOIN XuatXu ON XuatXu.ID = ChiTietSanPham.ID_XuatXu "
                + "JOIN ThuongHieu ON ThuongHieu.ID = ChiTietSanPham.ID_ThuongHieu "
                + "				WHERE ChiTietSanPham.TrangThai = ?";

        List<SanPham> listCTSP = new ArrayList<>();

        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);

            // Set parameter before executing query
            ps.setInt(1, TrangThai);

            rs = ps.executeQuery();

            while (rs.next()) {
                ChiTietSanPham ctsp = new ChiTietSanPham();
                SanPham sp = new SanPham();
                MauSac mauSac = new MauSac();
                LoaiDem loaiDem = new LoaiDem();
                XuatXu xuatXu = new XuatXu();
                DoCung doCung = new DoCung();
                DoDay doDay = new DoDay();
                ThuongHieu thuongHieu = new ThuongHieu();
                ThietKe thietKe = new ThietKe();
                KichThuoc kichThuoc = new KichThuoc();

                ctsp.setId(rs.getInt(1));
                sp.setTenSP(rs.getString(2));
                mauSac.setTenMS(rs.getString(3));
                kichThuoc.setTenKT(rs.getString(4));
                doDay.setTenDD(rs.getString(5));
                loaiDem.setTenLD(rs.getString(6));
                thietKe.setTenTK(rs.getString(7));
                xuatXu.setTenXX(rs.getString(8));
                thuongHieu.setTenTH(rs.getString(9));
                ctsp.setSoLuong(rs.getInt(10));
                ctsp.setGiaBan(rs.getFloat(11));

                // Assuming the following methods are available in ChiTietSanPham
                ctsp.setNgaySanXuat(rs.getDate(12));
                ctsp.setBaoHanh(rs.getInt(13));

                sp.setCtsp(ctsp);
                sp.setMauSac(mauSac);
                sp.setKichThuoc(kichThuoc);
                sp.setXuatXu(xuatXu);
                sp.setDoDay(doDay);
                sp.setDoCung(doCung);
                sp.setThietKe(thietKe);
                sp.setLoaiDem(loaiDem);
                sp.setThuongHieu(thuongHieu);

                listCTSP.add(sp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Properly close resources in a finally block
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return listCTSP;
    }

    public List<SanPham> getAllSanPhamTheoGia_TrangThai(int TrangThai) {
        String sql = "SELECT ChiTietSanPham.ID, SanPham.TenSP, MauSac.TenMS, KichThuoc.TenKT, DoDay.TenDD, LoaiDem.TenLD,\n"
                + "                ThietKe.TenTK, XuatXu.TenXX, ThuongHieu.TenTH, ChiTietSanPham.SoLuong, ChiTietSanPham.GiaBan\n"
                + "                FROM ChiTietSanPham\n"
                + "                JOIN SanPham ON SanPham.ID = ChiTietSanPham.ID_SanPham\n"
                + "                JOIN MauSac ON MauSac.ID = ChiTietSanPham.ID_MauSac\n"
                + "                JOIN KichThuoc ON KichThuoc.ID = ChiTietSanPham.ID_KichThuoc\n"
                + "                JOIN DoDay ON DoDay.ID = ChiTietSanPham.ID_DoDay\n"
                + "                JOIN LoaiDem ON LoaiDem.ID = ChiTietSanPham.ID_LoaiDem\n"
                + "                JOIN ThietKe ON ThietKe.ID = ChiTietSanPham.ID_ThietKe\n"
                + "                JOIN XuatXu ON XuatXu.ID = ChiTietSanPham.ID_XuatXu\n"
                + "                JOIN ThuongHieu ON ThuongHieu.ID = ChiTietSanPham.ID_ThuongHieu\n"
                + "				WHERE ChiTietSanPham.TrangThai = ?\n"
                + "				ORDER BY ChiTietSanPham.GiaBan DESC";
        List<SanPham> listCTSP = new ArrayList<>();
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            ps.setInt(1, TrangThai);
            while (rs.next()) {
                ChiTietSanPham ctsp = new ChiTietSanPham();
                SanPham sp = new SanPham();
                MauSac mauSac = new MauSac();
                LoaiDem loaiDem = new LoaiDem();
                XuatXu xuatXu = new XuatXu();
                DoCung doCung = new DoCung();
                DoDay doDay = new DoDay();
                ThuongHieu thuongHieu = new ThuongHieu();
                ThietKe thietKe = new ThietKe();
                KichThuoc kichThuoc = new KichThuoc();
                ctsp.setId(rs.getInt(1));
                sp.setTenSP(rs.getString(2));
                mauSac.setTenMS(rs.getString(3));
                kichThuoc.setTenKT(rs.getString(4));
                doDay.setTenDD(rs.getString(5));
                loaiDem.setTenLD(rs.getString(6));
                thietKe.setTenTK(rs.getString(7));
                xuatXu.setTenXX(rs.getString(8));
                thuongHieu.setTenTH(rs.getString(9));
                ctsp.setSoLuong(rs.getInt(10));
                ctsp.setGiaBan(rs.getFloat(11));
                ctsp.setNgaySanXuat(rs.getDate(12));
                ctsp.setBaoHanh(rs.getInt(13));
                sp.setCtsp(ctsp);
                sp.setMauSac(mauSac);
                sp.setKichThuoc(kichThuoc);
                sp.setXuatXu(xuatXu);
                sp.setDoDay(doDay);
                sp.setDoCung(doCung);
                sp.setThietKe(thietKe);
                sp.setLoaiDem(loaiDem);
                sp.setThuongHieu(thuongHieu);
                listCTSP.add(sp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listCTSP;
    }

    public List<SanPham> getAllSanPhamTheoSoLuong_TrangThai(int TrangThai) {
        String sql = "SELECT ChiTietSanPham.ID, SanPham.TenSP, MauSac.TenMS, KichThuoc.TenKT, DoDay.TenDD, LoaiDem.TenLD, "
                + "ThietKe.TenTK, XuatXu.TenXX, ThuongHieu.TenTH, ChiTietSanPham.SoLuong, ChiTietSanPham.GiaBan, "
                + "ChiTietSanPham.NgaySanXuat, ChiTietSanPham.BaoHanh "
                + "FROM ChiTietSanPham "
                + "JOIN SanPham ON SanPham.ID = ChiTietSanPham.ID_SanPham "
                + "JOIN MauSac ON MauSac.ID = ChiTietSanPham.ID_MauSac "
                + "JOIN KichThuoc ON KichThuoc.ID = ChiTietSanPham.ID_KichThuoc "
                + "JOIN DoDay ON DoDay.ID = ChiTietSanPham.ID_DoDay "
                + "JOIN LoaiDem ON LoaiDem.ID = ChiTietSanPham.ID_LoaiDem "
                + "JOIN ThietKe ON ThietKe.ID = ChiTietSanPham.ID_ThietKe "
                + "JOIN XuatXu ON XuatXu.ID = ChiTietSanPham.ID_XuatXu "
                + "JOIN ThuongHieu ON ThuongHieu.ID = ChiTietSanPham.ID_ThuongHieu "
                + "WHERE ChiTietSanPham.TrangThai = ? "
                + "ORDER BY ChiTietSanPham.SoLuong DESC";
        List<SanPham> listCTSP = new ArrayList<>();
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, TrangThai);  // Set the parameter value before executing the query
            rs = ps.executeQuery();
            while (rs.next()) {
                ChiTietSanPham ctsp = new ChiTietSanPham();
                SanPham sp = new SanPham();
                MauSac mauSac = new MauSac();
                LoaiDem loaiDem = new LoaiDem();
                XuatXu xuatXu = new XuatXu();
                DoCung doCung = new DoCung();
                DoDay doDay = new DoDay();
                ThuongHieu thuongHieu = new ThuongHieu();
                ThietKe thietKe = new ThietKe();
                KichThuoc kichThuoc = new KichThuoc();
                ctsp.setId(rs.getInt(1));
                sp.setTenSP(rs.getString(2));
                mauSac.setTenMS(rs.getString(3));
                kichThuoc.setTenKT(rs.getString(4));
                doDay.setTenDD(rs.getString(5));
                loaiDem.setTenLD(rs.getString(6));
                thietKe.setTenTK(rs.getString(7));
                xuatXu.setTenXX(rs.getString(8));
                thuongHieu.setTenTH(rs.getString(9));
                ctsp.setSoLuong(rs.getInt(10));
                ctsp.setGiaBan(rs.getFloat(11));
                ctsp.setNgaySanXuat(rs.getDate(12));
                ctsp.setBaoHanh(rs.getInt(13));
                sp.setCtsp(ctsp);
                sp.setMauSac(mauSac);
                sp.setKichThuoc(kichThuoc);
                sp.setXuatXu(xuatXu);
                sp.setDoDay(doDay);
                sp.setDoCung(doCung);
                sp.setThietKe(thietKe);
                sp.setLoaiDem(loaiDem);
                sp.setThuongHieu(thuongHieu);
                listCTSP.add(sp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listCTSP;
    }

    public double getDoanhThuTheoNgay(Date begin, Date end) {
        String sql = "select sum(ThanhTien) as TongThanhTien from HoaDon where TrangThai = 0 and NgayThanhToan >= ? and NgayThanhToan <= ?";
        double doanhThu = 0;
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, dateFormat.format(begin));
            ps.setString(2, dateFormat.format(end));
            rs = ps.executeQuery();
            while (rs.next()) {
                doanhThu = rs.getDouble(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Đóng kết nối và tài nguyên
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return doanhThu;
    }

    public int getTongDon(Date begin, Date end) {
        String sql = "select count(ID) as TongDon from HoaDon where NgayTao >= ? and NgayTao <= ?";
        int tongDon = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, begin);
            ps.setObject(2, end);
            rs = ps.executeQuery();

            if (rs.next()) {
                tongDon = rs.getInt("TongDon");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return tongDon;
    }

    public int getHoaDonCho(Date begin, Date end) {
        String sql = "select count(case when TrangThai = 3 then 1 end) as SoHoaDonCho"
                + " from HoaDon where NgayTao >= ? and NgayTao <= ?";
        int soHoaDonCho = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, begin);
            ps.setObject(2, end);
            rs = ps.executeQuery();
            if (rs.next()) {
                soHoaDonCho = rs.getInt("SoHoaDonCho");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return soHoaDonCho;
    }

    public int getHoaDonDaThanhToan(Date begin, Date end) {
        String sql = "select count(case when TrangThai = 0 then 1 end) as SoHoaDonDaThanhToan "
                + "from HoaDon where NgayTao >= ? and NgayTao <= ?";
        int soHoaDonDaThanhToan = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, begin);
            ps.setObject(2, end);
            rs = ps.executeQuery();
            if (rs.next()) {
                soHoaDonDaThanhToan = rs.getInt("SoHoaDonDaThanhToan");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return soHoaDonDaThanhToan;
    }

    public int getHoaDonDaHuy(Date begin, Date end) {
        String sql = "select count(case when TrangThai = 1 then 1 end) as SoHoaDonDaHuy"
                + " from HoaDon where NgayTao >= ? and NgayTao <= ?";
        int soHoaDonDaHuy = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, begin);
            ps.setObject(2, end);
            rs = ps.executeQuery();
            if (rs.next()) {
                soHoaDonDaHuy = rs.getInt("SoHoaDonDaHuy");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return soHoaDonDaHuy;
    }

//    public double getDoanhThuTheoNgayHienTai() {
//        String sql = "SELECT \n"
//                + "    CONVERT(DATE, NgayThanhToan) AS Ngay,\n"
//                + "    SUM(ISNULL(ThanhTien, 0)) AS TongThanhTien\n"
//                + "FROM \n"
//                + "    HoaDon\n"
//                + "WHERE \n"
//                + "    TrangThai = 0\n"
//                + "GROUP BY \n"
//                + "    CONVERT(DATE, NgayThanhToan)\n"
//                + "ORDER BY \n"
//                + "    Ngay;";
//        double doanhThu = 0;
//        try {
//            con = DBconnect.getConnection();
//            ps = con.prepareStatement(sql);
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                doanhThu = rs.getDouble(1);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return doanhThu;
//    }
    public double getDoanhThuTheoNgayHienTai() {
        String sql = "SELECT CONVERT(DATE, NgayThanhToan) AS Ngay, SUM(ISNULL(ThanhTien, 0)) AS TongThanhTien\n"
                + "FROM \n"
                + "    HoaDon WHERE TrangThai = 0 GROUP BY CONVERT(DATE, NgayThanhToan) ORDER BY Ngay;";

        double doanhThu = 0;
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                doanhThu = rs.getDouble("TongThanhTien");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return doanhThu;
    }

    public double getDoanhThuTheoThangHienTai() {
        String sql = "SELECT MONTH(NgayThanhToan) AS Thang, SUM(ISNULL(ThanhTien, 0)) AS TongThanhTien FROM \n"
                + "    HoaDon WHERE TrangThai = 0 GROUP BY MONTH(NgayThanhToan) ORDER BY Thang;";

        double doanhThu = 0;
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                doanhThu = rs.getDouble("TongThanhTien");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return doanhThu;
    }

    public double getDoanhThuTheoNamHienTai() {
        String sql = "select sum(ThanhTien) as TongThanhTien from HoaDon where TrangThai = 0";
        double doanhThu = 0;
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                doanhThu = rs.getDouble(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return doanhThu;
    }

    public double getDoanhThuTheoNgayThanhToan(Date ngayThanhToan) {
        String sql = "select sum(ThanhTien) from HoaDon where TrangThai = 0 and NgayThanhToan = ?";
        double doanhThu = 0;
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            ps.setString(1, dateFormat.format(ngayThanhToan));
            while (rs.next()) {
                doanhThu = rs.getDouble(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return doanhThu;
    }

    public int getSoLuongSPBanTheoNgay(Date ngayThanhToan) {
        String sql = "select sum(HoaDonChiTiet.SoLuong) from HoaDon\n"
                + "inner join HoaDonChiTiet on HoaDon.ID = HoaDonChiTiet.ID_HoaDon\n"
                + "where HoaDon.TrangThai = 0 and HoaDon.NgayThanhToan = ?";
        int soLuongSP = 0;
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            ps.setString(1, dateFormat.format(ngayThanhToan));
            while (rs.next()) {
                soLuongSP = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return soLuongSP;
    }

    public double getDoanhThuTheoThang(int month) {
        String sql = "select sum(ThanhTien) from HoaDon where TrangThai = 0 and \n"
                + "DATEPART(MONTH, HoaDon.NgayThanhToan) = ?";
        double doanhThu = 0;
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            ps.setInt(1, month);
            while (rs.next()) {
                doanhThu = rs.getDouble(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return doanhThu;
    }

    public int getSoLuongSanPhamTheoThang(int month) {
        String sql = "select sum(HoaDonChiTiet.SoLuong) from HoaDon\n"
                + "inner join HoaDonChiTiet on HoaDon.ID = HoaDonChiTiet.ID_HoaDon\n"
                + "where HoaDon.TrangThai = 0 and DATEPART(MONTH, HoaDon.NgayThanhToan) = ?";
        int soLuongSP = 0;
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            ps.setInt(1, month);
            while (rs.next()) {
                soLuongSP = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return soLuongSP;
    }
}
