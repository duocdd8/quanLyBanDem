package com.raven.form;

import com.raven.classmodel.GetChucVu;
import com.raven.classmodel.PhieuGiamGia;
import com.raven.repository.PhieuGiamGiaRepository;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Hoai Thu
 */
public class FormPhieuGiamGia extends javax.swing.JPanel {

    private PhieuGiamGiaRepository pggRepo = new PhieuGiamGiaRepository();
    private DefaultTableModel model = new DefaultTableModel();
    private int index = -1;
    DecimalFormat formatter = new DecimalFormat("###,###,###");

    private PhieuGiamGiaRepository repository;

    public FormPhieuGiamGia() {
        initComponents();
        repository = new PhieuGiamGiaRepository();
        loadTableData(pggRepo.getAllKM());
        addCboPhanTramGiam();
        if (GetChucVu.getChucVu() == 0) {
            btnThem.setEnabled(true);
            btnSua.setEnabled(true);
            btnLamMoi.setEnabled(true);
        } else if (GetChucVu.getChucVu() == 1) {
            btnThem.setEnabled(false);
            btnSua.setEnabled(true);
            btnLamMoi.setEnabled(true);
        }
    }

    void addCboPhanTramGiam() {
        cboPhanTramGiam.removeAllItems();
        cboPhanTramGiam.addItem("15%");
        cboPhanTramGiam.addItem("20%");
        cboPhanTramGiam.addItem("25%");
        cboPhanTramGiam.addItem("30%");
        cboPhanTramGiam.addItem("35%");
        cboPhanTramGiam.addItem("50%");
        cboPhanTramGiam.addItem("75%");
    }

    private void loadTableData(List<PhieuGiamGia> listpgg) {
        model = (DefaultTableModel) tbPhieuGiamGia.getModel();
        model.setRowCount(0);
        listpgg = pggRepo.getAllKM();
        DecimalFormat format = new DecimalFormat("###,###,###");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        int i = 1;
        for (PhieuGiamGia pgg : listpgg) {
            // Assuming `getSoLuong` returns the quantity and we skip those with 0 or less
            if (pgg.getSoHoaDonApDung() > 0) {
                capNhatTrangThaiCacPhieu(listpgg);
                Object row[] = {
                    i++,
                    "PGG00" + pgg.getId(),
                    pgg.getTenPhieuGiamGia(),
                    String.format("%.0f%%", pgg.getPhanTramGiamGia()),
                    sdf.format(pgg.getNgayBatDau()),
                    sdf.format(pgg.getNgayKetThuc()),
                    format.format(pgg.getGiaTriToiThieu()),
                    pgg.getSoHoaDonApDung(),
                    pgg.getMoTa(),
                    pgg.getTrangThaiHienThi()
                };

                model.addRow(row);
            }
        }

    }

    public void show(int index) {
        PhieuGiamGia pgg = pggRepo.getAllKM().get(index);
        txtMaPGG.setText("PGG00" + pgg.getId());
        txtTenPGG.setText(pgg.getTenPhieuGiamGia());
        cboPhanTramGiam.setSelectedItem(pgg.getPhanTramGiamGia());
        if (pgg.getNgayBatDau() != null) {
            JDateNgayBD.setDate(pgg.getNgayBatDau());
        }
        if (pgg.getNgayKetThuc() != null) {
            JDateNgayKT.setDate(pgg.getNgayKetThuc());
        }
        txtGiaTriToiThieu.setText(Double.valueOf(pgg.getGiaTriToiThieu()).toString());
        txtSoHoaDonApDung.setText(Integer.valueOf(pgg.getSoHoaDonApDung()).toString());
        txtMoTa.setText(pgg.getMoTa());
    }

    public PhieuGiamGia readForm() {
        try {
            String tenPGG = txtTenPGG.getText();

            // Xử lý phần trăm giảm
            Float phanTramGiam = null;
            String selectedItem = cboPhanTramGiam.getSelectedItem().toString();
            if (selectedItem != null && !selectedItem.isEmpty()) {
                selectedItem = selectedItem.replace("%", "").trim();
                phanTramGiam = Float.parseFloat(selectedItem);
            } else {
                JOptionPane.showMessageDialog(null, "Phần trăm giảm không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return null;
            }

            // Xử lý ngày bắt đầu và ngày kết thúc
            java.util.Date utilNgayBD = JDateNgayBD.getDate();
            java.util.Date utilNgayKT = JDateNgayKT.getDate();
            if (utilNgayBD == null || utilNgayKT == null) {
                JOptionPane.showMessageDialog(null, "Ngày bắt đầu hoặc ngày kết thúc không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return null;
            }
            Date ngayBD = new Date(utilNgayBD.getTime());
            Date ngayKT = new Date(utilNgayKT.getTime());

            // Xử lý giá trị tối thiểu
            double giaTriToiThieu = 0.0;
            if (!txtGiaTriToiThieu.getText().isEmpty()) {
                giaTriToiThieu = Double.parseDouble(txtGiaTriToiThieu.getText().replace(",", ""));
            } else {
                JOptionPane.showMessageDialog(null, "Giá trị tối thiểu không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return null;
            }

            // Xử lý số hóa đơn áp dụng
            int soHoaDonApDung = 0;
            if (!txtSoHoaDonApDung.getText().isEmpty()) {
                soHoaDonApDung = Integer.parseInt(txtSoHoaDonApDung.getText());
            } else {
                JOptionPane.showMessageDialog(null, "Số hóa đơn áp dụng không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return null;
            }

            String moTa = txtMoTa.getText();

            return new PhieuGiamGia(tenPGG, phanTramGiam, ngayBD, ngayKT, giaTriToiThieu, soHoaDonApDung, moTa);

        } catch (NumberFormatException | NullPointerException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
//    public PhieuGiamGia readForm() {
//        String tenPGG = txtTenPGG.getText();
//        Float phanTramGiam = null;
//        String selectedItem = cboPhanTramGiam.getSelectedItem().toString();
//        Date ngayBD = (Date) JDateNgayBD.getDate();
//        Date ngayKT = (Date) JDateNgayBD.getDate();
//        Double giaTriToiThieu = Double.parseDouble(txtGiaTriToiThieu.getText().toString());
//        int soHoaDonApDung = Integer.parseInt(txtSoHoaDonApDung.getText().toString());
//        String moTa = txtMoTa.getText();
//        return new PhieuGiamGia(tenPGG, phanTramGiam, ngayBD, ngayKT, giaTriToiThieu, soHoaDonApDung, moTa);
//    }

//    public PhieuGiamGia readForm() {
//        try {
//            String tenPGG = txtTenPGG.getText();
//
//            // Xử lý phần trăm giảm
//            Float phanTramGiam = null;
//            String selectedItem = cboPhanTramGiam.getSelectedItem().toString();
//            if (selectedItem != null && !selectedItem.isEmpty()) {
//                selectedItem = selectedItem.replace("%", "").trim();
//                phanTramGiam = Float.parseFloat(selectedItem);
//            } else {
//                throw new NullPointerException("Phần trăm giảm không được để trống!");
//            }
//
//            // Xử lý ngày bắt đầu và ngày kết thúc
//            java.util.Date utilNgayBD = JDateNgayBD.getDate();
//            java.util.Date utilNgayKT = JDateNgayKT.getDate();
//            if (utilNgayBD == null || utilNgayKT == null) {
//                throw new NullPointerException("Ngày bắt đầu hoặc ngày kết thúc không được để trống!");
//            }
//            Date ngayBD = new Date(utilNgayBD.getTime());
//            Date ngayKT = new Date(utilNgayKT.getTime());
//
//            // Xử lý giá trị tối thiểu
//            double giaTriToiThieu = 0.0;
//            if (!txtGiaTriToiThieu.getText().isEmpty()) {
//                giaTriToiThieu = Double.parseDouble(txtGiaTriToiThieu.getText().replace(",", ""));
//            } else {
//                throw new NullPointerException("Giá trị tối thiểu không được để trống!");
//            }
//
//            // Xử lý số hóa đơn áp dụng
//            int soHoaDonApDung = 0;
//            if (!txtSoHoaDonApDung.getText().isEmpty()) {
//                soHoaDonApDung = Integer.parseInt(txtSoHoaDonApDung.getText());
//            } else {
//                throw new NullPointerException("Số hóa đơn áp dụng không được để trống!");
//            }
//
//            String moTa = txtMoTa.getText();
//
//            return new PhieuGiamGia(tenPGG, phanTramGiam, ngayBD, ngayKT, giaTriToiThieu, soHoaDonApDung, moTa);
//
//        } catch (NumberFormatException | NullPointerException ex) {
//            ex.printStackTrace();
//            return null;
//        }
//    }
    public void clearFrom() {
        txtMaPGG.setText("");
        txtTenPGG.setText("");
        cboPhanTramGiam.setSelectedIndex(0);
        JDateNgayBD.setDate(null);
        JDateNgayKT.setDate(null);
        txtGiaTriToiThieu.setText("");
        txtSoHoaDonApDung.setText("");
        txtMoTa.setText("");

    }

    private void capNhatTrangThaiCacPhieu(List<PhieuGiamGia> danhSach) {
        for (int i = 0; i < danhSach.size(); i++) {
            PhieuGiamGia pgg = danhSach.get(i);
            pgg.capNhatTrangThai();
            try {
                repository.updateTrangThaiPhieuGiamGia(pgg);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public void search(String str, DefaultTableModel model, JTable table) {
        model = (DefaultTableModel) tbPhieuGiamGia.getModel();
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

    public boolean kiemTraTenPGGDaTonTai(String TenPGG) {
        List<String> dstenPGG = pggRepo.layTenPGGDaTonTai(); // Lấy danh sách số điện thoại từ cơ sở dữ liệu
        return dstenPGG.contains(TenPGG);
    }

    private boolean testData() {
        if (txtTenPGG.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không để trống tên phiếu giảm giá!");
            return false;
        } else if (!txtTenPGG.getText().matches("^[a-zA-Z0-9\\p{L}\\s]+$")) {
            JOptionPane.showMessageDialog(this, "Tên phiếu giảm giá không được chứa kí tự đặc biệt!");
            return false;
        } else if (txtTenPGG.getText().matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "Tên phiếu giảm giá không được là số!");
            return false;
        } else if (txtTenPGG.getText().length() > 250) {
            JOptionPane.showMessageDialog(this, "Tên phiếu giảm giá không được dài quá 250 ký tự!");
            return false;
        } else if (kiemTraTenPGGDaTonTai(txtTenPGG.getText())) {
            JOptionPane.showMessageDialog(this, "Tên phiếu giảm giá đã tồn tại!");
            return false;
        }
        if (JDateNgayBD.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Không để trống ngày bắt đầu!");
            return false;
        }
        if (JDateNgayKT.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Không để trống ngày kết thúc!");
            return false;
        }
        if (JDateNgayBD.getDate().after(JDateNgayKT.getDate())) {
            JOptionPane.showMessageDialog(this, "Ngày bắt đầu phải trước ngày kết thúc!");
            return false;
        }
        long thoiGianGiuaHaiNgay = Math.abs(JDateNgayKT.getDate().getTime() - JDateNgayBD.getDate().getTime());
        long thoiGianTrongNgay = TimeUnit.DAYS.convert(thoiGianGiuaHaiNgay, TimeUnit.MILLISECONDS);
        if (thoiGianTrongNgay > 30) {
            JOptionPane.showMessageDialog(this, "Khoảng thời gian giữa ngày bắt đầu và ngày kết thúc không được quá 1 tháng!");
            return false;
        }

        DecimalFormat formatter = new DecimalFormat("###,###,###");

        if (txtGiaTriToiThieu.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không để trống giá trị tối thiểu của phiếu giảm giá!");
            return false;
        } else {
            String giaTriToiThieuStr = txtGiaTriToiThieu.getText();
            try {
                // Parse the string to a number using the formatter
                Number number = formatter.parse(giaTriToiThieuStr);
                double giaTriToiThieu = number.doubleValue();

                if (giaTriToiThieu <= 200000 || giaTriToiThieu >= 1000000) {
                    String formattedValue = formatter.format(giaTriToiThieu);
                    JOptionPane.showMessageDialog(this, "Giá trị tối thiểu của phiếu giảm giá phải lớn hơn 200.000 và nhỏ hơn 1.000.000! Giá trị hiện tại: " + formattedValue);
                    return false;
                }
            } catch (ParseException e) {
                JOptionPane.showMessageDialog(this, "Giá trị tối thiểu của phiếu giảm giá phải là một số và không chứa ký tự đặc biệt!");
                return false;
            }
        }
        if (txtSoHoaDonApDung.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không để trống số hóa đơn áp dụng của phiếu giảm giá!");
            return false;
        } else {
            String soHoaDonApDungStr = txtSoHoaDonApDung.getText();
            if (!soHoaDonApDungStr.matches("\\d+")) {  // Chỉ chứa chữ số
                JOptionPane.showMessageDialog(this, "Số hóa đơn áp dụng của phiếu giảm giá phải là một số và không chứa ký tự đặc biệt!");
                return false;
            }
            try {
                int soHoaDonApDung = Integer.parseInt(soHoaDonApDungStr);
                if (soHoaDonApDung < 0 || soHoaDonApDung > 30) {
                    JOptionPane.showMessageDialog(this, "Số hóa đơn áp dụng của phiếu giảm giá phải lớn hơn 0 và nhỏ hơn 30!");
                    return false;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Số hóa đơn áp dụng của phiếu giảm giá phải là một số!");
                return false;
            }
        }
        return true;
    }

    boolean checkTenPGGDaTonTai(String inputTenPGG) {
        List<PhieuGiamGia> listSP = pggRepo.getAllKM();
        for (PhieuGiamGia sp : listSP) {
            if (sp.getTenPhieuGiamGia().equals(inputTenPGG)) {
                JOptionPane.showMessageDialog(this, "Tên sản phẩm " + inputTenPGG + " đã tồn tại.");
                return false;
            }
        }
        return true;
    }

    private boolean checkUpdate() {
        if (txtTenPGG.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không để trống tên phiếu giảm giá!");
            return false;
        }
        if (!txtTenPGG.getText().matches("^[a-zA-Z0-9\\p{L}\\s]+$")) {
            JOptionPane.showMessageDialog(this, "Tên phiếu giảm giá không được chứa kí tự đặc biệt!");
            return false;
        }
        if (txtTenPGG.getText().matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "Tên phiếu giảm giá không được là số!");
            return false;
        } else if (txtTenPGG.getText().length() > 250) {
            JOptionPane.showMessageDialog(this, "Tên phiếu giảm giá không được dài quá 250 ký tự!");
            return false;
        }
        if (JDateNgayBD.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Không để trống ngày bắt đầu!");
            return false;
        }
        if (JDateNgayKT.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Không để trống ngày kết thúc!");
            return false;
        }
        if (JDateNgayBD.getDate().after(JDateNgayKT.getDate())) {
            JOptionPane.showMessageDialog(this, "Ngày bắt đầu phải trước ngày kết thúc!");
            return false;
        }
        long thoiGianGiuaHaiNgay = Math.abs(JDateNgayKT.getDate().getTime() - JDateNgayBD.getDate().getTime());
        long thoiGianTrongNgay = TimeUnit.DAYS.convert(thoiGianGiuaHaiNgay, TimeUnit.MILLISECONDS);
        if (thoiGianTrongNgay > 30) {
            JOptionPane.showMessageDialog(this, "Khoảng thời gian giữa ngày bắt đầu và ngày kết thúc không được quá 1 tháng!");
            return false;
        }

        DecimalFormat formatter = new DecimalFormat("###,###,###");

        if (txtGiaTriToiThieu.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không để trống giá trị tối thiểu của phiếu giảm giá!");
            return false;
        } else {
            String giaTriToiThieuStr = txtGiaTriToiThieu.getText();
            try {
                // Parse the string to a number using the formatter
                Number number = formatter.parse(giaTriToiThieuStr);
                double giaTriToiThieu = number.doubleValue();

                if (giaTriToiThieu <= 200000 || giaTriToiThieu >= 1000000) {
                    String formattedValue = formatter.format(giaTriToiThieu);
                    JOptionPane.showMessageDialog(this, "Giá trị tối thiểu của phiếu giảm giá phải lớn hơn 200.000 và nhỏ hơn 1.000.000! Giá trị hiện tại: " + formattedValue);
                    return false;
                }
            } catch (ParseException e) {
                JOptionPane.showMessageDialog(this, "Giá trị tối thiểu của phiếu giảm giá phải là một số và không chứa ký tự đặc biệt!");
                return false;
            }
        }
        if (txtSoHoaDonApDung.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không để trống số hóa đơn áp dụng của phiếu giảm giá!");
            return false;
        } else {
            String soHoaDonApDungStr = txtSoHoaDonApDung.getText();
            if (!soHoaDonApDungStr.matches("\\d+")) {  // Chỉ chứa chữ số
                JOptionPane.showMessageDialog(this, "Số hóa đơn áp dụng của phiếu giảm giá phải là một số và không chứa ký tự đặc biệt!");
                return false;
            }
            try {
                int soHoaDonApDung = Integer.parseInt(soHoaDonApDungStr);
                if (soHoaDonApDung < 0 || soHoaDonApDung > 30) {
                    JOptionPane.showMessageDialog(this, "Số hóa đơn áp dụng của phiếu giảm giá phải lớn hơn 0 và nhỏ hơn 30!");
                    return false;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Số hóa đơn áp dụng của phiếu giảm giá phải là một số!");
                return false;
            }
        }
        return true;
    }

    public boolean kiemTraTen(String inputTen) {
        if (inputTen.length() < 6) {
            JOptionPane.showMessageDialog(this, "Tên phiếu giảm giá tối thiểu phải có 6 kí tự.");
            return false;
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        txtTenPGG = new javax.swing.JTextField();
        txtMoTa = new javax.swing.JTextField();
        txtGiaTriToiThieu = new javax.swing.JTextField();
        txtSoHoaDonApDung = new javax.swing.JTextField();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnLamMoi = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtMaPGG = new javax.swing.JTextField();
        JDateNgayBD = new com.toedter.calendar.JDateChooser();
        JDateNgayKT = new com.toedter.calendar.JDateChooser();
        cboPhanTramGiam = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        btnTimKiem = new javax.swing.JButton();
        dateNbd = new com.toedter.calendar.JDateChooser();
        dateNkt = new com.toedter.calendar.JDateChooser();
        btnHuyLoc = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbPhieuGiamGia = new javax.swing.JTable();

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 26)); // NOI18N
        jLabel1.setText("QUẢN LÝ PHIẾU GIẢM GIÁ");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel14.setText("Tên PGG");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel16.setText("Ngày bắt đầu");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel17.setText("Ngày kết thúc");

        jLabel23.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel23.setText("Mô tả");

        jLabel24.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel24.setText("Số hóa đơn áp dụng");

        jLabel25.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel25.setText("Giá trị tối thiểu");

        txtTenPGG.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        txtMoTa.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        txtGiaTriToiThieu.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        txtSoHoaDonApDung.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        btnThem.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnThem.setText("Thêm mới");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnSua.setText("Cập nhật");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnLamMoi.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnLamMoi.setText("Làm mới");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel3.setText("Phần trăm giảm");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel15.setText("Mã PGG");

        txtMaPGG.setEditable(false);
        txtMaPGG.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtMaPGG.setEnabled(false);

        JDateNgayBD.setFocusable(false);
        JDateNgayBD.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        JDateNgayKT.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        cboPhanTramGiam.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(158, 158, 158)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(JDateNgayBD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(JDateNgayKT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtMaPGG, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel16)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel17))
                                .addGap(31, 31, 31)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cboPhanTramGiam, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTenPGG, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel24)
                    .addComponent(jLabel23)
                    .addComponent(jLabel25))
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtGiaTriToiThieu, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSoHoaDonApDung, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMoTa, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(107, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnThem)
                .addGap(82, 82, 82)
                .addComponent(btnSua)
                .addGap(83, 83, 83)
                .addComponent(btnLamMoi)
                .addGap(303, 303, 303))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMaPGG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25)
                    .addComponent(txtGiaTriToiThieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSoHoaDonApDung, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel23)
                            .addComponent(txtMoTa, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTenPGG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(cboPhanTramGiam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(JDateNgayBD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel17)
                            .addComponent(JDateNgayKT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem)
                    .addComponent(btnLamMoi)
                    .addComponent(btnSua))
                .addGap(18, 18, 18))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel2.setText("Tìm kiếm");

        txtSearch.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel20.setText("Ngày bắt đầu");

        jLabel22.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel22.setText("Ngày kết thúc");

        btnTimKiem.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnTimKiem.setText("Lọc");
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });

        btnHuyLoc.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnHuyLoc.setText("Hủy");
        btnHuyLoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyLocActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20)
                    .addComponent(jLabel2))
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(dateNbd, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(93, 93, 93)
                        .addComponent(jLabel22)
                        .addGap(18, 18, 18)
                        .addComponent(dateNkt, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtSearch))
                .addGap(39, 39, 39)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnTimKiem)
                    .addComponent(btnHuyLoc))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(5, 5, 5)
                            .addComponent(jLabel20))
                        .addComponent(dateNbd, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addComponent(dateNkt, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimKiem))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnHuyLoc))
                    .addComponent(jLabel2))
                .addGap(0, 9, Short.MAX_VALUE))
        );

        tbPhieuGiamGia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã PGG", "Tên PGG", "Phần trăm giảm", "Ngày bắt đầu", "Ngày kết thúc", "Giá trị tối thiểu", "Số hóa đơn áp dụng", "Mô tả", "Trạng thái"
            }
        ));
        tbPhieuGiamGia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPhieuGiamGiaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbPhieuGiamGia);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(381, 381, 381)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1))))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        int choose = JOptionPane.showConfirmDialog(this, "Bạn có muốn thêm phiếu giảm giá mới không?", "Confirm", JOptionPane.YES_NO_OPTION);
        PhieuGiamGia pgg = readForm();
        if (choose == JOptionPane.NO_OPTION) {
            return;
        }
        if (testData() && kiemTraTen(txtTenPGG.getText())) {
            if (pggRepo.add(pgg) > 0) {
                JOptionPane.showMessageDialog(this, "Thêm thành công phiếu giảm giá!");
                loadTableData(pggRepo.getAllKM());
            } else {
                JOptionPane.showMessageDialog(this, "Thêm không thành công!");
            }
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        PhieuGiamGia pgg = readForm();
        int rowSelected = tbPhieuGiamGia.getSelectedRow();
        if (rowSelected == -1) {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn bản ghi nào");
            return;
        }
        Object idPGGObj = tbPhieuGiamGia.getValueAt(rowSelected, 0);
        int id;
        try {
            id = Integer.parseInt(idPGGObj.toString());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID không hợp lệ!");
            return;
        }
        int choose = JOptionPane.showConfirmDialog(this, "Bạn có muốn chỉnh sửa thông tin?", "Confirm", JOptionPane.YES_NO_OPTION);
        if (choose == JOptionPane.NO_OPTION) {
            return;
        }
        if (checkUpdate() && checkTenPGGDaTonTai(txtTenPGG.getText()) && kiemTraTen(txtTenPGG.getText())) {
            try {
                if (pggRepo.update(pgg, id) > 0) {
                    JOptionPane.showMessageDialog(this, "Chỉnh sửa thành công thông tin!");
                    loadTableData(pggRepo.getAllKM());
                } else {
                    JOptionPane.showMessageDialog(this, "Chỉnh sửa thông tin không thành công!");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Lỗi khi cập nhật thông tin: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        clearFrom();
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void loadTableDataLoc(List<PhieuGiamGia> listpgg) {
        model = (DefaultTableModel) tbPhieuGiamGia.getModel();
        model.setRowCount(0);
        DecimalFormat format = new DecimalFormat("###,###,###");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String formatPhanTram;
        int i = 1;
        for (PhieuGiamGia pgg : listpgg) {
            capNhatTrangThaiCacPhieu(listpgg);
            Object[] row = {
                i++,
                "PGG00" + pgg.getId(),
                pgg.getTenPhieuGiamGia(),
                formatPhanTram = String.format("%.0f%%", pgg.getPhanTramGiamGia()),
                sdf.format(pgg.getNgayBatDau()),
                sdf.format(pgg.getNgayKetThuc()),
                format.format(pgg.getGiaTriToiThieu()),
                pgg.getSoHoaDonApDung(),
                pgg.getMoTa(),
                pgg.getTrangThaiHienThi()
            };
            model.addRow(row);
        }
    }

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        // Lấy ngày được chọn từ các thành phần chọn ngày
        java.util.Date startDate = dateNbd.getDate();
        java.util.Date endDate = dateNkt.getDate();

        // Kiểm tra nếu startDate hoặc endDate là null
        if (startDate == null || endDate == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ ngày và giờ");
            return;
        } else {
            // Gọi phương thức find của repository với các ngày không null
            System.out.println("Start Date: " + startDate);
            System.out.println("End Date: " + endDate);

            List<PhieuGiamGia> listpgg = pggRepo.findPggDate(startDate, endDate);
            System.out.println("Found " + listpgg.size() + " items.");

            loadTableDataLoc(listpgg); // Tải dữ liệu vào bảng với danh sách đã lấy được
        }
    }//GEN-LAST:event_btnTimKiemActionPerformed

    private void tbPhieuGiamGiaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPhieuGiamGiaMouseClicked
        int index = tbPhieuGiamGia.getSelectedRow();
        if (index < 0) {
            return;
        }

        // Kiểm tra nếu số lần click chuột là 1
        if (evt.getClickCount() == 1) {
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

                txtMaPGG.setText(tbPhieuGiamGia.getValueAt(index, 1).toString());
                txtTenPGG.setText(tbPhieuGiamGia.getValueAt(index, 2).toString());
                cboPhanTramGiam.setSelectedItem(tbPhieuGiamGia.getValueAt(index, 3).toString());

                // Chuyển đổi và gán ngày bắt đầu
                java.util.Date utilDateNgayBD = dateFormat.parse(tbPhieuGiamGia.getValueAt(index, 4).toString());
                Date sqlDateNgayBD = new Date(utilDateNgayBD.getTime());
                JDateNgayBD.setDate(sqlDateNgayBD);

                // Chuyển đổi và gán ngày kết thúc
                java.util.Date utilDateNgayKT = dateFormat.parse(tbPhieuGiamGia.getValueAt(index, 5).toString());
                Date sqlDateNgayKT = new Date(utilDateNgayKT.getTime());
                JDateNgayKT.setDate(sqlDateNgayKT);

                txtGiaTriToiThieu.setText(tbPhieuGiamGia.getValueAt(index, 6).toString());
                txtSoHoaDonApDung.setText(tbPhieuGiamGia.getValueAt(index, 7).toString());
                txtMoTa.setText(tbPhieuGiamGia.getValueAt(index, 8).toString());

            } catch (Exception ex) {
                // Xử lý ngoại lệ nếu không thể chuyển đổi ngày tháng
                ex.printStackTrace();
                // Có thể hiển thị thông báo lỗi cho người dùng
            }
        }
    }//GEN-LAST:event_tbPhieuGiamGiaMouseClicked

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        String search = txtSearch.getText();
        model = (DefaultTableModel) tbPhieuGiamGia.getModel();
        search(search, model, tbPhieuGiamGia);
    }//GEN-LAST:event_txtSearchKeyReleased

    private void btnHuyLocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyLocActionPerformed

        loadTableData(pggRepo.getAllKM());
    }//GEN-LAST:event_btnHuyLocActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser JDateNgayBD;
    private com.toedter.calendar.JDateChooser JDateNgayKT;
    private javax.swing.JButton btnHuyLoc;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JComboBox<String> cboPhanTramGiam;
    private com.toedter.calendar.JDateChooser dateNbd;
    private com.toedter.calendar.JDateChooser dateNkt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbPhieuGiamGia;
    private javax.swing.JTextField txtGiaTriToiThieu;
    private javax.swing.JTextField txtMaPGG;
    private javax.swing.JTextField txtMoTa;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtSoHoaDonApDung;
    private javax.swing.JTextField txtTenPGG;
    // End of variables declaration//GEN-END:variables
}
