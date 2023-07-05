package dev.nuculabs;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoadModelListener implements ActionListener {
    final JFileChooser fileChooser = new JFileChooser();
    final JButton choseImageButton;

    public LoadModelListener(JButton choseImageButton) {
        this.choseImageButton = choseImageButton;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        fileChooser.showOpenDialog(null);
        String modelPath = fileChooser.getSelectedFile().getAbsolutePath();
        ChoseImageListener.modelPath = modelPath;
        this.choseImageButton.setEnabled(true);
    }
}
