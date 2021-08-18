/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HanhDong;

import DAO.BanHangDao;
import DAO.CTHoaDonBHDao;
import DAO.HoaDonBHDao;
import DAO.SuKienDao;
import Helper.ChuyenDoi;
import Helper.HanhDong;
import Helper.shareHelper;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import miniForm.MonAnBH;
import model.Ban;
import model.DanhMuc;
import model.HoaDonBH;
import model.KhuVuc;
import model.MonAn;
import model.SuKien;

/**
 *
 * @author CHIEN
 */
public class hanhDongBanHang {

    public static String MaHD;

    public static double TongTien;

    public static double TongTienThanhToan;
    //khai báo các compomen   
    public static JLabel lbTenDangNhap;

    public static JLabel lbTenBan;

    public static JLabel lbGiamGia;

    public static JLabel lbThanhToan;

    public static JLabel lbMaHD;

    public static JLabel LBTongTien;

    public static JPanel pnBan;

    public static JPanel pnMonAn;

    public static JComboBox cbxKhuVuc;

    public static JComboBox cbxDanhMuc;

    public static JComboBox cbxKhuyenMai;

    public static JTable tblBanHang;

    //khai báo các mode cần sd
    public static DefaultTableModel ModeTable;
    public static DefaultComboBoxModel comBoMode;
    public static DefaultComboBoxModel comBoDanhMuc;
    public static DefaultComboBoxModel comboSuKien;

    //khai báo Dao
    public static BanHangDao BHDao = new BanHangDao();
    public static SuKienDao SKDAO = new SuKienDao();
    public static HoaDonBHDao HDDAO = new HoaDonBHDao();
    public static CTHoaDonBHDao CTHDDAO = new CTHoaDonBHDao();
    //khai báo list cần dùng
    public static List<Ban> lstBan = new ArrayList<>();
    static List<MonAn> lstMonAn = new ArrayList<>();

    //load data to combo
    //load combo sự kiện
    public static void loadDataToComBoSuKien() {
        //LÀM LẠI
        comboSuKien = (DefaultComboBoxModel) cbxKhuyenMai.getModel();
        SKDAO.LoadDataToComBo(comboSuKien);
    }

    //load comboban
    public static void loadDataToComBoKV() {
        comBoMode = (DefaultComboBoxModel) cbxKhuVuc.getModel();
        BHDao.loadDataToComBoKhuVuc(comBoMode);
    }

    //load combo danh mục
    public static void loadDataToComBoDM() {
        comBoDanhMuc = (DefaultComboBoxModel) cbxDanhMuc.getModel();
        BHDao.LoadDataToComBoDanhMuc(comBoDanhMuc);
    }

    //tải danh sách bàn
    public static void loadDataToListBan() {
        lstBan.removeAll(lstBan);
        lstBan = BHDao.selectListBan();
    }
    //tải danh sách món

    public static void loadDataToListMonAn() {
        lstMonAn.removeAll(lstMonAn);
        lstMonAn = BHDao.selectListMonAn();
    }

    ///---------------BÀN--------------------
    
    public static KhuVuc kvDC = new KhuVuc();
    public static void LoadBanToPanel() {
        GridBagConstraints gbcBan = new GridBagConstraints();
        gbcBan.insets = new Insets(0, 0, 0, 0);
        int x = 0, y = 0;
        pnBan.removeAll();
        //
        loadDataToListBan();
        comBoMode = (DefaultComboBoxModel) cbxDanhMuc.getModel();
        //
        //KhuVuc kv = (KhuVuc) comBoMode.getElementAt(cbxKhuVuc.getSelectedIndex());;
        KhuVuc kv = kvDC;
        //
        for (int i = 0; i < lstBan.size(); i++) {
            Ban ban = lstBan.get(i);
            
            Integer makv = ban.getMaKhuVuc();
            
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
        pnBan.validate();
        pnBan.repaint();
    }
     public static Ban getBanBH() {
        KhuVuc Kv = kvDC;
        return BHDao.getBan(lbTenBan.getText(), Kv.getMaKhuVuc());
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
        btBan.addActionListener((ae) -> {
            lbTenBan.setText(btBan.getText());
            KhuVuc kv = kvDC;
            if (!ban.isTrangThai()) {
                ModeTable.setRowCount(0);
                HanhDong.MaHD = null;
                cbxKhuyenMai.setSelectedIndex(0);
                int chon = JOptionPane.showConfirmDialog(null, "Bạn muốn ngồi bàn này",
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
    public static void suKienHoaDon() {
        KhuVuc kvc = kvDC;
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
     //tạo hóa đơn tạm thời
    public static void taoHoaDonTamThoi() {
        comBoMode = (DefaultComboBoxModel) cbxKhuVuc.getModel();
        comboSuKien = (DefaultComboBoxModel) cbxKhuyenMai.getModel();
        //
        System.out.println(HDDAO.getSoHD());
        KhuVuc kv = kvDC;
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
    //sự kiện load data lên table ctHoaDon tạm thời
    public static void loadDaTaToTableCTHoaDon() {
        ModeTable = (DefaultTableModel) tblBanHang.getModel();
        ModeTable.setRowCount(0);
        CTHDDAO.loadDaTaToTableCTHD(HanhDong.MaHD, ModeTable);
        LBTongTien.setText(ChuyenDoi.chuyenDoiTien(HanhDong.TongTien));
        lbMaHD.setText(HanhDong.MaHD);
    }
    //
    public static void setTextTongTienThanhToan() {
        comboSuKien = (DefaultComboBoxModel) cbxKhuyenMai.getModel();
        SuKien sk = (SuKien) comboSuKien.getElementAt(cbxKhuyenMai.getSelectedIndex());
        if (sk != null) {
            HanhDong.TongTienThanhToan = HanhDong.TongTien - HanhDong.TongTien * (sk.getUuDai() / 100);
            lbGiamGia.setText(String.valueOf(sk.getUuDai()));
            lbThanhToan.setText(ChuyenDoi.chuyenDoiTien(HanhDong.TongTienThanhToan));
        }
    }
}
