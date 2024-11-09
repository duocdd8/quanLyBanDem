package com.raven.repository;

import com.raven.connection.DBconnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Hoai Thu
 */
public class GetIDSanPhamChiTiet {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sql = null;

    public String getIDMauSac(String mauSac) {
        String idMauSac = "";
        sql = "SELECT ID FROM MauSac WHERE TenMS = ?";
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, mauSac);
            rs = ps.executeQuery();
            while (rs.next()) {
                idMauSac = rs.getString(1);
            }
            return idMauSac;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getIDKichThuoc(String kichThuoc) {
        String idKichThuoc = "";
        sql = "SELECT ID FROM KichThuoc WHERE TenKT = ?";
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, kichThuoc);
            rs = ps.executeQuery();
            while (rs.next()) {
                idKichThuoc = rs.getString(1);
            }
            return idKichThuoc;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getIDThietKe(String thietKe) {
        String idThietKe = "";
        sql = "SELECT ID FROM ThietKe WHERE TenTK = ?";
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, thietKe);
            rs = ps.executeQuery();
            while (rs.next()) {
                idThietKe = rs.getString(1);
            }
            return idThietKe;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getIDThuongHieu(String thuongHieu) {
        String idThuongHieu = "";
        sql = "SELECT ID FROM ThuongHieu WHERE TenTH = ?";
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, thuongHieu);
            rs = ps.executeQuery();
            while (rs.next()) {
                idThuongHieu = rs.getString(1);
            }
            return idThuongHieu;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getIDLoaiDem(String loaiDem) {
        String idLoaiDem = "";
        sql = "SELECT ID FROM LoaiDem WHERE TenLD = ?";
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, loaiDem);
            rs = ps.executeQuery();
            while (rs.next()) {
                idLoaiDem = rs.getString(1);
            }
            return idLoaiDem;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getIDXuatXu(String xuatXu) {
        String idXuatXu = "";
        sql = "SELECT ID FROM XuatXu WHERE TenXX = ?";
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, xuatXu);
            rs = ps.executeQuery();
            while (rs.next()) {
                idXuatXu = rs.getString(1);
            }
            return idXuatXu;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getIDDoCung(String doCung) {
        String idDoCung = "";
        sql = "SELECT ID FROM DoCung WHERE TenDC = ?";
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, doCung);
            rs = ps.executeQuery();
            while (rs.next()) {
                idDoCung = rs.getString(1);
            }
            return idDoCung;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getIDDoDay(String doDay) {
        String idDoDay = "";
        sql = "SELECT ID FROM DoDay WHERE TenDD = ?";
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, doDay);
            rs = ps.executeQuery();
            while (rs.next()) {
                idDoDay = rs.getString(1);
            }
            return idDoDay;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public String getIDSanPham(String sanPham){
        String idSanPham = "";
        sql = "SELECT ID FROM SanPham WHERE TenSP = ?";
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, sanPham);
            rs = ps.executeQuery();
            while (rs.next()) {
                idSanPham = rs.getString(1);
            }
            return idSanPham;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
