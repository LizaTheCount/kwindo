package com.kwindo;

/**
 * Created by Sijmen on 13-5-2017.
 */
public class FlatAlgorithm extends KwindoAlgorithm {

    float prevStockLevel;
    
    //between 0 and 1 where 1 is ultimate risk
    int riskNumber;

    public FlatAlgorithm(int riskNumber) {
        this.riskNumber = riskNumber;
    }

    @Override
    int runAlgorithm(float stockLevel) {
        if(secondCounter == 1) {
            prevStockLevel = stockLevel;
            return 0;
        }
        
        float diff = prevStockLevel - stockLevel;
        prevStockLevel = stockLevel;
        
        return diff > 0 ? riskNumber : -1 * riskNumber;
    }
}
