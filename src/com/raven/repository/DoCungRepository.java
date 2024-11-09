package com.raven.repository;

import com.raven.classmodel.DoCung;
import com.raven.connection.DBconnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hoai Thu
 */
public class DoCungRepository {

    String sql = null;
    Connection con = null;
    ResultSet rs = null;
    PreparedStatement ps = null;

    public List<DoCung> getAll() {
        sql = "SELECT ID, TenDC, TrangThai FROM DoCung WHERE TrangThai = 1";
        List<DoCung> listdc = new ArrayList<>();
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                DoCung dc = new DoCung(rs.getInt("ID"), rs.getString("TenDC"), rs.getBoolean("TrangThai"));
                listdc.add(dc);
            }
            return listdc;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int themDC(DoCung dc) {
        sql = "INSERT INTO DoCung(TenDC, NgayTao, NgaySua, TrangThai) VALUES (?, GETDATE(), GETDATE(), ?)";
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, dc.getTenDC());
            ps.setObject(2, dc.isTrangThai());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public boolean suaDC(DoCung dc) {
        sql = "UPDATE DoCung SET TenDC = ? WHERE ID = ?";
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, dc.getTenDC());
            stm.setInt(2, dc.getId());
            stm.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public int xoaDC(int id) {
        sql = "UPDATE DoCung SET TrangThai = 0 WHERE ID = ?";
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

    public boolean kiemTraDoCungDaTonTai(String tenThuocTinh) {
        sql = "SELECT COUNT(*) FROM DoCung WHERE TenDC LIKE ?";
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
