package com.raven.repository;

import com.raven.classmodel.ChiTietSanPham;
import com.raven.classmodel.DoCung;
import com.raven.classmodel.DoDay;
import com.raven.classmodel.HoaDon;
import com.raven.classmodel.HoaDonChiTiet;
import com.raven.classmodel.KhachHang;
import com.raven.classmodel.KichThuoc;
import com.raven.classmodel.LoaiDem;
import com.raven.classmodel.MauSac;
import com.raven.classmodel.NhanVien;
import com.raven.classmodel.SanPham;
import com.raven.classmodel.ThietKe;
import com.raven.classmodel.ThuongHieu;
import com.raven.classmodel.XuatXu;
import com.raven.connection.DBconnect;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hoai Thu
 */
public class HoaDonRepository {

    String sql = null;
    Connection con = null;
    ResultSet rs = null;
    PreparedStatement ps = null;

    public List<HoaDon> getAllHD() {
        sql = "SELECT HoaDon.ID, SoLuong, Gia, (SoLuong*Gia) AS N'tongTien' FROM HoaDon JOIN HoaDonChiTiet ON HoaDonChiTiet.ID_HoaDon = HoaDon.ID";
        List<HoaDon> listhd = new ArrayList<>();
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                HoaDon hd = new HoaDon(rs.getInt("ID"), rs.getInt("SoLuong"), rs.getBigDecimal("Gia"), rs.getDouble("tongTien"));
                listhd.add(hd);
            }
            return listhd;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public HoaDon getHoaDonById1(int id) {
        HoaDon hd = null;
        try {
            con = DBconnect.getConnection();
            sql = "SELECT HD.ID, HD.NGAYTHANHTOAN, HD.ID_KHACHHANG, HD.ID_NHANVIEN, "
                    + "HD.ID_PHIEUGIAMGIA, HD.ID_HINHTHUCTHANHTOAN, HD.NGAYTAO, HD.NGAYSUA, HD.TRANGTHAI "
                    + "FROM HOADON AS HD "
                    + "WHERE HD.ID = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                hd = new HoaDon();
                
                hd.setId(rs.getInt("ID"));
                hd.setNgayThanhToan(rs.getDate("NGAYTHANHTOAN"));
                hd.setIdKH(rs.getInt("ID_KHACHHANG"));
                hd.setIdNV(rs.getInt("ID_NHANVIEN"));
                hd.setIdPGG(rs.getInt("ID_PHIEUGIAMGIA"));
                hd.setIdHTTT(rs.getInt("ID_HINHTHUCTHANHTOAN"));
                hd.setNgayTao(rs.getDate("NGAYTAO"));
                hd.setNgaySua(rs.getDate("NGAYSUA"));
                hd.setTrangThai(rs.getInt("TRANGTHAI"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Đóng các đối tượng ResultSet, PreparedStatement và Connection
            
        }
        return hd;
    }
    
    public List<HoaDon> getHD(int idKH) {
        sql = "SELECT HoaDon.ID, SoLuong, Gia, (SoLuong*Gia) AS N'tongTien', NgayThanhToan FROM HoaDon JOIN HoaDonChiTiet ON HoaDonChiTiet.ID_HoaDon = HoaDon.ID WHERE ID_KhachHang = ?";
        List<HoaDon> listhd = new ArrayList<>();
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, idKH);
            rs = ps.executeQuery();
            while (rs.next()) {
                HoaDon hd = new HoaDon(rs.getInt("ID"), rs.getInt("SoLuong"), rs.getBigDecimal("Gia"), rs.getDouble("tongTien"));
                listhd.add(hd);
            }
            return listhd;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<HoaDon> getAll() {
//        ArrayList<HoaDon> list = new ArrayList<>();
//        try {
//            Connection con = DBconnect.getConnection();
//            String getall = "SELECT HD.ID, KH.HoTen, KH.SoDT, KH.DiaChi, HD.THANHTIEN, HD.TrangThai, HD.NGAYTAO FROM HOADON AS HD JOIN KHACHHANG AS KH ON HD.ID_KHACHHANG = KH.ID";
//            PreparedStatement ps = con.prepareStatement(getall);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                HoaDon hd = new HoaDon();
//                KhachHang kh = new KhachHang();
//                hd.setId(rs.getInt(1));
//                kh.setHoTen(rs.getString(2));
//                kh.setSoDT(rs.getString(3));
//                kh.setDiaChi(rs.getString(4));
//                hd.setThanhtien(rs.getDouble(5));
//                hd.setTrangThai(rs.getInt(6));
//                hd.setNgayTao(rs.getDate(7));
//                hd.setKh(kh);
//                list.add(hd);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return list;
        ArrayList<HoaDon> list = new ArrayList<>();
        try {
            Connection con = DBconnect.getConnection();
            System.out.println("Database connection successful.");
            String getall = "SELECT HD.ID, KH.HoTen, KH.SoDT, KH.DiaChi, HD.THANHTIEN, HD.TrangThai, HD.NGAYTAO "
                    + "FROM HOADON AS HD "
                    + "LEFT JOIN KHACHHANG AS KH ON HD.ID_KHACHHANG = KH.ID";
            PreparedStatement ps = con.prepareStatement(getall);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDon hd = new HoaDon();
                KhachHang kh = new KhachHang();

                hd.setId(rs.getInt("ID"));
                kh.setHoTen(rs.getString("HoTen") != null ? rs.getString("HoTen") : "Không xác định");
                kh.setSoDT(rs.getString("SoDT") != null ? rs.getString("SoDT") : "Không xác định");
                kh.setDiaChi(rs.getString("DiaChi") != null ? rs.getString("DiaChi") : "Không xác định");
                hd.setThanhtien(rs.getDouble("THANHTIEN"));
                hd.setTrangThai(rs.getInt("TrangThai"));
                hd.setNgayTao(rs.getDate("NGAYTAO"));
                hd.setKh(kh);
                list.add(hd);
//                System.out.println("Added HoaDon: " + hd);
            }
            rs.close();
            ps.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;

    }

    public HoaDon getAllId(int id) {
        HoaDon hd = new HoaDon();
        try {
            Connection con = DBconnect.getConnection();
            String getall = "SELECT HD.ID, KH.HoTen, KH.SoDT, KH.DiaChi, HD.THANHTIEN, HD.TrangThai, HD.NGAYTAO FROM HOADON AS HD JOIN KHACHHANG AS KH ON HD.ID_KHACHHANG = KH.ID WHERE HD.ID = ?";
            PreparedStatement ps = con.prepareStatement(getall);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                KhachHang kh = new KhachHang();
                hd.setId(rs.getInt(1));
                kh.setHoTen(rs.getString(2));
                kh.setSoDT(rs.getString(3));
                kh.setDiaChi(rs.getString(4));
                hd.setThanhtien(rs.getDouble(5));
                hd.setTrangThai(rs.getInt(6));
                hd.setNgayTao(rs.getDate(7));
                hd.setKh(kh);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hd;
    }

    //tìm theo mã hóa đơn, tên khách hàng, giá  
    public ArrayList<HoaDon> getHodon(String key) {
        ArrayList<HoaDon> list = new ArrayList<>();
        try {
            Connection con = DBconnect.getConnection();
            String getall = "SELECT HD.ID, KH.HoTen, KH.SoDT, KH.DiaChi, HD.THANHTIEN, HD.TrangThai, HD.NGAYTAO FROM HOADON AS HD JOIN KHACHHANG AS KH ON HD.ID_KHACHHANG = KH.ID WHERE HD.THANHTIEN = ?";
            PreparedStatement ps = con.prepareStatement(key);
            ps.setInt(1, Integer.parseInt(key));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDon hd = new HoaDon();
                KhachHang kh = new KhachHang();
                hd.setId(rs.getInt(1));
                kh.setHoTen(rs.getString(2));
                kh.setSoDT(rs.getString(3));
                kh.setDiaChi(rs.getString(4));
                hd.setThanhtien(rs.getDouble(5));
                hd.setTrangThai(rs.getInt(6));
                hd.setNgayTao(rs.getDate(7));
                hd.setKh(kh);
                list.add(hd);
            }
        } catch (Exception e) {

            try {
                Connection con = DBconnect.getConnection();
                String get2 = "SELECT HD.ID, KH.HoTen, KH.SoDT, KH.DiaChi, HD.THANHTIEN, HD.TrangThai, HD.NGAYTAO FROM HOADON AS HD JOIN KHACHHANG AS KH ON HD.ID_KHACHHANG = KH.ID WHERE HD.ID = ?";
                PreparedStatement ps2 = con.prepareStatement(get2);
                ps2.setString(1, key);
                ResultSet rs = ps2.executeQuery();
                while (rs.next()) {
                    HoaDon hd = new HoaDon();
                    KhachHang kh = new KhachHang();
                    hd.setId(rs.getInt(1));
                    kh.setHoTen(rs.getString(2));
                    kh.setSoDT(rs.getString(3));
                    kh.setDiaChi(rs.getString(4));
                    hd.setThanhtien(rs.getDouble(5));
                    hd.setTrangThai(rs.getInt(6));
                    hd.setNgayTao(rs.getDate(7));
                    hd.setKh(kh);
                    list.add(hd);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            try {
                Connection con = DBconnect.getConnection();
                String get3 = "SELECT HD.ID, KH.HoTen, KH.SoDT, KH.DiaChi, HD.THANHTIEN, HD.TrangThai, HD.NGAYTAO FROM HOADON AS HD JOIN KHACHHANG AS KH ON HD.ID_KHACHHANG = KH.ID WHERE KH.HOTEN like ?";
                PreparedStatement ps3 = con.prepareStatement(get3);
                ps3.setString(1, "%" + key + "%");
                ResultSet rs = ps3.executeQuery();
                while (rs.next()) {
                    HoaDon hd = new HoaDon();
                    KhachHang kh = new KhachHang();
                    hd.setId(rs.getInt(1));
                    kh.setHoTen(rs.getString(2));
                    kh.setSoDT(rs.getString(3));
                    kh.setDiaChi(rs.getString(4));
                    hd.setThanhtien(rs.getDouble(5));
                    hd.setTrangThai(rs.getInt(6));
                    hd.setNgayTao(rs.getDate(7));
                    hd.setKh(kh);
                    list.add(hd);
                }
            } catch (Exception exp) {
                exp.printStackTrace();
            }

        }
        return list;
    }

    public ArrayList<HoaDon> getCbo(int tt) {
//        ArrayList<HoaDon> list = new ArrayList<>();
//        try {
//            Connection con = DBconnect.getConnection();
//            String get = "SELECT HD.ID, KH.HoTen, KH.SoDT, KH.DiaChi, HD.THANHTIEN, HD.TrangThai, HD.NGAYTAO FROM HOADON AS HD JOIN KHACHHANG AS KH ON HD.ID_KHACHHANG=KH.ID WHERE HD.TRANGTHAI = ?";
//            PreparedStatement ps = con.prepareStatement(get);
//            ps.setInt(1, tt);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                HoaDon hd = new HoaDon();
//                KhachHang kh = new KhachHang();
//                hd.setId(rs.getInt(1));
//                kh.setHoTen(rs.getString(2));
//                kh.setSoDT(rs.getString(3));
//                kh.setDiaChi(rs.getString(4));
//                hd.setThanhtien(rs.getDouble(5));
//                hd.setTrangThai(rs.getInt(6));
//                hd.setNgayTao(rs.getDate(7));
//                hd.setKh(kh);
//                list.add(hd);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//        return list;
        ArrayList<HoaDon> list = new ArrayList<>();
        try {
            Connection con = DBconnect.getConnection();
            String query = "SELECT HD.ID, KH.HoTen, KH.SoDT, KH.DiaChi, HD.THANHTIEN, HD.TrangThai, HD.NGAYTAO "
                    + "FROM HOADON AS HD "
                    + "LEFT JOIN KHACHHANG AS KH ON HD.ID_KHACHHANG = KH.ID "
                    + "WHERE HD.TrangThai = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, tt);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDon hd = new HoaDon();
                KhachHang kh = new KhachHang();

                hd.setId(rs.getInt("ID"));
                kh.setHoTen(rs.getString("HoTen") != null ? rs.getString("HoTen") : "Không xác định");
                kh.setSoDT(rs.getString("SoDT") != null ? rs.getString("SoDT") : "Không xác định");
                kh.setDiaChi(rs.getString("DiaChi") != null ? rs.getString("DiaChi") : "Không xác định");
                hd.setThanhtien(rs.getDouble("THANHTIEN"));
                hd.setTrangThai(rs.getInt("TrangThai"));
                hd.setNgayTao(rs.getDate("NGAYTAO"));
                hd.setKh(kh);
                list.add(hd);
            }
            rs.close();
            ps.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<HoaDon> getRbo(int httt) {
        ArrayList<HoaDon> list = new ArrayList<>();
        try {
            Connection con = DBconnect.getConnection();
            String get = "SELECT HD.ID, KH.HoTen, KH.SoDT, KH.DiaChi, HD.THANHTIEN, HD.TrangThai, HD.NGAYTAO FROM HOADON AS HD\n"
                    + "JOIN KHACHHANG AS KH ON HD.ID_KHACHHANG = KH.ID\n"
                    + "JOIN HinhThucThanhToan AS HTTT ON HD.ID_HinhThucThanhToan = HTTT.ID\n"
                    + "WHERE HTTT.id = ?";
            PreparedStatement ps = con.prepareCall(get);
            ps.setInt(1, httt);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDon hd = new HoaDon();
                KhachHang kh = new KhachHang();
                ChiTietSanPham spct = new ChiTietSanPham();
                SanPham sp = new SanPham();
                ThuongHieu th = new ThuongHieu();
                MauSac ms = new MauSac();
                KichThuoc kt = new KichThuoc();
                HoaDonChiTiet hdct = new HoaDonChiTiet();
                hd.setId(rs.getInt(1));
                kh.setHoTen(rs.getString(2));
                kh.setSoDT(rs.getString(3));
                kh.setDiaChi(rs.getString(4));
                hd.setThanhtien(rs.getDouble(5));
                hd.setTrangThai(rs.getInt(6));
                hd.setNgayTao(rs.getDate(7));
                hd.setKh(kh);
                hd.setHdct(hdct);
                hd.setSpct(spct);
                hd.setSp(sp);
                hd.setMs(ms);
                hd.setKt(kt);
                hd.setTh(th);
                list.add(hd);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public ArrayList<HoaDon> getAlltimkiemHoadon() {
        ArrayList<HoaDon> list = new ArrayList<>();
        try {
            Connection con = DBconnect.getConnection();
            String get = "SELECT HD.ID, KH.HoTen, KH.SoDT, KH.DiaChi, HD.THANHTIEN, HD.TrangThai, SP.TenSP, MS.TenMS, KT.TenKT, TH.TenTH, HD.NGAYTAO FROM HOADON AS HD\n"
                    + "JOIN KHACHHANG AS KH ON HD.ID_KHACHHANG = KH.ID\n"
                    + "JOIN HoaDonChiTiet AS HDCT ON HD.ID = HDCT.ID_HoaDon\n"
                    + "JOIN ChiTietSanPham AS CTSP ON CTSP.ID = HDCT.ID_SanPhamChiTiet\n"
                    + "JOIN SanPham AS SP ON SP.ID = CTSP.ID_SanPham\n"
                    + "JOIN MauSac AS MS ON MS.ID = CTSP.ID_MauSac\n"
                    + "JOIN KichThuoc AS KT ON KT.ID = CTSP.ID_KichThuoc\n"
                    + "JOIN ThuongHieu AS TH ON TH.ID = CTSP.ID_ThuongHieu";
            PreparedStatement ps = con.prepareStatement(get);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDon hd = new HoaDon();
                KhachHang kh = new KhachHang();
                ChiTietSanPham spct = new ChiTietSanPham();
                SanPham sp = new SanPham();
                ThuongHieu th = new ThuongHieu();
                MauSac ms = new MauSac();
                KichThuoc kt = new KichThuoc();
                HoaDonChiTiet hdct = new HoaDonChiTiet();

                hd.setId(rs.getInt(1));
                kh.setHoTen(rs.getString(2));
                kh.setSoDT(rs.getString(3));
                kh.setDiaChi(rs.getString(4));
                hd.setThanhtien(rs.getDouble(5));
                hd.setTrangThai(rs.getInt(6));
                sp.setTenSP(rs.getString(7));
                ms.setTenMS(rs.getString(8));
                kt.setTenKT(rs.getString(9));
                th.setTenTH(rs.getString(10));
                hd.setNgayTao(rs.getDate(11));
                hd.setKh(kh);
                hd.setHdct(hdct);
                hd.setSpct(spct);
                hd.setSp(sp);
                hd.setMs(ms);
                hd.setKt(kt);
                hd.setTh(th);
                list.add(hd);

            }

        } catch (Exception e) {
        }
        return list;
    }

    public ArrayList<HoaDon> getTimhoadon(String mahd) {
        ArrayList<HoaDon> list = new ArrayList<>();
        try {
            Connection con = DBconnect.getConnection();
            String get = "SELECT HD.ID, KH.HoTen, KH.SoDT, KH.DiaChi, HD.THANHTIEN, HD.TrangThai, SP.TenSP, MS.TenMS, KT.TenKT, TH.TenTH, HD.NGAYTAO FROM HOADON AS HD\n"
                    + "JOIN KHACHHANG AS KH ON HD.ID_KHACHHANG = KH.ID\n"
                    + "JOIN HoaDonChiTiet AS HDCT ON HD.ID = HDCT.ID_HoaDon\n"
                    + "JOIN ChiTietSanPham AS CTSP ON CTSP.ID = HDCT.ID_SanPhamChiTiet\n"
                    + "JOIN SanPham AS SP ON SP.ID = CTSP.ID_SanPham\n"
                    + "JOIN MauSac AS MS ON MS.ID = CTSP.ID_MauSac\n"
                    + "JOIN KichThuoc AS KT ON KT.ID = CTSP.ID_KichThuoc\n"
                    + "JOIN ThuongHieu AS TH ON TH.ID = CTSP.ID_ThuongHieu WHERE HD.ID = ?";
            PreparedStatement ps = con.prepareStatement(get);
            ps.setString(1, mahd);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDon hd = new HoaDon();
                KhachHang kh = new KhachHang();
                ChiTietSanPham spct = new ChiTietSanPham();
                SanPham sp = new SanPham();
                ThuongHieu th = new ThuongHieu();
                MauSac ms = new MauSac();
                KichThuoc kt = new KichThuoc();
                hd.setId(rs.getInt(1));
                kh.setHoTen(rs.getString(2));
                kh.setSoDT(rs.getString(3));
                kh.setDiaChi(rs.getString(4));
                hd.setThanhtien(rs.getDouble(5));
                hd.setTrangThai(rs.getInt(6));
                sp.setTenSP(rs.getString(7));
                ms.setTenMS(rs.getString(8));
                kt.setTenKT(rs.getString(9));
                th.setTenTH(rs.getString(10));
                hd.setNgayTao(rs.getDate(11));
                hd.setKh(kh);
                hd.setSp(sp);
                hd.setSpct(spct);
                hd.setMs(ms);
                hd.setTh(th);
                hd.setKt(kt);
                list.add(hd);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }

    public HoaDon getfilltxt(String ma) {
        HoaDon hd = new HoaDon();
        try {
            Connection con = DBconnect.getConnection();
            String getall = "SELECT HD.ID, KH.HoTen, KH.SoDT, KH.DiaChi, HD.THANHTIEN, HD.TrangThai FROM HOADON AS HD JOIN KHACHHANG AS KH ON HD.ID_KHACHHANG = KH.ID WHERE HD.ID=?";
            PreparedStatement ps = con.prepareStatement(getall);
            ps.setString(1, ma);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                KhachHang kh = new KhachHang();
                hd.setId(rs.getInt(1));
                kh.setHoTen(rs.getString(2));
                kh.setSoDT(rs.getString(3));
                kh.setDiaChi(rs.getString(4));
                hd.setThanhtien(rs.getDouble(5));
                hd.setTrangThai(rs.getInt(6));

                hd.setKh(kh);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return hd;
    }

    public ArrayList<HoaDon> fillHoaDonBanHang() {
        sql = "SELECT HoaDon.ID, NhanVien.ID, KhachHang.HoTen, HoaDon.NgayTao, HoaDon.TrangThai FROM HoaDon JOIN NhanVien ON HoaDon.ID_NhanVien = NhanVien.ID JOIN KhachHang ON HoaDon.ID_KhachHang = KhachHang.ID";
        ArrayList<HoaDon> listhdbh = new ArrayList<>();
        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                HoaDon hd = new HoaDon();
                NhanVien nv = new NhanVien();
                KhachHang kh = new KhachHang();
                hd.setId(rs.getInt(1));
                nv.setId(rs.getInt(2));
                kh.setHoTen(rs.getString(3));
                hd.setNgayTao(rs.getDate(4));
                hd.setTrangThai(rs.getInt(5));
                hd.setNv(nv);
                hd.setKh(kh);
                listhdbh.add(hd);
            }
            return listhdbh;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int kiemTraSoLuongHoaDonCho() {
        int numberInvoices = 0;
        sql = "SELECT COUNT(*) AS numberInvoices FROM HoaDon WHERE TrangThai = 3";
        try (Connection conn = DBconnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                numberInvoices = rs.getInt("numberInvoices");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return numberInvoices;
    }

    public int updateHoaDon(double tongTien, double giamGia, double thanhTien, int idHD) {
        String sql = "UPDATE HoaDon SET TONGTIEN = ?, GiamGia = ?, THANHTIEN = ? WHERE IDHOADON = ?";
        try (Connection conn = DBconnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDouble(1, tongTien);
            ps.setDouble(2, giamGia);
            ps.setDouble(3, thanhTien);
            ps.setInt(4, idHD);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int addHoaDonCho(HoaDon hd) {
        try {
            con = DBconnect.getConnection();
            String getAdd = "INSERT INTO HOADON (NgayTao,TrangThai,ThanhTien) VALUES(GETDATE(),?,?)";
            ps = con.prepareStatement(getAdd);
            ps.setInt(1, hd.getTrangThai());
            ps.setDouble(2, hd.getThanhtien());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public ArrayList<HoaDon> getHoaDonByTrangThai() {
        ArrayList<HoaDon> list = new ArrayList<>();
        try {
            con = DBconnect.getConnection();
            String getTT = "SELECT ID, NgayTao, TrangThai from HoaDon WHERE TrangThai = 3";
            ps = con.prepareStatement(getTT);
            rs = ps.executeQuery();
            while (rs.next()) {
                HoaDon hd = new HoaDon();
                hd.setId(rs.getInt(1));
                hd.setNgayTao(rs.getDate(2));
                hd.setTrangThai(rs.getInt(3));
                list.add(hd);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }

//    public List<HoaDonChiTiet> getChiTietSanPhamByMaHoaDon(int idHD) {
//        List<HoaDonChiTiet> hoaDonChiTiets = new ArrayList<>();
//        sql = "SELECT HDCT.ID, SP.TenSP, CTSP.SoLuong, CTSP.GiaBan, (CTSP.SoLuong*CTSP.GiaBan) AS 'ThanhTien'"
//                   + "FROM HoaDonChiTiet HDCT "
//                   + "JOIN ChiTietSanPham CTSP ON HDCT.ID_SanPhamChiTiet = CTSP.ID "
//                   + "JOIN SanPham SP ON CTSP.ID_SanPham = SP.ID "
//                   + "WHERE HDCT.ID_HoaDon = ?";
//        
//        try {
//            con = DBconnect.getConnection();
//            ps = con.prepareStatement(sql);
//            ps.setInt(1, idHD);
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                HoaDonChiTiet hdct = new HoaDonChiTiet();
//                hdct.setId(rs.getInt("ID"));
//                SanPham sanPham = new SanPham();
//                ChiTietSanPham ctsp = new ChiTietSanPham();
//                ctsp.setSoLuong(rs.getInt("SoLuong"));
//                ctsp.setGiaBan(rs.getDouble("GiaBan"));
//                ctsp.setThanhTien(rs.getDouble("ThanhTien"));
//                sanPham.setTenSP(rs.getString("TenSP"));
//                hdct.setSpct(ctsp);
//                hdct.setSp(sanPham);
//                hoaDonChiTiets.add(hdct);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//        return hoaDonChiTiets;
//    }
    public List<HoaDon> getChiTietSanPhamByMaHoaDon2(int idHD) {
        List<HoaDon> hoadonList = new ArrayList<>();
        sql = "SELECT HDCT.ID_SanPhamChiTiet,SP.TenSP,HDCT.SoLuong,HDCT.Gia FROM HOADON AS HD\n"
                + "JOIN HOADONCHITIET AS HDCT ON HD.ID=HDCT.ID_HoaDon\n"
                + "JOIN ChiTietSanPham AS CTSP ON CTSP.ID=HDCT.ID_SanPhamChiTiet\n"
                + "JOIN SanPham AS SP ON SP.ID=CTSP.ID\n"
                + "WHERE HD.ID = ?";

        try {
            con = DBconnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idHD);
            rs = ps.executeQuery();
            while (rs.next()) {
                SanPham sp = new SanPham();
                HoaDon hd = new HoaDon();
                HoaDonChiTiet hdct = new HoaDonChiTiet();
                ChiTietSanPham ctsp = new ChiTietSanPham();

                ctsp.setId(rs.getInt(1));
                sp.setTenSP(rs.getString(2));
                hdct.setSoluong(rs.getInt(3));
                hdct.setGia(rs.getDouble(4));
                hd.setHdct(hdct);
                hd.setSp(sp);
                hd.setHdct(hdct);
                hd.setSpct(ctsp);
                hoadonList.add(hd);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return hoadonList;
    }

    public HoaDon getHoaDonById(int id) {
        HoaDon hd = new HoaDon();
        try {
            con = DBconnect.getConnection();
            sql = "SELECT HD.ID, KH.HoTen, KH.SoDT, KH.DiaChi, HD.THANHTIEN, HD.TrangThai, HD.NGAYTAO "
                    + "FROM HOADON AS HD JOIN KHACHHANG AS KH ON HD.ID_KHACHHANG = KH.ID "
                    + "WHERE HD.ID = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                KhachHang kh = new KhachHang();
                hd.setId(rs.getInt("ID"));
                kh.setHoTen(rs.getString("HoTen"));
                kh.setSoDT(rs.getString("SoDT"));
                kh.setDiaChi(rs.getString("DiaChi"));
                hd.setThanhtien(rs.getDouble("THANHTIEN"));
                hd.setTrangThai(rs.getInt("TrangThai"));
                hd.setNgayTao(rs.getDate("NGAYTAO"));
                hd.setKh(kh);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hd;
    }

    public int updateTrangThaiHoaDon(int idHD, int trangThai) {
        sql = "UPDATE HoaDon SET TrangThai = ? WHERE ID = ?";
        try (Connection conn = DBconnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, trangThai);
            ps.setInt(2, idHD);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public void xoaTatCaSanPhamTrongHoaDon(int idHoaDon) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBconnect.getConnection();
            String delete = "DELETE FROM HoaDonChiTiet WHERE ID_HoaDon = ?";
            ps = con.prepareStatement(delete);
            ps.setInt(1, idHoaDon);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void xoaTungSanPhamTrongHoaDon(int idSanPhamChiTiet) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBconnect.getConnection();
            String delete = "DELETE FROM HoaDonChiTiet WHERE ID_SanPhamChiTiet = ?";
            ps = con.prepareStatement(delete);
            ps.setInt(1, idSanPhamChiTiet);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void updateSoLuongSanPhamTrongHoaDon(int idSanPhamChiTiet, int soLuongMoi) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBconnect.getConnection();
            String sql = "UPDATE hoaDonChiTiet SET soLuong = ? WHERE ID_SanPhamChiTiet = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, soLuongMoi);
            ps.setInt(2, idSanPhamChiTiet);
            int affectedRows = ps.executeUpdate();

            if (affectedRows == 1) {
                System.out.println("Cập nhật thành công số lượng sản phẩm với ID_SanPhamChiTiet: " + idSanPhamChiTiet + " thành " + soLuongMoi);
            } else {
                System.out.println("Không tìm thấy sản phẩm với ID_SanPhamChiTiet: " + idSanPhamChiTiet);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi cập nhật số lượng sản phẩm trong hóa đơn: " + e.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public int updateHoaDon(HoaDon hd, int id) {

        sql = "UPDATE HoaDon SET ThanhTien = ?, NgayThanhToan=?,ID_KhachHang = ?,ID_NhanVien=?,ID_PhieuGiamGia=?,ID_HinhThucThanhToan=?, NgayTao=?, TrangThai = ? WHERE ID = ?";

        try (Connection conn = DBconnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDouble(1, hd.getThanhtien());
            ps.setDate(2, new java.sql.Date(hd.getNgayThanhToan().getTime()));
            ps.setInt(3, hd.getIdKH());
            ps.setInt(4, hd.getIdNV());
            ps.setInt(5, hd.getIdPGG());
            ps.setInt(6, hd.getIdHTTT());
            ps.setDate(7, new java.sql.Date(hd.getNgayTao().getTime()));
            ps.setInt(8, hd.getTrangThai());
            ps.setInt(9, id);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return 1;
    }

    public void huyHoaDon(int hoaDonId) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = DBconnect.getConnection();
            con.setAutoCommit(false); // Bắt đầu giao dịch

            // Lấy thông tin chi tiết sản phẩm từ HoaDonChiTiet
            String selectSQL = "SELECT ID_SanPhamChiTiet, SoLuong FROM HoaDonChiTiet WHERE ID_HoaDon = ?";
            ps = con.prepareStatement(selectSQL);
            ps.setInt(1, hoaDonId);
            rs = ps.executeQuery();

            // Chuẩn bị để cập nhật lại số lượng sản phẩm
            String updateStockSQL = "UPDATE ChiTietSanPham SET SoLuong = SoLuong + ? WHERE ID = ?";
            PreparedStatement psUpdateStock = con.prepareStatement(updateStockSQL);

            while (rs.next()) {
                int idSanPhamChiTiet = rs.getInt("ID_SanPhamChiTiet");
                int soLuong = rs.getInt("SoLuong");

                // Cập nhật lại số lượng sản phẩm trong kho
                psUpdateStock.setInt(1, soLuong);
                psUpdateStock.setInt(2, idSanPhamChiTiet);
                psUpdateStock.executeUpdate();
            }

            // Xóa các chi tiết hóa đơn liên quan đến hóa đơn bị hủy
            String deleteSQL = "DELETE FROM HoaDonChiTiet WHERE ID_HoaDon = ?";
            ps = con.prepareStatement(deleteSQL);
            ps.setInt(1, hoaDonId);
            ps.executeUpdate();

            // Cập nhật trạng thái của hóa đơn thành 2 (bị hủy)
            String updateHoaDonSQL = "UPDATE HoaDon SET TrangThai = 2 WHERE ID = ?";
            ps = con.prepareStatement(updateHoaDonSQL);
            ps.setInt(1, hoaDonId);
            ps.executeUpdate();

            con.commit(); // Hoàn thành giao dịch
        } catch (Exception e) {
            if (con != null) {
                try {
                    con.rollback(); // Rollback nếu có lỗi xảy ra
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
