/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.modul9;

/**
 *
 * @author Voltkigisa
 */

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class AplikasiFileIO extends JFrame{
    //   komponen ui
    private JTextArea textArea;
    private JButton btnOpenText, btnSaveText;
    private JButton btnSaveBinary, btnLoadBinary;
    private JFileChooser fileChooser;
    private JButton btnAppendText;
    
    public AplikasiFileIO() {
        super("Tutorial File IO & Exception Handling");
        setSize(700,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        //Inisialisasi komponene
        textArea =  new JTextArea();
        textArea.setFont(new Font("Monospaced", Font. PLAIN, 26));
        fileChooser = new JFileChooser();
        
        // Panel Tombol
        JPanel buttonPanel = new JPanel();
        btnOpenText = new JButton("Buka Text");
        btnSaveText = new JButton("Simpan Text");
        btnSaveBinary = new JButton("Simpan Config (Binary)");
        btnLoadBinary = new JButton("Muat Config (Binary)");
        btnAppendText = new JButton("Append Text");
        
        buttonPanel.add(btnOpenText);
        buttonPanel.add(btnSaveText);
        buttonPanel.add(btnSaveBinary);
        buttonPanel.add(btnLoadBinary);
        buttonPanel.add(btnAppendText);
        
        // Layout
        add(new JScrollPane (textArea), BorderLayout.CENTER);
        add (buttonPanel, BorderLayout.SOUTH);
        
        //--Event Handling--
        
        // 1. MEMBACA FILE TEKS (Text Stream)
        btnOpenText.addActionListener (e -> bukaFileTeks());
        
        // 2. MENULIS FILE TEKS (Text Stream)
        btnSaveText.addActionListener (e -> simpanFileTeks());
        
        // 3. MENULIS FILE BINARY (Byte Stream)
        btnSaveBinary.addActionListener(e -> simpanConfigBinary());
        btnSaveBinary.addActionListener(e -> simpanUserConfig());
        
        // 4. MEMBACA FILE BINARY (Byte Stream)
        btnLoadBinary.addActionListener (e -> muatConfigBinary());
        btnLoadBinary.addActionListener(e -> muatUserConfig());
        
        btnAppendText.addActionListener(e -> appendFileTeks());
    
        bacaLastNotes();
    }
    
    private void bukaFileTeks() {
        if(fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            BufferedReader reader = null;//deklarasi di luar try agar diakses di finally
            
            try{
                //membuka stream
                reader = new BufferedReader(new FileReader(file));
                textArea.setText("");//kosongkan Area
                
                String line;
                // Baca baris demi baris
                while ((line = reader.readLine()) != null) {
                    textArea.append (line + "\n");
                }
                
                JOptionPane.showMessageDialog(this, "File berhasil dimmat!");
                
            }catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(this, "File tidak ditemukan: " + ex.getMessage());
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Gagal membaca file: " + ex.getMessage());
            } finally {
                // Blok Finally: Selalu dijalankan untuk menutup resource
                try {
                    if (reader != null) {
                        reader.close(); // Penting : Menutup Resouces
                        }
                    } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
    
    // Contoh: Menulis File Teks menggunakan Try-With-Resources (Lebih Modern)
    private void simpanFileTeks () {
        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            
            // try with resources otomatis menutup stream tanpa biok finally marmal
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write(textArea.getText());
                JOptionPane.showMessageDialog(this, "File berhasil disimpan!");
                simpanKeLastNotes();
            }catch (IOException ex){
                JOptionPane.showMessageDialog(this, "Gagal menyimpan file:" + ex.getMessage());
            }
        }
    }
    
    //Contoh: Menulis Binarу (Menyimpan ukuran foot saat ini ke file .bin)
    private void simpanConfigBinary() {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream("config.bin"))){
        // kita simpan ukuran font saat ini (Integer)
            int fontsize = textArea.getFont().getSize();
            dos.writeInt(fontsize);
            
            JOptionPane.showMessageDialog(this, "Ukuran font (" + fontsize + ") disimpan ke config.bin");
        }catch (IOException ex){
            JOptionPane.showMessageDialog(this, "Gagal menyimpan binary: " + ex.getMessage());
        }
    }
    
    private void muatConfigBinary() {
        try (DataInputStream dis = new DataInputStream(new FileInputStream("Config.bin"))){
            //Menbaca data Interger mentah
            int fontSize = dis.readInt();
            
            // Terapkan ke aplikasi
            textArea.setFont(new Font("Honospaced", Font. PLAIN, fontSize));
            JOptionPane.showMessageDialog(this, "Font diubah menjadi ukuran: "+fontSize);
        }catch (FileNotFoundException ex){
            JOptionPane.showMessageDialog(this, "File config.bin belum dibuat!");
        }catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Gagal membaca binary: " + ex.getMessage());
        }
    }
    
    private void bacaLastNotes() {
        File file = new File("last_notes.txt");
        
        try(BufferedReader reader = new BufferedReader(new FileReader(file))){
           textArea.setText("");
           
           String line;
           while ((line = reader.readLine()) != null){
               textArea.append(line+ "\n");
           }
        }catch (FileNotFoundException ex){
            //jika file tidak ada, abaikan saja - jangan tampikan error 
        }catch (IOException ex){
            ex.printStackTrace(); 
        }
    }
    
    private void simpanKeLastNotes() {
        File file = new File("last_notes.txt");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(textArea.getText());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    private void simpanUserConfig() {
        UserConfig config = new UserConfig();

        // Ambil data dari UI
        config.setUsername("Default User"); 
        config.setFontsize(textArea.getFont().getSize());

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("userconfig.bin"))) {
            oos.writeObject(config);
            JOptionPane.showMessageDialog(this, "Config berhasil disimpan ke userconfig.bin");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Gagal menyimpan config: " + ex.getMessage());
        }
    }
    
    private void muatUserConfig() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("userconfig.bin"))) {
        
            // lakukan casting
            UserConfig config = (UserConfig) ois.readObject();

            // Terapkan ke UI
            textArea.setFont(new Font("Monospaced", Font.PLAIN, config.getFontsize()));

            JOptionPane.showMessageDialog(this, 
                "Config berhasil dimuat!\nUsername: " + config.getUsername() +
                "\nFont Size: " + config.getFontsize());

        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "File userconfig.bin belum dibuat!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Gagal memuat config: " + ex.getMessage());
        }
    }
    
    private void appendFileTeks() {
    if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
        File file = fileChooser.getSelectedFile();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(textArea.getText());
            writer.newLine(); 
            JOptionPane.showMessageDialog(this, "Text berhasil ditambahkan ke file!");

            // update last_notes.txt
            simpanKeLastNotes();

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Gagal menambahkan text: " + ex.getMessage());
        }
    }
}
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(()-> {
            new AplikasiFileIO().setVisible(true);
        });
    }
}
