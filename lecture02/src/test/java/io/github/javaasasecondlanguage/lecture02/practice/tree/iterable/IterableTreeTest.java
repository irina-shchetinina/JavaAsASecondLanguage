package io.github.javaasasecondlanguage.lecture02.practice.tree.iterable;

import io.github.javaasasecondlanguage.lecture02.practice.tree.TreeNode;
import io.github.javaasasecondlanguage.lecture02.practice.tree.TreeNodeImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

class IterableTreeTest {

    List<Integer> EXPECTED = List.of(1, 20, 201, 202, 203, 30, 301, 302, 40);

    IterableTree tree = new IterableTree(
            node(1,
                    node(20,
                            node(201),
                            node(202),
                            node(203)
                    ),
                    node(30,
                            node(301),
                            node(302)
                    ),
                    node(40)
            )
    );

    @Test
    void forEachInLoop() {
        // any of:
        //   1 -> 20 -> 20x -> 20y -> 20z -> 30 -> 30x -> 30y -> 40
        //   1 -> 40 -> 30 -> 30x -> 30y -> 20 -> 20x -> 20y -> 20z

        List<Integer> actual = new ArrayList<>();

        for (var node : tree) {
            actual.add(node);
            System.out.println(node);
        }
        Assertions.assertEquals(EXPECTED, actual);
    }

    @Test
    void forEachMethod() {
        // any of:
        //   1 -> 20 -> 20x -> 20y -> 20z -> 30 -> 30x -> 30y -> 40
        //   1 -> 40 -> 30 -> 30x -> 30y -> 20 -> 20x -> 20y -> 20z
        List<Integer> actual = new ArrayList<>();

        tree.forEach(x -> {
            actual.add(x);
            System.out.println(x);
        });

        Assertions.assertEquals(EXPECTED, actual);
    }

    @Test
    void dataStream() {
        // any of:
        //   1 -> 20 -> 20x -> 20y -> 20z -> 30 -> 30x -> 30y -> 40
        //   1 -> 40 -> 30 -> 30x -> 30y -> 20 -> 20x -> 20y -> 20z
        List<Integer> actual = new ArrayList<>();
        StreamSupport.stream(tree.spliterator(), false)
                .peek(actual::add)
                .forEach(System.out::println);
        Assertions.assertEquals(EXPECTED, actual);
    }

    private static TreeNode<Integer> node(Integer e, TreeNode<Integer>... children) {
        return new TreeNodeImpl<>(e, children);
    }
}