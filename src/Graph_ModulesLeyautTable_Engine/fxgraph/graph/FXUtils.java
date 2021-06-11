package Graph_ModulesLeyautTable_Engine.fxgraph.graph;

import javafx.animation.AnimationTimer;

public class FXUtils
{
    private static long lastUpdate = 0;
    private static int index = 0;
    private static double[] frameRates = new double[100];

    static
    {
        AnimationTimer frameRateMeter = new AnimationTimer()
        {
            @Override
            public void handle(long now)
            {
                if (lastUpdate > 0)
                {
                    long nanosElapsed = now - lastUpdate;
                    double frameRate = 1000000000.0 / nanosElapsed;
                    index %= frameRates.length;
                    frameRates[index++] = frameRate;
                }

                lastUpdate = now;
            }
        };

        frameRateMeter.start();
    }

    /**
     * Returns the instantaneous FPS for the last frame rendered.
     *
     * @return
     */
    public static double getInstantFPS()
    {
        return frameRates[index % frameRates.length];
    }

    /**
     * Returns the average FPS for the last 100 frames rendered.
     * @return
     */
    public static double getAverageFPS()
    {
        double total = 0.0d;

        for (int i = 0; i < frameRates.length; i++)
        {
            total += frameRates[i];
        }

        return total / frameRates.length;
    }
}