package dev.nuculabs.services;


import ai.onnxruntime.OrtException;
import dev.nuculabs.keyworder.ModelInference;
import dev.nuculabs.keyworder.Prediction;

import java.util.List;

public class ModelPrediction {

    private static ModelPrediction Instance = null;

    private final ModelInference modelInference;

    private ModelPrediction() {
        modelInference = new ModelInference();
    }

    public List<Prediction> predictKeywordsForImage(String imagePath) {
        try {
            return modelInference.predictKeywordsForImage(imagePath);
        } catch (OrtException e) {
            throw new RuntimeException(e);
        }
    }

    public static synchronized ModelPrediction getInstance() {
        if (Instance == null) {
            Instance = new ModelPrediction();
        }

        return Instance;
    }
}