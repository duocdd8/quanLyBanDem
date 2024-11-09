/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.raven.form;

import com.raven.classmodel.GetChucVu;
import com.raven.classmodel.HoaDon;
import com.raven.classmodel.HoaDonChiTiet;
import com.raven.repository.HoaDonChiTietRepository;
import com.raven.repository.HoaDonRepository;
import com.raven.repository.ViewLichSuHoaDonRepository;
import com.raven.viewmodel.ViewLichSuHoaDon;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
public class FormHoaDon extends javax.swing.JPanel {

    public FormHoaDon() {
        initComponents();
        fillHoadon(hdSer.getAll());
        addcbo();
        if (GetChucVu.getChucVu() == 1) {
            btnXuatExcel.setEnabled(false);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtTimKiem = new javax.swing.JTextField();
        btnXuatExcel = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        cbotrangThai = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        rboTienMat = new javax.swing.JRadioButton();
        rboChuyenKhoan = new javax.swing.JRadioButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHoadon = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblhoadonchitiet = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbllichsuhoadon = new javax.swing.JTable();

        jTabbedPane1.setBackground(java.awt.Color.white);

        jPanel1.setBackground(java.awt.Color.white);

        jPanel3.setBackground(java.awt.Color.white);
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm kiếm", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        jLabel1.setText("Tìm kiếm");

        txtTimKiem.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTimKiemCaretUpdate(evt);
            }
        });
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

        btnXuatExcel.setText("Xuất excel");
        btnXuatExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatExcelActionPerformed(evt);
            }
        });

        jLabel2.setText("Trạng thái");

        cbotrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbotrangThai.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbotrangThaiItemStateChanged(evt);
            }
        });
        cbotrangThai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbotrangThaiActionPerformed(evt);
            }
        });

        jLabel3.setText("Hình  thức thanh toán");

        buttonGroup1.add(rboTienMat);
        rboTienMat.setText("Tiền mặt");
        rboTienMat.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rboTienMatItemStateChanged(evt);
            }
        });

        buttonGroup1.add(rboChuyenKhoan);
        rboChuyenKhoan.setText("Chuyển khoản");
        rboChuyenKhoan.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rboChuyenKhoanItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbotrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(65, 65, 65)
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(rboTienMat)
                        .addGap(37, 37, 37)
                        .addComponent(rboChuyenKhoan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnXuatExcel))
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 727, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cbotrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(rboTienMat)
                    .addComponent(rboChuyenKhoan)
                    .addComponent(btnXuatExcel))
                .addContainerGap(9, Short.MAX_VALUE))
        );

        jPanel4.setBackground(java.awt.Color.white);
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        tblHoadon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã hóa đơn", "Tên khách hàng", "Số điện thoại", "Địa chỉ", "Tổng tiền", "Trạng thái"
            }
        ));
        tblHoadon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoadonMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblHoadon);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(7, Short.MAX_VALUE))
        );

        jPanel5.setBackground(java.awt.Color.white);
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hóa đơn chi tiết", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        tblhoadonchitiet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Tên sản phẩm", "Thương hiệu", "Kích cỡ", "Màu sắc", "Số lượng", "Đơn giá"
            }
        ));
        jScrollPane2.setViewportView(tblhoadonchitiet);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(11, Short.MAX_VALUE))
        );

        jPanel6.setBackground(java.awt.Color.white);
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lịch sử hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        tbllichsuhoadon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã hóa đơn", "Nhân viên", "Khách hàng", "Ngày thanh toán", "Ngày tạo"
            }
        ));
        jScrollPane3.setViewportView(tbllichsuhoadon);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 578, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(110, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Hóa đơn", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblHoadonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoadonMouseClicked
        int selectedRow = tblHoadon.getSelectedRow();
        if (selectedRow < 0) {
            return;
        }
        DefaultTableModel tableModel = (DefaultTableModel) tblHoadon.getModel();
        String idString = tableModel.getValueAt(selectedRow, 1).toString();
        String numericPart = idString.replaceAll("[^0-9]", "");
        int id = Integer.parseInt(numericPart);
        ArrayList<HoaDonChiTiet> hd = hdctSer.getTimhdct(id);
        ViewLichSuHoaDon vls = vlsSer.getLichsu(id);
        ArrayList<ViewLichSuHoaDon> listls = new ArrayList<>();
        if (hd == null) {
            return;
        } else {
            listls.add(vls);
            fillHoadonchitiet(hd);
            fillLichsuhoadon(listls);
        }
    }//GEN-LAST:event_tblHoadonMouseClicked

    private void rboChuyenKhoanItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rboChuyenKhoanItemStateChanged
        int ht = rboTienMat.isSelected() == true ? 2 : 1;
        ArrayList<HoaDon> hd = hdSer.getRbo(ht);
        if (hd != null) {
            fillHoadon(hd);
            return;
        } else {
            JOptionPane.showMessageDialog(this, "Không có hóa đơn nào thanh toán bằng chuyển khoản");
        }
    }//GEN-LAST:event_rboChuyenKhoanItemStateChanged

    private void rboTienMatItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rboTienMatItemStateChanged
        int ht = rboChuyenKhoan.isSelected() == true ? 1 : 2;
        ArrayList<HoaDon> hd = hdSer.getRbo(ht);
        if (hd != null) {
            fillHoadon(hd);
            return;
        } else {
            JOptionPane.showMessageDialog(this, "Không có hóa đơn nào thanh toán bằng tiền mặt");
        }
    }//GEN-LAST:event_rboTienMatItemStateChanged

    private void cbotrangThaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbotrangThaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbotrangThaiActionPerformed

    private void cbotrangThaiItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbotrangThaiItemStateChanged
        //        int tt = cbotrangThai.getSelectedIndex();
        //        if (tt == 0) {
            //            fillHoadon(hdSer.getAll());
            //        } else if (tt != 0) {
            //            fillHoadon(hdSer.getCbo(tt - 1));
            //        } else {
            //            return;
            //        }
        int tt = cbotrangThai.getSelectedIndex();
        switch (tt) {
            case 0:
            // Hiển thị tất cả các hóa đơn
            fillHoadon(hdSer.getAll());
            break;
            case 1:
            // Hiển thị các hóa đơn đã thanh toán (Trạng thái = 0)
            fillHoadon(hdSer.getCbo(0));
            break;
            case 3:
            // Hiển thị các hóa đơn đã hủy (Trạng thái = 2)
            fillHoadon(hdSer.getCbo(2));
            break;
            case 2:
            // Hiển thị các hóa đơn chờ xác nhận (Trạng thái = 3)
            fillHoadon(hdSer.getCbo(3));
            break;
            default:
            break;
        }
    }//GEN-LAST:event_cbotrangThaiItemStateChanged

    private void btnXuatExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatExcelActionPerformed
        if (tblHoadon.getRowCount() > 0) {
            try {
                XSSFWorkbook wordkbook = new XSSFWorkbook();
                XSSFSheet sheet = wordkbook.createSheet("ListHD");
                XSSFRow row = null;
                row = sheet.createRow(1);
                XSSFCell cell = row.createCell(0, CellType.STRING);
                cell.setCellValue("STT");
                cell = row.createCell(1, CellType.STRING);
                cell.setCellValue("Mã hóa đơn");
                cell = row.createCell(2, CellType.STRING);
                cell.setCellValue("Tên khách hàng");
                cell = row.createCell(3, CellType.STRING);
                cell.setCellValue("Số điện thoại");
                cell = row.createCell(4, CellType.STRING);
                cell.setCellValue("Địa chỉ");
                cell = row.createCell(5, CellType.STRING);
                cell.setCellValue("Tổng tiền");

                for (int i = 0; i < this.tblHoadon.getRowCount(); i++) {
                    row = sheet.createRow(5 + i);
                    cell = row.createCell(0, CellType.NUMERIC);
                    cell.setCellValue(String.valueOf(this.tblHoadon.getValueAt(i, 0)));
                    cell = row.createCell(1, CellType.STRING);
                    cell.setCellValue(String.valueOf(this.tblHoadon.getValueAt(i, 1)));
                    cell = row.createCell(2, CellType.STRING);
                    cell.setCellValue(String.valueOf(this.tblHoadon.getValueAt(i, 2)));
                    cell = row.createCell(3, CellType.STRING);
                    cell.setCellValue(String.valueOf(this.tblHoadon.getValueAt(i, 3)));
                    cell = row.createCell(4, CellType.STRING);
                    cell.setCellValue(String.valueOf(this.tblHoadon.getValueAt(i, 4)));
                    cell = row.createCell(5, CellType.STRING);
                    cell.setCellValue(String.valueOf(this.tblHoadon.getValueAt(i, 5)));
                }
                File f = new File("D:\\DA1_Fpoly\\XuatExcel\\ListHD.xlsx");
                try {
                    FileOutputStream fis = new FileOutputStream(f);
                    wordkbook.write(fis);
                    fis.close();
                    JOptionPane.showMessageDialog(this, "Xuất file hóa đơn thành công !");
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

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
        String search = txtTimKiem.getText();
        model = (DefaultTableModel) tblHoadon.getModel();
        search(search, model, tblHoadon);
    }//GEN-LAST:event_txtTimKiemKeyReleased

    private void txtTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemActionPerformed

    private void txtTimKiemCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTimKiemCaretUpdate
        //        String key = txtTimKiem.getText();
        //        ArrayList<HoaDon> hd = hdSer.getHodon(key);
        //        if (key.isEmpty()) {
            //            fillHoadon(hdSer.getAll());
            //        } else if (hd == null) {
            //            return;
            //        } else {
            //            fillHoadon(hd);
            //        }
    }//GEN-LAST:event_txtTimKiemCaretUpdate

    private HoaDonRepository hdSer = new HoaDonRepository();
    private HoaDonChiTietRepository hdctSer = new HoaDonChiTietRepository();
    private ViewLichSuHoaDonRepository vlsSer = new ViewLichSuHoaDonRepository();
    private DefaultTableModel model = new DefaultTableModel();

    public void fillHoadon(ArrayList<HoaDon> hd) {
//        model = (DefaultTableModel) tblHoadon.getModel();
//        model.setRowCount(0);
//        int stt = 1;
//        DecimalFormat format = new DecimalFormat("###,###.## VND");
//        for (HoaDon h : hd) {
//            Object[] data = {
//                stt++,
//                "HD00" + h.getId(),
//                h.getKh().getHoTen(),
//                h.getKh().getSoDT(),
//                h.getKh().getDiaChi(),
//                format.format(h.getThanhtien()),
//                h.getTrangThai() == 0 ? "Đã thanh toán" : "Chưa thanh toán",
//                h.getNgayTao()
//            };
//
//            model.addRow(data);
//
//        }
        model = (DefaultTableModel) tblHoadon.getModel();
        model.setRowCount(0);
        int stt = 1;
        DecimalFormat format = new DecimalFormat("###,###.## VND");
        for (HoaDon h : hd) {
            String trangThai;
            if (h.getTrangThai() == 0) {
                trangThai = "Đã thanh toán";
            } else if (h.getTrangThai() == 1) {
                trangThai = "Chưa thanh toán";
            } else if (h.getTrangThai() == 2) {
                trangThai = "Đã hủy"; // Điều chỉnh nếu cần
            } else if (h.getTrangThai() == 3) {
                trangThai = "Chờ xác nhận"; // Ví dụ cho trạng thái 3
            } else {
                trangThai = "Trạng thái không xác định"; // Trường hợp cho các giá trị không mong đợi
            }

            Object[] data = {
                stt++,
                "HD00" + h.getId(),
                h.getKh().getHoTen() != null ? h.getKh().getHoTen() : "Chưa xác định",
                h.getKh().getSoDT() != null ? h.getKh().getSoDT() : "Chưa xác định",
                h.getKh().getDiaChi() != null ? h.getKh().getDiaChi() : "Chưa xác định",
                format.format(h.getThanhtien() != 0 ? h.getThanhtien() : 0),
                trangThai,
                h.getNgayTao() != null ? h.getNgayTao().toString() : "Chưa xác định"
            };

            model.addRow(data);
        }

    }

    public void fillHoadonchitiet(ArrayList<HoaDonChiTiet> hdct) {

        model = (DefaultTableModel) tblhoadonchitiet.getModel();
        model.setRowCount(0);
        int stt = 1;

        DecimalFormat format = new DecimalFormat("###,###.## VND");

        for (HoaDonChiTiet h : hdct) {
            Object[] data = {
                stt++,
                h.getSp().getTenSP(),
                h.getTh().getTenTH(),
                h.getKt().getTenKT(),
                h.getMs().getTenMS(),
                h.getSoluong(),
                format.format(h.getGia())
            };
            model.addRow(data);
        }

    }

//    public void fillLichsuhoadon(ArrayList<ViewLichSuHoaDon> vlshd) {
//        model = (DefaultTableModel) tbllichsuhoadon.getModel();
//        model.setRowCount(0);
//        int stt = 1;
//        DecimalFormat format = new DecimalFormat("###,###.## VND");
//        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//        for (ViewLichSuHoaDon ls : vlshd) {
//            Object[] data = {
//                stt++,
//                "LS00" + ls.getId(),
//                ls.getNv(),
//                ls.getKhachhang(),
//                sdf.format(ls.getNgaythanhtoan()),
//                sdf.format(ls.getNgaytao())
//            };
//            model.addRow(data);
//        }
//
//    }
    public void fillLichsuhoadon(ArrayList<ViewLichSuHoaDon> vlshd) {
        model = (DefaultTableModel) tbllichsuhoadon.getModel();
        model.setRowCount(0);
        int stt = 1;
        DecimalFormat format = new DecimalFormat("###,###.## VND");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        for (ViewLichSuHoaDon ls : vlshd) {
            Object[] data = {
                stt++,
                "LS00" + ls.getId(),
                ls.getNv(),
                ls.getKhachhang(),
                ls.getNgaythanhtoan() != null ? sdf.format(ls.getNgaythanhtoan()) : "", // Kiểm tra null trước khi format
                ls.getNgaytao() != null ? sdf.format(ls.getNgaytao()) : "" // Kiểm tra null trước khi format
            };
            model.addRow(data);
        }
    }



    public void addcbo() {
        cbotrangThai.removeAllItems();
        cbotrangThai.addItem("Tất cả");
        cbotrangThai.addItem("Đã thanh toán");
        cbotrangThai.addItem("Chờ xác nhận");
        cbotrangThai.addItem("Đã hủy");
    }

    public void search(String str, DefaultTableModel model, JTable table) {
        model = (DefaultTableModel) tblHoadon.getModel();
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnXuatExcel;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbotrangThai;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JRadioButton rboChuyenKhoan;
    private javax.swing.JRadioButton rboTienMat;
    private javax.swing.JTable tblHoadon;
    private javax.swing.JTable tblhoadonchitiet;
    private javax.swing.JTable tbllichsuhoadon;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
