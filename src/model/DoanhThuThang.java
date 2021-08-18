/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author trong
 */
public class DoanhThuThang {

    private String thang;
    private float doanhThu;

    public DoanhThuThang() {
    }

    public DoanhThuThang(String thang, float doanhThu) {
        this.thang = thang;
        this.doanhThu = doanhThu;
    }

    public String getThang() {
        return thang;
    }

    public void setThang(String thang) {
        this.thang = thang;
    }

    public float getDoanhThu() {
        return doanhThu;
    }

    public void setDoanhThu(float doanhThu) {
        this.doanhThu = doanhThu;
    }

}
