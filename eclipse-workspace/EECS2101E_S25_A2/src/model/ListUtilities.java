package model;

import tests.Node;

public class ListUtilities {

    public Node<String> getAllPrefixes(Node<Integer> input, int m, int n) {
        return getAllPrefixesHelper(input, m, n, 1, "");
    }

    private Node<String> getAllPrefixesHelper(Node<Integer> input, int m, int n, int len, String acc) {
        if (input == null || len > n) return null;
        acc += (acc.isEmpty() ? "" : ", ") + input.getElement();
        Node<String> rest = getAllPrefixesHelper(input.getNext(), m, n, len + 1, acc);
        return (len >= m) ? new Node<>("[" + acc + "]", rest) : rest;
    }

    public Node<Integer> getMergedChain(Node<Integer> left, Node<Integer> right) {
        if (left == null && right == null) return null;
        if (left == null) return new Node<>(right.getElement(), getMergedChain(null, right.getNext()));
        if (right == null) return new Node<>(left.getElement(), getMergedChain(left.getNext(), null));
        if (left.getElement() <= right.getElement())
            return new Node<>(left.getElement(), getMergedChain(left.getNext(), right));
        return new Node<>(right.getElement(), getMergedChain(left, right.getNext()));
    }

    public Node<Integer> getInterleavedArithmeticFibonacciSequences(int start, int diff, int m, int n) {
        return interleave(start, diff, m, n, 0, 1, 1);
    }

    private Node<Integer> interleave(int start, int diff, int m, int n, int i, int a, int b) {
        if (m + n == 0) return null;
        if (m == 0) return new Node<>(a, interleave(start, diff, 0, n - 1, 0, b, a + b));
        if (n == 0) return new Node<>(start + i * diff, interleave(start, diff, m - 1, 0, i + 1, a, b));
        return new Node<>(start + i * diff, new Node<>(a, interleave(start, diff, m - 1, n - 1, i + 1, b, a + b)));
    }

    public Node<String> getGroupedStrings(Node<String> input, int m, int n) {
        return group(input, m, n, 1);
    }

    private Node<String> group(Node<String> input, int m, int n, int stage) {
        if (stage > 3) return null;
        Node<String> current = filter(input, m, n, stage);
        return concat(current, group(input, m, n, stage + 1));
    }

    private Node<String> filter(Node<String> input, int m, int n, int group) {
        if (input == null) return null;
        String s = input.getElement();
        int len = s.length();
        boolean ok = (group == 1 && len < m) || (group == 2 && len >= m && len < n) || (group == 3 && len >= n);
        Node<String> rest = filter(input.getNext(), m, n, group);
        return ok ? new Node<>(s, rest) : rest;
    }

    private Node<String> concat(Node<String> a, Node<String> b) {
        return (a == null) ? b : new Node<>(a.getElement(), concat(a.getNext(), b));
    }
}

