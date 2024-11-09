package com.raven.repository;

import com.raven.classmodel.PhieuGiamGia;
import com.raven.connection.DBconnect;
import com.toedter.calendar.JDateChooser;
import java.awt.EventQueue;
import java.sql.Array;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.math.BigDecimal;
import java.util.List;

public class PhieuGiamGiaRepository {

    Connection con = null;
    ResultSet rs = null;
    PreparedStatement ps = null;
    String sql = null;

    public ArrayList<PhieuGiamGia> getAllKM() {
        ArrayList<PhieuGiamGia> dskm = new ArrayList<>();
        String sql = "SELECT Id, TenPGG, PhanTramGiamGia, NgayBatDau, NgayKetThuc, "
                + "GiaTriToiThieu, SoHoaDonApDung, NgayTao, NgaySua, "
                + "MoTa, TrangThai FROM PhieuGiamGia";
        try (Connection con = DBconnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                PhieuGiamGia pgg = new PhieuGiamGia(
                        rs.getInt("Id"),
                        rs.getString("TenPGG"),
                        rs.getFloat("PhanTramGiamGia"),
                        rs.getDate("NgayBatDau"),
                        rs.getDate("NgayKetThuc"),
                        rs.getDouble("GiaTriToiThieu"),
                        rs.getInt("SoHoaDonApDung"),
                        rs.getString("MoTa"),
                        rs.getInt("TrangThai")
                );
                dskm.add(pgg);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Lỗi GetAll Phieu giam gia!");
        }
        return dskm;
    }
    
    public ArrayList<String> getTenPGGDangHoatDong() {
        ArrayList<String> pggHD = new ArrayList<>();
        String sql = "SELECT TenPGG FROM PhieuGiamGia WHERE TrangThai = 1 AND SoHoaDonApDung > 0";
        try (Connection con = DBconnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                pggHD.add(rs.getString("TenPGG"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Lỗi GetAll Phieu giam gia!");
        }
        return pggHD;
    }
    
    public List<String> layTenPGGDaTonTai() {
        List<String> tenPGG = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            sql = "SELECT TenPGG FROM PhieuGiamGia";
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String pgg = rs.getString("TenPGG");
                tenPGG.add(pgg);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tenPGG;
    }
        public void updateSoHoaDonApDung(int id, int newSoHoaDon) {
        String sql = "UPDATE PhieuGiamGia SET SoHoaDonApDung = ? WHERE ID = ?";
        try (Connection con = DBconnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, newSoHoaDon);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Lỗi cập nhật số lượng hóa đơn áp dụng của phiếu giảm giá!");
        }
    }
    
    public ArrayList<PhieuGiamGia> getPGGDangHoatDong() {
        ArrayList<PhieuGiamGia> dskm = new ArrayList<>();
        String sql = "SELECT Id, TenPGG, PhanTramGiamGia, NgayBatDau, NgayKetThuc, "
                + "GiaTriToiThieu, SoHoaDonApDung, NgayTao, NgaySua, "
                + "MoTa, TrangThai FROM PhieuGiamGia WHERE TrangThai = 2";
        try (Connection con = DBconnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                PhieuGiamGia pgg = new PhieuGiamGia(
                        rs.getInt("Id"),
                        rs.getString("TenPGG"),
                        rs.getFloat("PhanTramGiamGia"),
                        rs.getDate("NgayBatDau"),
                        rs.getDate("NgayKetThuc"),
                        rs.getDouble("GiaTriToiThieu"),
                        rs.getInt("SoHoaDonApDung"),
                        rs.getString("MoTa"),
                        rs.getInt("TrangThai")
                );
                dskm.add(pgg);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Lỗi GetAll Phieu giam gia!");
        }
        return dskm;
    }

    public int add(PhieuGiamGia pgg) {
        String sql = "INSERT INTO PhieuGiamGia "
                + " (TenPGG, PhanTramGiamGia, NgayBatDau, NgayKetThuc, GiaTriToiThieu, SoHoaDonApDung, Mota,"
                + " TrangThai) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            Connection con = DBconnect.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, pgg.getTenPhieuGiamGia());
            ps.setFloat(2, pgg.getPhanTramGiamGia());
            java.util.Date dateNbd = pgg.getNgayBatDau();
            java.sql.Date sqlDateNbd = new java.sql.Date(dateNbd.getTime());
            ps.setDate(3, sqlDateNbd);
            java.util.Date dateNkt = pgg.getNgayKetThuc();
            java.sql.Date sqlDateNkt = new java.sql.Date(dateNkt.getTime());
            ps.setDate(4, sqlDateNkt);
            ps.setDouble(5, pgg.getGiaTriToiThieu());
            ps.setInt(6, pgg.getSoHoaDonApDung());
            ps.setString(7, pgg.getMoTa());
            ps.setInt(8, pgg.getTrangThai());
            return ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Lỗi ADD Phiếu giảm giá");
            e.printStackTrace();
            return 0;
        }

    }

    public int update(PhieuGiamGia pgg, int id) {
        String sql = "UPDATE PhieuGiamGia SET tenPGG = ?, phanTramGiamGia = ?, ngayBatDau = ?, NgayKetThuc = ?, GiaTriToiThieu = ?, SoHoaDonApDung = ?, MoTa = ?, TrangThai = ? WHERE ID   = ?";
        try (Connection con = DBconnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, pgg.getTenPhieuGiamGia());
            ps.setFloat(2, pgg.getPhanTramGiamGia());
            ps.setDate(3, new java.sql.Date(pgg.getNgayBatDau().getTime()));
            ps.setDate(4, new java.sql.Date(pgg.getNgayKetThuc().getTime()));
            ps.setDouble(5, pgg.getGiaTriToiThieu());
            ps.setInt(6, pgg.getSoHoaDonApDung());
            ps.setString(7, pgg.getMoTa());
            ps.setInt(8, pgg.getTrangThai());
            ps.setInt(9, id);
            return ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Lỗi update Phiếu giảm giá");
            e.printStackTrace();
            return 0;
        }

    }

    public List<PhieuGiamGia> find(JDateChooser bd, JDateChooser kt) {
        String sql = "SELECT Id, TenPGG, PhanTramGiamGia, NgayBatDau, NgayKetThuc, "
                + "GiaTriToiThieu, SoHoaDonApDung, NgayTao, NgaySua, "
                + "MoTa, TrangThai FROM PhieuGiamGia WHERE NgayBatDau BETWEEN ? AND ?";
        List<PhieuGiamGia> listpgg = new ArrayList<>();
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);

            java.util.Date startDate = bd.getDate();
            java.util.Date endDate = kt.getDate();

            // Các kiểm tra null đã được thực hiện trước đó trong btnTimKiemActionPerformed
            ps.setDate(1, new java.sql.Date(startDate.getTime()));
            ps.setDate(2, new java.sql.Date(endDate.getTime()));

            rs = ps.executeQuery();
            while (rs.next()) {
                PhieuGiamGia pgg = new PhieuGiamGia(
                        rs.getInt("Id"),
                        rs.getString("TenPGG"),
                        rs.getFloat("PhanTramGiamGia"),
                        rs.getDate("NgayBatDau"),
                        rs.getDate("NgayKetThuc"),
                        rs.getDouble("GiaTriToiThieu"),
                        rs.getInt("SoHoaDonApDung"),
                        rs.getString("MoTa"),
                        rs.getInt("TrangThai"));
                listpgg.add(pgg);
            }
            return listpgg;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<String> getVoucherNames() {
        ArrayList<String> voucherHD = new ArrayList<>();
        String sql = "SELECT TenPGG FROM PhieuGiamGia WHERE TrangThai = 1";
        try (
                Connection con = DBconnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                voucherHD.add(rs.getString("TenPGG"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Lỗi truy vấn phiếu giảm giá đang hoạt động!");
        }
        return voucherHD;
    }
    
    public PhieuGiamGia findGiaTri(String tenPgg){
        
        PhieuGiamGia pgg =  new PhieuGiamGia();
        
        try {
            con = DBconnect.getConnection();
            sql = "SELECT PhanTramGiamGia FROM PhieuGiamGia WHERE TenPGG = ? AND TrangThai = 1";
            ps = con.prepareStatement(sql);
            ps.setString(1,tenPgg);
            rs = ps.executeQuery();
            
            while(rs.next()){
                pgg.setPhanTramGiamGia(rs.getInt(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return pgg;
    }
    
    public PhieuGiamGia findId(String ten){
        
        PhieuGiamGia pgg =  new PhieuGiamGia();
        
        try {
            con = DBconnect.getConnection();
            sql = "SELECT ID FROM PhieuGiamGia WHERE TenPGG = ? AND TrangThai = 1";
            ps = con.prepareStatement(sql);
            ps.setString(1,ten);
            rs = ps.executeQuery();
            
            while(rs.next()){
                pgg.setId(rs.getInt(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return pgg;
    }
    
    public void updateTrangThaiPhieuGiamGia(PhieuGiamGia pgg) throws SQLException {
        String sql = "UPDATE PhieuGiamGia SET TrangThai = ? WHERE Id = ?";
        try (Connection con = DBconnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, pgg.getTrangThai());
            ps.setInt(2, pgg.getId());
            ps.executeUpdate();
        }
    }
    
    public List<PhieuGiamGia> findPggDate(java.util.Date startDate, java.util.Date endDate) {
        List<PhieuGiamGia> result = new ArrayList<>();

        String sql = "SELECT * FROM PhieuGiamGia WHERE ngayBatDau >= ? AND ngayKetThuc <= ?";
        try (Connection con = DBconnect.getConnection(); PreparedStatement statement = con.prepareStatement(sql)) {

            statement.setDate(1, new java.sql.Date(startDate.getTime()));
            statement.setDate(2, new java.sql.Date(endDate.getTime()));

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    PhieuGiamGia pgg = new PhieuGiamGia(
                            resultSet.getInt("Id"),
                            resultSet.getString("TenPGG"),
                            resultSet.getFloat("PhanTramGiamGia"),
                            resultSet.getDate("NgayBatDau"),
                            resultSet.getDate("NgayKetThuc"),
                            resultSet.getDouble("GiaTriToiThieu"),
                            resultSet.getInt("SoHoaDonApDung"),
                            resultSet.getString("MoTa"),
                            resultSet.getInt("TrangThai")
                    );
                    result.add(pgg);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Xử lý ngoại lệ nếu cần
        }

        return result;
    }
    
    public PhieuGiamGia findIdThanhToan(String ten) {
        PhieuGiamGia pgg = null;

        try {
            con = DBconnect.getConnection();
            sql = "SELECT ID, SoHoaDonApDung FROM PhieuGiamGia WHERE TenPGG = ? AND TrangThai = 1";
            ps = con.prepareStatement(sql);
            ps.setString(1, ten);
            rs = ps.executeQuery();

            if (rs.next()) {
                pgg = new PhieuGiamGia();
                pgg.setId(rs.getInt("ID"));
                pgg.setSoHoaDonApDung(rs.getInt("SoHoaDonApDung"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
        return pgg;
    }
}
