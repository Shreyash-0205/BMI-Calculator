package bmi_cal;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ForgotPassword extends JFrame implements ActionListener {

    JTextField tfusername;
    JPasswordField tfnewPassword;
    JButton submitBtn, backBtn;

    ForgotPassword() {

        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel lblusername = new JLabel("Username");
        lblusername.setBounds(40, 20, 100, 30);
        add(lblusername);

        tfusername = new JTextField();
        tfusername.setBounds(150, 20, 150, 30);
        add(tfusername);

        JLabel lblnewPassword = new JLabel("New Password");
        lblnewPassword.setBounds(40, 70, 100, 30);
        add(lblnewPassword);

        tfnewPassword = new JPasswordField();
        tfnewPassword.setBounds(150, 70, 150, 30);
        add(tfnewPassword);

        submitBtn = new JButton("Submit");
        submitBtn.setBounds(150, 120, 150, 30);
        submitBtn.setBackground(Color.blue);
        submitBtn.setForeground(Color.white);
        submitBtn.addActionListener(this);
        add(submitBtn);

        backBtn = new JButton("Back");
        backBtn.setBounds(150, 160, 150, 30);
        backBtn.setBackground(Color.white);
        backBtn.setForeground(Color.black);
        backBtn.addActionListener(e -> goBack());
        add(backBtn);

        setSize(400, 250);
        setLocation(500, 300);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submitBtn) {
            String username = tfusername.getText();
            String newPassword = new String(tfnewPassword.getPassword());

            try {
                Conn c = new Conn();
                String query = "update login set password = '" + newPassword + "' where username = '" + username + "'";
                int rowsAffected = c.s.executeUpdate(query);
                
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "Password updated successfully!");
                    setVisible(false);
                    new login().setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Username not found");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void goBack() {
        setVisible(false);
        new login().setVisible(true);
    }
}
