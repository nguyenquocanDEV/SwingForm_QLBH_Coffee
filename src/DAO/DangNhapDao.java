/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Helper.jdbcHelper;
import Helper.shareHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.TaiKhoanMode;

/**
 *
 * @author CHIEN
 */
public class DangNhapDao {

    public static boolean VaiTro = false;
 
    //code hướng mới
    //đọc 1 nhân viên từ bản ghi
//    public nhanVien readFromResultSet(ResultSet rs) throws SQLException{
//        nhanVien model=new nhanVien();
//        model.setMaNV(rs.getString("MaNV"));
//        model.setMatKhau(rs.getString("MatKhau"));
//        model.setHoTen(rs.getString("HoTen"));
//        model.setVaiTro(rs.getBoolean("VaiTro"));
//        return model;
//    }
    public TaiKhoanMode readFromResultSet(ResultSet rs) throws SQLException {
        TaiKhoanMode mode = new TaiKhoanMode();
        mode.setTenTaiKhoan(rs.getString(1));
        mode.setMatKhau(rs.getString(2));
        mode.setHoTen(rs.getString(3));
        mode.setEmail(rs.getString(4));
        mode.setVaiTro(rs.getBoolean(5));
        
        System.out.println(mode.isVaiTro());

        return mode;
    }
//lấy list danh sách tài khoản
    public List<TaiKhoanMode> select(String sql, Object... args) {
        List<TaiKhoanMode> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = jdbcHelper.executeQuery(sql, args);
                while (rs.next()) {
                    list.add(readFromResultSet(rs));
                }
            } finally {
                rs.getStatement().getConnection().close(); //đóng kết nối từ resultset
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }
        return list;
    }

    /**
     * Truy vấn thực thể theo mã id
     *
     * @param id là mã của bản ghi được truy vấn
     * @return thực thể chứa thông tin của bản ghi
     */
//    public nhanVien findById(String id) {
//        String sql="SELECT * FROM NhanVien WHERE MaNV=?";
//        List<nhanVien> list=select(sql, id);
//        return list.size()>0?list.get(0):null;               //có thể trả về là null
//    }
    public TaiKhoanMode findByTenTaiKhoan(String tenTaiKhoan) {
        String sql = "select * from TaiKhoan\n"
                + "where TenTaiKhoan = ? and trangThai = 1";
        List<TaiKhoanMode> list = select(sql, tenTaiKhoan);
        return list.size() > 0 ? list.get(0) : null;
    }
    
}
