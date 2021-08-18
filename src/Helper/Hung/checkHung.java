/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helper.Hung;

import java.awt.Color;
import static java.awt.Color.pink;
import static java.awt.Color.white;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Sieu Nhan Bay
 */
public class checkHung {

    //pass từ 3-16 kí tự
    public static boolean checkPass(JPasswordField txt) {
        txt.setBackground(white);
        if (txt.getPassword().length > 2 && txt.getPassword().length < 17) {
            return true;
        } else {
            txt.setBackground(pink);
            JOptionPane.showMessageDialog(txt, "phải có từ 3-16 kí tự.");
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
            JOptionPane.showMessageDialog(txt, "không đúng định dạng");
            return false;
        }
    }

    public static boolean checkNullText(JTextField txt) {
        if (txt.getText().trim().length() > 0) {
            return true;
        } else {
            txt.setBackground(pink);
//            dialogHelper.alert(txt.getRootPane(), "Không được để trống " + txt.getName());
            JOptionPane.showMessageDialog(txt, "Không được để trống ");
            return false;
        }
    }

    public static boolean checkNullText(JTextArea txt) {
        txt.setBackground(white);
        if (txt.getText().trim().length() > 0) {
            return true;
        } else {
            txt.setBackground(pink);
            JOptionPane.showMessageDialog(txt, "Không được để trống ");
            return false;
        }
    }

    public static boolean checkNullPass(JPasswordField txt) {
        txt.setBackground(white);
        if (txt.getPassword().length > 0) {
            return true;
        } else {
            txt.setBackground(pink);
            JOptionPane.showMessageDialog(txt, "Không được để trống ");
            return false;
        }
    }
      public static boolean checkMaNV(JTextField txt) {
        txt.setBackground(white);
        String id = txt.getText();
        String rgx = "[a-zA-Z0-9]{1,15}";
        if (id.matches(rgx)) {
            return true;
        } else {
            txt.setBackground(Color.red);
            JOptionPane.showMessageDialog(txt, "phải có 1-15 kí tự\nchữ hoa, thường không dấu hoặc số.");
            return false;
        }
    }
       public static boolean checkName(JTextField txt) {
        txt.setBackground(white);
        String id = txt.getText();
        String rgx = "^[A-Za-zÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ ]{3,25}$";
        if (id.matches(rgx)) {
            return true;
        } else {
            txt.setBackground(pink);
            JOptionPane.showMessageDialog(txt, "phải là tên tiếng việt hoặc không đấu\ntừ 3-25 kí tự");
            return false;
        }
        
    }
      
}
