/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;

/**
 *
 * @author ducmc
 */
public class MaXacNhan {
    private String maXacNan;
    private String NgayTao;
    private String ngayKt;

    public MaXacNhan() {
    }

    public MaXacNhan(String maXacNan, String NgayTao, String ngayKt) {
        this.maXacNan = maXacNan;
        this.NgayTao = NgayTao;
        this.ngayKt = ngayKt;
    }

    public String getMaXacNan() {
        return maXacNan;
    }

    public void setMaXacNan(String maXacNan) {
        this.maXacNan = maXacNan;
    }

    public String getNgayTao() {
        return NgayTao;
    }

    public void setNgayTao(String NgayTao) {
        this.NgayTao = NgayTao;
    }

    public String getNgayKt() {
        return ngayKt;
    }

    public void setNgayKt(String ngayKt) {
        this.ngayKt = ngayKt;
    }

   

   
    
    
    
   
    
    
    
}
