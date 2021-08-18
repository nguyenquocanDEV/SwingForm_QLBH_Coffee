/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helper.Duc;

import java.awt.Color;
import static java.awt.Color.pink;
import static java.awt.Color.white;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import jdk.nashorn.internal.ir.WhileNode;

/**
 *
 * @author ducmc
 */
public class unlityHelper {

    public static boolean checkName(JTextField txt) {
        txt.setBackground(Color.white);
        String id = txt.getText();
        String rgx = "^[A-Za-zÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ ]{3,50}$";
        if (id.matches(rgx)) {
            return true;
        } else {
            txt.setBackground(Color.pink);
            Helper.Duc.dialogHelper.alert(txt.getRootPane(), txt.getText() + "Bạn nhập sai định dạng tên món ăn");
            return false;
        }
    }

    public static boolean checkMoney(JTextField txt) {
        txt.setBackground(white);
        try {
            float hp = Float.parseFloat(txt.getText());
            if (hp >= 0) {
                return true;
            } else {
                txt.setBackground(pink);
                Helper.Duc.dialogHelper.alert(txt.getRootPane(), txt.getName() + " giá tiền phải là lớn hơn hoặc bằng 0.");
                return false;
            }
        } catch (NumberFormatException e) {
            txt.setBackground(pink);
            Helper.Duc.dialogHelper.alert(txt.getRootPane(), txt.getName() + " giá tiền phải là số thực.");
            return false;
        }
    }

    public static boolean checkNullText(JPasswordField txt) {
        txt.setBackground(white);
        String pW = new String(txt.getPassword());
        if (pW.trim().length() > 0) {
            return true;
        } else {
            txt.setBackground(pink);
            Helper.Duc.dialogHelper.alert(txt.getRootPane(), "Không được để trống " + txt.getName());
            return false;
        }
    }

    //
    public static boolean checkNullText2(JTextField txt) {
        txt.setBackground(white);
        if (txt.getText().trim().length() > 0) {
            return true;
        } else {
            txt.setBackground(pink);
            Helper.Duc.dialogHelper.alert(txt.getRootPane(), "Không được để trống " + txt.getName());
            return false;
        }

    }

    public static boolean checkEmail(JTextField txt) {
        txt.setBackground(white);
        String id = txt.getText();
        String rgx = "^[a-zA-Z][a-zA-Z0-9_\\.]{2,32}@[a-zA-Z0-9]{2,10}(\\.[a-zA-Z0-9]{2,4}){1,2}$";
        if (id.matches(rgx)) {
            return true;
        } else {
            txt.setBackground(pink);
            Helper.Duc.dialogHelper.alert(txt.getRootPane(), txt.getName() + " không đúng định dạng");
            return false;
        }
    }
}
