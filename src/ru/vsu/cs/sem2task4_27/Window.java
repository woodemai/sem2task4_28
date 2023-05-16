package ru.vsu.cs.sem2task4_27;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window extends JFrame {
    private JButton buttonInputDataArray;
    private JButton buttonInputOrderArray;
    private JTable tableDataArray;
    private JTable tableOrderArray;
    private JButton buttonExecute;
    private JTable tableOutput;
    private JPanel panel;

    public Window() {
        setTitle("sem2task4_27");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(panel);
        setVisible(true);
        buttonInputDataArray.addActionListener(e -> {

        });
        buttonInputOrderArray.addActionListener(e -> {

        });
        buttonExecute.addActionListener(e -> {

        });
    }
}
