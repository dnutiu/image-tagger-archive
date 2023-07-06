package dev.nuculabs;

import javax.swing.*;
import java.awt.*;

class ImageTaggerGUI {

    private JFrame frame;
    private JTextArea textArea;
    private JButton chooseImageButton;
    private JButton loadModelButton;
    private JLabel imageLabel;

    public ImageTaggerGUI() {
        initialize();
    }

    private void initialize() {
        // Creating the Frame
        frame = new JFrame("Image Tagger");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(640, 400);

        // Creating the panel at bottom and adding components
        JPanel bottomPanel = new JPanel();
        chooseImageButton = new JButton("Choose Image");
        loadModelButton = new JButton("Load Model");
        chooseImageButton.setEnabled(false);

        bottomPanel.add(loadModelButton);
        bottomPanel.add(chooseImageButton);

        // Creating the center panel and adding components
        JPanel centerPanel = new JPanel(new BorderLayout());
        textArea = new JTextArea("The predicted tags for the image will be shown here...");
        textArea.setEditable(false);
        textArea.setColumns(20);
        textArea.setLineWrap(true);
        textArea.setFont(new Font("Serif", Font.BOLD, 24));

        imageLabel = new JLabel();
        imageLabel.setPreferredSize(new Dimension(224, 224));

        centerPanel.add(textArea, BorderLayout.WEST);
        centerPanel.add(imageLabel, BorderLayout.EAST);

        // Adding listeners
        chooseImageButton.addActionListener(new ChoseImageListener(imageLabel, textArea));

        loadModelButton.addActionListener(new LoadModelListener(chooseImageButton));

        // Adding components to the frame
        frame.add(bottomPanel, BorderLayout.SOUTH);
        frame.add(centerPanel, BorderLayout.CENTER);

        // Make the frame visible
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ImageTaggerGUI();
            }
        });
    }
}
