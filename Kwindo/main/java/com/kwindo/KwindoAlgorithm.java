package com.kwindo;

/**
 * Created by Sijmen on 13-5-2017.
 */
public abstract class KwindoAlgorithm {
    
    final static int SECONDSINDAY = 30600;
    
    int secondCounter = 0;
    int ourStock = 0;
    
    public float profit = 0;
    
    public KwindoAlgorithm() {

    }

    /**
     * Receives the next second, return the amount of stocks to by/sell.
     */
    public int processSecond(float stockLevel){
        secondCounter++;
        
        if(dayEnd()) {
//            System.out.println("End of day profit: " + profit);
//            System.out.println("End of day our stock: " + ourStock);
            return sellbuyTo0(stockLevel);
        }
        int result = runAlgorithm(stockLevel);

        int futureStocks = result + ourStock;
        if(futureStocks > 100) 
            result = 100 - ourStock;
        if(futureStocks < -100)
            result = (100 + ourStock)*-1;
        
        updateProfilt(result, stockLevel);
        ourStock += result;
        
        return result;
    }

    abstract int runAlgorithm(float stockLevel);

    private int sellbuyTo0(float stockLevel) {
        int result = 0 - ourStock;
        ourStock +=result;

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
