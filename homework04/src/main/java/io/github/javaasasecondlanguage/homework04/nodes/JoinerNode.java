package io.github.javaasasecondlanguage.homework04.nodes;

import io.github.javaasasecondlanguage.homework04.Record;
import io.github.javaasasecondlanguage.homework04.RoutingCollector;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.sort;

public class JoinerNode implements ProcNode {

    private final RoutingCollector collector = new RoutingCollector();
    private final List<String> keyColumns;

    private final List<Record> leftAccumulatedRecords = new ArrayList<>();
    private final List<Record> rightAccumulatedRecords = new ArrayList<>();
    private boolean leftRecordsTerminated = false;
    private boolean rightRecordsTerminated = false;


    public JoinerNode(List<String> keyColumns) {
        this.keyColumns = keyColumns;
    }

    @Override
    public RoutingCollector getCollector() {
        return collector;
    }

    @Override
    public void push(Record inputRecord, int gateNumber) {
        if (gateNumber == 0) {
            if (leftRecordsTerminated)
                throw new IllegalArgumentException("Can't push gate "
                        + gateNumber + "after terminated record");

            if (!inputRecord.isTerminal()) {
                leftAccumulatedRecords.add(inputRecord);
                return;
            }
            leftRecordsTerminated = true;
        } else if (gateNumber == 1) {
            if (rightRecordsTerminated)
                throw new IllegalArgumentException("Can't push gate "
                        + gateNumber + "after terminated record");

            if (!inputRecord.isTerminal()) {
                rightAccumulatedRecords.add(inputRecord);
                return;
            }
            rightRecordsTerminated = true;
        } else {
            throw new IllegalArgumentException("Gate does not exist: " + gateNumber);
        }

        if (leftRecordsTerminated && rightRecordsTerminated) {
            joinAndCollect();
            clean();
        }

    }

    private void joinAndCollect() {
        for (Record leftRecord : leftAccumulatedRecords) {
            for (Record rightRecord : rightAccumulatedRecords) {
                // TODO turn leftRecord.copyColumns(keyColumns) to outer 'for' maybe
                if (leftRecord.copyColumns(keyColumns).equals(rightRecord.copyColumns(keyColumns))) {
                    final Record collectedRecord = new Record(leftRecord.getData())
                            .setAll(rightRecord.getData());
                    getCollector().collect(collectedRecord);
                }
            }
        }
        collector.collect(Record.terminalRecord());
    }

    private void clean() {
        leftRecordsTerminated = false;
        rightRecordsTerminated = false;
        leftAccumulatedRecords.clear();
        rightAccumulatedRecords.clear();
    }
}
