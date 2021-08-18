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
public class CTHoaDon {
    private String MaHD;
    private Integer MaMon;
    private String TenMon;
    private Integer SoLuong;
    private double DonGia;
    private double ThanhTien;
    //

    public CTHoaDon(String MaHD, Integer MaMon, String TenMon, Integer SoLuong, double DonGia, double ThanhTien) {
        this.MaHD = MaHD;
        this.MaMon = MaMon;
        this.TenMon = TenMon;
        this.SoLuong = SoLuong;
        this.DonGia = DonGia;
        this.ThanhTien = ThanhTien;
    }

    public CTHoaDon() {
    }
    //

    public String getMaHD() {
        return MaHD;
    }

    public void setMaHD(String MaHD) {
        this.MaHD = MaHD;
    }

    public Integer getMaMon() {
        return MaMon;
    }

    public void setMaMon(Integer MaMon) {
        this.MaMon = MaMon;
    }

    public String getTenMon() {
        return TenMon;
    }

    public void setTenMon(String TenMon) {
        this.TenMon = TenMon;
    }

    public Integer getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(Integer SoLuong) {
        this.SoLuong = SoLuong;
    }

    public double getDonGia() {
        return DonGia;
    }

    public void setDonGia(double DonGia) {
        this.DonGia = DonGia;
    }

    public double getThanhTien() {
        return ThanhTien;
    }

    public void setThanhTien(double ThanhTien) {
        this.ThanhTien = ThanhTien;
    }
}
