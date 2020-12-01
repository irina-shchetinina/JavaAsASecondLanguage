package io.github.javaasasecondlanguage.homework04.graphs;

import io.github.javaasasecondlanguage.homework04.GraphPartBuilder;
import io.github.javaasasecondlanguage.homework04.ProcGraph;
import io.github.javaasasecondlanguage.homework04.nodes.SortOrder;
import io.github.javaasasecondlanguage.homework04.ops.mappers.AddColumnMapper;
import io.github.javaasasecondlanguage.homework04.ops.mappers.LowerCaseMapper;
import io.github.javaasasecondlanguage.homework04.ops.mappers.RetainColumnsMapper;
import io.github.javaasasecondlanguage.homework04.ops.mappers.TokenizerMapper;
import io.github.javaasasecondlanguage.homework04.ops.reducers.CountReducer;
import io.github.javaasasecondlanguage.homework04.ops.reducers.FirstNReducer;
import io.github.javaasasecondlanguage.homework04.ops.reducers.WordFrequencyReducer;

import static java.util.List.of;

public class Tfidf {

    public static ProcGraph createGraph() {
        var inputGraph = GraphPartBuilder
                .init()
                .map(new RetainColumnsMapper(of("Id", "Text")))
                .map(new LowerCaseMapper("Text"));

        var tokensGraph = inputGraph
                .branch()
                .map(new TokenizerMapper("Text", "Token"));

        var tfGraph = tokensGraph
                .branch()
                .sortThenReduceBy(of("Id"), new WordFrequencyReducer("Token", "Tf"));

        var documentsCountGraph = inputGraph
                .branch()
                .reduceBy(of(), new CountReducer("DocCountTotal"));

        var idfGraph = tokensGraph
                .branch()
                .sortThenReduceBy(of("Id", "Token"), new FirstNReducer(1))
                .sortThenReduceBy(of("Token"), new CountReducer("DocCountForToken"))
                .join(documentsCountGraph, of())
                // make smooth_idf (with + 1)
                .map(new AddColumnMapper("Idf",
                        r -> Math.log((r.getDouble("DocCountTotal") + 1) / (r.getDouble("DocCountForToken") + 1)) + 1));
//                .map(new AddColumnMapper("Idf",
//                        r -> Math.log(r.getDouble("DocCountTotal") / r.getDouble("DocCountForToken"))));

        var tfIdfGraph = tfGraph
                .join(idfGraph, of("Token"))
                .map(new AddColumnMapper("TfIdf",
                        r -> r.getDouble("Tf") * r.getDouble("Idf")))
                .map(new RetainColumnsMapper(of("Id", "Token", "TfIdf")))
                .sortBy(of("Id", "TfIdf"));

        return new ProcGraph(
                of(tfIdfGraph.getStartNode()),
                of(tfIdfGraph.getEndNode())
        );
    }
}
