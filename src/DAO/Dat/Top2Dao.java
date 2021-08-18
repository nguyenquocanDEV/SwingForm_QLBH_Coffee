/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.Dat;

import model.Top2;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author trong
 */
public class Top2Dao {

    private Top2 readFromResultSet(ResultSet rs) throws SQLException {
        Top2 model = new Top2();
        model.setTenMon(rs.getString(1));
        model.setSl(rs.getInt(2));
        model.setNgay(rs.getString(3));
        return model;
    }

    public List<Top2> select(String sql, Object... args) {
        List<Top2> list = new ArrayList<>();
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

    public List< Top2> TkTop2(String nam, String nam2, String nam3) {
        String sql = "SELECT TenMon,SOLUONG,CONVERT(NVARCHAR ,NgayBan,105)\n"
                + "from CTHoaDon JOIN HoaDon ON HoaDon.MaHD = CTHoaDon.MaHD\n"
                + "WHERE GhiChu like N'Đ%' and NgayBan  BETWEEN ?  AND GETDATE() AND TenMon =(  SELECT top 1 TenMon\n"
                + "                                                      from CTHoaDon JOIN HoaDon ON HoaDon.MaHD = CTHoaDon.MaHD\n"
                + "                                                      WHERE  NgayBan BETWEEN  ?  AND GETDATE() \n"
                + "                                                      GROUP BY TenMon\n"
                + "                                                      having sum(soluong) < (SELECT top 1  sum(soluong)\n"
                + "                                                      from CTHoaDon JOIN HoaDon ON HoaDon.MaHD = CTHoaDon.MaHD\n"
                + "                                                      WHERE  NgayBan BETWEEN  ?  AND GETDATE() \n"
                + "						                              GROUP BY TenMon\n"
                + "						                              ORDER BY sum(soluong) DESC )\n"
                + "                                                      ORDER BY sum(soluong) DESC    )\n"
                + "GROUP BY TenMon ,NgayBan,SoLuong";
        return select(sql, nam, nam2, nam3);
    }

    public List< Top2> getTop2_7ngay() {
        String sql = "SELECT TenMon,SOLUONG,CONVERT(NVARCHAR ,NgayBan,105)\n"
                + "from CTHoaDon JOIN HoaDon ON HoaDon.MaHD = CTHoaDon.MaHD\n"
                + "WHERE GhiChu like N'Đ%' and NgayBan  BETWEEN GETDATE()-7  AND GETDATE() AND TenMon =(  SELECT top 1 TenMon\n"
                + "                                                      from CTHoaDon JOIN HoaDon ON HoaDon.MaHD = CTHoaDon.MaHD\n"
                + "                                                      WHERE  NgayBan BETWEEN  GETDATE()-7   AND GETDATE() \n"
                + "                                                      GROUP BY TenMon\n"
                + "                                                      having sum(soluong) < (SELECT top 1  sum(soluong)\n"
                + "                                                      from CTHoaDon JOIN HoaDon ON HoaDon.MaHD = CTHoaDon.MaHD\n"
                + "                                                      WHERE  NgayBan BETWEEN  GETDATE()-7   AND GETDATE() \n"
                + "						                              GROUP BY TenMon\n"
                + "						                              ORDER BY sum(soluong) DESC )\n"
                + "                                                      ORDER BY sum(soluong) DESC    )\n"
                + "GROUP BY TenMon ,NgayBan,SoLuong";
        return select(sql);
    }

    public List< Top2> DlTop2() {
        String sql = " SELECT  TenMon,SUM(SOLUONG),GETDATE()\n"
                + "  from CTHoaDon JOIN HoaDon ON HoaDon.MaHD = CTHoaDon.MaHD\n"
                + "  WHERE  NgayBan BETWEEN GETDATE()-7  AND GETDATE()\n"
                + "  GROUP BY TenMon\n"
                + "  having sum(soluong) = ( SELECT top 1 SUM(SOLUONG)\n"
                + "                         from CTHoaDon JOIN HoaDon ON HoaDon.MaHD = CTHoaDon.MaHD\n"
                + "                         WHERE  NgayBan BETWEEN GETDATE()-7  AND GETDATE()\n"
                + "                         GROUP BY TenMon\n"
                + "                         having sum(soluong) < (SELECT top 1  sum(soluong)\n"
                + "                         from CTHoaDon JOIN HoaDon ON HoaDon.MaHD = CTHoaDon.MaHD\n"
                + "                         WHERE  NgayBan BETWEEN GETDATE()-7  AND GETDATE()\n"
                + "						 GROUP BY TenMon\n"
                + "						 ORDER BY sum(soluong) DESC)\n"
                + "                        ORDER BY sum(soluong) DESC)\n"
                + "  ORDER BY sum(soluong) DESC";
        return select(sql);
    }

    public List< Top2> DlTkTop1(String nam, String nam2, String nam3) {
        String sql = "  SELECT  TenMon,SUM(SOLUONG),GETDATE()\n"
                + "  from CTHoaDon JOIN HoaDon ON HoaDon.MaHD = CTHoaDon.MaHD\n"
                + "  WHERE  NgayBan =?\n"
                + "  GROUP BY TenMon\n"
                + "  having sum(soluong) = ( SELECT top 1 SUM(SOLUONG)\n"
                + "                         from CTHoaDon JOIN HoaDon ON HoaDon.MaHD = CTHoaDon.MaHD\n"
                + "                         WHERE  NgayBan =?\n"
                + "                         GROUP BY TenMon\n"
                + "                         having sum(soluong) < (SELECT top 1  sum(soluong)\n"
                + "                         from CTHoaDon JOIN HoaDon ON HoaDon.MaHD = CTHoaDon.MaHD\n"
                + "                         WHERE  NgayBan =?\n"
                + "						 GROUP BY TenMon\n"
                + "						 ORDER BY sum(soluong) DESC)\n"
                + "                        ORDER BY sum(soluong) DESC)\n"
                + "  ORDER BY sum(soluong) DESC";
        return select(sql, nam, nam2, nam3);
    }

}
