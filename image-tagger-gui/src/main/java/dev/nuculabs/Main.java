package dev.nuculabs;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        //Creating the Frame
        JFrame frame = new JFrame("Image Tagger");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(640, 400);

        //Creating the panel at bottom and adding components
        JTextArea textArea = new JTextArea("The predicted tags for the image will be shown here...");
        textArea.setEditable(false);
        textArea.setColumns(20);
        textArea.setLineWrap(true);
        textArea.setFont(new Font("Serif", Font.BOLD, 24));

        JPanel panel = new JPanel();
        JButton choseImageButton = new JButton("Choose Image");
        JButton loadModelButton = new JButton("Load Model");
        choseImageButton.setEnabled(false);

        panel.add(loadModelButton);
        panel.add(choseImageButton);


        JPanel centerPanel = new JPanel();
        JLabel imageLabel = new JLabel();
        var centerPanelBorderLayout = new BorderLayout();
        centerPanelBorderLayout.setHgap(10);
        centerPanelBorderLayout.setVgap(20);

        centerPanel.setLayout(centerPanelBorderLayout);
        centerPanel.add(imageLabel, BorderLayout.EAST);
        centerPanel.add(textArea, BorderLayout.WEST);

        imageLabel.setSize(224, 224);

        // Adding listeners
        choseImageButton.addActionListener(new ChoseImageListener(imageLabel, textArea));
        loadModelButton.addActionListener(new LoadModelListener(choseImageButton));

        //Adding Components to the frame.
        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        frame.getContentPane().add(BorderLayout.CENTER, centerPanel);
        frame.setVisible(true);
    }
}