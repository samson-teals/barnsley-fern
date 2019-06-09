# Starter files for Barnsley's Fern

The `Main` class itself isn't supposed to be interesting: it only hosts a simple `Canvas` that we can draw on to.
Classes that implement the `IterableImage` interface can be used in the `Main` class.

The `Main` class then draws the image from `IterableImage` classes.
Feel free to modify `Main`.
For example, you may way to change the `framesPerSecond` or `iterationsPerFrame` values.

# Barnsley's Fern

Barnsley's fern is an example of an IFS, or `Iterated Function System` (https://en.wikipedia.org/wiki/Iterated_function_system).
These work by applying a function (an affine transform in this case) to "itself".
For example, Barnsley's fern is self-similar because all the "leaves" of the fern, are copies of the whole fern.
A consequence of this property is that the fern has infinite detail.

## Constructing the fern

This page has a few variation examples of IFS ferns: http://www.home.aone.net.au/~byzantium/ferns/fractal.html.
To get started, you may want to refer to the Python or QBasic examples on the wikipedia page: https://en.wikipedia.org/wiki/Barnsley_fern.

1. Start with the `CircleDemo.java` file.
2. Modify the `constructor`, `init`, and `iterate` functions. Set a proper bounding rectangle in `init`, and it should be enough to modify the `iterate` function itself. It is important to set a bounding rectangle greater than or equal to what you'll be plotting because there is no clipping in the `ImageWrapper`. An out-of-bounds value will throw an exception.
3. Remember that the `iterate` function is called to generate each new plot point. Note how the `prevX` and `prevY` variables transfer the values of `nextX` and `nextY` to the next iteration.
4. You can use the `plot` function to plot floating point values. This is provided by the `ImageWrapper` superclass.

To help with debugging, you may want to modify `iterationsPerFrame` in `static void main`. This controls how many iterations are run (or points that are generated) between each drawing operation. It is useful to set this to a low number to see how the fern is built up, or to a high number to quickly build up a recognizable fern.

### ImageWrapper.java

The `ImageWrapper` class contains a buffered image.
Whenever a point is plotted, the point is mapped to the appropriate pixel in the buffered image.
The buffered image is then exposed to the `Main` class which copies the image to the canvas on every timer event.

## Experiments

- Try changing some of the constants from what is given in the examples.
- Try implementing other `IFS`s such as the "Triangle" here: http://paulbourke.net/fractals/lsys/ (the bounds are [0,0] [1,1])
