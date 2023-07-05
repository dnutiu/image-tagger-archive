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
        ChoseImageListener.modelPath = fileChooser.getSelectedFile().getAbsolutePath();
        this.choseImageButton.setEnabled(true);
    }
}
