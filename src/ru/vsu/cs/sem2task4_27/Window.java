package ru.vsu.cs.sem2task4_27;

import ru.vsu.cs.sem2task4_27.utils.IOUtils;
import ru.vsu.cs.sem2task4_27.utils.JTableUtils;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;

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
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        int width = dimension.width / 2;
        int height = dimension.height / 2;
        setBounds(width / 2, height / 2, width, height);
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("."));
        FileFilter filter = new FileNameExtensionFilter("Text files", "txt");
        fileChooser.addChoosableFileFilter(filter);
        JTableUtils.initJTableForArray(tableDataArray, 40, false, true, false, true);
        JTableUtils.initJTableForArray(tableOrderArray, 40, false, true, false, true);
        JTableUtils.initJTableForArray(tableOutput, 40, false, true, false, true);
        buttonInputDataArray.addActionListener(e -> {
            try {
                if (fileChooser.showOpenDialog(panel) == JFileChooser.APPROVE_OPTION) {
                    String[] array = IOUtils.getStringArrayFromFile(fileChooser.getSelectedFile().getPath());
                    JTableUtils.writeArrayToJTable(tableDataArray, array);
                }
            } catch (Exception exception) {
                throw new RuntimeException(exception);
            }
        });
        buttonInputOrderArray.addActionListener(e -> {
            try {
                if (fileChooser.showOpenDialog(panel) == JFileChooser.APPROVE_OPTION) {
                    int[] array = IOUtils.getIntArrayFromFile(fileChooser.getSelectedFile().getPath());
                    JTableUtils.writeArrayToJTable(tableOrderArray, array);
                }
            } catch (Exception exception) {
                throw new RuntimeException(exception);
            }
        });
        buttonExecute.addActionListener(e -> {
            String[] sortData = JTableUtils.readStringMatrixFromJTable(tableDataArray)[0];
            int[] sortOrder = JTableUtils.readIntArrayFromJTable(tableOrderArray);
            Sort.sort(sortData, sortOrder);
            JTableUtils.writeArrayToJTable(tableOutput, sortData);
        });
    }
}
