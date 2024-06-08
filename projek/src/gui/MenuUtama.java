package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuUtama {
    private JButton AQUARIUM;
    private JButton AQUARIUM2;
    private JPanel MenuUtama;
    private JButton KOLAM;
    private JButton KOLAM2;

    public MenuUtama() {
        AQUARIUM.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aquarium aqua = new aquarium();
                aqua.createLayout();
            }
        });
        AQUARIUM2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aquarium2 aquaa = new aquarium2();
                aquaa.createLayout();
            }
        });
        KOLAM.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                kolam kol = new kolam();
                kol.createLayout();
            }
        });
        KOLAM2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                kolam2 koll = new kolam2();
                koll.createLayout();

            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("MenuUtama");
        frame.setContentPane(new MenuUtama().MenuUtama);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}
