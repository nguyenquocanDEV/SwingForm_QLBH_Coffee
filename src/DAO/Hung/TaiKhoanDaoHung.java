/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.Hung;

import Helper.jdbcHelper;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.TaiKhoanModeHung;
import Helper.Utils;
import java.sql.PreparedStatement;
import javax.swing.JTextField;

/**
 *
 * @author Hungit
 */
public class TaiKhoanDaoHung {

    static Connection con = Utils.myConnection();

    public TaiKhoanModeHung readFromResultSet(ResultSet rs) throws SQLException {
        TaiKhoanModeHung model = new TaiKhoanModeHung();
        model.setTenTaiKhoan(rs.getString(1));
        model.setHoTen(rs.getString(2));
        model.setMatKhau(rs.getString(3));
        model.setEmail(rs.getString(4));
        model.setVaiTro(rs.getBoolean(5));
        return model;
    }

    public synchronized static List<TaiKhoanModeHung> HienThi() {
        List<TaiKhoanModeHung> list = new ArrayList<>();
        try {
            String sql = "select tentaikhoan,matkhau,hoten,email,vaitro,trangthai from taikhoan\n"
                    + "where trangthai='1' and an='1'";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                TaiKhoanModeHung hd = new TaiKhoanModeHung();
                hd.setTenTaiKhoan(rs.getString(1));
                hd.setMatKhau(rs.getString(2));
                hd.setHoTen(rs.getString(3));
                hd.setEmail(rs.getString(4));
                hd.setVaiTro(rs.getBoolean(5));
                hd.setTrangThai(rs.getString(6));

                list.add(hd);
            }
        } catch (Exception e) {

        }

        return list;

    }

    public synchronized static List<TaiKhoanModeHung> HienThi1() {
        List<TaiKhoanModeHung> list = new ArrayList<>();
        try {
            String sql = "select tentaikhoan,matkhau,hoten,email,vaitro,trangthai from taikhoan\n"
                    + "where trangthai= 0 and an='1'";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                TaiKhoanModeHung hd = new TaiKhoanModeHung();
                hd.setTenTaiKhoan(rs.getString(1));
                hd.setMatKhau(rs.getString(2));
                hd.setHoTen(rs.getString(3));
                hd.setEmail(rs.getString(4));
                hd.setVaiTro(rs.getBoolean(5));
                hd.setTrangThai(rs.getString(6));

                list.add(hd);
            }
        } catch (Exception e) {

        }

        return list;

    }

    //thực hiện truy vấn lấy về 1 tập ResultSet rồi điền tập ResultSet đó vào 1 List
    public List<TaiKhoanModeHung> select(String sql, Object... args) {
        List<TaiKhoanModeHung> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = jdbcHelper.executeQuery(sql, args);
                while (rs.next()) {
                    list.add(readFromResultSet(rs));
                }
            } finally {
                rs.getStatement().getConnection().close();      //đóng kết nối từ resultSet
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException();
        }
        return list;
    }

    /**
     * Thêm mới thực thể vào CSDL
     *
     * @param entity là thực thể chứa thông tin bản ghi mới
     */
    public static void insert(TaiKhoanModeHung entity) {
        String sql = "INSERT INTO TAIKHOAN VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcHelper.executeUpdate(sql,
                entity.getTenTaiKhoan(),
                entity.getMatKhau(),
                entity.getHoTen(),
                entity.getEmail(),
                entity.isVaiTro(),
                entity.getTrangThai(),
                entity.isAn());
    }

    public static void update(TaiKhoanModeHung entity) {
        String sql = "UPDATE TAIKHOAN SET MatKhau=?, HoTen=?, VaiTro=? , EMAIL=?,an=?,trangthai=? WHERE TENTAIKHOAN=?";
        jdbcHelper.executeUpdate(sql,
                entity.getMatKhau(),
                entity.getHoTen(),
                entity.isVaiTro(),
                entity.getEmail(),
                entity.isAn(),
                entity.getTrangThai(),
                entity.getTenTaiKhoan());
    }

    public synchronized static List<TaiKhoanModeHung> TimKiemMa(String ma) {
        List<TaiKhoanModeHung> list = new ArrayList<>();
        try {
            String sql = "select * from taikhoan\n"
                    + "where (tentaikhoan + hoten + email) like ?";
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, "%" + ma + "%");
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                TaiKhoanModeHung tk = new TaiKhoanModeHung();
                tk.setTenTaiKhoan(rs.getString(1));
                tk.setHoTen(rs.getString(2));
                tk.setMatKhau(rs.getString(3));
                tk.setEmail(rs.getString(4));
                tk.setVaiTro(rs.getBoolean(5));

                list.add(tk);

            }
        } catch (Exception e) {

        }
        return list;
    }

    public  TaiKhoanModeHung checkTrungMa(String tenTaiKhoan) {
        String sql = "select * from taikhoan \n"
                + "where tentaikhoan = ? and trangthai=1";
        List<TaiKhoanModeHung> list = select(sql, tenTaiKhoan);
        return list.size() > 0 ? list.get(0) : null;
    }
    public void delete(String tenTaiKhoan) {
        String sql = "UPDATE taikhoan\n"
                + "SET an = 0\n"
                + "where tentaikhoan = ?";
        jdbcHelper.executeUpdate(sql, tenTaiKhoan);
    }
   public  TaiKhoanModeHung checkTrungMa1(String Gmail) {
        String sql = "select * from taikhoan \n"
                + "where gmail = ? and trangthai=1";
        List<TaiKhoanModeHung> list = select(sql, Gmail);
        return list.size() > 0 ? list.get(0) : null;
    }
}
