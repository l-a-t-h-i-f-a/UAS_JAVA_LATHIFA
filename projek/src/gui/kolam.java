package gui;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
public class kolam {
    private JTextField umur_kolam;
    private JTextField nama_kolam;
    private JTextField jenis_kolam;
    private JTextField warna_kolam;
    private JPanel kolamA;
    private JTextField harga_kolam;
    private JButton SAVE;
    private JButton UPDATE;
    private JButton DELETE;
    private JButton CANCEL;
    private JTable ikan_kolam;

    public kolam() {
        koneksi();
        table_kolam();
        SAVE.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nama_ikan, umur_ikan, jenis_ikan, warna_ikan, harga_ikan ;
                nama_ikan = nama_kolam.getText();
                umur_ikan = umur_kolam.getText();
                jenis_ikan = jenis_kolam.getText();
                warna_ikan = warna_kolam.getText();
                harga_ikan = harga_kolam.getText();
                try {
                    pst = con.prepareStatement("insert into kolam(nama_kolam, umur_kolam, jenis_kolam, warna_kolam, harga_kolam)values(?,?,?,?,?)");
                    pst.setString(1, nama_ikan);
                    pst.setString(2, umur_ikan);
                    pst.setString(3, jenis_ikan);
                    pst.setString(4, warna_ikan);
                    pst.setString(5, harga_ikan);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Data berhasil ditambahkan");
                    table_kolam();
                    nama_kolam.setText("");
                    umur_kolam.setText("");
                    jenis_kolam.setText("");
                    warna_kolam.setText("");
                    harga_kolam.setText("");
                }
                catch (SQLException e1)
                {

                    e1.printStackTrace();
                }
            }
        });
        UPDATE.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nama, umur, harga;
                nama= nama_kolam.getText();
                umur = umur_kolam.getText();
                harga = harga_kolam.getText();
                try {
                    Statement stt = con.createStatement();
                    stt.executeUpdate("UPDATE kolam SET umur_kolam = '" +umur+"', harga_kolam='" + harga + "' WHERE nama_kolam = '" + nama + "'");
                    JOptionPane.showMessageDialog(null, "Data Berhasil Diubah");
                    table_kolam();

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Data Gagal Diubah" + ex);
                }
            }
        });
        DELETE.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {int ok = JOptionPane.showConfirmDialog(null,"Apakah anda yakin akan menghapus? ","Konfirmasi",JOptionPane.YES_NO_OPTION);
                if (ok==0){
                    String sql = "delete from kolam where nama_kolam ='"+ nama_kolam.getText()+"'";
                    try{
                        PreparedStatement stat = con.prepareStatement(sql);
                        stat.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
                        table_kolam();

                    }catch (SQLException ex){
                        JOptionPane.showMessageDialog(null, "Data Gagal Dihapus"+ ex);
                    }
                }
            }
        });
        CANCEL.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                kosongkolam();
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("kolam");
        frame.setContentPane(new kolam().kolamA);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    protected void kosongkolam() {
        nama_kolam.setText("");
        umur_kolam.setText("");
        jenis_kolam.setText("");
        warna_kolam.setText("");
        harga_kolam.setText("");

    }
    void table_kolam() {
        try {
            pst = con.prepareStatement("select * from kolam");
            ResultSet rs = pst.executeQuery();
            ikan_kolam.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    Connection con;
    PreparedStatement pst;

    public void koneksi()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/ikan", "root","");
            System.out.println("Successs");
        }
        catch (ClassNotFoundException ex)
        {
            ex.printStackTrace();

        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }
    public void createLayout(){
        JFrame JFrame = new JFrame("kolam");
        JFrame.setContentPane(kolamA);
        JFrame.pack();
        JFrame.setLocationRelativeTo(null);
        JFrame.setVisible(true);
    }
}
