/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlbancoffe;

import model.DoanhThuNam;
import model.DoanhThuThang;
import model.Top1;
import model.Top2;
import model.Top3;
import DAO.Dat.ThongKeDaoNam;
import DAO.Dat.ThongKeDaoThang;
import DAO.Dat.Top1Dao;
import DAO.Dat.Top2Dao;
import DAO.Dat.Top3Dao;
import Helper.ChuyenDoi;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author trong
 */
public class thongKe extends javax.swing.JPanel {
    
    ThongKeDaoNam tkNam = new ThongKeDaoNam();
    ThongKeDaoThang tkThag = new ThongKeDaoThang();
    Top1Dao top1 = new Top1Dao();
    Top2Dao top2 = new Top2Dao();
    Top3Dao top3 = new Top3Dao();
    
    public thongKe() {
        initComponents();
        setNam(jpNam);
        loadNam();
        loadNam2();
        setTop_7ngay(jpTop4);
        DtNam();
        DlTop1();
        DlTop2();
        DlTop3();
        
    }
    
    public void setNam(JPanel jpnItem) {
        List<DoanhThuNam> listItem = tkNam.select();
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        if (listItem != null) {
            for (DoanhThuNam item : listItem) {
                dataset.addValue(item.getDoanhThu(), "Doanh thu", item.getNam());
            }
        }
        JFreeChart barChart = ChartFactory.createLineChart(
                "Biểu đồ thống kê doanh thu theo năm".toUpperCase(),
                "Thời gian(Năm)", "Doanh thu (VND)",
                dataset, PlotOrientation.VERTICAL, false, true, true);
        
        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(jpnItem.getWidth(), 350));
        
        jpnItem.removeAll();
        jpnItem.setLayout(new CardLayout());
        jpnItem.add(chartPanel);
        jpnItem.validate();
        jpnItem.repaint();
    }
    
    public void loadNam() {
        try {
            DefaultComboBoxModel model = (DefaultComboBoxModel) cboNam.getModel(); //kết nối model với cbo
            model.removeAllElements();   //xóa toàn bộ item của cbo
            List<DoanhThuNam> lN = tkNam.HienNam();
            for (DoanhThuNam hd : lN) {
                model.addElement(hd.getNam());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void loadNam2() {
        try {
            DefaultComboBoxModel model = (DefaultComboBoxModel) cboNam2.getModel(); //kết nối model với cbo
            model.removeAllElements();   //xóa toàn bộ item của cbo
            List<DoanhThuNam> lN = tkNam.HienNam();
            for (DoanhThuNam hd : lN) {
                model.addElement(hd.getNam());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jpTop4 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblNam = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblThang = new javax.swing.JTable();
        cboNam2 = new javax.swing.JComboBox<>();
        jPanel7 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lbTop1 = new javax.swing.JLabel();
        lbTop2 = new javax.swing.JLabel();
        lbTop3 = new javax.swing.JLabel();
        dateNgay2 = new com.toedter.calendar.JDateChooser();
        jButton2 = new javax.swing.JButton();
        lbTieuDe = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        dateNgay = new com.toedter.calendar.JDateChooser();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        cboNam = new javax.swing.JComboBox<>();
        jpThang = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jpNam = new javax.swing.JPanel();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton1.setText("OK");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jpTop4.setBackground(new java.awt.Color(204, 204, 204));
        jpTop4.setLayout(new java.awt.BorderLayout());

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setMaximumSize(new java.awt.Dimension(931, 494));
        jPanel4.setMinimumSize(new java.awt.Dimension(931, 494));
        jPanel4.setPreferredSize(new java.awt.Dimension(931, 494));
        jPanel4.setLayout(new java.awt.GridLayout(1, 0));

        jTabbedPane1.setMinimumSize(new java.awt.Dimension(931, 494));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        tblNam.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        tblNam.setForeground(new java.awt.Color(153, 153, 153));
        tblNam.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Stt", "Năm", "Doanh thu"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblNam.setFillsViewportHeight(true);
        tblNam.setMinimumSize(new java.awt.Dimension(45, 10));
        tblNam.setRequestFocusEnabled(false);
        tblNam.setRowHeight(30);
        jScrollPane1.setViewportView(tblNam);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 902, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Năm", jPanel5);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        tblThang.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        tblThang.setForeground(new java.awt.Color(102, 102, 102));
        tblThang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tháng", "Doanh thu"
            }
        ));
        tblThang.setFillsViewportHeight(true);
        tblThang.setRowHeight(30);
        jScrollPane2.setViewportView(tblThang);

        cboNam2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        cboNam2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboNam2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboNam2ItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(cboNam2, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 902, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cboNam2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 386, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Tháng", jPanel6);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("Top 1:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel2.setText("Top 2:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel4.setText("Top 3:");

        lbTop1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        lbTop2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        lbTop3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        dateNgay2.setDateFormatString("dd-MM-yyyy");

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jButton2.setText("OK");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        lbTieuDe.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lbTieuDe.setText("TÓP 3 SẢN PHẨM BÁN NHIỀU NHẤT TRONG 7 NGÀY GẦN NHẤT");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel5.setText("Chọn ngày");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(281, 281, 281)
                .addComponent(jLabel5)
                .addGap(27, 27, 27)
                .addComponent(dateNgay2, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(0, 52, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2)
                                .addComponent(jLabel1)))
                        .addGap(72, 72, 72)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbTop2, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbTop3, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbTop1, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lbTieuDe))
                .addGap(193, 193, 193))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel5)
                    .addComponent(dateNgay2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(53, 53, 53)
                .addComponent(lbTieuDe)
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lbTop1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(58, 58, 58)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel2)
                    .addComponent(lbTop2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(62, 62, 62)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                    .addComponent(lbTop3, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Top 3", jPanel7);

        jPanel4.add(jTabbedPane1);

        dateNgay.setDateFormatString("dd/MM/yyyy");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(92, 92, 92)
                        .addComponent(dateNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(17, Short.MAX_VALUE)
                        .addComponent(jpTop4, javax.swing.GroupLayout.PREFERRED_SIZE, 1159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 931, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 13, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(dateNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jpTop4, javax.swing.GroupLayout.PREFERRED_SIZE, 458, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Năm:");

        cboNam.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        cboNam.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboNamItemStateChanged(evt);
            }
        });
        cboNam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cboNamMouseClicked(evt);
            }
        });
        cboNam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboNamActionPerformed(evt);
            }
        });

        jpThang.setBackground(new java.awt.Color(204, 204, 255));
        jpThang.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jpThang.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cboNam, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(682, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpThang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboNam, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpThang, javax.swing.GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.setBorder(new javax.swing.border.MatteBorder(null));
        jPanel3.setLayout(new java.awt.BorderLayout());

        jpNam.setBackground(new java.awt.Color(255, 204, 204));
        jpNam.setLayout(new java.awt.GridLayout(1, 0));
        jPanel3.add(jpNam, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cboNamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboNamMouseClicked
        //  setChart2(jpThang);
    }//GEN-LAST:event_cboNamMouseClicked

    private void cboNamItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboNamItemStateChanged
        setThang(jpThang);
    }//GEN-LAST:event_cboNamItemStateChanged

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        setTop(jpTop4);
        if (evt.getClickCount() == 2) {
            setTop_7ngay(jpTop4);
        }
    }//GEN-LAST:event_jButton1MouseClicked

    private void cboNamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboNamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboNamActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void cboNam2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboNam2ItemStateChanged
        DtThang();
    }//GEN-LAST:event_cboNam2ItemStateChanged

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        DlTop1_TheoNgay();
        DlTop2_TheoNgay();
        DlTop3_TheoNgay();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String date = sdf.format(dateNgay2.getDate());
        lbTieuDe.setText("Tóp 3 sản phẩm bán chạy nhất trong ngày " + date);
    }//GEN-LAST:event_jButton2ActionPerformed
    public void setThang(JPanel jpnItem) {
        String nam = (String) cboNam.getSelectedItem();
        List<DoanhThuThang> listItem = tkThag.Thang(nam);
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        if (listItem != null) {
            for (DoanhThuThang item : listItem) {
                dataset.addValue(item.getDoanhThu(), "Doanh thu", item.getThang());
            }
        }
        JFreeChart barChart = ChartFactory.createLineChart(
                "Biểu đồ thống kê doanh thu các tháng".toUpperCase(),
                "Thời gian (Tháng)", "Doanh thu(vnđ)",
                dataset, PlotOrientation.VERTICAL, false, true, true);
        
        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(jpnItem.getWidth(), 350));
        
        jpnItem.removeAll();
        jpnItem.setLayout(new CardLayout());
        jpnItem.add(chartPanel);
        jpnItem.validate();
        jpnItem.repaint();
    }
    
    public void setTop(JPanel jpnItem) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(dateNgay.getDate());
        
        List<Top1> listItem = top1.TkTop1(date, date);
        
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        if (listItem != null) {
            for (Top1 item : listItem) {
                dataset.addValue(item.getSl(), "Top1" + item.getTenMon(), item.getNgay());
            }
        }
        List<Top2> listItem2 = top2.TkTop2(date, date, date);
        for (Top2 item : listItem2) {
            dataset.addValue(item.getSl(), "Top2" + item.getTenMon(), item.getNgay());
        }
        
        List<Top3> listItem3 = top3.TkTop3(date, date, date, date);
        
        for (Top3 item : listItem3) {
            dataset.addValue(item.getSl(), "Top3" + item.getTenMon(), item.getNgay());
        }
        
        JFreeChart barChart = ChartFactory.createLineChart(
                "Biểu đồ thống kê doanh thu tóp 3 sản phẩm bán chạy".toUpperCase(),
                "Thời gian (Ngày)", "Sản phẩm",
                dataset, PlotOrientation.VERTICAL, true, true, true);
        
        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(jpnItem.getWidth(), 350));
        
        jpnItem.removeAll();
        jpnItem.setLayout(new CardLayout());
        jpnItem.add(chartPanel);
        jpnItem.validate();
        jpnItem.repaint();
    }
    
    public void setTop_7ngay(JPanel jpnItem) {
        List<Top1> listItem = top1.getTop1_7ngay();
        
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        if (listItem != null) {
            for (Top1 item : listItem) {
                dataset.addValue(item.getSl(), "Top1" + item.getTenMon(), item.getNgay());
            }
        }
        List<Top2> listItem2 = top2.getTop2_7ngay();
        for (Top2 item : listItem2) {
            dataset.addValue(item.getSl(), "Top2" + item.getTenMon(), item.getNgay());
        }
        
        List<Top3> listItem3 = top3.getTop3_7ngay();
        
        for (Top3 item : listItem3) {
            dataset.addValue(item.getSl(), "Top3" + item.getTenMon(), item.getNgay());
        }
        
        JFreeChart barChart = ChartFactory.createLineChart(
                "Biểu đồ thống kê doanh thu tóp 3 sản phẩm bán chạy trong 7 ngày gần nhất".toUpperCase(),
                "Thời gian (Ngày)", "Sản phẩm",
                dataset, PlotOrientation.VERTICAL, true, true, true);
        
        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(jpnItem.getWidth(), 350));
        
        jpnItem.removeAll();
        jpnItem.setLayout(new CardLayout());
        jpnItem.add(chartPanel);
        jpnItem.validate();
        jpnItem.repaint();
    }
    
    public void DtNam() {
        DefaultTableModel model = (DefaultTableModel) tblNam.getModel();
        model.setRowCount(0);
        tblNam.getColumnModel().getColumn(0).setMinWidth(100);
        tblNam.getColumnModel().getColumn(0).setMaxWidth(100);
        
        try {
            int i = 1;
            List<DoanhThuNam> hd1 = tkNam.select();
            for (DoanhThuNam dt : hd1) {
                Object[] row = {
                    i++,
                    dt.getNam(),
                    ChuyenDoi.chuyenDoiTien(dt.getDoanhThu()) + " VND"};
                model.addRow(row);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
        
    }
    
    public void DtThang() {
        DefaultTableModel model = (DefaultTableModel) tblThang.getModel();
        model.setRowCount(0);
        try {
            String nam = (String) cboNam2.getSelectedItem();
            List<DoanhThuThang> hd1 = tkThag.Thang(nam);
            for (DoanhThuThang dt : hd1) {
                Object[] row = {
                    dt.getThang(),
                    ChuyenDoi.chuyenDoiTien(dt.getDoanhThu()) + " VND"};
                model.addRow(row);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
        
    }
    
    public void DlTop1() {
        List<Top1> top = top1.DlTop1();
        
        for (int i = 0; i < top.size(); i++) {
            Top1 a = top.get(0);
            lbTop1.setText(a.getTenMon() + " " + String.valueOf(a.getSl()) + " Sản phẩm");
        }
        
        if (top.size() == 2) {
            for (int i = 0; i < top.size(); i++) {
                Top1 a = top.get(1);
                lbTop2.setText(a.getTenMon() + " " + String.valueOf(a.getSl()) + " Sản phẩm");
            }
            List<Top2> top2a = top2.DlTop2();
            for (int i = 0; i < top2a.size(); i++) {
                Top2 b = top2a.get(0);
                lbTop3.setText(b.getTenMon() + " " + String.valueOf(b.getSl()) + " Sản phẩm");
            }
        }
        if (top.size() == 3) {
            for (int i = 0; i < top.size(); i++) {
                Top1 a = top.get(0);
                lbTop1.setText(a.getTenMon() + " " + String.valueOf(a.getSl()) + " Sản phẩm");
            }
            for (int i = 0; i < top.size(); i++) {
                Top1 a = top.get(1);
                lbTop2.setText(a.getTenMon() + " " + String.valueOf(a.getSl()) + " Sản phẩm");
            }
            for (int i = 0; i < top.size(); i++) {
                Top1 a = top.get(2);
                lbTop3.setText(a.getTenMon() + " " + String.valueOf(a.getSl()) + " Sản phẩm");
            }
        }
    }
    
    public void DlTop2() {
        List<Top1> top = top1.DlTop1();
        List<Top2> top2a = top2.DlTop2();
        if (top.size() == 1) {
            
            for (int i = 0; i < top2a.size(); i++) {
                Top2 a = top2a.get(i);
                lbTop2.setText(a.getTenMon() + " " + String.valueOf(a.getSl() + " Sản phẩm"));
            }
            
            if (top2a.size() == 2) {
                for (int i = 0; i < top2a.size(); i++) {
                    Top2 a = top2a.get(0);
                    lbTop2.setText(a.getTenMon() + " " + String.valueOf(a.getSl() + " Sản phẩm"));
                }
                for (int i = 0; i < top2a.size(); i++) {
                    Top2 a = top2a.get(1);
                    lbTop3.setText(a.getTenMon() + " " + String.valueOf(a.getSl() + " Sản phẩm"));
                }
            }
        }
        
    }
    
    public void DlTop3() {
        List<Top1> top = top1.DlTop1();
        List<Top2> top2a = top2.DlTop2();
        if (top.size() == 1) {
            if (top2a.size() == 1) {
                List<Top3> a = top3.DlTop3();
                for (Top3 top31 : a) {
                    lbTop3.setText(top31.getTenMon() + " " + String.valueOf(top31.getSl()) + " Sản phẩm");
                }
            }
        }
    }
    
    public void DlTop1_TheoNgay() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(dateNgay2.getDate());
        List<Top1> top = top1.DlTkTop1(date, date);
        for (int i = 0; i < top.size(); i++) {
            Top1 a = top.get(0);
            lbTop1.setText(a.getTenMon() + " " + String.valueOf(a.getSl()) + " Sản phẩm");
        }
        
        if (top.size() == 2) {
            for (int i = 0; i < top.size(); i++) {
                Top1 a = top.get(1);
                lbTop2.setText(a.getTenMon() + " " + String.valueOf(a.getSl()) + " Sản phẩm");
            }
            List<Top2> top2a = top2.DlTkTop1(date, date, date);
            for (int i = 0; i < top2a.size(); i++) {
                Top2 b = top2a.get(0);
                lbTop3.setText(b.getTenMon() + " " + String.valueOf(b.getSl()) + " Sản phẩm");
            }
        }
        if (top.size() == 3) {
            for (int i = 0; i < top.size(); i++) {
                Top1 a = top.get(0);
                lbTop1.setText(a.getTenMon() + " " + String.valueOf(a.getSl()) + " Sản phẩm");
            }
            for (int i = 0; i < top.size(); i++) {
                Top1 a = top.get(1);
                lbTop2.setText(a.getTenMon() + " " + String.valueOf(a.getSl()) + " Sản phẩm");
            }
            for (int i = 0; i < top.size(); i++) {
                Top1 a = top.get(2);
                lbTop3.setText(a.getTenMon() + " " + String.valueOf(a.getSl()) + " Sản phẩm");
            }
        }
        if (top.size() > 3) {
            for (int i = 0; i < top.size(); i++) {
                Top1 a = top.get(0);
                lbTop1.setText(a.getTenMon() + " " + String.valueOf(a.getSl()) + " Sản phẩm");
            }
            for (int i = 0; i < top.size(); i++) {
                Top1 a = top.get(1);
                lbTop2.setText(a.getTenMon() + " " + String.valueOf(a.getSl()) + " Sản phẩm");
            }
            for (int i = 0; i < top.size(); i++) {
                Top1 a = top.get(2);
                lbTop3.setText(a.getTenMon() + " " + String.valueOf(a.getSl()) + " Sản phẩm");
            }
            JOptionPane.showMessageDialog(this, "Có nhiều hơn 3 sản phẩm bán chạy có cùng số lượng bán ra");
        }
    }
    
    public void DlTop2_TheoNgay() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(dateNgay2.getDate());
        List<Top1> top = top1.DlTkTop1(date, date);
        List<Top2> top2a = top2.DlTkTop1(date, date, date);
        if (top.size() == 1) {
            
            for (int i = 0; i < top2a.size(); i++) {
                Top2 a = top2a.get(i);
                lbTop2.setText(a.getTenMon() + " " + String.valueOf(a.getSl() + " Sản phẩm"));
            }
            
            if (top2a.size() == 2) {
                for (int i = 0; i < top2a.size(); i++) {
                    Top2 a = top2a.get(0);
                    lbTop2.setText(a.getTenMon() + " " + String.valueOf(a.getSl() + " Sản phẩm"));
                }
                for (int i = 0; i < top2a.size(); i++) {
                    Top2 a = top2a.get(1);
                    lbTop3.setText(a.getTenMon() + " " + String.valueOf(a.getSl() + " Sản phẩm"));
                }
            }
        }
        
    }
    
    public void DlTop3_TheoNgay() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(dateNgay2.getDate());
        List<Top1> top = top1.DlTkTop1(date, date);
        List<Top2> top2a = top2.DlTkTop1(date, date, date);
        if (top.size() == 1) {
            if (top2a.size() == 1) {
                List<Top3> a = top3.DlTkTop3(date, date, date, date);
                for (Top3 top31 : a) {
                    lbTop3.setText(top31.getTenMon() + " " + String.valueOf(top31.getSl()) + " Sản phẩm");
                }
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cboNam;
    private javax.swing.JComboBox<String> cboNam2;
    private com.toedter.calendar.JDateChooser dateNgay;
    private com.toedter.calendar.JDateChooser dateNgay2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel jpNam;
    private javax.swing.JPanel jpThang;
    private javax.swing.JPanel jpTop4;
    private javax.swing.JLabel lbTieuDe;
    private javax.swing.JLabel lbTop1;
    private javax.swing.JLabel lbTop2;
    private javax.swing.JLabel lbTop3;
    private javax.swing.JTable tblNam;
    private javax.swing.JTable tblThang;
    // End of variables declaration//GEN-END:variables
}
