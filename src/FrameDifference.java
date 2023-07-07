import processing.core.PApplet;


public class FrameDifference implements PixelFilter {
    private static final short DIFFERENCE_THRESHOLD = 35;
    private DImage previousImg;

    public DImage processImage(DImage img) {
        if (previousImg == null) {
            previousImg = new DImage(img);
            return img; // return it
        }
        short[][] current = img.getBWPixelGrid();
        short[][] previous = previousImg.getBWPixelGrid();
        for (int i = 0; i < current.length; i++) {
            for (int j = 0; j < current[i].length; j++) {

                short difference = (short) Math.abs(previous[i][j] - current[i][j]);

                if(difference>DIFFERENCE_THRESHOLD){
                    difference=255;
                } else {
                    difference = 0;
                }

                current[i][j] = difference;
            }
        }
        previousImg = new DImage(img);
        img.setPixels(current);
        return img;
    }

    @Override
    public void drawOverlay(PApplet window, DImage original, DImage filtered) {

    }


}
