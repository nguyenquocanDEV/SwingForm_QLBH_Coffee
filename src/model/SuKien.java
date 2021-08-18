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
public class SuKien {
//    MaSuKien nvarchar(50) not null,
//	TenSuKien nvarchar(100) not null,
//	UuDai float not null,
//	tgBatDau datetime,
//	tgKetThuc dateTime,
//	LoaiSuKien int not null,
    private String MaSuKien;
    private String TenSuKien;
    private float UuDai;
    private String tgBatDau;
    private String tgKetThuc;
    private boolean loaiSuKien;
    private boolean trangThai;
    //
    public SuKien() {
    }

    public SuKien(String MaSuKien, String TenSuKien, float UuDai, String tgBatDau, String tgKetThuc) {
        this.MaSuKien = MaSuKien;
        this.TenSuKien = TenSuKien;
        this.UuDai = UuDai;
        this.tgBatDau = tgBatDau;
        this.tgKetThuc = tgKetThuc;
        
    }
    //

    public String getMaSuKien() {
        return MaSuKien;
    }

    public void setMaSuKien(String MaSuKien) {
        this.MaSuKien = MaSuKien;
    }

    public String getTenSuKien() {
        return TenSuKien;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    public void setTenSuKien(String TenSuKien) {
        this.TenSuKien = TenSuKien;
    }

    public float getUuDai() {
        return UuDai;
    }

    public void setUuDai(float UuDai) {
        this.UuDai = UuDai;
    }

    public String getTgBatDau() {
        return tgBatDau;
    }

    public void setTgBatDau(String tgBatDau) {
        this.tgBatDau = tgBatDau;
    }

    public String getTgKetThuc() {
        return tgKetThuc;
    }

    public void setTgKetThuc(String tgKetThuc) {
        this.tgKetThuc = tgKetThuc;
    }

    public boolean isLoaiSuKien() {
        return loaiSuKien;
    }

    public void setLoaiSuKien(boolean loaiSuKien) {
        this.loaiSuKien = loaiSuKien;
    }
    //

    @Override
    public String toString() {
        return TenSuKien;
    }
}
