/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.Dat;

import DAO.*;
import model.DoanhThuThang;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class ThongKeDaoThang {

    private DoanhThuThang readFromResultSet(ResultSet rs) throws SQLException {
        DoanhThuThang model = new DoanhThuThang();
        model.setThang(rs.getString(1));
        model.setDoanhThu(rs.getFloat(2));
        return model;
    }

    public List<DoanhThuThang> select(String sql, Object... args) {
        List<DoanhThuThang> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = Helper.jdbcHelper.executeQuery(sql, args);
                while (rs.next()) {
                    list.add(readFromResultSet(rs));
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException();
        }
        return list;
    }

    public List< DoanhThuThang> Thang(String nam) {
        String sql = " SELECT MONTH(NgayBan) ,SUM(TongTienThanhToan) AS DOANHTHU\n"
                + "FROM HoaDon\n"
                + "WHERE YEAR(NGAYBAN) = ?\n"
                + "GROUP BY MONTH(NgayBan)\n"
                + "ORDER BY MONTH(NgayBan) ASC";
        return select(sql, nam);
    }
}
