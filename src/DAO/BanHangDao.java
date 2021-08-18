/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Helper.jdbcHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import model.Ban;
import model.DanhMuc;
import model.KhuVuc;
import model.MonAn;

/**
 *
 * @author CHIEN
 */
public class BanHangDao {

    //code hướng mới
    //đọc 1 nhân viên từ bản ghi
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

    //load combo khu vực mới
    //đọc khu vực từ bản ghi
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

    //load combo khu vực
    public void loadDataToComBoKhuVuc(DefaultComboBoxModel cbx) {
        List<KhuVuc> khuVuc = selectListKhuVuc();
        cbx.removeAllElements();
        for (int i = 0; i < khuVuc.size(); i++) {
            KhuVuc kv = khuVuc.get(i);
            cbx.addElement(kv);
        }
    }

    //Danh mục
    //load combo danh mục
    //đọc danh mục từ bản ghi
    public DanhMuc readDanhMucFromResultSet(ResultSet rs) throws SQLException {
        DanhMuc mode = new DanhMuc();
        mode.setMaDanhMuc(rs.getInt(1));
        mode.setTenDanhMuc(rs.getString(2));

        return mode;
    }

    //lấy list danh sách danh mục
    public List<DanhMuc> selectDanhMuc(String sql, Object... args) {
        List<DanhMuc> danhMuc = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = jdbcHelper.executeQuery(sql, args);
                while (rs.next()) {
                    danhMuc.add(readDanhMucFromResultSet(rs));

                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        return danhMuc;
    }

    //truy vấn danh sách danh mục
    public List<DanhMuc> selectListDanhMuc() {
        String sql = "select * from DanhMuc\n"
                + "where TrangThaiHD = 1";
        return selectDanhMuc(sql);
    }

    //load danh mục vào combo
    public void LoadDataToComBoDanhMuc(DefaultComboBoxModel cbx) {
        cbx.removeAllElements();
        DanhMuc DMT = new DanhMuc();
        DMT.setTenDanhMuc("Tất cả các món");
        cbx.addElement(DMT);
        List<DanhMuc> danhMuc = selectListDanhMuc();
        for (int i = 0; i < danhMuc.size(); i++) {
            DanhMuc dm = danhMuc.get(i);
            cbx.addElement(dm);
        }
    }
    //load ban vao panel

    //món ăn
    //đọc món ăn từ bản ghi
    public MonAn readMonAnFromResultSet(ResultSet rs) throws SQLException {
        MonAn mode = new MonAn();
        mode.setMaMon(rs.getInt(1));
        mode.setTenMon(rs.getString(2));
        mode.setMaDanhMuc(rs.getInt(3));
        mode.setDonGia(rs.getDouble(4));
        mode.setHinhAnh(rs.getString(5));

        return mode;

    }
    //lấy list danh sách món

    public List<MonAn> selectMonAn(String sql, Object... args) {
        List<MonAn> monAn = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = jdbcHelper.executeQuery(sql, args);
                while (rs.next()) {
                    monAn.add(readMonAnFromResultSet(rs));
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        return monAn;
    }
//truy vấn danh sách món ăn

    public List<MonAn> selectListMonAn() {
        String sql = "select * from MonAn\n"
                + "where TrangThai=1";
        return selectMonAn(sql);
    }
//các thao tác bán hàng
    //truy vấn bàn
    //đổi trạng thái ngồi của bàn
    public void updateTrangThaiNgoiBan(Integer MaBan, Integer trangThaiNgoi) {
        String sql = "update Ban\n"
                + "set trangThaiNgoi = ?\n"
                + "where MaBan = ? and trangThaiHD = 1";
        jdbcHelper.executeUpdate(sql, trangThaiNgoi, MaBan);
    }

//    public void updateTrangThaiNgoiBan(String tenBan, Integer maKhuVuc, Integer trangThaiNgoi) {
//        String sql = "update Ban\n"
//                + "set trangThaiNgoi = ?\n"
//                + "where TenBan = ? and MaKhuVuc = ? and trangThaiHD = 1";
//        jdbcHelper.executeUpdate(sql, trangThaiNgoi, tenBan, maKhuVuc);
//    }

    //tìm mã bàn
    public Ban getBan(String tenBan, Integer MakhuVuc) {
        String sql = "select * from ban\n"
                + "where TenBan = ? and MaKhuVuc = ?";
        List<Ban> ban = selectBan(sql, tenBan, MakhuVuc);
        return ban.size() > 0 ? ban.get(0) : null;
    }

    //update trạng thái ngồi bàn theo mã bàn
    public void updateTrangThaiNgoiTheoMa(Integer trangThai, Integer maBan) {
        String sql = "update Ban\n"
                + "set trangThaiNgoi = ?\n"
                + "where MaBan = ?";
        jdbcHelper.executeUpdate(sql, trangThai,maBan);
    }
}
