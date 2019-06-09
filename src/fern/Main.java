package fern;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

public class Main {
    IterableImage iimage;
    int iterationsPerFrame;

    /**
     * The ImageCanvas nested class is only used for drawing. No calculations should be done here.
     */
    class ImageCanvas extends Canvas {
        int windowOffset;

        public ImageCanvas(int width, int height, int windowOffset) {
            setSize(width, windowOffset + height);

            this.windowOffset = windowOffset;
        }

        /**
         * This method paints the canvas and is called by the system whenever a repaint is requested.
         */
        public void paint(Graphics g) {
            BufferedImage image = iimage.getImage();
            int imageWidth = image.getWidth();
            int imageHeight = image.getHeight();

            Graphics2D g2 = (Graphics2D) g;
            g2.drawImage(image, 0, imageHeight + windowOffset, imageWidth, -imageHeight, null);
        }
    }

    public Main(IterableImage iimage, float framesPerSecond, int iterationsPerFrame) {
        this.iimage = iimage;
        this.iterationsPerFrame = iterationsPerFrame;

        // sets up graphics
        Frame frame = new Frame("Barnsley's Fern");
        frame.setVisible(true);
        Insets insets = frame.getInsets();

        Canvas canvas = new ImageCanvas(iimage.getImage().getWidth(), iimage.getImage().getHeight(), insets.top);
        frame.add(canvas);
        frame.setLayout(null);
        frame.setSize(canvas.getWidth(), canvas.getHeight());

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        // sets up timer so that we can animate the life grid
        int frameDelay = (int) (1000 / framesPerSecond);
        Timer timer = new Timer(frameDelay, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < iterationsPerFrame; i++) {
                    iimage.iterate();
                }

                canvas.repaint();
            }
        });
        timer.start();
    }

    public static void main(String args[]) {
        int width = 800;
        int height = 600;
        int framesPerSecond = 5;
        int iterationsPerFrame = 1000;

        IterableImage iimage = new CircleDemo(width, height);
        iimage.init();

        new Main(iimage, framesPerSecond, iterationsPerFrame);
    }
}
