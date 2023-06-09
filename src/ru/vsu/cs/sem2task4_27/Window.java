package ru.vsu.cs.sem2task4_27;

import ru.vsu.cs.sem2task4_27.utils.IOUtils;
import ru.vsu.cs.sem2task4_27.utils.JTableUtils;
import ru.vsu.cs.sem2task4_27.utils.SwingUtils;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;

public class Window extends JFrame {
    private JButton buttonInputDataArray;
    private JButton buttonInputOrderArray;
    private JTable tableDataArray;
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
        JTableUtils.initJTableForArray(tableOutput, 40, false, true, false, true);
        buttonInputDataArray.addActionListener(e -> {
            try {
                if (fileChooser.showOpenDialog(panel) == JFileChooser.APPROVE_OPTION) {
                    String[] array = IOUtils.getStringArrayFromFile(fileChooser.getSelectedFile().getPath());
                    String[][] tableArr = JTableUtils.readStringMatrixFromJTable(tableDataArray);
                    tableArr[0] = array;
                    JTableUtils.writeArrayToJTable(tableDataArray, tableArr);
                }
            } catch (Exception exception) {
                throw new RuntimeException(exception);
            }
        });
        buttonInputOrderArray.addActionListener(e -> {
            try {
                if (fileChooser.showOpenDialog(panel) == JFileChooser.APPROVE_OPTION) {
                    String[] array = IOUtils.getStringArrayFromFile(fileChooser.getSelectedFile().getPath());
                    String[][] tableArr = new String[2][array.length];
                    tableArr[0] = JTableUtils.readStringMatrixFromJTable(tableDataArray)[0];
                    tableArr[1] = array;
                    JTableUtils.writeArrayToJTable(tableDataArray, tableArr);
                }
            } catch (Exception exception) {
                throw new RuntimeException(exception);
            }
        });
        buttonExecute.addActionListener(e -> {
            try {
                String[] sortData = JTableUtils.readStringMatrixFromJTable(tableDataArray)[0];
                String[] sortOrderDefault = JTableUtils.readStringMatrixFromJTable(tableDataArray)[1];
                if (sortData.length != sortOrderDefault.length) {
                    throw new Exception();
                } else {
                    int[] sortOrder = new int[sortOrderDefault.length];
                    for (int i = 0; i < sortOrderDefault.length; i++) {
                        sortOrder[i] = Integer.parseInt(sortOrderDefault[i]);
                        if ((sortData[i].length() == 0) ||
                                (sortData[i].length() == 0 && sortOrderDefault[i].length() == 0)) {
                            throw new Exception();
                        }
                    }
                    Sort.sort(sortData, sortOrder);
                    JTableUtils.writeArrayToJTable(tableOutput, sortData);
                }
            } catch (Exception exception) {
                SwingUtils.showInfoMessageBox("Длина массива данных должны быть равна длине массива порядка!", "Ошибка");
            }

        });
    }
}
