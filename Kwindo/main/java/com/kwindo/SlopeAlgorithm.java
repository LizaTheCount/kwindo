package com.kwindo;

/**
 * Created by Vincent on 13-5-2017.
 */
public class SlopeAlgorithm extends KwindoAlgorithm {
    float prev[] = new float[15];
    int count = 0;
    @Override
    int runAlgorithm(float stockLevel) {
        if (count < prev.length && prev[count] == 0.0f) {
            prev[count] = stockLevel;
            count++;
            return 0;
        }

        float dir = prev[prev.length - 1] - prev[0];

//        System.out.println(dir);
        float newPrev[] = new float[15];

        int j = 0;
        for(int i = 1; i < prev.length; i++) {
            newPrev[j] = prev[i];
            j++;
        }

        newPrev[newPrev.length - 1] = stockLevel;

        prev = newPrev;

        if (dir >= +2.5) {
            return 1;
        } else if (dir <= -0.001) {
            return -1;
        }

        return 0;
    }
}
