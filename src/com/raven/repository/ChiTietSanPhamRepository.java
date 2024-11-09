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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hoai Thu
 */
public class ChiTietSanPhamRepository {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sql = null;

    public int themSanPhamChiTiet(SanPhamChiTiet spct) {
        sql = "INSERT INTO [dbo].[ChiTietSanPham]([SoLuong], [GiaBan], [NgaySanXuat], [BaoHanh], [ID_SanPham], [ID_MauSac], [ID_KichThuoc], [ID_ThietKe], [ID_ThuongHieu], [ID_LoaiDem]\n"
                + ", [ID_XuatXu], [ID_DoCung], [ID_DoDay], [NgayTao], [NgaySua], [TrangThai]) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, GETDATE(), GETDATE(), 1)";
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, spct.getSoLuong());
            ps.setDouble(2, spct.getGiaBan());
            ps.setDate(3, new java.sql.Date(spct.getNgaySanXuat().getTime()));
            ps.setInt(4, spct.getNamBaoHanh());
            ps.setString(5, spct.getIdSanPham());
            ps.setString(6, spct.getIdMauSac());
            ps.setString(7, spct.getIdKichThuoc());
            ps.setString(8, spct.getIdThietKe());
            ps.setString(9, spct.getIdThuongHieu());
            ps.setString(10, spct.getIdLoaiDem());
            ps.setString(11, spct.getIdXuatXu());
            ps.setString(12, spct.getIdDoCung());
            ps.setString(13, spct.getIdDoDay());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int suaSanPhamChiTiet(SanPhamChiTiet spct, int id) {
        sql = "UPDATE [dbo].[ChiTietSanPham] SET [SoLuong] = ?, [GiaBan] = ?, [NgaySanXuat] = ?, [BaoHanh] = ?, [ID_SanPham] = ?, [ID_MauSac] = ?\n"
                + ", [ID_KichThuoc] = ?, [ID_ThietKe] = ?, [ID_ThuongHieu] = ?, [ID_LoaiDem] = ?, [ID_XuatXu] = ?\n"
                + ", [ID_DoCung] = ?, [ID_DoDay] = ?, [NgayTao] = GETDATE(), [NgaySua] = GETDATE(), [TrangThai] = 1\n"
                + " WHERE [ID] = ?";
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, spct.getSoLuong());
            ps.setObject(2, spct.getGiaBan());
            ps.setObject(3, spct.getNgaySanXuat());
            ps.setObject(4, spct.getNamBaoHanh());
            ps.setString(5, spct.getIdSanPham());
            ps.setString(6, spct.getIdMauSac());
            ps.setString(7, spct.getIdKichThuoc());
            ps.setString(8, spct.getIdThietKe());
            ps.setString(9, spct.getIdThuongHieu());
            ps.setString(10, spct.getIdLoaiDem());
            ps.setString(11, spct.getIdXuatXu());
            ps.setString(12, spct.getIdDoCung());
            ps.setString(13, spct.getIdDoDay());
            ps.setObject(14, id);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int updateSPCT(ChiTietSanPham ctsp, int ID) {
        String sql = "UPDATE ChiTietSanPham SET SoLuong = ? WHERE ID = ?";
        try {
            Connection con = DBconnect.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setObject(1, ctsp.getSoLuong());
            ps.setObject(2, ID);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public List<ChiTietSanPham> getTableSanPham() {
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
                + "JOIN ThuongHieu ON ThuongHieu.ID = ChiTietSanPham.ID_ThuongHieu\n";
        List<ChiTietSanPham> list = new ArrayList<>();
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                ChiTietSanPham ctsp = new ChiTietSanPham();
                SanPham sp = new SanPham();
                ctsp.setId(rs.getInt(1));
                sp.setTenSP(rs.getString(2));
                ctsp.setTenMS(rs.getString(3));
                ctsp.setTenKT(rs.getString(4));
                ctsp.setTenDD(rs.getString(5));
                ctsp.setTenLD(rs.getString(6));
                ctsp.setTenTK(rs.getString(7));
                ctsp.setTenXX(rs.getString(8));
                ctsp.setTenTH(rs.getString(9));
                ctsp.setSoLuong(rs.getInt(10));
                ctsp.setGiaBan(rs.getDouble(11));
                ctsp.setSanPham(sp);
                list.add(ctsp);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<ChiTietSanPham> getAll() {
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
        List<ChiTietSanPham> listctsp = new ArrayList<>();
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                ChiTietSanPham ctsps = new ChiTietSanPham();
                SanPham sp = new SanPham();
                LoaiDem ld = new LoaiDem();
                XuatXu xx = new XuatXu();
                DoCung dc = new DoCung();
                DoDay dd = new DoDay();
                ThuongHieu th = new ThuongHieu();
                ThietKe tk = new ThietKe();
                KichThuoc kt = new KichThuoc();
                MauSac ms = new MauSac();
                ctsps.setId(rs.getInt("ID"));
                sp.setTenSP(rs.getString("TenSP"));
                ld.setTenLD(rs.getString("TenLD"));
                xx.setTenXX(rs.getString("TenXX"));
                dc.setTenDC(rs.getString("TenDC"));
                dd.setTenDD(rs.getString("TenDD"));
                ms.setTenMS(rs.getString("TenMS"));
                th.setTenTH(rs.getString("TenTH"));
                tk.setTenTK(rs.getString("TenTK"));
                kt.setTenKT(rs.getString("TenKT"));
                ctsps.setSoLuong(rs.getInt("SoLuong"));
                ctsps.setGiaBan(rs.getDouble("GiaBan"));
                ctsps.setNgaySanXuat(rs.getDate("NgaySanXuat"));
                ctsps.setBaoHanh(rs.getInt("BaoHanh"));
                ctsps.setSanPham(sp);
                ctsps.setLoaiDem(ld);
                ctsps.setKichThuoc(kt);
                ctsps.setXuatXu(xx);
                ctsps.setDoCung(dc);
                ctsps.setDoDay(dd);
                ctsps.setKichThuoc(kt);
                ctsps.setMauSac(ms);
                ctsps.setThuongHieu(th);
                listctsp.add(ctsps);
            }
            return listctsp;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<ChiTietSanPham> getAllId(int id) {
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
                + "WHERE ChiTietSanPham.Id = ?";
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                ChiTietSanPham ctsps = new ChiTietSanPham();
                SanPham sp = new SanPham();
                LoaiDem ld = new LoaiDem();
                XuatXu xx = new XuatXu();
                DoCung dc = new DoCung();
                DoDay dd = new DoDay();
                ThuongHieu th = new ThuongHieu();
                ThietKe tk = new ThietKe();
                KichThuoc kt = new KichThuoc();
                MauSac ms = new MauSac();
                ctsps.setId(rs.getInt("ID"));
                sp.setTenSP(rs.getString("TenSP"));
                ld.setTenLD(rs.getString("TenLD"));
                xx.setTenXX(rs.getString("TenXX"));
                dc.setTenDC(rs.getString("TenDC"));
                dd.setTenDD(rs.getString("TenDD"));
                ms.setTenMS(rs.getString("TenMS"));
                th.setTenTH(rs.getString("TenTH"));
                tk.setTenTK(rs.getString("TenTK"));
                kt.setTenKT(rs.getString("TenKT"));
                ctsps.setSoLuong(rs.getInt("SoLuong"));
                ctsps.setGiaBan(rs.getDouble("GiaBan"));
                ctsps.setNgaySanXuat(rs.getDate("NgaySanXuat"));
                ctsps.setBaoHanh(rs.getInt("BaoHanh"));
                ctsps.setSanPham(sp);
                ctsps.setLoaiDem(ld);
                ctsps.setKichThuoc(kt);
                ctsps.setXuatXu(xx);
                ctsps.setDoCung(dc);
                ctsps.setDoDay(dd);
                ctsps.setKichThuoc(kt);
                ctsps.setMauSac(ms);
                ctsps.setThuongHieu(th);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public int updateTrangThai(int id) {
        sql = "UPDATE SanPham SET TrangThai = 0 WHERE ID = ?";
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, id);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    public void updateStockQuantity(int id, int newQuantity) {
        String sql = "UPDATE ChiTietSanPham SET soLuong = ? WHERE ID = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, newQuantity);
            ps.setInt(2, id);
            int affectedRows = ps.executeUpdate();

            // Check if the update was successful
            if (affectedRows == 1) {
//                System.out.println("Cập nhật thành công số lượng sản phẩm với id: " + id + " thành " + newQuantity);
            } else {
                System.out.println("Không tìm thấy sản phẩm với id: " + id);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi cập nhật số lượng sản phẩm: " + e.getMessage());
        }
    }
}
