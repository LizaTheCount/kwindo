package com.kwindo;

/**
 * Created by Sijmen on 13-5-2017.
 */
public class FlatAlgorithm extends KwindoAlgorithm {
    
    float prevStockLevel;

    public static int magicNumber = 80;
    
    @Override
    int runAlgorithm(float stockLevel) {
        if(secondCounter == 1) {
            prevStockLevel = stockLevel;
            return 0;
        }
        float diff = (prevStockLevel - stockLevel) * magicNumber;
        if(diff > 1)
            return (int) diff;
        return -1 * (int) diff;
    }
}
