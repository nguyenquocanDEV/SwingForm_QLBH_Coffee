/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.Dat;

import model.HoaDon;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sieu Nhan Bay
 */
public class HoaDonDao {

    private HoaDon readFromResultSet(ResultSet rs) throws SQLException {
        HoaDon model = new HoaDon();
        model.setMaHD(rs.getString(1));
        model.setMaBan(rs.getInt(2));
        model.setNgayBan(rs.getString(3));
        model.setTenBan(rs.getString(4));
        model.setTenTaiKhoan(rs.getString(5));
        model.setTongTien(rs.getFloat(6));
        model.setMaSuKien(rs.getString(7));
        model.setUuDai(rs.getFloat(8));
        model.setTongTienThanhToan(rs.getFloat(9));
        model.setGhiChu(rs.getString(10));
        model.setSoHD(rs.getInt(11));
        return model;
    }

    public List<HoaDon> select(String sql, Object... args) {
        List<HoaDon> list = new ArrayList<>();
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

    public List<HoaDon> select() {
        String sql = "SELECT MaHD,MaBan,CONVERT(NVARCHAR,NGAYBAN,103),TenBan,\n"
                + "TenTaiKhoan,TongTien,MaSuKien,UuDai,TongTienThanhToan,GhiChu,SoHD\n"
                + "FROM HoaDon \n"
                + "where GhiChu = N'Đã thanh toán'";
        return select(sql);
    }

    public List<HoaDon> TimKiemMa(String id) {
        String sql = "SELECT MaHD,MaBan,CONVERT(NVARCHAR,NGAYBAN,103),TenBan,TenTaiKhoan,TongTien,MaSuKien,UuDai,TongTienThanhToan,GhiChu,SoHD\n"
                + "FROM HoaDon\n"
                + "where MaHD = ? and GhiChu = N'Đã thanh toán'";
        return select(sql, id);

    }

    public List<HoaDon> TimKiemNgay(String ngay) {
        String sql = "SELECT MaHD,MaBan,CONVERT(NVARCHAR,NGAYBAN,103),TenBan,TenTaiKhoan,TongTien,MaSuKien,UuDai,TongTienThanhToan,GhiChu,SoHD\n"
                + "FROM HoaDon\n"
                + "where CONVERT(nvarchar,ngayBan,103) = ? and GhiChu = N'Đã thanh toán'";
        return select(sql, ngay);

    }

    public List<HoaDon> findById(String id) {
        String sql = "SELECT *from HoaDon\n"
                + "where MaHD = ? and GhiChu = N'Đã thanh toán'";
        return select(sql, id);

    }
}
