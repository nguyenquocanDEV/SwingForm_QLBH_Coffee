/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.Dat;

import model.CTHoaDon;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sieu Nhan Bay
 */
public class CtHoaDonDao {

    private CTHoaDon readFromResultSet(ResultSet rs) throws SQLException {
        CTHoaDon model = new CTHoaDon();
        model.setMaHD(rs.getString(1));
        model.setMaMon(rs.getInt(2));
        model.setTenMon(rs.getString(3));
        model.setSoLuong(rs.getInt(4));
        model.setDonGia(rs.getDouble(5));
        model.setThanhTien(rs.getDouble(6));
        return model;
    }

    public List<CTHoaDon> select(String sql, Object... args) {
        List<CTHoaDon> list = new ArrayList<>();
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

    public List<CTHoaDon> findById(String id) {
        String sql = "select * from CTHoaDon\n"
                + "where MaHD = ?";
        return select(sql, id);

    }
}
