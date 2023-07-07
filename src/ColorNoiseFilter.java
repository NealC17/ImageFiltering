import processing.core.PApplet;

public class ColorNoiseFilter implements PixelFilter{

    private static final double PROB_TO_ADD = 0.25;

    public DImage processImage(DImage img) {
        short[][] red = img.getRedChannel();
        short[][] green = img.getGreenChannel();
        short[][] blue = img.getBlueChannel();

        for (int i = 0; i < red.length; i++) {
            for (int j = 0; j < red[i].length; j++) {

                if(Math.random()<PROB_TO_ADD){
                    red[i][j] = getRandom(255);
                    green[i][j] = getRandom(255);
                    blue[i][j] = getRandom(255);
                }

            }
            
        }

        
        img.setRedChannel(red);
        img.setGreenChannel(green);
        img.setBlueChannel(blue);
        return img;
    }

    private short getRandom(int maxNum) {
        return (short)(Math.random()*(maxNum+1));
    }

    @Override
    public void drawOverlay(PApplet window, DImage original, DImage filtered) {
        
    }
}
