package com.raven.repository;

import com.raven.classmodel.HinhThucThanhToan;
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
public class HinhThucThanhToanRepository {
    String sql = null;
    Connection con = null;
    ResultSet rs = null;
    PreparedStatement ps = null;

    public List<HinhThucThanhToan> getAll() {
        sql = "SELECT ID, HinhThucThanhToan, TrangThai FROM HinhThucThanhToan WHERE TrangThai = 1";
        List<HinhThucThanhToan> listhttt = new ArrayList<>();
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                HinhThucThanhToan httt = new HinhThucThanhToan(rs.getInt("ID"), rs.getString("HinhThucThanhToan"), rs.getInt("TrangThai"));
                listhttt.add(httt);
            }
            return listhttt;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public String selectIDHinhThucThanhToan(String tenHTTT) {
        String hinhThuc = "";
        sql = "SELECT ID FROM HinhThucThanhToan WHERE HinhThucThanhToan = ?";
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, tenHTTT);
            rs = ps.executeQuery();
            while (rs.next()) {
                hinhThuc = rs.getString("ID");
            }
            return hinhThuc;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public HinhThucThanhToan findId(String ten){
      HinhThucThanhToan httt= new HinhThucThanhToan();
        sql = "SELECT ID FROM HINHTHUCTHANHTOAN WHERE HINHTHUCTHANHTOAN=?";
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, ten);
            rs = ps.executeQuery();
            while (rs.next()) {
                httt.setId(rs.getInt(1));
            }
          
        } catch (Exception e) {
            e.printStackTrace();
        }
        return httt;
    }
}
