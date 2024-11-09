package com.raven.repository;

import com.raven.classmodel.XuatXu;
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
public class XuatXuRepository {

    String sql = null;
    Connection con = null;
    ResultSet rs = null;
    PreparedStatement ps = null;

    public List<XuatXu> getAll() {
        sql = "SELECT ID, TenXX, TrangThai FROM XuatXu WHERE TrangThai = 1";
        List<XuatXu> listms = new ArrayList<>();
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                XuatXu ms = new XuatXu(rs.getInt("ID"),rs.getString("TenXX"), rs.getBoolean("TrangThai"));
                listms.add(ms);
            }
            return listms;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public int themXX(XuatXu xx) {
        sql = "INSERT INTO XuatXu(TenXX, NgayTao, NgaySua, TrangThai) VALUES (?, GETDATE(), GETDATE(), ?)";
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, xx.getTenXX());
            ps.setObject(2, xx.isTrangThai());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    public boolean suaXX(XuatXu xx){
        sql = "UPDATE XuatXu SET TenXX = ? WHERE ID = ?";
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, xx.getTenXX());
            stm.setInt(2, xx.getId());
            stm.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public int xoaXX(int id) {
        sql = "UPDATE XuatXu SET TrangThai = 0 WHERE ID = ?";
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
    
    public boolean kiemTraXuatXuDaTonTai(String tenThuocTinh) {
        sql = "SELECT COUNT(*) FROM XuatXu WHERE TenXX = ?";
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
