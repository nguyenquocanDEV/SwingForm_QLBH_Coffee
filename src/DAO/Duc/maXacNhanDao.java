/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.Duc;

import Helper.jdbcHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.MaXacNhan;
import model.TaiKhoanMode;

/**
 *
 * @author ducmc
 */
public class maXacNhanDao {

    private MaXacNhan realFromReSultset(ResultSet rs) throws SQLException {
        MaXacNhan model = new MaXacNhan();
        model.setNgayTao(rs.getString("TGTAO"));
        model.setNgayKt(rs.getString("TGXOA"));
        model.setMaXacNan(rs.getString("MAXN"));
        return model;

    }

    public List<MaXacNhan> select(String sql, Object... args) {
        List<MaXacNhan> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = jdbcHelper.executeQuery(sql, args);
                while (rs.next()) {
                    list.add(realFromReSultset(rs));
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (Exception e) {
        }
        return list;
    }

    public void insert(MaXacNhan emiu) {
        String sql = "INSERT INTO MAXACNHAN (MAXN,TGTAO) VALUES (?,?) ";
        jdbcHelper.executeUpdate(sql,
                emiu.getMaXacNan(),
                emiu.getNgayTao()
        );
    }

    public MaXacNhan findByMaXacNhan(String maXacNhan) {
        String sql = "SELECT * FROM MAXACNHAN\n"
                + "where MAXN = ?";
        List<MaXacNhan> list = select(sql, maXacNhan);
        return list.size() > 0 ? list.get(0) : null;
    }

}
