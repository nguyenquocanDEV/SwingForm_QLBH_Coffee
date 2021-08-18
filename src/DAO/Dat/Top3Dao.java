/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.Dat;

import model.Top3;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author trong
 */
public class Top3Dao {

    private Top3 readFromResultSet(ResultSet rs) throws SQLException {
        Top3 model = new Top3();
        model.setTenMon(rs.getString(1));
        model.setSl(rs.getInt(2));
        model.setNgay(rs.getString(3));
        return model;
    }

    public List<Top3> select(String sql, Object... args) {
        List<Top3> list = new ArrayList<>();
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

    public List< Top3> TkTop3(String nam, String nam2, String nam3, String nam4) {
        String sql = "SELECT TenMon,SOLUONG,CONVERT(NVARCHAR ,NgayBan,105)\n"
                + "from CTHoaDon JOIN HoaDon ON HoaDon.MaHD = CTHoaDon.MaHD\n"
                + "WHERE GhiChu like N'Đ%' and NgayBan  BETWEEN ? AND GETDATE() AND TenMon =( SELECT top 1  TenMon\n"
                + "                                                                from CTHoaDon JOIN HoaDon ON HoaDon.MaHD = CTHoaDon.MaHD\n"
                + "                                                                WHERE  NgayBan BETWEEN  ?  AND GETDATE()  \n"
                + "                                                                GROUP BY TenMon\n"
                + "                                                                having sum(soluong) <(SELECT top 1 sum(soluong)\n"
                + "                                                                from CTHoaDon JOIN HoaDon ON HoaDon.MaHD = CTHoaDon.MaHD\n"
                + "                                                                 WHERE  NgayBan BETWEEN  ?  AND GETDATE() \n"
                + "                                                                GROUP BY TenMon\n"
                + "                                                                having sum(soluong) < (SELECT top 1  sum(soluong)\n"
                + "                                                                from CTHoaDon JOIN HoaDon ON HoaDon.MaHD = CTHoaDon.MaHD\n"
                + "                                                                WHERE  NgayBan BETWEEN  ?  AND GETDATE()  \n"
                + "						                                        GROUP BY TenMon\n"
                + "						                                        ORDER BY sum(soluong) DESC)\n"
                + "                                                                ORDER BY sum(soluong) DESC)\n"
                + "                                                                ORDER BY sum(soluong) DESC)\n"
                + "GROUP BY TenMon ,NgayBan,SoLuong";
        return select(sql, nam, nam2, nam3, nam4);
    }

    public List< Top3> getTop3_7ngay() {
        String sql = "SELECT TenMon,SOLUONG,CONVERT(NVARCHAR ,NgayBan,105)\n"
                + "from CTHoaDon JOIN HoaDon ON HoaDon.MaHD = CTHoaDon.MaHD\n"
                + "WHERE GhiChu like N'Đ%' and NgayBan  BETWEEN GETDATE()-7 AND GETDATE() AND TenMon =( SELECT top 1  TenMon\n"
                + "                                                                from CTHoaDon JOIN HoaDon ON HoaDon.MaHD = CTHoaDon.MaHD\n"
                + "                                                                WHERE  NgayBan BETWEEN  GETDATE()-7  AND GETDATE()  \n"
                + "                                                                GROUP BY TenMon\n"
                + "                                                                having sum(soluong) <(SELECT top 1 sum(soluong)\n"
                + "                                                                from CTHoaDon JOIN HoaDon ON HoaDon.MaHD = CTHoaDon.MaHD\n"
                + "                                                                 WHERE  NgayBan BETWEEN  GETDATE()-7  AND GETDATE() \n"
                + "                                                                GROUP BY TenMon\n"
                + "                                                                having sum(soluong) < (SELECT top 1  sum(soluong)\n"
                + "                                                                from CTHoaDon JOIN HoaDon ON HoaDon.MaHD = CTHoaDon.MaHD\n"
                + "                                                                WHERE  NgayBan BETWEEN  GETDATE()-7  AND GETDATE()  \n"
                + "						                                        GROUP BY TenMon\n"
                + "						                                        ORDER BY sum(soluong) DESC)\n"
                + "                                                                ORDER BY sum(soluong) DESC)\n"
                + "                                                                ORDER BY sum(soluong) DESC)\n"
                + "GROUP BY TenMon ,NgayBan,SoLuong";
        return select(sql);
    }

    public List< Top3> DlTop3() {
        String sql = "  SELECT top 1  TenMon,SUM(SOLUONG),GETDATE() \n"
                + "  from CTHoaDon JOIN HoaDon ON HoaDon.MaHD = CTHoaDon.MaHD\n"
                + "  WHERE  NgayBan BETWEEN  GETDATE()-7  AND GETDATE()  \n"
                + "  GROUP BY TenMon\n"
                + "  having sum(soluong) <(SELECT top 1 sum(soluong)\n"
                + "                       from CTHoaDon JOIN HoaDon ON HoaDon.MaHD = CTHoaDon.MaHD\n"
                + "                       WHERE  NgayBan BETWEEN  GETDATE()-7  AND GETDATE() \n"
                + "                       GROUP BY TenMon\n"
                + "                       having sum(soluong) < (SELECT top 1  sum(soluong)\n"
                + "                                              from CTHoaDon JOIN HoaDon ON HoaDon.MaHD = CTHoaDon.MaHD\n"
                + "                                              WHERE  NgayBan BETWEEN  GETDATE()-7  AND GETDATE() \n"
                + "						                     GROUP BY TenMon\n"
                + "						                     ORDER BY sum(soluong) DESC)\n"
                + "                      ORDER BY sum(soluong) DESC)\n"
                + "   ORDER BY sum(soluong) DESC";
        return select(sql);
    }

    public List< Top3> DlTkTop3(String nam, String nam2, String nam3, String nam4) {
        String sql = "SELECT top 1  TenMon,SUM(SOLUONG),GETDATE() \n"
                + "  from CTHoaDon JOIN HoaDon ON HoaDon.MaHD = CTHoaDon.MaHD\n"
                + "  WHERE  NgayBan =? \n"
                + "  GROUP BY TenMon\n"
                + "  having sum(soluong) <(SELECT top 1 sum(soluong)\n"
                + "                       from CTHoaDon JOIN HoaDon ON HoaDon.MaHD = CTHoaDon.MaHD\n"
                + "                       WHERE  NgayBan =? \n"
                + "                       GROUP BY TenMon\n"
                + "                       having sum(soluong) < (SELECT top 1  sum(soluong)\n"
                + "                                              from CTHoaDon JOIN HoaDon ON HoaDon.MaHD = CTHoaDon.MaHD\n"
                + "                                              WHERE  NgayBan =?\n"
                + "						                     GROUP BY TenMon\n"
                + "						                     ORDER BY sum(soluong) DESC)\n"
                + "                      ORDER BY sum(soluong) DESC)\n"
                + "   ORDER BY sum(soluong) DESC";
        return select(sql, nam, nam2, nam3);
    }
}
