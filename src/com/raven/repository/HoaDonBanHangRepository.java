package com.raven.repository;

import com.raven.classmodel.ChiTietSanPham;
import com.raven.classmodel.HoaDonBanHang;
import com.raven.classmodel.HoaDonChiTiet;
import com.raven.classmodel.SanPham;
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
public class HoaDonBanHangRepository {

    String sql = null;
    Connection con = null;
    ResultSet rs = null;
    PreparedStatement ps = null;

    public List<HoaDonBanHang> getChiTietSanPhamByMaHoaDon(int idHD) {
        List<HoaDonBanHang> banHangs = new ArrayList<>();
        sql = "SELECT HDCT.ID, SP.TenSP, CTSP.SoLuong, CTSP.GiaBan, (CTSP.SoLuong*CTSP.GiaBan) AS 'ThanhTien'"
                + "FROM HoaDonChiTiet HDCT "
                + "JOIN ChiTietSanPham CTSP ON HDCT.ID_SanPhamChiTiet = CTSP.ID "
                + "JOIN SanPham SP ON CTSP.ID_SanPham = SP.ID "
                + "WHERE HDCT.ID_HoaDon = ?";

        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idHD);
            rs = ps.executeQuery();
            while (rs.next()) {
                HoaDonBanHang hdbh = new HoaDonBanHang();
                hdbh.setId(rs.getInt("ID"));
                hdbh.setTenSP(rs.getString("TenSP"));
                hdbh.setSoLuong(rs.getInt("SoLuong"));
                hdbh.setGiaBan(rs.getDouble("GiaBan"));
                hdbh.setThanhTien(rs.getDouble("ThanhTien"));
                banHangs.add(hdbh);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return banHangs;
    }
}
