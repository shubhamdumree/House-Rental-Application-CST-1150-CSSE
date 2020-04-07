package sample;

import javax.swing.*;
import java.awt.*;


// Class written to overwrite existing JOptionPane
public class MessageBox {
    // Method to call write a message, takes a string as parameter
    public void ShowMessage(String message) {

        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                JOptionPane.showMessageDialog(null, message);

            }
        });
    }
}
