package com.raven.form;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.raven.classmodel.ChiTietSanPham;
import com.raven.classmodel.GetChucVu;
import com.raven.classmodel.HinhThucThanhToan;
import com.raven.classmodel.HoaDon;
import com.raven.classmodel.HoaDonBanHang;
import com.raven.classmodel.HoaDonChiTiet;
import com.raven.classmodel.KhachHang;
import com.raven.classmodel.KichThuoc;
import com.raven.classmodel.MauSac;
import com.raven.classmodel.NhanVien;
import com.raven.classmodel.PhieuGiamGia;
import com.raven.classmodel.SanPham;
import com.raven.classmodel.SanPhamChiTiet;
import com.raven.classmodel.XuatXu;
import com.raven.repository.ChiTietSanPhamRepository;
import com.raven.repository.HinhThucThanhToanRepository;
import com.raven.repository.HoaDonBanHangRepository;
import com.raven.repository.HoaDonChiTietRepository;
import com.raven.repository.HoaDonRepository;
import com.raven.repository.KhachHangRepository;
import com.raven.repository.KichThuocRepository;
import com.raven.repository.MauSacRepository;
import com.raven.repository.NhanVienRepository;
import com.raven.repository.PhieuGiamGiaRepository;
import com.raven.repository.SanPhamRepository;
import com.raven.repository.XuatXuRepository;
import java.awt.HeadlessException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentListener;
import javax.swing.event.UndoableEditListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Element;
import javax.swing.text.Position;
import javax.swing.text.Segment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.xmlbeans.impl.regex.REUtil;

/**
 *
 * @author Hoai Thu
 */
public class FormBanHang extends javax.swing.JPanel {

    private DefaultTableModel model = new DefaultTableModel();
    private DefaultTableModel modelHoaDonCho = new DefaultTableModel();
    private DefaultTableModel modelGioHang = new DefaultTableModel();
    private DefaultComboBoxModel cboModel = new DefaultComboBoxModel();
    private HoaDonRepository hoaDonRepository = new HoaDonRepository();
    private KichThuocRepository kichThuocRepository = new KichThuocRepository();
    private MauSacRepository mauSacRepository = new MauSacRepository();
    private XuatXuRepository xuatXuRepository = new XuatXuRepository();
    private HinhThucThanhToanRepository hinhThucThanhToanRepository = new HinhThucThanhToanRepository();
    private SanPhamRepository sanPhamRepository = new SanPhamRepository();
    private NhanVienRepository nhanVienRepository = new NhanVienRepository();
    private ChiTietSanPhamRepository chiTietSanPhamRepository = new ChiTietSanPhamRepository();
    private HoaDonChiTietRepository hoaDonChiTietRepository = new HoaDonChiTietRepository();
    private HoaDonBanHangRepository hoaDonBanHangRepository = new HoaDonBanHangRepository();
    private PhieuGiamGiaRepository phieuGiamGiaRepository = new PhieuGiamGiaRepository();
    private KhachHangRepository khachHangRepository = new KhachHangRepository();
    private List<ChiTietSanPham> listctsp = chiTietSanPhamRepository.getAll();
    private List<HoaDon> listhd = hoaDonRepository.getAll();
    private List<HoaDonChiTiet> listhdct = new ArrayList<>();
    private SanPham sanPham = new SanPham();
    private SanPhamChiTiet sanPhamChiTiet = new SanPhamChiTiet();
    private int rowHD = -1;
    private int selectMaHD = -1;
    private int rowSelectedGH;
    int soLuongConLai;
    int a;

    public FormBanHang() {
        initComponents();
        initCombobox();
        fillTableSanPham(sanPhamRepository.fillSPBH());
        fillHoaDonCho(hoaDonRepository.getHoaDonByTrangThai());
        fillVoucherComboBox(cboPhieuGiamGia);

    }

    public void fillVoucherComboBox(JComboBox<String> comboBox) {
        ArrayList<String> pggHD = phieuGiamGiaRepository.getTenPGGDangHoatDong();
        comboBox.removeAllItems();
        for (String name : pggHD) {
            comboBox.addItem(name);
        }
    }

    private void fillTableSanPham(List<SanPham> listspbh) {
        model = (DefaultTableModel) tbChiTietSanPham.getModel();
        model.setRowCount(0);
        listspbh = sanPhamRepository.fillSPBH();
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        int i = 1;
        for (SanPham sp : listspbh) {
            Object[] row = {
                i++,
                "SPCT00" + sp.getCtsp().getId(),
                sp.getTenSP(),
                sp.getMauSac().getTenMS(),
                sp.getKichThuoc().getTenKT(),
                sp.getDoDay().getTenDD() + " cm",
                sp.getLoaiDem().getTenLD(),
                sp.getThietKe().getTenTK(),
                sp.getXuatXu().getTenXX(),
                sp.getThuongHieu().getTenTH(),
                sp.getCtsp().getSoLuong(),
                formatter.format(sp.getCtsp().getGiaBan()) + " VND",};
            model.addRow(row);
        }
    }

    public void fillTableGioHang(int row, int soLuong) {
        String[] Cols = {"STT", "Mã CTSP", "Tên sản phẩm", "Số lượng", "Đơn giá", "Thành tiền"};
        modelGioHang.setColumnIdentifiers(Cols);
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        ChiTietSanPham ctsp = listctsp.get(row);
        String maCTSP = "SPCT00" + ctsp.getId();
        String ten = ctsp.getSanPham().getTenSP();
        double giaBan = ctsp.getGiaBan();
        double thanhTien = soLuong * giaBan;
        boolean found = false;
        for (int i = 0; i < modelGioHang.getRowCount(); i++) {
            String existingMaCTSP = modelGioHang.getValueAt(i, 2).toString();
            if (existingMaCTSP.equals(maCTSP)) {
                modelGioHang.setValueAt(soLuong, i, 4);
                modelGioHang.setValueAt(formatter.format(thanhTien), i, 6);
                found = true;
                break;
            }
        }
        if (!found) {
            Object[] data = {
                modelGioHang.getRowCount() + 1,
                maCTSP,
                ten,
                soLuong,
                formatter.format(giaBan),
                formatter.format(thanhTien)
            };
            modelGioHang.addRow(data);
        }
        tbGioHang.setModel(modelGioHang);
    }

    public void fillTableGioHang2(HoaDon hd) {
        String[] Cols = {"STT", "Mã CTSP", "Tên sản phẩm", "Số lượng", "Đơn giá", "Thành tiền"};
        modelGioHang.setColumnIdentifiers(Cols);
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        String ma = "SPCT00" + hd.getSpct().getId();
        String ten = hd.getSp().getTenSP();
        double giaBan = hd.getHdct().getGia();
        int soLuong = hd.getHdct().getSoluong();
        double thanhtien = soLuong * giaBan;
        int stt = 1;
        Object[] data = {
            stt++,
            ma,
            ten,
            soLuong,
            formatter.format(giaBan) + " VND",
            formatter.format(thanhtien) + " VND"
        };
        modelGioHang.addRow(data);
        tbGioHang.setModel(modelGioHang);
    }

    private BigDecimal tinhTongTien() throws NumberFormatException {
        BigDecimal tongTien2 = BigDecimal.ZERO;
        DecimalFormat currencyFormat = new DecimalFormat("###,###,###");
        for (int i = 0; i < tbGioHang.getRowCount(); i++) {
            // DonGia
            Object dgObj = tbGioHang.getValueAt(i, 4);
            if (dgObj == null) {
                continue;
            }
            BigDecimal donGia;
            try {
                donGia = BigDecimal.valueOf(currencyFormat.parse(dgObj.toString()).doubleValue());
            } catch (ParseException e) {
                continue;
            }
            Object slObj = tbGioHang.getValueAt(i, 3);
            if (slObj == null) {
                continue;
            }
            BigDecimal soLuong;
            try {
                soLuong = new BigDecimal(slObj.toString());
            } catch (NumberFormatException e) {
                continue;
            }
            BigDecimal tongTien1 = donGia.multiply(soLuong);
            tongTien2 = tongTien2.add(tongTien1);
        }
        txtTongTien.setText(currencyFormat.format(tongTien2) + " VND");
        txtThanhTien.setText(currencyFormat.format(tongTien2) + " VND");

        return tongTien2;
    }

    public void updateLocSanPham(JTable table, DefaultTableModel tableModel) {
        String mauSac = cboLocMauSac.getSelectedItem().toString();
        String kichThuoc = cboLocKichThuoc.getSelectedItem().toString();
        String xuatXu = cboLocXuatXu.getSelectedItem().toString();

        List<SanPham> products = sanPhamRepository.locSanPham(mauSac, kichThuoc, xuatXu);
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        tableModel.setRowCount(0); // Xóa dữ liệu cũ trong bảng
        int i = 1;
        for (SanPham sp : products) {
            Object[] row = new Object[]{
                i++,
                "SPCT00" + sp.getCtsp().getId(),
                sp.getTenSP(),
                sp.getMauSac().getTenMS(),
                sp.getKichThuoc().getTenKT(),
                sp.getDoDay().getTenDD() + " cm",
                sp.getLoaiDem().getTenLD(),
                sp.getThietKe().getTenTK(),
                sp.getXuatXu().getTenXX(),
                sp.getThuongHieu().getTenTH(),
                sp.getCtsp().getSoLuong(),
                formatter.format(sp.getCtsp().getGiaBan()) + " VND",};
            tableModel.addRow(row);
        }
    }

    public void search(String str, DefaultTableModel model, JTable table) {
        model = (DefaultTableModel) tbChiTietSanPham.getModel();
        TableRowSorter<DefaultTableModel> trs = new TableRowSorter<>(model);
        table.setRowSorter(trs);
        Pattern pattern = Pattern.compile(str, Pattern.CASE_INSENSITIVE);
        trs.setRowFilter(new RowFilter<DefaultTableModel, Object>() {
            @Override
            public boolean include(RowFilter.Entry<? extends DefaultTableModel, ? extends Object> entry) {
                for (int i = entry.getValueCount() - 1; i >= 0; i--) {
                    Object value = entry.getValue(i);
                    if (value != null) {
                        Matcher matcher = pattern.matcher(value.toString());
                        if (matcher.find()) {
                            return true;
                        }
                    }
                }
                return false;
            }

        }
        );
    }

    public void initCombobox() {
        fillCboKichThuoc();
        fillCboMauSac();
        fillCboXuatXu();
        fillCboHinhThucThanhToan();
    }

    public void fillCboKichThuoc() {
        cboModel = (DefaultComboBoxModel) this.cboLocKichThuoc.getModel();
        cboModel.removeAllElements();
        List<KichThuoc> listkt = kichThuocRepository.getAll();
        cboModel.addElement("Tất cả");
        for (KichThuoc kt : listkt) {
            cboModel.addElement(kt.getTenKT());
        }
    }

    public void fillCboMauSac() {
        cboModel = (DefaultComboBoxModel) this.cboLocMauSac.getModel();
        cboModel.removeAllElements();
        List<MauSac> listms = mauSacRepository.getAll();
        cboModel.addElement("Tất cả");
        for (MauSac ms : listms) {
            cboModel.addElement(ms.getTenMS());
        }
    }

    public void fillCboXuatXu() {
        cboModel = (DefaultComboBoxModel) this.cboLocXuatXu.getModel();
        cboModel.removeAllElements();
        List<XuatXu> listxx = xuatXuRepository.getAll();
        cboModel.addElement("Tất cả");
        for (XuatXu xx : listxx) {
            cboModel.addElement(xx.getTenXX());
        }
    }

    public void fillCboHinhThucThanhToan() {
        cboModel = (DefaultComboBoxModel) this.cboHinhThucThanhToan.getModel();
        cboModel.removeAllElements();
        List<HinhThucThanhToan> listhttt = hinhThucThanhToanRepository.getAll();
        for (HinhThucThanhToan httt : listhttt) {
            cboModel.addElement(httt.getHinhThucThanhToan());
        }
    }

    public void chonKH(String maKH, String tenKH, String soDT) {
        txtMaKhachHang.setText(maKH);
        txtTenKhachHang.setText(tenKH);
        txtSoDTKhachHang.setText(soDT);
    }

    private void openChonKhachHang() {
        ChonKhachHang chonKhachHangDialog = new ChonKhachHang((java.awt.Frame) javax.swing.SwingUtilities.getWindowAncestor(this), true, this);
        chonKhachHangDialog.setVisible(true);
    }

    public void locThuocTinh() {
        String tenMauSac = null;
        String tenKichThuoc = null;
        String tenXuatXu = null;
        if (cboLocMauSac.getSelectedItem() != null && cboLocKichThuoc.getSelectedItem() != null && cboLocXuatXu.getSelectedItem() != null) {
            if (!cboLocMauSac.getSelectedItem().equals("Tất cả")) {
                tenMauSac = cboLocMauSac.getSelectedItem().toString();
            }
            if (!cboLocKichThuoc.getSelectedItem().equals("Tất cả")) {
                tenKichThuoc = cboLocKichThuoc.getSelectedItem().toString();
            }
            if (!cboLocXuatXu.getSelectedItem().equals("Tất cả")) {
                tenXuatXu = cboLocXuatXu.getSelectedItem().toString();
            }
        }
    }

    void setKhachHang() {
        txtMaKhachHang.setText("KH000");
        txtTenKhachHang.setText("Khách lẻ");
        txtSoDTKhachHang.setText("");
    }

    public void fillHoaDonCho(ArrayList<HoaDon> hd) {
        modelHoaDonCho = (DefaultTableModel) tbHoaDonCho.getModel();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        modelHoaDonCho.setRowCount(0);
        int i = 1;
        for (HoaDon h : hd) {
            Object[] data = {
                i++,
                "HD00" + h.getId(),
                sdf.format(h.getNgayTao()),
                h.getTrangThai() == 3 ? "Chờ xử lí" : ""
            };
            modelHoaDonCho.addRow(data);
        }
    }

    public ChiTietSanPham readFormSP() {
        ChiTietSanPham ctsp = new ChiTietSanPham();
        ctsp.setSoLuong(soLuongConLai);
        return ctsp;
    }

    boolean kiemTraThanhToanTienMat() {
        if (txtTienMat.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không được để trống tiền mặt khách đưa !!!");
            return false;
        }
        try {
            if (Double.parseDouble(txtTienMat.getText()) < 0) {
                JOptionPane.showMessageDialog(this, "Số tiền nhập vào phải > 0 !!!");
                return false;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Nhập vào tiền phải là số !!!");
            return false;
        }
        return true;
    }

    boolean kiemTraThanhToanTienCK() {
        if (txtTienChuyenKhoan.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không được để trống tiền khách chuyển khoản !!!");
            return false;
        }
        try {
            if (Double.parseDouble(txtTienChuyenKhoan.getText()) < 0) {
                JOptionPane.showMessageDialog(this, "Số tiền nhập vào phải > 0 !!!");
                return false;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Nhập vào tiền phải là số");
            return false;
        }
        return true;
    }

    private int getSoLuongFromInput() {
        int soLuong = 0;
        try {
            String input = JOptionPane.showInputDialog("Nhập số lượng sản phẩm:");
            if (input == null || input.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Số lượng sản phẩm không được để trống");
                return 0;
            }
            soLuong = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Phải nhập vào 1 số!");
        }
        return soLuong;
    }

    boolean checkThanhToanTienMat() {
        if (txtTienMat.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tiền mặt khách đưa không được để trống");
            return false;
        }
        try {
            if (Double.parseDouble(txtTienMat.getText()) < 0) {
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Tiền phải là số");
            return false;
        }
        return true;
    }

    public void xuatHoaDon(int idHoaDon, String[] dssp) {
        String path = "";
        JFileChooser j = new JFileChooser();
        j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int x = j.showSaveDialog(null);
        if (x == JFileChooser.APPROVE_OPTION) {
            path = j.getSelectedFile().getPath();
        }

        Document doc = new Document();
        try {
            PdfWriter.getInstance(doc, new FileOutputStream(path + "/HoaDon_" + idHoaDon + ".pdf"));
            doc.open();

            // Lấy thông tin hóa đơn từ repository
            HoaDon hoaDon = hoaDonRepository.getHoaDonById1(idHoaDon);

            System.out.println("Thông tin hóa đơn từ repository: " + hoaDon); // In thông tin hóa đơn để kiểm tra

            if (hoaDon == null) {
                JOptionPane.showMessageDialog(null, "Không tìm thấy hóa đơn với ID: " + idHoaDon, "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int idNhanVien = hoaDon.getIdNV();
            int idKhachHang = hoaDon.getIdKH();

            // Kiểm tra xem idNhanVien và idKhachHang có giá trị hợp lệ không
            if (idNhanVien <= 0) {
                JOptionPane.showMessageDialog(null, "Không tìm thấy nhân viên cho hóa đơn có ID: " + idHoaDon, "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (idKhachHang <= 0) {
                JOptionPane.showMessageDialog(null, "Không tìm thấy khách hàng cho hóa đơn có ID: " + idHoaDon, "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Tiếp tục xử lý như bình thường
            // Lấy tên của nhân viên từ ID nhân viên
            String tenNhanVien = nhanVienRepository.getTenNhanVienByIdNhanVien(idNhanVien);
            if (tenNhanVien == null || tenNhanVien.isEmpty()) {
                tenNhanVien = "Unknown";
            }

            // Lấy tên của khách hàng từ ID khách hàng
            String tenKhachHang = khachHangRepository.getTenKhachHangByIdKhachHang(idKhachHang);
            if (tenKhachHang == null || tenKhachHang.isEmpty()) {
                tenKhachHang = "Khách lẻ"; // Giả sử mặc định là "Khách lẻ" nếu không tìm thấy tên khách hàng
            }

            // Tính tổng tiền trước và sau khi áp dụng phiếu giảm giá
            double tongTienTruocGG = 0;
            double tongTienSauGG = hoaDon.getThanhtien(); // Giả sử đã lưu tổng tiền sau khi áp dụng giảm giá

            doc.add(new Paragraph(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  FPOLY-SHOP  <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<"));
            doc.add(new Paragraph("------------------------------------------------------- HOA DON ------------------------------------------------------------"));
            doc.add(new Paragraph("Ma hoa don: " + "HD00" + idHoaDon));
            doc.add(new Paragraph("Ten khach hang: " + tenKhachHang));
            doc.add(new Paragraph("Ten nhan vien: " + tenNhanVien));
            doc.add(new Paragraph("Ngay tao: " + hoaDon.getNgayTao()));
            doc.add(new Paragraph("Ngay thanh toan: " + (hoaDon.getNgayThanhToan() != null ? hoaDon.getNgayThanhToan() : "Chưa thanh toán")));
            doc.add(new Paragraph(" "));

            PdfPTable table = new PdfPTable(4); // 4 columns: Tên SP, Số lượng, Đơn giá, Thành tiền
            addTableHeader(table);

            for (HoaDonChiTiet chiTiet : hoaDonChiTietRepository.getListAllByHoaDonId(idHoaDon)) {
                SanPham sanPham = sanPhamRepository.getSP(chiTiet.getIdCTSP());
                String tenSP = "";
                for (int i = 0; i < dssp.length; i++) {
                    tenSP = tenSP + dssp[i] + "\n";
                }
                double donGia = chiTiet.getGia();
                int soLuong = chiTiet.getSoluong();
                double thanhTien = donGia * soLuong;
                tongTienTruocGG += thanhTien;
                table.addCell(tenSP);
                table.addCell(String.valueOf(soLuong));
                table.addCell(String.valueOf(donGia));
                table.addCell(String.valueOf(thanhTien));
            }

            doc.add(table);

            String tenGiamGia = (String) cboPhieuGiamGia.getSelectedItem();
            //System.out.println("Phiếu giảm giá đã chọn: " + tenGiamGia);
            PhieuGiamGia phanTramGiam = phieuGiamGiaRepository.findGiaTri(tenGiamGia);
            double giatrigiam = phanTramGiam.getPhanTramGiamGia();
            //System.out.println("Phần trăm giảm giá: " + giatrigiam);
            String textTongTien = txtTongTien.getText();
            String soTien = extractNum(textTongTien);
//            System.out.println("Số tiền đã nhập: " + soTien);
            Double tt = Double.parseDouble(soTien);
            System.out.println("Tổng tiền (số): " + tt);
            double tienPhieuGiamGia = tt * (giatrigiam / 100);
            double tongTienSauGiam = tt - tienPhieuGiamGia;
//            System.out.println("Tiền giảm giá: " + tienPhieuGiamGia);
//            System.out.println("Tổng tiền sau khi giảm: " + tongTienSauGiam);

            doc.add(new Paragraph("Tong tien (truoc khi ap dung PGG): " + tongTienTruocGG));
            doc.add(new Paragraph("Tong tien (sau khi ap dung PGG): " + tongTienSauGiam));
            double tienKhachDua = hoaDon.getTongTien();
            doc.add(new Paragraph("Tien khach dua: " + tienKhachDua));
            double tienThua = tienKhachDua - tongTienSauGG;
            doc.add(new Paragraph("Tien thua: " + tienThua));
            doc.close();

            JOptionPane.showMessageDialog(null, "Xuất hóa đơn thành công!");
        } catch (DocumentException | FileNotFoundException ex) {
            Logger.getLogger(FormBanHang.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Lỗi khi xuất hóa đơn: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (HeadlessException ex) {
            Logger.getLogger(FormBanHang.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Lỗi khi xuất hóa đơn: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

    }
    
    private void addTableHeader(PdfPTable table) {
        Stream.of("Ten san pham", "So luong", "Don gia", "Thanh tien").forEach(columnTitle -> {
            PdfPCell header = new PdfPCell();
            header.setBackgroundColor(BaseColor.LIGHT_GRAY);
            header.setBorderWidth(2);
            header.setPhrase(new Phrase(columnTitle));
            table.addCell(header);
        });
    }

    public void resetForm() {
        txtMaHoaDon.setText(null);
        txtMaKhachHang.setText("KH000");
        txtNgayTao.setText(null);
        txtSoDTKhachHang.setText(null);
        txtTenKhachHang.setText("Khách lẻ");
        txtThanhTien.setText(null);
        txtTienChuyenKhoan.setText(null);
        txtTienMat.setText(null);
        txtTienThua.setText(null);
        txtTongTien.setText(null);
    }

    private void showWebcam() {
        JFrame webcamFrame = new JFrame("Webcam");
        webcamFrame.setSize(640, 480);
        webcamFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        webcamFrame.setLocationRelativeTo(null);

        Webcam webcam = Webcam.getDefault();
        WebcamPanel webcamPanel = new WebcamPanel(webcam);
        webcamPanel.setFPSDisplayed(true);
        webcamPanel.setDisplayDebugInfo(true);
        webcamPanel.setImageSizeDisplayed(true);
        webcamPanel.setMirrored(true);

        webcamFrame.add(webcamPanel);
        webcamFrame.setVisible(true);

        new Thread(() -> {
            while (webcamFrame.isVisible()) {
                BufferedImage image = webcam.getImage();
                if (image != null) {
                    try {
                        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(image)));
                        Result result = new MultiFormatReader().decode(bitmap);
                        if (result != null) {
                            String qrCode = result.getText();
                            SwingUtilities.invokeLater(() -> {
                                webcamFrame.dispose();
                                handleQRCode(qrCode);
                            });
                            break;
                        }
                    } catch (NotFoundException e) {
                        // No QR code found
                    }
                }
            }
        }).start();
    }

    private void handleQRCode(String qrCode) {
        String maSPCT = qrCode;
        String soLuongInput = JOptionPane.showInputDialog(this, "Nhập số lượng sản phẩm:");
        if (soLuongInput == null) {
            JOptionPane.showMessageDialog(this, "Hủy thao tác nhập số lượng sản phẩm");
            return;
        }
        if (!validateSoLuong(soLuongInput)) {
            return;
        }
        int soLuong = Integer.parseInt(soLuongInput);
        try {
            ChiTietSanPham selectedProduct = listctsp.stream()
                    .filter(ctsp -> ("SPCT00" + ctsp.getId()).equals(maSPCT))
                    .findFirst()
                    .orElseThrow(() -> new Exception("Sản phẩm không tồn tại"));
            int idHoaDon = -1;
            int rowHD = tbHoaDonCho.getSelectedRow();
            if (rowHD >= 0) {
                String maHoaDon = (String) tbHoaDonCho.getValueAt(rowHD, 1);
                idHoaDon = Integer.parseInt(maHoaDon.replace("HD00", ""));
            } else {
                JOptionPane.showMessageDialog(this, "Hãy chọn hóa đơn trước khi thêm sản phẩm");
                return;
            }
            if (soLuong > selectedProduct.getSoLuong()) {
                JOptionPane.showMessageDialog(this, "Số lượng mua vượt quá số lượng sản phẩm có sẵn!!!");
                return;
            }
            List<HoaDonChiTiet> listhdct = hoaDonChiTietRepository.getListAllByHoaDonId(idHoaDon);
            boolean found = false;
            for (HoaDonChiTiet hdct : listhdct) {
                if (hdct.getIdCTSP() == selectedProduct.getId()) {
                    hoaDonChiTietRepository.updateHDCTSoLuong2(selectedProduct.getId(), soLuong);
                    found = true;
                    break;
                }
            }
            if (!found) {
                hoaDonChiTietRepository.insertHoaDonChiTiet(idHoaDon, selectedProduct.getId(), soLuong);
            }
            sanPhamRepository.updateSoLuong(soLuong, selectedProduct.getId());
            listctsp = chiTietSanPhamRepository.getAll();
            fillTableSanPham(sanPhamRepository.fillSPBH());
            fillHoaDonCho(hoaDonRepository.getHoaDonByTrangThai());
            listhdct = hoaDonChiTietRepository.getListAllByHoaDonId1(idHoaDon);
            fillTableGioHang8(listhdct);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi: " + ex.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtMaKhachHang = new javax.swing.JTextField();
        txtTenKhachHang = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtSoDTKhachHang = new javax.swing.JTextField();
        btnChonKhachHang = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtMaHoaDon = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtNgayTao = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtThanhTien = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtTongTien = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        cboPhieuGiamGia = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        cboHinhThucThanhToan = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        txtTienMat = new javax.swing.JTextField();
        txtTienChuyenKhoan = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtTienThua = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        btnThanhToan = new javax.swing.JButton();
        btnLamMoi = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbHoaDonCho = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();
        txtTimKiemHoaDon = new javax.swing.JTextField();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbGioHang = new javax.swing.JTable();
        jPanel12 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbChiTietSanPham = new javax.swing.JTable();
        jLabel14 = new javax.swing.JLabel();
        txtTimKiemSanPham = new javax.swing.JTextField();
        cboLocMauSac = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        cboLocKichThuoc = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        cboLocXuatXu = new javax.swing.JComboBox<>();
        btnQuetQR = new javax.swing.JButton();
        btnXoaSanPham = new javax.swing.JButton();
        btnXoaTatCa = new javax.swing.JButton();
        btnTaoHoaDon = new javax.swing.JButton();
        btnHuyHoaDon = new javax.swing.JButton();

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tại quầy", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 16))); // NOI18N

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin khách hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Mã KH");

        txtMaKhachHang.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtMaKhachHang.setText("KH000");
        txtMaKhachHang.setEnabled(false);

        txtTenKhachHang.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTenKhachHang.setText("Khách lẻ");
        txtTenKhachHang.setEnabled(false);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Tên KH");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Số ĐT");

        txtSoDTKhachHang.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtSoDTKhachHang.setEnabled(false);

        btnChonKhachHang.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnChonKhachHang.setText("Chọn khách hàng");
        btnChonKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnChonKhachHangMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtMaKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(txtSoDTKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addComponent(btnChonKhachHang)))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtMaKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtTenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtSoDTKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnChonKhachHang)
                .addContainerGap(10, Short.MAX_VALUE))
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Mã HĐ");

        txtMaHoaDon.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtMaHoaDon.setEnabled(false);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Ngày tạo");

        txtNgayTao.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtNgayTao.setEnabled(false);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Thành tiền");

        txtThanhTien.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtThanhTien.setEnabled(false);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Tổng tiền");

        txtTongTien.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTongTien.setText("000.000.000 VND");
        txtTongTien.setEnabled(false);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Phiếu GG");

        cboPhieuGiamGia.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cboPhieuGiamGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboPhieuGiamGiaActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setText("Hình thức");

        cboHinhThucThanhToan.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cboHinhThucThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboHinhThucThanhToanActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setText("Tiền mặt");

        txtTienMat.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTienMat.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTienMatCaretUpdate(evt);
            }
        });

        txtTienChuyenKhoan.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTienChuyenKhoan.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTienChuyenKhoanCaretUpdate(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setText("Tiền CK");

        txtTienThua.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setText("Tiền thừa");

        btnThanhToan.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnThanhToan.setText("Thanh toán");
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        btnLamMoi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnLamMoi.setText("Làm mới");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jLabel7)
                    .addComponent(jLabel6)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(jLabel8))
                .addGap(10, 10, 10)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cboPhieuGiamGia, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTienThua, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTienChuyenKhoan, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTienMat, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cboHinhThucThanhToan, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtThanhTien, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNgayTao)
                    .addComponent(txtTongTien)
                    .addComponent(txtMaHoaDon, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtMaHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtNgayTao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtThanhTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(cboPhieuGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(cboHinhThucThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtTienMat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtTienChuyenKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtTienThua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThanhToan)
                    .addComponent(btnLamMoi))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hóa đơn chờ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 16))); // NOI18N

        tbHoaDonCho.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "STT", "Mã HD", "Ngày tạo", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbHoaDonCho.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbHoaDonChoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbHoaDonCho);

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setText("Tìm kiếm");

        txtTimKiemHoaDon.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(18, 18, 18)
                        .addComponent(txtTimKiemHoaDon)))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtTimKiemHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Giỏ hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 16))); // NOI18N

        tbGioHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã SP", "Tên SP", "Số lượng", "Giá bán", "Thành tiền"
            }
        ));
        tbGioHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbGioHangMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbGioHang);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 725, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 16))); // NOI18N

        tbChiTietSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã CTSP", "Tên SP", "Màu sắc", "Kích thước", "Độ dày", "Loại đệm", "Thiết kế", "Xuất xứ", "Thương hiệu", "Số lượng tồn", "Đơn giá"
            }
        ));
        tbChiTietSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbChiTietSanPhamMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbChiTietSanPham);

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setText("Tìm kiếm");

        txtTimKiemSanPham.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTimKiemSanPham.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemSanPhamKeyReleased(evt);
            }
        });

        cboLocMauSac.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cboLocMauSac.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboLocMauSacItemStateChanged(evt);
            }
        });
        cboLocMauSac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboLocMauSacActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel15.setText("Màu sắc");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel16.setText("Kích thước");

        cboLocKichThuoc.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cboLocKichThuoc.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboLocKichThuocItemStateChanged(evt);
            }
        });
        cboLocKichThuoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboLocKichThuocActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel17.setText("Xuất xứ");

        cboLocXuatXu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cboLocXuatXu.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboLocXuatXuItemStateChanged(evt);
            }
        });
        cboLocXuatXu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboLocXuatXuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addGap(18, 18, 18)
                        .addComponent(txtTimKiemSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboLocMauSac, 0, 1, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cboLocKichThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cboLocXuatXu, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel17)
                        .addComponent(cboLocXuatXu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cboLocKichThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel16))
                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel14)
                        .addComponent(txtTimKiemSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel15)
                        .addComponent(cboLocMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnQuetQR.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnQuetQR.setText("Quét QR");
        btnQuetQR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuetQRActionPerformed(evt);
            }
        });

        btnXoaSanPham.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnXoaSanPham.setText("Xóa sản phẩm");
        btnXoaSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaSanPhamActionPerformed(evt);
            }
        });

        btnXoaTatCa.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnXoaTatCa.setText("Xóa tất cả");
        btnXoaTatCa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaTatCaActionPerformed(evt);
            }
        });

        btnTaoHoaDon.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnTaoHoaDon.setText("Tạo hóa đơn");
        btnTaoHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoHoaDonActionPerformed(evt);
            }
        });

        btnHuyHoaDon.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnHuyHoaDon.setText("Hủy hóa đơn");
        btnHuyHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyHoaDonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnXoaSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnQuetQR, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnXoaTatCa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnTaoHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnHuyHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(btnQuetQR)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnXoaSanPham)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnXoaTatCa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnTaoHoaDon)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnHuyHoaDon)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnChonKhachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnChonKhachHangMouseClicked
        openChonKhachHang();
    }//GEN-LAST:event_btnChonKhachHangMouseClicked

    private void cboLocMauSacItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboLocMauSacItemStateChanged
        locThuocTinh();
    }//GEN-LAST:event_cboLocMauSacItemStateChanged

    private void cboLocKichThuocItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboLocKichThuocItemStateChanged
        locThuocTinh();
    }//GEN-LAST:event_cboLocKichThuocItemStateChanged

    private void cboLocXuatXuItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboLocXuatXuItemStateChanged
        locThuocTinh();
    }//GEN-LAST:event_cboLocXuatXuItemStateChanged
    private void fillTableGioHang8(List<HoaDonChiTiet> listhdct) {
        DefaultTableModel model = (DefaultTableModel) tbGioHang.getModel();
        model.setRowCount(0);
        DecimalFormat formatter = new DecimalFormat("###,###,### VND");
        int i = 1;
        for (HoaDonChiTiet hdct : listhdct) {
            Object[] rowData = new Object[]{
                i++,
                "SPCT00" + hdct.getIdCTSP(),
                hdct.getTenSP(),
                hdct.getSoluong(),
                formatter.format(hdct.getSpct().getGiaBan()),
                formatter.format(hdct.getSpct().getThanhTien())
            };
            model.addRow(rowData);
        }
    }
    private void tbChiTietSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbChiTietSanPhamMouseClicked
        int rowHD = tbHoaDonCho.getSelectedRow();
        int idHoaDon = -1;
        if (rowHD >= 0) {
            String maHoaDon = (String) tbHoaDonCho.getValueAt(rowHD, 1);
            idHoaDon = Integer.parseInt(maHoaDon.replace("HD00", ""));
        } else {
            JOptionPane.showMessageDialog(this, "Hãy chọn hóa đơn trước khi thêm sản phẩm");
            return;
        }
        int selectedRow = tbChiTietSanPham.getSelectedRow();
        if (selectedRow < 0 || selectedRow >= listctsp.size()) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm hợp lệ");
            return;
        }

        // Lấy số lượng từ ô input
        String soLuongInput = JOptionPane.showInputDialog(this, "Nhập số lượng sản phẩm:");

        // Kiểm tra nếu người dùng nhấn "Cancel"
        if (soLuongInput == null) {
            JOptionPane.showMessageDialog(this, "Hủy thao tác nhập số lượng sản phẩm");
            return;
        }

        // Gọi hàm validate số lượng
        if (!validateSoLuong(soLuongInput)) {
            return;
        }

        int soLuong = Integer.parseInt(soLuongInput);

        try {
            ChiTietSanPham ctsp = listctsp.get(selectedRow);
            int idCTSP = ctsp.getId();
            int soLuongThuc = ctsp.getSoLuong();
            if (soLuong > soLuongThuc) {
                JOptionPane.showMessageDialog(this, "Số lượng mua vượt quá số lượng sản phẩm có sẵn!!!");
                return;
            }
            List<HoaDonChiTiet> listhdct = hoaDonChiTietRepository.getListAllByHoaDonId(idHoaDon);
            boolean found = false;
            for (HoaDonChiTiet hdct : listhdct) {
                System.out.println("hdct.getIdCTSP(): " + hdct.getIdCTSP() + ", idCTSP: " + idCTSP);

                if (hdct.getIdCTSP() == idCTSP) {
                    hoaDonChiTietRepository.updateHDCTSoLuong2(idCTSP, soLuong);
                    found = true;
                    break;
                }
            }
            if (!found) {
                hoaDonChiTietRepository.insertHoaDonChiTiet(idHoaDon, idCTSP, soLuong);
            }
            sanPhamRepository.updateSoLuong(soLuong, idCTSP);
            listctsp = chiTietSanPhamRepository.getAll();
            fillTableSanPham(sanPhamRepository.fillSPBH());
            fillHoaDonCho(hoaDonRepository.getHoaDonByTrangThai());
            listhdct = hoaDonChiTietRepository.getListAllByHoaDonId1(idHoaDon);
            fillTableGioHang8(listhdct);
        } catch (IndexOutOfBoundsException ex) {
            JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi chỉ số: " + ex.getMessage());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi: " + ex.getMessage());
        }

    }//GEN-LAST:event_tbChiTietSanPhamMouseClicked
    private boolean validateSoLuong(String soLuongInput) {
        if (soLuongInput == null || soLuongInput.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Số lượng không được để trống.");
            return false;
        }
        if (!soLuongInput.matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "Số lượng phải là số nguyên và không chứa ký tự đặc biệt.");
            return false;
        }
        int soLuong = Integer.parseInt(soLuongInput);
        if (soLuong <= 0) {
            JOptionPane.showMessageDialog(this, "Số lượng mua phải lớn hơn 0.");
            return false;
        }
        return true;
    }
    private void btnTaoHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoHoaDonActionPerformed
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn tạo hóa đơn chờ?", "Xác nhận tạo hóa đơn chờ", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            LocalDate ngayTao = LocalDate.now();
            Date ngayTaoDate = java.sql.Date.valueOf(ngayTao);
            double dongia = 0;
            int trangThai = 3;
            HoaDon hd = new HoaDon();
            hd.setNgayTao(ngayTaoDate);
            hd.setTrangThai(trangThai);
            hd.setThanhtien(dongia);
            if (hoaDonRepository.kiemTraSoLuongHoaDonCho() < 10) {
                int themThanhCong = hoaDonRepository.addHoaDonCho(hd);
                if (themThanhCong == 0) {
                    JOptionPane.showMessageDialog(this, "Thêm thất bại !");
                } else {
                    JOptionPane.showMessageDialog(this, "Tạo thành công hóa đơn chờ !!!");
                    fillHoaDonCho(hoaDonRepository.getHoaDonByTrangThai());
                }
            } else {
                JOptionPane.showMessageDialog(this, "Đã vượt quá số lượng hóa đơn chờ (tối đa: 10)!");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Đã hủy tạo hóa đơn chờ.");
        }
    }//GEN-LAST:event_btnTaoHoaDonActionPerformed

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        LocalDate ngayTt = LocalDate.now();
        Date ngaythanhtoan = java.sql.Date.valueOf(ngayTt);
        String textTongTien = txtTongTien.getText();
        String soTien = extractNumber(textTongTien);
        Double tt = Double.parseDouble(soTien);
        String textThanhTien = txtThanhTien.getText();
        String soThanhTien = extractNumber(textThanhTien);
        Double thanhTienDouble = Double.parseDouble(soThanhTien);
        System.out.println("Thành tiền (số): " + thanhTienDouble);
        String maKh = txtMaKhachHang.getText().substring(3);
        int maKhachHang = Integer.parseInt(maKh);
        String mahd = txtMaHoaDon.getText().substring(3);
        int maHoaDon = Integer.parseInt(mahd);
        String ngayt = txtNgayTao.getText();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.parse(ngayt, formatter);
        java.sql.Date ngayTao = java.sql.Date.valueOf(localDate);
        System.out.println("Ngày tạo: " + ngayTao);

        // Lấy thông tin phiếu giảm giá được chọn từ combobox
        String pggSelected = cboPhieuGiamGia.getSelectedItem().toString();
        PhieuGiamGia phieugiamgia = phieuGiamGiaRepository.findIdThanhToan(pggSelected);
        int idpgg = phieugiamgia.getId();
        System.out.println(idpgg + "pgg");

        // Lấy số lượng phiếu giảm giá đã áp dụng từ cơ sở dữ liệu
        int currentSoHoaDon = phieugiamgia.getSoHoaDonApDung();
        System.out.println(currentSoHoaDon);

        // Lấy thông tin hình thức thanh toán từ combobox
        String htttSelected = cboHinhThucThanhToan.getSelectedItem().toString();
        HinhThucThanhToan httt = hinhThucThanhToanRepository.findId(htttSelected);
        int idhttt = httt.getId();
        System.out.println(idhttt);

        double tm = 0;
        double tck = 0;

        // Kiểm tra và lấy thông tin số tiền mặt và chuyển khoản dựa trên hình thức thanh toán
        if (htttSelected.equals("Tiền mặt")) {
            String textTienmat = txtTienMat.getText();
            String tienmat = extractNumber(textTienmat);
            if (!validateSoTien(tienmat, "Tiền mặt")) {
                return;
            }
            tm = Double.parseDouble(tienmat);
        } else if (htttSelected.equals("Chuyển khoản")) {
            String textTienChuyenKhoan = txtTienChuyenKhoan.getText();
            String tienchuyenkhoan = extractNumber(textTienChuyenKhoan);
            if (!validateSoTien(tienchuyenkhoan, "Tiền chuyển khoản")) {
                return;
            }
            tck = Double.parseDouble(tienchuyenkhoan);
        }
        if (tm + tck < thanhTienDouble) {
            JOptionPane.showMessageDialog(this, "Số tiền khách đưa không đủ");
            return;
        }

        // Lấy thông tin chức vụ của nhân viên
        int chucVu = GetChucVu.getId();
        System.out.println(chucVu + "CV");

        // Cập nhật thông tin hóa đơn
        HoaDon hd = new HoaDon();
        hd.setThanhtien(thanhTienDouble);
        hd.setNgayThanhToan(ngaythanhtoan);
        hd.setIdKH(maKhachHang);
        hd.setIdNV(chucVu);
        hd.setIdPGG(idpgg);
        hd.setIdHTTT(idhttt);
        hd.setNgayTao(ngayTao);
        hd.setTrangThai(0); // Trạng thái mặc định là 0 khi thanh toán thành công

        // Thực hiện cập nhật hóa đơn và trừ số lượng phiếu giảm giá
        int up = hoaDonRepository.updateHoaDon(hd, maHoaDon);
        if (up > 0) {
            JOptionPane.showMessageDialog(this, "Thanh toán thành công!");

            // Trừ số lượng PGG đã sử dụng trong cơ sở dữ liệu
            int newSoHoaDon = currentSoHoaDon - 1;
            phieuGiamGiaRepository.updateSoHoaDonApDung(idpgg, newSoHoaDon);

            // Cập nhật giao diện và dữ liệu sau khi thanh toán thành công
            fillHoaDonCho(hoaDonRepository.getHoaDonByTrangThai());
            modelGioHang.setRowCount(0); // Xóa giỏ hàng sau khi thanh toán
            resetForm(); // Reset các trường dữ liệu trên form
//            int soSP = listhdct.size();
//            String dssp[] = new String[soSP];
//            for (int i = 0; i < soSP; i++) {
//                HoaDonChiTiet hdct = listhdct.get(i);
//                dssp[i] = hdct.getTenSP();
//            }
            //xuatHoaDon(maHoaDon, dssp); // Hiển thị hóa đơn sau khi thanh toán
        } else {
            JOptionPane.showMessageDialog(this, "Thanh toán thất bại!");
        }
    }//GEN-LAST:event_btnThanhToanActionPerformed

//    public void xuatHoaDon(int idHoaDon) {
//        String path = "";
//        JFileChooser j = new JFileChooser();
//        j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
//
//        int x = j.showSaveDialog(null);
//        if (x == JFileChooser.APPROVE_OPTION) {
//            path = j.getSelectedFile().getPath();
//        }
//
//        if (path.isEmpty()) {
//            JOptionPane.showMessageDialog(null, "No directory selected!", "Error", JOptionPane.ERROR_MESSAGE);
//            return;
//        }
//
//        // Use PdfWriter to write the Document to a file
//        Document doc = new Document();
//
//        try {
//            PdfWriter.getInstance(doc, new FileOutputStream(path + "/HoaDon_" + idHoaDon + ".pdf"));
//            doc.open();
//
//            // Get invoice information from repository
//            HoaDon hoaDon = hoaDonRepository.getHoaDonById(idHoaDon);
//            if (hoaDon == null) {
//                JOptionPane.showMessageDialog(null, "Không tìm thấy hóa đơn với ID: " + idHoaDon, "Error", JOptionPane.ERROR_MESSAGE);
//                return;
//            }
//
//            // Get invoice details from repository
//            List<HoaDonChiTiet> hoaDonChiTiets = hoaDonChiTietRepository.getListAllByHoaDonId(idHoaDon);
//
//            // Get employee ID and customer ID from invoice
//            int idNhanVien = hoaDon.getIdNV();
//            int idKhachHang = hoaDon.getIdKH();
//
//            // Get employee name from employee ID
//            String tenNhanVien = nhanVienRepository.getTenNhanVien(idNhanVien);
//            if (tenNhanVien == null) {
//                tenNhanVien = "Unknown";
//            }
//
//            // Get customer name from customer ID
//            String tenKhachHang = khachHangRepository.getTenKhachHang(idKhachHang);
//            if (tenKhachHang == null) {
//                tenKhachHang = "Unknown";
//            }
//
//            // Calculate total before and after applying discount voucher
//            double tongTienTruocGG = 0;
//            double tongTienSauGG = hoaDon.getThanhtien();  // Assume total after applying discount
//
//            doc.add(new Paragraph(">>>>>>>>>>>>FPOLY-SHOP<<<<<<<<<<<<<"));
//            doc.add(new Paragraph("-------------------------------------------------------HOA DON------------------------------------------------------------"));
//            doc.add(new Paragraph("Ma Hoa Don: " + "HD00" + idHoaDon));
//            doc.add(new Paragraph("Tên khách hàng: " + tenKhachHang));
//            doc.add(new Paragraph("Tên nhân viên: " + tenNhanVien));
//            doc.add(new Paragraph("Ngay Tao: " + hoaDon.getNgayTao()));
//            doc.add(new Paragraph("Ngày thanh toán: " + hoaDon.getNgayThanhToan()));
//            doc.add(new Paragraph(" "));
//
//            PdfPTable table = new PdfPTable(4); // 4 columns: Tên SP, Số lượng, Đơn giá, Thành tiền
//            addTableHeader(table);
//
//            for (HoaDonChiTiet chiTiet : hoaDonChiTiets) {
//                SanPham sanPham = sanPhamRepository.getSP(chiTiet.getIdCTSP());
//                String tenSanPham = sanPham != null ? sanPham.getTenSP() : "Unknown";
//                double donGia = chiTiet.getGia();
//                int soLuong = chiTiet.getSoluong();
//                double thanhTien = donGia * soLuong;
//
//                tongTienTruocGG += thanhTien;
//
//                table.addCell(tenSanPham);
//                table.addCell(String.valueOf(soLuong));
//                table.addCell(String.valueOf(donGia));
//                table.addCell(String.valueOf(thanhTien));
//            }
//
//            doc.add(table);
//
//            doc.add(new Paragraph("Tong tien (truoc khi áp dung PGG): " + tongTienTruocGG));
//            doc.add(new Paragraph("Tong tien (sau khi áp dung PGG): " + tongTienSauGG));
//
//            double tienKhachDua = hoaDon.getTongTien();
//            doc.add(new Paragraph("Tien khach dua: " + tienKhachDua));
//            double tienThua = tienKhachDua - tongTienSauGG;
//            doc.add(new Paragraph("Tien thua: " + tienThua));
//
//            doc.close();
//
//            JOptionPane.showMessageDialog(null, "Xuất hóa đơn thành công!");
//        } catch (DocumentException | FileNotFoundException ex) {
//            Logger.getLogger(InvoiceGenerator.class.getName()).log(Level.SEVERE, null, ex);
//            JOptionPane.showMessageDialog(null, "Lỗi khi xuất hóa đơn: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//        } catch (HeadlessException ex) {
//            Logger.getLogger(InvoiceGenerator.class.getName()).log(Level.SEVERE, null, ex);
//            JOptionPane.showMessageDialog(null, "Lỗi khi ghi file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//        } catch (Exception ex) {
//            Logger.getLogger(InvoiceGenerator.class.getName()).log(Level.SEVERE, null, ex);
//            JOptionPane.showMessageDialog(null, "Lỗi không xác định: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//        }
//    }
//
//    private void addTableHeader(PdfPTable table) {
//        table.addCell("Ten San Pham");
//        table.addCell("So Luong");
//        table.addCell("Don Gia");
//        table.addCell("Thanh Tien");
//    }
    private boolean validateSoTien(String soTienInput, String fieldName) {
        if (soTienInput == null || soTienInput.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, fieldName + " không được để trống.");
            return false;
        }
        if (!soTienInput.matches("\\d+")) {
            JOptionPane.showMessageDialog(this, fieldName + " phải là số và không chứa ký tự đặc biệt.");
            return false;
        }
//        double soTien = Double.parseDouble(soTienInput);
//        if (soTien <= 0) {
//            JOptionPane.showMessageDialog(this, fieldName + " phải lớn hơn 0.");
//            return false;
//        }
        return true;
    }
    private void cboPhieuGiamGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboPhieuGiamGiaActionPerformed
        String tenGiamGia = (String) cboPhieuGiamGia.getSelectedItem();
        System.out.println("Phiếu giảm giá đã chọn: " + tenGiamGia);
        PhieuGiamGia phanTramGiam = phieuGiamGiaRepository.findGiaTri(tenGiamGia);
        double giatrigiam = phanTramGiam.getPhanTramGiamGia();
        System.out.println("Phần trăm giảm giá: " + giatrigiam);
        String textTongTien = txtTongTien.getText();
        String soTien = extractNum(textTongTien);
        System.out.println("Số tiền đã nhập: " + soTien);
        Double tt = Double.parseDouble(soTien);
        System.out.println("Tổng tiền (số): " + tt);
        double tienPhieuGiamGia = tt * (giatrigiam / 100);
        double tongTienSauGiam = tt - tienPhieuGiamGia;
        System.out.println("Tiền giảm giá: " + tienPhieuGiamGia);
        System.out.println("Tổng tiền sau khi giảm: " + tongTienSauGiam);
        DecimalFormat formatter = new DecimalFormat("###,###,### VND");
        txtThanhTien.setText(formatter.format(tongTienSauGiam));
    }//GEN-LAST:event_cboPhieuGiamGiaActionPerformed

    private String extractNum(String text) {
        Pattern pattern = Pattern.compile("\\d");
        Matcher matcher = pattern.matcher(text);
        StringBuilder number = new StringBuilder();
        while (matcher.find()) {
            number.append(matcher.group());
        }
        return number.toString();
    }

    private void btnXoaSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaSanPhamActionPerformed
        int rowCount = tbGioHang.getRowCount();
        if (rowCount == 0) {
            JOptionPane.showMessageDialog(this, "Không có sản phẩm nào trong giỏ hàng.");
        } else {
            int selectedRow = tbGioHang.getSelectedRow();
            if (selectedRow >= 0) {
                int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa sản phẩm này trong giỏ hàng?", "Xác nhận", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    String maChiTietSP = (String) tbGioHang.getValueAt(selectedRow, 1);
                    int soLuongTrongGio = (int) tbGioHang.getValueAt(selectedRow, 3);
                    int idChiTietSP = Integer.parseInt(maChiTietSP.substring(4));
                    boolean found = false;
                    for (int j = 0; j < tbChiTietSanPham.getRowCount(); j++) {
                        String maChiTietSPTrongKho = (String) tbChiTietSanPham.getValueAt(j, 1);
                        int idChiTietSPTrongKho = Integer.parseInt(maChiTietSPTrongKho.substring(4));
                        if (idChiTietSPTrongKho == idChiTietSP) {
                            int soLuongHienTai = (int) tbChiTietSanPham.getValueAt(j, 10);
                            int newStockQuantity = soLuongHienTai + soLuongTrongGio;
                            tbChiTietSanPham.setValueAt(newStockQuantity, j, 10);
                            chiTietSanPhamRepository.updateStockQuantity(idChiTietSP, newStockQuantity);
                            found = true;
                            break;
                        }
                    }
                    {
                        System.out.println("Không tìm thấy sản phẩm với mã chi tiết: " + maChiTietSP);
                    }
                    hoaDonRepository.xoaTungSanPhamTrongHoaDon(idChiTietSP);
                    ((DefaultTableModel) tbGioHang.getModel()).removeRow(selectedRow);
                    tbChiTietSanPham.setModel(tbChiTietSanPham.getModel());
                }
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn một sản phẩm để xóa.");
            }
        }
    }//GEN-LAST:event_btnXoaSanPhamActionPerformed

    private void btnXoaTatCaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaTatCaActionPerformed
        int rowCount = tbGioHang.getRowCount();
        if (rowCount == 0) {
            JOptionPane.showMessageDialog(this, "Không có sản phẩm nào trong giỏ hàng.");
        } else {
            int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa tất cả sản phẩm trong giỏ hàng?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                int selectedRow = tbHoaDonCho.getSelectedRow();
                if (selectedRow >= 0) {
                    String maHoaDonString = (String) tbHoaDonCho.getValueAt(selectedRow, 1);
                    int idHoaDon = Integer.parseInt(maHoaDonString.substring(3));
                    for (int i = 0; i < rowCount; i++) {
                        String maChiTietSP = (String) tbGioHang.getValueAt(i, 1);
                        int soLuongTrongGio = (int) tbGioHang.getValueAt(i, 3);
                        int idChiTietSP = Integer.parseInt(maChiTietSP.substring(4));
                        boolean found = false;
                        for (int j = 0; j < tbChiTietSanPham.getRowCount(); j++) {
                            String maChiTietSPTrongKho = (String) tbChiTietSanPham.getValueAt(j, 1);
                            int idChiTietSPTrongKho = Integer.parseInt(maChiTietSPTrongKho.substring(4));
                            if (idChiTietSPTrongKho == idChiTietSP) {
                                int soLuongHienTai = (int) tbChiTietSanPham.getValueAt(j, 10);
                                int newStockQuantity = soLuongHienTai + soLuongTrongGio;
                                tbChiTietSanPham.setValueAt(newStockQuantity, j, 10);
                                chiTietSanPhamRepository.updateStockQuantity(idChiTietSP, newStockQuantity);
                                found = true;
                                break;
                            }
                        }
                        if (!found) {
                            System.out.println("Không tìm thấy sản phẩm với mã chi tiết: " + maChiTietSP);
                        }
                    }
                    hoaDonRepository.xoaTatCaSanPhamTrongHoaDon(idHoaDon);
                    modelGioHang.setRowCount(0);
                    tbGioHang.setModel(modelGioHang);
                    tbChiTietSanPham.setModel(tbChiTietSanPham.getModel());
                    JOptionPane.showMessageDialog(this, "Tất cả sản phẩm đã được xóa.");
                }
            }
        }
    }//GEN-LAST:event_btnXoaTatCaActionPerformed

    private void tbHoaDonChoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbHoaDonChoMouseClicked
        int selectedRow = tbHoaDonCho.getSelectedRow();
        if (selectedRow >= 0) {
            String maHoaDonString = (String) tbHoaDonCho.getValueAt(selectedRow, 1);
            int idHoaDon = Integer.parseInt(maHoaDonString.substring(3));
            List<HoaDon> HoaDonList = hoaDonRepository.getChiTietSanPhamByMaHoaDon2(idHoaDon);
            modelGioHang.setRowCount(0);
            for (HoaDon hd : HoaDonList) {
                fillTableGioHang2(hd);
            }
        }
        txtMaHoaDon.setText(tbHoaDonCho.getValueAt(tbHoaDonCho.getSelectedRow(), 1).toString());
        txtNgayTao.setText(tbHoaDonCho.getValueAt(tbHoaDonCho.getSelectedRow(), 2).toString());
        txtMaKhachHang.setText("KH00");
        txtTenKhachHang.setText("Khách lẻ");
        txtSoDTKhachHang.setText("");
        tinhTongTien();
    }//GEN-LAST:event_tbHoaDonChoMouseClicked

    private void tbGioHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbGioHangMouseClicked
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        int selectedRow = tbGioHang.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một sản phẩm trong giỏ hàng.");
            return;
        }
        String maCTSP = modelGioHang.getValueAt(selectedRow, 1).toString().trim(); // Cột 2: Mã CTSP
        ChiTietSanPham ctsp = null;
        for (ChiTietSanPham item : listctsp) {
            if (("SPCT00" + item.getId()).equals(maCTSP)) {
                ctsp = item;
                break;
            }
        }
        if (ctsp == null) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy sản phẩm chi tiết.");
            return;
        }
        int soLuongHienTai = ctsp.getSoLuong();
        String soLuongTrongGioHangStr = modelGioHang.getValueAt(selectedRow, 3).toString().replace(",", ""); // Cột 4: Số lượng
        int soLuongTrongGioHang = Integer.parseInt(soLuongTrongGioHangStr);
        String input = JOptionPane.showInputDialog("Nhập số lượng mới:", soLuongTrongGioHang);

        // Kiểm tra nếu người dùng nhấn "Cancel"
        if (input == null) {
            JOptionPane.showMessageDialog(this, "Hủy thao tác sửa số lượng");
            return;
        }

        // Gọi hàm validate số lượng
        if (!validateSoLuong(input)) {
            return;
        }
        int newSoLuong = Integer.parseInt(input);
        if (newSoLuong <= 0 || newSoLuong > (soLuongHienTai + soLuongTrongGioHang)) {
            JOptionPane.showMessageDialog(this, "Số lượng phải là một số nguyên dương và không vượt quá số lượng hiện tại trong kho.");
            return;
        }
        try {
            chiTietSanPhamRepository.updateStockQuantity(ctsp.getId(), soLuongHienTai + soLuongTrongGioHang - newSoLuong);
            hoaDonRepository.updateSoLuongSanPhamTrongHoaDon(ctsp.getId(), newSoLuong);
            ctsp.setSoLuong(soLuongHienTai + soLuongTrongGioHang - newSoLuong);
            modelGioHang.setValueAt(newSoLuong, selectedRow, 3);
            double donGia = ctsp.getGiaBan();
            double thanhTien = donGia * newSoLuong;
            modelGioHang.setValueAt(formatter.format(thanhTien), selectedRow, 5);
            for (int i = 0; i < model.getRowCount(); i++) {
                String maSP = model.getValueAt(i, 1).toString();
                if (maCTSP.equals(maSP)) {
                    int soLuongConLai = soLuongHienTai - (newSoLuong - soLuongTrongGioHang);
                    model.setValueAt(soLuongConLai, i, 10);
                    break;
                }
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Số lượng phải là một số nguyên.");
        }
    }//GEN-LAST:event_tbGioHangMouseClicked

    private void cboLocMauSacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboLocMauSacActionPerformed
        cboLocMauSac.addActionListener(e -> updateLocSanPham(tbChiTietSanPham, (DefaultTableModel) tbChiTietSanPham.getModel()));
    }//GEN-LAST:event_cboLocMauSacActionPerformed

    private void cboLocKichThuocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboLocKichThuocActionPerformed
        cboLocKichThuoc.addActionListener(e -> updateLocSanPham(tbChiTietSanPham, (DefaultTableModel) tbChiTietSanPham.getModel()));
    }//GEN-LAST:event_cboLocKichThuocActionPerformed

    private void cboLocXuatXuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboLocXuatXuActionPerformed
        cboLocXuatXu.addActionListener(e -> updateLocSanPham(tbChiTietSanPham, (DefaultTableModel) tbChiTietSanPham.getModel()));
    }//GEN-LAST:event_cboLocXuatXuActionPerformed

    private void cboHinhThucThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboHinhThucThanhToanActionPerformed
        String selectedMethod = (String) cboHinhThucThanhToan.getSelectedItem();
        if ("Tiền mặt".equals(selectedMethod)) {
            txtTienMat.setEnabled(true);
            txtTienChuyenKhoan.setEnabled(false);
            txtTienMat.setText("");
            txtTienChuyenKhoan.setText("0 VND");
            txtTienThua.setText("");
        } else if ("Chuyển khoản".equals(selectedMethod)) {
            txtTienMat.setEnabled(false);
            txtTienChuyenKhoan.setEnabled(true);
            txtTienChuyenKhoan.setText("");
            txtTienMat.setText("0 VND");
            txtTienThua.setText("");
        }
    }//GEN-LAST:event_cboHinhThucThanhToanActionPerformed

    private void txtTienMatCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTienMatCaretUpdate
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        String textThanhTien = txtThanhTien.getText().trim();
        String tm = txtTienMat.getText().trim();

        // Kiểm tra hình thức thanh toán hiện tại
        String htttSelected = cboHinhThucThanhToan.getSelectedItem().toString();
        if (!htttSelected.equals("Tiền mặt")) {
            return;
        }

        if (textThanhTien == null || textThanhTien.isEmpty()) {
            txtTienThua.setText("0 VND");
            return;
        }
        if (tm == null || tm.isEmpty()) {
            txtTienThua.setText("0 VND");
            return;
        }
        if (!tm.matches("\\d+")) {
            txtTienThua.setText("0 VND");
            JOptionPane.showMessageDialog(null, "Số tiền mặt nhập vào không được là chữ và không được chứa ký tự đặc biệt");
            return;
        }
        try {
            String soTien = extractNumber(textThanhTien);
            double thanhTien = Double.parseDouble(soTien);
            String tm1 = extractNumber(tm);
            double tienMat = Double.parseDouble(tm1);
            if (tienMat < 0) {
                txtTienThua.setText("0 VND");
                JOptionPane.showMessageDialog(null, "Số tiền mặt nhập vào phải là số nguyên dương");
                return;
            }
            double tienThua = 0;
            if (tienMat == thanhTien) {
                tienThua = tienMat - thanhTien;
                txtTienThua.setText(formatter.format(tienThua) + " VND");
            } else if (tienMat > thanhTien) {
                tienThua = tienMat - thanhTien;
                txtTienThua.setText(formatter.format(tienThua) + " VND");
            } else {
                txtTienThua.setText("0 VND");
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            txtTienThua.setText("0 VND");
        }
    }//GEN-LAST:event_txtTienMatCaretUpdate
    private String extractNumber(String input) {
        if (input == null || input.isEmpty()) {
            return "0";
        }
        return input.replaceAll("[^\\d]", "");
    }
    private void txtTienChuyenKhoanCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTienChuyenKhoanCaretUpdate
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        String textThanhTien = txtThanhTien.getText().trim();
        String tck = txtTienChuyenKhoan.getText().trim();

        // Kiểm tra hình thức thanh toán hiện tại
        String htttSelected = cboHinhThucThanhToan.getSelectedItem().toString();
        if (!htttSelected.equals("Chuyển khoản")) {
            return;
        }

        if (textThanhTien == null || textThanhTien.isEmpty()) {
            txtTienThua.setText("0 VND");
            return;
        }
        if (tck == null || tck.isEmpty()) {
            txtTienThua.setText("0 VND");
            return;
        }
        if (!tck.matches("\\d+")) {
            txtTienThua.setText("0 VND");
            JOptionPane.showMessageDialog(null, "Số tiền chuyển khoản nhập vào không được là chữ và không được chứa ký tự đặc biệt");
            return;
        }
        try {
            String soTien = extractNumber(textThanhTien);
            double thanhTien = Double.parseDouble(soTien);
            String tck2 = extractNumber(tck);
            double tienChuyenKhoan = Double.parseDouble(tck2);
            if (tienChuyenKhoan < 0) {
                txtTienThua.setText("0 VND");
                JOptionPane.showMessageDialog(null, "Số tiền chuyển khoản nhập vào phải là số nguyên dương");
                return;
            }
            double tienThua = 0;
            if (tienChuyenKhoan == thanhTien) {
                tienThua = tienChuyenKhoan - thanhTien;
                txtTienThua.setText(formatter.format(tienThua) + " VND");
            } else if (tienChuyenKhoan > thanhTien) {
                tienThua = tienChuyenKhoan - thanhTien;
                txtTienThua.setText(formatter.format(tienThua) + " VND");
            } else {
                txtTienThua.setText("0 VND");
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            txtTienThua.setText("0 VND");
        }
    }//GEN-LAST:event_txtTienChuyenKhoanCaretUpdate

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        resetForm();
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void btnHuyHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyHoaDonActionPerformed
//        int selectedRow = tbHoaDonCho.getSelectedRow();
//
//        if (selectedRow != -1 && selectedRow < tbHoaDonCho.getRowCount()) {
//            // Hiển thị hộp thoại xác nhận
//            int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn hủy hóa đơn chờ này?", "Xác nhận hủy hóa đơn", JOptionPane.YES_NO_OPTION);
//
//            if (confirm == JOptionPane.YES_OPTION) {
//                String maHoaDon = (String) tbHoaDonCho.getValueAt(selectedRow, 1);
//                int selectedHoaDonId = Integer.parseInt(maHoaDon.replace("HD00", ""));
//
//                // Hủy hóa đơn và xóa các sản phẩm liên quan
//                hoaDonRepository.huyHoaDon(selectedHoaDonId);
//
//                JOptionPane.showMessageDialog(this, "Hủy thành công");
//
//                // Xóa hàng khỏi bảng
//                DefaultTableModel model = (DefaultTableModel) tbHoaDonCho.getModel();
//                model.removeRow(selectedRow);
//
//                // Cập nhật lại số lượng sản phẩm trong kho hàng trong giao diện
//                for (int i = 0; i < tbGioHang.getRowCount(); i++) {
//                    String maChiTietSP = (String) tbGioHang.getValueAt(i, 1); // Lấy mã chi tiết sản phẩm từ cột thứ hai
//                    int soLuongTrongGio = (int) tbGioHang.getValueAt(i, 3); // Lấy số lượng từ cột thứ tư
//
//                    // Tách mã chi tiết sản phẩm để lấy id
//                    int idChiTietSP = Integer.parseInt(maChiTietSP.substring(4));
//
//                    // Tìm sản phẩm trong bảng chi tiết sản phẩm và cập nhật số lượng
//                    boolean found = false;
//                    for (int j = 0; j < tbChiTietSanPham.getRowCount(); j++) {
//                        String maChiTietSPTrongKho = (String) tbChiTietSanPham.getValueAt(j, 1); // Lấy mã chi tiết sản phẩm từ cột thứ hai
//                        int idChiTietSPTrongKho = Integer.parseInt(maChiTietSPTrongKho.substring(4));
//
//                        if (idChiTietSPTrongKho == idChiTietSP) {
//                            int soLuongHienTai = (int) tbChiTietSanPham.getValueAt(j, 10); // Lấy số lượng từ cột thứ 11
//                            int newStockQuantity = soLuongHienTai + soLuongTrongGio;
//                            tbChiTietSanPham.setValueAt(newStockQuantity, j, 10);
//
//                            found = true;
//                            break;
//                        }
//                    }
//
//                    if (!found) {
//                        System.out.println("Không tìm thấy sản phẩm với mã chi tiết: " + maChiTietSP);
//                    }
//                }
//
//                // Xóa mô hình giỏ hàng
//                DefaultTableModel modelGioHang = (DefaultTableModel) tbGioHang.getModel();
//                modelGioHang.setRowCount(0);
//
//                // Làm mới mô hình bảng kho hàng
//                tbChiTietSanPham.setModel(tbChiTietSanPham.getModel());
//
//            } else {
//                JOptionPane.showMessageDialog(this, "Đã hủy yêu cầu hủy hóa đơn chờ.");
//            }
//        } else {
//            JOptionPane.showMessageDialog(this, "Vui lòng chọn một hóa đơn để hủy.");
//        }

        int selectedRow = tbHoaDonCho.getSelectedRow();

        if (selectedRow != -1 && selectedRow < tbHoaDonCho.getRowCount()) {
            // Hiển thị hộp thoại xác nhận
            int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn hủy hóa đơn chờ này?", "Xác nhận hủy hóa đơn", JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                Object value = tbHoaDonCho.getValueAt(selectedRow, 1);
                if (value != null) {
                    String maHoaDon = (String) value;
                    int selectedHoaDonId = Integer.parseInt(maHoaDon.replace("HD00", ""));

                    // Hủy hóa đơn và xóa các sản phẩm liên quan
                    hoaDonRepository.huyHoaDon(selectedHoaDonId);

                    JOptionPane.showMessageDialog(this, "Hủy thành công");

                    // Xóa hàng khỏi bảng
                    DefaultTableModel model = (DefaultTableModel) tbHoaDonCho.getModel();
                    model.removeRow(selectedRow);

                    // Cập nhật lại số lượng sản phẩm trong kho hàng trong giao diện
                    for (int i = 0; i < tbGioHang.getRowCount(); i++) {
                        Object maChiTietSPValue = tbGioHang.getValueAt(i, 1); // Lấy mã chi tiết sản phẩm từ cột thứ hai
                        Object soLuongTrongGioValue = tbGioHang.getValueAt(i, 3); // Lấy số lượng từ cột thứ tư

                        if (maChiTietSPValue != null && soLuongTrongGioValue != null) {
                            String maChiTietSP = (String) maChiTietSPValue;
                            int soLuongTrongGio = (Integer) soLuongTrongGioValue;

                            // Tách mã chi tiết sản phẩm để lấy id
                            int idChiTietSP = Integer.parseInt(maChiTietSP.substring(4));

                            // Tìm sản phẩm trong bảng chi tiết sản phẩm và cập nhật số lượng
                            boolean found = false;
                            for (int j = 0; j < tbChiTietSanPham.getRowCount(); j++) {
                                Object maChiTietSPTrongKhoValue = tbChiTietSanPham.getValueAt(j, 1); // Lấy mã chi tiết sản phẩm từ cột thứ hai
                                Object soLuongHienTaiValue = tbChiTietSanPham.getValueAt(j, 10); // Lấy số lượng từ cột thứ 11

                                if (maChiTietSPTrongKhoValue != null && soLuongHienTaiValue != null) {
                                    String maChiTietSPTrongKho = (String) maChiTietSPTrongKhoValue;
                                    int idChiTietSPTrongKho = Integer.parseInt(maChiTietSPTrongKho.substring(4));
                                    int soLuongHienTai = (Integer) soLuongHienTaiValue;

                                    if (idChiTietSPTrongKho == idChiTietSP) {
                                        int newStockQuantity = soLuongHienTai + soLuongTrongGio;
                                        found = true;
                                        break;
                                    }
                                }
                            }

                            if (!found) {
                                System.out.println("Không tìm thấy sản phẩm với mã chi tiết: " + maChiTietSP);
                            }
                        } else {
                            System.out.println("Giá trị của mã chi tiết sản phẩm hoặc số lượng trong giỏ là null.");
                        }
                    }

                    // Xóa mô hình giỏ hàng
                    DefaultTableModel modelGioHang = (DefaultTableModel) tbGioHang.getModel();
                    modelGioHang.setRowCount(0);

                    // Làm mới mô hình bảng kho hàng
                    tbChiTietSanPham.setModel(tbChiTietSanPham.getModel());

                } else {
                    JOptionPane.showMessageDialog(this, "Giá trị của hóa đơn là null.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Đã hủy yêu cầu hủy hóa đơn chờ.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một hóa đơn để hủy.");
        }
    }//GEN-LAST:event_btnHuyHoaDonActionPerformed

    private void txtTimKiemSanPhamKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemSanPhamKeyReleased
        String search = txtTimKiemSanPham.getText();
        model = (DefaultTableModel) tbChiTietSanPham.getModel();
        search(search, model, tbChiTietSanPham);
    }//GEN-LAST:event_txtTimKiemSanPhamKeyReleased

    private void btnQuetQRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuetQRActionPerformed
        showWebcam();
    }//GEN-LAST:event_btnQuetQRActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChonKhachHang;
    private javax.swing.JButton btnHuyHoaDon;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnQuetQR;
    private javax.swing.JButton btnTaoHoaDon;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnXoaSanPham;
    private javax.swing.JButton btnXoaTatCa;
    private javax.swing.JComboBox<String> cboHinhThucThanhToan;
    private javax.swing.JComboBox<String> cboLocKichThuoc;
    private javax.swing.JComboBox<String> cboLocMauSac;
    private javax.swing.JComboBox<String> cboLocXuatXu;
    private javax.swing.JComboBox<String> cboPhieuGiamGia;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tbChiTietSanPham;
    private javax.swing.JTable tbGioHang;
    private javax.swing.JTable tbHoaDonCho;
    private javax.swing.JTextField txtMaHoaDon;
    private javax.swing.JTextField txtMaKhachHang;
    private javax.swing.JTextField txtNgayTao;
    private javax.swing.JTextField txtSoDTKhachHang;
    private javax.swing.JTextField txtTenKhachHang;
    private javax.swing.JTextField txtThanhTien;
    private javax.swing.JTextField txtTienChuyenKhoan;
    private javax.swing.JTextField txtTienMat;
    private javax.swing.JTextField txtTienThua;
    private javax.swing.JTextField txtTimKiemHoaDon;
    private javax.swing.JTextField txtTimKiemSanPham;
    private javax.swing.JTextField txtTongTien;
    // End of variables declaration//GEN-END:variables
}
