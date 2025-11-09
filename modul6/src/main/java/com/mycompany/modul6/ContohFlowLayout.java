package com.mycompany.modul6;

import javax.swing.*;
/**
 *
 * @author Voltkigisa
 */
public class ContohFlowLayout {
    public static void main(String[] args) {
    //1. buat frame (window)
    JFrame frame = new JFrame("Contoh FlowLayout");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(300, 500);
    
    //2. buat panel
    //JPanel secara default sudah menggunakan Flowlayout
    JPanel panel = new JPanel();
    //Anda juga bisa  mengaturnya secara eksplisit
    //panel.setLayout (new Flowlayout(Flowlayout.CENTER));//CENTER, LEFT, atau RIGHT
    
    //3. buat dan tambahkan komponen
    panel.add(new JButton("Teombol 1"));
    panel.add(new JButton("Teombol 2"));
    panel.add(new JButton("Teombol Tiga"));
    panel.add(new JButton("Teombol Empat Panjang"));
    panel.add(new JButton("Teombol 5"));
    
    //4. Tambahkan panel ke frame
    frame.add(panel);
    
    //5. tampilkan frame
    frame.setVisible(true);
    }
}
