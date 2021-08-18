/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlbancoffe;

import DAO.BanHangDao;
import DAO.CTHoaDonBHDao;
import DAO.HoaDonBHDao;
import DAO.SuKienDao;
import HanhDong.hanhDongBanHang;
import Helper.ChuyenDoi;
import Helper.HanhDong;
import Helper.shareHelper;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.table.DefaultTableModel;
import miniForm.ChuyenBanMni;
import miniForm.GopBanMni;
import miniForm.HoaDonTT;
import miniForm.MonAnBH;
import miniForm.TachBanMni;
import model.Ban;
import model.CTHoaDon;
import model.DanhMuc;
import model.HoaDonBH;
import model.KhuVuc;
import model.MonAn;
import model.SuKien;

/**
 *
 * @author CHIEN
 */
public class BanHang extends javax.swing.JPanel {

    /**
     * Creates new form BanHang
     */
    DefaultComboBoxModel comBoMode;
    DefaultComboBoxModel comBoDanhMuc;
    DefaultTableModel modeTableBH;
    DefaultComboBoxModel comboSuKien;

    BanHangDao BHDao = new BanHangDao();
    SuKienDao SKDAO = new SuKienDao();
    HoaDonBHDao HDDAO = new HoaDonBHDao();
    CTHoaDonBHDao CTHDDAO = new CTHoaDonBHDao();

    List<Ban> lstBan = new ArrayList<>();
    List<MonAn> lstMonAn = new ArrayList<>();

    private double tongTien = 0;
    private double tongTienTT = 0;
//    

    public BanHang() {
        initComponents();
        init();
        HanhDong.MaHD = null;
        lbMaHD.setText(null);
        lbTongTien.setText(null);
        lbThanhToan.setText(null);
    }

    void init() {
        SKDAO.updateSukienAn();
        khoitaoCompomen();
        modeTableBH = (DefaultTableModel) tblBanHang.getModel();
        modeTableBH.setRowCount(0);
        //
        lbTendangNhap.setText(shareHelper.User.getHoTen());
        //load dữ liệu vào các combo
        loadDataToComBoSuKien();
        loadDataToComBoKV();//khu vực bàn
        loadDataToComBoDM();
        //lấy danh sách bàn và món ăn
        loadDataToListBan();//ds bàn
        hanhDongBanHang.lstBan = lstBan;
        loadDataToListMonAn();
        //
        LoadBanToPanel();//tải bàn lên panel
        HienMon();
        //

    }

    //khởi tạo các compomen
    public void khoitaoCompomen() {
        hanhDongBanHang.lbTenDangNhap = this.lbTendangNhap;
        hanhDongBanHang.lbTenBan = this.lbTenBan;
        hanhDongBanHang.lbGiamGia = this.lbGiamGia;
        hanhDongBanHang.lbThanhToan = this.lbThanhToan;
        hanhDongBanHang.lbMaHD = this.lbMaHD;
        hanhDongBanHang.LBTongTien = this.lbTongTien;
        hanhDongBanHang.pnBan = this.pnBan;
        hanhDongBanHang.pnMonAn = this.pnMonAn;
        hanhDongBanHang.cbxKhuVuc = this.cbxKhuVuc;
        hanhDongBanHang.cbxDanhMuc = this.cbxDanhMuc;
        hanhDongBanHang.cbxKhuyenMai = this.cbxKhuyenMai;
        hanhDongBanHang.tblBanHang = this.tblBanHang;
    }

    //load combo sự kiện
    public void loadDataToComBoSuKien() {
        //LÀM LẠI
        comboSuKien = (DefaultComboBoxModel) cbxKhuyenMai.getModel();
        SKDAO.LoadDataToComBo(comboSuKien);
    }

    //load comboban
    public void loadDataToComBoKV() {
        comBoMode = (DefaultComboBoxModel) cbxKhuVuc.getModel();
        BHDao.loadDataToComBoKhuVuc(comBoMode);
    }

    //load combo danh mục
    public void loadDataToComBoDM() {
        comBoDanhMuc = (DefaultComboBoxModel) cbxDanhMuc.getModel();
        BHDao.LoadDataToComBoDanhMuc(comBoDanhMuc);
    }

    //tải danh sách bàn
    public void loadDataToListBan() {
        lstBan.removeAll(lstBan);
        lstBan = BHDao.selectListBan();
    }
    //tải danh sách món

    public void loadDataToListMonAn() {
        lstMonAn.removeAll(lstMonAn);
        lstMonAn = BHDao.selectListMonAn();
    }

    //hiện bàn
    GridBagConstraints gbcBan = new GridBagConstraints();

    public void LoadBanToPanel() {
        gbcBan.insets = new Insets(0, 0, 0, 0);
        int x = 0, y = 0;
        pnBan.removeAll();
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
                    pnBan.add(taoBan(ban), gbcBan);
                }
            }
        }
        pnBan.validate();
        pnBan.repaint();
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
            KhuVuc kv = (KhuVuc) comBoMode.getElementAt(cbxKhuVuc.getSelectedIndex());;
            if (!ban.isTrangThai()) {
                lbMaHD.setText(null);
                lbTongTien.setText(null);
                lbThanhToan.setText(null);
                modeTableBH.setRowCount(0);//
                HanhDong.MaHD = null;//
                cbxKhuyenMai.setSelectedIndex(0);//
                int chon = JOptionPane.showConfirmDialog(this, "Bạn muốn ngồi bàn này",
                        "Xác nhận đặt bàn", JOptionPane.YES_NO_OPTION);
                if (chon == JOptionPane.YES_OPTION) {
                    BHDao.updateTrangThaiNgoiBan(getBanBH().getMaBan(), 1);
                    loadDataToListBan();
                    LoadBanToPanel();
                    //tạo hóa đơn tạm thời
                    taoHoaDonTamThoi();
                    loadDaTaToTableCTHoaDon();

                }
            } else {
                suKienHoaDon();
                loadDaTaToTableCTHoaDon();
                setTextTongTienThanhToan();
            }
            System.out.println(HanhDong.MaHD);
        });
        return btBan;
    }

    //xử lý sự kiện hóa đơn
    public void suKienHoaDon() {
        KhuVuc kvc = (KhuVuc) comBoMode.getElementAt(cbxKhuVuc.getSelectedIndex());
        if (kvc != null) {
            Ban banChon = BHDao.getBan(lbTenBan.getText(), kvc.getMaKhuVuc());
            HoaDonBH hoaDonChon = HDDAO.getHoaDon(banChon.getMaBan());
            HanhDong.MaHD = hoaDonChon.getMaHD();

            lbMaHD.setText(hoaDonChon.getMaHD());
//            lbTongTien.setText(String.valueOf(hoaDonChon.getTongTien()));
//            lbThanhToan.setText(String.valueOf(hoaDonChon.getTongTienThanhToan()));
            lbGiamGia.setText(String.valueOf(hoaDonChon.getUuDai()));
        }
    }

    //sự kiện load data lên table ctHoaDon tạm thời
    public void loadDaTaToTableCTHoaDon() {
        CTHDDAO.loadDaTaToTableCTHD(HanhDong.MaHD, modeTableBH);
        lbTongTien.setText(ChuyenDoi.chuyenDoiTien(HanhDong.TongTien));
        lbMaHD.setText(HanhDong.MaHD);
    }

    //lấy bàn
    public Ban getBanBH() {
        KhuVuc kv = (KhuVuc) comBoMode.getElementAt(cbxKhuVuc.getSelectedIndex());
        return BHDao.getBan(lbTenBan.getText(), kv.getMaKhuVuc());
    }

    //tạo hóa đơn tạm thời
    public void taoHoaDonTamThoi() {
        System.out.println(HDDAO.getSoHD());
        KhuVuc kv = (KhuVuc) comBoMode.getElementAt(cbxKhuVuc.getSelectedIndex());
        SuKien sk = (SuKien) comboSuKien.getElementAt(cbxKhuyenMai.getSelectedIndex());

        HoaDonBH HDTamThoi = new HoaDonBH();
        HanhDong.MaHD = "HD0" + HDDAO.getSoHD();
        //tao mã hóa đơn
        HDTamThoi.setMaHD("HD0" + HDDAO.getSoHD());
        //lấy mã bàn
        HDTamThoi.setMaBan(BHDao.getBan(lbTenBan.getText(), kv.getMaKhuVuc()).getMaBan());
        //lấy tên bàn
        HDTamThoi.setTenBan(lbTenBan.getText());
        //lấy tên tài khoản
        HDTamThoi.setTenTaiKhoan(shareHelper.User.getTenTaiKhoan());
        //tổng tiền trên 1 hóa đơn chưa có km
        HDTamThoi.setTongTien(0);
        //lấy mã sự kiện
        HDTamThoi.setMaSuKien(sk.getMaSuKien());
        //lấy ưu đãi
        HDTamThoi.setUuDai(sk.getUuDai());
        //lấy tổng tiền thanh toán sau khi có km
        HDTamThoi.setTongTienThanhToan(0);
        //ghi chú là chưa thanh toán
        HDTamThoi.setGhiChu("Chưa Thanh Toán");

        HDDAO.insertHoaDon(HDTamThoi);

    }
    //hien mon
    GridBagConstraints gbc = new GridBagConstraints();

    public void HienMon() {
        HanhDong.LBTongTien = lbTongTien;
        pnMonAn.removeAll();
        DanhMuc dm = (DanhMuc) comBoDanhMuc.getElementAt(cbxDanhMuc.getSelectedIndex());
        gbc.insets = new Insets(20, 20, 20, 20);
        int x = 0, y = 0;
        for (int i = 0; i < lstMonAn.size(); i++) {
            MonAn MA = lstMonAn.get(i);
            gbc.gridx = x;
            gbc.gridy = y;

            if (dm.getTenDanhMuc().equalsIgnoreCase("Tất cả các món")) {
                ++x;
                if (x % 4 == 0) {
                    x = 0;
                    ++y;
                }
                MonAnBH pnMonBH = new MonAnBH(MA, modeTableBH);
                pnMonAn.add(pnMonBH, gbc);
            } else {

                if (dm.getMaDanhMuc().equals(MA.getMaDanhMuc())) {
                    ++x;
                    if (x % 4 == 0) {
                        x = 0;
                        ++y;
                    }
                    MonAnBH pnMonBH = new MonAnBH(MA, modeTableBH);
                    pnMonAn.add(pnMonBH, gbc);
                }
            }

            pnMonAn.validate();
            pnMonAn.repaint();
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

        jPanel1 = new javax.swing.JPanel();
        cbxKhuVuc = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblBanHang = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        cbxKhuyenMai = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        lbGiamGia = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lbThanhToan = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        btnTachGopBan = new javax.swing.JButton();
        btnHuyBan = new javax.swing.JButton();
        btnChuyenBan = new javax.swing.JButton();
        btnThanhToan = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        lbMaHD = new javax.swing.JLabel();
        lbGiamGia1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lbTongTien = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        lbTenBan = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        pnBan = new javax.swing.JPanel();
        cbxDanhMuc = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        pnMonAn = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lbTendangNhap = new javax.swing.JLabel();

        setMaximumSize(new java.awt.Dimension(1600, 900));
        setMinimumSize(new java.awt.Dimension(1600, 900));
        setLayout(new java.awt.GridLayout(1, 0));

        jPanel1.setBackground(new java.awt.Color(107, 70, 52));
        jPanel1.setFocusable(false);
        jPanel1.setMaximumSize(new java.awt.Dimension(1600, 900));
        jPanel1.setMinimumSize(new java.awt.Dimension(1600, 900));

        cbxKhuVuc.setBackground(new java.awt.Color(204, 204, 255));
        cbxKhuVuc.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        cbxKhuVuc.setForeground(new java.awt.Color(107, 70, 52));
        cbxKhuVuc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Khu vực", "tầng 1", "tầng 2", "sân thượng" }));
        cbxKhuVuc.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxKhuVucItemStateChanged(evt);
            }
        });
        cbxKhuVuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxKhuVucActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(212, 181, 152));

        tblBanHang.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tblBanHang.setForeground(new java.awt.Color(107, 70, 52));
        tblBanHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, "aaaa",  new Integer(2), "aaaa", "aaaa", null},
                {null, null, "aaaa",  new Integer(2), "aaaa", "aaaa", null},
                {null, null, "aaaa",  new Integer(2), "aaaa", "aaaa", null},
                {null, null, "aaaa",  new Integer(2), "aaaa", null, null}
            },
            new String [] {
                "Stt", "Mã", "Tên món", "SL", "Đơn giá", "Thành tiền", "Xóa"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblBanHang.setFillsViewportHeight(true);
        tblBanHang.setRowHeight(30);
        tblBanHang.setSelectionBackground(new java.awt.Color(107, 70, 52));
        tblBanHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBanHangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblBanHang);
        if (tblBanHang.getColumnModel().getColumnCount() > 0) {
            tblBanHang.getColumnModel().getColumn(0).setResizable(false);
            tblBanHang.getColumnModel().getColumn(6).setResizable(false);
        }
        tblBanHang.getColumnModel().getColumn(0).setPreferredWidth(15);
        tblBanHang.getColumnModel().getColumn(1).setPreferredWidth(5);
        tblBanHang.getColumnModel().getColumn(2).setPreferredWidth(100);
        tblBanHang.getColumnModel().getColumn(3).setPreferredWidth(10);
        tblBanHang.getColumnModel().getColumn(6).setPreferredWidth(15);

        jButton1.setBackground(new java.awt.Color(107, 70, 52));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Cập nhập");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        cbxKhuyenMai.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cbxKhuyenMai.setForeground(new java.awt.Color(107, 70, 52));
        cbxKhuyenMai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Khuyến mại", "70%", "40%", "30%" }));
        cbxKhuyenMai.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxKhuyenMaiItemStateChanged(evt);
            }
        });

        jLabel2.setBackground(new java.awt.Color(107, 70, 52));
        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(107, 70, 52));
        jLabel2.setText("Giảm giá :");

        lbGiamGia.setBackground(new java.awt.Color(107, 70, 52));
        lbGiamGia.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lbGiamGia.setForeground(new java.awt.Color(107, 70, 52));
        lbGiamGia.setText("jLabel3");

        jLabel4.setBackground(new java.awt.Color(107, 70, 52));
        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(107, 70, 52));
        jLabel4.setText("Thanh toán:");

        lbThanhToan.setBackground(new java.awt.Color(107, 70, 52));
        lbThanhToan.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lbThanhToan.setForeground(new java.awt.Color(107, 70, 52));
        lbThanhToan.setText("....................................");

        jPanel5.setBackground(new java.awt.Color(212, 181, 152));
        jPanel5.setLayout(new java.awt.GridLayout(2, 2, 20, 20));

        btnTachGopBan.setBackground(new java.awt.Color(107, 70, 52));
        btnTachGopBan.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnTachGopBan.setForeground(new java.awt.Color(255, 255, 255));
        btnTachGopBan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/tach.png"))); // NOI18N
        btnTachGopBan.setText("Tách/Gộp bàn");
        btnTachGopBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTachGopBanActionPerformed(evt);
            }
        });
        jPanel5.add(btnTachGopBan);

        btnHuyBan.setBackground(new java.awt.Color(204, 0, 0));
        btnHuyBan.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnHuyBan.setForeground(new java.awt.Color(255, 255, 255));
        btnHuyBan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/huy.png"))); // NOI18N
        btnHuyBan.setText("Hủy bàn");
        btnHuyBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyBanActionPerformed(evt);
            }
        });
        jPanel5.add(btnHuyBan);

        btnChuyenBan.setBackground(new java.awt.Color(107, 70, 52));
        btnChuyenBan.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnChuyenBan.setForeground(new java.awt.Color(255, 255, 255));
        btnChuyenBan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/gop.png"))); // NOI18N
        btnChuyenBan.setText("Chuyển bàn");
        btnChuyenBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChuyenBanActionPerformed(evt);
            }
        });
        jPanel5.add(btnChuyenBan);

        btnThanhToan.setBackground(new java.awt.Color(126, 199, 47));
        btnThanhToan.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnThanhToan.setForeground(new java.awt.Color(255, 255, 255));
        btnThanhToan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/muaHang.png"))); // NOI18N
        btnThanhToan.setText("Thanh toán");
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });
        jPanel5.add(btnThanhToan);

        jLabel6.setBackground(new java.awt.Color(107, 70, 52));
        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(107, 70, 52));
        jLabel6.setText("Mã HD:");

        lbMaHD.setBackground(new java.awt.Color(107, 70, 52));
        lbMaHD.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lbMaHD.setForeground(new java.awt.Color(107, 70, 52));
        lbMaHD.setText(".........................");

        lbGiamGia1.setBackground(new java.awt.Color(107, 70, 52));
        lbGiamGia1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lbGiamGia1.setForeground(new java.awt.Color(107, 70, 52));
        lbGiamGia1.setText("%");

        jLabel8.setBackground(new java.awt.Color(107, 70, 52));
        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(107, 70, 52));
        jLabel8.setText("VND");

        jLabel9.setBackground(new java.awt.Color(107, 70, 52));
        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(107, 70, 52));
        jLabel9.setText("Tổng tiền:");

        lbTongTien.setBackground(new java.awt.Color(107, 70, 52));
        lbTongTien.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lbTongTien.setForeground(new java.awt.Color(107, 70, 52));
        lbTongTien.setText("...................................");
        lbTongTien.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                lbTongTienPropertyChange(evt);
            }
        });

        jLabel11.setBackground(new java.awt.Color(107, 70, 52));
        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(107, 70, 52));
        jLabel11.setText("VND");

        lbTenBan.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lbTenBan.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(cbxKhuyenMai, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lbMaHD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addComponent(lbGiamGia)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(lbGiamGia1))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addComponent(lbThanhToan)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jLabel8)))
                                    .addGap(0, 0, Short.MAX_VALUE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lbTongTien)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(66, 66, 66))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lbTenBan, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbTenBan, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(cbxKhuyenMai, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lbGiamGia)
                                    .addComponent(lbGiamGia1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lbThanhToan)
                                    .addComponent(jLabel8)))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lbTongTien)
                                .addComponent(jLabel11)))
                        .addGap(5, 5, 5)
                        .addComponent(lbMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addGap(5, 5, 5)
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(212, 181, 152));
        jPanel3.setMaximumSize(new java.awt.Dimension(1130, 337));
        jPanel3.setMinimumSize(new java.awt.Dimension(1130, 337));
        jPanel3.setPreferredSize(new java.awt.Dimension(1130, 337));
        jPanel3.setLayout(new java.awt.GridLayout(1, 0));

        pnBan.setBackground(new java.awt.Color(212, 181, 152));
        pnBan.setLayout(new java.awt.GridBagLayout());
        jScrollPane2.setViewportView(pnBan);

        jPanel3.add(jScrollPane2);

        cbxDanhMuc.setBackground(new java.awt.Color(204, 204, 255));
        cbxDanhMuc.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        cbxDanhMuc.setForeground(new java.awt.Color(107, 70, 52));
        cbxDanhMuc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Danh mục", "coffee", "đồ uống", "đồ gọi thêm", "trà sữa" }));
        cbxDanhMuc.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxDanhMucItemStateChanged(evt);
            }
        });
        cbxDanhMuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxDanhMucActionPerformed(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(212, 181, 152));
        jPanel4.setMaximumSize(new java.awt.Dimension(1130, 450));
        jPanel4.setMinimumSize(new java.awt.Dimension(1130, 450));
        jPanel4.setPreferredSize(new java.awt.Dimension(1130, 450));
        jPanel4.setLayout(new java.awt.GridLayout(1, 0));

        pnMonAn.setBackground(new java.awt.Color(212, 181, 152));
        pnMonAn.setLayout(new java.awt.GridBagLayout());
        jScrollPane3.setViewportView(pnMonAn);

        jPanel4.add(jScrollPane3);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Nhân viên bán hàng:");

        lbTendangNhap.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbTendangNhap.setForeground(new java.awt.Color(255, 255, 255));
        lbTendangNhap.setText("ho ten");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(cbxDanhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(cbxKhuVuc, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbTendangNhap)
                        .addGap(23, 23, 23)))
                .addGap(10, 10, 10)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel1)
                    .addComponent(lbTendangNhap)
                    .addComponent(cbxKhuVuc, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbxDanhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        add(jPanel1);
    }// </editor-fold>//GEN-END:initComponents

    private void cbxKhuVucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxKhuVucActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxKhuVucActionPerformed
    //  cập nhập table

    public void capNhap() {
        modeTableBH = (DefaultTableModel) tblBanHang.getModel();
        for (int i = 0; i < modeTableBH.getRowCount(); i++) {
            boolean xoa = (boolean) modeTableBH.getValueAt(i, 6);
            Integer Mamon = (Integer) modeTableBH.getValueAt(i, 1);
            System.out.println(Mamon);
            if (xoa) {
                try {
                    CTHDDAO.deleteCTHoaDon(lbMaHD.getText(), Mamon);
                    System.out.println("Xóa ok");
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Xóa lỗi");
                }
            }
        }
        loadDaTaToTableCTHoaDon();
    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        capNhap();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void cbxDanhMucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxDanhMucActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxDanhMucActionPerformed

    private void btnTachGopBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTachGopBanActionPerformed
        // TODO add your handling code here:
        khoitaoCompomen();
        KhuVuc kv = (KhuVuc) comBoMode.getElementAt(cbxKhuVuc.getSelectedIndex());
        hanhDongBanHang.kvDC = kv;
        if (lbTenBan.getText().length() == 0) {
            JOptionPane.showMessageDialog(this, "Bạn hãy chọn bàn cần TÁCH/GỘP");
        } else {
            Ban BanGop = getBanBH();
            if (BanGop.isTrangThai()) {
                Object LuaChon[] = {"Gộp bàn", "Tách bàn"};
                int Kq = JOptionPane.showOptionDialog(this, "Mời Bạn Chọn",
                        "Tách/Gộp Bàn",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        LuaChon, LuaChon[0]);
                if (Kq == 0) {

                    SuKien sk = (SuKien) comboSuKien.getElementAt(cbxKhuyenMai.getSelectedIndex());
                    HoaDonBH HD = HDDAO.getHoaDon(BanGop.getMaBan());
                    GopBanMni GB = new GopBanMni(HD, sk, BanGop);
                    GB.setVisible(true);
                } else {
                    //code tách bàn
                    SuKien sk = (SuKien) comboSuKien.getElementAt(cbxKhuyenMai.getSelectedIndex());
                    HoaDonBH HD = HDDAO.getHoaDon(BanGop.getMaBan());
                    TachBanMni TB = new TachBanMni(HD, sk);
                    TB.setVisible(true);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Hãy chọn bàn đang có người ngồi");
            }

        }

    }//GEN-LAST:event_btnTachGopBanActionPerformed

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        khoitaoCompomen();
        KhuVuc kv = (KhuVuc) comBoMode.getElementAt(cbxKhuVuc.getSelectedIndex());
        hanhDongBanHang.kvDC = kv;
        if (lbMaHD.getText() != null) {
            SuKien sk = (SuKien) comboSuKien.getElementAt(cbxKhuyenMai.getSelectedIndex());
            HoaDonTT hd = new HoaDonTT(HanhDong.MaHD, kv, getBanBH(), sk);
            hd.setVisible(true);

        } else {
            JOptionPane.showMessageDialog(this, "Mời chọn bàn thanh toán");
        }
    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void cbxKhuVucItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxKhuVucItemStateChanged
        // TODO add your handling code here:
        lstBan = hanhDongBanHang.lstBan;
        LoadBanToPanel();
    }//GEN-LAST:event_cbxKhuVucItemStateChanged

    private void cbxDanhMucItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxDanhMucItemStateChanged
        // TODO add your handling code here:
        HienMon();
    }//GEN-LAST:event_cbxDanhMucItemStateChanged

    private void btnHuyBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyBanActionPerformed
        // TODO add your handling code here:

        KhuVuc kv = (KhuVuc) comBoMode.getElementAt(cbxKhuVuc.getSelectedIndex());
        if (lbTenBan.getText().length() == 0) {
            JOptionPane.showMessageDialog(this, "Thông báo", "Chọn bàn cần hủy", JOptionPane.QUESTION_MESSAGE);
        } else {
            int chon = JOptionPane.showConfirmDialog(this, "Xác nhận", "Bạn có chắc hủy bàn", JOptionPane.YES_NO_OPTION);
            if (chon == JOptionPane.YES_OPTION) {
                try {
                    HDDAO.deleteHoaDonTamThoi(HanhDong.MaHD);
                    BHDao.updateTrangThaiNgoiBan(getBanBH().getMaBan(), 0);
//                    loadDataToListBan();
//                    LoadBanToPanel();

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Không thể xóa vì còn món");
                }
                khoitaoCompomen();
                hanhDongBanHang.kvDC = kv;
                hanhDongBanHang.LoadBanToPanel();
            }
        }
    }//GEN-LAST:event_btnHuyBanActionPerformed
    public void setTextTongTienThanhToan() {
        SuKien sk = (SuKien) comboSuKien.getElementAt(cbxKhuyenMai.getSelectedIndex());
        if (sk != null) {
            HanhDong.TongTienThanhToan = HanhDong.TongTien - HanhDong.TongTien * (sk.getUuDai() / 100);
            lbGiamGia.setText(String.valueOf(sk.getUuDai()));
            lbThanhToan.setText(ChuyenDoi.chuyenDoiTien(HanhDong.TongTienThanhToan));
        }
    }
    private void cbxKhuyenMaiItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxKhuyenMaiItemStateChanged
        // TODO add your handling code here:     
        setTextTongTienThanhToan();
    }//GEN-LAST:event_cbxKhuyenMaiItemStateChanged

    private void lbTongTienPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_lbTongTienPropertyChange
        // TODO add your handling code here:
        setTextTongTienThanhToan();
    }//GEN-LAST:event_lbTongTienPropertyChange

    private void tblBanHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBanHangMouseClicked
        // TODO add your handling code here:
        modeTableBH = (DefaultTableModel) tblBanHang.getModel();
        Integer index = tblBanHang.getSelectedRow();
        if (evt.getClickCount() == 2) {
            String sl = JOptionPane.showInputDialog(tblBanHang, "Nhập Số Lượng", modeTableBH.getValueAt(index, 3));
            try {
                Integer.parseInt(sl);
                //modeTableBH.setValueAt(sl, index, 3);
                Integer maMon = (Integer) modeTableBH.getValueAt(index, 1);
                CTHDDAO.updateSLCTHoaDon(HanhDong.MaHD, maMon, sl);
                loadDaTaToTableCTHoaDon();

            } catch (Exception e) {
                JOptionPane.showMessageDialog(tblBanHang, "Bạn phải nhập số nguyên");
            }
        }
    }//GEN-LAST:event_tblBanHangMouseClicked

    private void btnChuyenBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChuyenBanActionPerformed
        khoitaoCompomen();
        KhuVuc kv = (KhuVuc) comBoMode.getElementAt(cbxKhuVuc.getSelectedIndex());
        hanhDongBanHang.kvDC = kv;
// TODO add your handling code here:
        if (lbTenBan.getText().length() == 0) {
            JOptionPane.showMessageDialog(this, "Bạn hãy chọn bàn cần chuyển");
        } else {
            Ban bn = getBanBH();
            if (bn.isTrangThai()) {
                ChuyenBanMni cb = new ChuyenBanMni(lbMaHD.getText(), bn.getMaBan());
                cb.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Bạn hãy chọn bàn đã có người ngồi");
            }
        }
    }//GEN-LAST:event_btnChuyenBanActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChuyenBan;
    private javax.swing.JButton btnHuyBan;
    private javax.swing.JButton btnTachGopBan;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JComboBox<String> cbxDanhMuc;
    private javax.swing.JComboBox<String> cbxKhuVuc;
    private javax.swing.JComboBox<String> cbxKhuyenMai;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lbGiamGia;
    private javax.swing.JLabel lbGiamGia1;
    private javax.swing.JLabel lbMaHD;
    private javax.swing.JLabel lbTenBan;
    private javax.swing.JLabel lbTendangNhap;
    private javax.swing.JLabel lbThanhToan;
    private javax.swing.JLabel lbTongTien;
    private javax.swing.JPanel pnBan;
    private javax.swing.JPanel pnMonAn;
    private javax.swing.JTable tblBanHang;
    // End of variables declaration//GEN-END:variables

}
