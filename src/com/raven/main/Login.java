/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raven.main;

import com.raven.classmodel.GetChucVu;
import com.raven.classmodel.login;
import com.raven.repository.loginSer;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author RAVEN
 */
public class Login extends javax.swing.JPanel {

    private loginSer lgSer = new loginSer();

    /**
     * Creates new form Login
     */
    public Login() {
        initComponents();

    }

    public void login() {
        txtTenDN.grabFocus();
    }

    public void addEventRegister(ActionListener event) {
        cmdRegister.addActionListener(event);
    }

    public String returnChucVu() {
        login lg = lgSer.getDangNhap(txtTenDN.getText(), txtPass.getText());
        if (lg.getChucVu() == 1) {
            return "Nhân viên";
        } else if (lg.getChucVu() == 0) {
            return "Quản lí";
        } else {

            System.out.println("Lỗi");
            return null;

        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtTenDN = new swing.MyTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtPass = new swing.MyPassword();
        jLabel3 = new javax.swing.JLabel();
        btnLogin = new swing.MyButton();
        cmdRegister = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setText("User Name");

        jLabel2.setFont(new java.awt.Font("sansserif", 1, 48)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(69, 68, 68));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Login");

        jLabel3.setText("Password");

        btnLogin.setBackground(new java.awt.Color(125, 229, 251));
        btnLogin.setForeground(new java.awt.Color(40, 40, 40));
        btnLogin.setText("Login");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        cmdRegister.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        cmdRegister.setForeground(new java.awt.Color(30, 122, 236));
        cmdRegister.setText("Register Now");
        cmdRegister.setContentAreaFilled(false);
        cmdRegister.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(50, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1)
                    .addComponent(txtTenDN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
                    .addComponent(txtPass, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmdRegister, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(50, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(txtTenDN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addComponent(cmdRegister)
                .addGap(30, 30, 30))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed

        if (txtTenDN.getText().isEmpty() && txtPass.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Bạn cần nhập vào tài khoản cần đăng nhập");
            return;
        }
        if (txtTenDN.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tên đăng nhập không được để trống");
            return;
        }
        if (txtPass.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mật khẩu không được để trông");
            return;
        }

        login lg = lgSer.getDangNhap(txtTenDN.getText(), txtPass.getText());

        if (lg == null) {
            JOptionPane.showMessageDialog(this, "Tài khoản không tồn tại");
            txtTenDN.setText("");
            txtPass.setText("");
            return;
        } else if (lg != null) {

            GetChucVu.setTenDN(lg.getTenDN());
            GetChucVu.setMatkKhau(lg.getMatKhau());
            GetChucVu.setChucVu(lg.getChucVu());
            GetChucVu.setId(lg.getID());
            GetChucVu.setTrangthai(lg.getTrnagThai());
            if (GetChucVu.getTrangthai() == 1) {
                
                    JOptionPane.showMessageDialog(this, "Đăng nhập thành công với tư cách: " + returnChucVu());
                    System.out.println(lg.getChucVu());
                    Main main = new Main();
                    main.setVisible(true);
                    System.out.println("ok");
                
            } else if (GetChucVu.getTrangthai() == 0) {
                System.out.println("not ok");
                JOptionPane.showMessageDialog(this, "Nhân viên đã nghỉ việc.Không thể đăng nhập!");
                return;
            } else {
                System.out.println("LOI");
            }

        }

    }//GEN-LAST:event_btnLoginActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private swing.MyButton btnLogin;
    private javax.swing.JButton cmdRegister;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private swing.MyPassword txtPass;
    private swing.MyTextField txtTenDN;
    // End of variables declaration//GEN-END:variables
}