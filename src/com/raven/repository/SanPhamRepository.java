package com.raven.repository;

import com.raven.classmodel.ChiTietSanPham;
import com.raven.classmodel.DoCung;
import com.raven.classmodel.DoDay;
import com.raven.classmodel.KichThuoc;
import com.raven.classmodel.LoaiDem;
import com.raven.classmodel.MauSac;
import com.raven.classmodel.SanPham;
import com.raven.classmodel.SanPhamChiTiet;
import com.raven.classmodel.ThietKe;
import com.raven.classmodel.ThuongHieu;
import com.raven.classmodel.XuatXu;
import com.raven.connection.DBconnect;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

/**
 *
 * @author Hoai Thu
 */
public class SanPhamRepository {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sql = null;

    
    public List<SanPham> fillCTSP() {
        List<SanPham> listSP = new ArrayList();
        try {
            sql = "SELECT ChiTietSanPham.ID, SanPham.TenSP, LoaiDem.TenLD, XuatXu.TenXX, DoCung.TenDC, DoDay.TenDD, MauSac.TenMS, ThuongHieu.TenTH, ThietKe.TenTK, KichThuoc.TenKT, \n"
                    + "ChiTietSanPham.SoLuong, ChiTietSanPham.GiaBan, ChiTietSanPham.NgaySanXuat, ChiTietSanPham.BaoHanh\n"
                    + "FROM ChiTietSanPham \n"
                    + "JOIN SanPham ON SanPham.ID = ChiTietSanPham.ID_SanPham\n"
                    + "JOIN MauSac ON MauSac.ID = ChiTietSanPham.ID_MauSac\n"
                    + "JOIN LoaiDem ON LoaiDem.ID = ChiTietSanPham.ID_LoaiDem\n"
                    + "JOIN XuatXu ON XuatXu.ID = ChiTietSanPham.ID_XuatXu\n"
                    + "JOIN KichThuoc ON KichThuoc.ID = ChiTietSanPham.ID_KichThuoc\n"
                    + "JOIN DoCung ON DoCung.ID = ChiTietSanPham.ID_DoCung\n"
                    + "JOIN DoDay ON DoDay.ID = ChiTietSanPham.ID_DoDay\n"
                    + "JOIN ThuongHieu ON ThuongHieu.ID = ChiTietSanPham.ID_ThuongHieu\n"
                    + "JOIN ThietKe ON ThietKe.ID = ChiTietSanPham.ID_ThietKe\n"
                    + "WHERE ChiTietSanPham.TrangThai = 1";
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
                loaiDem.setTenLD(rs.getString(3));
                xuatXu.setTenXX(rs.getString(4));
                doCung.setTenDC(rs.getString(5));
                doDay.setTenDD(rs.getString(6));
                mauSac.setTenMS(rs.getString(7));
                thuongHieu.setTenTH(rs.getString(8));
                thietKe.setTenTK(rs.getString(9));
                kichThuoc.setTenKT(rs.getString(10));
                ctsp.setSoLuong(rs.getInt(11));
                ctsp.setGiaBan(rs.getFloat(12));
                ctsp.setNgaySanXuat(rs.getDate(13));
                ctsp.setBaoHanh(rs.getInt(14));
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
        try {
            rs.getStatement().getConnection().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listSP;
    }

    public List<SanPham> fillSP() {
        List<SanPham> listSP = new ArrayList();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT ID, TenSP, TrangThai FROM SanPham WHERE TrangThai = 1";
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setId(rs.getInt(1));
                sp.setTenSP(rs.getString(2));
                sp.setTrangThai(rs.getBoolean(3));
                listSP.add(sp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            rs.getStatement().getConnection().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listSP;
    }

    public List<SanPham> fillSanPhamConHang() {
        List<SanPham> listspch = new ArrayList();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT ID, TenSP, TrangThai FROM SanPham WHERE TrangThai = 1";
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setId(rs.getInt(1));
                sp.setTenSP(rs.getString(2));
                sp.setTrangThai(rs.getBoolean(3));
                listspch.add(sp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            rs.getStatement().getConnection().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listspch;
    }

    public List<SanPham> fillSanPhamHetHang() {
        List<SanPham> listsphh = new ArrayList();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT ID, TenSP, TrangThai FROM SanPham WHERE TrangThai = 0";
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setId(rs.getInt(1));
                sp.setTenSP(rs.getString(2));
                sp.setTrangThai(rs.getBoolean(3));
                listsphh.add(sp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            rs.getStatement().getConnection().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listsphh;
    }

    public int insertSanPham(SanPham sp) {
        sql = "INSERT INTO SanPham(TenSP, NgayTao, NgaySua, TrangThai) VALUES (?, GETDATE(), GETDATE(), 1)";
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, sp.getTenSP());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public SanPham getSP(int id) {
        sql = "SELECT TenSP, TrangThai FROM SanPham WHERE ID =?";
        SanPham sp = null;
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                sp = new SanPham();
                sp.setTenSP(rs.getString(1));
                sp.setTrangThai(rs.getBoolean(2));
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<SanPham> getAllSP(boolean TrangThai) {
        List<SanPham> listSP = new ArrayList();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT ID, TenSP, TrangThai FROM SanPham WHERE TrangThai = ?";
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setBoolean(1, TrangThai);
            rs = ps.executeQuery();
            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setId(rs.getInt(1));
                sp.setTenSP(rs.getString(2));
                sp.setTrangThai(rs.getBoolean(3));
                listSP.add(sp);
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
        return listSP;
    }

    public List<SanPham> getAll() {
        sql = "SELECT ID, TenSP, TrangThai FROM SanPham";
        List<SanPham> listSP = new ArrayList();
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setId(rs.getInt(1));
                sp.setTenSP(rs.getString(2));
                sp.setTrangThai(rs.getBoolean(3));
                listSP.add(sp);
            }
            return listSP;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int updateSP(SanPham sp, int id) {
        sql = "UPDATE SanPham SET TenSP = ?  WHERE ID = ?";
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, sp.getTenSP());
            ps.setObject(2, id);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public List<SanPham> fillSPBH() {
        List<SanPham> listspbh = new ArrayList<>();
        try {
            sql = "SELECT ChiTietSanPham.ID, SanPham.TenSP, MauSac.TenMS, KichThuoc.TenKT, DoDay.TenDD, LoaiDem.TenLD,\n"
                    + "ThietKe.TenTK, XuatXu.TenXX, ThuongHieu.TenTH, ChiTietSanPham.SoLuong, ChiTietSanPham.GiaBan\n"
                    + "FROM ChiTietSanPham\n"
                    + "JOIN SanPham ON SanPham.ID = ChiTietSanPham.ID_SanPham\n"
                    + "JOIN MauSac ON MauSac.ID = ChiTietSanPham.ID_MauSac\n"
                    + "JOIN KichThuoc ON KichThuoc.ID = ChiTietSanPham.ID_KichThuoc\n"
                    + "JOIN DoDay ON DoDay.ID = ChiTietSanPham.ID_DoDay\n"
                    + "JOIN LoaiDem ON LoaiDem.ID = ChiTietSanPham.ID_LoaiDem\n"
                    + "JOIN ThietKe ON ThietKe.ID = ChiTietSanPham.ID_ThietKe\n"
                    + "JOIN XuatXu ON XuatXu.ID = ChiTietSanPham.ID_XuatXu\n"
                    + "JOIN ThuongHieu ON ThuongHieu.ID = ChiTietSanPham.ID_ThuongHieu\n"
                    + "WHERE ChiTietSanPham.TrangThai = 1 ";
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
                ctsp.setGiaBan(rs.getDouble(11));
                sp.setCtsp(ctsp);
                sp.setDoCung(doCung);
                sp.setDoDay(doDay);
                sp.setMauSac(mauSac);
                sp.setKichThuoc(kichThuoc);
                sp.setXuatXu(xuatXu);
                sp.setThietKe(thietKe);
                sp.setThuongHieu(thuongHieu);
                sp.setLoaiDem(loaiDem);
                listspbh.add(sp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            rs.getStatement().getConnection().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listspbh;
    }

    public int updateSoLuong(int soLuong, int idSPCT) {
        sql = "UPDATE ChiTietSanPham SET SoLuong =SoLuong - ? WHERE ID = ?";
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, soLuong);
            ps.setInt(2, idSPCT);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int updateSoLuongHoanLai(int soLuong, int idSPCT) {
        sql = "UPDATE ChiTietSanPham SET SoLuong = SoLuong + ? WHERE ID = ?";
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, soLuong);
            ps.setInt(2, idSPCT);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public List<SanPham> locSanPham(String mauSac, String kichThuoc, String xuatXu) {
        List<SanPham> listSP = new ArrayList<>();
        String sql = "SELECT ChiTietSanPham.ID, SanPham.TenSP, LoaiDem.TenLD, XuatXu.TenXX, DoCung.TenDC, DoDay.TenDD, MauSac.TenMS, ThuongHieu.TenTH, ThietKe.TenTK, KichThuoc.TenKT, "
                + "ChiTietSanPham.SoLuong, ChiTietSanPham.GiaBan, ChiTietSanPham.NgaySanXuat, ChiTietSanPham.BaoHanh "
                + "FROM ChiTietSanPham "
                + "JOIN SanPham ON SanPham.ID = ChiTietSanPham.ID_SanPham "
                + "JOIN MauSac ON MauSac.ID = ChiTietSanPham.ID_MauSac "
                + "JOIN LoaiDem ON LoaiDem.ID = ChiTietSanPham.ID_LoaiDem "
                + "JOIN XuatXu ON XuatXu.ID = ChiTietSanPham.ID_XuatXu "
                + "JOIN KichThuoc ON KichThuoc.ID = ChiTietSanPham.ID_KichThuoc "
                + "JOIN DoCung ON DoCung.ID = ChiTietSanPham.ID_DoCung "
                + "JOIN DoDay ON DoDay.ID = ChiTietSanPham.ID_DoDay "
                + "JOIN ThuongHieu ON ThuongHieu.ID = ChiTietSanPham.ID_ThuongHieu "
                + "JOIN ThietKe ON ThietKe.ID = ChiTietSanPham.ID_ThietKe "
                + "WHERE ChiTietSanPham.TrangThai = 1";

        if (!"Tất cả".equals(mauSac)) {
            sql += " AND MauSac.TenMS = ?";
        }
        if (!"Tất cả".equals(kichThuoc)) {
            sql += " AND KichThuoc.TenKT = ?";
        }
        if (!"Tất cả".equals(xuatXu)) {
            sql += " AND XuatXu.TenXX = ?";
        }

        try (Connection con = DBconnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            int paramIndex = 1;
            if (!"Tất cả".equals(mauSac)) {
                ps.setString(paramIndex++, mauSac);
            }
            if (!"Tất cả".equals(kichThuoc)) {
                ps.setString(paramIndex++, kichThuoc);
            }
            if (!"Tất cả".equals(xuatXu)) {
                ps.setString(paramIndex++, xuatXu);
            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ChiTietSanPham ctsp = new ChiTietSanPham();
                SanPham sp = new SanPham();
                MauSac ms = new MauSac();
                LoaiDem ld = new LoaiDem();
                XuatXu xx = new XuatXu();
                DoCung dc = new DoCung();
                DoDay dd = new DoDay();
                ThuongHieu th = new ThuongHieu();
                ThietKe tk = new ThietKe();
                KichThuoc kt = new KichThuoc();
                ctsp.setId(rs.getInt(1));
                sp.setTenSP(rs.getString(2));
                ld.setTenLD(rs.getString(3));
                xx.setTenXX(rs.getString(4));
                dc.setTenDC(rs.getString(5));
                dd.setTenDD(rs.getString(6));
                ms.setTenMS(rs.getString(7));
                th.setTenTH(rs.getString(8));
                tk.setTenTK(rs.getString(9));
                kt.setTenKT(rs.getString(10));
                ctsp.setSoLuong(rs.getInt(11));
                ctsp.setGiaBan(rs.getFloat(12));
                ctsp.setNgaySanXuat(rs.getDate(13));
                ctsp.setBaoHanh(rs.getInt(14));
                sp.setCtsp(ctsp);
                sp.setMauSac(ms);
                sp.setKichThuoc(kt);
                sp.setXuatXu(xx);
                sp.setDoDay(dd);
                sp.setDoCung(dc);
                sp.setThietKe(tk);
                sp.setLoaiDem(ld);
                sp.setThuongHieu(th);
                listSP.add(sp);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listSP;
    }

    private int id() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
