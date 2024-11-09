package com.raven.repository;

import com.raven.connection.DBconnect;
import com.raven.viewmodel.ViewLichSuHoaDon;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Hoai Thu
 */
public class ViewLichSuHoaDonRepository {
    public ArrayList<ViewLichSuHoaDon> getAll() {
        ArrayList<ViewLichSuHoaDon> list = new ArrayList<>();
        try {
            Connection con = DBconnect.getConnection();
            String get = "SELECT HD.ID, NV.HoTen, KH.HoTen, HD.NgayThanhToan, HD.NgayTao FROM HoaDon AS HD\n"
                    + "JOIN NhanVien AS NV ON HD.ID_NhanVien = NV.ID\n"
                    + "JOIN KhachHang AS KH ON HD.ID_KhachHang = KH.ID";
            PreparedStatement ps = con.prepareStatement(get);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ViewLichSuHoaDon vls = new ViewLichSuHoaDon();
                vls.setId(rs.getInt(1));
                vls.setNv(rs.getString(2));
                vls.setKhachhang(rs.getString(3));
                vls.setNgaythanhtoan(rs.getDate(4));
                vls.setNgaytao(rs.getDate(5));
                list.add(vls);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }

    public ViewLichSuHoaDon getLichsu(int id) {
        ViewLichSuHoaDon vls = new ViewLichSuHoaDon();
        try {
            Connection con = DBconnect.getConnection();
            String get = "SELECT HD.ID, NV.HoTen, KH.HoTen, HD.NgayThanhToan, HD.NgayTao FROM HoaDon AS HD\n"
                    + "JOIN NhanVien AS NV ON HD.ID_NhanVien = NV.ID\n"
                    + "JOIN KhachHang AS KH ON HD.ID_KhachHang = KH.ID WHERE HD.ID = ?";
            PreparedStatement ps = con.prepareStatement(get);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                
                vls.setId(rs.getInt(1));
                vls.setNv(rs.getString(2));
                vls.setKhachhang(rs.getString(3));
                vls.setNgaythanhtoan(rs.getDate(4));
                vls.setNgaytao(rs.getDate(5));
               return vls;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return vls;
    }
}
