import processing.core.PApplet;

public class RemoveRedFilter implements PixelFilter {

    public DImage processImage(DImage img) {
        short[][] blackPixels = new short[img.getHeight()][img.getWidth()];

        for (int i = 0; i < blackPixels.length; i++) {
            for (int j = 0; j < blackPixels[i][j]; j++) {
                blackPixels[i][j] = (short) (100);
            }
        }

        img.setRedChannel(blackPixels);
        return img;
    }

    @Override
    public void drawOverlay(PApplet window, DImage original, DImage filtered) {

    }
}
