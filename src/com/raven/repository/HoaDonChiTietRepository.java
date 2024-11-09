package com.raven.repository;

import com.raven.classmodel.ChiTietSanPham;
import com.raven.classmodel.HoaDonChiTiet;
import com.raven.classmodel.KichThuoc;
import com.raven.classmodel.MauSac;
import com.raven.classmodel.SanPham;
import com.raven.classmodel.ThuongHieu;
import com.raven.connection.DBconnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hoai Thu
 */
public class HoaDonChiTietRepository {

    public ArrayList<HoaDonChiTiet> getAll() {
        ArrayList<HoaDonChiTiet> list = new ArrayList<>();
        try {
            Connection con = DBconnect.getConnection();
            String get = "SELECT SP.TENSP, TH.TenTH, KT.TenKT, MS.TenMS, HDCT.SoLuong, HDCT.Gia FROM HoaDonChiTiet AS HDCT \n"
                    + "JOIN ChiTietSanPham AS CTSP ON HDCT.ID_ChiTietSanPham = CTSP.ID\n"
                    + "JOIN MauSac AS MS ON CTSP.ID_MauSac = MS.ID\n"
                    + "JOIN KichThuoc AS KT ON CTSP.ID_KichThuoc = KT.ID\n"
                    + "JOIN ThuongHieu AS TH ON CTSP.ID_ThuongHieu = TH.ID\n"
                    + "JOIN SanPham AS SP ON CTSP.ID_SanPham = SP.ID";
            PreparedStatement ps = con.prepareStatement(get);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDonChiTiet hdct = new HoaDonChiTiet();
                ChiTietSanPham spct = new ChiTietSanPham();
                SanPham sp = new SanPham();
                MauSac ms = new MauSac();
                ThuongHieu th = new ThuongHieu();
                KichThuoc kt = new KichThuoc();
                sp.setTenSP(rs.getString(1));
                th.setTenTH(rs.getString(2));
                kt.setTenKT(rs.getString(3));
                ms.setTenMS(rs.getString(4));
                hdct.setSoluong(rs.getInt(5));
                hdct.setGia(rs.getDouble(6));
                hdct.setSpct(spct);
                hdct.setSp(sp);
                hdct.setMs(ms);
                hdct.setTh(th);
                hdct.setKt(kt);
                list.add(hdct);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }

    public ArrayList<HoaDonChiTiet> getTimhdct(int id) {
        ArrayList<HoaDonChiTiet> list = new ArrayList<>();
        try {
            Connection con = DBconnect.getConnection();
            String get = "SELECT SP.TENSP, TH.TenTH, KT.TenKT, MS.TenMS, HDCT.SoLuong, HDCT.Gia FROM HoaDonChiTiet AS HDCT \n"
                    + "JOIN ChiTietSanPham AS CTSP ON HDCT.ID_SanPhamChiTiet = CTSP.ID\n"
                    + "JOIN MauSac AS MS ON CTSP.ID_MauSac = MS.ID\n"
                    + "JOIN KichThuoc AS KT ON CTSP.ID_KichThuoc = KT.ID\n"
                    + "JOIN ThuongHieu AS TH ON CTSP.ID_ThuongHieu = TH.ID\n"
                    + "JOIN SanPham AS SP ON CTSP.ID_SanPham = SP.ID\n"
                    + "JOIN HoaDon AS HD ON HDCT.ID_HoaDon = HD.ID\n"
                    + "WHERE HD.ID = ?";
            PreparedStatement ps = con.prepareStatement(get);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDonChiTiet hdct = new HoaDonChiTiet();
                ChiTietSanPham spct = new ChiTietSanPham();
                SanPham sp = new SanPham();
                MauSac ms = new MauSac();
                ThuongHieu th = new ThuongHieu();
                KichThuoc kt = new KichThuoc();
                sp.setTenSP(rs.getString(1));
                th.setTenTH(rs.getString(2));
                kt.setTenKT(rs.getString(3));
                ms.setTenMS(rs.getString(4));
                hdct.setSoluong(rs.getInt(5));
                hdct.setGia(rs.getDouble(6));
                hdct.setSpct(spct);
                hdct.setSp(sp);
                hdct.setMs(ms);
                hdct.setTh(th);
                hdct.setKt(kt);
                list.add(hdct);
                
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }

    public int insertHoaDonChiTiet(int idHoaDon, int idSanPhamChiTiet, int soLuong) {
        String sql = "INSERT INTO HOADONCHITIET (ID_HoaDon, ID_SanPhamChiTiet, SoLuong, Gia, TrangThai) "
                + "VALUES (?, ?, ?, (SELECT GiaBan FROM ChiTietSanPham WHERE ID = ?), 0)";
        try (Connection con = DBconnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idHoaDon);
            ps.setInt(2, idSanPhamChiTiet);
            ps.setInt(3, soLuong);
            ps.setInt(4, idSanPhamChiTiet);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int updateHDCTSoLuong(int idSPCT, int soLuong) {
        String sql = "UPDATE ChiTietSanPham SET SoLuong = SoLuong + ? WHERE ID = ?";
        try (Connection con = DBconnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, soLuong);
            ps.setInt(2, idSPCT);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public List<HoaDonChiTiet> getListAll(int idHDCT) {
        String sql = "SELECT ChiTietSanPham.ID, SanPham.TenSP, HoaDonChiTiet.SoLuong, ChiTietSanPham.GiaBan, (HoaDonChiTiet.SoLuong * ChiTietSanPham.GiaBan) AS ThanhTien\n"
                + "FROM HoaDonChiTiet \n"
                + "JOIN ChiTietSanPham ON HoaDonChiTiet.ID_SanPhamChiTiet = ChiTietSanPham.ID\n"
                + "JOIN SanPham ON ChiTietSanPham.ID_SanPham = SanPham.ID\n"
                + "WHERE HoaDonChiTiet.ID = ?";
        List<HoaDonChiTiet> listHDCT = new ArrayList<>();
        try (Connection conn = DBconnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idHDCT);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    HoaDonChiTiet hdct = new HoaDonChiTiet();
                    ChiTietSanPham ctsp = new ChiTietSanPham();
                    hdct.setIdCTSP(rs.getInt(1));
                    hdct.setTenSP(rs.getNString(2));
                    hdct.setSoluong(rs.getInt(3));
                    ctsp.setGiaBan(rs.getDouble(4));
                    ctsp.setThanhTien(rs.getDouble(5));
                    hdct.setSpct(ctsp);
                    listHDCT.add(hdct);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listHDCT;
    }

    public String selectIdHDCT(String id, String idSPCT) {
        String idHD = "";
        String sql = "SELECT ID from HoaDonChiTiet WHERE ID_HoaDon = ? AND ID_SanPhamChiTiet = ?";
        try {
            Connection con = DBconnect.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setObject(1, id);
            ps.setObject(2, idSPCT);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                idHD = rs.getString("ID");
            }
            return idHD;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public int updateHDCTSoLuong2(int idSPCT, int soLuong) {
        String sql = "UPDATE HoaDonChiTiet SET SoLuong = SoLuong + ? WHERE ID_SanPhamChiTiet = ?";
        try (Connection con = DBconnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, soLuong);
            ps.setInt(2, idSPCT);

            int affectedRows = ps.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Cập nhật thành công số lượng sản phẩm với ID: " + idSPCT + ", số lượng mới: " + soLuong);
            } else {
                System.out.println("Không tìm thấy sản phẩm với ID: " + idSPCT);
            }

            return affectedRows;
        } catch (Exception e) {
            System.out.println("Lỗi khi cập nhật số lượng sản phẩm: " + e.getMessage());
            return 0;
        }
    }

    public List<HoaDonChiTiet> getListAllByHoaDonId(int idHoaDon) {
        String sql = "SELECT HDCT.ID, HDCT.ID_SanPhamChiTiet, HDCT.SoLuong, HDCT.Gia "
                + "FROM HoaDonChiTiet AS HDCT "
                + "WHERE HDCT.ID_HoaDon = ?";
        List<HoaDonChiTiet> listHDCT = new ArrayList<>();
        try (Connection conn = DBconnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idHoaDon);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    HoaDonChiTiet hdct = new HoaDonChiTiet();
                    hdct.setId(rs.getInt("ID"));
                    hdct.setIdCTSP(rs.getInt("ID_SanPhamChiTiet"));
                    hdct.setSoluong(rs.getInt("SoLuong"));
                    hdct.setGia(rs.getDouble("Gia"));
                    listHDCT.add(hdct);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listHDCT;
    }
    
    public List<HoaDonChiTiet> getListAllByHoaDonId1(int idHoaDon) {
    String sql = "SELECT ChiTietSanPham.ID, SanPham.TenSP, HoaDonChiTiet.SoLuong, ChiTietSanPham.GiaBan, (HoaDonChiTiet.SoLuong * ChiTietSanPham.GiaBan) AS ThanhTien "
               + "FROM HoaDonChiTiet "
               + "JOIN ChiTietSanPham ON HoaDonChiTiet.ID_SanPhamChiTiet = ChiTietSanPham.ID "
               + "JOIN SanPham ON ChiTietSanPham.ID_SanPham = SanPham.ID "
               + "WHERE HoaDonChiTiet.ID_HoaDon = ?";
    List<HoaDonChiTiet> listHDCT = new ArrayList<>();
    try (Connection conn = DBconnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, idHoaDon);
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                HoaDonChiTiet hdct = new HoaDonChiTiet();
                ChiTietSanPham ctsp = new ChiTietSanPham();
                hdct.setIdCTSP(rs.getInt(1));
                hdct.setTenSP(rs.getString(2));
                hdct.setSoluong(rs.getInt(3));
                ctsp.setGiaBan(rs.getDouble(4));
                ctsp.setThanhTien(rs.getDouble(5));
                hdct.setSpct(ctsp);
                listHDCT.add(hdct);
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return listHDCT;
}
}
