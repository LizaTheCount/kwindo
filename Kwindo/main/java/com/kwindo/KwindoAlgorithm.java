package com.kwindo;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Sijmen on 13-5-2017.
 */
public class KwindoAlgorithm {
    
    final static int SECONDSINDAY = 30600;
    
    int secondCounter = 0;
    int amountOfStock = 0;
    
    public float profit = 0;
    
    public KwindoAlgorithm() {

    }

    /**
     * Receives the next second, return the amount of stocks to by/sell.
     */
    public int processSecond(float stockLevel){
        secondCounter++;
        
        if(dayEnd())
            return sellbuyTo0(stockLevel);
        
        return randomStockAlgorithm(stockLevel);
    }

    private int randomStockAlgorithm(float stockLevel) {
        int stockBuySellAmount = ThreadLocalRandom.current().nextInt(-10, 11);
        int futureStocks = stockBuySellAmount + amountOfStock;
        if(futureStocks > 100 || futureStocks < -100)
            return 0;
        amountOfStock = futureStocks;
        updateProfilt(stockBuySellAmount, stockLevel);
        return stockBuySellAmount;
    }

    private int sellbuyTo0(float stockLevel) {
        int result = 0 - amountOfStock;
        amountOfStock+=result;

        updateProfilt(result, stockLevel);
        return result;
    }

    private boolean dayEnd() {
        return secondCounter % (SECONDSINDAY) == 0;
    }

    private void updateProfilt(int stockBuySellAmount, float stockLevel) {
        profit += stockBuySellAmount * stockLevel;
    }
}
