/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Helper.ChuyenDoi;
import Helper.HanhDong;
import Helper.jdbcHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import model.CTHoaDon;
import model.MonAn;

/**
 *
 * @author CHIEN
 */
public class CTHoaDonBHDao {

    //đọc một đối tượng cthoaDon
    private CTHoaDon readCTHoaDonFromResultSet(ResultSet rs) throws SQLException {
        CTHoaDon mode = new CTHoaDon();
        mode.setMaHD(rs.getString(1));
        mode.setMaMon(rs.getInt(2));
        mode.setTenMon(rs.getString(3));
        mode.setSoLuong(rs.getInt(4));
        mode.setDonGia(rs.getFloat(5));
        mode.setThanhTien(rs.getFloat(6));
        return mode;
    }

    //lấy về một list cthoadon
    public List<CTHoaDon> selectCTHoaDon(String sql, Object... args) {
        List<CTHoaDon> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = jdbcHelper.executeQuery(sql, args);
                while (rs.next()) {
                    list.add(readCTHoaDonFromResultSet(rs));
                }
            } catch (Exception e) {
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        return list;
    }

    //lay về đối tượng cthoadon bằng mã hd
    public List<CTHoaDon> findCTHoaDonByMaHD(String maHD) {
        String sql = "select * from CTHoaDon\n"
                + "where MaHD = ?";
        List<CTHoaDon> list = selectCTHoaDon(sql, maHD);
        return list;
    }

    //thêm 1 ct hoadon vào mahd
    public void insertCTHoaDon(String MaHD, MonAn mon, Integer sl) {
        String sql = "insert into CTHoaDon values(?,?,?,?,?,?)";
        double thanhTien;
        thanhTien = sl * mon.getDonGia();
        jdbcHelper.executeUpdate(sql,
                MaHD,
                mon.getMaMon(),
                mon.getTenMon(),
                sl,
                mon.getDonGia(),
                thanhTien);
    }

    //delete một cthd
    public void deleteCTHoaDon(String MaHD, Integer MaMon) {
        String sql = "delete from CTHoaDon\n"
                + "where MaHD = ? and MaMon = ?";
        jdbcHelper.executeUpdate(sql, MaHD, MaMon);
    }

    //update số lượng ct hóa đớn
    public void updateSLCTHoaDon(String MaHD, Integer MaMon, String SL) {
        String sql = "update CTHoaDon\n"
                + "set SoLuong = ?,\n"
                + "	ThanhTien = ? * DonGia\n"
                + "where MaHD = ? and MaMon = ?";
        jdbcHelper.executeUpdate(sql, SL, SL, MaHD, MaMon);
    }

    //update số lượng ct hóa đơn chia
    public void updateSLCTHoaDonChia(String MaHD, Integer MaMon, String SL) {
        String sql = "update CTHoaDon\n"
                + "set SoLuong = SoLuong -?,\n"
                + "	ThanhTien = (SoLuong - ?) * DonGia\n"
                + "where MaHD = ? and MaMon = ?";
        jdbcHelper.executeUpdate(sql, SL, SL, MaHD, MaMon);
    }

    //load dữ liêu cthoadon vào table
    public void loadDaTaToTableCTHD(String maHD, DefaultTableModel mode) {
        HanhDong.TongTien = 0;
        List<CTHoaDon> CTHD = findCTHoaDonByMaHD(maHD);
        mode.setRowCount(0);

        for (int i = 0; i < CTHD.size(); i++) {
            CTHoaDon cthd = CTHD.get(i);
            if (cthd.getSoLuong() != 0) {
                Vector v = new Vector();
                v.add(i + 1);
                v.add(cthd.getMaMon());
                v.add(cthd.getTenMon());
                v.add(cthd.getSoLuong());
                v.add(ChuyenDoi.chuyenDoiTien(cthd.getDonGia()));
                v.add(ChuyenDoi.chuyenDoiTien(cthd.getThanhTien()));
                v.add(false);

                HanhDong.TongTien = HanhDong.TongTien + cthd.getDonGia() * cthd.getSoLuong();
                mode.addRow(v);
            } else {
                deleteCTHoaDon(maHD, cthd.getMaMon());
            }

        }
    }

    //load dữ liêu cthoadon vào tableHD
    public void loadDaTaToTableCTHD2(String maHD, DefaultTableModel mode) {
        HanhDong.TongTien = 0;
        List<CTHoaDon> CTHD = findCTHoaDonByMaHD(maHD);
        mode.setRowCount(0);

        for (int i = 0; i < CTHD.size(); i++) {
            CTHoaDon cthd = CTHD.get(i);
            if (cthd.getSoLuong() != 0) {
                Vector v = new Vector();
                v.add(i + 1);
                v.add(cthd.getTenMon());
                v.add(cthd.getSoLuong());
                v.add(ChuyenDoi.chuyenDoiTien(cthd.getDonGia()));
                v.add(ChuyenDoi.chuyenDoiTien(cthd.getThanhTien()));

                HanhDong.TongTien = HanhDong.TongTien + cthd.getDonGia() * cthd.getSoLuong();
                mode.addRow(v);
            } else {
                deleteCTHoaDon(maHD, cthd.getMaMon());
            }

        }
    }

    //insert ctHoaDon theo từng mã
    //thêm 1 ct hoadon vào mahd
    public void insertCTHoaDonGopBan(String MaHD, Integer MaMon, String TenMon, Integer Sl, double DonGia) {
        String sql = "insert into CTHoaDon values(?,?,?,?,?,?)";
        double thanhTien;
        thanhTien = Sl * DonGia;
        jdbcHelper.executeUpdate(sql,
                MaHD,
                MaMon,
                TenMon,
                Sl,
                DonGia,
                thanhTien);
    }
//    load ct hóa đơn gộp bàn mới

    public void loadCTHoaDonGop(String MaHDMoi, String MaHDGop, String MaHDChuyen) {
        String sql = "select MaMon, TenMon, sum(SoLuong) as SL, DonGia, sum(SoLuong) * DonGia\n"
                + "from CTHoaDon\n"
                + "where MaHD = ? or MaHD = ?\n"
                + "group by MaMon, TenMon, DonGia";
        try {
            ResultSet rs = null;
            try {
                rs = jdbcHelper.executeQuery(sql, MaHDGop, MaHDChuyen);
                while (rs.next()) {
                    insertCTHoaDonGopBan(MaHDMoi, rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getDouble(4));
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    //delete ct hoadon cũ gộp bàn
    public void deleteCTHoaDonCuGop(String MaHDGop, String MaHDChuyen) {
        String sql = "delete from CTHoaDon\n"
                + "where MaHD = ? or MaHD =?";
        jdbcHelper.executeUpdate(sql, MaHDGop, MaHDChuyen);
    }
}
