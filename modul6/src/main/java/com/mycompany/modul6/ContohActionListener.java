package com.mycompany.modul6;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 *
 * @author Voltkigisa
 */
public class ContohActionListener {
    public static void main(String[] args){
        JFrame frame = new JFrame("Contoh ActionListener");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize (300, 150);
        frame.setLayout(new FlowLayout());
        
        // 1. Buat komponen (Event Source dan komponen lain)
        JLabel label = new JLabel("Halo, klik tombol di bawah!");
        JButton button= new JButton("Klik Saya!");
        
        // 2. Buat Event Listener
        // Kita menggunakan "anonymous inner class" di sini
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 3. Logika yang dieksekusi saat event terjadi
                label.setText("Tombol telah diklik!");
            }
      
        };
        
        // 4. Daftarkan listener ke source
        button.addActionListener(listener);
    
        // Tambahkan komponen ke frame
        frame.add(label);
        frame.add(button);
        frame.setVisible(true);
    }
}
