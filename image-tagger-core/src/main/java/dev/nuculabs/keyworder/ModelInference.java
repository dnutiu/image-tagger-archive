package dev.nuculabs.keyworder;

import ai.onnxruntime.OnnxTensor;
import ai.onnxruntime.OrtEnvironment;
import ai.onnxruntime.OrtException;
import ai.onnxruntime.OrtSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Class that handles the inference of the model
 */
public class ModelInference {
    /**
     * Image processor used to process the image before inference.
     */
    public IImageProcessor imageProcessor = new ImageProcessor();

    private final OrtEnvironment environment;
    private final OrtSession session;

    private final String inputName;
    private final String outputName;


    /**
     * Constructor
     */
    public ModelInference() {
        try {
            var modelStream = ModelInference.class.getResourceAsStream("/model/resnet50.onnx");
            Objects.requireNonNull(modelStream);

            environment = OrtEnvironment.getEnvironment();
            var sessionOptions = new OrtSession.SessionOptions();
            session = environment.createSession(modelStream.readAllBytes(), sessionOptions);

            inputName = session.getInputNames().iterator().next();
            outputName = session.getOutputNames().iterator().next();
        } catch (OrtException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Predicts the keywords for the given image
     * @param imagePath - path to the image
     * @param threshold - threshold for the predictions
     * @return  List<Prediction> - list of predictions
     * @throws OrtException - exception thrown by the inference
     */
    public List<Prediction> predictKeywordsForImage(String imagePath, float threshold) throws OrtException {
        // Create input tensor
        OnnxTensor inputTensor = OnnxTensor.createTensor(environment, imageProcessor.processImage(imagePath));
        var inputs = Map.of(inputName, inputTensor);
        // Run the model.
        var results = session.run(inputs);
        // Get output tensor
        var outputTensor = results.get(outputName);
        if (outputTensor.isPresent()) {
            float[][] floatBuffer = (float[][]) outputTensor.get().getValue();
            ArrayList<Prediction> predictions = new ArrayList<>();
            // collect predictions
            for (int i = 0; i < floatBuffer[0].length; i++) {
                if (floatBuffer[0][i] > threshold) {
                    predictions.add(new Prediction(Keywords.KEYWORDS.get(i), floatBuffer[0][i]));
                }
            }
            return predictions;
        } else {
            return List.of();
        }
    }

    /**
     * Predicts the keywords for the given image
     * @param imagePath - path to the image
     * @return List<Prediction> - list of predictions
     * @throws OrtException - exception thrown by the inference
     */
    public List<Prediction> predictKeywordsForImage(String imagePath) throws OrtException {
        return this.predictKeywordsForImage(imagePath, -1.11f);
    }
}
