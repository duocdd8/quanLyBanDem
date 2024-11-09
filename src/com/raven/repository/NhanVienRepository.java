package com.raven.repository;

import com.raven.classmodel.NhanVien;
import com.raven.connection.DBconnect;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class NhanVienRepository {

    Connection con = null;
    PreparedStatement ps = null;
    String sql = null;
    ResultSet rs = null;
    
    public String getTenNhanVienByIdNhanVien(int idNhanVien) {
        String tenNhanVien = "";
        String sql = "SELECT nv.HoTen "
                + "FROM HoaDon hd "
                + "JOIN NhanVien nv ON hd.Id_NhanVien = nv.ID "
                + "WHERE hd.Id_NhanVien = ?";
        try (Connection con = DBconnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idNhanVien);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    tenNhanVien = rs.getString("HoTen");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tenNhanVien;
    }
    
    public List<NhanVien> getAll(boolean TrangThai) {
        String sql = "SELECT ID, HoTen, GioiTinh, NgaySinh, Email, CCCD, SoDT, TenDangNhap, MatKhau, DiaChi, ChucVu FROM NhanVien WHERE TrangThai = ?";
        List<NhanVien> listnv = new ArrayList();
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setBoolean(1, TrangThai);
            rs = ps.executeQuery();
            while (rs.next()) {
                NhanVien nv = new NhanVien();
                nv.setId(rs.getInt(1));
                nv.setHoTen(rs.getString(2));
                nv.setGioiTinh(rs.getBoolean(3));
                nv.setNgaySinh(rs.getDate(4));
                nv.setEmail(rs.getString(5));
                nv.setCccd(rs.getString(6));
                nv.setSdt(rs.getString(7));
                nv.setTenDN(rs.getString(8));
                nv.setMatKhau(rs.getString(9));
                nv.setDiaChi(rs.getString(10));
                nv.setChucVu(rs.getBoolean(11));
                listnv.add(nv);
            }
            return listnv;
        } catch (Exception e) {
            return null;
        }
    }

    public List<NhanVien> fillFemale() {
        List<NhanVien> listNu = new ArrayList();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            sql = "SELECT ID, HoTen, GioiTinh, NgaySinh, Email, CCCD, SoDT, TenDangNhap, MatKhau, DiaChi, ChucVu FROM NhanVien WHERE GioiTinh = 1";
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                NhanVien nv = new NhanVien();
                nv.setId(rs.getInt(1));
                nv.setHoTen(rs.getString(2));
                nv.setGioiTinh(rs.getBoolean(3));
                nv.setNgaySinh(rs.getDate(4));
                nv.setEmail(rs.getString(5));
                nv.setCccd(rs.getString(6));
                nv.setSdt(rs.getString(7));
                nv.setTenDN(rs.getString(8));
                nv.setMatKhau(rs.getString(9));
                nv.setDiaChi(rs.getString(10));
                nv.setChucVu(rs.getBoolean(11));
                listNu.add(nv);
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

    public List<NhanVien> fillMale() {
        List<NhanVien> listNam = new ArrayList();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            sql = "SELECT ID, HoTen, GioiTinh, NgaySinh, Email, CCCD, SoDT, TenDangNhap, MatKhau, DiaChi, ChucVu FROM NhanVien WHERE GioiTinh = 0";
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                NhanVien nv = new NhanVien();
                nv.setId(rs.getInt(1));
                nv.setHoTen(rs.getString(2));
                nv.setGioiTinh(rs.getBoolean(3));
                nv.setNgaySinh(rs.getDate(4));
                nv.setEmail(rs.getString(5));
                nv.setCccd(rs.getString(6));
                nv.setSdt(rs.getString(7));
                nv.setTenDN(rs.getString(8));
                nv.setMatKhau(rs.getString(9));
                nv.setDiaChi(rs.getString(10));
                nv.setChucVu(rs.getBoolean(11));
                listNam.add(nv);
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

    public NhanVien getNV(int id) {
        String sql = "SELECT ID, HoTen, GioiTinh, NgaySinh, Email, CCCD, SoDT, TenDangNhap, MatKhau, DiaChi, ChucVu FROM NhanVien WHERE ID = ?";
        NhanVien nv = null;
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                nv = new NhanVien(rs.getInt("ID"), rs.getString("HoTen"),
                        rs.getBoolean("GioiTinh"), rs.getDate("NgaySinh"),
                        rs.getString("Email"), rs.getString("CCCD"),
                        rs.getString("SoDT"), rs.getString("TenDangNhap"),
                        rs.getString("MatKhau"), rs.getString("DiaChi"),
                        rs.getBoolean("ChucVu"));
            }
            return nv;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<NhanVien> fillNhanVien() {
        List<NhanVien> listNhanVien = new ArrayList();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            sql = "SELECT ID, HoTen, GioiTinh, NgaySinh, Email, CCCD, SoDT, TenDangNhap, MatKhau, DiaChi, ChucVu FROM NhanVien WHERE ChucVu = 0";
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                NhanVien nv = new NhanVien();
                nv.setId(rs.getInt(1));
                nv.setHoTen(rs.getString(2));
                nv.setGioiTinh(rs.getBoolean(3));
                nv.setNgaySinh(rs.getDate(4));
                nv.setEmail(rs.getString(5));
                nv.setCccd(rs.getString(6));
                nv.setSdt(rs.getString(7));
                nv.setTenDN(rs.getString(8));
                nv.setMatKhau(rs.getString(9));
                nv.setDiaChi(rs.getString(10));
                nv.setChucVu(rs.getBoolean(11));
                listNhanVien.add(nv);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            rs.getStatement().getConnection().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listNhanVien;
    }

    public List<NhanVien> fillQuanLy() {
        List<NhanVien> listQuanLy = new ArrayList();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            sql = "SELECT ID, HoTen, GioiTinh, NgaySinh, Email, CCCD, SoDT, TenDangNhap, MatKhau, DiaChi, ChucVu FROM NhanVien WHERE ChucVu = 1";
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                NhanVien nv = new NhanVien();
                nv.setId(rs.getInt(1));
                nv.setHoTen(rs.getString(2));
                nv.setGioiTinh(rs.getBoolean(3));
                nv.setNgaySinh(rs.getDate(4));
                nv.setEmail(rs.getString(5));
                nv.setCccd(rs.getString(6));
                nv.setSdt(rs.getString(7));
                nv.setTenDN(rs.getString(8));
                nv.setMatKhau(rs.getString(9));
                nv.setDiaChi(rs.getString(10));
                nv.setChucVu(rs.getBoolean(11));
                listQuanLy.add(nv);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            rs.getStatement().getConnection().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listQuanLy;
    }

    public int insert(NhanVien nv) {
        String sql = "INSERT INTO NhanVien (HoTen, GioiTinh, NgaySinh, Email, CCCD, SoDT, TenDangNhap, MatKhau, DiaChi, ChucVu, TrangThai) VALUES (?, ?, ?, ?, ?, ?, ?, ? ,? ,?, 1)";
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, nv.getHoTen());
            ps.setObject(2, nv.isGioiTinh());
            java.util.Date date = nv.getNgaySinh();
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            ps.setDate(3, sqlDate);
            ps.setObject(4, nv.getEmail());
            ps.setObject(5, nv.getCccd());
            ps.setObject(6, nv.getSdt());
            ps.setObject(7, nv.getTenDN());
            ps.setObject(8, nv.getMatKhau());
            ps.setObject(9, nv.getDiaChi());
            ps.setObject(10, nv.isChucVu());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int update(NhanVien nv, int id) {
        String sql = "UPDATE NhanVien SET HoTen = ?, GioiTinh = ?, NgaySinh = ?, Email = ?, CCCD = ?, SoDT = ?, TenDangNhap = ?, MatKhau = ?, DiaChi = ?, ChucVu = ? WHERE ID = ?";
        try {
            con = DBconnect.getConnection();
            if (con == null) {
                JOptionPane.showMessageDialog(null, "Kết nối cơ sở dữ liệu không thành công!");
                return 0;
            }
            ps = con.prepareStatement(sql);
            ps.setObject(1, nv.getHoTen());
            ps.setObject(2, nv.isGioiTinh());
            ps.setObject(3, nv.getNgaySinh());
            ps.setObject(4, nv.getEmail());
            ps.setObject(5, nv.getCccd());
            ps.setObject(6, nv.getSdt());
            ps.setObject(7, nv.getTenDN());
            ps.setObject(8, nv.getMatKhau());
            ps.setObject(9, nv.getDiaChi());
            ps.setObject(10, nv.isChucVu());
            ps.setObject(11, id);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi cập nhật thông tin nhân viên: " + e.getMessage());
            return 0;
        }
    }

    public int updateTrangThai(int id, boolean isDeleted) {
        String sql = "UPDATE NhanVien SET TrangThai = ? WHERE ID = ?";
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, isDeleted);
            ps.setObject(2, id);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public List<String> laySoDienThoaiDaTonTai() {
        List<String> soDienThoai = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            sql = "SELECT SoDT FROM NhanVien";
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

    public List<String> layCCCDDaTonTai() {
        List<String> CCCD = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            sql = "SELECT CCCD FROM NhanVien";
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            // Lấy danh sách số điện thoại từ kết quả truy vấn
            while (rs.next()) {
                String cccd = rs.getString("CCCD");
                CCCD.add(cccd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return CCCD;
    }

    public List<String> layTenDangNhapDaTonTai() {
        List<String> TenDangNhap = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            sql = "SELECT TenDangNhap FROM NhanVien";
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            // Lấy danh sách số điện thoại từ kết quả truy vấn
            while (rs.next()) {
                String tdn = rs.getString("TenDangNhap");
                TenDangNhap.add(tdn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return TenDangNhap;
    }

    public List<String> layEmailDaTonTai() {
        List<String> Email = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            sql = "SELECT Email FROM NhanVien";
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            // Lấy danh sách số điện thoại từ kết quả truy vấn
            while (rs.next()) {
                String email = rs.getString("Email");
                Email.add(email);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Email;
    }

    public NhanVien selectTenNhanVienByID(int ID) {
        NhanVien nhanVien = null;
        sql = "SELECT HoTen FROM NhanVien WHERE ID = ?";
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, ID);
            rs = ps.executeQuery();
            while (rs.next()) {
                nhanVien = new NhanVien();
                nhanVien.setHoTen(rs.getString("HoTen"));
            }
            return nhanVien;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<NhanVien> getNhanVienByGenderAndRole(int gender, int role) {
        List<NhanVien> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            sql = "SELECT ID, HoTen, GioiTinh, NgaySinh, Email, CCCD, SoDT, TenDangNhap, MatKhau, DiaChi, ChucVu FROM NhanVien WHERE GioiTinh = ? AND ChucVu = ?";
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, gender);
            ps.setInt(2, role);
            rs = ps.executeQuery();
            while (rs.next()) {
                NhanVien nv = new NhanVien();
                nv.setId(rs.getInt(1));
                nv.setHoTen(rs.getString(2));
                nv.setGioiTinh(rs.getBoolean(3));
                nv.setNgaySinh(rs.getDate(4));
                nv.setEmail(rs.getString(5));
                nv.setCccd(rs.getString(6));
                nv.setSdt(rs.getString(7));
                nv.setTenDN(rs.getString(8));
                nv.setMatKhau(rs.getString(9));
                nv.setDiaChi(rs.getString(10));
                nv.setChucVu(rs.getBoolean(11));
                list.add(nv);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }
}
