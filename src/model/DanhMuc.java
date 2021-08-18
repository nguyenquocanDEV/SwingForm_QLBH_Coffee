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
public class DanhMuc {
    private Integer MaDanhMuc;
    private String TenDanhMuc;
    //

    public DanhMuc() {
    }

    public DanhMuc(Integer MaDanhMuc, String TenDanhMuc) {
        this.MaDanhMuc = MaDanhMuc;
        this.TenDanhMuc = TenDanhMuc;
    }
    //

    public Integer getMaDanhMuc() {
        return MaDanhMuc;
    }

    public void setMaDanhMuc(Integer MaDanhMuc) {
        this.MaDanhMuc = MaDanhMuc;
    }

    public String getTenDanhMuc() {
        return TenDanhMuc;
    }

    public void setTenDanhMuc(String TenDanhMuc) {
        this.TenDanhMuc = TenDanhMuc;
    }
    //

    @Override
    public String toString() {
        return TenDanhMuc;
    }
}
