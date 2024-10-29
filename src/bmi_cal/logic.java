package bmi_cal;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class logic extends JFrame {

    public static void ErrorMessage(String paneTitle, String errorMessage) {
        JOptionPane alerta = new JOptionPane(errorMessage);
        alerta.setMessageType(JOptionPane.ERROR_MESSAGE);
        JDialog dialogo = alerta.createDialog(paneTitle);
        dialogo.setVisible(true);
        dialogo.setAlwaysOnTop(true);
    }


    logic() {
        setTitle("BMI Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(450, 200, 639, 400);

        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        contentPane.add(panel, BorderLayout.CENTER);
        panel.setLayout(null);


        JPanel panelHeight = new JPanel();
        panelHeight.setBackground(Color.LIGHT_GRAY);
        panelHeight.setBounds(12, 58, 292, 141);
        panel.add(panelHeight);
        panelHeight.setLayout(null);

        JTextField txtHeight = new JTextField();
        txtHeight.setFont(new Font("Noto Sans CJK JP Black", Font.BOLD, 18));
        txtHeight.setBounds(74, 62, 146, 37);
        panelHeight.add(txtHeight);
        txtHeight.setColumns(10);

        JLabel lblYourHeight = new JLabel("Height (fts)");
        lblYourHeight.setFont(new Font("Noto Sans CJK KR Black", Font.BOLD, 20));
        lblYourHeight.setBounds(74, 12, 146, 37);
        panelHeight.add(lblYourHeight);


        JPanel panelWeight = new JPanel();
        panelWeight.setBackground(Color.LIGHT_GRAY);
        panelWeight.setBounds(317, 58, 292, 141);
        panel.add(panelWeight);
        panelWeight.setLayout(null);

        JTextField txtWeight = new JTextField();
        txtWeight.setFont(new Font("Noto Sans CJK JP Black", Font.BOLD, 18));
        txtWeight.setColumns(10);
        txtWeight.setBounds(67, 63, 146, 37);
        panelWeight.add(txtWeight);

        JLabel lblYourWeight = new JLabel("Weight (Kgs)");
        lblYourWeight.setFont(new Font("Noto Sans CJK KR Black", Font.BOLD, 20));
        lblYourWeight.setBounds(67, 12, 146, 37);
        panelWeight.add(lblYourWeight);


        JPanel panelImc = new JPanel();
        panelImc.setBackground(Color.LIGHT_GRAY);
        panelImc.setBounds(12, 210, 292, 100);
        panel.add(panelImc);
        panelImc.setLayout(null);

        JLabel lblYourImcIs = new JLabel("Your BMI is:");
        lblYourImcIs.setFont(new Font("Noto Sans CJK KR Black", Font.BOLD, 20));
        lblYourImcIs.setBounds(12, 12, 146, 37);
        panelImc.add(lblYourImcIs);

        JLabel lblImc = new JLabel("");
        lblImc.setFont(new Font("Noto Sans CJK JP Black", Font.BOLD, 18));
        lblImc.setBounds(141, 12, 111, 35);
        panelImc.add(lblImc);

        JLabel lblCategory = new JLabel("");
        lblCategory.setFont(new Font("Noto Sans CJK JP Black", Font.BOLD, 18));
        lblCategory.setBounds(12, 50, 250, 35);
        panelImc.add(lblCategory);

        JButton btnCalculate = new JButton("Calculate My BMI!");
        btnCalculate.setBounds(317, 210, 292, 59);
        panel.add(btnCalculate);
        btnCalculate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (txtHeight.getText().isEmpty() || txtWeight.getText().isEmpty()) {
                    ErrorMessage("Error - Empty Field", "At least one field is empty, check that!");
                } else if (txtHeight.getText().equals("0") || txtWeight.getText().equals("0")) {
                    ErrorMessage("Error - Zero Field", "Value cannot be 0");
                } else {
                    try {
                        double height = Double.parseDouble(checkGD(txtHeight.getText()));
                        double weight = Double.parseDouble(checkGD(txtWeight.getText()));
                        double bmi = calculateBMI(height, weight);
                        lblImc.setText(String.format("%.2f", bmi));
                        lblCategory.setText(getBMICategory(bmi));
                    } catch (NumberFormatException e) {
                        ErrorMessage("Error - Invalid Input", "Please enter valid numbers for height and weight.");
                    }
                }
            }
        });


        JLabel lblImcCalculator = new JLabel("BMI Calculator");
        lblImcCalculator.setForeground(Color.blue);
        lblImcCalculator.setFont(new Font("Noto Sans CJK KR Black", Font.BOLD, 40));
        lblImcCalculator.setBounds(200, 10, 498, 37);
        panel.add(lblImcCalculator);

    }


    private String checkGD(String input) {
        return input.trim();
    }


    private double calculateBMI(double height,double weight) {
        return weight /((height * 0.3048)*(height * 0.3048));
    }


    private String getBMICategory(double bmi) {
        if (bmi < 18.5) {
            return "Underweight";
        } else if (bmi >= 18.5 && bmi < 24.9) {
            return "Normal weight";
        } else if (bmi >= 25 && bmi < 29.9) {
            return "Overweight";
        } else {
            return "Obesity";
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new logic().setVisible(true);
        });
    }
}
