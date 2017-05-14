package com.kwindo;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Sijmen on 13-5-2017.
 */
public class KwindoAlgorithm {
    
    final static int SECONDSINDAY = 30600;
    
    int secondCounter = 0;
    int ourStock = 0;
    
    public float profit = 0;
    public float maxProfit = 0;
    public float minProfit = 0;
    
    public float daileyProfit = 0;
    public float minDailyProfit = 0;
    public float maxDailyProfit = 0;
    
    private float balancePoint = 3;
    
    public KwindoAlgorithm(float balancePoint) {
        this.balancePoint = balancePoint;
    }

    /**
     * Receives the next second, return the amount of stocks to by/sell.
     */
    public int processSecond(float stockLevel){
        secondCounter++;
        
        if(dayEnd()) {
            int result =  sellEverythingAlgorithm();
            ourStock += result;
            updateProfilt(result, stockLevel);
            

            if(daileyProfit > maxDailyProfit)
                maxDailyProfit = daileyProfit;
            if(daileyProfit < minDailyProfit)
                minDailyProfit = daileyProfit;
            
            daileyProfit = 0;
            
            
            return result;
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

    private int runAlgorithm(float stockLevel) {
        float alpha = calcUpdateWalkingAverage(stockLevel);
        if(alpha <= balancePoint && alpha >= -balancePoint)
            return runFlatAlgorithm(stockLevel);
        return runSlopeAlgorithm(alpha);
    }

    private int sellEverythingAlgorithm() {
        return 0 - ourStock;
    }

    private boolean dayEnd() {
        return secondCounter % (SECONDSINDAY) == 0;
    }

    private void updateProfilt(int stockBuySellAmount, float stockLevel) {
        profit += calcProfit(stockBuySellAmount, stockLevel);
        daileyProfit += calcProfit(stockBuySellAmount, stockLevel);
        if(profit > maxProfit)
            maxProfit = profit;
        if(profit < minProfit)
            minProfit = profit;
    }
    
    private float calcProfit(int stockBuySellAmount, float stockLevel){
        return (-1*stockBuySellAmount) * stockLevel;
    }


    float prevStockLevel;
    int runFlatAlgorithm(float stockLevel) {
        if(secondCounter == 1) {
            prevStockLevel = stockLevel;
            return 0;
        }

        float diff = stockLevel - prevStockLevel;
        prevStockLevel = stockLevel;

        return diff >= 0 ? 100 : -1 * 100;
    }
    
    LinkedList<Float> history = new LinkedList<>();
    int runSlopeAlgorithm(float alpha) {
        if(alpha == -1) //not trustworthy walking average
            return 0;
        if (alpha > balancePoint) 
            return 100;
        else if (alpha < -balancePoint) 
            return -100;
        return 0;
    }
    
    float calcUpdateWalkingAverage(float stockLevel){
        history.addFirst(stockLevel);
        if(history.size() > 60)
            history.removeLast();
        if(history.size() < 10)
            return -1;
        
        float lastWindowAvg = 0;
        for(int i = 0; i < history.size()-6; i++){
            float windowAvg = avg(history.subList(i, i+5));
            if(lastWindowAvg == 0) {
                lastWindowAvg = windowAvg;
                continue;
            }
            if(windowAvg - lastWindowAvg > balancePoint) {
                history.clear();
                return -1;
            }
        }

        float curAvg = avg(history.subList(0, 4));
                
        float longAvg = avg(history.subList(history.size()-6, history.size()-1));

        return curAvg - longAvg;
    }
    
    float avg(List<Float> list){
        return (float) list.stream().mapToDouble(f -> f).average().getAsDouble();
    }
}
