package gui;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class kolam2 {
    private JPanel KolamB;
    private JTextField nama_bayikolam;
    private JTextField jenis_bayikolam;
    private JButton SAVEButton;
    private JButton DELETEButton;
    private JTable bayi_kolam;

    public kolam2() {
        koneksi();
        table_kolam2();
        SAVEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nama_bayi, jenis_bayi;
                nama_bayi = nama_bayikolam.getText();
                jenis_bayi = jenis_bayikolam.getText();

                try {
                    pst = con.prepareStatement("insert into kolam2(nama_kolam2, jenis_kolam2)values(?,?)");
                    pst.setString(1, nama_bayi);
                    pst.setString(2, jenis_bayi);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Data berhasil ditambahkan");
                    table_kolam2();
                    nama_bayikolam.setText("");
                    jenis_bayikolam.setText("");
                }

                catch (SQLException e1)
                {

                    e1.printStackTrace();
                }

            }
        });
        DELETEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int ok = JOptionPane.showConfirmDialog(null,"Apakah anda yakin akan menghapus? ","Konfirmasi",JOptionPane.YES_NO_OPTION);
                if (ok==0){
                    String sql = "delete from kolam2 where nama_kolam2 ='"+ nama_bayikolam.getText()+"'";
                    try{
                        PreparedStatement stat = con.prepareStatement(sql);
                        stat.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
                        table_kolam2();

                    }catch (SQLException ex){
                        JOptionPane.showMessageDialog(null, "Data Gagal Dihapus"+ ex);
                    }

                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("kolam2");
        frame.setContentPane(new kolam2().KolamB);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    void table_kolam2() {
        try {
            pst = con.prepareStatement("select * from kolam2");
            ResultSet rs = pst.executeQuery();
            bayi_kolam.setModel(DbUtils.resultSetToTableModel(rs));
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
        JFrame JFrame = new JFrame("kolam2");
        JFrame.setContentPane(KolamB);
        JFrame.pack();
        JFrame.setLocationRelativeTo(null);
        JFrame.setVisible(true);
    }
}
