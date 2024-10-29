package bmi_cal;

import java.awt.Color;
import java.awt.Font;
import java.sql.*;
import javax.swing.*;

public class Register extends JFrame {

    JTextField tfusername;
    JPasswordField tfpassword;
    JButton RegisterBtn;
    JButton BackBtn;

    Register() {

        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel lblusername = new JLabel("Username");
        lblusername.setBounds(40, 30, 100, 30);
        lblusername.setFont(new Font("serif", Font.PLAIN, 22));
        add(lblusername);

        tfusername = new JTextField();
        tfusername.setBounds(150, 30, 150, 30);
        add(tfusername);

        JLabel lblpassword = new JLabel("Password");
        lblpassword.setBounds(40, 75, 100, 30);
        lblpassword.setFont(new Font("serif", Font.PLAIN, 22));
        add(lblpassword);

        tfpassword = new JPasswordField();
        tfpassword.setBounds(150, 80, 150, 30);
        add(tfpassword);

        RegisterBtn = new JButton("REGISTER");
        RegisterBtn.setBounds(100, 130, 150, 30);
        RegisterBtn.setBackground(Color.black);
        RegisterBtn.setForeground(Color.white);
        RegisterBtn.addActionListener(e -> registerUser());
        add(RegisterBtn);
        
        BackBtn = new JButton("BACK");
        BackBtn.setBounds(100, 170, 150, 30);
        BackBtn.setBackground(Color.black);
        BackBtn.setForeground(Color.white);
        BackBtn.addActionListener(e -> openLoginPage());
        add(BackBtn);

        setSize(350, 250);
        setLocation(570, 300);
        setVisible(true);
    }

    private void registerUser() {
        String username = tfusername.getText();
        String password = new String(tfpassword.getPassword());

        String url = "jdbc:mysql:///bmi_calculator";
        String user = "root";
        String pass = "Rahul@126";

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.");
            return;
        }

        try (Connection conn = DriverManager.getConnection(url, user, pass)) {
            String sql = "INSERT INTO login (username, password) VALUES (?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(this, "User registered successfully!");
                tfusername.setText("");
                tfpassword.setText("");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error connecting to the database. Please try again.");
        }
    }

    
    private void openLoginPage() {
        setVisible(false);
        new login().setVisible(true);
    }

    public static void main(String[] args) {
        new Register();
    }
}
