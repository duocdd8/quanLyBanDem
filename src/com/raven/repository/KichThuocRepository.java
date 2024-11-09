package com.raven.repository;

import com.raven.classmodel.KichThuoc;
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
public class KichThuocRepository {

    String sql = null;
    Connection con = null;
    ResultSet rs = null;
    PreparedStatement ps = null;

    public List<KichThuoc> getAll() {
        sql = "SELECT ID, TenKT, TrangThai FROM KichThuoc WHERE TrangThai = 1";
        List<KichThuoc> listkt = new ArrayList<>();
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                KichThuoc kt = new KichThuoc(rs.getInt("ID"),rs.getString("TenKT"), rs.getBoolean("TrangThai"));
                listkt.add(kt);
            }
            return listkt;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public int themKT(KichThuoc kt) {
        sql = "INSERT INTO KichThuoc(TenKT, NgayTao, NgaySua, TrangThai) VALUES (?, GETDATE(), GETDATE(), ?)";
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, kt.getTenKT());
            ps.setObject(2, kt.isTrangThai());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    public boolean suaKT(KichThuoc kt){
        sql = "UPDATE KichThuoc SET TenKT = ? WHERE ID = ?";
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, kt.getTenKT());
            stm.setInt(2, kt.getId());
            stm.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public int xoaKT(int id) {
        sql = "UPDATE KichThuoc SET TrangThai = 0 WHERE ID = ?";
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
    
    public boolean kiemTraKichThuocDaTonTai(String tenThuocTinh) {
        sql = "SELECT COUNT(*) FROM KichThuoc WHERE TenKT = ?";
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
