package com.mycompany.pertemuan1;

import javax.swing.*;
import java.awt.*;


/**
 *
 * @author Voltkigisa
 */
public class latihan3 {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("Jendela dengan Tombol");
                frame.setSize (400, 300);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                
                //1. Atur Layout manager
                //FlowLayout akan menyusun komponen dari kiri ke kanan.
                frame.setLayout(new FlowLayout());
                
                //2. Buat komponen GUI
                JLabel label = new JLabel("Teks Awal");
                JButton button = new JButton ("Klik Saya");
                
                /* 3. tambahkan aksi (ActionListener) ke Tombol 
                    Penambahan ini menggunakan lambda untuk mempersingkat
                    penggunaan anonymous inner class
                */
                button.addActionListener(e ->{
                   //aksi ini akan mengubah tesk pada label
                   label.setText("Tombol berhasil diklik!");
                });
                /* 
                   4.Tambahkan komponen ke frame
                    karena kita menggunakan FlowLayout
                    keduanya akan tampil berdampingan
                */
                frame.add(label);
                frame.add(button);
                
                frame.setVisible(true);
            }
        });
    }   
}
