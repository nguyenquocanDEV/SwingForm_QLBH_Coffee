/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlbancoffe;

import model.CTHoaDon;
import DAO.Dat.CtHoaDonDao;
import DAO.Dat.HoaDonDao;
import Helper.ChuyenDoi;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author CHIEN
 */
public class HoaDon extends javax.swing.JPanel {

    HoaDonDao hdd = new HoaDonDao();
    CtHoaDonDao cthd = new CtHoaDonDao();
    int index = 0;

    public HoaDon() {
        initComponents();
        loadTable();
        showDetail(0);
        tblCTHoaDon.getColumnModel().getColumn(0).setMinWidth(100);
        tblCTHoaDon.getColumnModel().getColumn(0).setMaxWidth(100);
    }

    public void loadTable() {
        DefaultTableModel model = (DefaultTableModel) tblHoaDon.getModel();
        model.setRowCount(0);
        tblHoaDon.getColumnModel().getColumn(0).setMinWidth(100);
        tblHoaDon.getColumnModel().getColumn(0).setMaxWidth(100);
        try {
            int i = 1;
            List<model.HoaDon> hd1 = hdd.select();
            for (model.HoaDon hd : hd1) {
                Object[] row = {
                    i++,
                    hd.getMaHD(),
                    hd.getNgayBan(),
                    ChuyenDoi.chuyenDoiTien(hd.getTongTien()) + " VND",
                    hd.getGhiChu()};
                model.addRow(row);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtNgayMua = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtTongTien = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtTenTK = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHoaDon = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblCTHoaDon = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        txtTkMa = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        dateNgay = new com.toedter.calendar.JDateChooser();

        setMaximumSize(new java.awt.Dimension(1600, 900));
        setMinimumSize(new java.awt.Dimension(1600, 900));
        setPreferredSize(new java.awt.Dimension(1600, 900));
        setVerifyInputWhenFocusTarget(false);
        setLayout(new java.awt.GridLayout(1, 0));

        jPanel1.setBackground(new java.awt.Color(107, 70, 52));
        jPanel1.setMaximumSize(new java.awt.Dimension(1600, 900));
        jPanel1.setMinimumSize(new java.awt.Dimension(1600, 900));
        jPanel1.setPreferredSize(new java.awt.Dimension(1600, 900));
        jPanel1.setLayout(new java.awt.GridLayout(1, 2));

        jPanel3.setBackground(new java.awt.Color(107, 70, 52));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Hóa đơn");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Ngày mua:");

        txtNgayMua.setEditable(false);
        txtNgayMua.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtNgayMua.setForeground(new java.awt.Color(107, 70, 52));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Tổng tiền:");

        txtTongTien.setEditable(false);
        txtTongTien.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtTongTien.setForeground(new java.awt.Color(107, 70, 52));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Nhân viên bán hàng");

        txtTenTK.setEditable(false);
        txtTenTK.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtTenTK.setForeground(new java.awt.Color(107, 70, 52));
        txtTenTK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenTKActionPerformed(evt);
            }
        });

        tblHoaDon.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        tblHoaDon.setForeground(new java.awt.Color(107, 70, 52));
        tblHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"ss", "ss", "ss", "ss", "ss"},
                {"ss", "ss", "ss", "ss", "ss"},
                {"ss", "ss", "ss", "ss", "ss"},
                {"ss", "ss", "ss", "ss", null}
            },
            new String [] {
                "STT", "Mã HD", "Ngày bán", "Tổng tiền", "Ghi chú"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHoaDon.setFillsViewportHeight(true);
        tblHoaDon.setRowHeight(35);
        tblHoaDon.setSelectionBackground(new java.awt.Color(212, 181, 152));
        tblHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblHoaDon);
        tblHoaDon.getColumnModel().getColumn(0).setPreferredWidth(20);
        tblHoaDon.getColumnModel().getColumn(1).setPreferredWidth(30);
        tblHoaDon.getColumnModel().getColumn(2).setPreferredWidth(100);
        tblHoaDon.getColumnModel().getColumn(3).setPreferredWidth(50);
        if (tblHoaDon.getColumnModel().getColumnCount() > 0) {
            tblHoaDon.getColumnModel().getColumn(0).setResizable(false);
        }

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTongTien)
                                    .addComponent(txtNgayMua, javax.swing.GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE))))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(txtTenTK, javax.swing.GroupLayout.DEFAULT_SIZE, 342, Short.MAX_VALUE))
                        .addGap(100, 100, 100))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(txtNgayMua, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTenTK, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 714, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(jPanel3);

        jPanel2.setBackground(new java.awt.Color(212, 181, 152));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/logo1.png"))); // NOI18N

        tblCTHoaDon.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        tblCTHoaDon.setForeground(new java.awt.Color(107, 70, 52));
        tblCTHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Tên SP", "Số lượng", "Đơn giá", "Thành tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblCTHoaDon.setFillsViewportHeight(true);
        tblCTHoaDon.setRowHeight(35);
        tblCTHoaDon.setSelectionBackground(new java.awt.Color(212, 181, 152));
        jScrollPane2.setViewportView(tblCTHoaDon);
        tblCTHoaDon.getColumnModel().getColumn(0).setPreferredWidth(5);
        tblCTHoaDon.getColumnModel().getColumn(1).setPreferredWidth(50);
        tblCTHoaDon.getColumnModel().getColumn(2).setPreferredWidth(5);
        tblCTHoaDon.getColumnModel().getColumn(3).setPreferredWidth(50);

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/logoCF.png"))); // NOI18N

        jButton2.setBackground(new java.awt.Color(107, 70, 52));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/hienThi.png"))); // NOI18N
        jButton2.setText("Hiển thị tất cả");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(107, 70, 52));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/timKiem.png"))); // NOI18N
        jButton1.setText("Tìm hóa đơn");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Mã hóa đơn:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Chọn ngày:");

        dateNgay.setDateFormatString("dd/MM/yyyy");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 479, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel8))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtTkMa, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
                                    .addComponent(dateNgay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton2)))
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(102, 102, 102))
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTkMa, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(17, 17, 17)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel8)
                            .addComponent(dateNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(11, 11, 11)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel1.add(jPanel2);

        add(jPanel1);
    }// </editor-fold>//GEN-END:initComponents

    private void txtTenTKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenTKActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenTKActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        if (txtTkMa.getText().equals("")) {
            timKiemNgay();
        } else {
            timKiemMa();
        }
        txtTkMa.setText("");
        DefaultTableModel model = (DefaultTableModel) tblCTHoaDon.getModel();
        model.setRowCount(0);

    }//GEN-LAST:event_jButton1ActionPerformed
    public void timKiemNgay() {
        DefaultTableModel model = (DefaultTableModel) tblHoaDon.getModel();
        model.setRowCount(0);
        try {
            int i = 1;
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String date = sdf.format(dateNgay.getDate());
            List<model.HoaDon> hd1 = hdd.TimKiemNgay(date);
            for (model.HoaDon hd : hd1) {
                Object[] row = {
                    i++,
                    hd.getMaHD(),
                    hd.getNgayBan(),
                    hd.getTongTien(),
                    hd.getGhiChu()};
                model.addRow(row);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }

    public void timKiemMa() {
        DefaultTableModel model = (DefaultTableModel) tblHoaDon.getModel();
        model.setRowCount(0);
        try {
            int i = 1;
            List<model.HoaDon> hd1 = hdd.TimKiemMa(txtTkMa.getText());
            for (model.HoaDon hd : hd1) {
                Object[] row = {
                    i++,
                    hd.getMaHD(),
                    hd.getNgayBan(),
                    ChuyenDoi.chuyenDoiTien(hd.getTongTien()) + " VND",
                    hd.getGhiChu()};
                model.addRow(row);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }
    private void tblHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseClicked
        if (evt.getClickCount() == 1) {
            this.index = tblHoaDon.getSelectedRow();
            if (this.index >= 0) {
                showDetail(index);
                loadTable2();
                loadTxt();
            }

        }
    }//GEN-LAST:event_tblHoaDonMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        loadTable();
        DefaultTableModel model = (DefaultTableModel) tblCTHoaDon.getModel();
        model.setRowCount(0);
    }//GEN-LAST:event_jButton2ActionPerformed

    public void loadTable2() {
        DefaultTableModel model = (DefaultTableModel) tblCTHoaDon.getModel();
        model.setRowCount(0);
        tblCTHoaDon.getColumnModel().getColumn(0).setMinWidth(100);
        tblCTHoaDon.getColumnModel().getColumn(0).setMaxWidth(100);
        try {
            int i = 1;
            List<CTHoaDon> hd1 = (List<CTHoaDon>) cthd.findById(tblHoaDon.getValueAt(index, 1).toString());
            for (CTHoaDon hd : hd1) {
                Object[] row = {
                    i++,
                    hd.getTenMon(),
                    hd.getSoLuong(),
                    ChuyenDoi.chuyenDoiTien(hd.getDonGia()) + " VND",
                    ChuyenDoi.chuyenDoiTien(hd.getThanhTien()) + " VND"};
                model.addRow(row);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }

    public void loadTxt() {
        try {
            int i = 1;
            List<model.HoaDon> hd1 = (List<model.HoaDon>) hdd.findById(tblHoaDon.getValueAt(index, 1).toString());
            for (model.HoaDon hd : hd1) {
                txtTenTK.setText(hd.getTenTaiKhoan());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }

    public void showDetail(int row) {
        txtNgayMua.setText(tblHoaDon.getValueAt(row, 2).toString());
        txtTongTien.setText(tblHoaDon.getValueAt(row, 3).toString());
        loadTxt();
        tblHoaDon.setRowSelectionInterval(index, index);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser dateNgay;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblCTHoaDon;
    private javax.swing.JTable tblHoaDon;
    private javax.swing.JTextField txtNgayMua;
    private javax.swing.JTextField txtTenTK;
    private javax.swing.JTextField txtTkMa;
    private javax.swing.JTextField txtTongTien;
    // End of variables declaration//GEN-END:variables
}
