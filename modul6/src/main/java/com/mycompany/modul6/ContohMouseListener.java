package com.mycompany.modul6;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 *
 * @author Voltkigisa
 */
public class ContohMouseListener {
    public static void main(String[] args){
        JFrame frame = new JFrame("Contoh MouseListener");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize (300, 300);
        
        // 1. Buat komponen (Event Source)
        JPanel panel = new JPanel();
        panel.setBackground(Color.GRAY);
        panel.setPreferredSize(new Dimension(200, 200));
        
        //2. Buat Event Listener (Menggunakan Mouse Adapter)
        MouseAdapter adapter = new MouseAdapter() {
            
            @Override
            public void mouseEntered(MouseEvent e){
                //saat mouse masuk ke panel akan berubah warna
                panel.setBackground(Color.CYAN);
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                //saat kelua lagi balik lagi ke gray
                panel.setBackground(Color.GRAY);
            }
            
            @Override
            public void mouseClicked(MouseEvent e){
                //saat diklik tampilkan koordinat klik
                System.out.println("Mouse diklik di: x=" + e.getX()+", y-" + e.getY());
            }
        };
        
        //3. daftarkan listener ke souce(panel)
        panel.addMouseListener(adapter);
        
        //4. daftarkan panel ke frame
        frame.add(panel);
        frame.setVisible(true);
    }
}

