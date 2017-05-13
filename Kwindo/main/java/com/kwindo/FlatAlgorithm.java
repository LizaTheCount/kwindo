package com.kwindo;

/**
 * Created by Sijmen on 13-5-2017.
 */
public class FlatAlgorithm extends KwindoAlgorithm {

    float prevStockLevel;
    
    @Override
    int runAlgorithm(float stockLevel) {
        if(secondCounter == 1) {
            prevStockLevel = stockLevel;
            return 0;
        }
        
        float diff = prevStockLevel - stockLevel;
        prevStockLevel = stockLevel;

        return diff > 0 ? 100 : -100;
    }
}
