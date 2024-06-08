package gui;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
public class aquarium2 {
    private JPanel AquariumB;
    private JTextField nama_bayiaqua;
    private JButton SAVE;
    private JButton DELETE;
    private JTable bayi_aqua;
    private JTextField jenis_bayiaqua;

    public aquarium2() {
        koneksi();
        table_aqua2();
        SAVE.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nama_bayi, jenis_bayi;
                nama_bayi = nama_bayiaqua.getText();
                jenis_bayi = jenis_bayiaqua.getText();

                try {
                    pst = con.prepareStatement("insert into aquarium2(nama_aqua2, jenis_aqua2)values(?,?)");
                    pst.setString(1, nama_bayi);
                    pst.setString(2, jenis_bayi);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Data berhasil ditambahkan");
                    table_aqua2();
                    nama_bayiaqua.setText("");
                    jenis_bayiaqua.setText("");
                } catch (SQLException e1) {

                    e1.printStackTrace();
                }

            }
        });
        DELETE.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int ok = JOptionPane.showConfirmDialog(null,"Apakah anda yakin akan menghapus? ","Konfirmasi",JOptionPane.YES_NO_OPTION);
                if (ok==0){
                    String sql = "delete from aquarium2 where nama_aqua2 ='"+ nama_bayiaqua.getText()+"'";
                    try{
                        PreparedStatement stat = con.prepareStatement(sql);
                        stat.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
                        table_aqua2();

                    }catch (SQLException ex){
                        JOptionPane.showMessageDialog(null, "Data Gagal Dihapus"+ ex);
                    }

                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("aquarium2");
        frame.setContentPane(new aquarium2().AquariumB);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public void table_aqua2() {
        try {
            pst = con.prepareStatement("select * from aquarium2");
            ResultSet rs = pst.executeQuery();
            bayi_aqua.setModel(DbUtils.resultSetToTableModel(rs));
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
        JFrame JFrame = new JFrame("aquarium2");
        JFrame.setContentPane(AquariumB);
        JFrame.pack();
        JFrame.setLocationRelativeTo(null);
        JFrame.setVisible(true);
    }
}
