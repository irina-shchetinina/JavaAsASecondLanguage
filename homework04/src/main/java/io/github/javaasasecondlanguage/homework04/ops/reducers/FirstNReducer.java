package io.github.javaasasecondlanguage.homework04.ops.reducers;

import io.github.javaasasecondlanguage.homework04.Collector;
import io.github.javaasasecondlanguage.homework04.Record;
import io.github.javaasasecondlanguage.homework04.ops.Reducer;

import java.util.Map;

/**
 * Returns at most maxAmount records per group.
 */
public class FirstNReducer implements Reducer {

    private final int maxAmount;
    private int currentCount = 0;

    public FirstNReducer(int maxAmount) {
        this.maxAmount = maxAmount;
    }

    @Override
    public void apply(Record inputRecord, Collector collector, Map<String, Object> groupByEntries) {
        if (currentCount < maxAmount) {
            collector.collect(inputRecord);
        }
        currentCount++;
    }

    @Override
    public void signalGroupWasFinished(Collector collector, Map<String, Object> groupByEntries) {
        currentCount = 0;
    }
}
