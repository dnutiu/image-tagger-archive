package dev.nuculabs.pages.main;
import dev.nuculabs.controls.ImagePredictionEntry;
import dev.nuculabs.services.ModelPrediction;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.fxml.FXML;

import javax.swing.filechooser.FileNameExtensionFilter;

public class MainPageController {
    private static final Logger logger = LoggerFactory.getLogger(MainPageController.class);
    private final ModelPrediction modelPrediction = ModelPrediction.getInstance();

    @FXML
    private VBox verticalBox;

    @FXML
    private Label statusLabel;

    public MainPageController() {
        logger.info("MainPageController created");
    }

    @FXML
    protected void onLoadImageButtonClick() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose images");
        var files = fileChooser.showOpenMultipleDialog(null);
        if (files == null) {
            return;
        }

        statusLabel.setVisible(true);

        // Create a new thread to predict the images.
        var thread = new Thread(() -> {
            try {
                for (var file : files) {
                    // Get predictions for the image.
                    var predictions = modelPrediction.predictKeywordsForImage(file.getAbsolutePath());
                    Platform.runLater(() -> {
                        // Add image and prediction to the view.
                        var children = verticalBox.getChildren();
                        children.add(new ImagePredictionEntry(file.getAbsolutePath(), predictions));
                        children.add(new Separator());
                    });
                }
                Platform.runLater(() -> {
                    statusLabel.setVisible(false);
                });
            } catch (Exception e) {
                logger.error("Error while predicting images", e);
            }
        });
        thread.start();
    }
}
