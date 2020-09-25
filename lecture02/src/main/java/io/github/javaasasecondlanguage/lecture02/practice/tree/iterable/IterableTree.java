package io.github.javaasasecondlanguage.lecture02.practice.tree.iterable;

import io.github.javaasasecondlanguage.lecture02.practice.tree.Tree;
import io.github.javaasasecondlanguage.lecture02.practice.tree.TreeNode;

import java.util.*;

public class IterableTree
        implements Tree<Integer>, Iterable<Integer> {
    // не стала наследовать AbstractTree, просто реализовала нужные
    // для интерфейса Tree методы

    private TreeNode<Integer> root;

    public IterableTree(TreeNode<Integer> root) {
        this.root = root;
    }

    @Override
    public TreeNode<Integer> getRoot() {
        return root;
    }

    public void printTree() {
        printNode(root);
    }

    private void printNode(TreeNode<Integer> node) {
        if (node == null) {
            System.out.println("Nothing");
            return;
        }
        System.out.println(node.element());
        for (TreeNode<Integer> child : node.getChildren()) {
            printNode(child);
        }

    }

    /**
     * Pre-order iterator
     * https://en.wikipedia.org/wiki/Tree_traversal#Pre-order_(NLR)
     * <p>
     * iterate(node), where node = [data, children] ->
     * 1. data
     * 2. for child in children:
     * iterate(child)
     *
     * @return
     */
    @Override
    public Iterator<Integer> iterator() {

        // предполагаем, что количество элементов не должно меняться.
        // это можно сделать через modCount и expectedModCount, если они не равны во время
        // итераций, то выбрасывать исключение о throw new ConcurrentModificationException();

        Iterator<Integer> it = new Iterator<>() {

            private TreeNode<Integer> nextNode = root;
            private Stack<StateElement> statesStack = new Stack<>();
            private StateElement curState = new StateElement(nextNode, -1);

            @Override
            public boolean hasNext() {
                return nextNode != null;
            }

            @Override
            public Integer next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                TreeNode<Integer> currentNode = nextNode;
                nextNode = getNextNode();
                return currentNode.element();
            }

            private TreeNode<Integer> getNextNode() {
                do {
                    if (hasNextInCurrentState()) {
                        return processNextInCurrentState();
                    }

                } while (popState());
                return null;
            }

            private boolean hasNextInCurrentState() {
                return curState.hasNextChild();
            }

            private TreeNode<Integer> processNextInCurrentState() {
                final TreeNode<Integer> next = curState.getNext();
                statesStack.push(curState);
                curState = new StateElement(next, -1);
                return next;
            }

            private boolean popState() {
                if (statesStack.isEmpty()) {
                    return false;
                }
                curState = statesStack.pop();
                return true;
            }

            class StateElement {
                private TreeNode<Integer> node;
                private int curChildNumber;

                StateElement(TreeNode<Integer> node, int curChildNumber) {
                    this.node = node;
                    this.curChildNumber = curChildNumber;
                }

                TreeNode<Integer> getNext() {
                    return node.getChildren().get(++curChildNumber);
                }

                boolean hasNextChild() {
                    return node.getChildren() != null
                            && node.getChildren().size() > curChildNumber + 1;
                }
            }
        };
        return it;
    }
}


