/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.raven.form;

import com.raven.classmodel.DoCung;
import com.raven.classmodel.DoDay;
import com.raven.classmodel.GetChucVu;
import com.raven.classmodel.KichThuoc;
import com.raven.classmodel.LoaiDem;
import com.raven.classmodel.MauSac;
import com.raven.classmodel.SanPham;
import com.raven.classmodel.ThietKe;
import com.raven.classmodel.ThuongHieu;
import com.raven.classmodel.XuatXu;
import com.raven.repository.DoCungRepository;
import com.raven.repository.DoDayRepository;
import com.raven.repository.KichThuocRepository;
import com.raven.repository.LoaiDemRepository;
import com.raven.repository.MauSacRepository;
import com.raven.repository.SanPhamRepository;
import com.raven.repository.ThietKeRepository;
import com.raven.repository.ThuongHieuRepository;
import com.raven.repository.XuatXuRepository;
import java.awt.Frame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author Hoai Thu
 */
public class ThemNhanhThuocTinh extends javax.swing.JDialog {

    private DoCungRepository doCungRepository = new DoCungRepository();
    private DoDayRepository doDayRepository = new DoDayRepository();
    private KichThuocRepository kichThuocRepository = new KichThuocRepository();
    private LoaiDemRepository loaiDemRepository = new LoaiDemRepository();
    private MauSacRepository mauSacRepository = new MauSacRepository();
    private ThietKeRepository thietKeRepository = new ThietKeRepository();
    private ThuongHieuRepository thuongHieuRepository = new ThuongHieuRepository();
    private XuatXuRepository xuatXuRepository = new XuatXuRepository();
    private SanPhamRepository sanPhamRepository = new SanPhamRepository();
    public static String tenThuocTinh;
    FormSanPham formSanPham = new FormSanPham();

    public ThemNhanhThuocTinh(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setTitle("Thêm nhanh thuộc tính");
        setLocationRelativeTo(null);
          if(GetChucVu.getChucVu()==0){
          btnThemNhanhThuocTinh.setEnabled(true);
        }
        else if(GetChucVu.getChucVu()==1) {
            
            btnThemNhanhThuocTinh.setEnabled(false);
            
        }
        
    }

    boolean validateThuocTinh() {
        String tenThuocTinh = txtTenThuocTinhThemNhanh.getText().trim();
        if (tenThuocTinh.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng điền tên thuộc tính muốn thêm !");
            return false;
        }

        if (tenThuocTinh.length() > 50) {
            JOptionPane.showMessageDialog(this, "Không được điền lớn hơn 50 ký tự!");
            return false;
        }

        if (doCungRepository.kiemTraDoCungDaTonTai(tenThuocTinh)
                || doDayRepository.kiemTraDoDayDaTonTai(tenThuocTinh)
                || kichThuocRepository.kiemTraKichThuocDaTonTai(tenThuocTinh)
                || loaiDemRepository.kiemTraLoaiDemDaTonTai(tenThuocTinh)
                || mauSacRepository.kiemTraMauSacDaTonTai(tenThuocTinh)
                || thietKeRepository.kiemTraThietKeDaTonTai(tenThuocTinh)
                || thuongHieuRepository.kiemTraThuongHieuDaTonTai(tenThuocTinh)
                || xuatXuRepository.kiemTraXuatXuDaTonTai(tenThuocTinh)) {

            JOptionPane.showMessageDialog(this, "Tên thuộc tính đã tồn tại, vui lòng nhập tên khác !");
            return false;
        }
        return true;
    }

    boolean isValidTenThuocTinh(String tenThuocTinh) {
        return tenThuocTinh.matches("\\p{L}+");
    }

    DoCung readFormDoCung() {
        return new DoCung(txtTenThuocTinhThemNhanh.getText().trim(), true);
    }

    DoDay readFormDoDay() {
        return new DoDay(txtTenThuocTinhThemNhanh.getText().trim(), true);
    }

    KichThuoc readFormKichThuoc() {
        return new KichThuoc(txtTenThuocTinhThemNhanh.getText().trim(), true);
    }

    LoaiDem readFormLoaiDem() {
        return new LoaiDem(txtTenThuocTinhThemNhanh.getText().trim(), true);
    }

    MauSac readFormMauSac() {
        return new MauSac(txtTenThuocTinhThemNhanh.getText().trim(), true);
    }

    ThietKe readFormThietKe() {
        return new ThietKe(txtTenThuocTinhThemNhanh.getText().trim(), true);
    }

    ThuongHieu readFormThuongHieu() {
        return new ThuongHieu(txtTenThuocTinhThemNhanh.getText().trim(), true);
    }

    XuatXu readFormXuatXu() {
        return new XuatXu(txtTenThuocTinhThemNhanh.getText().trim(), true);
    }
    
    SanPham readFormSanPham(){
        return new SanPham(txtTenThuocTinhThemNhanh.getText().trim(), true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnThemNhanhThuocTinh = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtTenThuocTinhThemNhanh = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        btnThemNhanhThuocTinh.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnThemNhanhThuocTinh.setText("Thêm mới");
        btnThemNhanhThuocTinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemNhanhThuocTinhActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel1.setText("Tên thuộc tính");

        txtTenThuocTinhThemNhanh.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel1)
                .addGap(49, 49, 49)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnThemNhanhThuocTinh)
                    .addComponent(txtTenThuocTinhThemNhanh, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtTenThuocTinhThemNhanh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnThemNhanhThuocTinh)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemNhanhThuocTinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemNhanhThuocTinhActionPerformed
        if (validateThuocTinh()) {
            int choice = JOptionPane.showConfirmDialog(this, "Bạn có muốn thêm không ?", "Thêm thuộc tính", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                switch (ThemNhanhThuocTinh.tenThuocTinh) {
                    case "DoCung":
                        doCungRepository.themDC(readFormDoCung());
                        formSanPham.fillCboDoCung();
                        formSanPham.fillTableDoCung(doCungRepository.getAll());
                        break;
                    case "DoDay":
                        doDayRepository.themDD(readFormDoDay());
                        formSanPham.fillCboDoDay();
                        formSanPham.fillTableDoDay(doDayRepository.getAll());
                        break;
                    case "KichThuoc":
                        kichThuocRepository.themKT(readFormKichThuoc());
                        formSanPham.fillCboKichThuoc();
                        formSanPham.fillTableKichThuoc(kichThuocRepository.getAll());
                        break;
                    case "LoaiDem":
                        loaiDemRepository.themLD(readFormLoaiDem());
                        formSanPham.fillCboLoaiDem();
                        formSanPham.fillTableLoaiDem(loaiDemRepository.getAll());
                        break;
                    case "MauSac":
                        mauSacRepository.themMS(readFormMauSac());
                        formSanPham.fillCboMauSac();
                        formSanPham.fillTableMauSac(mauSacRepository.getAll());
                        break;
                    case "ThietKe":
                        thietKeRepository.themTK(readFormThietKe());
                        formSanPham.fillCboThietKe();
                        formSanPham.fillTableThietKe(thietKeRepository.getAll());
                        break;
                    case "ThuongHieu":
                        thuongHieuRepository.themTH(readFormThuongHieu());
                        formSanPham.fillCboThuongHieu();
                        formSanPham.fillTableThuongHieu(thuongHieuRepository.getAll());
                        break;
                    case "XuatXu":
                        xuatXuRepository.themXX(readFormXuatXu());
                        formSanPham.fillCboXuatXu();
                        formSanPham.fillTableXuatXu(xuatXuRepository.getAll());
                        break;
                    case "SanPham":
                        sanPhamRepository.insertSanPham(readFormSanPham());
                        formSanPham.fillCboTenSanPham();
                        formSanPham.fillTableDSSP(sanPhamRepository.fillSP());
                }
                JOptionPane.showMessageDialog(this, "Thêm thành công !");
            }
        }
    }//GEN-LAST:event_btnThemNhanhThuocTinhActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ThemNhanhThuocTinh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ThemNhanhThuocTinh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ThemNhanhThuocTinh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ThemNhanhThuocTinh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ThemNhanhThuocTinh dialog = new ThemNhanhThuocTinh(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnThemNhanhThuocTinh;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField txtTenThuocTinhThemNhanh;
    // End of variables declaration//GEN-END:variables
}
