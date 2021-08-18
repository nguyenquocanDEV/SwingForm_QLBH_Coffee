/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helper;

import java.awt.Image;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.swing.ImageIcon;
import model.TaiKhoanMode;

/**
 *
 * @author CHIEN
 */
public class shareHelper {

    public static TaiKhoanMode User = null;
    public static boolean vaitro = false;
    //icon bàn
    ImageIcon img = new ImageIcon("src\\Icon\\table.png");
    Image im = img.getImage();
    ImageIcon icon = new ImageIcon(im.getScaledInstance(50, 50, im.SCALE_SMOOTH));
    //

    public static synchronized ImageIcon getIcon() {
        ImageIcon img = new ImageIcon("src\\Icon\\table.png");
        Image im = img.getImage();
        ImageIcon icon = new ImageIcon(im.getScaledInstance(50, 50, im.SCALE_SMOOTH));
        return icon;
    }

    //
    //xóa thông tin người xử dụng khi đăng xuất
    public synchronized static void logOff() {
        shareHelper.User = null;
    }

    //kiểm tra đã đăng nhập hay chưa
    public synchronized static boolean checkDangNhap() {
        return shareHelper.User != null;
    }

    /**
     * Ảnh biểu tượng của ứng dụng, xuất hiện trên mọi cửa sổ
     */

    /**
     * Sao chép file logo chuyên đề vào thư mục logos (tạo nếu chưa có thư mục
     * logos)
     *
     * @param file là đối tượng file ảnh
     * @return chép được hay không
     */
    public static boolean saveLogo(File file) {
        File dir = new File("logos");  //khai báo thư mục logos ngang hàng với src
        // Tạo thư mục nếu chưa tồn tại
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File newFile = new File(dir, file.getName());
        try {
            // Copy vào thư mục logos (đè nếu đã tồn tại)
            Path source = Paths.get(file.getAbsolutePath());
            Path destination = Paths.get(newFile.getAbsolutePath());
            Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    /**
     * Đọc hình ảnh logo chuyên đề trong thư mục logos theo tenFile
     *
     * @param fileName là tên file logo
     * @return ImageIcon ảnh đọc được
     */
    public static ImageIcon readLogo(String fileName) {
        File path = new File("logos", fileName);
        return new ImageIcon(new ImageIcon(path.getAbsolutePath()).getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT));
    }

}
