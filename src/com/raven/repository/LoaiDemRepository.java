package com.raven.repository;

import com.raven.classmodel.LoaiDem;
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
public class LoaiDemRepository {

    String sql = null;
    Connection con = null;
    ResultSet rs = null;
    PreparedStatement ps = null;

    public List<LoaiDem> getAll() {
        sql = "SELECT ID, TenLD, TrangThai FROM LoaiDem WHERE TrangThai = 1";
        List<LoaiDem> listld = new ArrayList<>();
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                LoaiDem ld = new LoaiDem(rs.getInt("ID"),rs.getString("TenLD"), rs.getBoolean("TrangThai"));
                listld.add(ld);
            }
            return listld;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public int themLD(LoaiDem kt) {
        sql = "INSERT INTO LoaiDem(TenLD, NgayTao, NgaySua, TrangThai) VALUES (?, GETDATE(), GETDATE(), ?)";
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, kt.getTenLD());
            ps.setObject(2, kt.isTrangThai());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    public boolean suaLD(LoaiDem ld){
        sql = "UPDATE LoaiDem SET TenLD = ? WHERE ID = ?";
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, ld.getTenLD());
            stm.setInt(2, ld.getId());
            stm.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public int xoaLD(int id) {
        sql = "UPDATE LoaiDem SET TrangThai = 0 WHERE ID = ?";
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
    
    public boolean kiemTraLoaiDemDaTonTai(String tenThuocTinh) {
        sql = "SELECT COUNT(*) FROM LoaiDem WHERE TenLD = ?";
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
