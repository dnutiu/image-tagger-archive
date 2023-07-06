package dev.nuculabs.pages.main;
import dev.nuculabs.services.ModelPrediction;
import javafx.stage.FileChooser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.File;

public class MainPageController {
    private static final Logger logger = LoggerFactory.getLogger(MainPageController.class);
    private final ModelPrediction modelPrediction = ModelPrediction.getInstance();
    public MainPageController() {
        logger.info("MainPageController created");
    }

    @FXML
    private Label predictedImageTags;

    @FXML
    protected void onLoadImageButtonClick() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        File file = fileChooser.showOpenDialog(null);

        if (file == null) {
            return;
        }

        var predictions = modelPrediction.predictKeywordsForImage(file.getAbsolutePath());
        var predictionsSb = new StringBuilder();
        for (var prediction : predictions) {
            predictionsSb.append(prediction.label());
            predictionsSb.append(", ");
        }

        predictedImageTags.setText(predictionsSb.toString());
    }
}
