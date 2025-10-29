package com.mycompany.pertemuan1;

import javax.swing.*;


/**
 *
 * @author Voltkigisa
 */
public class latihan2 {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("Jendela dengan Label");
                frame.setSize (400, 300);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                
                //1. Buat Komponen JLabel
                JLabel label = new JLabel("ini adalah JLabel.");
                
                //2. Tambahkan JLabel ke JFrame
                //secara default, JFrame menggunakan BorderLayout,
                //Dan .add() akan menambahkannya ke bagian tengah (Center)
                frame.add(label);
                frame.setVisible(true);
            }
        });
    }   
}