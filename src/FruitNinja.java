import javafx.scene.shape.Circle;
import processing.core.PApplet;
import processing.core.PConstants;

import java.util.ArrayList;

public class FruitNinja implements PixelFilter{
    private static final short DIFFERENCE_THRESHOLD = 35;
    private static final double CIRCLE_SPAWN_RATE = 0.1;
    private DImage previousImg;
    private ArrayList<Circle> fruit = new ArrayList<Circle>();
    private int score;

    public DImage processImage(DImage img) {
        if (previousImg == null) {
            previousImg = new DImage(img);
            return img;
        }

        short[][] current = img.getBWPixelGrid();
        short[][] previous = previousImg.getBWPixelGrid();
        for (int i = 0; i < current.length; i++) {
            for (int j = 0; j < current[i].length; j++) {

                short difference = (short) Math.abs(previous[i][j] - current[i][j]);

                if(difference>DIFFERENCE_THRESHOLD){
                    difference=255;
                    isFruitHit(j, i);
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

    private void isFruitHit(int x, int y) {
        Circle c;
        for(int i = 0; i < fruit.size();i++){
            c = fruit.get(i);
            if(c != null && c.isHit(x,y)){
                score++;
                fruit.remove(i);
                i--;
            }
        }
    }

    @Override
    public void drawOverlay(PApplet window, DImage original, DImage filtered) {
        if(Math.random()<CIRCLE_SPAWN_RATE){
            fruit.add(new Circle((int)(Math.random()*original.getWidth()),(int)(Math.random()*original.getHeight())));
        }

        Circle c;
        for(int i = 0; i < fruit.size();i++){
            c = fruit.get(i);
            c.draw(window);
        }

        window.text("Score: " + score, 10,10);
    }

    public class Circle{
        private int x, y;
        private int radius;

        public Circle(int x, int y){
            this.x=x;
            this.y=y;
            if(Math.random()<0.5){
                radius=20;
            } else {
                radius = 30;
            }

        }

        public void draw(PApplet window){
            window.fill(255);
            window.ellipseMode(PConstants.RADIUS);
            window.ellipse(x,y,radius,radius);
        }

        public boolean isHit(int x, int y) {
            if(dist(x,y) < radius){
                return true;
            }
            return false;
        }

        private double dist(int x, int y) {
            return Math.sqrt((this.x-x)*(this.x-x) + (this.y-y)*(this.y-y));
        }
    }
}
