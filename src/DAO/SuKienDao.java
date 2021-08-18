/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Helper.ChuyenDoi;
import Helper.jdbcHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import model.SuKien;
import qlbancoffe.SuKienBH;

/**
 *
 * @author CHIEN
 */
public class SuKienDao {

    //LỖI LÀM LẠI
    //đọc 1 sự kiện từ bản ghi
    public SuKien readSuKienFromResultSet(ResultSet rs) throws SQLException {
        SuKien mode = new SuKien();
        mode.setMaSuKien(rs.getString(1));
        mode.setTenSuKien(rs.getString(2));
        mode.setUuDai(rs.getInt(3));
        mode.setTgBatDau(rs.getString(4));
        mode.setTgKetThuc(rs.getString(5));
        mode.setLoaiSuKien(rs.getBoolean(6));
        mode.setTrangThai(rs.getBoolean(7));
        System.out.println(mode);

        return mode;
    }

    //lấy list danh sách sự kiện
    public List<SuKien> selectSuKien(String sql, Object... args) {
        List<SuKien> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = jdbcHelper.executeQuery(sql, args);
                while (rs.next()) {
                    list.add(readSuKienFromResultSet(rs));
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        return list;
    }

    //load data to combo
    public void LoadDataToComBo(DefaultComboBoxModel cbx) {
        cbx.removeAllElements();
        String sql = "select * from SuKien\n"
                + "where TrangThai = 1 and AnSk = 1";
        List<SuKien> list = selectSuKien(sql);
        for (int i = 0; i < list.size(); i++) {
            SuKien sk = list.get(i);
            cbx.addElement(sk);
        }
    }

    //lấy list sự kiện
    public List<SuKien> getlistSuKienBH() {
        String sql = "select MaSuKien,TenSuKien,UuDai,CONVERT(nvarchar,tgBatDau,103),\n"
                + "CONVERT(nvarchar,tgKetThuc,103),LoaiSuKien,TrangThai,AnSK\n"
                + "from SuKien\n"
                + "where AnSk = 1";
        List<SuKien> listSK = selectSuKien(sql);
        return listSK;
    }

    //load datatoTable
    public void loadDatatoTable(DefaultTableModel mode, boolean trangThai) {
        updateSukienAn();
        List<SuKien> list = getlistSuKienBH();
        mode.setRowCount(0);
        for (int i = 0; i < list.size(); i++) {
            SuKien sk = list.get(i);
            if (sk.isTrangThai() == trangThai) {
                Vector v = new Vector();
                v.add(sk.getMaSuKien());
                v.add(sk.getTenSuKien());
                v.add(sk.getUuDai());
                v.add(sk.getTgBatDau());
                v.add(sk.getTgKetThuc());
                mode.addRow(v);

            }
        }
    }

    public void updateSukienAn() {
        String sql = "update SuKien\n"
                + "set AnSK = 0\n"
                + "where datediff(day,GETDATE(),tgKetThuc) <0 and LoaiSuKien = 1";
        jdbcHelper.executeUpdate(sql);
    }

    //tìm sự kiện theo mã
    public SuKien findByMaSK(String maSK) {
        String sql = "select * from SuKien\n"
                + "where MaSuKien = ?";
        List<SuKien> list = selectSuKien(sql, maSK);
        return list.size() > 0 ? list.get(0) : null;
    }

    //tìm sự kiện theo tên (check trùng tên khi sự kiện đang hoạt động)
    public SuKien findByTenSK(String tenSK) {
        String sql = "select * from SuKien\n"
                + "where TenSuKien = ? and TrangThai = 1 and AnSK = 1";
        List<SuKien> list = selectSuKien(sql, tenSK);
        return list.size() > 0 ? list.get(0) : null;
    }

    //update sukien
    public void updateSuKien(boolean huy, SuKien sk) {
        if (!huy) {
            String sql = "update SuKien\n"
                    + "set TenSuKien = ?,\n"
                    + "	UuDai = ?,\n"
                    + "	tgKetThuc = ?,\n"
                    + "	LoaiSuKien = ?\n"
                    + "where MaSuKien = ?";
            jdbcHelper.executeUpdate(sql,
                    sk.getTenSuKien(),
                    sk.getUuDai(),
                    sk.getTgKetThuc(),
                    sk.isLoaiSuKien(),
                    sk.getMaSuKien());
        } else {
            String sql = "update SuKien\n"
                    + "set TrangThai = 0\n"
                    + "where MaSuKien = ?";
            jdbcHelper.executeUpdate(sql, sk.getMaSuKien());
        }
    }

    //ẩn sự kiện
    public void anSuKien(SuKien sk) {
        String sql = "update SuKien\n"
                + "set AnSK = 0\n"
                + "where MaSuKien = ?";
        jdbcHelper.executeUpdate(sql, sk.getMaSuKien());
    }

    //insert một sự kiện
    public void insertSuKien(SuKien sk, boolean loaiSK) {
        if (loaiSK) {
            String sql = "insert into SuKien VALUES(?,?,?,GETDATE(),?,1,1,1)";
            jdbcHelper.executeUpdate(sql,
                    sk.getMaSuKien(),
                    sk.getTenSuKien(),
                    sk.getUuDai(),
                    sk.getTgKetThuc());
        } else {
            String sql = "insert into SuKien VALUES(?,?,?,GETDATE(),null,0,1,1)";
            jdbcHelper.executeUpdate(sql,
                    sk.getMaSuKien(),
                    sk.getTenSuKien(),
                    sk.getUuDai());
        }
    }

    //tìm sự kiện
    public List<SuKien> findSuKien(String chuoi) {
        String sql = "select MaSuKien,TenSuKien,UuDai,CONVERT(nvarchar,tgBatDau,103),\n"
                + "CONVERT(nvarchar,tgKetThuc,103),LoaiSuKien,TrangThai,AnSK\n"
                + "from SuKien\n"
                + "where TrangThai = 1 and AnSk = 1	\n"
                + "and ((MaSuKien + TenSuKien + CONVERT(nvarchar,UuDai,0) +CONVERT(nvarchar,tgBatDau,103)+CONVERT(nvarchar,tgKetThuc,103)) like ?\n"
                + "or (MaSuKien + TenSuKien + CONVERT(nvarchar,UuDai,0) +CONVERT(nvarchar,tgBatDau,103)) like ?)";
        List<SuKien> list = selectSuKien(sql, "%" + chuoi + "%", "%" + chuoi + "%");
        return list;
    }

    //load datatoTable sau khi tìm kiếm
    public void loadDataFindToTable(DefaultTableModel mode, boolean trangThai, String chuoi) {
        List<SuKien> list = findSuKien(chuoi);
        mode.setRowCount(0);
        for (int i = 0; i < list.size(); i++) {
            SuKien sk = list.get(i);
            if (sk.isTrangThai() == trangThai) {
                Vector v = new Vector();
                v.add(sk.getMaSuKien());
                v.add(sk.getTenSuKien());
                v.add(sk.getUuDai());
                v.add(sk.getTgBatDau());
                v.add(sk.getTgKetThuc());
                mode.addRow(v);
            }
        }
    }
    //tính ngày hợp lệ khi thêm

    public boolean checkNgayThem(Date ngayKT) throws SQLException {
        SimpleDateFormat fomater = new SimpleDateFormat("MM/dd/yyyy");
        String ngayKetThuc = fomater.format(ngayKT);
        String sql = "select datediff(day,GETDATE(),?)";
        ResultSet rs = jdbcHelper.executeQuery(sql, ngayKetThuc);

        Integer soNgay = null;
        if (rs.next()) {
            soNgay = rs.getInt(1);
        }
        return soNgay < 0 ? false : true;
    }
}
