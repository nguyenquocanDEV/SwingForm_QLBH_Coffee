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
public class HoaDonMD {
//    MaHD nvarchar(50) not null,
//	MaBan int not null,
//	TenBan nvarchar(100),
//	TenTaiKhoan nvarchar(50) not null,
//	TongTien money not null,
//	MaSuKien nvarchar(50),
//	UuDai float,
//	TongTienThanhToan money,

    private String MaHD;
    private int MaBan;
    private String TenBan;
    private String TenTaiKhoan;
    private double TongTien;
    private String MaSuKien;
    private double UuDai;
    private double TongTienThanhToan;
    //

    public HoaDonMD() {
    }

    public HoaDonMD(String MaHD, int MaBan, String TenBan, String TenTaiKhoan, double TongTien, String MaSuKien, double UuDai, double TongTienThanhToan) {
        this.MaHD = MaHD;
        this.MaBan = MaBan;
        this.TenBan = TenBan;
        this.TenTaiKhoan = TenTaiKhoan;
        this.TongTien = TongTien;
        this.MaSuKien = MaSuKien;
        this.UuDai = UuDai;
        this.TongTienThanhToan = TongTienThanhToan;
    }
//

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
}
