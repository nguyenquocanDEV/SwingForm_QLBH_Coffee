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
public class KhuVuc {
//    
//	MaKhuVuc int identity,
//	TenKhuVuc nvarchar(100) not null,
//	TrangThai bit not null,

    private int MaKhuVuc;
    private String TenKhuVuc;
    private boolean TrangThai;
    //

    public KhuVuc() {
    }

    public KhuVuc(int MaKhuVuc, String TenKhuVuc, boolean TrangThai) {
        this.MaKhuVuc = MaKhuVuc;
        this.TenKhuVuc = TenKhuVuc;
        this.TrangThai = TrangThai;
    }
    //

    public int getMaKhuVuc() {
        return MaKhuVuc;
    }

    public void setMaKhuVuc(int MaKhuVuc) {
        this.MaKhuVuc = MaKhuVuc;
    }

    public String getTenKhuVuc() {
        return TenKhuVuc;
    }

    public void setTenKhuVuc(String TenKhuVuc) {
        this.TenKhuVuc = TenKhuVuc;
    }

    public boolean isTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(boolean TrangThai) {
        this.TrangThai = TrangThai;
    }
    //

    @Override
    public String toString() {
        return TenKhuVuc;
    }
}
