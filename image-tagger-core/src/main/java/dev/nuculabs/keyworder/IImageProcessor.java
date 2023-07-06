package dev.nuculabs.keyworder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public interface IImageProcessor {
    /**
     * Read image from path.
     *
     * @param path Path to image
     * @return BufferedImage a buffered image.
     * @throws IOException If image cannot be read
     */
    default BufferedImage readImage(String path) throws IOException {
        File imageFile = new File(path);
        return ImageIO.read(imageFile);
    }

    BufferedImage cropImage(BufferedImage bufferedImage);

    float[][][][] processImage(String imagePath);
}
