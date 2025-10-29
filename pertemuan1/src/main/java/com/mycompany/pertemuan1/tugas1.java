package com.mycompany.pertemuan1;

import javax.swing.*;
import java.awt.*;


/**
 *
 * @author Voltkigisa
 */
public class tugas1 {
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
                JButton bSouth = new JButton ("Tomnbol ada di bawah (SOUTH)");
                JButton bWest = new JButton ("WEST");
                JButton bEast = new JButton ("EAST");
                JButton bCenter = new JButton ("CENTER");
                
                /* 3. tambahkan aksi (ActionListener) ke Tombol 
                    Penambahan ini menggunakan lambda untuk mempersingkat
                    penggunaan anonymous inner class
                */
                bSouth.addActionListener(e ->{
                   //aksi ini akan mengubah tesk pada label
                   label.setText("Tombol di SOUTH diklik");
                });
                 bWest.addActionListener(e ->{
                   //aksi ini akan mengubah tesk pada label
                   label.setText("Tombol di WEST diklik");
                });
                  bEast.addActionListener(e ->{
                   //aksi ini akan mengubah tesk pada label
                   label.setText("Tombol di EAST diklik");
                });
                   bCenter.addActionListener(e ->{
                   //aksi ini akan mengubah tesk pada label
                   label.setText("Tombol di CENTER diklik");
                });
                /* 
                   4. tambahkan komponen ke frame DENGAN POSISI
                */
                frame.add(label, BorderLayout.NORTH);
                frame.add(bSouth, BorderLayout.SOUTH);
                //Kita bisa tambahkan komponen lain
                frame.add(bWest, BorderLayout.WEST);
                frame.add(bEast, BorderLayout.EAST);
                frame.add(bCenter, BorderLayout.CENTER);
                
                frame.setVisible(true);
            }
        });
    }   
}
