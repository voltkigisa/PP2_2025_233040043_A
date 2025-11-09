package com.mycompany.modul6;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
/**
 *
 * @author Voltkigisa
 */
public class latihan2 {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Celcius to Fahrenheit");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        
        JPanel panel = new JPanel(new GridLayout(2,2,3,3));
        
        JLabel labelCelcius = new JLabel("Celcius:");
        JTextField inputCelcius = new JTextField();
        
        JLabel labelFahrenheit = new JLabel("Fahrenheit:");
        JLabel hasilFahrenheit = new JLabel(""); // Untuk menampilkan hasil

        JButton konversi = new JButton("Konversi");
        
        konversi.addActionListener((ActionEvent e) -> {
            double celcius = Double.parseDouble(inputCelcius.getText());
            double fahrenheit = (celcius * 9 / 5) + 32;
            hasilFahrenheit.setText(String.format("%.2f", fahrenheit));
        });
        
        panel.add(labelCelcius);
        panel.add(inputCelcius);
        panel.add(labelFahrenheit);
        panel.add(hasilFahrenheit);
        
        frame.add(panel);
        frame.add(konversi, BorderLayout.SOUTH);
        
        frame.setVisible(true);
    }
}
