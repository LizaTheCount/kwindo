package com.kwindo;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Sijmen on 13-5-2017.
 */
public class RandomAlgorithm extends KwindoAlgorithm {

    @Override
    int runAlgorithm(float stockLevel) {
        return ThreadLocalRandom.current().nextInt(-10, 11);
    }
}
