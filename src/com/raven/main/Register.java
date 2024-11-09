/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raven.main;

import com.raven.classmodel.DoiMatKhau;
import com.raven.repository.DoiMauKhauSer;
import java.awt.event.ActionListener;

import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


import javax.swing.JOptionPane;

/**
 *
 * @author RAVEN
 */
public class Register extends javax.swing.JPanel {

    private DoiMauKhauSer dmkSer = new DoiMauKhauSer();

    /**
     * Creates new form Login
     */
    public Register() {
        initComponents();
    }

    public void register() {
        txtUser.grabFocus();
    }

    DoiMatKhau readForm() {
        DoiMatKhau dmk = new DoiMatKhau();
        String ma = txtUser.getText();
        String mk = txtPass1.getText();
        return new DoiMatKhau(ma, mk);
    }

    public void addEventBackLogin(ActionListener event) {
        cmdBackLogin.addActionListener(event);
    }

    public boolean checkForm() {

        if (txtUser.getText().isEmpty() && txtPass.getText().isEmpty() && txtPass1.getText().isEmpty() && txtEmail.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng, nhập vào đầy đủ thông tin!");
            return false;
        }
        if (txtUser.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tài khoản không được để trống!");
            return false;
        }
        if (txtPass.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng, nhập vào mật khẩu mới!");
            return false;
        }
        if (txtPass1.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng, xác nhận lại mật khẩu mới!");
            return false;
        }
        return true;
    }

    public boolean checkEmail() {
        String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        String email = txtEmail.getText();

        if (email.matches(EMAIL_REGEX) == false) {
            JOptionPane.showMessageDialog(this, "Email không đúng định dạng!");
            return false;
        }
        if (txtEmail.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Email không được để trống!");
            return false;
        }
        if (dmkSer.getEmail(email) == null) {
            JOptionPane.showMessageDialog(this, "Email không khớp với thông tin tài khoản!");
            return false;
        }
        return true;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtUser = new swing.MyTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtPass = new swing.MyPassword();
        jLabel3 = new javax.swing.JLabel();
        btnLogin = new swing.MyButton();
        cmdBackLogin = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtPass1 = new swing.MyPassword();
        txtEmail = new swing.MyTextField();
        jLabel5 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setText("User Name");

        jLabel2.setFont(new java.awt.Font("sansserif", 1, 48)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(69, 68, 68));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Register");

        jLabel3.setText("Password");

        btnLogin.setBackground(new java.awt.Color(125, 229, 251));
        btnLogin.setForeground(new java.awt.Color(40, 40, 40));
        btnLogin.setText("Login");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        cmdBackLogin.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        cmdBackLogin.setForeground(new java.awt.Color(30, 122, 236));
        cmdBackLogin.setText("Back to Login");
        cmdBackLogin.setContentAreaFilled(false);
        cmdBackLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel4.setText("Confirm Password");

        jLabel5.setText("Email");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(50, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1)
                    .addComponent(txtUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
                    .addComponent(txtPass, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmdBackLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4)
                    .addComponent(txtPass1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(txtPass1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addComponent(cmdBackLogin)
                .addGap(30, 30, 30))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        DoiMatKhau dm = readForm();

        if (!checkForm()) {
            return;
        } else if (!checkEmail()) {
            return;
        } else {
            if (!dmkSer.getUser(dm)) {
                JOptionPane.showMessageDialog(this, "Mã nhân viên không tồn tại!");
                return;
            } else {
                if (txtPass.getText().equals(txtPass1.getText())) {
                    if (dmkSer.updatePass(dm) > 0) {
                        JOptionPane.showMessageDialog(this, "Đổi mật khẩu thành công!");

                        final String from = "anhnhph40387@fpt.edu.vn";
                        final String pass = "vrgpkogcovwokifq";
                        final String to = txtEmail.getText();
                        Properties prop = new Properties();
                        prop.put("mail.smtp.host", "smtp.gmail.com");//SMTP HOST
                        prop.put("mail.smtp.port", "587");//TLS=587,SSL=465
                        prop.put("mail.smtp.auth", "true");
                        prop.put("mail.smtp.starttls.enable", "true");

//                        gửi mail
                        Authenticator auth = new Authenticator() {
                            @Override
                            protected PasswordAuthentication getPasswordAuthentication() {
                                return new PasswordAuthentication(from, pass);
                            }

                        };

                        // phiên làm  việc
                        Session session = Session.getInstance(prop, auth);
                        MimeMessage msg = new MimeMessage(session);
                        try {
                            msg.addHeader("Content-type", "text; charset=UTF-8");
                            //người gửi
                            msg.setFrom(from);
                            //người nhận
                            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
                            //tiêu đề 
                            msg.setSubject("Gửi mật khẩu");
                            //quy định ngày gửi
                            msg.setSentDate(new Date());
                            //quy định email nhận phản hồi
                            //   msg.setReplyTo(null);
                            //nội dung
                            msg.setText("Mật khẩu được đặt lại là: " + txtPass1.getText(), "UTF-8");

                            Transport.send(msg);

                        } catch (Exception e) {
                            e.printStackTrace();
                            return;
                        }    

                    } else {
                        JOptionPane.showMessageDialog(this, "Đổi mật khẩu không thành công");
                        System.out.println("Đổi mật khẩu không thành công");
                        return;
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng mật khẩu mới vào ô 'Confirm passworld'! ");
                    return;
                }
            }

        }
    }//GEN-LAST:event_btnLoginActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private swing.MyButton btnLogin;
    private javax.swing.JButton cmdBackLogin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private swing.MyTextField txtEmail;
    private swing.MyPassword txtPass;
    private swing.MyPassword txtPass1;
    private swing.MyTextField txtUser;
    // End of variables declaration//GEN-END:variables
}
