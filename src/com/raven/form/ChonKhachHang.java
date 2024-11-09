package com.raven.form;

import com.raven.classmodel.KhachHang;
import com.raven.repository.KhachHangRepository;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Hoai Thu
 */
public class ChonKhachHang extends javax.swing.JDialog {

    private KhachHangRepository khachHangRepository = new KhachHangRepository();
    private DefaultTableModel dtm = new DefaultTableModel();
    private FormBanHang fbh;

    public ChonKhachHang(java.awt.Frame parent, boolean modal, FormBanHang fbh) {
        super(parent, modal);
        this.fbh = fbh;
        initComponents();
        fillTable(khachHangRepository.getAll());
        setTitle("Chọn khách hàng");
        setLocationRelativeTo(null);
    }

    void fillTable(List<KhachHang> listkh) {
        dtm = (DefaultTableModel) tbKhachHang.getModel();
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

    void chonKhachHang() {
        int row = tbKhachHang.getSelectedRow();
        if (row >= 0) {
            KhachHang kh = khachHangRepository.getAll().get(row);
            String maKH = tbKhachHang.getValueAt(row, 1).toString();
            String tenKH = tbKhachHang.getValueAt(row, 2).toString();
            String soDT = tbKhachHang.getValueAt(row, 4).toString();
            if (fbh != null) {
                fbh.chonKH(maKH, tenKH, soDT);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Form bán hàng chưa được khởi tạo!");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn khách hàng nào!");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbKhachHang = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtTimKiemKhachHang = new javax.swing.JTextField();
        btnChonKhachHang = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jTabbedPane1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        tbKhachHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã KH", "Họ tên", "Giới tính", "Số điện thoại", "Email", "Địa chỉ", "Thành phố"
            }
        ));
        jScrollPane1.setViewportView(tbKhachHang);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel1.setText("Tìm kiếm");

        txtTimKiemKhachHang.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        btnChonKhachHang.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnChonKhachHang.setText("Chọn khách hàng");
        btnChonKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnChonKhachHangMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jLabel1)
                        .addGap(26, 26, 26)
                        .addComponent(txtTimKiemKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 434, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(btnChonKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 762, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtTimKiemKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnChonKhachHang))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
                .addGap(19, 19, 19))
        );

        jTabbedPane1.addTab("Danh sách khách hàng", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnChonKhachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnChonKhachHangMouseClicked
        this.chonKhachHang();
    }//GEN-LAST:event_btnChonKhachHangMouseClicked
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FormBanHang formBanHang = new FormBanHang();
                ChonKhachHang dialog = new ChonKhachHang(new javax.swing.JFrame(), true, formBanHang);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChonKhachHang;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tbKhachHang;
    private javax.swing.JTextField txtTimKiemKhachHang;
    // End of variables declaration//GEN-END:variables
}
