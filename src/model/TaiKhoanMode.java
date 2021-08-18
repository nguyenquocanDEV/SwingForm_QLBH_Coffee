/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author CHIEN
 */
public class TaiKhoanMode {

    private String tenTaiKhoan;
    private String matKhau;
    private String hoTen;
    private String email;
    private boolean vaiTro;
    private String trangThai;
    //

    public TaiKhoanMode() {
    }

    public TaiKhoanMode(String tenTaiKhoan, String matKhau, String hoTen, String email, boolean vaiTro, String trangThai) {
        this.tenTaiKhoan = tenTaiKhoan;
        this.matKhau = matKhau;
        this.hoTen = hoTen;
        this.email = email;
        this.vaiTro = vaiTro;
        this.trangThai = trangThai;
    }
    //

    public String getTenTaiKhoan() {
        return tenTaiKhoan;
    }

    public void setTenTaiKhoan(String tenTaiKhoan) {
        this.tenTaiKhoan = tenTaiKhoan;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isVaiTro() {
        return vaiTro;
    }

    public void setVaiTro(boolean vaiTro) {
        this.vaiTro = vaiTro;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        return tenTaiKhoan + vaiTro + matKhau;
    }

}
