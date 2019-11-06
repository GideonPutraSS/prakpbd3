/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tugaspbd;

import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author david
 */
public class Tugaspbd {

    static final String Jdbc_driver = "com.mysql.jdbc.Driver";
    static final String root = "jdbc:mysql://localhost/mahasiswa";
    static final String username = "root";
    static final String password = "";

    static Connection con;
    static Statement st;
    static ResultSet rs;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        koneksi();
        try {
            if (login()) {
               insertdata();
            } else {
                System.out.println("salah");
            }
        } catch (Exception e) {
            e.getMessage();
        }
        
    }

    static void koneksi() {
        try {
            Class.forName(Jdbc_driver);
            con = DriverManager.getConnection(root, username, password);
            st = con.createStatement();
        } catch (Exception e) {
            e.getMessage();
        }
    }

    static boolean login() {
        boolean cek;
        try {
            cek = false;
            String user = JOptionPane.showInputDialog("username");
            String pass = JOptionPane.showInputDialog("password");
            String sql = "select * from pegawai where id_pegawai='" + user + "'and nama_pegawai = '" + pass + "'";
            rs = st.executeQuery(sql);
            while (rs.next()) {
                cek = true;
            }
            return cek;
        } catch (Exception e) {
            cek = false;
            return cek;
        }
    }

    static void tampildata() {
        try {
            String sql = "select * from pegawai";
            rs = st.executeQuery(sql);
            while (rs.next()) {
                System.out.print(rs.getString(1) + " | ");
                System.out.print(rs.getString(2) + " | ");
                System.out.print(rs.getString(3) + " | ");
                System.out.print(rs.getString(4) + " | ");
                System.out.print(rs.getString(5) + " | ");
                System.out.println(rs.getString(6));
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }

    static void insertdata() {

        String id_pegawai = JOptionPane.showInputDialog("id_pegawai");
        String  nama_pegawai= JOptionPane.showInputDialog("nama_pegawai");
        String jenis_kelamin = JOptionPane.showInputDialog("jenis_kelamin");
        String umur = JOptionPane.showInputDialog("umur");
        String alamat = JOptionPane.showInputDialog("alamat");
        String gaji_pegawai = JOptionPane.showInputDialog("gaji_pegawai");
        String sql = "insert into pegawai values ('" + id_pegawai + "','" + nama_pegawai + "','" +jenis_kelamin+ "','" +umur+ "','" +alamat+ "','" +gaji_pegawai+ "')";
        try {
            st.executeUpdate(sql);
            tampildata();
            
        } catch (Exception e) {
            e.getMessage();
        }
    }
}
