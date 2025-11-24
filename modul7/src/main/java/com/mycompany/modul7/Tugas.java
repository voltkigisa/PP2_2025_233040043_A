package com.mycompany.modul7;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class Tugas extends JFrame {

    private JTextField txtNama;
    private JTextField txtNilai;
    private JComboBox<String> cmbMatkul;
    private JTable tableData;
    private DefaultTableModel tableModel;
    private JTabbedPane tabbedPane;

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
        
        //tombol Reset
        JButton btnReset = new JButton("Reset");
        panel.add(btnReset);
        
        // Tombol Simpan
        JButton btnSimpan = new JButton("Simpan Data");
        panel.add(btnSimpan);

        // Event Handleing tombol simpan
        btnSimpan.addActionListener((ActionEvent e) -> {
            prosesSimpan();
        });
        
        //Event Handling tombol reset
        btnReset.addActionListener(e -> 
            resetForm()
        );
       
        return panel;
    }
    
    //Method Untuk membuat desain tab tabel
    private JPanel createTablePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        //Setup model tabel (Kolom)
        String[] kolom = {"Nama Siswa", "Mata Pelajaran", "Nilai", "Grade"};
        tableModel = new DefaultTableModel(kolom, 0);
        tableData = new JTable(tableModel);
        
        //tombol hapus
        JButton btnHapus = new JButton("Hapus Data Terpilih");
        btnHapus.addActionListener(e ->
            hapusData()
        );
        
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(btnHapus);
        panel.add(bottomPanel, BorderLayout.SOUTH);
        
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

        // VALIDASI NAMA
        if (nama.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nama tidak boleh kosong!",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (nama.length() < 3) {
            JOptionPane.showMessageDialog(this, "Nama minimal 3 karakter!",
                    "Error", JOptionPane.ERROR_MESSAGE);
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

        //3. Logika Switch (Menentukan grade)
        String grade = "";
        int kondisi = 0;
        
        if (nilai >= 80) kondisi = 1;
        else if (nilai >= 70) kondisi = 2;
        else if (nilai >= 60) kondisi = 3;
        else if (nilai >= 50) kondisi = 4;
        else if (nilai >= 40) kondisi = 5;
        else if (nilai >= 30) kondisi = 6;
        else kondisi = 7;

        switch (kondisi) {
            case 1: grade = "A"; break;
            case 2: grade = "AB"; break;
            case 3: grade = "B"; break;
            case 4: grade = "BC"; break;
            case 5: grade = "C"; break;
            case 6: grade = "D"; break;
            default: grade = "E"; break;
        }

        //4. Masukkan ke tabel(Update model)
        Object[] baris = {nama, matkul, nilai, grade};
        tableModel.addRow(baris);

        //5. Reset form dan pindah tab
        resetForm();

        JOptionPane.showMessageDialog(this, "Data Berhasil Disimpan!");
        tabbedPane.setSelectedIndex(1);
    }
    
    private void resetForm(){
        txtNama.setText("");
        txtNilai.setText("");
        cmbMatkul.setSelectedIndex(0);
    }
    
    // Hapus Data
    private void hapusData() {
        int row = tableData.getSelectedRow();

        if (row >= 0) {
            tableModel.removeRow(row);
            JOptionPane.showMessageDialog(this, "Data berhasil dihapus!");
        } else {
            JOptionPane.showMessageDialog(this,
                    "Pilih baris yang akan dihapus!",
                    "Peringatan",
                    JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public Tugas() {
        //1. Konfigurasi Frame Utama
        setTitle("Manajemen Nilai Siswa");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //2. Inisialisasi Tabbed Pane
        tabbedPane = new JTabbedPane();
        //3. Membuat Panel Untuk Tab1 (Form Input)
        tabbedPane.addTab("Input Data", createInputPanel());
        //4. Membuat Panel Untuk Tab 2 (tabel Data)
        tabbedPane.addTab("Data Tersimpan", createTablePanel());
        //Menambahkan TabbedPane Ke Frame
        add(tabbedPane);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() ->{
            new Tugas().setVisible(true);
        });
    }
}
