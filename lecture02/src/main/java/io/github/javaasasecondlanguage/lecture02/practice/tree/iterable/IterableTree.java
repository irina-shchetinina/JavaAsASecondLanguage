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

        Iterator<Integer> it = new Iterator<Integer>() {

            private TreeNode<Integer> currentNode = null;
            private TreeNode<Integer> nextNode = root;
            private Stack<StateElement> statesStack = new Stack<>();

            {
                if (root != null) {
                    statesStack.push(new StateElement(root, -1));
                }
            }

            @Override
            public boolean hasNext() {
                return nextNode != null;
            }

            @Override
            public Integer next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                currentNode = nextNode;
                nextNode = getNextNode(currentNode);
                return currentNode.element();
            }

            private TreeNode<Integer> getNextNode(TreeNode<Integer> currentNode) {
                StateElement curState = statesStack.peek();
                List<TreeNode<Integer>> curChildren = curState.getNode().getChildren();
                if (curChildren != null && !curChildren.isEmpty()) {
                    nextNode = curChildren.get(0);
                    curState.setCurChildNumber(0);
                    statesStack.push(new StateElement(nextNode, -1));
                    return nextNode;
                }

                // детей нет, идём выше. У родителя точно есть дети (мы были в одном из них)
                statesStack.pop();
                while (!statesStack.empty()) {
                    curState = statesStack.peek();

                    if (curState.getNode().getChildren().size()
                            > curState.getCurChildNumber() + 1) {

                        curState.setCurChildNumber(curState.getCurChildNumber() + 1);
                        nextNode = curState.getNode().getChildren()
                                .get(curState.getCurChildNumber());
                        statesStack.push(new StateElement(nextNode, -1));
                        return nextNode;
                    } else { // у этой ноды обошли уже всех детей
                        statesStack.pop();
                    }
                }
                return null;
            }

            class StateElement {
                private TreeNode<Integer> node;
                private int curChildNumber;

                public StateElement(TreeNode<Integer> node, int curChildNumber) {
                    this.node = node;
                    this.curChildNumber = curChildNumber;
                }

                public TreeNode<Integer> getNode() {
                    return node;
                }

                public void setNode(TreeNode<Integer> node) {
                    this.node = node;
                }

                public int getCurChildNumber() {
                    return curChildNumber;
                }

                public void setCurChildNumber(int curChildNumber) {
                    this.curChildNumber = curChildNumber;
                }
            }
        };
        return it;
    }
}


