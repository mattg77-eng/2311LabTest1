package model;

import java.util.ArrayList;
import java.util.HashSet;

public class RecursiveMethods {

    // Task 1
    public String task1(String str) {
        return findParen(str, 0);
    }

    private String findParen(String str, int index) {
        if (index >= str.length()) return "";
        if (str.charAt(index) == '(') {
            return collectParen(str, index);
        }
        return findParen(str, index + 1);
    }

    private String collectParen(String str, int i) {
        if (str.charAt(i) == ')') return ")";
        return str.charAt(i) + collectParen(str, i + 1);
    }

    // Task 2
    public boolean task2(int[] a, int target) {
        return checkSum(a, target, 0);
    }

    private boolean checkSum(int[] a, int target, int i) {
        if (target == 0) return true;
        if (i >= a.length) return false;

        return checkSum(a, target - a[i], i + 1) || checkSum(a, target, i + 1);
    }

    // Task 3
    public int task3(int h, int n) {
        if (h == 0) return 1;
        if (h < 0) return 0;

        int total = 0;
        for (int i = 1; i <= n; i++) {
            total += task3(h - i, n);
        }
        return total;
    }

    // Task 4
    public HashSet<ArrayList<Integer>> task4(int h, int n) {
        HashSet<ArrayList<Integer>> result = new HashSet<>();
        findWays(h, n, new ArrayList<>(), result);
        return result;
    }

    private void findWays(int h, int n, ArrayList<Integer> current, HashSet<ArrayList<Integer>> result) {
        if (h == 0) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = 1; i <= n; i++) {
            if (h - i >= 0) {
                current.add(i);
                findWays(h - i, n, current, result);
                current.remove(current.size() - 1); // backtrack
            }
        }
    }
}

