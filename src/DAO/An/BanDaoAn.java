/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.An;

import Helper.jdbcHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Ban;

/**
 *
 * @author CHIEN
 */
public class BanDaoAn {
    //load combob= ban new

    public Ban readFromBanResultSet(ResultSet rs) throws SQLException {
        Ban modeBan = new Ban();
        modeBan.setMaBan(rs.getInt(1));
        modeBan.setTenBan(rs.getString(2));
        modeBan.setMaKhuVuc(rs.getInt(3));
        modeBan.setTrangThai(rs.getBoolean(4));
        
        return modeBan;
    }

    //láy list danh sách bàn
    public List<Ban> selectBan(String sql, Object... args) {
        List<Ban> ban = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = jdbcHelper.executeQuery(sql, args);
                while (rs.next()) {
                    ban.add(readFromBanResultSet(rs));
                }
            } finally {
                rs.getStatement().getConnection().close();//đóng kết nối
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        return ban;
    }

    //truy vấn bàn
    public List<Ban> selectListBan() {
        String sql = "select * from Ban\n"
                + "where trangThaiHD = 1";
        return selectBan(sql);
    }

    //truy vấn bàn đang hoạt động theo mã khu vực
    public List<Ban> selectListKhucVuc(Integer maKV) {
        String sql = "select * from Ban\n"
                + "where MaKhuVuc = ? and trangThaiHD = 1";
        return selectBan(sql, maKV);
    }

    //update ẩn
    public void updateAn(Integer maBan) {
        String sql = "update Ban\n"
                + "set trangThaiHD = 0\n"
                + "where MaBan = ?";
        jdbcHelper.executeUpdate(sql, maBan);
    }

    //update tên bàn
    public void updateTenBan(Integer maBan, String tenBan) {
        String sql = "update Ban\n"
                + "set TenBan = ?\n"
                + "where MaBan = ?";
        jdbcHelper.executeUpdate(sql, tenBan, maBan);
    }

    //check tên bàn
    public boolean checkTenBan(String TenBan) {
        String sql = "select * from ban\n"
                + "where trangThaiHD = 1 and TenBan = ?";
        List<Ban> list = selectBan(sql, TenBan);
        return list.size() != 0 ? false : true;
    }

    //insert bàn
    public void insertBan(String tenBan, Integer maKV) {
        String sql = "insert into Ban values(?,?,0,1)";
        jdbcHelper.executeUpdate(sql, tenBan, maKV);
    }
}
