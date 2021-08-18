/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helper;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author CHIEN
 */
public class ChuyenDoi {

    public static String chuyenDoiTien(double tien) {
        NumberFormat fomater = new DecimalFormat("###,###,###,###");
        return fomater.format(tien);
    }

    //
    public static Date chuyenDoiNgay(String ngay) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return formatter.parse(ngay);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }

    }
    //
    public static String chuyenDoiNgayVeString1(Date ngay){
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        return formatter.format(ngay);
    }
}
