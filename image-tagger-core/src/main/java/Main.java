import ai.onnxruntime.OrtException;
import dev.nuculabs.keyworder.core.ModelInference;
import dev.nuculabs.keyworder.core.Prediction;

public class Main {
    static String modelPath = "C:\\Users\\nutiu\\Downloads\\resnet50_10_epochs.onnx";
    static String imagePath = "C:\\Users\\nutiu\\Downloads\\9.jpg";

    public static void main(String[] args) throws OrtException {
        System.out.println("Onnx Runtime Java Image Prediction");

        ModelInference modelInference = new ModelInference(modelPath);
        var predictions = modelInference.predictKeywordsForImage(imagePath);
        for (Prediction prediction : predictions) {
            System.out.println(prediction.label() + " " + prediction.confidence());
        }
    }
}