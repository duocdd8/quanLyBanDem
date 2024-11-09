package com.raven.repository;

import com.raven.classmodel.DoDay;
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
public class DoDayRepository {

    String sql = null;
    Connection con = null;
    ResultSet rs = null;
    PreparedStatement ps = null;

    public List<DoDay> getAll() {
        sql = "SELECT ID, TenDD, TrangThai FROM DoDay WHERE TrangThai = 1";
        List<DoDay> listdc = new ArrayList<>();
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                DoDay dc = new DoDay(rs.getInt("ID"),rs.getString("TenDD"), rs.getBoolean("TrangThai"));
                listdc.add(dc);
            }
            return listdc;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public int themDD(DoDay dd) {
        sql = "INSERT INTO DoDay(TenDD, NgayTao, NgaySua, TrangThai) VALUES (?, GETDATE(), GETDATE(), ?)";
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, dd.getTenDD());
            ps.setObject(2, dd.isTrangThai());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    public boolean suaDD(DoDay dd){
        sql = "UPDATE DoDay SET TenDD = ? WHERE ID = ?";
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, dd.getTenDD());
            stm.setInt(2, dd.getId());
            stm.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public int xoaDD(int id) {
        sql = "UPDATE DoDay SET TrangThai = 0 WHERE ID = ?";
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
    
    public boolean kiemTraDoDayDaTonTai(String tenThuocTinh) {
        sql = "SELECT COUNT(*) FROM DoDay WHERE TenDD = ?";
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
