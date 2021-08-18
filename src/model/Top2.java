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
public class Top2 {

    private String tenMon;
    private int sl;
    private String ngay;

    public Top2() {
    }

    public Top2(String tenMon, int sl, String ngay) {
        this.tenMon = tenMon;
        this.sl = sl;
        this.ngay = ngay;
    }

    public String getTenMon() {
        return tenMon;
    }

    public void setTenMon(String tenMon) {
        this.tenMon = tenMon;
    }

    public int getSl() {
        return sl;
    }

    public void setSl(int sl) {
        this.sl = sl;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

}
