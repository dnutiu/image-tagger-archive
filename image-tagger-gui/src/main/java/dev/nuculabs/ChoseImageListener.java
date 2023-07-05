package dev.nuculabs;

import ai.onnxruntime.OrtException;
import dev.nuculabs.keyworder.core.ModelInference;
import dev.nuculabs.keyworder.core.Prediction;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class ChoseImageListener implements ActionListener {
    final JFileChooser fileChooser = new JFileChooser();
    private final JTextArea textArea;
    private final JLabel imageLabel;
    static String modelPath = "";
    public ChoseImageListener(JLabel imageLabel, JTextArea textArea) {
        this.textArea = textArea;
        this.imageLabel = imageLabel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            fileChooser.showOpenDialog(null);
            String imagePath = fileChooser.getSelectedFile().getAbsolutePath();

            // Display Image
            var image = ImageIO.read(new File(imagePath));
            this.imageLabel.setIcon(new ImageIcon(image.getScaledInstance(224, 224, Image.SCALE_SMOOTH)));

            ModelInference modelInference = new ModelInference(modelPath);
            var predictions = modelInference.predictKeywordsForImage(imagePath);
            StringBuilder stringBuilder = new StringBuilder();
            for (Prediction prediction : predictions) {
                stringBuilder.append(prediction.label()).append(" ");
            }
            if (predictions.isEmpty()) {
                stringBuilder.append("No predictions found.");
            } else {
                this.textArea.setText(stringBuilder.toString());
            }
        } catch (OrtException | IOException exception) {
            exception.printStackTrace();
            this.textArea.setText("Error while predicting the keywords for the image.");
        }

    }
}
