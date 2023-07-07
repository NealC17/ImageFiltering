import org.omg.PortableServer.POA;
import processing.core.PApplet;

import java.util.ArrayList;

public class RandomWalk implements PixelFilter {
    private ArrayList<Point> ellipseCoords ;

    public RandomWalk(){
        ellipseCoords = new ArrayList<Point>();
        for (int i = 0; i < 10; i++) {
            ellipseCoords.add(new Point(200,200));
        }

    }

    @Override
    public DImage processImage(DImage img) {
        // we don't change the input image at all!
        return img;
    }

    @Override
    public void drawOverlay(PApplet window, DImage original, DImage filtered) {
        window.fill(255);
        for(Point p: ellipseCoords){
            window.ellipse(p.x, p.y, 10, 10);
            p.takeRandomStep();
        }
    }

    public void drawOverlay(PApplet window) {

    }


    public class Point {
        private int x, y;
        public static final int speed = 3;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public void takeRandomStep() {
            double rand = Math.random();
            if (rand > 0.75) {
                y -= speed;
            } else if (rand > 0.5) {
                y += speed;
            } else if (rand > 0.25) {
                x -= speed;
            } else {
                x += speed;
            }
        }

    }


}
