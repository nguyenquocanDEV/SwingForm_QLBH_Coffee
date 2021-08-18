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
public class MonAn {
//    MaMon int identity,
//	TenMon nvarchar(100) not null,
//	MaDanhMuc int,
//	DonGia money not null,
//	HinhAnh nvarchar(1000) not null,
//	TrangThai int not null,

    private int MaMon;
    private String TenMon;
    private int MaDanhMuc;
    private double DonGia;
    private String HinhAnh;
    private int TrangThai;
    //

    public MonAn(int MaMon, String TenMon, int MaDanhMuc, double DonGia, String HinhAnh, int TrangThai) {
        this.MaMon = MaMon;
        this.TenMon = TenMon;
        this.MaDanhMuc = MaDanhMuc;
        this.DonGia = DonGia;
        this.HinhAnh = HinhAnh;
        this.TrangThai = TrangThai;
    }

    public MonAn() {
    }
//

    public int getMaMon() {
        return MaMon;
    }

    public void setMaMon(int MaMon) {
        this.MaMon = MaMon;
    }

    public String getTenMon() {
        return TenMon;
    }

    public void setTenMon(String TenMon) {
        this.TenMon = TenMon;
    }

    public int getMaDanhMuc() {
        return MaDanhMuc;
    }

    public void setMaDanhMuc(int MaDanhMuc) {
        this.MaDanhMuc = MaDanhMuc;
    }

    public double getDonGia() {
        return DonGia;
    }

    public void setDonGia(double DonGia) {
        this.DonGia = DonGia;
    }

    public String getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(String HinhAnh) {
        this.HinhAnh = HinhAnh;
    }

    public int getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(int TrangThai) {
        this.TrangThai = TrangThai;
    }

}
