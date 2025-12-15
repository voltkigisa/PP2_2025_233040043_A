/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.modul10.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MahasiswaView extends JFrame {

    public JTextField txtNama, txtNIM, txtJurusan, txtCari;
    public JButton btnSimpan, btnEdit, btnHapus, btnClear, btnCari;
    public JTable table;
    public DefaultTableModel model;

    public MahasiswaView() {
        setTitle("Aplikasi CRUD Mahasiswa - MVC");
        setSize(600, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panelForm = new JPanel(new GridLayout(5, 2, 10, 10));
        panelForm.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        panelForm.add(new JLabel("Nama"));
        txtNama = new JTextField();
        panelForm.add(txtNama);

        panelForm.add(new JLabel("NIM"));
        txtNIM = new JTextField();
        panelForm.add(txtNIM);

        panelForm.add(new JLabel("Jurusan"));
        txtJurusan = new JTextField();
        panelForm.add(txtJurusan);

        panelForm.add(new JLabel("Cari Nama"));
        txtCari = new JTextField();
        panelForm.add(txtCari);

        JPanel panelButton = new JPanel();
        btnSimpan = new JButton("Simpan");
        btnEdit = new JButton("Edit");
        btnHapus = new JButton("Hapus");
        btnClear = new JButton("Clear");
        btnCari = new JButton("Cari");

        panelButton.add(btnSimpan);
        panelButton.add(btnEdit);
        panelButton.add(btnHapus);
        panelButton.add(btnClear);
        panelButton.add(btnCari);
        
        JPanel panelAtas = new JPanel (new BorderLayout());
        panelAtas.add(panelForm, BorderLayout.NORTH);
        panelAtas.add(panelButton, BorderLayout.SOUTH);
        add(panelAtas, BorderLayout.NORTH);

        model = new DefaultTableModel(
            new String[]{"No", "Nama", "NIM", "Jurusan"}, 0
        );
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);
    }
}
