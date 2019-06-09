package fern;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageWrapper {
    BufferedImage image;
    int color;

    int width;
    int height;

    double minX;
    double maxX;
    double minY;
    double maxY;

    double scaleFactor;
    int offsetX;
    int offsetY;

    ImageWrapper(int width, int height) {
        color = new Color(0, 255, 0).getRGB();
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        this.width = width;
        this.height = height;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void init() {
        Graphics2D g2 = image.createGraphics();
        g2.setBackground((new Color(0, 0, 0)));
        g2.clearRect(0, 0, image.getWidth(), image.getHeight());
    }

    /**
     * Plot to buffered image, scaling according to the image dimensions minX, minY, maxX, maxY.
     */
    void plot(double x, double y) {
        int plotX = (int)((x - minX) * scaleFactor) + offsetX;
        int plotY = (int)((y - minY) * scaleFactor) + offsetY;

        image.setRGB(plotX, plotY, color);
    }

    /**
     * Set drawing bounds.
     */
    void setBounds(double minX, double minY, double maxX, double maxY) {
        this.minX = minX;
        this.minY = minY;
        this.maxX = maxX;
        this.maxY = maxY;

        double plotWidth = maxX - minX;
        double plotHeight = maxY - minY;

        // Determine minimum scale factor.
        double width = this.width;
        double height = this.height;

        scaleFactor = Math.min(width / plotWidth, height / plotHeight);

        offsetX = (int)((width - (scaleFactor * plotWidth)) / 2);
        offsetY = (int)((height - (scaleFactor * plotHeight)) / 2);
    }
}
