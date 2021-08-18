/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlbancoffe;

import DAO.Hung.TaiKhoanDaoHung;

import Helper.Utils;
import static Helper.Utils.myConnection;
import Helper.Hung.checkHung;
import java.awt.Color;
import static java.awt.Color.pink;
import static java.awt.Color.white;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.TaiKhoanModeHung;

/**
 *
 * @author CHIEN
 */
public class TaiKhoan extends javax.swing.JPanel {

    /**
     * Creates new form TaiKhoan
     */
    TaiKhoanDaoHung tkdao = new TaiKhoanDaoHung();
    DefaultTableModel model;
    int index = 0;
    List<model.TaiKhoanModeHung> listTaiKhoan = new ArrayList<>();

    public TaiKhoan() {

        initComponents();

        loadTable();
    }

    public TaiKhoanModeHung getModel() {
        TaiKhoanModeHung model = new TaiKhoanModeHung();
        model.setTenTaiKhoan(txtTenDN.getText());
        model.setHoTen(txtHoten.getText());
        model.setMatKhau(new String(txtPass.getPassword()));  //chuyển char[] thành String
        model.setEmail(txtEmail.getText());
        if (rdQuanLi.isSelected()) {

            model.setVaiTro(true);
        }
        if (rdNhanVien.isSelected()) {
            model.setVaiTro(false);
        }

        model.setTrangThai("1");
        model.setAn(true);
        return model;
    }

    public TaiKhoanModeHung getModel1() {
        TaiKhoanModeHung model = new TaiKhoanModeHung();
        model.setTenTaiKhoan(txtTenDN.getText());
        model.setHoTen(txtHoten.getText());
        model.setMatKhau(new String(txtPass.getPassword()));  //chuyển char[] thành String
        model.setEmail(txtEmail.getText());
        if (rdQuanLi.isSelected()) {

            model.setVaiTro(true);
        }
        if (rdNhanVien.isSelected()) {
            model.setVaiTro(false);
        }

        if (rdVoHieuHoa.isSelected()) {
            model.setTrangThai("0");
        } else {
            model.setTrangThai("1");
        }
        model.setAn(true);
        return model;
    }

    public void loadTable() {
        String a;
        DefaultTableModel model = (DefaultTableModel) tbTaiKhoan.getModel();
        model.setRowCount(0);
        try {
            int i = 1;
            List<TaiKhoanModeHung> hd1 = TaiKhoanDaoHung.HienThi();
            for (TaiKhoanModeHung hd : hd1) {
                if (hd.isVaiTro() == false) {
                    a = "Nhân Viên";

                } else {
                    a = "Quản lí";
                }

                Object[] row = {
                    i++,
                    hd.getTenTaiKhoan(),
                    hd.getHoTen(),
                    hd.getMatKhau(),
                    hd.getEmail(),
                    a};

                model.addRow(row);
                btXoa.setEnabled(false);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }

    public void loadTable1() {
        String a;
        DefaultTableModel model = (DefaultTableModel) tbTaiKhoan.getModel();
        model.setRowCount(0);
        try {
            int i = 1;
            List<TaiKhoanModeHung> hd1 = TaiKhoanDaoHung.HienThi1();
            for (TaiKhoanModeHung hd : hd1) {
                if (hd.isVaiTro() == false) {
                    a = "Nhân Viên";

                } else {
                    a = "Quản lí";
                }

                Object[] row = {
                    i++,
                    hd.getTenTaiKhoan(),
                    hd.getHoTen(),
                    hd.getMatKhau(),
                    hd.getEmail(),
                    a};

                model.addRow(row);

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }

    public void insert() {
        TaiKhoanModeHung model = getModel();
        try {
            TaiKhoanDaoHung.insert(model);
            JOptionPane.showMessageDialog(this, "Thêm thành công");
            this.loadTable();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Thêm thất bại");
        }
    }

    public void update() {
        TaiKhoanModeHung model = getModel1();
        String confirm = new String(txtPassConfirm.getPassword());
        if (!confirm.equals(model.getMatKhau())) {
            txtPassConfirm.setBackground(pink);
            JOptionPane.showMessageDialog(this, "Xác nhận mật khẩu không đúng!");
            checkmail();
            
        } else {
            try {
                TaiKhoanDaoHung.update(model);     //cập nhật nhân viên theo maNV
                this.loadTable();//điền tt mới vào bảng
                JOptionPane.showMessageDialog(this, "Cập nhật thành công!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtTenDN = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtPass = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();
        txtPassConfirm = new javax.swing.JPasswordField();
        jLabel5 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        btNew = new javax.swing.JButton();
        btEdit = new javax.swing.JButton();
        btAdd = new javax.swing.JButton();
        btFind = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbTaiKhoan = new javax.swing.JTable();
        rdVoHieuHoa = new javax.swing.JCheckBox();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        cbTaiKhoan = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        txtHoten = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        rdQuanLi = new javax.swing.JRadioButton();
        rdNhanVien = new javax.swing.JRadioButton();
        btXoa = new javax.swing.JButton();

        setMaximumSize(new java.awt.Dimension(1600, 900));
        setMinimumSize(new java.awt.Dimension(1600, 900));
        setPreferredSize(new java.awt.Dimension(1600, 900));
        setLayout(new java.awt.GridLayout(1, 0));

        jPanel1.setBackground(new java.awt.Color(212, 181, 152));
        jPanel1.setMaximumSize(new java.awt.Dimension(1600, 900));
        jPanel1.setMinimumSize(new java.awt.Dimension(1600, 900));
        jPanel1.setPreferredSize(new java.awt.Dimension(1600, 900));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Quản lý tài khoản");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Tên đăng nhập:");

        txtTenDN.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtTenDN.setForeground(new java.awt.Color(107, 70, 52));
        txtTenDN.setVerifyInputWhenFocusTarget(false);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Mật khẩu:");

        txtPass.setVerifyInputWhenFocusTarget(false);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Nhập lại mật khẩu:");

        txtPassConfirm.setVerifyInputWhenFocusTarget(false);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Email:");

        txtEmail.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtEmail.setForeground(new java.awt.Color(107, 70, 52));
        txtEmail.setVerifyInputWhenFocusTarget(false);

        jPanel2.setBackground(new java.awt.Color(212, 181, 152));
        jPanel2.setLayout(new java.awt.GridLayout(2, 2, 20, 20));

        btNew.setBackground(new java.awt.Color(50, 191, 190));
        btNew.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btNew.setForeground(new java.awt.Color(255, 255, 255));
        btNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/hienThi.png"))); // NOI18N
        btNew.setText("Làm mới");
        btNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNewActionPerformed(evt);
            }
        });
        jPanel2.add(btNew);

        btEdit.setBackground(new java.awt.Color(253, 138, 79));
        btEdit.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btEdit.setForeground(new java.awt.Color(255, 255, 255));
        btEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/sua.png"))); // NOI18N
        btEdit.setText("Sửa");
        btEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEditActionPerformed(evt);
            }
        });
        jPanel2.add(btEdit);

        btAdd.setBackground(new java.awt.Color(107, 70, 52));
        btAdd.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btAdd.setForeground(new java.awt.Color(255, 255, 255));
        btAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/them.png"))); // NOI18N
        btAdd.setText("Thêm tài khoản");
        btAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAddActionPerformed(evt);
            }
        });
        jPanel2.add(btAdd);

        btFind.setBackground(new java.awt.Color(107, 70, 52));
        btFind.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btFind.setForeground(new java.awt.Color(255, 255, 255));
        btFind.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/timKiem.png"))); // NOI18N
        btFind.setText("Tìm kiếm");
        btFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btFindActionPerformed(evt);
            }
        });
        jPanel2.add(btFind);

        tbTaiKhoan.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tbTaiKhoan.setForeground(new java.awt.Color(107, 70, 52));
        tbTaiKhoan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Tên đăng nhập", "Họ tên", "Mật khẩu", "Email", "Vai trò"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbTaiKhoan.setFillsViewportHeight(true);
        tbTaiKhoan.setRowHeight(35);
        tbTaiKhoan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbTaiKhoanMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbTaiKhoan);

        rdVoHieuHoa.setBackground(new java.awt.Color(107, 70, 52));
        rdVoHieuHoa.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        rdVoHieuHoa.setForeground(new java.awt.Color(255, 255, 255));
        rdVoHieuHoa.setText("Vô hiệu hóa");
        rdVoHieuHoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdVoHieuHoaActionPerformed(evt);
            }
        });

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/myfriend.png"))); // NOI18N

        cbTaiKhoan.setBackground(new java.awt.Color(204, 204, 255));
        cbTaiKhoan.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        cbTaiKhoan.setForeground(new java.awt.Color(107, 70, 52));
        cbTaiKhoan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tài khoản đang hoạt động", "Tài khoản không hoạt động" }));
        cbTaiKhoan.setRequestFocusEnabled(false);
        cbTaiKhoan.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbTaiKhoanItemStateChanged(evt);
            }
        });
        cbTaiKhoan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbTaiKhoanMouseClicked(evt);
            }
        });
        cbTaiKhoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTaiKhoanActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Ho Tên:");

        txtHoten.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtHoten.setForeground(new java.awt.Color(107, 70, 52));
        txtHoten.setVerifyInputWhenFocusTarget(false);
        txtHoten.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHotenActionPerformed(evt);
            }
        });

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/logoCF.png"))); // NOI18N

        buttonGroup1.add(rdQuanLi);
        rdQuanLi.setText("Quản lý");
        rdQuanLi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdQuanLiActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdNhanVien);
        rdNhanVien.setSelected(true);
        rdNhanVien.setText("Nhân viên");

        btXoa.setBackground(new java.awt.Color(255, 102, 102));
        btXoa.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btXoa.setForeground(new java.awt.Color(255, 255, 255));
        btXoa.setText("Xóa nhân viên");
        btXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btXoaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel1)
                        .addGap(81, 81, 81))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(cbTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btXoa))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 488, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtHoten, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtEmail, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPass, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPassConfirm, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTenDN, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(rdVoHieuHoa)
                                .addGap(18, 18, 18)
                                .addComponent(rdQuanLi)
                                .addGap(29, 29, 29)
                                .addComponent(rdNhanVien))
                            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(30, 30, 30)
                        .addComponent(jLabel7))
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 445, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 736, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addGap(73, 73, 73))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtHoten, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTenDN, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPassConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(rdVoHieuHoa)
                                    .addComponent(rdQuanLi)
                                    .addComponent(rdNhanVien))
                                .addGap(18, 18, 18)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cbTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(btXoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(4, 4, 4)))
                                .addComponent(jScrollPane1))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        add(jPanel1);

        getAccessibleContext().setAccessibleName("");
    }// </editor-fold>//GEN-END:initComponents

    private void btNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNewActionPerformed
        // TODO add your handling code here:
        this.txtTenDN.setText("");
        this.txtHoten.setText("");
        this.txtPass.setText("");
        this.txtPassConfirm.setText("");
        this.txtEmail.setText("");
    }//GEN-LAST:event_btNewActionPerformed

    private void btEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEditActionPerformed
        // TODO add your handling code here:
        this.update();
    }//GEN-LAST:event_btEditActionPerformed

    private void btAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAddActionPerformed
        // TODO add your handling code here:

        if (checkHung.checkNullText(txtTenDN)
                && checkHung.checkNullPass(txtPass)
                && checkHung.checkNullPass(txtPassConfirm)
                && checkHung.checkNullText(txtHoten)
                && checkHung.checkEmail(txtEmail)&& checkMa() && checkmail()) {
            if (doiMatKhau()) {
              insert();
            } 
        }
    }//GEN-LAST:event_btAddActionPerformed
     public boolean doiMatKhau() {
         TaiKhoanModeHung model = getModel1();
      String confirm = new String(txtPassConfirm.getPassword());
        if (!confirm.equals(model.getMatKhau())) {
            txtPassConfirm.setBackground(pink);
            JOptionPane.showMessageDialog(this, "Xác nhận mật khẩu không đúng!");
            return false;
        } else {
            return true;
        }
            
    }
    public boolean checkMa() {
        String tenTaiKhoan = txtTenDN.getText();
        try {
            TaiKhoanModeHung tk = tkdao.checkTrungMa(tenTaiKhoan);
            if (tk != null) {
                JOptionPane.showMessageDialog(this, "Đã trùng tên đăng nhập");
                return false;
            }
            if (tk == null) {
                System.out.println("Thêm thành công2");
                return true;
            }

        } catch (Exception e) {
        }
        return true;

    }
    public boolean checkmail() {
        String mail = txtEmail.getText();
        try {
            TaiKhoanModeHung tk = tkdao.checkTrungMa1(mail);
            if (tk != null) {
                JOptionPane.showMessageDialog(this, "Đã trùng Gmail");
                return false;
            }
            if (tk == null) {
                System.out.println("Thêm thành công");
                return true;
            }

        } catch (Exception e) {
        }
        return true;

    }
    public void checkall(){
          if (checkHung.checkNullText(txtTenDN)
                && checkHung.checkNullPass(txtPass)
                && checkHung.checkNullPass(txtPassConfirm)
                && checkHung.checkNullText(txtHoten)
                && checkHung.checkEmail(txtEmail)&& checkMa() && checkmail()) {
            if (doiMatKhau()) {
              
             } 
        }
    }
    public void delete() {
   int hoi = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa?", "HỎi xóa", JOptionPane.YES_NO_OPTION);
        if (hoi != JOptionPane.YES_OPTION) {
            return;
        }
        String tendn = txtTenDN.getText();
        try {
            tkdao.delete(tendn);   //xóa nhân viên theo maNV
            this.loadTable1();//điền tt mới vào bảng
            //  this.clear();       //xóa trắng form và chỉnh lại status
            JOptionPane.showMessageDialog(this, "Xóa thành công!");
        } catch (Exception e) {
        }
        
    }
    
    private void btFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btFindActionPerformed
        // TODO add your handling code here:
        String a;
        String timkiem = JOptionPane.showInputDialog(this, "Tìm kiếm tài khoản");

        DefaultTableModel model = (DefaultTableModel) tbTaiKhoan.getModel();
        model.setRowCount(0);
        try {
            int i = 1;
            List<TaiKhoanModeHung> hd1 = TaiKhoanDaoHung.TimKiemMa(timkiem);
            for (TaiKhoanModeHung hd : hd1) {
                if (hd.isVaiTro() == false) {
                    a = "Nhân Viên";

                } else {
                    a = "Quản lí";
                }
                Object[] row = {
                    i++,
                    hd.getTenTaiKhoan(),
                    hd.getHoTen(),
                    hd.getMatKhau(),
                    hd.getEmail(),
                    a};

                model.addRow(row);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);

        }


    }//GEN-LAST:event_btFindActionPerformed

    private void rdVoHieuHoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdVoHieuHoaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdVoHieuHoaActionPerformed

    private void cbTaiKhoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTaiKhoanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbTaiKhoanActionPerformed

    private void tbTaiKhoanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbTaiKhoanMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            this.index = tbTaiKhoan.getSelectedRow();//lấy vị trí dòng được chọn
            if (this.index >= 0) {

                showDetail(index);

            }
        }

    }//GEN-LAST:event_tbTaiKhoanMouseClicked

    private void txtHotenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHotenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHotenActionPerformed

    private void rdQuanLiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdQuanLiActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_rdQuanLiActionPerformed

    private void cbTaiKhoanItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbTaiKhoanItemStateChanged
        // TODO add your handling code here:

        if (cbTaiKhoan.getSelectedItem().equals("Tài khoản đang hoạt động")) {
            loadTable();

        }
        if (cbTaiKhoan.getSelectedItem().equals("Tài khoản không hoạt động")) {
            loadTable1();
            btXoa.setEnabled(true);
        }

    }//GEN-LAST:event_cbTaiKhoanItemStateChanged

    private void cbTaiKhoanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbTaiKhoanMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_cbTaiKhoanMouseClicked

    private void btXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btXoaActionPerformed
        // TODO add your handling code here:
        delete();
    }//GEN-LAST:event_btXoaActionPerformed
    public void showDetail(int row) {
        txtHoten.setText(tbTaiKhoan.getValueAt(row, 2).toString());
        txtTenDN.setText(tbTaiKhoan.getValueAt(row, 1).toString());
        txtPass.setText(tbTaiKhoan.getValueAt(row, 3).toString());
        txtEmail.setText(tbTaiKhoan.getValueAt(row, 4).toString());
        if (tbTaiKhoan.getValueAt(row, 5).equals("Quản lí")) {
            rdQuanLi.setSelected(true);
        }
        if (tbTaiKhoan.getValueAt(row, 5).equals("Nhân Viên")) {
            rdNhanVien.setSelected(true);
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAdd;
    private javax.swing.JButton btEdit;
    private javax.swing.JButton btFind;
    private javax.swing.JButton btNew;
    private javax.swing.JButton btXoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbTaiKhoan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rdNhanVien;
    private javax.swing.JRadioButton rdQuanLi;
    private javax.swing.JCheckBox rdVoHieuHoa;
    private javax.swing.JTable tbTaiKhoan;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtHoten;
    private javax.swing.JPasswordField txtPass;
    private javax.swing.JPasswordField txtPassConfirm;
    private javax.swing.JTextField txtTenDN;
    // End of variables declaration//GEN-END:variables
}
