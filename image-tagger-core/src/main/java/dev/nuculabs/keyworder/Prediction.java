package dev.nuculabs.keyworder;

/**
 * Prediction record.
 * @param label - The predicted label.
 * @param confidence - The confidence of the prediction.
 */
public record Prediction(String label, float confidence) {
}
