package com.raven.form;

import com.raven.classmodel.GetChucVu;
import com.raven.classmodel.NhanVien;
import com.raven.repository.NhanVienRepository;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class FormNhanVien extends javax.swing.JPanel {

    private NhanVienRepository repo = new NhanVienRepository();
    private DefaultTableModel model = new DefaultTableModel();
    private int index = -1;
    private static String IdSelected = "";
    private static boolean IsGetTabNhanVien = true;

    public FormNhanVien() {
        initComponents();
        fillTable(repo.getAll(IsGetTabNhanVien));
        if (GetChucVu.getChucVu() == 0) {
            btnAdd.setEnabled(true);
            btnUpdateTrangThai.setEnabled(true);
            btnUpdate.setEnabled(true);
            btnReset.setEnabled(true);
        } else if (GetChucVu.getChucVu() == 1) {
            btnAdd.setEnabled(false);
            btnUpdateTrangThai.setEnabled(false);
            btnUpdate.setEnabled(false);
            btnReset.setEnabled(true);
        }
    }

    public void fillTable(List<NhanVien> listnv) {
        model = (DefaultTableModel) tbNhanVien.getModel();
        listnv = repo.getAll(IsGetTabNhanVien);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        model.setRowCount(0);
        int i = 1;
        for (NhanVien nv : listnv) {
            Object row[] = {
                i++,
                "NV00" + nv.getId(),
                nv.getHoTen(),
                nv.isGioiTinh() ? "Nữ" : "Nam",
                sdf.format(nv.getNgaySinh()),
                nv.getEmail(),
                nv.getCccd(),
                nv.getSdt(),
                nv.getTenDN(),
                nv.getMatKhau(),
                nv.getDiaChi(),
                nv.isChucVu() ? "Nhân Viên" : "Quản lý"
            };
            model.addRow(row);
        }
    }

    public void fillGioiTinhNu(List<NhanVien> listNu) {
        model = (DefaultTableModel) tbNhanVien.getModel();
        listNu = repo.fillFemale();

        model.setRowCount(0);
        int i = 1;
        for (NhanVien nv : listNu) {
            Object row[] = {
                i++,
                "NV00" + nv.getId(),
                nv.getHoTen(),
                nv.isGioiTinh() ? "Nữ" : "Nam",
                nv.getNgaySinh(),
                nv.getEmail(),
                nv.getCccd(),
                nv.getSdt(),
                nv.getTenDN(),
                nv.getMatKhau(),
                nv.getDiaChi(),
                nv.isChucVu() ? "Nhân Viên" : "Quản lý"
            };
            model.addRow(row);
        }
    }

    public void fillGioiTinhNam(List<NhanVien> listNam) {
        model = (DefaultTableModel) tbNhanVien.getModel();
        listNam = repo.fillMale();

        model.setRowCount(0);
        int i = 1;
        for (NhanVien nv : listNam) {
            Object row[] = {
                i++,
                "NV00" + nv.getId(),
                nv.getHoTen(),
                nv.isGioiTinh() ? "Nữ" : "Nam",
                nv.getNgaySinh(),
                nv.getEmail(),
                nv.getCccd(),
                nv.getSdt(),
                nv.getTenDN(),
                nv.getMatKhau(),
                nv.getDiaChi(),
                nv.isChucVu() ? "Nhân Viên" : "Quản lý"
            };
            model.addRow(row);
        }
    }

    public void fillNhanVien(List<NhanVien> listNhanVien) {
        model = (DefaultTableModel) tbNhanVien.getModel();
        listNhanVien = repo.fillNhanVien();

        model.setRowCount(0);
        int i = 1;
        for (NhanVien nv : listNhanVien) {
            Object row[] = {
                i++,
                "NV00" + nv.getId(),
                nv.getHoTen(),
                nv.isGioiTinh() ? "Nữ" : "Nam",
                nv.getNgaySinh(),
                nv.getEmail(),
                nv.getCccd(),
                nv.getSdt(),
                nv.getTenDN(),
                nv.getMatKhau(),
                nv.getDiaChi(),
                nv.isChucVu() ? "Nhân Viên" : "Quản lý"
            };
            model.addRow(row);
        }
    }

    public void fillQuanLy(List<NhanVien> listQuanLy) {
        model = (DefaultTableModel) tbNhanVien.getModel();
        listQuanLy = repo.fillQuanLy();

        model.setRowCount(0);
        int i = 1;
        for (NhanVien nv : listQuanLy) {
            Object row[] = {
                i++,
                "NV00" + nv.getId(),
                nv.getHoTen(),
                nv.isGioiTinh() ? "Nữ" : "Nam",
                nv.getNgaySinh(),
                nv.getEmail(),
                nv.getCccd(),
                nv.getSdt(),
                nv.getTenDN(),
                nv.getMatKhau(),
                nv.getDiaChi(),
                nv.isChucVu() ? "Nhân Viên" : "Quản lý"
            };
            model.addRow(row);
        }
    }

    public void show(int index) {
        NhanVien nv = repo.getAll(IsGetTabNhanVien).get(index);
        txtMaNV.setText("NV00" + nv.getId());
        txtHoTen.setText(nv.getHoTen());
        rdoNam.setSelected(!nv.isGioiTinh());
        rdoNu.setSelected(nv.isGioiTinh());
        if (nv.getNgaySinh() != null) {
            JDate.setDate(nv.getNgaySinh());
        }
        txtEmail.setText(nv.getEmail());
        txtCCCD.setText(nv.getCccd());
        txtSdt.setText(nv.getSdt());
        txtTenDN.setText(nv.getTenDN());
        txtMatKhau.setText(nv.getMatKhau());
        txtDiaChi.setText(nv.getDiaChi());
        rdoNhanVien.setSelected(!nv.isChucVu());
        rdoQuanLy.setSelected(nv.isChucVu());
    }

    public NhanVien readForm() {
        String hoTen = txtHoTen.getText();
        boolean gioiTinh = rdoNu.isSelected();
        Date ngaySinh = JDate.getDate();
        String email = txtEmail.getText();
        String cccd = txtCCCD.getText();
        String sdt = txtSdt.getText();
        String tenDN = txtTenDN.getText();
        String matKhau = txtMatKhau.getText();
        String diaChi = txtDiaChi.getText();
        boolean chucVu = rdoQuanLy.isSelected();
        return new NhanVien(hoTen, gioiTinh, ngaySinh, email, cccd, sdt, tenDN, matKhau, diaChi, chucVu);
    }

    public void search(String str, DefaultTableModel model, JTable table) {
        model = (DefaultTableModel) tbNhanVien.getModel();
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

    private boolean check() {
        if (txtHoTen.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không để trống tên nhân viên!");
            return false;
        } else if (!txtHoTen.getText().matches("^[a-zA-Z\\p{L}\\s]+$")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng định dạng họ tên!");
            return false;
        } else if (txtHoTen.getText().length() > 50) {
            JOptionPane.showMessageDialog(this, "Tên nhân viên không được dài quá 50 ký tự!");
            return false;
        }

        if (JDate.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Không để trống ngày sinh nhân viên!");
            return false;
        } else {
            LocalDate dob = JDate.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate currentDate = LocalDate.now();
            Period period = Period.between(dob, currentDate);
            int age = period.getYears();
            if (age < 18) { // Kiểm tra tuổi nhân viên >18
                JOptionPane.showMessageDialog(this, "Nhân viên phải từ 18 tuổi trở lên!");
                return false;
            }
        }
        if (txtSdt.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không bỏ trống số điện thoại!");
            return false;
        } else if (!txtSdt.getText().matches("(84|0[3|5|7|8|9])+([0-9]{8})")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng định dạng số điện thoại!");
            return false;
        }
        if (txtTenDN.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không để trống tên đăng nhập!");
            return false;
        } else if (txtTenDN.getText().length() > 20) {
            JOptionPane.showMessageDialog(this, "Tên đăng nhập không được dài quá 20 ký tự!");
            return false;
        }
        if (txtDiaChi.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không để trống địa chỉ!");
            return false;
        } else if (!txtDiaChi.getText().matches("^[a-zA-Z\\p{L}\\s]+$")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng định dạng địa chỉ!");
            return false;
        } else if (txtDiaChi.getText().length() > 255) {
            JOptionPane.showMessageDialog(this, "Địa chỉ không được dài quá 255 ký tự!");
            return false;
        }
        if (txtEmail.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không để trống email");
            return false;
        } else if (!txtEmail.getText().matches("^(.+)@(.+)$")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng định dạng email!");
            return false;
        }
        if (txtCCCD.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không để trống căn cước công dân!");
            return false;
        } else if (!txtCCCD.getText().matches("\\d{12}")) {  // Kiểm tra số CCCD gồm 12 chữ số
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng định dạng căn cước công dân (12 chữ số)!");
            return false;
        }
        return true;
    }

    public boolean kiemTraSoDienThoaiTonTai(String soDienThoaiCanKiemTra) {
        List<String> danhSachSoDienThoai = repo.laySoDienThoaiDaTonTai(); // Lấy danh sách số điện thoại từ cơ sở dữ liệu
        return danhSachSoDienThoai.contains(soDienThoaiCanKiemTra);
    }

    public boolean kiemTraCCCDTonTai(String CCCDCanKiemTra) {
        List<String> danhSachCCCD = repo.layCCCDDaTonTai(); // Lấy danh sách số điện thoại từ cơ sở dữ liệu
        return danhSachCCCD.contains(CCCDCanKiemTra);
    }

    public boolean kiemTraTenDangNhapTonTai(String TenDNCanKiemTra) {
        List<String> danhsachTenDN = repo.layTenDangNhapDaTonTai(); // Lấy danh sách số điện thoại từ cơ sở dữ liệu
        return danhsachTenDN.contains(TenDNCanKiemTra);
    }

    public boolean kiemTraEmailTonTai(String EmailCanKiemTra) {
        List<String> danhsachEmail = repo.layEmailDaTonTai(); // Lấy danh sách số điện thoại từ cơ sở dữ liệu
        return danhsachEmail.contains(EmailCanKiemTra);
    }

    private boolean testData() {
        if (txtHoTen.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không để trống tên nhân viên!");
            return false;
        } else if (!txtHoTen.getText().matches("^[a-zA-Z\\p{L}\\s]+$")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng định dạng họ tên!");
            return false;
        } else if (txtHoTen.getText().length() > 50) {
            JOptionPane.showMessageDialog(this, "Tên nhân viên không được dài quá 50 ký tự!");
            return false;
        }

        if (JDate.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Không để trống ngày sinh nhân viên!");
            return false;
        } else {
            LocalDate dob = JDate.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate currentDate = LocalDate.now();
            Period period = Period.between(dob, currentDate);
            int age = period.getYears();
            if (age < 18) { // Kiểm tra tuổi nhân viên >18
                JOptionPane.showMessageDialog(this, "Nhân viên phải từ 18 tuổi trở lên!");
                return false;
            }
        }
        if (txtSdt.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không bỏ trống số điện thoại!");
            return false;
        } else if (!txtSdt.getText().matches("(84|0[3|5|7|8|9])+([0-9]{8})")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng định dạng số điện thoại!");
            return false;
        } else if (kiemTraSoDienThoaiTonTai(txtSdt.getText())) {
            JOptionPane.showMessageDialog(this, "Số điện thoại đã tồn tại!");
            return false;
        }
        if (txtTenDN.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không để trống tên đăng nhập!");
            return false;
        } else if (txtTenDN.getText().length() > 20) {
            JOptionPane.showMessageDialog(this, "Tên đăng nhập không được dài quá 20 ký tự!");
            return false;
        } else if (!txtTenDN.getText().matches("^[a-zA-Z0-9]+$")) {  // Kiểm tra tên đăng nhập không chứa ký tự đặc biệt
            JOptionPane.showMessageDialog(this, "Tên đăng nhập không được chứa ký tự đặc biệt!");
            return false;
        } else if (kiemTraTenDangNhapTonTai(txtTenDN.getText())) {
            JOptionPane.showMessageDialog(this, "Tên đăng nhập đã tồn tại!");
            return false;
        }
        if (txtMatKhau.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không để trống mật khẩu!");
            return false;
        } else if (txtMatKhau.getText().length() > 50) {
            JOptionPane.showMessageDialog(this, "Mật khẩu không được dài quá 50 ký tự!");
            return false;
        }
        if (txtDiaChi.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không để trống địa chỉ!");
            return false;
        } else if (!txtDiaChi.getText().matches("^[a-zA-Z\\p{L}\\s]+$")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng định dạng họ tên!");
            return false;
        } else if (txtDiaChi.getText().length() > 255) {
            JOptionPane.showMessageDialog(this, "Địa chỉ không được dài quá 255 ký tự!");
            return false;
        }
        if (txtEmail.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không để trống email");
            return false;
        } else if (!txtEmail.getText().matches("^(.+)@(.+)$")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng định dạng email!");
            return false;
        } else if (kiemTraEmailTonTai(txtEmail.getText())) {
            JOptionPane.showMessageDialog(this, "Email đã tồn tại!");
            return false;
        }
        if (txtCCCD.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không để trống căn cước công dân!");
            return false;
        } else if (!txtCCCD.getText().matches("\\d{12}")) {  // Kiểm tra số CCCD gồm 12 chữ số
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng định dạng căn cước công dân (12 chữ số)!");
            return false;
        } else if (kiemTraCCCDTonTai(txtCCCD.getText())) {
            JOptionPane.showMessageDialog(this, "Căn cước công dân đã tồn tại!");
            return false;
        }
        return true;
    }

    public boolean kiemTraTen(String inputTen) {
        if (inputTen.length() < 2) {
            JOptionPane.showMessageDialog(this, "Tên nhân viên phải chứa tối thiểu 2 kí tự.");
            return false;
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        buttonGroup5 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtMaNV = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtHoTen = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        rdoNam = new javax.swing.JRadioButton();
        rdoNu = new javax.swing.JRadioButton();
        jLabel7 = new javax.swing.JLabel();
        JDate = new com.toedter.calendar.JDateChooser();
        jLabel8 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtDiaChi = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        rdoNhanVien = new javax.swing.JRadioButton();
        rdoQuanLy = new javax.swing.JRadioButton();
        txtMatKhau = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtTenDN = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtSdt = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtCCCD = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        txtTimKiem = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        rdoSearchMale = new javax.swing.JRadioButton();
        rdoSearchFemale = new javax.swing.JRadioButton();
        rdoAll = new javax.swing.JRadioButton();
        jLabel18 = new javax.swing.JLabel();
        rdoSearchQuanLy = new javax.swing.JRadioButton();
        rdoSearchNhanVien = new javax.swing.JRadioButton();
        rdoSearchAll = new javax.swing.JRadioButton();
        jPanel3 = new javax.swing.JPanel();
        tabs = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbNhanVien = new javax.swing.JTable();
        btnUpdateTrangThai = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("QUẢN LÝ NHÂN VIÊN");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin nhân viên", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Mã nhân viên");

        txtMaNV.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtMaNV.setEnabled(false);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Họ tên");

        txtHoTen.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Giới tính");

        buttonGroup1.add(rdoNam);
        rdoNam.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rdoNam.setSelected(true);
        rdoNam.setText("Nam");

        buttonGroup1.add(rdoNu);
        rdoNu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rdoNu.setText("Nữ");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Ngày sinh");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Email");

        txtEmail.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setText("Địa chỉ");

        txtDiaChi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setText("Chức vụ");

        buttonGroup2.add(rdoNhanVien);
        rdoNhanVien.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rdoNhanVien.setSelected(true);
        rdoNhanVien.setText("Quản lý");

        buttonGroup2.add(rdoQuanLy);
        rdoQuanLy.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rdoQuanLy.setText("Nhân Viên");

        txtMatKhau.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setText("Tên đăng nhập");

        txtTenDN.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setText("Mật khẩu");

        txtSdt.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel15.setText("Số điện thoại");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setText("CCCD");

        txtCCCD.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(72, 72, 72)
                        .addComponent(txtCCCD))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel4)
                                .addComponent(jLabel5)
                                .addComponent(jLabel6)
                                .addComponent(jLabel7))
                            .addGap(26, 26, 26)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(rdoNam)
                                    .addGap(65, 65, 65)
                                    .addComponent(rdoNu)
                                    .addGap(14, 14, 14))
                                .addComponent(txtHoTen)
                                .addComponent(txtMaNV)
                                .addComponent(JDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel8)
                            .addGap(77, 77, 77)
                            .addComponent(txtEmail))))
                .addGap(79, 79, 79)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(160, 160, 160)
                                .addComponent(rdoQuanLy))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(jLabel15)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSdt, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTenDN, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rdoNhanVien))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(15, 15, 15))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel6)
                                .addComponent(rdoNam, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addComponent(rdoNu, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(JDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(txtTenDN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rdoNhanVien)
                            .addComponent(rdoQuanLy)
                            .addComponent(jLabel11))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtCCCD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 14, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm kiếm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        txtTimKiem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyReleased(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel16.setText("Tìm kiếm");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel17.setText("Giới tính");

        buttonGroup4.add(rdoSearchMale);
        rdoSearchMale.setText("Nam");
        rdoSearchMale.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rdoSearchMaleItemStateChanged(evt);
            }
        });

        buttonGroup4.add(rdoSearchFemale);
        rdoSearchFemale.setText("Nữ");
        rdoSearchFemale.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rdoSearchFemaleItemStateChanged(evt);
            }
        });

        buttonGroup4.add(rdoAll);
        rdoAll.setSelected(true);
        rdoAll.setText("Tất cả");
        rdoAll.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rdoAllItemStateChanged(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel18.setText("Chức vụ");

        buttonGroup5.add(rdoSearchQuanLy);
        rdoSearchQuanLy.setText("Nhân Viên");
        rdoSearchQuanLy.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rdoSearchQuanLyItemStateChanged(evt);
            }
        });

        buttonGroup5.add(rdoSearchNhanVien);
        rdoSearchNhanVien.setText("Quản lý");
        rdoSearchNhanVien.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rdoSearchNhanVienItemStateChanged(evt);
            }
        });

        buttonGroup5.add(rdoSearchAll);
        rdoSearchAll.setSelected(true);
        rdoSearchAll.setText("Tất cả");
        rdoSearchAll.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rdoSearchAllItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(rdoSearchMale)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(rdoSearchFemale)
                        .addGap(33, 33, 33)
                        .addComponent(rdoAll)
                        .addGap(56, 56, 56))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(rdoSearchNhanVien)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                        .addComponent(rdoSearchQuanLy)
                        .addGap(29, 29, 29)
                        .addComponent(rdoSearchAll)
                        .addGap(14, 14, 14))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdoSearchMale)
                    .addComponent(rdoSearchFemale)
                    .addComponent(rdoAll))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdoSearchQuanLy)
                    .addComponent(rdoSearchNhanVien)
                    .addComponent(rdoSearchAll))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin nhân viên", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        tabs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabsMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1109, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        tabs.addTab("Danh sách nhân viên", jPanel5);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1109, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        tabs.addTab("Danh sách nhân viên đã nghỉ việc", jPanel9);

        tbNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tbNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbNhanVienMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbNhanVien);

        btnUpdateTrangThai.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnUpdateTrangThai.setText("Trạng thái");
        btnUpdateTrangThai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateTrangThaiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tabs)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnUpdateTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabs, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnUpdateTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        btnAdd.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnAdd.setText("Thêm");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnUpdate.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnUpdate.setText("Cập nhật");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnReset.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnReset.setText("Làm mới");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnReset, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(btnUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(btnAdd)
                        .addGap(39, 39, 39)
                        .addComponent(btnUpdate)
                        .addGap(40, 40, 40)
                        .addComponent(btnReset))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tabsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabsMouseClicked
        //nếu như click vào tab 1 thì là false
        //ngược lại click vào tab2 là bằng true
        int selectedIndex = tabs.getSelectedIndex();
        if (selectedIndex == 0) {
            IsGetTabNhanVien = true;
        } else { //G.diện nv nghỉ làm
            IsGetTabNhanVien = false;
        }
        this.fillTable(repo.getAll(IsGetTabNhanVien));
    }//GEN-LAST:event_tabsMouseClicked

    private void tbNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbNhanVienMouseClicked
        int rowCount = this.tbNhanVien.getRowCount();
        index = tbNhanVien.getSelectedRow();
        if (index != -1 && rowCount > 0) {
            IdSelected = tbNhanVien.getValueAt(index, 0).toString();
            this.show(index);
        }
    }//GEN-LAST:event_tbNhanVienMouseClicked

    private void btnUpdateTrangThaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateTrangThaiActionPerformed
        boolean isDeleted = !IsGetTabNhanVien;
        int choose = tbNhanVien.getSelectedRow();
        if (choose == -1) {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn bản ghi nào!");
            return;
        }
        try {
            Object idObject = tbNhanVien.getValueAt(choose, 0);
            int id;
            if (idObject instanceof Integer) {
                id = (Integer) idObject;
            } else if (idObject instanceof String) {
                id = Integer.parseInt((String) idObject);
            } else {
                throw new ClassCastException("ID không phải là Integer hoặc String.");
            }

            int confirm = JOptionPane.showConfirmDialog(this, "Bạn có muốn chỉnh sửa trạng thái nhân viên?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.NO_OPTION) {
                return;
            }
            if (repo.updateTrangThai(id, isDeleted) > 0) {
                JOptionPane.showMessageDialog(this, "Chỉnh sửa trạng thái thành công!");
                this.fillTable(repo.getAll(IsGetTabNhanVien));
            } else {
                JOptionPane.showMessageDialog(this, "Chỉnh sửa trạng thái không thành công!");
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "ID không hợp lệ!");
        } catch (ClassCastException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "ID không đúng định dạng!");
        }
    }//GEN-LAST:event_btnUpdateTrangThaiActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        txtMaNV.setText("");
        txtHoTen.setText("");
        rdoNam.setSelected(false);
        rdoNu.setSelected(false);
        JDate.setDate(null);
        txtSdt.setText("");
        txtTenDN.setText("");
        txtMatKhau.setText("");
        txtEmail.setText("");
        txtCCCD.setText("");
        rdoNhanVien.setSelected(false);
        rdoQuanLy.setSelected(false);
        txtDiaChi.setText("");
        txtTimKiem.setText("");
        rdoSearchFemale.setSelected(false);
        rdoSearchMale.setSelected(false);
        rdoAll.setSelected(false);
        rdoSearchNhanVien.setSelected(false);
        rdoSearchNhanVien.setSelected(false);
        rdoSearchAll.setSelected(false);
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        NhanVien nv = this.readForm();
        index = tbNhanVien.getSelectedRow();
        int sttDuocChon = tbNhanVien.getSelectedRow();
        if (sttDuocChon == -1) {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn bản ghi nào !");
            return;
        }
        Object idNVObj = tbNhanVien.getValueAt(index, 0);
        int idNV;
        try {
            idNV = Integer.parseInt(idNVObj.toString());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID nhân viên không hợp lệ!");
            return;
        }
        int choose = JOptionPane.showConfirmDialog(this, "Bạn có muốn chỉnh sửa thông tin nhân viên ?", "Confirm", JOptionPane.YES_NO_OPTION);
        if (choose == JOptionPane.NO_OPTION) {
            return;
        }
        if (check() && kiemTraTen(txtHoTen.getText())) {
            if (repo.update(nv, idNV) > 0) {
                JOptionPane.showMessageDialog(this, "Chỉnh sửa thành công thông tin nhân viên !");
                fillTable(repo.getAll(IsGetTabNhanVien));
            } else {
                JOptionPane.showMessageDialog(this, "Chỉnh sửa thông tin không thành công !");
            }
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        int choose = JOptionPane.showConfirmDialog(this, "Bạn có muốn thêm thông tin nhân viên?", "Confirm", JOptionPane.YES_NO_OPTION);
        if (choose == JOptionPane.NO_OPTION) {
            return;
        }
        if (testData() && kiemTraTen(txtHoTen.getText())) {
            NhanVien nv = this.readForm();
            if (repo.getNV(nv.getId()) != null) {
                JOptionPane.showMessageDialog(this, "Thêm không thành công do trùng mã!");
            } else {
                if (repo.insert(nv) > 0) {
                    JOptionPane.showMessageDialog(this, "Thêm thành công nhân viên!");
                    fillTable(repo.getAll(IsGetTabNhanVien));
                } else {
                    JOptionPane.showMessageDialog(this, "Thêm không thành công!");
                }
            }
        }
    }//GEN-LAST:event_btnAddActionPerformed

    public void updateTableBasedOnFilters() {
        boolean isMale = rdoSearchMale.isSelected();
        boolean isFemale = rdoSearchFemale.isSelected();
        boolean isNhanVien = rdoSearchNhanVien.isSelected();
        boolean isQuanLy = rdoSearchQuanLy.isSelected();
        List<NhanVien> filteredList = new ArrayList<>();
        if (isMale && isNhanVien) {
            filteredList = repo.getNhanVienByGenderAndRole(0, 0);
        } else if (isMale && isQuanLy) {
            filteredList = repo.getNhanVienByGenderAndRole(0, 1);
        } else if (isFemale && isNhanVien) {
            filteredList = repo.getNhanVienByGenderAndRole(1, 0);
        } else if (isFemale && isQuanLy) {
            filteredList = repo.getNhanVienByGenderAndRole(1, 1);
        } else if (isMale) {
            filteredList = repo.fillMale();
        } else if (isFemale) {
            filteredList = repo.fillFemale();
        } else if (isNhanVien) {
            filteredList = repo.fillNhanVien();
        } else if (isQuanLy) {
            filteredList = repo.fillQuanLy();
        } else {
            filteredList = repo.getAll(IsGetTabNhanVien);
        }
        fillTableList(filteredList);
    }

    public void fillTableList(List<NhanVien> listNhanVien) {
        model = (DefaultTableModel) tbNhanVien.getModel();
        model.setRowCount(0);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        int i = 1;
        for (NhanVien nv : listNhanVien) {
            Object row[] = {
                i++,
                "NV00" + nv.getId(),
                nv.getHoTen(),
                nv.isGioiTinh() ? "Nữ" : "Nam",
                sdf.format(nv.getNgaySinh()),
                nv.getEmail(),
                nv.getCccd(),
                nv.getSdt(),
                nv.getTenDN(),
                nv.getMatKhau(),
                nv.getDiaChi(),
                nv.isChucVu() ? "Nhân Viên" : "Quản lý"
            };
            model.addRow(row);
        }
    }

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
        String search = txtTimKiem.getText();
        model = (DefaultTableModel) tbNhanVien.getModel();
        search(search, model, tbNhanVien);
    }//GEN-LAST:event_txtTimKiemKeyReleased

    private void rdoSearchMaleItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rdoSearchMaleItemStateChanged
        if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
            updateTableBasedOnFilters();
        }
    }//GEN-LAST:event_rdoSearchMaleItemStateChanged

    private void rdoAllItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rdoAllItemStateChanged
        if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
            fillTable(repo.getAll(IsGetTabNhanVien));
        }
    }//GEN-LAST:event_rdoAllItemStateChanged

    private void rdoSearchNhanVienItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rdoSearchNhanVienItemStateChanged
        if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
            updateTableBasedOnFilters();
        }
    }//GEN-LAST:event_rdoSearchNhanVienItemStateChanged

    private void rdoSearchAllItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rdoSearchAllItemStateChanged
        if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
            fillTable(repo.getAll(IsGetTabNhanVien));
        }
    }//GEN-LAST:event_rdoSearchAllItemStateChanged

    private void rdoSearchFemaleItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rdoSearchFemaleItemStateChanged
        if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
            updateTableBasedOnFilters();
        }
    }//GEN-LAST:event_rdoSearchFemaleItemStateChanged

    private void rdoSearchQuanLyItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rdoSearchQuanLyItemStateChanged
        if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
            updateTableBasedOnFilters();
        }
    }//GEN-LAST:event_rdoSearchQuanLyItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser JDate;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton btnUpdateTrangThai;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.ButtonGroup buttonGroup5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JRadioButton rdoAll;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNhanVien;
    private javax.swing.JRadioButton rdoNu;
    private javax.swing.JRadioButton rdoQuanLy;
    private javax.swing.JRadioButton rdoSearchAll;
    private javax.swing.JRadioButton rdoSearchFemale;
    private javax.swing.JRadioButton rdoSearchMale;
    private javax.swing.JRadioButton rdoSearchNhanVien;
    private javax.swing.JRadioButton rdoSearchQuanLy;
    private javax.swing.JTabbedPane tabs;
    private javax.swing.JTable tbNhanVien;
    private javax.swing.JTextField txtCCCD;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JTextField txtMatKhau;
    private javax.swing.JTextField txtSdt;
    private javax.swing.JTextField txtTenDN;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
