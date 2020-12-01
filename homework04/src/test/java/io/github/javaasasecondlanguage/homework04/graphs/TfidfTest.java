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

class TfidfTest {

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
            new String[]{"Id", "Text"},
            new Object[][]{
                    {1, "You were born with potential."},
                    {2, "You were born with goodness and trust."},
//                    {3, "You were born with ideals and dreams."},
//                    {4, "You were born with greatness."},
//                    {5, "You were born with wings."},
//                    {6, "You are not meant for crawling, so don't."},
                    {7, "You have wings."},
                    {8, "Learn to use them and fly."},
            }
    );

    private static final List<Record> expectedRecords = convertToRecords(
            new String[]{"Id", "Token", "TfIdf"},
            new Object[][]{
                    {1, "you", 0.245},
                    {1, "born", 0.302},
                    {1, "were", 0.302},
                    {1, "with", 0.302},
                    {1, "potential", 0.383},
                    {2, "you", 0.175},
                    {2, "and", 0.216},
                    {2, "born", 0.216},
                    {2, "were", 0.216},
                    {2, "with", 0.216},
                    {2, "goodness", 0.274},
                    {2, "trust", 0.274},
                    {7, "you", 0.408},
                    {7, "have", 0.639},
                    {7, "wings", 0.639},
                    {8, "and", 0.252},
                    {8, "fly", 0.319},
                    {8, "learn", 0.319},
                    {8, "them", 0.319},
                    {8, "to", 0.319},
                    {8, "use", 0.319},
            }
    );
}