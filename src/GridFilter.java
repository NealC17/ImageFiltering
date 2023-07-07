import processing.core.PApplet;

import javax.swing.*;

public class GridFilter implements PixelFilter{

    private final int n;

    public GridFilter(){
        n = Integer.parseInt(JOptionPane.showInputDialog("Enter numGrid lines"));
    }

    @Override
    public DImage processImage(DImage img) {
        return img;
    }

    @Override
    public void drawOverlay(PApplet window, DImage original, DImage filtered) {
        int xIncrement = original.getHeight()/n;
        int yIncrement = original.getWidth()/n;
        window.strokeWeight(2);
        for (int i = 0; i < original.getWidth(); i+=xIncrement) {
            window.line(i,0,i,original.getHeight());
        }


        for (int i = 0; i < original.getHeight(); i+=yIncrement) {
            window.line(0, i, original.getWidth(),i);
        }

    }

    public void drawOverlay(PApplet window) {
        window.fill(255, 0, 0);
        window.ellipse(0, 0, 10, 10);
    }




}
