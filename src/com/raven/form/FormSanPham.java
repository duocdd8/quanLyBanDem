package com.raven.form;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.raven.classmodel.ChiTietSanPham;
import com.raven.classmodel.DoCung;
import com.raven.classmodel.DoDay;
import com.raven.classmodel.GetChucVu;
import com.raven.classmodel.KichThuoc;
import com.raven.classmodel.LoaiDem;
import com.raven.classmodel.MauSac;
import com.raven.classmodel.SanPham;
import com.raven.classmodel.SanPhamChiTiet;
import com.raven.classmodel.ThietKe;
import com.raven.classmodel.ThuongHieu;
import com.raven.classmodel.XuatXu;
import com.raven.main.Main;
import com.raven.repository.ChiTietSanPhamRepository;
import com.raven.repository.DoCungRepository;
import com.raven.repository.DoDayRepository;
import com.raven.repository.GetIDSanPhamChiTiet;
import com.raven.repository.KichThuocRepository;
import com.raven.repository.LoaiDemRepository;
import com.raven.repository.MauSacRepository;
import com.raven.repository.SanPhamRepository;
import com.raven.repository.ThietKeRepository;
import com.raven.repository.ThuongHieuRepository;
import com.raven.repository.XuatXuRepository;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Hoai Thu
 */
public class FormSanPham extends javax.swing.JPanel {

    DefaultTableModel model = new DefaultTableModel();
    DefaultComboBoxModel cboModel = new DefaultComboBoxModel();
    SanPhamRepository sanPhamRepository = new SanPhamRepository();
    KichThuocRepository kichThuocRepository = new KichThuocRepository();
    DoCungRepository doCungRepository = new DoCungRepository();
    DoDayRepository doDayRepository = new DoDayRepository();
    LoaiDemRepository loaiDemRepository = new LoaiDemRepository();
    MauSacRepository mauSacRepository = new MauSacRepository();
    ThietKeRepository thietKeRepository = new ThietKeRepository();
    ThuongHieuRepository thuongHieuRepository = new ThuongHieuRepository();
    XuatXuRepository xuatXuRepository = new XuatXuRepository();
    GetIDSanPhamChiTiet getIDSanPhamChiTiet = new GetIDSanPhamChiTiet();
    ChiTietSanPham chiTietSanPham = new ChiTietSanPham();
    ChiTietSanPhamRepository chiTietSanPhamRepository = new ChiTietSanPhamRepository();

    List<DoCung> listdc = new ArrayList<>();
    List<DoDay> listdd = new ArrayList<>();
    List<LoaiDem> listld = new ArrayList<>();
    List<ThietKe> listtk = new ArrayList<>();
    List<ThuongHieu> listth = new ArrayList<>();
    List<XuatXu> listxx = new ArrayList<>();
    List<MauSac> listms = new ArrayList<>();
    List<KichThuoc> listkt = new ArrayList<>();

    private int index = -1;

    public FormSanPham() {
        initComponents();
        initCombobox();
        fillTableDSSP(sanPhamRepository.fillSP());
        fillTableDSCTSP(sanPhamRepository.fillCTSP());
        if (GetChucVu.getChucVu() == 0) {
            btnCapNhat.setEnabled(true);
            btnChinhSuaSanPham.setEnabled(true);
            btnChinhSuaThuocTinh.setEnabled(true);
            btnLamMoiCTSP.setEnabled(true);
            btnThemCTSP.setEnabled(true);
            btnThemMoiSanPham.setEnabled(true);
            btnThemNhanhDoCung.setEnabled(true);
            btnThemNhanhDoDay.setEnabled(true);
            btnThemNhanhKichThuoc.setEnabled(true);
            btnThemNhanhLoaiDem.setEnabled(true);
            btnThemNhanhMauSac.setEnabled(true);
            btnThemNhanhTenSP.setEnabled(true);
            btnThemNhanhThietKe.setEnabled(true);
            btnThemNhanhThuongHieu.setEnabled(true);
            btnThemNhanhXuatXu.setEnabled(true);
            btnThemThuocTinh.setEnabled(true);
            btnXoaThuocTinh.setEnabled(true);
            btnXuatExcel.setEnabled(true);

        } else if (GetChucVu.getChucVu() == 1) {
            btnCapNhat.setEnabled(false);
            btnChinhSuaSanPham.setEnabled(false);
            btnChinhSuaThuocTinh.setEnabled(false);
            btnLamMoiCTSP.setEnabled(false);
            btnThemCTSP.setEnabled(false);
            btnThemMoiSanPham.setEnabled(false);
            btnThemNhanhDoCung.setEnabled(false);
            btnThemNhanhDoDay.setEnabled(false);
            btnThemNhanhKichThuoc.setEnabled(false);
            btnThemNhanhLoaiDem.setEnabled(false);
            btnThemNhanhMauSac.setEnabled(false);
            btnThemNhanhTenSP.setEnabled(false);
            btnThemNhanhThietKe.setEnabled(false);
            btnThemNhanhThuongHieu.setEnabled(false);
            btnThemNhanhXuatXu.setEnabled(false);
            btnThemThuocTinh.setEnabled(false);
            btnXoaThuocTinh.setEnabled(false);
            btnXuatExcel.setEnabled(false);
        }
    }

    private void openThemNhanh() {
        Main m = new Main();
        new ThemNhanhThuocTinh(m, true).setVisible(true);
    }

    public void fillTableDSSP(List<SanPham> listsp) {
        model = (DefaultTableModel) tbDSSP.getModel();
        model.setRowCount(0);
        listsp = sanPhamRepository.fillSP();
        int i = 1;
        for (SanPham sp : listsp) {
            Object[] row = {
                i++,
                "SP00" + sp.getId(),
                sp.getTenSP(),
                sp.isTrangThai() ? "Còn hàng" : "Hết hàng"
            };
            model.addRow(row);
        }
    }

    public void fillSanPhamConHang(List<SanPham> listspch) {
        model = (DefaultTableModel) tbDSSP.getModel();
        model.setRowCount(0);
        listspch = sanPhamRepository.fillSanPhamConHang();
        int i = 1;
        for (SanPham sp : listspch) {
            Object[] row = {
                i++,
                "SP00" + sp.getId(),
                sp.getTenSP(),
                sp.isTrangThai() ? "Còn hàng" : "Hết hàng"
            };
            model.addRow(row);
        }
    }

    public void fillSanPhamHetHang(List<SanPham> listsphh) {
        model = (DefaultTableModel) tbDSSP.getModel();
        model.setRowCount(0);
        listsphh = sanPhamRepository.fillSanPhamHetHang();
        int i = 1;
        for (SanPham sp : listsphh) {
            Object[] row = {
                i++,
                "SP00" + sp.getId(),
                sp.getTenSP(),
                sp.isTrangThai() == true ? "Còn hàng" : "Hết hàng"
            };
            model.addRow(row);
        }
    }

    public void fillTableDSCTSP(List<SanPham> listctsp) {
        model = (DefaultTableModel) tbCTSP.getModel();
        model.setRowCount(0);
        listctsp = sanPhamRepository.fillCTSP();
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        int i = 1;
        for (SanPham sp : listctsp) {
            Object[] row = {
                sp.getCtsp().getId(),
                "SPCT00" + sp.getCtsp().getId(),
                sp.getTenSP(),
                sp.getLoaiDem().getTenLD(),
                sp.getXuatXu().getTenXX(),
                sp.getDoCung().getTenDC(),
                sp.getDoDay().getTenDD() + " cm",
                sp.getMauSac().getTenMS(),
                sp.getThuongHieu().getTenTH(),
                sp.getThietKe().getTenTK(),
                sp.getKichThuoc().getTenKT(),
                formatter.format(sp.getCtsp().getGiaBan()) + " VND",
                sp.getCtsp().getSoLuong(),
                sdf.format(sp.getCtsp().getNgaySanXuat()),
                sp.getCtsp().getBaoHanh(),
                sdf.format(sp.getCtsp().getNgaySanXuat())
            };
            model.addRow(row);
        }
    }

    public void fillTableKichThuoc(List<KichThuoc> listkt) {
        model = (DefaultTableModel) tbDSTT.getModel();
        model.setRowCount(0);
        listkt = kichThuocRepository.getAll();
        int i = 1;
        for (KichThuoc kt : listkt) {
            Object[] row = {
                i++,
                "KT00" + kt.getId(),
                kt.getTenKT(),
                kt.isTrangThai() ? "Còn hàng" : "Hết hàng"
            };
            model.addRow(row);
        }
    }

    public void fillTableDoCung(List<DoCung> listdc) {
        model = (DefaultTableModel) tbDSTT.getModel();
        model.setRowCount(0);
        listdc = doCungRepository.getAll();
        int i = 1;
        for (DoCung dc : listdc) {
            Object[] row = {
                i++,
                "DC00" + dc.getId(),
                dc.getTenDC(),
                dc.isTrangThai() ? "Còn hàng" : "Hết hàng"
            };
            model.addRow(row);
        }
    }

    public void fillTableDoDay(List<DoDay> listdd) {
        model = (DefaultTableModel) tbDSTT.getModel();
        model.setRowCount(0);
        listdd = doDayRepository.getAll();
        int i = 1;
        for (DoDay dd : listdd) {
            Object[] row = {
                i++,
                "DD00" + dd.getId(),
                dd.getTenDD() + "cm",
                dd.isTrangThai() ? "Còn hàng" : "Hết hàng"
            };
            model.addRow(row);
        }
    }

    public void fillTableLoaiDem(List<LoaiDem> listld) {
        model = (DefaultTableModel) tbDSTT.getModel();
        model.setRowCount(0);
        listld = loaiDemRepository.getAll();
        int i = 1;
        for (LoaiDem ld : listld) {
            Object[] row = {
                i++,
                "LD00" + ld.getId(),
                ld.getTenLD(),
                ld.isTrangThai() ? "Còn hàng" : "Hết hàng"
            };
            model.addRow(row);
        }
    }

    public void fillTableMauSac(List<MauSac> listms) {
        model = (DefaultTableModel) tbDSTT.getModel();
        model.setRowCount(0);
        listms = mauSacRepository.getAll();
        int i = 1;
        for (MauSac ms : listms) {
            Object[] row = {
                i++,
                "MS00" + ms.getId(),
                ms.getTenMS(),
                ms.isTrangThai() ? "Còn hàng" : "Hết hàng"
            };
            model.addRow(row);
        }
    }

    public void fillTableThietKe(List<ThietKe> listtk) {
        model = (DefaultTableModel) tbDSTT.getModel();
        model.setRowCount(0);
        listtk = thietKeRepository.getAll();
        int i = 1;
        for (ThietKe tk : listtk) {
            Object[] row = {
                i++,
                "TK00" + tk.getId(),
                tk.getTenTK(),
                tk.isTrangThai() ? "Còn hàng" : "Hết hàng"
            };
            model.addRow(row);
        }
    }

    public void fillTableThuongHieu(List<ThuongHieu> listth) {
        model = (DefaultTableModel) tbDSTT.getModel();
        model.setRowCount(0);
        listth = thuongHieuRepository.getAll();
        int i = 1;
        for (ThuongHieu th : listth) {
            Object[] row = {
                i++,
                "TH00" + th.getId(),
                th.getTenTH(),
                th.isTrangThai() ? "Còn hàng" : "Hết hàng"
            };
            model.addRow(row);
        }
    }

    public void fillTableXuatXu(List<XuatXu> listxx) {
        model = (DefaultTableModel) tbDSTT.getModel();
        model.setRowCount(0);
        listxx = xuatXuRepository.getAll();
        int i = 1;
        for (XuatXu xx : listxx) {
            Object[] row = {
                i++,
                "XX00" + xx.getId(),
                xx.getTenXX(),
                xx.isTrangThai() ? "Còn hàng" : "Hết hàng"
            };
            model.addRow(row);
        }
    }

    public void initCombobox() {
        fillCboDoCung();
        fillCboDoDay();
        fillCboKichThuoc();
        fillCboLoaiDem();
        fillCboMauSac();
        fillCboThietKe();
        fillCboThuongHieu();
        fillCboXuatXu();
        fillCboTenSanPham();
    }

    public void fillCboDoCung() {
        cboModel = (DefaultComboBoxModel) this.cboDoCung.getModel();
        cboModel.removeAllElements();
        List<DoCung> listdc = doCungRepository.getAll();
        for (DoCung dc : listdc) {
            cboModel.addElement(dc.getTenDC());
        }
    }

    public void fillCboDoDay() {
        cboModel = (DefaultComboBoxModel) this.cboDoDay.getModel();
        cboModel.removeAllElements();
        List<DoDay> listdd = doDayRepository.getAll();
        for (DoDay dd : listdd) {
            cboModel.addElement(dd.getTenDD());
        }
    }

    public void fillCboKichThuoc() {
        cboModel = (DefaultComboBoxModel) this.cboKichThuoc.getModel();
        cboModel.removeAllElements();
        List<KichThuoc> listkt = kichThuocRepository.getAll();
        for (KichThuoc kt : listkt) {
            cboModel.addElement(kt.getTenKT());
        }
    }

    public void fillCboLoaiDem() {
        cboModel = (DefaultComboBoxModel) this.cboLoaiDem.getModel();
        cboModel.removeAllElements();
        List<LoaiDem> listld = loaiDemRepository.getAll();
        for (LoaiDem ld : listld) {
            cboModel.addElement(ld.getTenLD());
        }
    }

    public void fillCboMauSac() {
        cboModel = (DefaultComboBoxModel) this.cboMauSac.getModel();
        cboModel.removeAllElements();
        List<MauSac> listms = mauSacRepository.getAll();
        for (MauSac ms : listms) {
            cboModel.addElement(ms.getTenMS());
        }
    }

    public void fillCboThietKe() {
        cboModel = (DefaultComboBoxModel) this.cboThietKe.getModel();
        cboModel.removeAllElements();
        List<ThietKe> listtk = thietKeRepository.getAll();
        for (ThietKe tk : listtk) {
            cboModel.addElement(tk.getTenTK());
        }
    }

    public void fillCboThuongHieu() {
        cboModel = (DefaultComboBoxModel) this.cboThuongHieu.getModel();
        cboModel.removeAllElements();
        List<ThuongHieu> listth = thuongHieuRepository.getAll();
        for (ThuongHieu th : listth) {
            cboModel.addElement(th.getTenTH());
        }
    }

    public void fillCboXuatXu() {
        cboModel = (DefaultComboBoxModel) this.cboXuatXu.getModel();
        cboModel.removeAllElements();
        List<XuatXu> listxx = xuatXuRepository.getAll();
        for (XuatXu xx : listxx) {
            cboModel.addElement(xx.getTenXX());
        }
    }

    public void fillCboTenSanPham() {
        cboModel = (DefaultComboBoxModel) this.cboTenSanPham.getModel();
        cboModel.removeAllElements();
        List<SanPham> listsp = sanPhamRepository.fillSP();
        for (SanPham sp : listsp) {
            cboModel.addElement(sp.getTenSP());
        }
    }

    public void resetSanPham() {
        txtMaSP.setText(null);
        txtTenSanPham.setText(null);
        txtTimKiemSanPham.setText(null);
    }

    public void resetCTSP() {
        txtMaCTSP.setText(null);
        txtSoLuong.setText(null);
        txtNamBH.setText(null);
        cboTenSanPham.setSelectedIndex(0);
        txtGiaBan.setText(null);
        cboDoCung.setSelectedIndex(0);
        cboDoDay.setSelectedIndex(0);
        cboKichThuoc.setSelectedIndex(0);
        cboLoaiDem.setSelectedIndex(0);
        cboMauSac.setSelectedIndex(0);
        cboThietKe.setSelectedIndex(0);
        cboThuongHieu.setSelectedIndex(0);
        cboXuatXu.setSelectedIndex(0);
        txtTimKiemCTSP.setText(null);
    }

    public void resetThuocTinh() {
        txtTenThuocTinh.setText(null);
        txtMaThuocTinh.setText(null);
    }

    void searchSP(String name) {
        RowFilter rf = null;
        DefaultTableModel model = (DefaultTableModel) tbDSSP.getModel();
        TableRowSorter<DefaultTableModel> trs = new TableRowSorter<DefaultTableModel>(model);
        tbDSSP.setRowSorter(trs);
        trs.setRowFilter(RowFilter.regexFilter(name, 2));
    }

    void searchTT(String name) {
        RowFilter rf = null;
        DefaultTableModel model = (DefaultTableModel) tbDSTT.getModel();
        TableRowSorter<DefaultTableModel> trs = new TableRowSorter<DefaultTableModel>(model);
        tbDSTT.setRowSorter(trs);
        trs.setRowFilter(RowFilter.regexFilter(name, 2));
    }

    void searchCTSP(String name) {

    }

    boolean kiemTraThuocTinhRong() {
        if (txtTenThuocTinh.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không được bỏ trống tên thuộc tính !");
            return false;
        }
        return true;
    }

    boolean kiemTraThuocTinhQuaKyTu() {
        if (txtTenThuocTinh.getText().length() > 50) {
            JOptionPane.showMessageDialog(this, "Tên thuộc tính không được vượt quá 50 ký tự!");
            return false;
        }
        return true;
    }

    public boolean kiemTraThuocTinhDaTonTai() {
        String inputTenThuocTinh = txtTenThuocTinh.getText().trim();
        for (int i = 0; i < tbDSTT.getRowCount(); i++) {
            String tenThuocTinh = this.tbDSTT.getValueAt(i, 1).toString();
            if (tenThuocTinh.equalsIgnoreCase(inputTenThuocTinh)) {
                JOptionPane.showMessageDialog(this, "Tên thuộc tính " + inputTenThuocTinh + " đã tồn tại ");
                return false;
            }
        }
        return true;
    }

    public boolean kiemTraDoDaiTenThuocTinh(String input) {
        if (input.length() < 2) {
            JOptionPane.showMessageDialog(this, "Tên thuộc tính phải có tối thiểu 2 ký tự.");
            return false;
        }
        return true;
    }

    public boolean kiemTraTenThuocTinhChuaChu(String input) {
        String regex = "^[a-zA-Z\\p{L}\\s]+$";
        String message = (input.matches(regex)) ? "" : "Tên thuộc tính chỉ được là chữ và khoảng trắng.";
        JOptionPane.showMessageDialog(this, message);
        return input.matches(regex);
    }

    public boolean kiemTraTenThuocTinhChuaSo(String inputDoDay) {
        String regex = "\\d+";
        String message = "";

        if (!inputDoDay.matches(regex)) {
            message = "Tên độ dày phải là số.";
        } else {
            int number = Integer.parseInt(inputDoDay);
            if (number <= 0 || number > 30) {
                message = "Tên độ dày phải lớn hơn 0 và nhỏ hơn hoặc bằng 30.";
            }
        }

        // Display the message if there is any
        if (!message.isEmpty()) {
            JOptionPane.showMessageDialog(null, message);
            return false;
        }
        return true;
    }

    public boolean kiemTraKichThuoc(String size) {
        Pattern pattern = Pattern.compile("^(\\d+) x (\\d+)$");
        Matcher matcher = pattern.matcher(size);
        if (!matcher.matches()) {
            JOptionPane.showMessageDialog(null, "Kích thước phải ở định dạng 'rộng x dài'.");
            return false;
        }
        int width = Integer.parseInt(matcher.group(1));
        int length = Integer.parseInt(matcher.group(2));
        if (!matcher.group(1).matches("[a-zA-Z0-9]+") || !matcher.group(2).matches("[a-zA-Z0-9]+")) {
            JOptionPane.showMessageDialog(null, "Kích thước không được chứa ký tự đặc biệt.");
            return false;
        }
        if (width < 100 || width > 200) {
            JOptionPane.showMessageDialog(null, "Độ rộng phải từ 100 đến 200.");
            return false;
        }
        if (length < 180 || length > 220) {
            JOptionPane.showMessageDialog(null, "Độ dài phải từ 180 đến 220.");
            return false;
        }
        if (width == length) {
            JOptionPane.showMessageDialog(null, "Độ rộng và độ dài không được bằng nhau.");
            return false;
        }
        return true;
    }

    boolean checkKichThuocDaTonTai(String inputSize) {
        List<KichThuoc> lisrkt = kichThuocRepository.getAll();
        for (KichThuoc kt : lisrkt) {
            if (kt.getTenKT().equals(inputSize)) {
                JOptionPane.showMessageDialog(this, "Kích thước " + inputSize + " đã tồn tại.");
                return false;
            }
        }
        return true;
    }

    public void themDoCung(String tenThuocTinh) {
        DoCung dc = new DoCung();
        dc.setTenDC(tenThuocTinh.trim());
        dc.setTrangThai(true);
        doCungRepository.themDC(dc);
        JOptionPane.showMessageDialog(this, "Thêm độ cứng " + tenThuocTinh + " thành công !");
    }

    public void themDoDay(String tenThuocTinh) {
        DoDay dd = new DoDay();
        dd.setTenDD(tenThuocTinh.trim());
        dd.setTrangThai(true);
        doDayRepository.themDD(dd);
        JOptionPane.showMessageDialog(this, "Thêm độ dày " + tenThuocTinh + " thành công !");
    }

    public void themKichThuoc(String tenThuocTinh) {
        KichThuoc kt = new KichThuoc();
        kt.setTenKT(tenThuocTinh.trim());
        kt.setTrangThai(true);
        kichThuocRepository.themKT(kt);
        JOptionPane.showMessageDialog(this, "Thêm kích thước " + tenThuocTinh + " thành công !");
    }

    public void themLoaiDem(String tenThuocTinh) {
        LoaiDem ld = new LoaiDem();
        ld.setTenLD(tenThuocTinh.trim());
        ld.setTrangThai(true);
        loaiDemRepository.themLD(ld);
        JOptionPane.showMessageDialog(this, "Thêm loại đệm " + tenThuocTinh + " thành công !");
    }

    public void themMauSac(String tenThuocTinh) {
        MauSac ms = new MauSac();
        ms.setTenMS(tenThuocTinh.trim());
        ms.setTrangThai(true);
        mauSacRepository.themMS(ms);
        JOptionPane.showMessageDialog(this, "Thêm màu sắc " + tenThuocTinh + " thành công !");
    }

    public void themThietKe(String tenThuocTinh) {
        ThietKe tk = new ThietKe();
        tk.setTenTK(tenThuocTinh.trim());
        tk.setTrangThai(true);
        thietKeRepository.themTK(tk);
        JOptionPane.showMessageDialog(this, "Thêm thiết kế " + tenThuocTinh + " thành công !");
    }

    public void themThuongHieu(String tenThuocTinh) {
        ThuongHieu th = new ThuongHieu();
        th.setTenTH(tenThuocTinh.trim());
        th.setTrangThai(true);
        thuongHieuRepository.themTH(th);
        JOptionPane.showMessageDialog(this, "Thêm thương hiệu " + tenThuocTinh + " thành công !");
    }

    public void themXuatXu(String tenThuocTinh) {
        XuatXu xx = new XuatXu();
        xx.setTenXX(tenThuocTinh.trim());
        xx.setTrangThai(true);
        xuatXuRepository.themXX(xx);
        JOptionPane.showMessageDialog(this, "Thêm xuất xứ " + tenThuocTinh + " thành công !");
    }

    public void suaKichThuoc(int rowSelected) {
        int i = tbDSTT.getSelectedRow();
        KichThuoc kt = new KichThuoc();
        kt.setTenKT(txtTenThuocTinh.getText());
        kt.setId((int) tbDSTT.getValueAt(i, 0));
        boolean ktResult = kichThuocRepository.suaKT(kt);
        if (ktResult) {
            fillTableKichThuoc(kichThuocRepository.getAll());
        }
    }

    public void suaDoCung(int rowSelected) {
        int i = tbDSTT.getSelectedRow();
        DoCung dc = new DoCung();
        dc.setTenDC(txtTenThuocTinh.getText());
        dc.setId((int) tbDSTT.getValueAt(i, 0));
        boolean ktResult = doCungRepository.suaDC(dc);
        if (ktResult) {
            fillTableDoCung(doCungRepository.getAll());
        }
    }

    public void suaDoDay(int rowSelected) {
        int i = tbDSTT.getSelectedRow();
        DoDay dd = new DoDay();
        dd.setTenDD(txtTenThuocTinh.getText());
        dd.setId((int) tbDSTT.getValueAt(i, 0));
        boolean ktResult = doDayRepository.suaDD(dd);
        if (ktResult) {
            fillTableDoDay(doDayRepository.getAll());
        }
    }

    public void suaLoaiDem(int rowSelected) {
        int i = tbDSTT.getSelectedRow();
        LoaiDem ld = new LoaiDem();
        ld.setTenLD(txtTenThuocTinh.getText());
        ld.setId((int) tbDSTT.getValueAt(i, 0));
        boolean ktResult = loaiDemRepository.suaLD(ld);
        if (ktResult) {
            fillTableLoaiDem(loaiDemRepository.getAll());
        }
    }

    public void suaMauSac(int rowSelected) {
        int i = tbDSTT.getSelectedRow();
        MauSac ms = new MauSac();
        ms.setTenMS(txtTenThuocTinh.getText());
        ms.setId((int) tbDSTT.getValueAt(i, 0));
        boolean ktResult = mauSacRepository.suaMS(ms);
        if (ktResult) {
            fillTableMauSac(mauSacRepository.getAll());
        }
    }

    public void suaThietKe(int rowSelected) {
        int i = tbDSTT.getSelectedRow();
        ThietKe tk = new ThietKe();
        tk.setTenTK(txtTenThuocTinh.getText());
        tk.setId((int) tbDSTT.getValueAt(i, 0));
        boolean ktResult = thietKeRepository.suaTK(tk);
        if (ktResult) {
            fillTableThietKe(thietKeRepository.getAll());
        }
    }

    public void suaThuongHieu(int rowSelected) {
        int i = tbDSTT.getSelectedRow();
        ThuongHieu th = new ThuongHieu();
        th.setTenTH(txtTenThuocTinh.getText());
        th.setId((int) tbDSTT.getValueAt(i, 0));
        boolean ktResult = thuongHieuRepository.suaTH(th);
        if (ktResult) {
            fillTableThuongHieu(thuongHieuRepository.getAll());
        }
    }

    public void suaXuatXu(int rowSelected) {
        int i = tbDSTT.getSelectedRow();
        XuatXu xx = new XuatXu();
        xx.setTenXX(txtTenThuocTinh.getText());
        xx.setId((int) tbDSTT.getValueAt(i, 0));
        boolean ktResult = xuatXuRepository.suaXX(xx);
        if (ktResult) {
            fillTableXuatXu(xuatXuRepository.getAll());
        }
    }

    public SanPham readForm() {
        String tenSP = txtTenSanPham.getText();
        return new SanPham(tenSP);
    }

    public SanPhamChiTiet readFormSPCT() {
        try {
            // Kiểm tra và chuyển đổi giá trị Số lượng
            String soLuongStr = txtSoLuong.getText().trim();
            if (soLuongStr.isEmpty() || !soLuongStr.matches("\\d+")) {
                throw new NumberFormatException("Số lượng không được để trống và phải là số nguyên dương.");
            }
            int soLuong = Integer.parseInt(soLuongStr);

            // Kiểm tra và chuyển đổi giá trị Giá bán
            String giaBanStr = txtGiaBan.getText().trim().replaceAll("[^0-9.]", "");
            if (giaBanStr.isEmpty() || !giaBanStr.matches("\\d+(\\.\\d+)?")) {
                throw new NumberFormatException("Giá bán không được để trống và phải là số hợp lệ.");
            }
            double giaBan = Double.parseDouble(giaBanStr);

            // Kiểm tra và chuyển đổi giá trị Năm bảo hành
            String namBHStr = txtNamBH.getText().trim();
            if (namBHStr.isEmpty() || !namBHStr.matches("\\d+")) {
                throw new NumberFormatException("Năm bảo hành không được để trống và phải là số nguyên.");
            }
            int namBH = Integer.parseInt(namBHStr);

            // Kiểm tra giá trị Ngày sản xuất
            java.util.Date ngaySX = JDate.getDate();
            if (ngaySX == null) {
                throw new IllegalArgumentException("Ngày sản xuất không được để trống.");
            }

            // Tạo đối tượng SanPhamChiTiet và trả về
            return new SanPhamChiTiet(
                    getIDSanPhamChiTiet.getIDSanPham(cboTenSanPham.getSelectedItem() + ""),
                    soLuong,
                    giaBan,
                    namBH,
                    ngaySX,
                    getIDSanPhamChiTiet.getIDLoaiDem(cboLoaiDem.getSelectedItem() + ""),
                    getIDSanPhamChiTiet.getIDXuatXu(cboXuatXu.getSelectedItem() + ""),
                    getIDSanPhamChiTiet.getIDThuongHieu(cboThuongHieu.getSelectedItem() + ""),
                    getIDSanPhamChiTiet.getIDDoDay(cboDoDay.getSelectedItem() + ""),
                    getIDSanPhamChiTiet.getIDDoCung(cboDoCung.getSelectedItem() + ""),
                    getIDSanPhamChiTiet.getIDThietKe(cboThietKe.getSelectedItem() + ""),
                    getIDSanPhamChiTiet.getIDKichThuoc(cboKichThuoc.getSelectedItem() + ""),
                    getIDSanPhamChiTiet.getIDMauSac(cboMauSac.getSelectedItem() + "")
            );
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return null;
        }

    }

    public void search(String str, DefaultTableModel model, JTable table) {
        model = (DefaultTableModel) tbCTSP.getModel();
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

    boolean testData(String tenSanPham) {
        if (tenSanPham.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tên sản phẩm không được để trống.");
            return false;
        }
        if (!tenSanPham.matches("^[a-zA-Z\\p{L}\\s]+$")) {
            JOptionPane.showMessageDialog(this, "Tên sản phẩm không được chứa ký tự đặc biệt hoặc số.");
            return false;
        }
        if (tenSanPham.length() < 2) {
            JOptionPane.showMessageDialog(this, "Tên sản phẩm phải có tối thiểu 2 ký tự.");
            return false;
        }
        if (tenSanPham.length() > 255) {
            JOptionPane.showMessageDialog(this, "Tên sản phẩm không được quá 255 ký tự.");
            return false;
        }
        return true;
    }

    boolean checkSanPhamDaTonTai(String inputTenSanPham) {
        List<SanPham> listSP = sanPhamRepository.fillSP();
        for (SanPham sp : listSP) {
            if (sp.getTenSP().equals(inputTenSanPham)) {
                JOptionPane.showMessageDialog(this, "Tên sản phẩm " + inputTenSanPham + " đã tồn tại.");
                return false;
            }
        }
        return true;
    }

    boolean checkLoaiDemDaTonTai(String inputTenLoaiDem) {
        List<LoaiDem> listLD = loaiDemRepository.getAll();
        for (LoaiDem ld : listLD) {
            if (ld.getTenLD().equals(inputTenLoaiDem)) {
                JOptionPane.showMessageDialog(this, "Tên loại đệm " + inputTenLoaiDem + " đã tồn tại.");
                return false;
            }
        }
        return true;
    }

    boolean checkMauSacDaTonTai(String inputTenMauSac) {
        List<MauSac> listMS = mauSacRepository.getAll();
        for (MauSac ms : listMS) {
            if (ms.getTenMS().equals(inputTenMauSac)) {
                JOptionPane.showMessageDialog(this, "Tên màu sắc " + inputTenMauSac + " đã tồn tại.");
                return false;
            }
        }
        return true;
    }

    boolean checkThuongHieuDaTonTai(String inputTenThuongHieu) {
        List<ThuongHieu> listTH = thuongHieuRepository.getAll();
        for (ThuongHieu th : listTH) {
            if (th.getTenTH().equals(inputTenThuongHieu)) {
                JOptionPane.showMessageDialog(this, "Tên thương hiệu " + inputTenThuongHieu + " đã tồn tại.");
                return false;
            }
        }
        return true;
    }

    boolean checkXuatXuDaTonTai(String inputTenXuatXu) {
        List<XuatXu> listXX = xuatXuRepository.getAll();
        for (XuatXu xx : listXX) {
            if (xx.getTenXX().equals(inputTenXuatXu)) {
                JOptionPane.showMessageDialog(this, "Tên xuất xứ " + inputTenXuatXu + " đã tồn tại.");
                return false;
            }
        }
        return true;
    }

    boolean checkDoCungDaTonTai(String inputDoCung) {
        List<DoCung> listDC = doCungRepository.getAll();
        for (DoCung dc : listDC) {
            if (dc.getTenDC().equals(inputDoCung)) {
                JOptionPane.showMessageDialog(this, "Độ cứng " + inputDoCung + " đã tồn tại.");
                return false;
            }
        }
        return true;
    }

    boolean checkDoDayDaTonTai(String inputDoDay) {
        List<DoDay> listDD = doDayRepository.getAll();
        for (DoDay dd : listDD) {
            if (dd.getTenDD().equals(inputDoDay)) {
                JOptionPane.showMessageDialog(this, "Độ dày " + inputDoDay + " đã tồn tại.");
                return false;
            }
        }
        return true;
    }

    boolean checkThietKeDaTonTai(String inputThietKe) {
        List<ThietKe> listTK = thietKeRepository.getAll();
        for (ThietKe tk : listTK) {
            if (tk.getTenTK().equals(inputThietKe)) {
                JOptionPane.showMessageDialog(this, "Thiết kế " + inputThietKe + " đã tồn tại.");
                return false;
            }
        }
        return true;
    }

    private void showQRCode(String data) {
        try {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(data, BarcodeFormat.QR_CODE, 200, 200);
            BufferedImage qrImage = MatrixToImageWriter.toBufferedImage(bitMatrix);
            JFrame qrFrame = new JFrame("QR Code");
            qrFrame.setSize(250, 250);
            qrFrame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            qrFrame.add(new JLabel(new ImageIcon(qrImage)));
            qrFrame.setVisible(true);
            qrFrame.setLocationRelativeTo(null);
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jProgressBar1 = new javax.swing.JProgressBar();
        jProgressBar2 = new javax.swing.JProgressBar();
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        txtTenSanPham = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtMaSP = new javax.swing.JTextField();
        txtTimKiemSanPham = new javax.swing.JTextField();
        btnLamMoiSanPham = new javax.swing.JButton();
        btnThemMoiSanPham = new javax.swing.JButton();
        btnChinhSuaSanPham = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();
        btnChinhSuaTrangThai = new javax.swing.JButton();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbDSSP = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        cboLoaiDem = new javax.swing.JComboBox<>();
        cboMauSac = new javax.swing.JComboBox<>();
        cboXuatXu = new javax.swing.JComboBox<>();
        cboKichThuoc = new javax.swing.JComboBox<>();
        cboDoCung = new javax.swing.JComboBox<>();
        cboThuongHieu = new javax.swing.JComboBox<>();
        cboDoDay = new javax.swing.JComboBox<>();
        cboThietKe = new javax.swing.JComboBox<>();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        txtMaCTSP = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        txtGiaBan = new javax.swing.JTextField();
        txtSoLuong = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        txtNamBH = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        btnThemNhanhLoaiDem = new javax.swing.JButton();
        btnThemNhanhXuatXu = new javax.swing.JButton();
        btnThemNhanhDoDay = new javax.swing.JButton();
        btnThemNhanhThuongHieu = new javax.swing.JButton();
        btnThemNhanhThietKe = new javax.swing.JButton();
        btnThemNhanhDoCung = new javax.swing.JButton();
        btnThemNhanhKichThuoc = new javax.swing.JButton();
        btnThemNhanhMauSac = new javax.swing.JButton();
        JDate = new com.toedter.calendar.JDateChooser();
        btnThemNhanhTenSP = new javax.swing.JButton();
        cboTenSanPham = new javax.swing.JComboBox<>();
        txtTimKiemCTSP = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        btnThemCTSP = new javax.swing.JButton();
        btnCapNhat = new javax.swing.JButton();
        btnXuatExcel = new javax.swing.JButton();
        btnLamMoiCTSP = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbCTSP = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        btnQRCode = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtMaThuocTinh = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtTenThuocTinh = new javax.swing.JTextField();
        rdoMauSac = new javax.swing.JRadioButton();
        rdoLoaiDem = new javax.swing.JRadioButton();
        rdoThuongHieu = new javax.swing.JRadioButton();
        rdoThietKe = new javax.swing.JRadioButton();
        rdoDoDay = new javax.swing.JRadioButton();
        rdoDoCung = new javax.swing.JRadioButton();
        rdoKichThuoc = new javax.swing.JRadioButton();
        rdoXuatXu = new javax.swing.JRadioButton();
        btnLamMoiThuocTinh = new javax.swing.JButton();
        btnThemThuocTinh = new javax.swing.JButton();
        btnChinhSuaThuocTinh = new javax.swing.JButton();
        btnXoaThuocTinh = new javax.swing.JButton();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbDSTT = new javax.swing.JTable();
        jLabel19 = new javax.swing.JLabel();

        jTabbedPane1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        txtTenSanPham.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel6.setText("Tên sản phẩm");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel5.setText("Mã sản phẩm");

        txtMaSP.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtMaSP.setEnabled(false);

        txtTimKiemSanPham.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtTimKiemSanPham.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemSanPhamKeyReleased(evt);
            }
        });

        btnLamMoiSanPham.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnLamMoiSanPham.setText("Làm mới");
        btnLamMoiSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiSanPhamActionPerformed(evt);
            }
        });

        btnThemMoiSanPham.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnThemMoiSanPham.setText("Thêm mới");
        btnThemMoiSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemMoiSanPhamActionPerformed(evt);
            }
        });

        btnChinhSuaSanPham.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnChinhSuaSanPham.setText("Chỉnh sửa");
        btnChinhSuaSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChinhSuaSanPhamActionPerformed(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel25.setText("Tìm kiếm");

        btnChinhSuaTrangThai.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnChinhSuaTrangThai.setText("Trạng thái");
        btnChinhSuaTrangThai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChinhSuaTrangThaiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(98, 98, 98)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel25)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5))
                        .addGap(42, 42, 42)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTenSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE)
                            .addComponent(txtTimKiemSanPham)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(237, 237, 237)
                        .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(101, 101, 101)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnLamMoiSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnChinhSuaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(58, 58, 58)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnChinhSuaTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThemMoiSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtTenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTimKiemSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel25)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnLamMoiSanPham)
                            .addComponent(btnThemMoiSanPham))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnChinhSuaSanPham)
                            .addComponent(btnChinhSuaTrangThai))))
                .addGap(0, 39, Short.MAX_VALUE))
        );

        jTabbedPane3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        tbDSSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "STT", "Mã sản phẩm", "Tên sản phẩm", "Trạng thái"
            }
        ));
        tbDSSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbDSSPMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbDSSP);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1036, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
        );

        jTabbedPane3.addTab("Danh sách sản phẩm", jPanel6);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTabbedPane3))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(126, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Sản phẩm", jPanel1);

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 16))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel1.setText("Loại đệm");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel11.setText("Màu sắc");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel12.setText("Xuất xứ");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel13.setText("Thương hiệu");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel14.setText("Kích thước");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel15.setText("Độ cứng");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel16.setText("Độ dày");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel17.setText("Thiết kế");

        cboLoaiDem.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        cboMauSac.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        cboXuatXu.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        cboKichThuoc.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        cboDoCung.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        cboThuongHieu.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        cboDoDay.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        cboThietKe.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel20.setText("Mã CTSP");

        jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel21.setText("Tên sản phẩm");

        txtMaCTSP.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtMaCTSP.setEnabled(false);

        jLabel22.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel22.setText("Số lượng");

        jLabel23.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel23.setText("Giá bán");

        txtGiaBan.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        txtSoLuong.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        jLabel24.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel24.setText("Năm bảo hành");

        txtNamBH.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        jLabel26.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel26.setText("Ngày sản xuất");

        btnThemNhanhLoaiDem.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnThemNhanhLoaiDem.setText("+");
        btnThemNhanhLoaiDem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThemNhanhLoaiDemMouseClicked(evt);
            }
        });

        btnThemNhanhXuatXu.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnThemNhanhXuatXu.setText("+");
        btnThemNhanhXuatXu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThemNhanhXuatXuMouseClicked(evt);
            }
        });

        btnThemNhanhDoDay.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnThemNhanhDoDay.setText("+");
        btnThemNhanhDoDay.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThemNhanhDoDayMouseClicked(evt);
            }
        });

        btnThemNhanhThuongHieu.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnThemNhanhThuongHieu.setText("+");
        btnThemNhanhThuongHieu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThemNhanhThuongHieuMouseClicked(evt);
            }
        });

        btnThemNhanhThietKe.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnThemNhanhThietKe.setText("+");
        btnThemNhanhThietKe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThemNhanhThietKeMouseClicked(evt);
            }
        });

        btnThemNhanhDoCung.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnThemNhanhDoCung.setText("+");
        btnThemNhanhDoCung.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThemNhanhDoCungMouseClicked(evt);
            }
        });

        btnThemNhanhKichThuoc.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnThemNhanhKichThuoc.setText("+");
        btnThemNhanhKichThuoc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThemNhanhKichThuocMouseClicked(evt);
            }
        });

        btnThemNhanhMauSac.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnThemNhanhMauSac.setText("+");
        btnThemNhanhMauSac.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThemNhanhMauSacMouseClicked(evt);
            }
        });

        btnThemNhanhTenSP.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnThemNhanhTenSP.setText("+");
        btnThemNhanhTenSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThemNhanhTenSPMouseClicked(evt);
            }
        });

        cboTenSanPham.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel21))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtMaCTSP, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
                            .addComponent(cboTenSanPham, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnThemNhanhTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel23)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(38, 38, 38)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel24)
                            .addComponent(jLabel26))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNamBH)
                            .addComponent(JDate, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(cboLoaiDem, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cboXuatXu, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnThemNhanhLoaiDem, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                            .addComponent(btnThemNhanhXuatXu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addGap(12, 12, 12)
                                .addComponent(cboThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cboDoDay, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnThemNhanhThuongHieu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnThemNhanhDoDay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17)
                            .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cboDoCung, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboThietKe, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(btnThemNhanhThietKe)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel11))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(btnThemNhanhDoCung)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel14)))
                        .addGap(13, 13, 13)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cboKichThuoc, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cboMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnThemNhanhKichThuoc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnThemNhanhMauSac, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(13, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel7Layout.createSequentialGroup()
                            .addGap(61, 61, 61)
                            .addComponent(btnThemNhanhTenSP))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                            .addGap(15, 15, 15)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel20)
                                .addComponent(txtMaCTSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel7Layout.createSequentialGroup()
                                    .addComponent(jLabel21)
                                    .addGap(6, 6, 6))
                                .addComponent(cboTenSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE))))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel22)
                                    .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel24)
                                    .addComponent(txtNamBH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel23)
                                    .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel26)))
                            .addComponent(JDate, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnThemNhanhThuongHieu)
                                .addComponent(jLabel15))
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cboThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel13)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cboDoCung)
                                    .addComponent(btnThemNhanhDoCung))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnThemNhanhThietKe)
                                    .addComponent(jLabel17)
                                    .addComponent(cboThietKe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnThemNhanhDoDay)
                                    .addComponent(cboDoDay)
                                    .addComponent(jLabel16)
                                    .addComponent(jLabel11)
                                    .addComponent(cboXuatXu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnThemNhanhXuatXu)
                                    .addComponent(jLabel12)
                                    .addComponent(cboMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnThemNhanhMauSac))
                                .addGap(34, 34, 34))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(cboKichThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel14))
                                    .addComponent(cboLoaiDem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1)
                                    .addComponent(btnThemNhanhLoaiDem)
                                    .addComponent(btnThemNhanhKichThuoc))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(14, 14, 14))))
        );

        txtTimKiemCTSP.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtTimKiemCTSP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemCTSPKeyReleased(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel18.setText("Tìm kiếm");

        btnThemCTSP.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnThemCTSP.setText("Thêm mới");
        btnThemCTSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemCTSPActionPerformed(evt);
            }
        });

        btnCapNhat.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnCapNhat.setText("Cập nhật");
        btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
            }
        });

        btnXuatExcel.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnXuatExcel.setText("Xuất excel");
        btnXuatExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatExcelActionPerformed(evt);
            }
        });

        btnLamMoiCTSP.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnLamMoiCTSP.setText("Làm mới");
        btnLamMoiCTSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiCTSPActionPerformed(evt);
            }
        });

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách thông tin sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 16))); // NOI18N

        tbCTSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã CTSP", "Tên CTSP", "Loại đệm", "Xuất xứ", "Độ cứng", "Độ dày", "Màu sắc", "Thương hiệu", "Thiết kế", "Kích thước", "Giá bán", "Số lượng", "Ngày sản xuất", "Năm BH"
            }
        ));
        tbCTSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbCTSPMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbCTSP);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1074, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jLabel10.setText("Thông tin sản phẩm");

        btnQRCode.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnQRCode.setText("QR Code");
        btnQRCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQRCodeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(457, 457, 457)
                .addComponent(jLabel10)
                .addGap(0, 468, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addGap(18, 18, 18)
                        .addComponent(txtTimKiemCTSP, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(62, 62, 62)
                        .addComponent(btnQRCode, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(btnThemCTSP, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(btnCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(btnLamMoiCTSP, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(btnXuatExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemCTSP)
                    .addComponent(jLabel18)
                    .addComponent(txtTimKiemCTSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCapNhat)
                    .addComponent(btnLamMoiCTSP)
                    .addComponent(btnXuatExcel)
                    .addComponent(btnQRCode))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Sản phẩm chi tiết", jPanel2);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel2.setText("Mã thuộc tính");

        txtMaThuocTinh.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtMaThuocTinh.setEnabled(false);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel3.setText("Tên thuộc tính");

        txtTenThuocTinh.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        buttonGroup2.add(rdoMauSac);
        rdoMauSac.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        rdoMauSac.setText("Màu sắc");
        rdoMauSac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoMauSacActionPerformed(evt);
            }
        });

        buttonGroup2.add(rdoLoaiDem);
        rdoLoaiDem.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        rdoLoaiDem.setText("Loại đệm");
        rdoLoaiDem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoLoaiDemActionPerformed(evt);
            }
        });

        buttonGroup2.add(rdoThuongHieu);
        rdoThuongHieu.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        rdoThuongHieu.setText("Thương hiệu");
        rdoThuongHieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoThuongHieuActionPerformed(evt);
            }
        });

        buttonGroup2.add(rdoThietKe);
        rdoThietKe.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        rdoThietKe.setText("Thiết kế");
        rdoThietKe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoThietKeActionPerformed(evt);
            }
        });

        buttonGroup2.add(rdoDoDay);
        rdoDoDay.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        rdoDoDay.setText("Độ dày");
        rdoDoDay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoDoDayActionPerformed(evt);
            }
        });

        buttonGroup2.add(rdoDoCung);
        rdoDoCung.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        rdoDoCung.setText("Độ cứng");
        rdoDoCung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoDoCungActionPerformed(evt);
            }
        });

        buttonGroup2.add(rdoKichThuoc);
        rdoKichThuoc.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        rdoKichThuoc.setText("Kích thước");
        rdoKichThuoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoKichThuocActionPerformed(evt);
            }
        });

        buttonGroup2.add(rdoXuatXu);
        rdoXuatXu.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        rdoXuatXu.setText("Xuất xứ");
        rdoXuatXu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoXuatXuActionPerformed(evt);
            }
        });

        btnLamMoiThuocTinh.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnLamMoiThuocTinh.setText("Làm mới");
        btnLamMoiThuocTinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiThuocTinhActionPerformed(evt);
            }
        });

        btnThemThuocTinh.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnThemThuocTinh.setText("Thêm mới");
        btnThemThuocTinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemThuocTinhActionPerformed(evt);
            }
        });

        btnChinhSuaThuocTinh.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnChinhSuaThuocTinh.setText("Chỉnh sửa");
        btnChinhSuaThuocTinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChinhSuaThuocTinhActionPerformed(evt);
            }
        });

        btnXoaThuocTinh.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnXoaThuocTinh.setText("Xóa");
        btnXoaThuocTinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaThuocTinhActionPerformed(evt);
            }
        });

        jTabbedPane2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        tbDSTT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "STT", "Mã thuộc tính", "Tên thuộc tính", "Trạng thái"
            }
        ));
        tbDSTT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbDSTTMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbDSTT);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1036, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 337, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("Danh sách thuộc tính", jPanel4);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1036, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 32, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtMaThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(txtTenThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnLamMoiThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnThemThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnChinhSuaThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnXoaThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(72, 72, 72)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rdoLoaiDem)
                                    .addComponent(rdoDoCung))
                                .addGap(54, 54, 54)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rdoMauSac)
                                    .addComponent(rdoDoDay))
                                .addGap(58, 58, 58)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rdoThuongHieu)
                                    .addComponent(rdoThietKe))
                                .addGap(47, 47, 47)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rdoKichThuoc)
                                    .addComponent(rdoXuatXu))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(rdoThuongHieu)
                                .addGap(18, 18, 18)
                                .addComponent(rdoThietKe))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(rdoXuatXu)
                                .addGap(18, 18, 18)
                                .addComponent(rdoKichThuoc))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(rdoMauSac)
                                .addGap(18, 18, 18)
                                .addComponent(rdoDoDay))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(rdoLoaiDem)
                                .addGap(18, 18, 18)
                                .addComponent(rdoDoCung)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnLamMoiThuocTinh)
                            .addComponent(btnThemThuocTinh)
                            .addComponent(btnChinhSuaThuocTinh)
                            .addComponent(btnXoaThuocTinh)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtMaThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtTenThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );

        jTabbedPane1.addTab("Thuộc tính sản phẩm", jPanel3);

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel19.setText("QUẢN LÝ SẢN PHẨM");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel19)
                .addGap(442, 442, 442))
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void rdoDoDayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoDoDayActionPerformed
        fillTableDoDay(doDayRepository.getAll());
    }//GEN-LAST:event_rdoDoDayActionPerformed

    private void rdoKichThuocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoKichThuocActionPerformed
        fillTableKichThuoc(kichThuocRepository.getAll());
    }//GEN-LAST:event_rdoKichThuocActionPerformed

    private void rdoXuatXuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoXuatXuActionPerformed
        fillTableXuatXu(xuatXuRepository.getAll());
    }//GEN-LAST:event_rdoXuatXuActionPerformed

    private void rdoThietKeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoThietKeActionPerformed
        fillTableThietKe(thietKeRepository.getAll());
    }//GEN-LAST:event_rdoThietKeActionPerformed

    private void rdoThuongHieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoThuongHieuActionPerformed
        fillTableThuongHieu(thuongHieuRepository.getAll());
    }//GEN-LAST:event_rdoThuongHieuActionPerformed

    private void rdoMauSacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoMauSacActionPerformed
        fillTableMauSac(mauSacRepository.getAll());
    }//GEN-LAST:event_rdoMauSacActionPerformed

    private void rdoDoCungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoDoCungActionPerformed
        fillTableDoCung(doCungRepository.getAll());
    }//GEN-LAST:event_rdoDoCungActionPerformed

    private void rdoLoaiDemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoLoaiDemActionPerformed
        fillTableLoaiDem(loaiDemRepository.getAll());
    }//GEN-LAST:event_rdoLoaiDemActionPerformed

    private void btnXoaThuocTinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaThuocTinhActionPerformed
        int rowSelected = this.tbDSTT.getSelectedRow();
        if (rowSelected != -1) {
            if (tbDSTT.getRowCount() > 1) {
                String idString = (String) tbDSTT.getValueAt(rowSelected, 1);
                int id = extractNumericId(idString);
                if (rdoDoCung.isSelected()) {
                    int confirm = JOptionPane.showConfirmDialog(this, "Bạn muốn xóa độ cứng đang chọn ?");
                    if (confirm == JOptionPane.YES_OPTION) {
                        String message = (doCungRepository.xoaDC(id) != 0) ? "Xóa độ cứng " + tbDSTT.getValueAt(rowSelected, 2).toString() + " thành công !" : "Xóa độ cứng " + tbDSTT.getValueAt(rowSelected, 2).toString() + " thất bại !";
                        JOptionPane.showMessageDialog(this, message);
                        fillTableDoCung(doCungRepository.getAll());
                        fillCboDoCung();
                        resetThuocTinh();
                    }
                } else if (rdoDoDay.isSelected()) {
                    int confirm = JOptionPane.showConfirmDialog(this, "Bạn muốn xóa độ dày đang chọn ?");
                    if (confirm == JOptionPane.YES_OPTION) {
                        String message = (doDayRepository.xoaDD(id) != 0) ? "Xóa độ dày " + tbDSTT.getValueAt(rowSelected, 2).toString() + " thành công !" : "Xóa độ dày " + tbDSTT.getValueAt(rowSelected, 2).toString() + " thất bại !";
                        JOptionPane.showMessageDialog(this, message);
                        fillTableDoDay(doDayRepository.getAll());
                        fillCboDoCung();
                        resetThuocTinh();
                    }
                } else if (rdoKichThuoc.isSelected()) {
                    int confirm = JOptionPane.showConfirmDialog(this, "Bạn muốn xóa kích thước đang chọn ?");
                    if (confirm == JOptionPane.YES_OPTION) {
                        String message = (kichThuocRepository.xoaKT(id) != 0) ? "Xóa kích thước " + tbDSTT.getValueAt(rowSelected, 2).toString() + " thành công !" : "Xóa kích thước " + tbDSTT.getValueAt(rowSelected, 2).toString() + " thất bại !";
                        JOptionPane.showMessageDialog(this, message);
                        fillTableKichThuoc(kichThuocRepository.getAll());
                        fillCboKichThuoc();
                        resetThuocTinh();
                    }
                } else if (rdoLoaiDem.isSelected()) {
                    int confirm = JOptionPane.showConfirmDialog(this, "Bạn muốn xóa loại đệm đang chọn ?");
                    if (confirm == JOptionPane.YES_OPTION) {
                        String message = (loaiDemRepository.xoaLD(id) != 0) ? "Xóa loại đệm " + tbDSTT.getValueAt(rowSelected, 2).toString() + " thành công !" : "Xóa loại đệm " + tbDSTT.getValueAt(rowSelected, 2).toString() + " thất bại !";
                        JOptionPane.showMessageDialog(this, message);
                        fillTableLoaiDem(loaiDemRepository.getAll());
                        fillCboLoaiDem();
                        resetThuocTinh();
                    }
                } else if (rdoMauSac.isSelected()) {
                    int confirm = JOptionPane.showConfirmDialog(this, "Bạn muốn xóa màu sắc đang chọn ?");
                    if (confirm == JOptionPane.YES_OPTION) {
                        String message = (mauSacRepository.xoaMS(id) != 0) ? "Xóa màu sắc " + tbDSTT.getValueAt(rowSelected, 2).toString() + " thành công !" : "Xóa màu sắc " + tbDSTT.getValueAt(rowSelected, 2).toString() + " thất bại !";
                        JOptionPane.showMessageDialog(this, message);
                        fillTableMauSac(mauSacRepository.getAll());
                        fillCboMauSac();
                        resetThuocTinh();
                    }
                } else if (rdoThietKe.isSelected()) {
                    int confirm = JOptionPane.showConfirmDialog(this, "Bạn muốn xóa thiết kế đang chọn ?");
                    if (confirm == JOptionPane.YES_OPTION) {
                        String message = (thietKeRepository.xoaTK(id) != 0) ? "Xóa thiết kế " + tbDSTT.getValueAt(rowSelected, 2).toString() + " thành công !" : "Xóa thiết kế " + tbDSTT.getValueAt(rowSelected, 2).toString() + " thất bại !";
                        JOptionPane.showMessageDialog(this, message);
                        fillTableThietKe(thietKeRepository.getAll());
                        fillCboThietKe();
                        resetThuocTinh();
                    }
                } else if (rdoThuongHieu.isSelected()) {
                    int confirm = JOptionPane.showConfirmDialog(this, "Bạn muốn xóa thương hiệu đang chọn ?");
                    if (confirm == JOptionPane.YES_OPTION) {
                        String message = (thuongHieuRepository.xoaTH(id) != 0) ? "Xóa thương hiệu " + tbDSTT.getValueAt(rowSelected, 2).toString() + " thành công !" : "Xóa thương hiệu " + tbDSTT.getValueAt(rowSelected, 2).toString() + " thất bại !";
                        JOptionPane.showMessageDialog(this, message);
                        fillTableThuongHieu(thuongHieuRepository.getAll());
                        fillCboThuongHieu();
                        resetThuocTinh();
                    }
                } else if (rdoXuatXu.isSelected()) {
                    int confirm = JOptionPane.showConfirmDialog(this, "Bạn muốn xóa xuất xứ đang chọn ?");
                    if (confirm == JOptionPane.YES_OPTION) {
                        String message = (xuatXuRepository.xoaXX(id) != 0) ? "Xóa xuất xứ " + tbDSTT.getValueAt(rowSelected, 2).toString() + " thành công !" : "Xóa xuất xứ " + tbDSTT.getValueAt(rowSelected, 2).toString() + " thất bại !";
                        JOptionPane.showMessageDialog(this, message);
                        fillTableXuatXu(xuatXuRepository.getAll());
                        fillCboXuatXu();
                        resetThuocTinh();
                    }
                }
            }
        } else if (rowSelected == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn thuộc tính muốn xóa !");
        } else if (tbDSTT.getRowCount() == 1) {
            JOptionPane.showMessageDialog(this, "Không được xóa thuộc tính cuối cùng !");
        }
    }//GEN-LAST:event_btnXoaThuocTinhActionPerformed
    private int extractNumericId(String idString) {
        String numericString = idString.replaceAll("\\D+", "");
        return Integer.parseInt(numericString);
    }
    private void btnChinhSuaThuocTinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChinhSuaThuocTinhActionPerformed
        int rowSelected = this.tbDSTT.getSelectedRow();
        String tenThuocTinh = txtTenThuocTinh.getText();
        if (rowSelected != -1) {
            if (rdoKichThuoc.isSelected()) {
                if (kiemTraThuocTinhRong() && kiemTraKichThuoc(tenThuocTinh) && checkKichThuocDaTonTai(tenThuocTinh)) {
                    int choose = JOptionPane.showConfirmDialog(this, "Bạn có muốn chỉnh sửa kích thước này ?", "Confirm", JOptionPane.YES_NO_OPTION);
                    if (choose == JOptionPane.NO_OPTION) {
                        return;
                    }
                    JOptionPane.showMessageDialog(this, "Update kích thước " + this.tbDSTT.getValueAt(rowSelected, 2) + " thành " + txtTenThuocTinh.getText().trim() + " thành công");
                    suaKichThuoc(rowSelected);
                    fillCboKichThuoc();
                    fillTableKichThuoc(kichThuocRepository.getAll());
                }
            }
            if (rdoDoCung.isSelected()) {
                if (kiemTraThuocTinhRong() && checkDoCungDaTonTai(tenThuocTinh) && kiemTraTenThuocTinhChuaChu(tenThuocTinh) && kiemTraThuocTinhQuaKyTu() && kiemTraDoDaiTenThuocTinh(tenThuocTinh)) {
                    int choose = JOptionPane.showConfirmDialog(this, "Bạn có muốn chỉnh sửa độ cứng này ?", "Confirm", JOptionPane.YES_NO_OPTION);
                    if (choose == JOptionPane.NO_OPTION) {
                        return;
                    }
                    JOptionPane.showMessageDialog(this, "Update độ cứng " + this.tbDSTT.getValueAt(rowSelected, 2) + " thành " + txtTenThuocTinh.getText().trim() + " thành công");
                    suaDoCung(rowSelected);
                    fillCboDoCung();
                    fillTableDoCung(doCungRepository.getAll());
                }
            }
            if (rdoDoDay.isSelected()) {
                if (kiemTraThuocTinhRong() && checkDoDayDaTonTai(tenThuocTinh) && kiemTraTenThuocTinhChuaSo(tenThuocTinh)) {
                    int choose = JOptionPane.showConfirmDialog(this, "Bạn có muốn chỉnh sửa độ dày này ?", "Confirm", JOptionPane.YES_NO_OPTION);
                    if (choose == JOptionPane.NO_OPTION) {
                        return;
                    }
                    JOptionPane.showMessageDialog(this, "Update độ dày " + this.tbDSTT.getValueAt(rowSelected, 2) + " thành " + txtTenThuocTinh.getText().trim() + " thành công");
                    suaDoDay(rowSelected);
                    fillCboDoDay();
                    fillTableDoDay(doDayRepository.getAll());
                }
            }
            if (rdoLoaiDem.isSelected()) {
                if (kiemTraThuocTinhRong() && checkLoaiDemDaTonTai(tenThuocTinh) && kiemTraTenThuocTinhChuaChu(tenThuocTinh) && kiemTraThuocTinhQuaKyTu() && kiemTraDoDaiTenThuocTinh(tenThuocTinh)) {
                    int choose = JOptionPane.showConfirmDialog(this, "Bạn có muốn chỉnh sửa loại đệm này ?", "Confirm", JOptionPane.YES_NO_OPTION);
                    if (choose == JOptionPane.NO_OPTION) {
                        return;
                    }
                    JOptionPane.showMessageDialog(this, "Update loại đệm " + this.tbDSTT.getValueAt(rowSelected, 2) + " thành " + txtTenThuocTinh.getText().trim() + " thành công");
                    suaLoaiDem(rowSelected);
                    fillCboLoaiDem();
                    fillTableLoaiDem(loaiDemRepository.getAll());
                }
            }
            if (rdoMauSac.isSelected()) {
                if (kiemTraThuocTinhRong() && checkMauSacDaTonTai(tenThuocTinh) && kiemTraTenThuocTinhChuaChu(tenThuocTinh) && kiemTraThuocTinhQuaKyTu() && kiemTraDoDaiTenThuocTinh(tenThuocTinh)) {
                    int choose = JOptionPane.showConfirmDialog(this, "Bạn có muốn chỉnh sửa màu sắc này ?", "Confirm", JOptionPane.YES_NO_OPTION);
                    if (choose == JOptionPane.NO_OPTION) {
                        return;
                    }
                    JOptionPane.showMessageDialog(this, "Update màu sắc " + this.tbDSTT.getValueAt(rowSelected, 2) + " thành " + txtTenThuocTinh.getText().trim() + " thành công");
                    suaMauSac(rowSelected);
                    fillCboDoDay();
                    fillTableDoDay(doDayRepository.getAll());
                }
            }
            if (rdoThietKe.isSelected()) {
                if (kiemTraThuocTinhRong() && checkThietKeDaTonTai(tenThuocTinh) && kiemTraTenThuocTinhChuaChu(tenThuocTinh) && kiemTraThuocTinhQuaKyTu() && kiemTraDoDaiTenThuocTinh(tenThuocTinh)) {
                    int choose = JOptionPane.showConfirmDialog(this, "Bạn có muốn chỉnh sửa thiết kế này ?", "Confirm", JOptionPane.YES_NO_OPTION);
                    if (choose == JOptionPane.NO_OPTION) {
                        return;
                    }
                    JOptionPane.showMessageDialog(this, "Update thiết kế " + this.tbDSTT.getValueAt(rowSelected, 2) + " thành " + txtTenThuocTinh.getText().trim() + " thành công");
                    suaThietKe(rowSelected);
                    fillCboThietKe();
                    fillTableThietKe(thietKeRepository.getAll());
                }
            }
            if (rdoThuongHieu.isSelected()) {
                if (kiemTraThuocTinhRong() && checkThuongHieuDaTonTai(tenThuocTinh) && kiemTraTenThuocTinhChuaChu(tenThuocTinh) && kiemTraThuocTinhQuaKyTu() && kiemTraDoDaiTenThuocTinh(tenThuocTinh)) {
                    int choose = JOptionPane.showConfirmDialog(this, "Bạn có muốn chỉnh sửa thương hiệu này ?", "Confirm", JOptionPane.YES_NO_OPTION);
                    if (choose == JOptionPane.NO_OPTION) {
                        return;
                    }
                    JOptionPane.showMessageDialog(this, "Update thương hiệu " + this.tbDSTT.getValueAt(rowSelected, 2) + " thành " + txtTenThuocTinh.getText().trim() + " thành công");
                    suaThuongHieu(rowSelected);
                    fillCboThuongHieu();
                    fillTableThuongHieu(thuongHieuRepository.getAll());
                }
            }
            if (rdoXuatXu.isSelected()) {
                if (kiemTraThuocTinhRong() && checkXuatXuDaTonTai(tenThuocTinh) && kiemTraTenThuocTinhChuaChu(tenThuocTinh) && kiemTraThuocTinhQuaKyTu() && kiemTraDoDaiTenThuocTinh(tenThuocTinh)) {
                    int choose = JOptionPane.showConfirmDialog(this, "Bạn có muốn chỉnh sửa xuất xứ này ?", "Confirm", JOptionPane.YES_NO_OPTION);
                    if (choose == JOptionPane.NO_OPTION) {
                        return;
                    }
                    JOptionPane.showMessageDialog(this, "Update xuất xứ " + this.tbDSTT.getValueAt(rowSelected, 2) + " thành " + txtTenThuocTinh.getText().trim() + " thành công");
                    suaDoDay(rowSelected);
                    fillCboXuatXu();
                    fillTableXuatXu(xuatXuRepository.getAll());
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Chỉnh sửa không thành công do không có dòng nào được chọn !");
        }

    }//GEN-LAST:event_btnChinhSuaThuocTinhActionPerformed

    private void btnThemThuocTinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemThuocTinhActionPerformed
        String tenThuocTinh = txtTenThuocTinh.getText();
        if (rdoDoCung.isSelected()) {
            if (kiemTraThuocTinhRong() && checkDoCungDaTonTai(tenThuocTinh) && kiemTraTenThuocTinhChuaChu(tenThuocTinh) && kiemTraThuocTinhQuaKyTu() && kiemTraDoDaiTenThuocTinh(tenThuocTinh)) {
                themDoCung(tenThuocTinh);
                fillCboDoCung();
                fillTableDoCung(doCungRepository.getAll());
            }
        } else if (rdoDoDay.isSelected()) {
            if (kiemTraThuocTinhRong() && checkDoDayDaTonTai(tenThuocTinh) && kiemTraTenThuocTinhChuaSo(tenThuocTinh)) {
                themDoDay(tenThuocTinh);
                fillCboDoDay();
                fillTableDoDay(doDayRepository.getAll());
            }
        } else if (rdoKichThuoc.isSelected()) {
            if (kiemTraThuocTinhRong() && kiemTraKichThuoc(tenThuocTinh) && checkKichThuocDaTonTai(tenThuocTinh)) {
                themKichThuoc(tenThuocTinh);
                fillCboKichThuoc();
                fillTableKichThuoc(kichThuocRepository.getAll());
            }
        } else if (rdoLoaiDem.isSelected()) {
            if (kiemTraThuocTinhRong() && checkLoaiDemDaTonTai(tenThuocTinh) && kiemTraTenThuocTinhChuaChu(tenThuocTinh) && kiemTraThuocTinhQuaKyTu() && kiemTraDoDaiTenThuocTinh(tenThuocTinh)) {
                themLoaiDem(tenThuocTinh);
                fillCboLoaiDem();
                fillTableLoaiDem(loaiDemRepository.getAll());
            }
        } else if (rdoMauSac.isSelected()) {
            if (kiemTraThuocTinhRong() && checkMauSacDaTonTai(tenThuocTinh) && kiemTraTenThuocTinhChuaChu(tenThuocTinh) && kiemTraThuocTinhQuaKyTu() && kiemTraDoDaiTenThuocTinh(tenThuocTinh)) {
                themMauSac(tenThuocTinh);
                fillCboMauSac();
                fillTableMauSac(mauSacRepository.getAll());
            }
        } else if (rdoThietKe.isSelected()) {
            if (kiemTraThuocTinhRong() && checkThietKeDaTonTai(tenThuocTinh) && kiemTraTenThuocTinhChuaChu(tenThuocTinh) && kiemTraThuocTinhQuaKyTu() && kiemTraDoDaiTenThuocTinh(tenThuocTinh)) {
                themThietKe(tenThuocTinh);
                fillCboThietKe();
                fillTableThietKe(thietKeRepository.getAll());
            }
        } else if (rdoThuongHieu.isSelected()) {
            if (kiemTraThuocTinhRong() && checkThuongHieuDaTonTai(tenThuocTinh) && kiemTraTenThuocTinhChuaChu(tenThuocTinh) && kiemTraThuocTinhQuaKyTu() && kiemTraDoDaiTenThuocTinh(tenThuocTinh)) {
                themThuongHieu(tenThuocTinh);
                fillCboThuongHieu();
                fillTableThuongHieu(thuongHieuRepository.getAll());
            }
        } else if (rdoXuatXu.isSelected()) {
            if (kiemTraThuocTinhRong() && checkXuatXuDaTonTai(tenThuocTinh) && kiemTraTenThuocTinhChuaChu(tenThuocTinh) && kiemTraThuocTinhQuaKyTu() && kiemTraDoDaiTenThuocTinh(tenThuocTinh)) {
                themXuatXu(tenThuocTinh);
                fillCboXuatXu();
                fillTableXuatXu(xuatXuRepository.getAll());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Thêm mới không thành công do chưa chọn thuộc tính !");
        }

    }//GEN-LAST:event_btnThemThuocTinhActionPerformed

    private void btnLamMoiThuocTinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiThuocTinhActionPerformed
        resetThuocTinh();
    }//GEN-LAST:event_btnLamMoiThuocTinhActionPerformed

    private void btnLamMoiSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiSanPhamActionPerformed
        resetSanPham();
    }//GEN-LAST:event_btnLamMoiSanPhamActionPerformed

    private void btnLamMoiCTSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiCTSPActionPerformed
        resetCTSP();
    }//GEN-LAST:event_btnLamMoiCTSPActionPerformed

    private void tbDSTTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDSTTMouseClicked
        int rowSelected = this.tbDSTT.getSelectedRow();
        if (model.getRowCount() > 0 && evt.getClickCount() == 1) {
            txtMaThuocTinh.setText(this.tbDSTT.getValueAt(rowSelected, 1).toString());
            txtTenThuocTinh.setText(this.tbDSTT.getValueAt(rowSelected, 2).toString());
        }
    }//GEN-LAST:event_tbDSTTMouseClicked

    private void tbDSSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDSSPMouseClicked
        int rowSelected = this.tbDSSP.getSelectedRow();
        if (model.getRowCount() > 0 && evt.getClickCount() == 1) {
            txtMaSP.setText(this.tbDSSP.getValueAt(rowSelected, 1).toString());
            txtTenSanPham.setText(this.tbDSSP.getValueAt(rowSelected, 2).toString());
            String trangThai = this.tbDSSP.getValueAt(rowSelected, 3).toString();
        }
    }//GEN-LAST:event_tbDSSPMouseClicked

    private void tbCTSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbCTSPMouseClicked
        int rowSelected = this.tbCTSP.getSelectedRow();
        if (model.getRowCount() > 0 && evt.getClickCount() == 1) {
            txtMaCTSP.setText(this.tbCTSP.getValueAt(rowSelected, 1).toString());
            cboTenSanPham.setSelectedItem(this.tbCTSP.getValueAt(rowSelected, 2).toString());
            cboLoaiDem.setSelectedItem(this.tbCTSP.getValueAt(rowSelected, 3).toString());
            cboXuatXu.setSelectedItem(this.tbCTSP.getValueAt(rowSelected, 4).toString());
            cboDoCung.setSelectedItem(this.tbCTSP.getValueAt(rowSelected, 5).toString());
            cboDoDay.setSelectedItem(this.tbCTSP.getValueAt(rowSelected, 6).toString());
            cboMauSac.setSelectedItem(this.tbCTSP.getValueAt(rowSelected, 7).toString());
            cboThuongHieu.setSelectedItem(this.tbCTSP.getValueAt(rowSelected, 8).toString());
            cboThietKe.setSelectedItem(this.tbCTSP.getValueAt(rowSelected, 9).toString());
            cboKichThuoc.setSelectedItem(this.tbCTSP.getValueAt(rowSelected, 10).toString());
            txtSoLuong.setText(this.tbCTSP.getValueAt(rowSelected, 12).toString());
            txtGiaBan.setText(this.tbCTSP.getValueAt(rowSelected, 11).toString());
            txtNamBH.setText(this.tbCTSP.getValueAt(rowSelected, 14).toString());
            String dateString = this.tbCTSP.getValueAt(rowSelected, 13).toString();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); // Adjusted format

            try {
                // Parsing the date string to a java.util.Date object
                java.util.Date utilDate = dateFormat.parse(dateString);
                // Converting java.util.Date to java.sql.Date
                java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                // Setting the date to JDate
                JDate.setDate(sqlDate);
            } catch (Exception e) {
                e.printStackTrace(); // Handle the exception as needed
            }
        }
    }//GEN-LAST:event_tbCTSPMouseClicked

    private void btnXuatExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatExcelActionPerformed
        if (tbCTSP.getRowCount() > 0) {
            try {
                XSSFWorkbook wordkbook = new XSSFWorkbook();
                XSSFSheet sheet = wordkbook.createSheet("ListCTSP");
                XSSFRow row = null;
                row = sheet.createRow(3);
                XSSFCell cell = row.createCell(0, CellType.STRING);
                cell.setCellValue("STT");
                cell = row.createCell(1, CellType.STRING);
                cell.setCellValue("Tên CTSP");
                cell = row.createCell(2, CellType.STRING);
                cell.setCellValue("Mã SPCT");
                cell = row.createCell(3, CellType.STRING);
                cell.setCellValue("Loại đệm");
                cell = row.createCell(4, CellType.STRING);
                cell.setCellValue("Xuất xứ");
                cell = row.createCell(5, CellType.STRING);
                cell.setCellValue("Độ cứng");
                cell = row.createCell(6, CellType.STRING);
                cell.setCellValue("Độ dày");
                cell = row.createCell(7, CellType.STRING);
                cell.setCellValue("Màu sắc");
                cell = row.createCell(8, CellType.STRING);
                cell.setCellValue("Thương hiệu");
                cell = row.createCell(9, CellType.STRING);
                cell.setCellValue("Thiết kế");
                cell = row.createCell(10, CellType.STRING);
                cell.setCellValue("Kích thước");
                cell = row.createCell(11, CellType.STRING);
                cell.setCellValue("Số lượng");
                cell = row.createCell(12, CellType.STRING);
                cell.setCellValue("Giá bán");
                cell = row.createCell(13, CellType.STRING);
                cell.setCellValue("Ngày sản xuất");
                cell = row.createCell(14, CellType.STRING);
                cell.setCellValue("Năm bảo hành");

                for (int i = 0; i < this.tbCTSP.getRowCount(); i++) {
                    row = sheet.createRow(5 + i);
                    cell = row.createCell(0, CellType.NUMERIC);
                    cell.setCellValue(String.valueOf(this.tbCTSP.getValueAt(i, 0)));
                    cell = row.createCell(1, CellType.STRING);
                    cell.setCellValue(String.valueOf(this.tbCTSP.getValueAt(i, 1)));
                    cell = row.createCell(2, CellType.STRING);
                    cell.setCellValue(String.valueOf(this.tbCTSP.getValueAt(i, 2)));
                    cell = row.createCell(3, CellType.STRING);
                    cell.setCellValue(String.valueOf(this.tbCTSP.getValueAt(i, 3)));
                    cell = row.createCell(4, CellType.STRING);
                    cell.setCellValue(String.valueOf(this.tbCTSP.getValueAt(i, 4)));
                    cell = row.createCell(5, CellType.STRING);
                    cell.setCellValue(String.valueOf(this.tbCTSP.getValueAt(i, 5)));
                    cell = row.createCell(6, CellType.STRING);
                    cell.setCellValue(String.valueOf(this.tbCTSP.getValueAt(i, 6)));
                    cell = row.createCell(7, CellType.STRING);
                    cell.setCellValue(String.valueOf(this.tbCTSP.getValueAt(i, 7)));
                    cell = row.createCell(8, CellType.STRING);
                    cell.setCellValue(String.valueOf(this.tbCTSP.getValueAt(i, 8)));
                    cell = row.createCell(9, CellType.NUMERIC);
                    cell.setCellValue(String.valueOf(this.tbCTSP.getValueAt(i, 9)));
                    cell = row.createCell(10, CellType.NUMERIC);
                    cell.setCellValue(String.valueOf(this.tbCTSP.getValueAt(i, 10)));
                    cell = row.createCell(11, CellType.NUMERIC);
                    cell.setCellValue(String.valueOf(this.tbCTSP.getValueAt(i, 11)));
                    cell = row.createCell(12, CellType.NUMERIC);
                    cell.setCellValue(String.valueOf(this.tbCTSP.getValueAt(i, 12)));
                    cell = row.createCell(13, CellType.NUMERIC);
                    cell.setCellValue(String.valueOf(this.tbCTSP.getValueAt(i, 13)));
                    cell = row.createCell(14, CellType.NUMERIC);
                    cell.setCellValue(String.valueOf(this.tbCTSP.getValueAt(i, 14)));
                }
                File f = new File("D:\\DA1_Fpoly\\XuatExcel\\ListCTSP.xlsx");
                try {
                    FileOutputStream fis = new FileOutputStream(f);
                    wordkbook.write(fis);
                    fis.close();
                    JOptionPane.showMessageDialog(this, "Xuất file chi tiết sản phẩm thành công !");
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();

                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Lỗi mở file !");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Xuất file thất bại do danh sách trống !");
        }
    }//GEN-LAST:event_btnXuatExcelActionPerformed

    private void btnThemCTSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemCTSPActionPerformed
        int choice = JOptionPane.showConfirmDialog(this, "Bạn có muốn thêm không?", "Thêm", JOptionPane.YES_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            SanPhamChiTiet spct = this.readFormSPCT();
            if (spct == null) {
                return;
            }

            // Kiểm tra số lượng
            int soLuong = spct.getSoLuong();
            if (soLuong <= 0 || soLuong > 500) {
                JOptionPane.showMessageDialog(this, "Số lượng phải lớn hơn 0 và không quá 500.");
                return;
            }

            // Kiểm tra giá bán
            double giaBan = spct.getGiaBan();
            if (giaBan <= 200000 || giaBan > 10000000) {
                JOptionPane.showMessageDialog(this, "Giá bán phải lớn hơn 200.000 VND và không quá 10.000.000 VND.");
                return;
            }

            // Kiểm tra năm bảo hành
            int namBaoHanh = spct.getNamBaoHanh();
            if (namBaoHanh <= 0 || namBaoHanh > 10) {
                JOptionPane.showMessageDialog(this, "Năm bảo hành phải lớn hơn 0 và không quá 10 năm.");
                return;
            }

            // Kiểm tra ngày sản xuất
            java.util.Date ngaySanXuatUtil = spct.getNgaySanXuat();
            java.sql.Date ngaySanXuat = new java.sql.Date(ngaySanXuatUtil.getTime());
            java.sql.Date hienTai = new java.sql.Date(System.currentTimeMillis());
            Calendar cal = Calendar.getInstance();
            cal.setTime(hienTai);
            cal.add(Calendar.YEAR, -3);
            java.sql.Date baNamTruoc = new java.sql.Date(cal.getTimeInMillis());
            if (ngaySanXuat.after(hienTai) || ngaySanXuat.before(baNamTruoc)) {
                JOptionPane.showMessageDialog(this, "Ngày sản xuất không được lớn hơn ngày hiện tại và không quá 3 năm về trước.");
                return;
            }

            // Thêm sản phẩm chi tiết vào cơ sở dữ liệu
            if (chiTietSanPhamRepository.themSanPhamChiTiet(spct) != 0) {
                fillTableDSCTSP(sanPhamRepository.fillCTSP());
                fillTableDoCung(listdc);
                fillTableDoDay(listdd);
                fillTableKichThuoc(listkt);
                fillTableLoaiDem(listld);
                fillTableMauSac(listms);
                fillTableThietKe(listtk);
                fillTableThuongHieu(listth);
                fillTableXuatXu(listxx);
                fillTableDSSP(sanPhamRepository.fillSP());
                JOptionPane.showMessageDialog(this, "Thêm thành công sản phẩm chi tiết !!!");
            }
        }
    }//GEN-LAST:event_btnThemCTSPActionPerformed

    private void btnThemMoiSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemMoiSanPhamActionPerformed
        int choose = JOptionPane.showConfirmDialog(this, "Bạn có muốn thêm thông tin sản phẩm mới?", "Confirm", JOptionPane.YES_NO_OPTION);
        if (choose == JOptionPane.NO_OPTION) {
            return;
        }
        SanPham sp = this.readForm();
        String tenSanPham = txtTenSanPham.getText();
        if (testData(tenSanPham) && checkSanPhamDaTonTai(tenSanPham)) {
            if (sanPhamRepository.getSP(sp.getId()) != null) {
                JOptionPane.showMessageDialog(this, "Thêm không thành công do trùng mã!");
            } else {
                if (sanPhamRepository.insertSanPham(sp) > 0) {
                    JOptionPane.showMessageDialog(this, "Thêm thông tin sản phẩm thành công!");
                    fillTableDSSP(sanPhamRepository.getAllSP(true));
                } else {
                    JOptionPane.showMessageDialog(this, "Thêm không thành công!");
                }
            }
        }
    }//GEN-LAST:event_btnThemMoiSanPhamActionPerformed

    private void btnChinhSuaSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChinhSuaSanPhamActionPerformed
        SanPham sp = this.readForm();
        index = tbDSSP.getSelectedRow();
        int choose = tbDSSP.getSelectedRow();
        if (choose == -1) {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn bản ghi nào !");
            return;
        }
        String productId = tbDSSP.getValueAt(index, 1).toString();
        String numericPart = productId.replaceAll("\\D+", "");
        int id = Integer.parseInt(numericPart);

        int choose1 = JOptionPane.showConfirmDialog(this, "Bạn có muốn chỉnh sửa thông tin sản phẩm?", "Confirm", JOptionPane.YES_NO_OPTION);
        if (choose1 == JOptionPane.NO_OPTION) {
            return;
        }
        String tenSanPham = txtTenSanPham.getText();
        if (testData(tenSanPham)) {
            int result = sanPhamRepository.updateSP(sp, id);
            if (result > 0) {
                JOptionPane.showMessageDialog(this, "Chỉnh sửa thành công sản phẩm!");
                fillTableDSSP(sanPhamRepository.getAll());
            } else {
                JOptionPane.showMessageDialog(this, "Chỉnh sửa thông tin sản phẩm không thành công!");
            }
        }
    }//GEN-LAST:event_btnChinhSuaSanPhamActionPerformed

    private void btnThemNhanhLoaiDemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemNhanhLoaiDemMouseClicked
        ThemNhanhThuocTinh.tenThuocTinh = "LoaiDem";
        openThemNhanh();
        fillCboLoaiDem();
        fillTableLoaiDem(loaiDemRepository.getAll());
    }//GEN-LAST:event_btnThemNhanhLoaiDemMouseClicked

    private void btnThemNhanhXuatXuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemNhanhXuatXuMouseClicked
        ThemNhanhThuocTinh.tenThuocTinh = "XuatXu";
        openThemNhanh();
        fillCboXuatXu();
        fillTableXuatXu(xuatXuRepository.getAll());
    }//GEN-LAST:event_btnThemNhanhXuatXuMouseClicked

    private void btnThemNhanhThuongHieuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemNhanhThuongHieuMouseClicked
        ThemNhanhThuocTinh.tenThuocTinh = "ThuongHieu";
        openThemNhanh();
        fillCboThuongHieu();
        fillTableThuongHieu(thuongHieuRepository.getAll());
    }//GEN-LAST:event_btnThemNhanhThuongHieuMouseClicked

    private void btnThemNhanhDoDayMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemNhanhDoDayMouseClicked
        ThemNhanhThuocTinh.tenThuocTinh = "DoDay";
        openThemNhanh();
        fillCboDoDay();
        fillTableDoDay(doDayRepository.getAll());
    }//GEN-LAST:event_btnThemNhanhDoDayMouseClicked

    private void btnThemNhanhDoCungMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemNhanhDoCungMouseClicked
        ThemNhanhThuocTinh.tenThuocTinh = "DoCung";
        openThemNhanh();
        fillCboDoCung();
        fillTableDoCung(doCungRepository.getAll());
    }//GEN-LAST:event_btnThemNhanhDoCungMouseClicked

    private void btnThemNhanhThietKeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemNhanhThietKeMouseClicked
        ThemNhanhThuocTinh.tenThuocTinh = "ThietKe";
        openThemNhanh();
        fillCboLoaiDem();
        fillTableThietKe(thietKeRepository.getAll());
    }//GEN-LAST:event_btnThemNhanhThietKeMouseClicked

    private void btnThemNhanhKichThuocMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemNhanhKichThuocMouseClicked
        ThemNhanhThuocTinh.tenThuocTinh = "KichThuoc";
        openThemNhanh();
        fillCboKichThuoc();
        fillTableKichThuoc(kichThuocRepository.getAll());
    }//GEN-LAST:event_btnThemNhanhKichThuocMouseClicked

    private void btnThemNhanhMauSacMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemNhanhMauSacMouseClicked
        ThemNhanhThuocTinh.tenThuocTinh = "MauSac";
        openThemNhanh();
        fillCboMauSac();
        fillTableMauSac(mauSacRepository.getAll());
    }//GEN-LAST:event_btnThemNhanhMauSacMouseClicked

    private void btnThemNhanhTenSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemNhanhTenSPMouseClicked
        ThemNhanhThuocTinh.tenThuocTinh = "SanPham";
        openThemNhanh();
        fillCboTenSanPham();
        fillTableDSSP(sanPhamRepository.fillSP());
    }//GEN-LAST:event_btnThemNhanhTenSPMouseClicked

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed
        index = tbCTSP.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn 1 sản phẩm để sửa!");
        } else {
            int choice = JOptionPane.showConfirmDialog(this, "Bạn có muốn sửa không?", "Sửa", JOptionPane.YES_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                String idStr = tbCTSP.getValueAt(index, 0).toString();
                int id = Integer.parseInt(idStr); // Chuyển đổi từ String sang Integer
                SanPhamChiTiet spct = this.readFormSPCT();
                // Kiểm tra số lượng
                int soLuong = spct.getSoLuong();
                if (soLuong <= 0 || soLuong > 500) {
                    JOptionPane.showMessageDialog(this, "Số lượng phải lớn hơn 0 và không quá 500.");
                    return;
                }

                // Kiểm tra giá bán
                double giaBan = spct.getGiaBan();
                if (giaBan <= 200000 || giaBan > 10000000) {
                    JOptionPane.showMessageDialog(this, "Giá bán phải lớn hơn 200.000 VND và không quá 10.000.000 VND.");
                    return;
                }

                // Kiểm tra năm bảo hành
                int namBaoHanh = spct.getNamBaoHanh();
                if (namBaoHanh <= 0 || namBaoHanh > 10) {
                    JOptionPane.showMessageDialog(this, "Năm bảo hành phải lớn hơn 0 và không quá 10 năm.");
                    return;
                }

                // Kiểm tra ngày sản xuất
                java.util.Date ngaySanXuatUtil = spct.getNgaySanXuat();
                java.sql.Date ngaySanXuat = new java.sql.Date(ngaySanXuatUtil.getTime());
                java.sql.Date hienTai = new java.sql.Date(System.currentTimeMillis());
                Calendar cal = Calendar.getInstance();
                cal.setTime(hienTai);
                cal.add(Calendar.YEAR, -3);
                java.sql.Date baNamTruoc = new java.sql.Date(cal.getTimeInMillis());
                if (ngaySanXuat.after(hienTai) || ngaySanXuat.before(baNamTruoc)) {
                    JOptionPane.showMessageDialog(this, "Ngày sản xuất không được lớn hơn ngày hiện tại và không quá 3 năm về trước.");
                    return;
                }
                chiTietSanPhamRepository.suaSanPhamChiTiet(spct, id);
                JOptionPane.showMessageDialog(this, "Cập nhật sản phẩm chi tiết thành công !!!");
                fillTableDSCTSP(sanPhamRepository.fillCTSP());
                fillTableDoCung(listdc);
                fillTableDoDay(listdd);
                fillTableKichThuoc(listkt);
                fillTableLoaiDem(listld);
                fillTableMauSac(listms);
                fillTableThietKe(listtk);
                fillTableThuongHieu(listth);
                fillTableXuatXu(listxx);
            }
        }
    }//GEN-LAST:event_btnCapNhatActionPerformed

    private void txtTimKiemSanPhamKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemSanPhamKeyReleased
        String search = txtTimKiemSanPham.getText();
        model = (DefaultTableModel) tbDSSP.getModel();
        search(search, model, tbDSSP);
    }//GEN-LAST:event_txtTimKiemSanPhamKeyReleased

    private void txtTimKiemCTSPKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemCTSPKeyReleased
        String search = txtTimKiemCTSP.getText();
        model = (DefaultTableModel) tbCTSP.getModel();
        search(search, model, tbCTSP);
    }//GEN-LAST:event_txtTimKiemCTSPKeyReleased

    private void btnChinhSuaTrangThaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChinhSuaTrangThaiActionPerformed
        int selectedRow = tbDSSP.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một sản phẩm trước khi cập nhật trạng thái.");
            return;
        }

        String idSPString = txtMaSP.getText();

        if (idSPString.length() < 3) {
            JOptionPane.showMessageDialog(this, "Mã sản phẩm không hợp lệ.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn cập nhật trạng thái của sản phẩm?", "Xác nhận", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            try {
                int idSP = Integer.parseInt(idSPString.substring(3));
                int result = chiTietSanPhamRepository.updateTrangThai(idSP);
                if (result > 0) {
                    JOptionPane.showMessageDialog(this, "Đã cập nhật trạng thái sản phẩm thành hết hàng.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    fillTableDSSP(sanPhamRepository.fillSP());
                } else {
                    JOptionPane.showMessageDialog(this, "Cập nhật trạng thái thất bại.", "Thông báo", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Mã sản phẩm không hợp lệ.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnChinhSuaTrangThaiActionPerformed

    private void btnQRCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQRCodeActionPerformed
        int selectedRowSPCT = tbCTSP.getSelectedRow();
        if (selectedRowSPCT != -1) {
            String maCTSP = model.getValueAt(selectedRowSPCT, 1).toString();
            showQRCode(maCTSP);
        } else {
            JOptionPane.showMessageDialog(null, "Hãy chọn một sản phẩm trong bảng.");
        }
    }//GEN-LAST:event_btnQRCodeActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser JDate;
    private javax.swing.JButton btnCapNhat;
    private javax.swing.JButton btnChinhSuaSanPham;
    private javax.swing.JButton btnChinhSuaThuocTinh;
    private javax.swing.JButton btnChinhSuaTrangThai;
    private javax.swing.JButton btnLamMoiCTSP;
    private javax.swing.JButton btnLamMoiSanPham;
    private javax.swing.JButton btnLamMoiThuocTinh;
    private javax.swing.JButton btnQRCode;
    private javax.swing.JButton btnThemCTSP;
    private javax.swing.JButton btnThemMoiSanPham;
    private javax.swing.JButton btnThemNhanhDoCung;
    private javax.swing.JButton btnThemNhanhDoDay;
    private javax.swing.JButton btnThemNhanhKichThuoc;
    private javax.swing.JButton btnThemNhanhLoaiDem;
    private javax.swing.JButton btnThemNhanhMauSac;
    private javax.swing.JButton btnThemNhanhTenSP;
    private javax.swing.JButton btnThemNhanhThietKe;
    private javax.swing.JButton btnThemNhanhThuongHieu;
    private javax.swing.JButton btnThemNhanhXuatXu;
    private javax.swing.JButton btnThemThuocTinh;
    private javax.swing.JButton btnXoaThuocTinh;
    private javax.swing.JButton btnXuatExcel;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.JComboBox<String> cboDoCung;
    private javax.swing.JComboBox<String> cboDoDay;
    private javax.swing.JComboBox<String> cboKichThuoc;
    private javax.swing.JComboBox<String> cboLoaiDem;
    private javax.swing.JComboBox<String> cboMauSac;
    private javax.swing.JComboBox<String> cboTenSanPham;
    private javax.swing.JComboBox<String> cboThietKe;
    private javax.swing.JComboBox<String> cboThuongHieu;
    private javax.swing.JComboBox<String> cboXuatXu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JProgressBar jProgressBar2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JRadioButton rdoDoCung;
    private javax.swing.JRadioButton rdoDoDay;
    private javax.swing.JRadioButton rdoKichThuoc;
    private javax.swing.JRadioButton rdoLoaiDem;
    private javax.swing.JRadioButton rdoMauSac;
    private javax.swing.JRadioButton rdoThietKe;
    private javax.swing.JRadioButton rdoThuongHieu;
    private javax.swing.JRadioButton rdoXuatXu;
    private javax.swing.JTable tbCTSP;
    private javax.swing.JTable tbDSSP;
    private javax.swing.JTable tbDSTT;
    private javax.swing.JTextField txtGiaBan;
    private javax.swing.JTextField txtMaCTSP;
    private javax.swing.JTextField txtMaSP;
    private javax.swing.JTextField txtMaThuocTinh;
    private javax.swing.JTextField txtNamBH;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTenSanPham;
    private javax.swing.JTextField txtTenThuocTinh;
    private javax.swing.JTextField txtTimKiemCTSP;
    private javax.swing.JTextField txtTimKiemSanPham;
    // End of variables declaration//GEN-END:variables
}
