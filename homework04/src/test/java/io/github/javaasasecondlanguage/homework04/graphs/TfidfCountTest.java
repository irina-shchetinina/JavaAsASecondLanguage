package io.github.javaasasecondlanguage.homework04.graphs;

import io.github.javaasasecondlanguage.homework04.GraphPartBuilder;
import io.github.javaasasecondlanguage.homework04.Record;
import io.github.javaasasecondlanguage.homework04.nodes.ProcNode;
import io.github.javaasasecondlanguage.homework04.utils.ListDumper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.github.javaasasecondlanguage.homework04.utils.AssertionUtils.assertRecordsEqual;
import static io.github.javaasasecondlanguage.homework04.utils.TestUtils.convertToRecords;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class TfidfCountTest {

    private ProcNode inputNode;
    private ProcNode outputNode;

    @BeforeEach
    void setUp() {
        var graph = Tfidf.createGraph();

        assertNotNull(graph.getInputNodes());
        assertNotNull(graph.getInputNodes());

        assertEquals(1, graph.getInputNodes().size());
        assertEquals(1, graph.getOutputNodes().size());

        inputNode = graph.getInputNodes().get(0);
        outputNode = graph.getOutputNodes().get(0);
    }

    @Test
    void general() {
        var listDumper = new ListDumper();
        GraphPartBuilder
                .startFrom(outputNode)
                .map(listDumper);

        for (var record : inputRecords) {
            inputNode.push(record, 0);
        }
        inputNode.push(Record.terminalRecord(), 0);

        List<Record> actualRecords = listDumper.getRecords();

        assertRecordsEqual(expectedRecords, actualRecords);
    }

    private static final List<Record> inputRecords = convertToRecords(
            new String[]{"Id", "Author", "Text"},
            new Object[][]{
                    {1, "Garrus", "I'm Garrus Vakarian and this is now my favorite spot on the Citadel."},
                    {2, "Illusive", "Strength for Cerberus is strength for every human. Cerberus is humanity."},
                    {3, "Tali", "After time adrift among open stars, along tides of light and through shoals of dust, I will return to where I began."},
                    {4, "Miranda", "I settle for nothing but the best."},
                    {5, "Legion", "Geth do not intentionally infiltrate."},
                    {6, "Illusive", "You're unique. Not just in terms of what you've accomplished, but what you represent."},
                    {7, "Garrus", "Can it wait for a bit? I'm in the middle of some calibrations."},
                    {8, "Legion", "Does this unit have a soul?"},
                    {9, "Mordin", "Lots of ways to help people. Sometimes heal patients; sometimes execute dangerous people. Either way helps."},
                    {10, "Miranda", "We're all asking you to do the impossible, Shepard. No pressure."},
                    {11, "Garrus", "I had reach, but she had flexibility."},
                    {12, "Garrus", "You know me, always like to savor that last shot before popping the heat sink."},
                    {13, "Legion", "Does this unit have a soul?"},
                    {14, "Mordin", "No glands, replaced by tech. No digestive system, replaced by tech. No soul. Replaced by tech."},
            }
    );

    private static final List<Record> expectedRecords = convertToRecords(
            new String[]{"Author", "TotalWords"},
            new Object[][]{
                    {"Garrus", 50},
            }
    );
}