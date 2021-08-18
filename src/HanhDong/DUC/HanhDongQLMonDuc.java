/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HanhDong.DUC;

import DAO.Duc.DanhMucDaoDuc;
import DAO.Duc.MonAnDucDao;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import miniForm.MniQLMonAn;
import model.DanhMuc;
import model.MonAn;

/**
 *
 * @author CHIEN
 */
public class HanhDongQLMonDuc {

    public static JComboBox cbxDanhMuc;
    //
    public static JButton btnThemDM;
    public static JButton btnXoaDM;
    public static JButton btnThemMon;
    public static JButton btnXacNhan;
    //
    public static JPanel pnMon;
    //
    public static DefaultComboBoxModel comBoDanhMuc;
    public static DanhMucDaoDuc DMDUCDao = new DanhMucDaoDuc();
    public static MonAnDucDao MADUCDao = new MonAnDucDao();
    //
    public static List<MonAn> lstMonAn = new ArrayList<>();
    //
    //load combo danh mục
    public void loadDataToComBoDM() {
        comBoDanhMuc = (DefaultComboBoxModel) cbxDanhMuc.getModel();
        DMDUCDao.LoadDataToComBoDanhMuc(comBoDanhMuc);
    }
    //
    //hien mon
    public static GridBagConstraints gbc = new GridBagConstraints();
    public static DanhMuc dm = new DanhMuc();
    public static void HienMon() {
        pnMon.removeAll();
        lstMonAn = null;
        lstMonAn = MADUCDao.getListMonAn();
       // DanhMuc dm = (DanhMuc) comBoDanhMuc.getElementAt(cbxDanhMuc.getSelectedIndex());
        gbc.insets = new Insets(20, 20, 20, 20);
        int x = 0, y = 0;
        if (dm != null) {
            for (int i = 0; i < lstMonAn.size(); i++) {
                MonAn MA = lstMonAn.get(i);
                gbc.gridx = x;
                gbc.gridy = y;

                if (dm.getTenDanhMuc().equalsIgnoreCase("Tất cả các món")) {
                    ++x;
                    if (x % 5 == 0) {
                        x = 0;
                        ++y;
                    }
                    MniQLMonAn mni = new MniQLMonAn(MA);
                    pnMon.add(mni, gbc);
                } else {

                    if (dm.getMaDanhMuc().equals(MA.getMaDanhMuc())) {
                        ++x;
                        if (x % 4 == 0) {
                            x = 0;
                            ++y;
                        }
                        MniQLMonAn mni = new MniQLMonAn(MA);
                        pnMon.add(mni, gbc);
                    }
                }

                pnMon.validate();
                pnMon.repaint();
            }
        }

    }

}
