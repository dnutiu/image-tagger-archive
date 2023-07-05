package dev.nuculabs;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        //Creating the Frame
        JFrame frame = new JFrame("Image Tagger");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(640, 400);

        //Creating the panel at bottom and adding components
        JTextArea textArea = new JTextArea("The predicted tags for the image will be shown here...");

        JPanel panel = new JPanel();
        JButton choseImageButton = new JButton("Choose Image");
        JButton loadModelButton = new JButton("Load Model");
        choseImageButton.addActionListener(new ChoseImageListener(textArea));
        loadModelButton.addActionListener(new LoadModelListener(choseImageButton));
        choseImageButton.setEnabled(false);

        panel.add(loadModelButton);
        panel.add(choseImageButton);


        //Adding Components to the frame.
        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        frame.getContentPane().add(BorderLayout.CENTER, textArea);
        frame.setVisible(true);
    }
}