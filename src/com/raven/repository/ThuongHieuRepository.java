package com.raven.repository;

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
public class ThuongHieuRepository {

    String sql = null;
    Connection con = null;
    ResultSet rs = null;
    PreparedStatement ps = null;

    public List<ThuongHieu> getAll() {
        sql = "SELECT ID, TenTH, TrangThai FROM ThuongHieu WHERE TrangThai = 1";
        List<ThuongHieu> listth = new ArrayList<>();
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                ThuongHieu th = new ThuongHieu(rs.getInt("ID"),rs.getString("TenTH"), rs.getBoolean("TrangThai"));
                listth.add(th);
            }
            return listth;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public int themTH(ThuongHieu th) {
        sql = "INSERT INTO ThuongHieu(TenTH, NgayTao, NgaySua, TrangThai) VALUES (?, GETDATE(), GETDATE(), ?)";
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, th.getTenTH());
            ps.setObject(2, th.isTrangThai());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    public boolean suaTH(ThuongHieu th){
        sql = "UPDATE ThuongHieu SET TenMS = ? WHERE ID = ?";
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, th.getTenTH());
            stm.setInt(2, th.getId());
            stm.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public int xoaTH(int id) {
        sql = "UPDATE ThuongHieu SET TrangThai = 0 WHERE ID = ?";
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
    
    public boolean kiemTraThuongHieuDaTonTai(String tenThuocTinh) {
        sql = "SELECT COUNT(*) FROM ThuongHieu WHERE TenTH = ?";
        try (
            Connection con = DBconnect.getConnection(); 
            PreparedStatement ps = con.prepareStatement(sql)
                ) {
                    ps.setString(1, tenThuocTinh);
                    try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        int count = rs.getInt(1);
                        return count > 0;
                    }
                }
            } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
