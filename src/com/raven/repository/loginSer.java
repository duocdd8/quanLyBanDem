/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.repository;

import com.raven.classmodel.login;
import com.raven.connection.DBconnect;
import java.sql.*;

/**
 *
 * @author Admin
 */
public class loginSer {

    public login getDangNhap(String TenDangNhap, String mk) {//, int trangthai
        login lg = new login();
        try {
            Connection con = DBconnect.getConnection();
            String get = "SELECT ID, TenDangNhap, MatKhau, ChucVu, TrangThai  FROM NhanVien WHERE TenDangNhap = ? AND MatKhau = ?";//
            PreparedStatement ps = con.prepareStatement(get);
            ps.setString(1, TenDangNhap);
            ps.setString(2, mk);
//            ps.setInt(3, trangthai);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                
                lg.setID(rs.getInt(1));
                lg.setTenDN(rs.getString(2));
                lg.setMatKhau(rs.getString(3));
                lg.setChucVu(rs.getInt(4));
                lg.setTrnagThai(rs.getInt(5));
                return lg;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

}
