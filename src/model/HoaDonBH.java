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
public class HoaDonBH {
    private String MaHD;
    private Integer MaBan;
    private String NgayBan;
    private String TenBan;
    private String TenTaiKhoan;
    private double TongTien;
    private String MaSuKien;
    private double UuDai;
    private double TongTienThanhToan;
    private boolean TrangThaiThanhToan;
    private String GhiChu;
    private Integer soHD;
    //

    public HoaDonBH(String MaHD, Integer MaBan, String NgayBan, String TenBan, String TenTaiKhoan, double TongTien, String MaSuKien, double UuDai, double TongTienThanhToan, String GhiChu, Integer soHD) {
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
        this.soHD = soHD;
    }

    public HoaDonBH() {
    }
    //

    public String getMaHD() {
        return MaHD;
    }

    public void setMaHD(String MaHD) {
        this.MaHD = MaHD;
    }

    public Integer getMaBan() {
        return MaBan;
    }

    public void setMaBan(Integer MaBan) {
        this.MaBan = MaBan;
    }

    public String getNgayBan() {
        return NgayBan;
    }

    public void setNgayBan(String NgayBan) {
        this.NgayBan = NgayBan;
    }

    public boolean isTrangThaiThanhToan() {
        return TrangThaiThanhToan;
    }

    public void setTrangThaiThanhToan(boolean TrangThaiThanhToan) {
        this.TrangThaiThanhToan = TrangThaiThanhToan;
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

    public double getTongTien() {
        return TongTien;
    }

    public void setTongTien(double TongTien) {
        this.TongTien = TongTien;
    }

    public String getMaSuKien() {
        return MaSuKien;
    }

    public void setMaSuKien(String MaSuKien) {
        this.MaSuKien = MaSuKien;
    }

    public double getUuDai() {
        return UuDai;
    }

    public void setUuDai(double UuDai) {
        this.UuDai = UuDai;
    }

    public double getTongTienThanhToan() {
        return TongTienThanhToan;
    }

    public void setTongTienThanhToan(double TongTienThanhToan) {
        this.TongTienThanhToan = TongTienThanhToan;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String GhiChu) {
        this.GhiChu = GhiChu;
    }

    public Integer getSoHD() {
        return soHD;
    }

    public void setSoHD(Integer soHD) {
        this.soHD = soHD;
    }
}
