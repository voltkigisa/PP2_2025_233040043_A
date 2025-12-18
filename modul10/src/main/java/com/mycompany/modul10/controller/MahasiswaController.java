/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.modul10.controller;

import com.mycompany.modul10.model.MahasiswaModel;
import com.mycompany.modul10.view.MahasiswaView;
import javax.swing.*;
import java.sql.ResultSet;

public class MahasiswaController {

    private MahasiswaView view;
    private MahasiswaModel model;

    public MahasiswaController(MahasiswaView view) {
        this.view = view;
        this.model = new MahasiswaModel();
        
        view.table.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                int row = view.table.getSelectedRow();

                view.txtNama.setText(view.model.getValueAt(row, 1).toString());
                view.txtNIM.setText(view.model.getValueAt(row, 2).toString());
                view.txtJurusan.setText(view.model.getValueAt(row, 3).toString());
            }
        });

        
        view.btnSimpan.addActionListener(e -> simpan());
        view.btnEdit.addActionListener(e -> edit());
        view.btnHapus.addActionListener(e -> hapus());
        view.btnCari.addActionListener(e -> cari());
        view.btnClear.addActionListener(e -> clear());

        loadData();
    }
    
    private void edit() {
        if (view.txtNIM.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(view, "Pilih data dari tabel terlebih dahulu!");
            return;
        }

        try {
            int hasil = model.update(
                view.txtNama.getText(),
                view.txtJurusan.getText(),
                view.txtNIM.getText()
            );

            if (hasil > 0) {
                JOptionPane.showMessageDialog(view, "Data berhasil diubah");
                loadData();
                clear();
            } else {
                JOptionPane.showMessageDialog(view, "Data tidak ditemukan!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, e.getMessage());
        }
    }


    private void loadData() {
        try {
            view.model.setRowCount(0);
            ResultSet rs = model.getAll();
            int no = 1;
            while (rs.next()) {
                view.model.addRow(new Object[]{
                    no++,
                    rs.getString("nama"),
                    rs.getString("nim"),
                    rs.getString("jurusan")
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, e.getMessage());
        }
    }

    private boolean validasiInput() {
        if (view.txtNama.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(view, "Nama tidak boleh kosong!");
            view.txtNama.requestFocus();
            return false;
        }

        if (view.txtNIM.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(view, "NIM tidak boleh kosong!");
            view.txtNIM.requestFocus();
            return false;
        }

        if (view.txtJurusan.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(view, "Jurusan tidak boleh kosong!");
            view.txtJurusan.requestFocus();
            return false;
        }

        return true;
    }

    private void simpan() {
    if (!validasiInput()) return;

    try {
        if (model.nimSudahAda(view.txtNIM.getText())) {
            JOptionPane.showMessageDialog(view, "NIM sudah ada!");
            return;
        }
        model.insert(
            view.txtNama.getText(),
            view.txtNIM.getText(),
            view.txtJurusan.getText()
        );
        loadData();
        clear();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(view, e.getMessage());
    }
}
    
    private void hapus() {
        try {
            int confirm = JOptionPane.showConfirmDialog(
                view,
                "Yakin ingin menghapus data?",
                "Konfirmasi",
                JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {
                model.delete(view.txtNIM.getText());
                JOptionPane.showMessageDialog(view, "Data berhasil dihapus");
                loadData();
                clear();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, e.getMessage());
        }
    }


    private void cari() {
        try {
            view.model.setRowCount(0);
            ResultSet rs = model.cariNama(view.txtCari.getText());
            int no = 1;
            while (rs.next()) {
                view.model.addRow(new Object[]{
                    no++,
                    rs.getString("nama"),
                    rs.getString("nim"),
                    rs.getString("jurusan")
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, e.getMessage());
        }
    }

    private void clear() {
        view.txtNama.setText("");
        view.txtNIM.setText("");
        view.txtJurusan.setText("");
    }
}
