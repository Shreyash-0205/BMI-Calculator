package bmi_cal;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.ResultSet;

public class login extends JFrame implements ActionListener {

    JTextField tfusername;
    JPasswordField tfpassword;
    JButton loginBtn, registerBtn, forgotBtn;

    login() {

        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel lblusername = new JLabel("Username");
        lblusername.setBounds(40, 20, 100, 30);
        add(lblusername);

        tfusername = new JTextField();
        tfusername.setBounds(150, 20, 150, 30);
        add(tfusername);

        JLabel lblpassword = new JLabel("Password");
        lblpassword.setBounds(40, 70, 100, 30);
        add(lblpassword);

        tfpassword = new JPasswordField();
        tfpassword.setBounds(150, 70, 150, 30);
        add(tfpassword);

        loginBtn = new JButton("LOGIN");
        loginBtn.setBounds(150, 140, 150, 30);
        loginBtn.setBackground(Color.blue);
        loginBtn.setForeground(Color.white);
        loginBtn.addActionListener(this);
        add(loginBtn);

        registerBtn = new JButton("Register");
        registerBtn.setBounds(150, 180, 150, 30);
        registerBtn.setBackground(Color.white);
        registerBtn.setForeground(Color.black);
        registerBtn.addActionListener(e -> openRegisterPage());
        add(registerBtn);

        
        forgotBtn = new JButton("Forgot Password");
        forgotBtn.setBounds(10, 140, 130, 30);
        forgotBtn.setBackground(Color.white);
        forgotBtn.setForeground(Color.black);
        forgotBtn.addActionListener(e -> openForgotPasswordPage());
        add(forgotBtn);

        ImageIcon i1 = new ImageIcon(getClass().getResource("/icons/login_image.jpg"));
        Image i2 = i1.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(350, 0, 200, 200);
        add(image);

        setSize(600, 350);
        setLocation(450, 250);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == loginBtn) {
            try {
                String username = tfusername.getText();
                String password = new String(tfpassword.getPassword());

                Conn c = new Conn();
                String query = "select * from login where username = '" + username + "' and password = '" + password + "'";

                ResultSet rs = c.s.executeQuery(query);
                if (rs.next()) {
                    JOptionPane.showMessageDialog(null, "Login successful!");
                    setVisible(false);
                    new logic().setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid username or password");
                } 

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void openRegisterPage() {
        setVisible(false);
        new Register().setVisible(true);
    }

    private void openForgotPasswordPage() {
        setVisible(false);
        new ForgotPassword().setVisible(true);
    }

    public static void main(String[] args) {
        new login();
    }
}
