package gui;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
public class aquarium {
    private JTextField nama_aqua;
    private JTextField umur_aqua;
    private JTextField jenis_aqua;
    private JTextField warna_aqua;
    private JTextField harga_aqua;
    private JButton UPDATEButton;
    private JButton SAVEButton;
    private JButton CANCELButton;
    private JButton DELETEButton;
    private JTable ikan_aqua;
    private JPanel AquariumA;

    public aquarium() {
        koneksi();
        table_aqua();
        SAVEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nama_ikan, umur_ikan, jenis_ikan, warna_ikan, harga_ikan ;
                nama_ikan = nama_aqua.getText();
                umur_ikan = umur_aqua.getText();
                jenis_ikan = jenis_aqua.getText();
                warna_ikan = warna_aqua.getText();
                harga_ikan = harga_aqua.getText();

                try {
                    pst = con.prepareStatement("insert into aquarium(nama_aqua, umur_aqua, jenis_aqua, warna_aqua, harga_aqua)values(?,?,?,?,?)");
                    pst.setString(1, nama_ikan);
                    pst.setString(2, umur_ikan);
                    pst.setString(3, jenis_ikan);
                    pst.setString(4, warna_ikan);
                    pst.setString(5, harga_ikan);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Data berhasil ditambahkan");
                    table_aqua();
                    nama_aqua.setText("");
                    umur_aqua.setText("");
                    jenis_aqua.setText("");
                    warna_aqua.setText("");
                    harga_aqua.setText("");
                }

                catch (SQLException e1)
                {

                    e1.printStackTrace();
                }

            }
        });
        UPDATEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nama, umur, harga;
                nama= nama_aqua.getText();
                umur = umur_aqua.getText();
                harga = harga_aqua.getText();
                try {
                    Statement stt = con.createStatement();
                    stt.executeUpdate("UPDATE aquarium SET umur_aqua = '" +umur+"', harga_aqua='" + harga + "' WHERE nama_aqua = '" + nama + "'");
                    JOptionPane.showMessageDialog(null, "Data Berhasil Diubah");
                    table_aqua();

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Data Gagal Diubah" + ex);
                }
            }
        });
        DELETEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int ok = JOptionPane.showConfirmDialog(null,"Apakah anda yakin akan menghapus? ","Konfirmasi",JOptionPane.YES_NO_OPTION);
                if (ok==0){
                    String sql = "delete from aquarium where nama_aqua ='"+ nama_aqua.getText()+"'";
                    try{
                        PreparedStatement stat = con.prepareStatement(sql);
                        stat.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
                        table_aqua();

                    }catch (SQLException ex){
                        JOptionPane.showMessageDialog(null, "Data Gagal Dihapus"+ ex);
                    }

                }

            }
        });
        CANCELButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                kosongaquarium();
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("aquarium");
        frame.setContentPane(new aquarium().AquariumA);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    protected void kosongaquarium() {
        nama_aqua.setText("");
        umur_aqua.setText("");
        jenis_aqua.setText("");
        warna_aqua.setText("");
        harga_aqua.setText("");
    }
        void table_aqua() {
        try {
            pst = con.prepareStatement("select * from aquarium");
            ResultSet rs = pst.executeQuery();
            ikan_aqua.setModel(DbUtils.resultSetToTableModel(rs));
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
        JFrame JFrame = new JFrame("aquarium");
        JFrame.setContentPane(AquariumA);
        JFrame.pack();
        JFrame.setLocationRelativeTo(null);
        JFrame.setVisible(true);
    }
}
