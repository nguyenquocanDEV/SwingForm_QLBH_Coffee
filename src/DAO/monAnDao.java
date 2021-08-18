/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Helper.jdbcHelper;
import java.sql.Connection;

import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.MonAn;

/**
 *
 * @author ducmc
 */
public class monAnDao {

    private MonAn readFromResultSet(ResultSet rs) throws SQLException {
        MonAn model = new MonAn();
        model.setMaMon(rs.getInt("MaMon"));
        model.setTenMon(rs.getString("TenMon"));
        model.setMaDanhMuc(rs.getInt("MaDanhMuc"));
        model.setDonGia(rs.getDouble("DonGia"));
        model.setHinhAnh(rs.getString("HinhAnh"));
        model.setTrangThai(rs.getInt("TrangThai"));
        return model;

    }

    public List<MonAn> select(String sql, Object... args) {
        List<MonAn> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = jdbcHelper.executeQuery(sql, args);
                while (rs.next()) {
                    list.add(readFromResultSet(rs));
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }
        return list;

    }

    /**
     * Thêm mới thực thể vào CSDL
     *
     * @param entity là thực thể chứa thông tin bản ghi mới
     */
    public void insert(MonAn entity) {
        String sql = "INSERT INTO MonAn(TenMon,MaDanhMuc,DonGia,HinhAnh,TrangThai) VALUES (?,?,?,?,?)";
        jdbcHelper.executeUpdate(sql,
                entity.getTenMon(),
                entity.getMaDanhMuc(),
                entity.getDonGia(),
                entity.getHinhAnh(),
                entity.getTrangThai()
        );

    }

    //
    public MonAn findByMaMon(Integer maMon) {
        String sql = "select * from MonAn\n"
                + "where TrangThai = 1 and MaMon = ?";
        List<MonAn> list = select(sql, maMon);
        return list.size() > 0 ? list.get(0) : null;
    }
//    public TaiKhoanMode findByTenTaiKhoan(String tenTaiKhoan) {
//        String sql = "select * from TaiKhoan\n"
//                + "where TenTaiKhoan = ? and trangThai = 1";
//        List<TaiKhoanMode> list = select(sql, tenTaiKhoan);
//        return list.size() > 0 ? list.get(0) : null;
//    }

}
