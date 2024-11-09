package com.raven.form;

import com.raven.classmodel.GetChucVu;
import com.raven.classmodel.HoaDon;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import com.raven.classmodel.KhachHang;
import com.raven.repository.HoaDonRepository;
import com.raven.repository.KhachHangRepository;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JTable;

/**
 *
 * @author HoaiThu
 */
public class FormKhachHang extends javax.swing.JPanel {

    private DefaultTableModel dtm = new DefaultTableModel();
    private KhachHangRepository khachHangRepository = new KhachHangRepository();
    private HoaDonRepository hoaDonRepository = new HoaDonRepository();
    private KhachHang kh = new KhachHang();
    private int row = -1;

    public FormKhachHang() {
        initComponents();
        fillTable(khachHangRepository.getAll());
        loadTableHD(hoaDonRepository.getAll());

        if (GetChucVu.getChucVu() == 0) {
            btnAdd.setEnabled(true);
            btnDelete.setEnabled(true);
            btnUpdate.setEnabled(true);
            btnReset.setEnabled(true);
        } else if (GetChucVu.getChucVu() == 1) {
            btnAdd.setEnabled(true);
            btnDelete.setEnabled(false);
            btnUpdate.setEnabled(true);
            btnReset.setEnabled(true);
        }

    }

    void fillTable(List<KhachHang> listkh) {
        dtm = (DefaultTableModel) tbQLKH.getModel();
        dtm.setRowCount(0);
        listkh = khachHangRepository.getAll();
        int i = 1;
        for (KhachHang kh : listkh) {
            Object[] row = {
                i++,
                "KH00" + kh.getId(),
                kh.getHoTen(),
                kh.isGioiTinh() ? "Nữ" : "Nam",
                kh.getSoDT(),
                kh.getEmail(),
                kh.getDiaChi(),
                kh.getThanhPho()
            };
            dtm.addRow(row);
        }
    }

    void fillTableHD(int idKH) {
        dtm = (DefaultTableModel) tbDSHD.getModel();
        dtm.setRowCount(0);
        List<HoaDon> listhd = hoaDonRepository.getHD(idKH);
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        int i = 1;
        for (HoaDon hd : listhd) {
            Object[] row = {
                i++,
                "HD00" + hd.getId(),
                hd.getSoLuong(),
                formatter.format(hd.getGia()) + " VND",
                formatter.format(hd.getTongTien()) + " VND",};
            dtm.addRow(row);
        }
    }

    public void fillGioiTinhNu(List<KhachHang> listNu) {
        dtm = (DefaultTableModel) tbQLKH.getModel();
        dtm.setRowCount(0);
        listNu = khachHangRepository.fillGenderFemale();
        int i = 1;
        for (KhachHang kh : listNu) {
            Object[] row = {
                i++,
                "KH00" + kh.getId(),
                kh.getHoTen(),
                kh.isGioiTinh() ? "Nữ" : "Nam",
                kh.getSoDT(),
                kh.getEmail(),
                kh.getDiaChi(),
                kh.getThanhPho()
            };
            dtm.addRow(row);
        }
    }

    public void fillGioiTinhNam(List<KhachHang> listNam) {
        dtm = (DefaultTableModel) tbQLKH.getModel();
        dtm.setRowCount(0);
        listNam = khachHangRepository.fillGenderMale();
        int i = 1;
        for (KhachHang kh : listNam) {
            Object[] row = {
                i++,
                "KH00" + kh.getId(),
                kh.getHoTen(),
                kh.isGioiTinh() ? "Nữ" : "Nam",
                kh.getSoDT(),
                kh.getEmail(),
                kh.getDiaChi(),
                kh.getThanhPho()
            };
            dtm.addRow(row);
        }
    }

    void loadTableHD(List<HoaDon> listhd) {
        dtm = (DefaultTableModel) tbDSHD.getModel();
        dtm.setRowCount(0);
        listhd = hoaDonRepository.getAllHD();
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        int i = 1;
        for (HoaDon hd : listhd) {
            Object[] row = {
                i++,
                "HD00" + hd.getId(),
                hd.getSoLuong(),
                formatter.format(hd.getGia()) + " VND",
                formatter.format(hd.getTongTien()) + " VND",};
            dtm.addRow(row);
        }
    }

    void clearForm() {
        txtThanhPho.setText("");
        txtSoDT.setText("");
        txtEmail.setText("");
        txtHoTen.setText("");
        txtDiaChi.setText("");
    }

    void showData(int index) {
        KhachHang kh = khachHangRepository.getAll().get(index);
        txtHoTen.setText(kh.getHoTen());
        txtDiaChi.setText(kh.getDiaChi());
        txtSoDT.setText(kh.getSoDT());
        txtEmail.setText(kh.getEmail());
        txtThanhPho.setText(kh.getThanhPho());
        rdoNam.setSelected(!kh.isGioiTinh());
        rdoNu.setSelected(kh.isGioiTinh());
    }

    KhachHang readForm() {
        String hoTen = txtHoTen.getText().trim();
        String soDT = txtSoDT.getText().trim();
        String thanhPho = txtThanhPho.getText().trim();
        String email = txtEmail.getText().trim();
        String diaChi = txtDiaChi.getText().trim();
        Boolean gioiTinh = rdoNu.isSelected();
        return new KhachHang(hoTen, gioiTinh, soDT, email, diaChi, thanhPho);
    }

    public void search(String str, DefaultTableModel model, JTable table) {
        model = (DefaultTableModel) tbQLKH.getModel();
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

    public void searchHD(String str, DefaultTableModel model, JTable table) {
        model = (DefaultTableModel) tbDSHD.getModel();
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

    boolean check() {
        if (txtHoTen.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không bỏ trống họ tên khách hàng !");
            return false;
        } else if (!txtHoTen.getText().matches("^[a-zA-Z\\p{L}\\s]+$")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng định dạng họ tên!");
            return false;
        } else if (txtHoTen.getText().length() > 50) {
            JOptionPane.showMessageDialog(this, "Họ tên khách hàng không được dài quá 50 ký tự!");
            return false;
        }
        if (txtSoDT.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không bỏ trống số điện thoại khách hàng !");
            return false;
        } else if (!txtSoDT.getText().matches("(84|0[3|5|7|8|9])+([0-9]{8})")) {
            JOptionPane.showMessageDialog(this, "Số điện thoại nhập vào không đúng định dạng, vui lòng nhập lại!");
            return false;
        }
        if (txtEmail.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không để trống email");
            return false;
        } else if (!txtEmail.getText().matches("^(.+)@(.+)$")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng định dạng email!");
            return false;
        }
        if (txtDiaChi.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không bỏ trống địa chỉ của khách hàng !");
            return false;
        } else if (!txtDiaChi.getText().matches("^[a-zA-Z\\p{L}\\s]+$")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng định dạng địa chỉ!");
            return false;
        } else if (txtDiaChi.getText().length() > 255) {
            JOptionPane.showMessageDialog(this, "Mật khẩu không được dài quá 255 ký tự!");
        }
        if (txtThanhPho.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không bỏ trống thành phố của khách hàng !");
            return false;
        } else if (!txtThanhPho.getText().matches("^[a-zA-Z\\p{L}\\s]+$")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng định dạng thành phố!");
            return false;
        } else if (txtThanhPho.getText().length() > 50) {
            JOptionPane.showMessageDialog(this, "Thành phố không được dài quá 255 ký tự!");
        }
        return true;
    }

    public boolean kiemTraSoDienThoaiTonTai(String soDienThoaiCanKiemTra) {
        List<String> danhSachSoDienThoai = khachHangRepository.laySoDienThoaiDaTonTai(); // Lấy danh sách số điện thoại từ cơ sở dữ liệu
        return danhSachSoDienThoai.contains(soDienThoaiCanKiemTra);
    }

    public boolean kiemTraEmailTonTai(String EmailCanKiemTra) {
        List<String> danhsachEmail = khachHangRepository.layEmailDaTonTai(); // Lấy danh sách số điện thoại từ cơ sở dữ liệu
        return danhsachEmail.contains(EmailCanKiemTra);
    }

    boolean testData() {
        if (txtHoTen.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không bỏ trống họ tên khách hàng !");
            return false;
        } else if (!txtHoTen.getText().matches("^[a-zA-Z\\p{L}\\s]+$")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng định dạng họ tên!");
            return false;
        } else if (txtHoTen.getText().length() > 50) {
            JOptionPane.showMessageDialog(this, "Họ tên khách hàng không được dài quá 50 ký tự!");
            return false;
        }
        if (txtSoDT.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không bỏ trống số điện thoại khách hàng !");
            return false;
        } else if (!txtSoDT.getText().matches("(84|0[3|5|7|8|9])+([0-9]{8})")) {
            JOptionPane.showMessageDialog(this, "Số điện thoại nhập vào không đúng định dạng, vui lòng nhập lại!");
            return false;
        } else if (kiemTraSoDienThoaiTonTai(txtSoDT.getText())) {
            JOptionPane.showMessageDialog(this, "Số điện thoại đã tồn tại!");
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
        if (txtDiaChi.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không bỏ trống địa chỉ của khách hàng !");
            return false;
        } else if (!txtDiaChi.getText().matches("^[a-zA-Z\\p{L}\\s]+$")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng định dạng địa chỉ!");
            return false;
        } else if (txtDiaChi.getText().length() > 255) {
            JOptionPane.showMessageDialog(this, "Mật khẩu không được dài quá 255 ký tự!");
        }
        if (txtThanhPho.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không bỏ trống thành phố của khách hàng !");
            return false;
        } else if (!txtThanhPho.getText().matches("^[a-zA-Z\\p{L}\\s]+$")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng định dạng thành phố!");
            return false;
        } else if (txtThanhPho.getText().length() > 50) {
            JOptionPane.showMessageDialog(this, "Thành phố không được dài quá 255 ký tự!");
        }
        return true;
    }

    public boolean kiemTraDoDaiTen(String input) {
        if (input.length() < 2) {
            JOptionPane.showMessageDialog(this, "Tên khách hàng phải chứa tối thiểu 2 kí tự.");
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
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtHoTen = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtSoDT = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtDiaChi = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtThanhPho = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        rdoNam = new javax.swing.JRadioButton();
        rdoNu = new javax.swing.JRadioButton();
        jLabel13 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        tabs = new javax.swing.JTabbedPane();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbQLKH = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbDSHD = new javax.swing.JTable();
        jPanel9 = new javax.swing.JPanel();
        rdoSearchMale = new javax.swing.JRadioButton();
        rdoSearchAll = new javax.swing.JRadioButton();
        jLabel22 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        txtTimKiemKH = new javax.swing.JTextField();
        rdoSearchFemale = new javax.swing.JRadioButton();
        btnReset = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        txtTimKiemHD = new javax.swing.JTextField();
        btnDelete = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("QUẢN LÝ KHÁCH HÀNG");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin khách hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 16))); // NOI18N

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel3.setText("Họ tên");

        txtHoTen.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel11.setText("Số ĐT");

        txtSoDT.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel12.setText("Địa chỉ");

        txtDiaChi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel14.setText("Thành phố");

        txtThanhPho.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel15.setText("Giới tính");

        buttonGroup1.add(rdoNam);
        rdoNam.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        rdoNam.setText("Nam");

        buttonGroup1.add(rdoNu);
        rdoNu.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        rdoNu.setText("Nữ");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel13.setText("Email");

        txtEmail.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(137, 137, 137)
                                .addComponent(rdoNam)
                                .addGap(34, 34, 34)
                                .addComponent(rdoNu))
                            .addComponent(jLabel15))
                        .addGap(47, 47, 47))
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel5Layout.createSequentialGroup()
                            .addComponent(jLabel13)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(jLabel12)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel11))
                                .addGap(46, 46, 46)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtSoDT)
                                    .addComponent(txtHoTen)
                                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(txtThanhPho, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSoDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtThanhPho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(rdoNam)
                    .addComponent(rdoNu))
                .addGap(29, 29, 29))
        );

        tabs.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        tbQLKH.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã KH", "Họ và tên", "Giới tính", "Số điện thoại", "Email", "Địa chỉ", "Thành phố"
            }
        )
        {
            public boolean isCellEditable(int row, int column){
                return false;
            }
        }
    );
    tbQLKH.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            tbQLKHMouseClicked(evt);
        }
    });
    jScrollPane1.setViewportView(tbQLKH);

    javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
    jPanel6.setLayout(jPanel6Layout);
    jPanel6Layout.setHorizontalGroup(
        jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1010, Short.MAX_VALUE)
    );
    jPanel6Layout.setVerticalGroup(
        jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
    );

    tabs.addTab("Thông tin khách hàng", jPanel6);

    tbDSHD.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {
            {null, null, null, null, null},
            {null, null, null, null, null},
            {null, null, null, null, null},
            {null, null, null, null, null}
        },
        new String [] {
            "STT", "Mã hóa đơn", "Số lượng", "Giá", "Thành tiền"
        }
    ));
    jScrollPane2.setViewportView(tbDSHD);

    javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
    jPanel8.setLayout(jPanel8Layout);
    jPanel8Layout.setHorizontalGroup(
        jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1010, Short.MAX_VALUE)
    );
    jPanel8Layout.setVerticalGroup(
        jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
    );

    tabs.addTab("Danh sách hóa đơn", jPanel8);

    jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm kiếm khách hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 16))); // NOI18N
    jPanel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

    buttonGroup2.add(rdoSearchMale);
    rdoSearchMale.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
    rdoSearchMale.setText("Nam");
    rdoSearchMale.addItemListener(new java.awt.event.ItemListener() {
        public void itemStateChanged(java.awt.event.ItemEvent evt) {
            rdoSearchMaleItemStateChanged(evt);
        }
    });
    rdoSearchMale.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            rdoSearchMaleActionPerformed(evt);
        }
    });

    buttonGroup2.add(rdoSearchAll);
    rdoSearchAll.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
    rdoSearchAll.setText("Tất cả");
    rdoSearchAll.addItemListener(new java.awt.event.ItemListener() {
        public void itemStateChanged(java.awt.event.ItemEvent evt) {
            rdoSearchAllItemStateChanged(evt);
        }
    });

    jLabel22.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
    jLabel22.setText("Giới tính");

    jLabel24.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
    jLabel24.setText("Tìm kiếm");

    txtTimKiemKH.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
    txtTimKiemKH.addCaretListener(new javax.swing.event.CaretListener() {
        public void caretUpdate(javax.swing.event.CaretEvent evt) {
            txtTimKiemKHCaretUpdate(evt);
        }
    });
    txtTimKiemKH.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseEntered(java.awt.event.MouseEvent evt) {
            txtTimKiemKHMouseEntered(evt);
        }
    });
    txtTimKiemKH.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyReleased(java.awt.event.KeyEvent evt) {
            txtTimKiemKHKeyReleased(evt);
        }
    });

    buttonGroup2.add(rdoSearchFemale);
    rdoSearchFemale.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
    rdoSearchFemale.setText("Nữ");
    rdoSearchFemale.addItemListener(new java.awt.event.ItemListener() {
        public void itemStateChanged(java.awt.event.ItemEvent evt) {
            rdoSearchFemaleItemStateChanged(evt);
        }
    });
    rdoSearchFemale.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            rdoSearchFemaleActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
    jPanel9.setLayout(jPanel9Layout);
    jPanel9Layout.setHorizontalGroup(
        jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel9Layout.createSequentialGroup()
            .addGap(16, 16, 16)
            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel24)
                .addComponent(jLabel22))
            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel9Layout.createSequentialGroup()
                    .addGap(33, 33, 33)
                    .addComponent(rdoSearchMale)
                    .addGap(18, 18, 18)
                    .addComponent(rdoSearchFemale)
                    .addGap(18, 18, 18)
                    .addComponent(rdoSearchAll))
                .addGroup(jPanel9Layout.createSequentialGroup()
                    .addGap(18, 18, 18)
                    .addComponent(txtTimKiemKH, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addContainerGap(21, Short.MAX_VALUE))
    );
    jPanel9Layout.setVerticalGroup(
        jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel9Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel24)
                .addComponent(txtTimKiemKH, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel22)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdoSearchAll, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdoSearchMale)
                    .addComponent(rdoSearchFemale, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addContainerGap(16, Short.MAX_VALUE))
    );

    btnReset.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
    btnReset.setText("Làm mới");
    btnReset.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnResetActionPerformed(evt);
        }
    });

    btnAdd.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
    btnAdd.setText("Thêm mới");
    btnAdd.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnAddActionPerformed(evt);
        }
    });

    btnUpdate.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
    btnUpdate.setText("Chỉnh sửa");
    btnUpdate.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnUpdateActionPerformed(evt);
        }
    });

    jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm kiếm hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 16))); // NOI18N

    jLabel26.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
    jLabel26.setText("Tìm kiếm");

    txtTimKiemHD.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
    txtTimKiemHD.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyReleased(java.awt.event.KeyEvent evt) {
            txtTimKiemHDKeyReleased(evt);
        }
    });

    javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
    jPanel11.setLayout(jPanel11Layout);
    jPanel11Layout.setHorizontalGroup(
        jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel11Layout.createSequentialGroup()
            .addGap(16, 16, 16)
            .addComponent(jLabel26)
            .addGap(18, 18, 18)
            .addComponent(txtTimKiemHD, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    jPanel11Layout.setVerticalGroup(
        jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel11Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel26)
                .addComponent(txtTimKiemHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addContainerGap(16, Short.MAX_VALUE))
    );

    btnDelete.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
    btnDelete.setText("Xóa");
    btnDelete.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnDeleteActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
    jPanel4.setLayout(jPanel4Layout);
    jPanel4Layout.setHorizontalGroup(
        jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel4Layout.createSequentialGroup()
            .addGap(38, 38, 38)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(tabs)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addGap(38, 38, 38))
    );
    jPanel4Layout.setVerticalGroup(
        jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel4Layout.createSequentialGroup()
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(btnReset)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(btnAdd)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(btnUpdate)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(btnDelete)
                    .addGap(93, 93, 93))
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addGap(48, 48, 48)
                            .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addComponent(tabs, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(16, 16, 16))
    );

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );
    jPanel1Layout.setVerticalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addContainerGap())
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );
    }// </editor-fold>//GEN-END:initComponents

    private void rdoSearchMaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoSearchMaleActionPerformed

    }//GEN-LAST:event_rdoSearchMaleActionPerformed

    private void tbQLKHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbQLKHMouseClicked
        int rowSelected = this.tbQLKH.getSelectedRow();
        if (dtm.getRowCount() > 0 && rowSelected >= 0) {
            if (evt.getClickCount() == 1) {
                showData(rowSelected);
            } else if (evt.getClickCount() == 2) {
                int idKH = Integer.parseInt(this.tbQLKH.getValueAt(rowSelected, 0).toString());
                fillTableHD(idKH);
                tabs.setSelectedIndex(1);
            }
        }
    }//GEN-LAST:event_tbQLKHMouseClicked

    private void rdoSearchMaleItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rdoSearchMaleItemStateChanged
        fillGioiTinhNam(khachHangRepository.fillGenderMale());
    }//GEN-LAST:event_rdoSearchMaleItemStateChanged

    private void rdoSearchAllItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rdoSearchAllItemStateChanged
        fillTable(khachHangRepository.getAll());
    }//GEN-LAST:event_rdoSearchAllItemStateChanged

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        clearForm();
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        int choose = JOptionPane.showConfirmDialog(this, "Bạn có muốn thêm thông tin khách hàng ?", "Confirm", JOptionPane.YES_NO_OPTION);
        if (choose == JOptionPane.NO_OPTION) {
            return;
        }
        if (testData() && kiemTraDoDaiTen(txtHoTen.getText())) {
            KhachHang kh = this.readForm();
            if (khachHangRepository.insertKH(kh) > 0) {
                    JOptionPane.showMessageDialog(this, "Thêm thành công khách hàng mới !");
                    fillTable(khachHangRepository.getAll());
                } else {
                    JOptionPane.showMessageDialog(this, "Thêm không thành công !");
                }
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        KhachHang kh = this.readForm();
        row = tbQLKH.getSelectedRow();
        int sttDuocChon = tbQLKH.getSelectedRow();
        if (sttDuocChon == -1) {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn khách hàng nào để cập nhật !");
            return;
        }
        Object idKHObj = tbQLKH.getValueAt(row, 0);
        int idKH;
        try {
            idKH = Integer.parseInt(idKHObj.toString());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID khách hàng không hợp lệ!");
            return;
        }
        int choose = JOptionPane.showConfirmDialog(this, "Bạn có muốn chỉnh sửa thông tin khách hàng ?", "Confirm", JOptionPane.YES_NO_OPTION);
        if (choose == JOptionPane.NO_OPTION) {
            return;
        }
        if (check() && kiemTraDoDaiTen(txtHoTen.getText())) {
            if (khachHangRepository.updateKH(kh, idKH) > 0) {
                JOptionPane.showMessageDialog(this, "Chỉnh sửa thành công thông tin khách hàng !");
                fillTable(khachHangRepository.getAll());
            } else {
                JOptionPane.showMessageDialog(this, "Chỉnh sửa thông tin không thành công !");
            }
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        row = tbQLKH.getSelectedRow();
        int sttDuocChon = tbQLKH.getSelectedRow();
        if (sttDuocChon == -1) {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn khách hàng nào !");
            return;
        }
        String idKHString = (String) tbQLKH.getValueAt(row, 1);
        String numericPart = idKHString.substring(3);
        int idKH = Integer.parseInt(numericPart);

        int choose = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa khách hàng này ?", "Confirm", JOptionPane.YES_NO_OPTION);
        if (choose == JOptionPane.NO_OPTION) {
            return;
        }
        if (khachHangRepository.deleteKH(idKH) > 0) {
            JOptionPane.showMessageDialog(this, "Xoá thành công khách hàng đã chọn !");
            this.fillTable(khachHangRepository.getAll());
        } else {
            JOptionPane.showMessageDialog(this, "Xoá không thành công !");
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void txtTimKiemKHKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKHKeyReleased
        String search = txtTimKiemKH.getText();
        dtm = (DefaultTableModel) tbQLKH.getModel();
        search(search, dtm, tbQLKH);
    }//GEN-LAST:event_txtTimKiemKHKeyReleased

    private void txtTimKiemKHCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTimKiemKHCaretUpdate

    }//GEN-LAST:event_txtTimKiemKHCaretUpdate

    private void txtTimKiemKHMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTimKiemKHMouseEntered

    }//GEN-LAST:event_txtTimKiemKHMouseEntered

    private void rdoSearchFemaleItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rdoSearchFemaleItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoSearchFemaleItemStateChanged

    private void rdoSearchFemaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoSearchFemaleActionPerformed
        fillGioiTinhNu(khachHangRepository.fillGenderFemale());
    }//GEN-LAST:event_rdoSearchFemaleActionPerformed

    private void txtTimKiemHDKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemHDKeyReleased
        String search = txtTimKiemHD.getText();
        dtm = (DefaultTableModel) tbDSHD.getModel();
        searchHD(search, dtm, tbDSHD);
    }//GEN-LAST:event_txtTimKiemHDKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnUpdate;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNu;
    private javax.swing.JRadioButton rdoSearchAll;
    private javax.swing.JRadioButton rdoSearchFemale;
    private javax.swing.JRadioButton rdoSearchMale;
    private javax.swing.JTabbedPane tabs;
    private javax.swing.JTable tbDSHD;
    private javax.swing.JTable tbQLKH;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtSoDT;
    private javax.swing.JTextField txtThanhPho;
    private javax.swing.JTextField txtTimKiemHD;
    private javax.swing.JTextField txtTimKiemKH;
    // End of variables declaration//GEN-END:variables
}
