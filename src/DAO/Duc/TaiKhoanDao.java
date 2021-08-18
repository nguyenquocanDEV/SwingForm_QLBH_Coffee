/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.Duc;

import Helper.jdbcHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.TaiKhoanMode;

public class TaiKhoanDao {

    // static Connection con = Utils.myConnection();
    public TaiKhoanMode readFromResultSet(ResultSet rs) throws SQLException {
        TaiKhoanMode model = new TaiKhoanMode();
        model.setTenTaiKhoan(rs.getString("Tên tài khoản"));
        model.setHoTen(rs.getString("Họ tên"));
        model.setMatKhau(rs.getString("Mật khẩu"));
        model.setEmail(rs.getString("Email"));
        model.setVaiTro(rs.getBoolean("VaiTro"));
        return model;
    }

    //thực hiện truy vấn lấy về 1 tập ResultSet rồi điền tập ResultSet đó vào 1 List
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
                rs.getStatement().getConnection().close();      //đóng kết nối từ resultSet
            }
        } catch (SQLException ex) {
            throw new RuntimeException();
        }
        return list;
    }

    public void doimk(TaiKhoanMode emiu) {
        String sql = "update TAIKHOAN set MATKHAU = ?\n"
                + "where EMAIL = ?";
        jdbcHelper.executeUpdate(sql,
                emiu.getMatKhau(),
                emiu.getEmail());

    }

    public TaiKhoanMode findByEmail(String email) {
        String sql = "select * from TAIKHOAN\n"
                + "where EMAIL = ?";
        List<TaiKhoanMode> list = select(sql, email);
        return list.size() > 0 ? list.get(0) : null;
    }

}
