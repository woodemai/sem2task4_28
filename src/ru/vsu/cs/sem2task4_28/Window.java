package ru.vsu.cs.sem2task4_28;

import ru.vsu.cs.sem2task4_28.utils.IOUtils;
import ru.vsu.cs.sem2task4_28.utils.JTableUtils;
import ru.vsu.cs.sem2task4_28.utils.SwingUtils;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;

public class Window extends JFrame {
    private JButton buttonInput;
    private JTable tableInput;
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
        JTableUtils.initJTableForArray(tableInput, 40, false, true, false, true);
        JTableUtils.initJTableForArray(tableOutput, 40, false, true, false, true);
        buttonInput.addActionListener(e -> {
//            try {
//                if (fileChooser.showOpenDialog(panel) == JFileChooser.APPROVE_OPTION) {
//                    String[] array = IOUtils.getStringArrayFromFile(fileChooser.getSelectedFile().getPath());
//                    String[][] tableArr = JTableUtils.readStringMatrixFromJTable(tableInput);
//                    tableArr[0] = array;
//                    JTableUtils.writeArrayToJTable(tableInput, tableArr);
//                }
//            } catch (Exception exception) {
//                throw new RuntimeException(exception);
//            }
            try {
                if (fileChooser.showOpenDialog(panel) == JFileChooser.APPROVE_OPTION) {
                    String[][] array = IOUtils.getStringArrayFromFile(fileChooser.getSelectedFile().getPath());
                    JTableUtils.writeArrayToJTable(tableInput, array);
                }

            } catch (Exception exception) {
                SwingUtils.showInfoMessageBox("Ошибка при загрузки данных", "Ошибка");
                throw new RuntimeException(exception);
            }
        });
//        buttonInputOrderArray.addActionListener(e -> {
//            try {
//                if (fileChooser.showOpenDialog(panel) == JFileChooser.APPROVE_OPTION) {
//                    String[] array = IOUtils.getStringArrayFromFile(fileChooser.getSelectedFile().getPath());
//                    String[][] tableArr = new String[2][array.length];
//                    tableArr[0] = JTableUtils.readStringMatrixFromJTable(tableInput)[0];
//                    tableArr[1] = array;
//                    JTableUtils.writeArrayToJTable(tableInput, tableArr);
//                }
//            } catch (Exception exception) {
//                throw new RuntimeException(exception);
//            }
//        });
        buttonExecute.addActionListener(e -> {
            try {
                String[][] array = JTableUtils.readStringMatrixFromJTable(tableInput);
                if (array.length != 2) {
                    throw new Exception();
                } else {
                    int[] orderArray = new int[array[0].length];
                    for (int i = 0; i < array[0].length; i++) {
                        if ((array[0][i].equals(""))) {
                            throw new Exception("empty");
                        } else {
                            orderArray[i] = Integer.parseInt(array[0][i]);
                        }
                    }
                    Sort.sort(array[1], orderArray);
                    for (int i = 0; i < array[0].length; i++) {
                        array[0][i] = String.valueOf(orderArray[i]);
                    }
                }
                JTableUtils.writeArrayToJTable(tableOutput, array);

            } catch (Exception exception) {
                if (exception.getMessage().equals("empty")) {
                    SwingUtils.showInfoMessageBox("Не должно быть пустых ячеек!", "Ошибка");

                } else {
                    SwingUtils.showInfoMessageBox("Длина массива данных должны быть равна длине массива порядка!", "Ошибка");
                }
            }


        });
    }
}
