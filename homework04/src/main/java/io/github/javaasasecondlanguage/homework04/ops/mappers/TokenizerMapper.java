package io.github.javaasasecondlanguage.homework04.ops.mappers;

import io.github.javaasasecondlanguage.homework04.Collector;
import io.github.javaasasecondlanguage.homework04.Record;
import io.github.javaasasecondlanguage.homework04.ops.Mapper;

import java.util.Collections;

/**
 * Splits text in the specified column into words, then creates a new record with each word.
 * <p>
 * Split should happen on the following symbols: " ", ".", ",", "!", ";", "?", "'", ":"
 */
public class TokenizerMapper implements Mapper {

    private static final String SPLIT_PATTERN = "[\\s,\\.\\!\\;\\?\\'\\:\"]+";

    private final String inputColumn;
    private final String outputColumn;

    public TokenizerMapper(String inputColumn, String outputColumn) {
        this.inputColumn = inputColumn;
        this.outputColumn = outputColumn;
    }

    @Override
    public void apply(Record inputRecord, Collector collector) {
        final Object value = inputRecord.get(inputColumn);
        if (value instanceof String) {
            final String[] words = ((String) value).split(SPLIT_PATTERN);
            for (String word : words) {
                collector.collect(inputRecord
                        .copyColumnsExcept(Collections.singleton(inputColumn))
                        .set(outputColumn, word));
            }
        } else {
            collector.collect(inputRecord);
        }

    }
}
