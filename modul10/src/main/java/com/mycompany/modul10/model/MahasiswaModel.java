/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.modul10.model;

import com.mycompany.modul10.KoneksiDB;
import java.sql.*;

public class MahasiswaModel {

    public ResultSet getAll() throws Exception {
        Connection conn = KoneksiDB.configDB();
        Statement stm = conn.createStatement();
        return stm.executeQuery("SELECT * FROM mahasiswa");
    }

    public ResultSet cariNama(String keyword) throws Exception {
        Connection conn = KoneksiDB.configDB();
        PreparedStatement pst = conn.prepareStatement(
            "SELECT * FROM mahasiswa WHERE nama LIKE ?"
        );
        pst.setString(1, "%" + keyword + "%");
        return pst.executeQuery();
    }

    public boolean nimSudahAda(String nim) throws Exception {
        Connection conn = KoneksiDB.configDB();
        PreparedStatement pst = conn.prepareStatement(
            "SELECT nim FROM mahasiswa WHERE nim = ?"
        );
        pst.setString(1, nim);
        ResultSet rs = pst.executeQuery();
        return rs.next();
    }

    public void insert(String nama, String nim, String jurusan) throws Exception {
        String sql = "INSERT INTO mahasiswa (nama, nim, jurusan) VALUES (?, ?, ?)";
        Connection conn = KoneksiDB.configDB();
        PreparedStatement pst = conn.prepareStatement(sql);

        pst.setString(1, nama);
        pst.setString(2, nim);
        pst.setString(3, jurusan);
        pst.execute();
    }

    public int update(String nama, String jurusan, String nim) throws Exception {
        String sql = "UPDATE mahasiswa SET nama=?, jurusan=? WHERE nim=?";
        Connection conn = KoneksiDB.configDB();
        PreparedStatement pst = conn.prepareStatement(sql);

        pst.setString(1, nama);
        pst.setString(2, jurusan);
        pst.setString(3, nim);

        return pst.executeUpdate(); // PENTING
    }

    public void delete(String nim) throws Exception {
        String sql = "DELETE FROM mahasiswa WHERE nim=?";
        Connection conn = KoneksiDB.configDB();
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, nim);
        pst.execute();
    }
}

