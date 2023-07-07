import processing.core.PApplet;

public class MouseFilter implements PixelFilter {


    private static final int COLOR_THRESHOLD = 240;
    private static final short DIFFERENCE_THRESHOLD = 15;
    private DImage previousImg;

    private double[][] kernel = {{1/25.0,1/25.0,1/25.0,1/25.0,1/25.0},
                                {1/25.0,1/25.0,1/25.0,1/25.0,1/25.0},
                                {1/25.0,1/25.0,1/25.0,1/25.0,1/25.0},
                                {1/25.0,1/25.0,1/25.0,1/25.0,1/25.0},
                                {1/25.0,1/25.0,1/25.0,1/25.0,1/25.0}};

    @Override
    public DImage processImage(DImage img) {

        DImage frameDiff = frameDifference(img);
        DImage blurred = blur(frameDiff);
        return blurred;
    }

    public DImage blur(DImage img) {
        int width = img.getWidth(), height = img.getHeight();
        short[][] im = img.getBWPixelGrid();
        short[][] newImage = new short[height][width];


        for(int r = 1; r < im.length-2; r ++){
            for(int c= 1; c < im[0].length-2;c++){
                short out = getNewWeight(im, r, c);
                if(out<0)out=0;
                newImage[r][c]= out;
            }
        }

        DImage out = new DImage(img);
        out.setPixels(newImage);
        return out;
    }

    private short getNewWeight(short[][] im, int r, int c) {
        short output = 0;
        for (int rowOffSet = -kernel.length/2; rowOffSet < 1+kernel.length/2; rowOffSet++) {
            for(int colOffSet = -kernel.length/2;colOffSet<1+kernel.length/2;colOffSet++){
                output+=kernel[rowOffSet+kernel.length/2][colOffSet+kernel.length/2]*im[r+rowOffSet][c+colOffSet];
            }


        }
        return output;
    }

    private DImage frameDifference(DImage img) {
        if (previousImg == null) {
            previousImg = new DImage(img);
            return img; // return it
        }
        short[][] current = img.getBWPixelGrid();
        short[][] previous = previousImg.getBWPixelGrid();
        for (int i = 0; i < current.length; i++) {
            for (int j = 0; j < current[i].length; j++) {

                short difference = (short) Math.abs(previous[i][j] - current[i][j]);

                if (difference > DIFFERENCE_THRESHOLD) {
                    difference = 255;
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
