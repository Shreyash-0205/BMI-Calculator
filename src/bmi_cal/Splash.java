package bmi_cal;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Splash extends JFrame implements ActionListener {


	Splash() {

        getContentPane().setBackground(Color.white);
        setLayout(null);

        JLabel heading = new JLabel("BMI CALCULATOR");
        heading.setBounds(220, 30, 1200, 60);
        heading.setFont(new Font("serif", Font.PLAIN, 60));
        heading.setForeground(Color.BLACK);
        add(heading);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/bmisplash.png"));
        Image i2 = i1.getImage().getScaledInstance(1200, 700, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 00, 900, 500);
        add(image);

        JButton clickhere = new JButton("CALCULATE NOW");
        clickhere.setBounds(120, 250, 300,70);
        clickhere.setBackground(Color.WHITE);
        clickhere.setForeground(Color.BLACK);
        clickhere.addActionListener(this);
        image.add(clickhere);


        setSize(900, 500);
        setLocation(300, 150);
        setVisible(true);


    }

    public void actionPerformed(ActionEvent ae) {
        setVisible(false);
        new login();
    }

    public static void main(String args[]) {
        new Splash();
    }
}