/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 *
 * @author trong
 */
public class HoaDon {

    private String MaHD;
    private int MaBan;
    private String NgayBan;
    private String TenBan;
    private String TenTaiKhoan;
    private float TongTien;
    private String MaSuKien;
    private float UuDai;
    private float TongTienThanhToan;
    private String GhiChu;
    private int SoHD;

    public HoaDon() {
    }

    public HoaDon(String MaHD, int MaBan, String NgayBan, String TenBan, String TenTaiKhoan, float TongTien, String MaSuKien, float UuDai, float TongTienThanhToan, String GhiChu, int SoHD) {
        this.MaHD = MaHD;
        this.MaBan = MaBan;
        this.NgayBan = NgayBan;
        this.TenBan = TenBan;
        this.TenTaiKhoan = TenTaiKhoan;
        this.TongTien = TongTien;
        this.MaSuKien = MaSuKien;
        this.UuDai = UuDai;
        this.TongTienThanhToan = TongTienThanhToan;
        this.GhiChu = GhiChu;
        this.SoHD = SoHD;
    }

    public String getMaHD() {
        return MaHD;
    }

    public void setMaHD(String MaHD) {
        this.MaHD = MaHD;
    }

    public int getMaBan() {
        return MaBan;
    }

    public void setMaBan(int MaBan) {
        this.MaBan = MaBan;
    }

    public String getNgayBan() {
        return NgayBan;
    }

    public void setNgayBan(String NgayBan) {
        this.NgayBan = NgayBan;
    }

    public String getTenBan() {
        return TenBan;
    }

    public void setTenBan(String TenBan) {
        this.TenBan = TenBan;
    }

    public String getTenTaiKhoan() {
        return TenTaiKhoan;
    }

    public void setTenTaiKhoan(String TenTaiKhoan) {
        this.TenTaiKhoan = TenTaiKhoan;
    }

    public float getTongTien() {
        return TongTien;
    }

    public void setTongTien(float TongTien) {
        this.TongTien = TongTien;
    }

    public String getMaSuKien() {
        return MaSuKien;
    }

    public void setMaSuKien(String MaSuKien) {
        this.MaSuKien = MaSuKien;
    }

    public float getUuDai() {
        return UuDai;
    }

    public void setUuDai(float UuDai) {
        this.UuDai = UuDai;
    }

    public float getTongTienThanhToan() {
        return TongTienThanhToan;
    }

    public void setTongTienThanhToan(float TongTienThanhToan) {
        this.TongTienThanhToan = TongTienThanhToan;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String GhiChu) {
        this.GhiChu = GhiChu;
    }

    public int getSoHD() {
        return SoHD;
    }

    public void setSoHD(int SoHD) {
        this.SoHD = SoHD;
    }

}
