/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HanhDong.AN;

import DAO.An.BanDaoAn;
import DAO.An.KhuVucDaoAn;
import Helper.HanhDong;
import Helper.shareHelper;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import model.Ban;
import model.KhuVuc;

/**
 *
 * @author CHIEN
 */
public class HanhDongAn {

    public static DefaultComboBoxModel comBoMode;
    public static List<Ban> lstBan = new ArrayList<>();
    public static KhuVucDaoAn KVDAOAN = new KhuVucDaoAn();
    public static BanDaoAn BANDAOAN = new BanDaoAn();
    //
    
    //
    public static JComboBox cbxKhuVuc;
    public static JPanel pnBanQL;
    public static JLabel lbTenBan;
    public static JButton btnThemKV;
    public static JButton btnXoaKV;
    public static JButton btnThemBan;
    //

    //load comboban
    public static void loadDataToComBoKV() {
        comBoMode = (DefaultComboBoxModel) cbxKhuVuc.getModel();
        KVDAOAN.loadDataToComBoKhuVuc(comBoMode);
    }

    //tải danh sách bàn
    public static void loadDataToListBan() {
        lstBan.removeAll(lstBan);
        lstBan = BANDAOAN.selectListBan();
    }
    //hiện bàn
    public static KhuVuc kv = new KhuVuc();
    public static GridBagConstraints gbcBan = new GridBagConstraints();

    public static void LoadBanToPanel() {
        
        List<Ban> listBan = BANDAOAN.selectListBan();
        gbcBan.insets = new Insets(10, 10, 10, 10);
        int x = 0, y = 0;
        pnBanQL.removeAll();
        //
        //KhuVuc kv = (KhuVuc) comBoMode.getElementAt(cbxKhuVuc.getSelectedIndex());;
        //
        for (int i = 0; i < listBan.size(); i++) {
            Ban ban = listBan.get(i);

            Integer makv = ban.getMaKhuVuc();

            if (kv != null) {
                if (makv.equals(kv.getMaKhuVuc())) {

                    gbcBan.gridx = x;
                    gbcBan.gridy = y;
                    ++x;
                    if (x % 14 == 0) {
                        x = 0;
                        ++y;
                    }
                    pnBanQL.add(taoBan(ban), gbcBan);
                }
            }
        }
        pnBanQL.validate();
        pnBanQL.repaint();
    }

    //tạo button ban
    public static JButton taoBan(Ban ban) {
        JButton btBan = new JButton();
        btBan.setText(ban.getTenBan());
        btBan.setPreferredSize(new Dimension(100, 100));
        btBan.setIcon(shareHelper.getIcon());
        btBan.setBackground(Color.white);
        btBan.setVerticalTextPosition(JLabel.BOTTOM);
        btBan.setHorizontalTextPosition(JLabel.CENTER);
        if (ban.isTrangThai()) {
            btBan.setBackground(Color.red);
        } else {
            btBan.setBackground(Color.white);
        }
        //xử lý sự kiện
        btBan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                lbTenBan.setText(btBan.getText());
                //KhuVuc kv = (KhuVuc) comBoMode.getElementAt(cbxKhuVuc.getSelectedIndex());
                //HanhDongAn.kv = kv;
                if (!ban.isTrangThai()) {
                    Object LuaChon[] = {"Xóa bàn", "Sửa bàn"};
                    int Kq = JOptionPane.showOptionDialog(null, "Mời Bạn Chọn",
                            "Thao tác",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            LuaChon, LuaChon[0]);
                    if (Kq == 0) {
                        //code xóa
                        Integer chon = JOptionPane.showConfirmDialog(null, "Bạn muốn xóa",
                                "Xác nhận", JOptionPane.YES_NO_OPTION);
                        if (chon == JOptionPane.YES_OPTION) {
                            BANDAOAN.updateAn(ban.getMaBan());
                            JOptionPane.showMessageDialog(null, "Xóa thành công");
                            HanhDongAn.loadDataToListBan();
                            HanhDongAn.LoadBanToPanel();
                        }
                    } else if (Kq == 1) {
                        String tenBan = JOptionPane.showInputDialog(null, "Nhập tên bàn", ban.getTenBan());
                        //code sửa bàn
                        if (!ban.getTenBan().equalsIgnoreCase(tenBan)) {
                            if (BANDAOAN.checkTenBan(tenBan)) {
                                BANDAOAN.updateTenBan(ban.getMaBan(), tenBan);
                                JOptionPane.showMessageDialog(null, "Sửa thành công");
                                HanhDongAn.loadDataToComBoKV();
                                HanhDongAn.loadDataToListBan();
                                HanhDongAn.LoadBanToPanel();
                            } else {
                                JOptionPane.showMessageDialog(null, "Trùng tên bàn");
                                
                            }
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Bàn đang có người ngồi không thể thao tác");
                }
            }
        });
        return btBan;
    }

}
