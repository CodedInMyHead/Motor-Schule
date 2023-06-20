package gui;

import java.awt.*;
import java.util.function.Function;

public class FunctionCanvas extends Canvas {

    final int TILE_COUNT = 8;
    final int OFFSET_ALL = 25;
    int SIZE;

    final int FUNCTION_SIZE;
    final int TILE_SIZE;
    final double a = -0.0455;
    final double y = 68.9275;
    public final Function<Integer, Integer> motorFunctionNormal = (Integer x) -> (int) -(a * x + y);

    FunctionCanvas(int size) {
        this.SIZE = size;
        this.FUNCTION_SIZE = SIZE - (OFFSET_ALL * 2);
        this.TILE_SIZE = FUNCTION_SIZE / TILE_COUNT;
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        paintLines(g);
        drawGraph(g);
    }

    void drawGraph(final Graphics g) {
        int internalLenMin = 1295;
        int internalLenMax = (int) (internalLenMin + TILE_COUNT * 27.5);
        int yMin = motorFunctionNormal.apply(internalLenMin);
        int yMax = motorFunctionNormal.apply(internalLenMax);
        System.out.println(internalLenMin + "," + yMin + " , " + internalLenMax + "," + yMax);
        g.drawLine(internalLenMin - 1295 + OFFSET_ALL, yMin * FUNCTION_SIZE/TILE_SIZE, internalLenMax - 1295 + OFFSET_ALL, yMax * FUNCTION_SIZE/TILE_SIZE);
        g.translate(OFFSET_ALL, FUNCTION_SIZE + OFFSET_ALL);
        g.drawLine(0, (int) -mapNumber(69, 0, 80, 0, FUNCTION_SIZE), (int) mapNumber(1515, 0, 1600, 0, FUNCTION_SIZE), 0);
        /*
        g.drawLine((int) mapNumber(1405, 0, 1515, 0, FUNCTION_SIZE),
                (int) -mapNumber(5, 0, 69, 0, FUNCTION_SIZE),
                (int) mapNumber(1438, 0, 1515, 0, FUNCTION_SIZE),
                (int) -mapNumber(3.5, 0, 69, 0, FUNCTION_SIZE));

         */


    }
    private void paintLines(Graphics g) {
        g.drawLine(OFFSET_ALL, OFFSET_ALL, OFFSET_ALL, OFFSET_ALL + FUNCTION_SIZE);
        g.drawLine(OFFSET_ALL, OFFSET_ALL + FUNCTION_SIZE, OFFSET_ALL + FUNCTION_SIZE, OFFSET_ALL + FUNCTION_SIZE);
    }

    public static double mapNumber(double value, double oldMin, double oldMax, double newMin, double newMax) {
        // Calculate the ratio of the value's position in the old range
        double oldRange = oldMax - oldMin;
        double newRange = newMax - newMin;
        double scaledValue = (value - oldMin) / oldRange;

        // Map the scaled value to the new range
        double mappedValue = newMin + (scaledValue * newRange);

        return mappedValue;
    }
}
