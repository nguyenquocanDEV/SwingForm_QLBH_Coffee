/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.Dat;

import model.Top1;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author trong
 */
public class Top1Dao {

    private Top1 readFromResultSet(ResultSet rs) throws SQLException {
        Top1 model = new Top1();
        model.setTenMon(rs.getString(1));
        model.setSl(rs.getInt(2));
        model.setNgay(rs.getString(3));
        return model;
    }

    public List<Top1> select(String sql, Object... args) {
        List<Top1> list = new ArrayList<>();
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

    public List< Top1> TkTop1(String nam, String nam2) {
        String sql = "SELECT TenMon,SOLUONG,CONVERT(NVARCHAR ,NgayBan,105)\n"
                + "from CTHoaDon JOIN HoaDon ON HoaDon.MaHD = CTHoaDon.MaHD\n"
                + "WHERE GhiChu = N'Đã thanh toán' and NgayBan  BETWEEN ? AND GETDATE() AND TenMon =(SELECT top 1   TenMon\n"
                + "                                                               from CTHoaDon JOIN HoaDon ON HoaDon.MaHD = CTHoaDon.MaHD\n"
                + "                                                                WHERE  NgayBan BETWEEN  ?  AND GETDATE() \n"
                + "								GROUP BY TenMon\n"
                + "                                                               ORDER BY sum(soluong) DESC)\n"
                + "GROUP BY TenMon ,NgayBan,SoLuong";
        return select(sql, nam, nam2);
    }

    public List< Top1> getTop1_7ngay() {
        String sql = "SELECT TenMon,SOLUONG,CONVERT(NVARCHAR ,NgayBan,105)\n"
                + "from CTHoaDon JOIN HoaDon ON HoaDon.MaHD = CTHoaDon.MaHD\n"
                + "WHERE GhiChu = N'Đã thanh toán' and NgayBan  BETWEEN GETDATE()-7 AND GETDATE() AND TenMon =(SELECT top 1   TenMon\n"
                + "                                                               from CTHoaDon JOIN HoaDon ON HoaDon.MaHD = CTHoaDon.MaHD\n"
                + "                                                                WHERE  NgayBan BETWEEN  GETDATE()-7  AND GETDATE() \n"
                + "																GROUP BY TenMon\n"
                + "                                                               ORDER BY sum(soluong) DESC)\n"
                + "GROUP BY TenMon ,NgayBan,SoLuong";
        return select(sql);
    }

    public List< Top1> DlTop1() {
        String sql = "SELECT   TenMon,SUM(SOLUONG),GETDATE() \n"
                + "from CTHoaDon JOIN HoaDon ON HoaDon.MaHD = CTHoaDon.MaHD\n"
                + "WHERE  (NgayBan BETWEEN  GETDATE()-7  AND GETDATE()) and GhiChu = N'Đã thanh toán'   \n"
                + "GROUP BY TenMon\n"
                + "HAVING sum(soluong) =( SELECT DISTINCT TOP 1 sum(soluong)\n"
                + "            from CTHoaDon JOIN HoaDon ON HoaDon.MaHD = CTHoaDon.MaHD\n"
                + "			WHERE  NgayBan BETWEEN  GETDATE()-7  AND GETDATE() \n"
                + "            GROUP BY TenMon\n"
                + "            ORDER BY sum(soluong) DESC)\n"
                + "ORDER BY sum(soluong) DESC";
        return select(sql);
    }

    public List< Top1> DlTkTop1(String nam, String nam2) {
        String sql = "SELECT   TenMon,SUM(SOLUONG),GETDATE() \n"
                + "from CTHoaDon JOIN HoaDon ON HoaDon.MaHD = CTHoaDon.MaHD\n"
                + "WHERE  NgayBan =? and GhiChu = N'Đã thanh toán'\n"
                + "GROUP BY TenMon\n"
                + "HAVING sum(soluong) =( SELECT DISTINCT TOP 1 sum(soluong)\n"
                + "            from CTHoaDon JOIN HoaDon ON HoaDon.MaHD = CTHoaDon.MaHD\n"
                + "			WHERE  NgayBan =? \n"
                + "            GROUP BY TenMon\n"
                + "            ORDER BY sum(soluong) DESC)\n"
                + "ORDER BY sum(soluong) DESC";
        return select(sql, nam, nam2);
    }
}
