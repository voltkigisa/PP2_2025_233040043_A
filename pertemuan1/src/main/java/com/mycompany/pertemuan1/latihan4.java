package com.mycompany.pertemuan1;

import javax.swing.*;
import java.awt.*;


/**
 *
 * @author Voltkigisa
 */
public class latihan4 {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("Jendela dengan Tombol");
                frame.setSize (400, 300);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                
                //1. Atur Layout manager ke BorderLayout
                //sebenarnya ini tidak perlu
                //karena BorderLayout adalah Layout Manager default
                frame.setLayout(new BorderLayout());
                
                //2. Buat komponen
                JLabel label = new JLabel("Label ada di atas (NORTH)");
                JButton button = new JButton ("Tomnbol ada di bawah (SOUTH)");
                
                /* 3. tambahkan aksi (ActionListener) ke Tombol 
                    Penambahan ini menggunakan lambda untuk mempersingkat
                    penggunaan anonymous inner class
                */
                button.addActionListener(e ->{
                   //aksi ini akan mengubah tesk pada label
                   label.setText("Tombol di SOUTH diklik");
                });
                /* 
                   4. tambahkan komponen ke frame DENGAN POSISI
                */
                frame.add(label, BorderLayout.NORTH);
                frame.add(button, BorderLayout.SOUTH);
                //Kita bisa tambahkan komponen lain
                frame.add(new JButton("WEST"), BorderLayout.WEST);
                frame.add(new JButton("EAST"), BorderLayout.EAST);
                frame.add(new JButton("CENTER"), BorderLayout.CENTER);
                
                frame.setVisible(true);
            }
        });
    }   
}
