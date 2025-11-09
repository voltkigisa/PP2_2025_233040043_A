package com.mycompany.modul6;

import javax.swing.*;
import java.awt.*;
/**
 *
 * @author Voltkigisa
 */
public class latihan1 {
    public static void main(String[] args) {
        // 1. Buat Frame
        JFrame frame = new JFrame("latihan1");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize (400, 300);
        
        //2. Buat layar di bagian astas 
        frame.add(new JTextField(), BorderLayout.NORTH);
        
        //3. buat panel untuk tombol dengan grid 4x4
        JPanel panelTombol = new JPanel();
        panelTombol.setLayout(new GridLayout(4, 4, 5, 5));
        
        //4, tambahkan 16 tombol
        panelTombol.add(new JButton("7"));
        panelTombol.add(new JButton("8"));
        panelTombol.add(new JButton("9"));
        panelTombol.add(new JButton("/"));
        panelTombol.add(new JButton("4"));
        panelTombol.add(new JButton("5"));
        panelTombol.add(new JButton("6"));
        panelTombol.add(new JButton("*"));
        panelTombol.add(new JButton("1"));
        panelTombol.add(new JButton("2"));
        panelTombol.add(new JButton("3"));
        panelTombol.add(new JButton("-"));
        panelTombol.add(new JButton("0"));
        panelTombol.add(new JButton("."));
        panelTombol.add(new JButton("="));
        panelTombol.add(new JButton("+"));
        
        //tambahkan panel ke frame CENTER
        frame.add(panelTombol, BorderLayout.CENTER);
        
        frame.setVisible(true);
    }
}

