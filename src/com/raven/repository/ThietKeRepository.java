package com.raven.repository;

import com.raven.classmodel.ThietKe;
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
public class ThietKeRepository {

    String sql = null;
    Connection con = null;
    ResultSet rs = null;
    PreparedStatement ps = null;

    public List<ThietKe> getAll() {
        sql = "SELECT ID, TenTK, TrangThai FROM ThietKe WHERE TrangThai = 1";
        List<ThietKe> listtk = new ArrayList<>();
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                ThietKe tk = new ThietKe(rs.getInt("ID"),rs.getString("TenTK"), rs.getBoolean("TrangThai"));
                listtk.add(tk);
            }
            return listtk;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public int themTK(ThietKe tk) {
        sql = "INSERT INTO ThietKe(TenTK, NgayTao, NgaySua, TrangThai) VALUES (?, GETDATE(), GETDATE(), ?)";
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, tk.getTenTK());
            ps.setObject(2, tk.isTrangThai());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    public boolean suaTK(ThietKe tk){
        sql = "UPDATE ThietKe SET TenTK = ? WHERE ID = ?";
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, tk.getTenTK());
            stm.setInt(2, tk.getId());
            stm.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public int xoaTK(int id) {
        sql = "UPDATE ThietKe SET TrangThai = 0 WHERE ID = ?";
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
    
    public boolean kiemTraThietKeDaTonTai(String tenThuocTinh) {
        sql = "SELECT COUNT(*) FROM ThietKe WHERE TenTK = ?";
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
