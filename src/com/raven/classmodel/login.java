/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.classmodel;

/**
 *
 * @author Admin
 */
public class login {
    
    private int ID;
    private String tenDN;
    private String matKhau;
    private int chucVu;
    private int trnagThai;

    public login() {
    }

    public login(int ID, String tenDN, String matKhau, int chucVu, int trnagThai) {
        this.ID = ID;
        this.tenDN = tenDN;
        this.matKhau = matKhau;
        this.chucVu = chucVu;
        this.trnagThai = trnagThai;
    }

    public int getTrnagThai() {
        return trnagThai;
    }

    public void setTrnagThai(int trnagThai) {
        this.trnagThai = trnagThai;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTenDN() {
        return tenDN;
    }

    public void setTenDN(String tenDN) {
        this.tenDN = tenDN;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public int getChucVu() {
        return chucVu;
    }

    public void setChucVu(int chucVu) {
        this.chucVu = chucVu;
    }
    
    
    
}
