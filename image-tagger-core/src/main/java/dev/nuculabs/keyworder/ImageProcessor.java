package dev.nuculabs.keyworder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Image processor for image classification.
 */
public class ImageProcessor implements IImageProcessor {
    /**
     * Mean for each color channel.
     */
    private final float[] mean = new float[]{0.485f, 0.456f, 0.406f};
    /**
     * Standard deviation for each color channel.
     */
    private final float[] standardDeviation = new float[]{0.229f, 0.224f, 0.225f};
    // Debug flag
    public boolean debug = false;

    /**
     * Crop image to preserve aspect ratio.
     *
     * @param bufferedImage Image to center crop
     * @return Center cropped image
     */
    @Override
    public BufferedImage cropImage(BufferedImage bufferedImage) {
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();
        int startX = 0;
        int startY = 0;
        if (width > height) {
            startX = (width - height) / 2;
            //noinspection ReassignedVariable,SuspiciousNameCombination
            width = height;
        } else {
            startY = (height - width) / 2;
            //noinspection ReassignedVariable,SuspiciousNameCombination
            height = width;
        }
        bufferedImage = bufferedImage.getSubimage(startX, startY, width, height);
        return bufferedImage;
    }

    /**
     * Process image for classification.
     *
     * @param imagePath - Path to image
     * @return float1][3][224][224] - Image tensor
     */
    @Override
    public float[][][][] processImage(String imagePath) {
        try {
            float[][][][] tensorData = new float[1][3][224][224];
            // Read image
            BufferedImage bufferedImage = this.readImage(imagePath);

            // Center crop image
            bufferedImage = this.cropImage(bufferedImage);
            if (debug) {
                ImageIO.write(bufferedImage, "jpg", new File("./debug-cropped.jpg"));
            }

            // Resize image
            var resizedImage = bufferedImage.getScaledInstance(224, 224, 4);

            // Process image
            BufferedImage scaledImage = new BufferedImage(224, 224, BufferedImage.TYPE_4BYTE_ABGR);
            scaledImage.getGraphics().drawImage(resizedImage, 0, 0, null);
            for (var y = 0; y < scaledImage.getHeight(); y++) {
                for (var x = 0; x < scaledImage.getWidth(); x++) {
                    int pixel = scaledImage.getRGB(x, y);
                    // Get RGB values
                    tensorData[0][0][y][x] = (((pixel >> 16) & 0xFF) / 255f - mean[0]) / standardDeviation[0];
                    tensorData[0][1][y][x] = (((pixel >> 16) & 0xFF) / 255f - mean[1]) / standardDeviation[1];
                    tensorData[0][2][y][x] = (((pixel >> 16) & 0xFF) / 255f - mean[2]) / standardDeviation[2];
                }
            }
            return tensorData;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
