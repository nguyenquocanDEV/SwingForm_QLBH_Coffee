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
import javax.swing.DefaultComboBoxModel;
import model.KhuVuc;

/**
 *
 * @author CHIEN
 */
public class KhuVucDaoAn {
    
    public KhuVuc readComBoKhuVucFromResultSet(ResultSet rs) throws SQLException {
        KhuVuc mode = new KhuVuc();
        mode.setMaKhuVuc(rs.getInt(1));
        mode.setTenKhuVuc(rs.getString(2));
        mode.setTrangThai(rs.getBoolean(3));
        
        return mode;
    }

    //lấy list danh sách khu vực
    public List<KhuVuc> selectKhuVuc(String sql, Object... args) {
        List<KhuVuc> khuVuc = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = jdbcHelper.executeQuery(sql, args);
                while (rs.next()) {
                    khuVuc.add(readComBoKhuVucFromResultSet(rs));
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        return khuVuc;
    }

    //truy vấn danh sách khu vưc
    public List<KhuVuc> selectListKhuVuc() {
        String sql = "select * from KhuVuc\n"
                + "where TrangThaiHD = 1";
        return selectKhuVuc(sql);
    }

    //check xem có trùng tên với khu vực đang còn hoạt động không
    public boolean checkTenKV(String tenKV) {
        String sql = "select * from KhuVuc\n"
                + "where TenKhuVuc = ? and TrangThaiHD = 1";
        List<KhuVuc> list = selectKhuVuc(sql, tenKV);
        return list.size() > 0 ? false : true;
    }

    //update ẩn khu vực
    public void updateAn(Integer Makv) {
        String sql = "update KhuVuc\n"
                + "set TrangThaiHD = 0\n"
                + "where MaKhuVuc = ?";
        jdbcHelper.executeUpdate(sql, Makv);
        
    }

    //inssert khu vuc
    public void insertKhuVuc(String TenKV) {
        String sql = "insert into KhuVuc values(?,1)";
        jdbcHelper.executeUpdate(sql, TenKV);
    }

    //load combo khu vực
    public void loadDataToComBoKhuVuc(DefaultComboBoxModel cbx) {
        List<KhuVuc> khuVuc = selectListKhuVuc();
        cbx.removeAllElements();
        for (int i = 0; i < khuVuc.size(); i++) {
            KhuVuc kv = khuVuc.get(i);
            cbx.addElement(kv);
        }
    }
}
