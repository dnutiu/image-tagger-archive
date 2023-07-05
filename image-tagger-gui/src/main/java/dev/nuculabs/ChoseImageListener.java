package dev.nuculabs;

import ai.onnxruntime.OrtException;
import dev.nuculabs.keyworder.core.ModelInference;
import dev.nuculabs.keyworder.core.Prediction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChoseImageListener implements ActionListener {
    final JFileChooser fileChooser = new JFileChooser();
    private final JTextArea textArea;
    static String modelPath = "";
    public ChoseImageListener(JTextArea textArea) {
        this.textArea = textArea;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            fileChooser.showOpenDialog(null);
            String imagePath = fileChooser.getSelectedFile().getAbsolutePath();

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
        } catch (OrtException exception) {
            exception.printStackTrace();
            this.textArea.setText("Error while predicting the keywords for the image.");
        }

    }
}
