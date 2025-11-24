package com.mycompany.modul7;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManajemenNilaiSiswaApp extends JFrame {

    private JTextField txtNama;
    private JTextField txtNilai;
    private JComboBox<String> cmbMatkul;
    private JTable tableData;
    private DefaultTableModel tableModel;
    private JTabbedPane tabbedpane;

    private JPanel createInputPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // komponen Nama
        panel.add(new JLabel("Nama Siswa:"));
        txtNama = new JTextField();
        panel.add(txtNama);

        //komponen Mata Pelajaran (ComboBox)
        panel.add(new JLabel("Mata Pelajaran:"));
        String[] matkul = {
                "Matematika Dasar",
                "Bahasa Indonesia",
                "Algoritma dan Pemrograman I",
                "Praktikum Pemrograman II"
        };
        cmbMatkul = new JComboBox<>(matkul);
        panel.add(cmbMatkul);

        // Komponen Nilai
        panel.add(new JLabel("Nilai (0-100):"));
        txtNilai = new JTextField();
        panel.add(txtNilai);

        // Tombol Simpan
        panel.add(new JLabel(""));
        JButton btnSimpan = new JButton("Simpan Data");
        panel.add(btnSimpan);

        // Event Handleing tolbol simpan
        btnSimpan.addActionListener((ActionEvent e) -> {
            prosesSimpan();
        });

        return panel;
    }
    
    //Method Untuk membuat desain tab tabel
    private JPanel createTablePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        //Setup model tabel (Kolom)
        String[] kolom = {"Nama Siswa", "Mata Pelajaran", "Nilai", "Grade"};
        tableModel = new DefaultTableModel(kolom, 0);
        tableData = new JTable(tableModel);
        
        //Membungkus lebel dengan ScrollPane (agar bisa discroll jika data banyak)
        JScrollPane scrollPane = new JScrollPane(tableData);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }
    
    //Logika validasi dan penyimpanan Data
    private void prosesSimpan() {
        //1.  Ambil data dari input
        String nama = txtNama.getText();
        String matkul = (String) cmbMatkul.getSelectedItem();
        String strNilai = txtNilai.getText();

        // 2. Validasi Input
        if (nama.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nama tidak boleh kosong!",
                    "Error Validasi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validasi 2: cek apakah nilai berupa angka dan dalam range valid
        int nilai;
        try {
            nilai = Integer.parseInt(strNilai);

            if (nilai < 0 || nilai > 100) {
                JOptionPane.showMessageDialog(this,
                        "Nilai harus antara 0 - 100!",
                        "Error Validasi",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Nilai harus berupa angka!",
                    "Error Validasi",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        //3. Logika Bisnis (Menentukan grade)
        String grade;
        if (nilai >= 80) grade = "A";
        else if (nilai >= 70) grade = "AB";
        else if (nilai >= 60) grade = "B";
        else if (nilai >= 50) grade = "BC";
        else if (nilai >= 40) grade = "C";
        else if (nilai >= 30) grade = "D";
        else grade = "E";

        //4. Masukkan ke tabel(Update model)
        Object[] baris = {nama, matkul, nilai, grade};
        tableModel.addRow(baris);

        //5. Reset form dan pindah tab
        txtNama.setText("");
        txtNilai.setText("");
        cmbMatkul.setSelectedIndex(0);

        JOptionPane.showMessageDialog(this, "Data Berhasil Disimpan!");
        tabbedpane.setSelectedIndex(1);
    }
    
    public ManajemenNilaiSiswaApp() {
        //1. Konfigurasi Frame Utama
        setTitle("Manajemen Nilai Siswa");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //2. Inisialisasi Tabbed Pane
        tabbedpane = new JTabbedPane();
        //3. Membuat Panel Untuk Tab1 (Form Input)
        tabbedpane.addTab("Input Data", createInputPanel());
        //4. Membuat Panel Untuk Tab 2 (tabel Data)
        tabbedpane.addTab("Data Tersimpan", createTablePanel());
        //Menambahkan TabbedPane Ke Frame
        add(tabbedpane);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() ->{
            new ManajemenNilaiSiswaApp().setVisible(true);
        });
    }
}
