/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.Duc;

import Helper.jdbcHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import model.DanhMuc;

/**
 *
 * @author ducmc
 */
public class danhMucDao {

    private DanhMuc readFromResulset(ResultSet rs) throws SQLException {
        DanhMuc dm = new DanhMuc();
        dm.setMaDanhMuc(rs.getInt("MADANHMUC"));
        dm.setTenDanhMuc(rs.getString("TENDANHMUC"));

        return dm;
    }

    public List<DanhMuc> Select(String sql, Object... args) {
        List<DanhMuc> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = jdbcHelper.executeQuery(sql, args);
                while (rs.next()) {
                    list.add(readFromResulset(rs));
                }
            } finally {
                rs.getStatement().getConnection().close();

            }
        } catch (Exception e) {
        }
        return list;
    }

    public void insert(String tenDanhMuc, Integer trangThaiHd) {
        String sql = "INSERT INTO DANHMUC (TenDanhMuc,TrangThaiHD) VALUES (?,?)";
        jdbcHelper.executeUpdate(sql, tenDanhMuc, trangThaiHd
        );

    }

    public List<DanhMuc> selectListDanhMuc() {
        String sql = "select * from DanhMuc\n"
                + "where TrangThaiHD = 1";

        return Select(sql);
    }

    public void LoadDataToComBoDanhMuc(DefaultComboBoxModel cbx) {
        cbx.removeAllElements();
        DanhMuc DMT = new DanhMuc();
        DMT.setTenDanhMuc("Chọn danh mục");
        cbx.addElement(DMT);
        List<DanhMuc> danhMuc = selectListDanhMuc();
        for (int i = 0; i < danhMuc.size(); i++) {
            DanhMuc dm = danhMuc.get(i);
            cbx.addElement(dm);
        }
    }

    public void delete(String tenDm) {
        String sql = "DELETE FROM DanhMuc \n"
                + "WHERE TenDanhMuc = ?";
        jdbcHelper.executeUpdate(sql, tenDm);
    }

//    public void loadMadm(DefaultComboBoxModel cbxn) {
//        String sql = "SELECT MADANHMUC FROM DANHMUC WHERE TENDANHMUC = ?";
//        jdbcHelper.executeUpdate(sql, cbxn);
//
//    }

}
