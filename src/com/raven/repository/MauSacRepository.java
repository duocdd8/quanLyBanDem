package com.raven.repository;

import com.raven.classmodel.MauSac;
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
public class MauSacRepository {

    String sql = null;
    Connection con = null;
    ResultSet rs = null;
    PreparedStatement ps = null;

    public List<MauSac> getAll() {
        sql = "SELECT ID, TenMS, TrangThai FROM MauSac WHERE TrangThai = 1";
        List<MauSac> listms = new ArrayList<>();
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                MauSac ms = new MauSac(rs.getInt("ID"),rs.getString("TenMS"), rs.getBoolean("TrangThai"));
                listms.add(ms);
            }
            return listms;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public int themMS(MauSac ms) {
        sql = "INSERT INTO MauSac(TenMS, NgayTao, NgaySua, TrangThai) VALUES (?, GETDATE(), GETDATE(), ?)";
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, ms.getTenMS());
            ps.setObject(2, ms.isTrangThai());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    public boolean suaMS(MauSac ms){
        sql = "UPDATE MauSac SET TenMS = ? WHERE ID = ?";
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, ms.getTenMS());
            stm.setInt(2, ms.getId());
            stm.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public int xoaMS(int id) {
        sql = "UPDATE MauSac SET TrangThai = 0 WHERE ID = ?";
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
    
    public boolean kiemTraMauSacDaTonTai(String tenThuocTinh) {
        sql = "SELECT COUNT(*) FROM MauSac WHERE TenMS LIKE ?";
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
