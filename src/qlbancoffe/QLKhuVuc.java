/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlbancoffe;

import DAO.An.BanDaoAn;
import DAO.An.KhuVucDaoAn;
import DAO.BanHangDao;
import HanhDong.AN.HanhDongAn;
import HanhDong.hanhDongBanHang;
import Helper.HanhDong;
import Helper.shareHelper;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import miniForm.ThemBan1;
import miniForm.ThemBan2;
import model.Ban;
import model.KhuVuc;

/**
 *
 * @author CHIEN
 */
public class QLKhuVuc extends javax.swing.JPanel {

    DefaultComboBoxModel comBoMode;
    List<Ban> lstBan = new ArrayList<>();
    //BanHangDao BHDao = new BanHangDao();
    KhuVucDaoAn KVDAOAN = new KhuVucDaoAn();
    BanDaoAn BANDAOAN = new BanDaoAn();

    /**
     * Creates new form QLKhuVuc
     */
    public QLKhuVuc() {
        initComponents();
        init();
    }

    void init() {
        comBoMode = (DefaultComboBoxModel) cbxKhuVuc.getModel();
        loadDataToComBoKV();
        loadDataToListBan();
        LoadBanToPanel();
        khoiTaoComponent();

    }

    //khoi tao cac  component
    public void khoiTaoComponent() {
        HanhDongAn.cbxKhuVuc = this.cbxKhuVuc;
        HanhDongAn.pnBanQL = this.pnBanQL;
        HanhDongAn.lbTenBan = this.lbTenBan;
        HanhDongAn.comBoMode = this.comBoMode;
        HanhDongAn.btnThemBan = this.btnThemBan;
        HanhDongAn.btnThemKV = this.btnThemKV;
        HanhDongAn.btnXoaKV = this.btnXoaKV;
    }

    //load comboban
    public void loadDataToComBoKV() {
        comBoMode = (DefaultComboBoxModel) cbxKhuVuc.getModel();
        KVDAOAN.loadDataToComBoKhuVuc(comBoMode);
    }

    //tải danh sách bàn
    public void loadDataToListBan() {
        lstBan.removeAll(lstBan);
        lstBan = BANDAOAN.selectListBan();
    }
    //hiện bàn
    GridBagConstraints gbcBan = new GridBagConstraints();

    public void LoadBanToPanel() {
        loadDataToListBan();
        gbcBan.insets = new Insets(10, 10, 10, 10);
        int x = 0, y = 0;
        pnBanQL.removeAll();
        //
        KhuVuc kv = (KhuVuc) comBoMode.getElementAt(cbxKhuVuc.getSelectedIndex());;
        //
        for (int i = 0; i < lstBan.size(); i++) {
            Ban ban = lstBan.get(i);

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
    public JButton taoBan(Ban ban) {
        
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
        btBan.addActionListener((ae) -> {

            lbTenBan.setText(btBan.getText());
            KhuVuc kv = (KhuVuc) comBoMode.getElementAt(cbxKhuVuc.getSelectedIndex());
            if (!ban.isTrangThai()) {
                Object LuaChon[] = {"Xóa bàn", "Sửa bàn"};
                int Kq = JOptionPane.showOptionDialog(this, "Mời Bạn Chọn",
                        "Thao tác",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        LuaChon, LuaChon[0]);
                if (Kq == 0) {
                    //code xóa
                    Integer chon = JOptionPane.showConfirmDialog(this, "Bạn muốn xóa",
                            "Xác nhận", JOptionPane.YES_NO_OPTION);
                    if (chon == JOptionPane.YES_OPTION) {
                        BANDAOAN.updateAn(ban.getMaBan());
                        JOptionPane.showMessageDialog(this, "Xóa thành công");
                        loadDataToListBan();
                        LoadBanToPanel();
                    }
                } else if (Kq == 1) {
                    String tenBan = JOptionPane.showInputDialog(this, "Nhập tên bàn", ban.getTenBan());
                    //code sửa bàn
                    if (!ban.getTenBan().equalsIgnoreCase(tenBan)) {
                        if (BANDAOAN.checkTenBan(tenBan)) {
                            BANDAOAN.updateTenBan(ban.getMaBan(), tenBan);
                            JOptionPane.showMessageDialog(this, "Sửa thành công");
                            //loadDataToComBoKV();
                            loadDataToListBan();
                            LoadBanToPanel();
                        } else {
                            JOptionPane.showMessageDialog(this, "Trùng tên bàn");

                        }
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Bàn đang có người ngồi không thể thao tác");
            }
            System.out.println(HanhDong.MaHD);
        });
        return btBan;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnKhuVuc = new javax.swing.JPanel();
        cbxKhuVuc = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        pnBanQL = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        btnThemKV = new javax.swing.JButton();
        btnXoaKV = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        btnThemBan = new javax.swing.JButton();
        lbTenBan = new javax.swing.JLabel();

        setMaximumSize(new java.awt.Dimension(1600, 900));
        setMinimumSize(new java.awt.Dimension(1600, 900));
        setName(""); // NOI18N
        setPreferredSize(new java.awt.Dimension(1600, 900));
        setLayout(new java.awt.GridLayout(1, 0));

        pnKhuVuc.setBackground(new java.awt.Color(107, 70, 52));
        pnKhuVuc.setMaximumSize(new java.awt.Dimension(1600, 900));
        pnKhuVuc.setMinimumSize(new java.awt.Dimension(1600, 900));
        pnKhuVuc.setPreferredSize(new java.awt.Dimension(1600, 900));

        cbxKhuVuc.setBackground(new java.awt.Color(204, 204, 255));
        cbxKhuVuc.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        cbxKhuVuc.setForeground(new java.awt.Color(107, 70, 52));
        cbxKhuVuc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Khu vực", "tầng 1", "tầng 2", "sân thượng" }));
        cbxKhuVuc.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxKhuVucItemStateChanged(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(212, 181, 152));
        jPanel2.setLayout(new java.awt.GridLayout(1, 0));

        pnBanQL.setBackground(new java.awt.Color(212, 181, 152));
        pnBanQL.setLayout(new java.awt.GridBagLayout());
        jScrollPane1.setViewportView(pnBanQL);

        jPanel2.add(jScrollPane1);

        jPanel4.setBackground(new java.awt.Color(107, 70, 52));
        jPanel4.setLayout(new java.awt.GridLayout(1, 2, 20, 0));

        btnThemKV.setBackground(new java.awt.Color(126, 199, 47));
        btnThemKV.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnThemKV.setForeground(new java.awt.Color(255, 255, 255));
        btnThemKV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/them.png"))); // NOI18N
        btnThemKV.setText("Thêm khu vực");
        btnThemKV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemKVActionPerformed(evt);
            }
        });
        jPanel4.add(btnThemKV);

        btnXoaKV.setBackground(new java.awt.Color(204, 0, 0));
        btnXoaKV.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnXoaKV.setForeground(new java.awt.Color(255, 255, 255));
        btnXoaKV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/huy.png"))); // NOI18N
        btnXoaKV.setText("Xóa khu vực");
        btnXoaKV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaKVActionPerformed(evt);
            }
        });
        jPanel4.add(btnXoaKV);

        jPanel5.setBackground(new java.awt.Color(107, 70, 52));
        jPanel5.setLayout(new java.awt.GridLayout(1, 3, 25, 20));

        jButton7.setBackground(new java.awt.Color(29, 120, 115));
        jButton7.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/dangNhap.png"))); // NOI18N
        jButton7.setText("Xác nhận");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton7);

        btnThemBan.setBackground(new java.awt.Color(126, 199, 47));
        btnThemBan.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnThemBan.setForeground(new java.awt.Color(255, 255, 255));
        btnThemBan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/them.png"))); // NOI18N
        btnThemBan.setText("Thêm bàn");
        btnThemBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemBanActionPerformed(evt);
            }
        });

        lbTenBan.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbTenBan.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pnKhuVucLayout = new javax.swing.GroupLayout(pnKhuVuc);
        pnKhuVuc.setLayout(pnKhuVucLayout);
        pnKhuVucLayout.setHorizontalGroup(
            pnKhuVucLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnKhuVucLayout.createSequentialGroup()
                .addGroup(pnKhuVucLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnKhuVucLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(pnKhuVucLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnKhuVucLayout.createSequentialGroup()
                                .addComponent(cbxKhuVuc, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(89, 89, 89)
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 443, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(lbTenBan, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 556, Short.MAX_VALUE))
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(pnKhuVucLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnThemBan, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pnKhuVucLayout.setVerticalGroup(
            pnKhuVucLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnKhuVucLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnKhuVucLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxKhuVuc, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbTenBan, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 706, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(pnKhuVucLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThemBan, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        add(pnKhuVuc);
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemKVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemKVActionPerformed
        // TODO add your handling code here:
        String khuVuc = JOptionPane.showInputDialog(this, "Tên khu vực");
        if (khuVuc.length() == 0) {
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập");
        } else {
            if (KVDAOAN.checkTenKV(khuVuc)) {
                try {
                    KVDAOAN.insertKhuVuc(khuVuc);
                    JOptionPane.showMessageDialog(this, "Thêm thành công");
                    loadDataToComBoKV();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Lỗi thêm");
                    e.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Tên khuc vực trùng");
            }
        }

    }//GEN-LAST:event_btnThemKVActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        ManHinhBatDau bt = new ManHinhBatDau();
        GridLayout grl = new GridLayout();
        pnKhuVuc.setLayout(grl);
        pnKhuVuc.removeAll();
        pnKhuVuc.add(bt);
        pnKhuVuc.validate();
        pnKhuVuc.repaint();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void btnThemBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemBanActionPerformed
        // TODO add your handling code here
        khoiTaoComponent();
        KhuVuc kv = (KhuVuc) comBoMode.getElementAt(cbxKhuVuc.getSelectedIndex());
        hanhDongBanHang.kvDC = kv;
        ThemBan1 tb = new ThemBan1();
        tb.setVisible(true);
    }//GEN-LAST:event_btnThemBanActionPerformed

    private void cbxKhuVucItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxKhuVucItemStateChanged
        // TODO add your handling code here:
        KhuVuc Kv = (KhuVuc) comBoMode.getElementAt(cbxKhuVuc.getSelectedIndex());
        HanhDongAn.kv = Kv;
        LoadBanToPanel();
    }//GEN-LAST:event_cbxKhuVucItemStateChanged

    private void btnXoaKVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaKVActionPerformed
        // TODO add your handling code here:
        comBoMode = (DefaultComboBoxModel) cbxKhuVuc.getModel();
        KhuVuc KV = (KhuVuc) comBoMode.getElementAt(cbxKhuVuc.getSelectedIndex());
        if (BANDAOAN.selectListKhucVuc(KV.getMaKhuVuc()).size() > 0) {
            JOptionPane.showMessageDialog(this, "Khu vực đang còn bàn không thể xóa");
        } else {
            int chon = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa " + KV.getTenKhuVuc(),
                    "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (chon == JOptionPane.YES_OPTION) {
                try {
                    KVDAOAN.updateAn(KV.getMaKhuVuc());
                    JOptionPane.showMessageDialog(this, "Xóa thành công");
                    loadDataToComBoKV();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Lỗi xóa");
                    e.printStackTrace();
                }

            }
        }
    }//GEN-LAST:event_btnXoaKVActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnThemBan;
    private javax.swing.JButton btnThemKV;
    private javax.swing.JButton btnXoaKV;
    private javax.swing.JComboBox<String> cbxKhuVuc;
    private javax.swing.JButton jButton7;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbTenBan;
    private javax.swing.JPanel pnBanQL;
    private javax.swing.JPanel pnKhuVuc;
    // End of variables declaration//GEN-END:variables
}
