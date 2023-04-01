package com.example.pairEmployees.beans;

import com.example.pairEmployees.api.PairEmployeesDao;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class FileChooser extends JFrame implements ActionListener {

    private final JButton button;

    public FileChooser() {
        setTitle("File Chooser");
        setSize(500, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTextField textField = new JTextField();
        button = new JButton("Choose File");
        button.addActionListener(this);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(textField, BorderLayout.CENTER);
        panel.add(button, BorderLayout.EAST);

        add(panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {

//            JDialog dialog = new JDialog(this, "Choose a file", true);

            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new FileNameExtensionFilter("CSV Files", "csv"));
            int response = fileChooser.showOpenDialog(this);
            if (response == JFileChooser.APPROVE_OPTION) {
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                PairEmployeesDao pairEmployeesDao = new PairEmployeesDao();
                pairEmployeesDao.getPairedEmpl(file);
//                dialog.dispose();
                try {
                    Desktop.getDesktop().browse(new URI("http://localhost:8080/emp"));
                } catch (IOException | URISyntaxException ex) {
                    ex.printStackTrace();
                }

            }

        }

    }

}
