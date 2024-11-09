package com.raven.form;

import com.raven.classmodel.ChiTietSanPham;
import com.raven.classmodel.HoaDon;
import com.raven.classmodel.HoaDonChiTiet;
import com.raven.classmodel.SanPham;
import com.raven.classmodel.ThongKe;
import com.raven.repository.HoaDonChiTietRepository;
import com.raven.repository.ThongKeRepository;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class FormThongKe extends javax.swing.JPanel {

    List<SanPham> listctsp = new ArrayList<>();
    List<HoaDon> listHDTK = new ArrayList<>();
    ThongKeRepository thongKeRepo = new ThongKeRepository();
    HoaDonChiTietRepository hoaDonChiTietRepository = new HoaDonChiTietRepository();
    List<HoaDonChiTiet> listHDCT = new ArrayList<>();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    DecimalFormat currencyFormat = new DecimalFormat("#,##0");
    DefaultTableModel model = new DefaultTableModel();

    public FormThongKe() {
        initComponents();
        fillDoanhThu();
        //fillDonHang();
        fillTableSP(listctsp);
    }

    void fillDoanhThu() {
        double doanhThuTheoNgay = thongKeRepo.getDoanhThuTheoNgayHienTai();
        txtDoanhThuTheoNgay.setText(currencyFormat.format(doanhThuTheoNgay));
        double doanhThuTheoThang = thongKeRepo.getDoanhThuTheoThangHienTai();
        txtDoanhThuTheoThang.setText(currencyFormat.format(doanhThuTheoThang));
        double doanhThuTheoNam = thongKeRepo.getDoanhThuTheoNamHienTai();
        txtDoanhThuTheoNam.setText(currencyFormat.format(doanhThuTheoNam));
    }

//    void fillDonHang(){
//       int tongSoLuongDonHang = thongKeRepo.getTongSoLuongDonHang();
//        txtTongDonHang.setText(String.valueOf(tongSoLuongDonHang));
//        int soDonDaThanhToan = thongKeRepo.getHoaDonDaThanhToan();
//        txtDaThanhToan.setText(String.valueOf(soDonDaThanhToan));
//        int soDonDaHuy = thongKeRepo.getHoaDonDaHuy();
//        txtDaHuy.setText(String.valueOf(soDonDaHuy));
//    }
    public void fillTableSP(List<SanPham> listctsp) {
        model = (DefaultTableModel) tbSanPhamThongKe.getModel();
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String cols[] = {
            "STT",
            "Mã CTSP",
            "Tên sản phẩm",
            "Loại đệm",
            "Xuất xứ",
            "Thương hiệu",
            "Số lượng",
            "Giá bán",
            "Ngày sản xuất",
            "Năm bảo hành",
            "Trạng thái"
        };
        model.setColumnIdentifiers(cols);
        model.setRowCount(0);
        int i = 1;
        for (SanPham sp : listctsp) {
            Object[] row = {
                i++,
                "SPCT00" + sp.getCtsp().getId(),
                sp.getTenSP(),
                sp.getLoaiDem().getTenLD(),
                sp.getXuatXu().getTenXX(),
                sp.getThuongHieu().getTenTH(),
                sp.getCtsp().getSoLuong(),
                formatter.format(sp.getCtsp().getGiaBan()) + " VND",
                sdf.format(sp.getCtsp().getNgaySanXuat()),
                sp.getCtsp().getBaoHanh(),
                sp.getCtsp().getSoLuong() > 1 ? "Còn hàng" : "Hết hàng"
            };
            model.addRow(row);
        }
        tbSanPhamThongKe.setModel(model);
    }

    public void search(String str, DefaultTableModel model, JTable table) {
        model = (DefaultTableModel) tbSanPhamThongKe.getModel();
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

    public void LocNgayThongke() {
        Date timeBegin = JDateNgayBatDau.getDate();
        Date timeEnd = JDateNgayKetThuc.getDate();

        if (timeBegin == null || timeEnd == null) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn cả ngày bắt đầu và ngày kết thúc.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (timeBegin.after(timeEnd)) {
            JOptionPane.showMessageDialog(null, "Ngày bắt đầu phải nhỏ hơn hoặc bằng ngày kết thúc.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Double doanhThu = thongKeRepo.getDoanhThuTheoNgay(timeBegin, timeEnd);
        txtDoanhThu.setText(currencyFormat.format(doanhThu));
        int tongSoLuongDonHang = thongKeRepo.getTongDon(timeBegin, timeEnd);
        txtTongDonHang.setText(String.valueOf(tongSoLuongDonHang));
        int soDonDaThanhToan = thongKeRepo.getHoaDonDaThanhToan(timeBegin, timeEnd);
        txtDaThanhToan.setText(String.valueOf(soDonDaThanhToan));
        int soDonDaHuy = thongKeRepo.getHoaDonDaHuy(timeBegin, timeEnd);
        txtDaHuy.setText(String.valueOf(soDonDaHuy));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane = new javax.swing.JTabbedPane();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbSanPhamThongKe = new javax.swing.JTable();
        jPanel17 = new javax.swing.JPanel();
        cboSapXepSP = new javax.swing.JComboBox<>();
        jLabel28 = new javax.swing.JLabel();
        txtTimKiem = new javax.swing.JTextField();
        jLabel52 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        txtTongDonHang = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        txtDaThanhToan = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        txtDaHuy = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        txtDoanhThu = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        txtDoanhThuTheoNgay = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jPanel21 = new javax.swing.JPanel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        txtDoanhThuTheoThang = new javax.swing.JLabel();
        jPanel22 = new javax.swing.JPanel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        txtDoanhThuTheoNam = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        btnLoc = new javax.swing.JButton();
        jLabel55 = new javax.swing.JLabel();
        JDateNgayBatDau = new com.toedter.calendar.JDateChooser();
        JDateNgayKetThuc = new com.toedter.calendar.JDateChooser();

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        tbSanPhamThongKe.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tbSanPhamThongKe.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane4.setViewportView(tbSanPhamThongKe);

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));

        cboSapXepSP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "----------", "Số lượng", "Đơn giá" }));
        cboSapXepSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboSapXepSPActionPerformed(evt);
            }
        });

        jLabel28.setText("Sắp xếp theo");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboSapXepSP, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(94, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addContainerGap(42, Short.MAX_VALUE)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboSapXepSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17))
        );

        txtTimKiem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemActionPerformed(evt);
            }
        });
        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyReleased(evt);
            }
        });

        jLabel52.setText("Tìm kiếm");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(210, 210, 210)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 1030, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(19, 19, 19)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane.addTab("Sản phẩm", jPanel8);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thống kê", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18))); // NOI18N

        jPanel19.setBackground(new java.awt.Color(204, 51, 0));
        jPanel19.setForeground(new java.awt.Color(255, 204, 204));
        jPanel19.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel19.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 30, -1, -1));

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 255, 255));
        jLabel32.setText("Tổng đơn hàng");
        jPanel19.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, -1, -1));

        txtTongDonHang.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        txtTongDonHang.setForeground(new java.awt.Color(255, 255, 255));
        txtTongDonHang.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtTongDonHang.setText("0");
        txtTongDonHang.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                txtTongDonHangComponentShown(evt);
            }
        });
        jPanel19.add(txtTongDonHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(126, 6, -1, -1));

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setText("đơn hàng");
        jPanel19.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(154, 6, -1, -1));

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(255, 255, 255));
        jLabel34.setText("Đã thanh toán");
        jPanel19.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

        txtDaThanhToan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtDaThanhToan.setForeground(new java.awt.Color(255, 255, 255));
        txtDaThanhToan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtDaThanhToan.setText("0");
        txtDaThanhToan.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                txtDaThanhToanComponentShown(evt);
            }
        });
        jPanel19.add(txtDaThanhToan, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 40, 41, 15));

        jLabel41.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(255, 255, 255));
        jLabel41.setText("Bị hủy:");
        jPanel19.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, -1));

        txtDaHuy.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtDaHuy.setForeground(new java.awt.Color(255, 255, 255));
        txtDaHuy.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtDaHuy.setText("0");
        txtDaHuy.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                txtDaHuyComponentShown(evt);
            }
        });
        jPanel19.add(txtDaHuy, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 70, 45, 19));

        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(255, 255, 255));
        jLabel35.setText("Doanh thu");
        jPanel19.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, -1, -1));

        txtDoanhThu.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        txtDoanhThu.setForeground(new java.awt.Color(255, 255, 255));
        txtDoanhThu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtDoanhThu.setText("0");
        txtDoanhThu.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                txtDoanhThuComponentShown(evt);
            }
        });
        jPanel19.add(txtDoanhThu, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 100, -1, -1));

        jLabel36.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(255, 255, 255));
        jLabel36.setText("VNĐ");
        jPanel19.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 100, -1, -1));

        jPanel20.setBackground(new java.awt.Color(0, 51, 153));
        jPanel20.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel20.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 30, -1, -1));

        jLabel43.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(255, 255, 255));
        jLabel43.setText("Doanh thu trong ngày");
        jPanel20.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        txtDoanhThuTheoNgay.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        txtDoanhThuTheoNgay.setForeground(new java.awt.Color(255, 255, 255));
        txtDoanhThuTheoNgay.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtDoanhThuTheoNgay.setText("0");
        jPanel20.add(txtDoanhThuTheoNgay, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 97, -1));

        jLabel44.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(255, 255, 255));
        jLabel44.setText("VNĐ");
        jPanel20.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 50, -1, -1));

        jPanel21.setBackground(new java.awt.Color(0, 102, 102));

        jLabel45.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(255, 255, 255));
        jLabel45.setText("Doanh thu trong tháng");

        jLabel46.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(255, 255, 255));
        jLabel46.setText("VNĐ");

        txtDoanhThuTheoThang.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        txtDoanhThuTheoThang.setForeground(new java.awt.Color(255, 255, 255));
        txtDoanhThuTheoThang.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtDoanhThuTheoThang.setText("0");

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtDoanhThuTheoThang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel46)
                .addGap(22, 22, 22))
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel45)
                .addGap(44, 44, 44)
                .addComponent(jLabel47)
                .addContainerGap())
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jLabel47))
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jLabel45)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDoanhThuTheoThang, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel46))))
                .addContainerGap(61, Short.MAX_VALUE))
        );

        jPanel22.setBackground(new java.awt.Color(102, 0, 102));

        jLabel49.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(255, 255, 255));
        jLabel49.setText("Doanh thu trong năm");

        jLabel50.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(255, 255, 255));
        jLabel50.setText("VNĐ");

        txtDoanhThuTheoNam.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        txtDoanhThuTheoNam.setForeground(new java.awt.Color(255, 255, 255));
        txtDoanhThuTheoNam.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtDoanhThuTheoNam.setText("0");

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel49)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel48)
                .addContainerGap())
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addComponent(txtDoanhThuTheoNam, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel50)
                .addGap(0, 24, Short.MAX_VALUE))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jLabel48))
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDoanhThuTheoNam, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel50))
                .addContainerGap(65, Short.MAX_VALUE))
        );

        jLabel51.setText("Ngày bắt đầu");

        btnLoc.setText("Lọc");
        btnLoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocActionPerformed(evt);
            }
        });

        jLabel55.setText("Ngày kết thúc");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(JDateNgayBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(70, 70, 70)
                        .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(JDateNgayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(51, 51, 51)
                        .addComponent(btnLoc))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnLoc, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(JDateNgayBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JDateNgayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 1055, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 20, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cboSapXepSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboSapXepSPActionPerformed
        txtTimKiem.setText("");
        String selectedOption = (String) cboSapXepSP.getSelectedItem();
        if (selectedOption != null) {
            List<SanPham> sortedList;
            if (selectedOption.equals("Số lượng")) {
                sortedList = thongKeRepo.getAllSanPhamTheoSoLuong();
            } else if (selectedOption.equals("Đơn giá")) {
                sortedList = thongKeRepo.getAllSanPhamTheoGia();
            } else {
                sortedList = thongKeRepo.getAllSanPham();
            }
            fillTableSP(sortedList);
        }

    }//GEN-LAST:event_cboSapXepSPActionPerformed

    private void txtTongDonHangComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_txtTongDonHangComponentShown

    }//GEN-LAST:event_txtTongDonHangComponentShown

    private void txtDaThanhToanComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_txtDaThanhToanComponentShown

    }//GEN-LAST:event_txtDaThanhToanComponentShown

    private void txtDaHuyComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_txtDaHuyComponentShown

    }//GEN-LAST:event_txtDaHuyComponentShown

    private void btnLocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocActionPerformed
        LocNgayThongke();
    }//GEN-LAST:event_btnLocActionPerformed

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
        String search = txtTimKiem.getText();
        model = (DefaultTableModel) tbSanPhamThongKe.getModel();
        search(search, model, tbSanPhamThongKe);
    }//GEN-LAST:event_txtTimKiemKeyReleased

    private void txtTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemActionPerformed

    }//GEN-LAST:event_txtTimKiemActionPerformed

    private void txtDoanhThuComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_txtDoanhThuComponentShown
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDoanhThuComponentShown


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser JDateNgayBatDau;
    private com.toedter.calendar.JDateChooser JDateNgayKetThuc;
    private javax.swing.JButton btnLoc;
    private javax.swing.JComboBox<String> cboSapXepSP;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane;
    private javax.swing.JTable tbSanPhamThongKe;
    private javax.swing.JLabel txtDaHuy;
    private javax.swing.JLabel txtDaThanhToan;
    private javax.swing.JLabel txtDoanhThu;
    private javax.swing.JLabel txtDoanhThuTheoNam;
    private javax.swing.JLabel txtDoanhThuTheoNgay;
    private javax.swing.JLabel txtDoanhThuTheoThang;
    private javax.swing.JTextField txtTimKiem;
    private javax.swing.JLabel txtTongDonHang;
    // End of variables declaration//GEN-END:variables
}
