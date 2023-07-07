package dev.nuculabs.controls;

import dev.nuculabs.keyworder.Prediction;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.image.Image;

import java.io.IOException;
import java.util.List;

/**
 * This class is used to create a custom control for the image prediction entry.
 */
public class ImagePredictionEntry extends HBox {

    /**
     * The image view for the image prediction entry.
     */
    @FXML
    private ImageView imageView;

    /**
     * The text area for the image prediction entry.
     */
    @FXML
    private TextArea predictedImageTags;

    /**
     * Constructor for the image prediction entry.
     *
     * @param imagePath   The image path.
     * @param predictions The prediction list.
     */
    public ImagePredictionEntry(String imagePath, List<Prediction> predictions) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/components/image_prediction_entry.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        setImage(imagePath);
        setText(predictions);
    }

    /**
     * Getter for the image view.
     *
     * @param predictions The prediction list.
     */
    public void setText(List<Prediction> predictions) {
        var predictionsSb = new StringBuilder();
        for (var prediction : predictions) {
            predictionsSb.append(prediction.label());
            predictionsSb.append(", ");
        }
        predictedImageTags.setText(predictionsSb.toString());
    }

    /**
     * Setter for setting the image.
     */
    public void setImage(String imagePath) {
        imageView.setImage(new Image(imagePath));
        imageView.resize(244, 244);
        imageView.setFitHeight(244);
        imageView.setFitWidth(244);
        imageView.setSmooth(true);
        imageView.setCache(true);
    }
}
