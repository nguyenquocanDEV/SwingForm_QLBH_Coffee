/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.Dat;

import DAO.*;
import model.DoanhThuNam;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class ThongKeDaoNam {

    private DoanhThuNam readFromResultSet(ResultSet rs) throws SQLException {
        DoanhThuNam model = new DoanhThuNam();
        model.setNam(rs.getString(1));
        model.setDoanhThu(rs.getFloat(2));
        return model;
    }

    public List<DoanhThuNam> select(String sql, Object... args) {
        List<DoanhThuNam> list = new ArrayList<>();
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

    public List<DoanhThuNam> select() {
        String sql = " SELECT YEAR( NgayBan) ,SUM(TongTienThanhToan) AS DOANHTHU\n"
                + "FROM HoaDon\n"
                + "GROUP BY YEAR( NgayBan)\n"
                + "ORDER BY YEAR( NgayBan) ASC";
        return select(sql);
    }

    public List<DoanhThuNam> HienNam() {
        String sql = "SELECT YEAR( NgayBan) ,SUM(TongTienThanhToan) AS DOANHTHU\n"
                + "FROM HOADON\n"
                + "GROUP BY YEAR(NGAYBAN)\n"
                + "ORDER BY YEAR(NGAYBAN) DESC";
        return select(sql);
    }
}
