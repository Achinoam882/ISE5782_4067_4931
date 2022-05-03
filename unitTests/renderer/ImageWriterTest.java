package renderer;

import org.junit.jupiter.api.Test;
import primitives.Color;

import static org.junit.jupiter.api.Assertions.*;

class ImageWriterTest {

    @Test
    void testWriteToImage() {
        int nX = 800;
        int nY = 500;

        int interval = 50; // 800/50 == 16  500/50 == 10

        Color yellowColor = new Color(255d,255d,0d);
        Color redColor = new Color(255d,0,0d);

        ImageWriter imageWriter = new ImageWriter("YellowSubmarine",nX,nY);
        for (int i = 0; i < nX; i++) {
            for (int j = 0; j < nY; j++) {
                if(i % interval == 0 || j % interval == 0) {
                    imageWriter.writePixel(i, j, redColor);
                }
                else {
                    imageWriter.writePixel(i,j,yellowColor);
                }
            }
        }
        imageWriter.writeToImage();
    }
}