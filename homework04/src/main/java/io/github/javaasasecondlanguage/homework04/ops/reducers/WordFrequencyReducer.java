package io.github.javaasasecondlanguage.homework04.ops.reducers;

import io.github.javaasasecondlanguage.homework04.Collector;
import io.github.javaasasecondlanguage.homework04.Record;
import io.github.javaasasecondlanguage.homework04.ops.Reducer;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * Calculate frequency of values in column for each group.
 */
public class WordFrequencyReducer implements Reducer {

    private final String termColumn;
    private final String outputColumn;
    private Map<Object, Integer> termCount = new HashMap<>();
    private long documentTermsCount = 0;

    public WordFrequencyReducer(String termColumn, String outputColumn) {
        this.termColumn = termColumn;
        this.outputColumn = outputColumn;
    }

    @Override
    public void apply(Record inputRecord, Collector collector, Map<String, Object> groupByEntries) {
        termCount.compute(inputRecord.get(termColumn), (term, count) -> count == null ? 1 : count + 1);
        documentTermsCount++;
    }

    @Override
    public void signalGroupWasFinished(Collector collector, Map<String, Object> groupByEntries) {
        termCount.entrySet().stream()
                .sorted(Comparator.comparing(o -> o.getKey().toString()))
                .map(entry -> {
                    final Record record = new Record(groupByEntries);
                    record.set(termColumn, entry.getKey());
                    record.set(outputColumn, (double) entry.getValue() / (double) documentTermsCount);
                    return record;
                })
                .forEach(collector::collect);

        termCount = new HashMap<>();
        documentTermsCount = 0;
    }
}
