/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Helper.HanhDong;
import Helper.jdbcHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.HoaDonBH;

/**
 *
 * @author CHIEN
 */
public class HoaDonBHDao {

    //đọc hóa đơn từ bản ghi
    public HoaDonBH readHoaDonBHFromResultSet(ResultSet rs) throws SQLException {
        HoaDonBH mode = new HoaDonBH();
        mode.setMaHD(rs.getString(1));
        mode.setMaBan(rs.getInt(2));
        mode.setNgayBan(rs.getString(3));
        mode.setTenBan(rs.getString(4));
        mode.setTenTaiKhoan(rs.getString(5));
        mode.setTongTien(rs.getFloat(6));
        mode.setMaSuKien(rs.getString(7));
        mode.setUuDai(rs.getFloat(8));
        mode.setTongTienThanhToan(rs.getFloat(9));
        mode.setTrangThaiThanhToan(rs.getBoolean(10));
        mode.setSoHD(rs.getInt(11));
        return mode;
    }

    //lấy lits danh sách hóa đơn
    public List<HoaDonBH> selectHDBH(String sql, Object... args) {
        List<HoaDonBH> listHD = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = jdbcHelper.executeQuery(sql, args);
                while (rs.next()) {
                    listHD.add(readHoaDonBHFromResultSet(rs));
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        return listHD;
    }

    //truy vấn soHD
    public Integer getSoHD() {
        String sql = "select max(soHD) from HoaDon";
        Integer soHD = 1;
        try {
            ResultSet rs = null;
            try {
                rs = jdbcHelper.executeQuery(sql);
                if (rs.next()) {
                    soHD = rs.getInt(1) + 1;
                } else {
                    soHD = 1;
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();

        }
        return soHD;
    }

    //truy vấn hóa đơn theo mã bàn
    public HoaDonBH getHoaDon(int maBan) {
        String sql = "select * from HoaDon\n"
                + "where GhiChu = N'Chưa Thanh Toán' and MaBan = ?";
        List<HoaDonBH> list = new ArrayList<>();
        list = selectHDBH(sql, maBan);
        return list.size() != 0 ? list.get(0) : null;
    }
//     public TaiKhoanMode findByTenTaiKhoan(String tenTaiKhoan) {
//        String sql = "select * from TaiKhoan\n"
//                + "where TenTaiKhoan = ? and trangThai = 1";
//        List<TaiKhoanMode> list = select(sql, tenTaiKhoan);
//        return list.size() > 0 ? list.get(0) : null;
//    }
    //tạo một hóa đơn tạm thời

    public void insertHoaDon(HoaDonBH entity) {
        String sql = "insert into HoaDon values(?,?,GETDATE(),?,?,?,?,?,?,?)";
        jdbcHelper.executeUpdate(sql,
                entity.getMaHD(),
                entity.getMaBan(),
                entity.getTenBan(),
                entity.getTenTaiKhoan(),
                entity.getTongTien(),
                entity.getMaSuKien(),
                entity.getUuDai(),
                entity.getTongTienThanhToan(),
                entity.getGhiChu()
        );
    }

    //xóa hóa đơn
    public void deleteHoaDonTamThoi(String maHD) {
        String sql = "delete from HoaDon\n"
                + "where GhiChu = N'Chưa Thanh Toán' and MaHD = ?";
        jdbcHelper.executeUpdate(sql, maHD);
    }

    //upadate
    //doi ma ban
    public void updateMabanHoadon(String maHD, Integer maBan, String tenBan) {
        String sql = "update HoaDon\n"
                + "set MaBan = ?,\n"
                + "	TenBan = ?\n"
                + "where MaHD = ?";
        jdbcHelper.executeUpdate(sql, maBan, tenBan, maHD);
    }

    //thanh toán hoa đơn
    public void thanhToanHoaDon(String tenTk, double tongTien, String maSK, double UD, double TienTT, String maHD) {
        String sql = "update HoaDon\n"
                + "set TenTaiKhoan = ?,\n"
                + "	TongTien = ?,\n"
                + "	MaSuKien = ?,\n"
                + "	UuDai = ?,\n"
                + "	TongTienThanhToan = ?,\n"
                + "	GhiChu = N'Đã Thanh Toán'\n"
                + "where MaHD = ?";
        jdbcHelper.executeUpdate(sql, tenTk, tongTien, maSK, UD, TienTT, maHD);
    }
}
