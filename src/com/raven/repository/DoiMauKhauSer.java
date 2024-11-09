/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.repository;



import com.raven.classmodel.DoiMatKhau;
import com.raven.connection.DBconnect;
import java.sql.*;

/**
 *
 * @author Admin
 */
public class DoiMauKhauSer {
    
    public int updatePass(DoiMatKhau dmk){
        try {
            
             Connection con = DBconnect.getConnection();
            String getUp = "UPDATE NhanVien SET MatKhau = ? WHERE TenDangNhap = ?"; 
            PreparedStatement ps = con.prepareStatement(getUp);
            ps.setString(1, dmk.getMatKhau());
            ps.setString(2, dmk.getTenDangNhap());
            return ps.executeUpdate();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public boolean getUser(DoiMatKhau dmk){
        try {
            Connection con = DBconnect.getConnection();
            String get = "SELECT TenDangNhap FROM NHANVIEN WHERE TenDangNhap = ?";
            PreparedStatement ps = con.prepareStatement(get);
            ps.setString(1, dmk.getTenDangNhap());
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    } 
    
    public DoiMatKhau getEmail(String email){
        DoiMatKhau dmk = new DoiMatKhau();
        try {
            Connection con = DBconnect.getConnection();
            String get= "SELECT EMAIL FROM NHANVIEN WHERE EMAIL=?";
            PreparedStatement ps = con.prepareStatement(get);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                dmk.setEmail(rs.getString(1));
                return dmk;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return dmk;
    }
    
}
