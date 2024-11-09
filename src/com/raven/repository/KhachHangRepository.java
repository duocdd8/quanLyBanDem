package com.raven.repository;

import com.raven.classmodel.KhachHang;
import com.raven.connection.DBconnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hoai Thu
 */
public class KhachHangRepository {

    String sql = null;
    Connection con = null;
    ResultSet rs = null;
    PreparedStatement ps = null;
    
    public String getTenKhachHangByIdKhachHang(int idKhachHang) {
        String tenKhachHang = "";
        String sql = "SELECT kh.HoTen "
                + "FROM HoaDon hd "
                + "JOIN KhachHang kh ON hd.Id_KhachHang = kh.ID "
                + "WHERE hd.Id_KhachHang = ?";
        try (Connection con = DBconnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idKhachHang);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    tenKhachHang = rs.getString("HoTen");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tenKhachHang;
    }
    
    
    public List<KhachHang> getAll() {
        sql = "SELECT ID, HoTen, GioiTinh, SoDT, Email, DiaChi, ThanhPho FROM KhachHang WHERE TrangThai = 1";
        List<KhachHang> listkh = new ArrayList<>();
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                KhachHang kh = new KhachHang(rs.getInt("ID"), rs.getString("HoTen"), rs.getBoolean("GioiTinh"), rs.getString("SoDT"), rs.getString("Email"), rs.getString("DiaChi"), rs.getString("ThanhPho"));
                listkh.add(kh);
            }
            return listkh;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public KhachHang getKH(int id) {
        sql = "SELECT ID, HoTen, GioiTinh, SoDT, Email, DiaChi, ThanhPho FROM KhachHang WHERE ID = ?";
        KhachHang kh = null;
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                kh = new KhachHang(rs.getInt("ID"), rs.getString("HoTen"), rs.getBoolean("GioiTinh"), rs.getString("SoDT"), rs.getString("Email"), rs.getString("DiaChi"), rs.getString("ThanhPho"));
            }
            return kh;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int insertKH(KhachHang kh) {
        sql = "INSERT INTO KhachHang(HoTen, GioiTinh, SoDT, Email, DiaChi, ThanhPho) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, kh.getHoTen());
            ps.setObject(2, kh.isGioiTinh());
            ps.setObject(3, kh.getSoDT());
            ps.setObject(4, kh.getEmail());
            ps.setObject(5, kh.getDiaChi());
            ps.setObject(6, kh.getThanhPho());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int updateKH(KhachHang kh, int id) {
        sql = "UPDATE KhachHang SET HoTen = ?, GioiTinh = ?, SoDT = ?, Email = ?, DiaChi = ?, ThanhPho = ? WHERE ID = ?";
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, kh.getHoTen());
            ps.setObject(2, kh.isGioiTinh());
            ps.setObject(3, kh.getSoDT());
            ps.setObject(4, kh.getEmail());
            ps.setObject(5, kh.getDiaChi());
            ps.setObject(6, kh.getThanhPho());
            ps.setObject(7, id);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int deleteKH(int id) {
        sql = "UPDATE KhachHang SET TrangThai = 0 WHERE ID = ?";
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

    public List<KhachHang> fillGenderMale() {
        List<KhachHang> listNam = new ArrayList();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            sql = "SELECT ID, HoTen, GioiTinh, SoDT, Email, DiaChi, ThanhPho FROM KhachHang WHERE GioiTinh = 0";
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                KhachHang kh = new KhachHang();
                kh.setId(rs.getInt(1));
                kh.setHoTen(rs.getString(2));
                kh.setGioiTinh(rs.getBoolean(3));
                kh.setSoDT(rs.getString(4));
                kh.setEmail(rs.getString(5));
                kh.setDiaChi(rs.getString(6));
                kh.setThanhPho(rs.getString(7));
                listNam.add(kh);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            rs.getStatement().getConnection().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listNam;
    }

    public List<KhachHang> fillGenderFemale() {
        List<KhachHang> listNu = new ArrayList();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            sql = "SELECT ID, HoTen, GioiTinh, SoDT, Email, DiaChi, ThanhPho FROM KhachHang WHERE GioiTinh = 1";
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                KhachHang kh = new KhachHang();
                kh.setId(rs.getInt(1));
                kh.setHoTen(rs.getString(2));
                kh.setGioiTinh(rs.getBoolean(3));
                kh.setSoDT(rs.getString(4));
                kh.setEmail(rs.getString(5));
                kh.setDiaChi(rs.getString(6));
                kh.setThanhPho(rs.getString(7));
                listNu.add(kh);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            rs.getStatement().getConnection().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listNu;
    }

    public String selectIDKhachHang(String hoTen, String soDT) {
        String khachHang = "";
        sql = "  SELECT ID FROM KhachHang WHERE HoTen = ? AND SoDT = ?";
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, hoTen);
            ps.setObject(2, soDT);
            rs = ps.executeQuery();
            while (rs.next()) {
                khachHang = rs.getString("ID");
            }
            return khachHang;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String selectIdKhachHangByTen(String hoTen) {
        String khachHang = "";
        sql = "SELECT ID FROM KhachHang WHERE HoTen = ?";
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, hoTen);
            rs = ps.executeQuery();
            while (rs.next()) {
                khachHang = rs.getString("ID");
            }
            return khachHang;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<String> layEmailDaTonTai() {
        List<String> Email = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            sql = "SELECT Email FROM KhachHang";
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            // Lấy danh sách số điện thoại từ kết quả truy vấn
            while (rs.next()) {
                String sdt = rs.getString("Email");
                Email.add(sdt);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Email;
    }

    public List<String> laySoDienThoaiDaTonTai() {
        List<String> soDienThoai = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            sql = "SELECT SoDT FROM KhachHang";
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            // Lấy danh sách số điện thoại từ kết quả truy vấn
            while (rs.next()) {
                String sdt = rs.getString("SoDT");
                soDienThoai.add(sdt);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return soDienThoai;
    }
    
    public KhachHang selectTenKhachHangByID(int ID) {
        KhachHang khachHang = null;
        sql = "SELECT HoTen FROM KhachHang WHERE ID = ?";
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, ID);
            rs = ps.executeQuery();
            while (rs.next()) {
                khachHang = new KhachHang();
                khachHang.setHoTen(rs.getString("HoTen"));
            }
            return khachHang;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
