package fern;

import java.util.Random;

public class CircleDemo extends ImageWrapper implements IterableImage {
    long randomSeed;
    Random rand;

    double prevX;
    double prevY;

    // The following counters are fun: they are another way to approach pi.
    // Pi is the area of the unit circle divided by the area of the square enclosing the unit circle, or
    // put another way, pi / 4 is the number of dots landing inside the circle divided by the total number
    // of dots. We need to multiply by 4 because the unit circle is enclosed by a 2x2 unit square.
    double counter;
    double countInCircle;

    CircleDemo(int width, int height) {
        super(width, height);

        //setBounds(-2.2, 0, 2.7, 10); // this is the range for Barnsley's fern.
        setBounds(-1, -1, 1, 1);

        randomSeed = System.currentTimeMillis();
    }

    public void init() {
        super.init();

        rand = new Random(randomSeed);

        prevX = 0;
        prevY = 0;

        // init pi counters
        counter = 0;
        countInCircle = 0;
    }

    /**
     * This function is called to plot another iteration.
     */
    public void iterate() {
        double nextX = 0;
        double nextY = 0;

        // This is a circle demo, but you can find a python example here: https://en.wikipedia.org/wiki/Barnsley_fern
        // For this demo, we'll use nextX and nextY to represent a point.
        // We will reset the point to 0 if it is outside the circle.
        double randomResolution = 1000000; // A suitably high random resolution gives us a good range of values between -1 and 1
        nextX = (rand.nextInt((int)randomResolution * 2) / randomResolution) - 1;
        nextY = (rand.nextInt((int)randomResolution * 2) / randomResolution) - 1;

        if (Math.sqrt((nextX * nextX) + (nextY * nextY)) > 1) {
            nextX = 0;
            nextY = 0;
        } else {
            countInCircle++;
        }

        prevX = nextX;
        prevY = nextY;

        plot(nextX, nextY);

        // Print the value of pi every 10000 counts to reduce the number of lines output.
        counter++;
        if (counter % 10000 == 0) {
            System.out.println("After " + counter + " iterations, Pi is approximately: " + countInCircle / counter * 4);
        }
    }
}
