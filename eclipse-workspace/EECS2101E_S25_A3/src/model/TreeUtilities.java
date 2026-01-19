package model;

import tests.TreeNode;
import tests.SLLNode;

public class TreeUtilities {
    
    private static class Stats {
        int count;
        int sum;
        
        Stats(int count, int sum) {
            this.count = count;
            this.sum = sum;
        }
    }
    
    public SLLNode<Integer> getElementsOfRanks(TreeNode<Integer> n, int i, int j) {
        
        SLLNode<Integer> allElements = collectElements(n);
        allElements = sortList(allElements);
        return extractRange(allElements, i - 1, j - 1);
    }
    
    public TreeNode<String> getStats(TreeNode<Integer> n) {
        if (n == null) {
            return null;
        }
        return buildStatsTree(n);
    }
    
    private SLLNode<Integer> collectElements(TreeNode<Integer> node) {
        if (node == null) {
            return null;
        }
        
        SLLNode<Integer> head = new SLLNode<>(node.getElement(), null);
        SLLNode<Integer> tail = head;
        SLLNode<TreeNode<Integer>> childNode = node.getChildren();
        
        while (childNode != null) {
            TreeNode<Integer> child = childNode.getElement();
            SLLNode<Integer> childElements = collectElements(child);
            
            if (childElements != null) {
                tail.setNext(childElements);
                while (tail.getNext() != null) {
                    tail = tail.getNext();
                }
            }
            
            childNode = childNode.getNext();
        }
        
        return head;
    }
    
    private SLLNode<Integer> sortList(SLLNode<Integer> head) {
        if (head == null || head.getNext() == null) {
            return head;
        }
        
        SLLNode<Integer> sorted = null;
        SLLNode<Integer> current = head;
        
        while (current != null) {
            SLLNode<Integer> next = current.getNext();
            
            if (sorted == null || sorted.getElement() >= current.getElement()) {
                current.setNext(sorted);
                sorted = current;
            } 
            else {
                SLLNode<Integer> temp = sorted;
                while (temp.getNext() != null && temp.getNext().getElement() < current.getElement()) {
                    temp = temp.getNext();
                }
                current.setNext(temp.getNext());
                temp.setNext(current);
            }
            
            current = next;
        }
        
        return sorted;
    }
    
    private SLLNode<Integer> extractRange(SLLNode<Integer> head, int start, int end) {
        if (head == null || start > end) {
            return null;
        }
        
        SLLNode<Integer> current = head;
        int index = 0;
        
        while (current != null && index < start) {
            current = current.getNext();
            index++;
        }
        
        if (current == null) {
            return null;
        }
        
        SLLNode<Integer> resultHead = new SLLNode<>(current.getElement(), null);
        SLLNode<Integer> resultTail = resultHead;
        index++;
        current = current.getNext();
        
        while (current != null && index <= end) {
            resultTail.setNext(new SLLNode<>(current.getElement(), null));
            resultTail = resultTail.getNext();
            current = current.getNext();
            index++;
        }
        
        return resultHead;
    }
    
    private TreeNode<String> buildStatsTree(TreeNode<Integer> node) {
        if (node == null) {
            return null;
        }
        
        Stats stats = calculateStats(node);
        String statsString = String.format("Number of descendants: %d; Sum of descendants: %d", stats.count, stats.sum);
        TreeNode<String> statsNode = new TreeNode<>(statsString);
        
        SLLNode<TreeNode<Integer>> childNode = node.getChildren();
        SLLNode<TreeNode<String>> statsChildHead = null;
        SLLNode<TreeNode<String>> statsChildTail = null;
        
        while (childNode != null) {
            TreeNode<Integer> child = childNode.getElement();
            TreeNode<String> statsChild = buildStatsTree(child);
            
            if (statsChild != null) {
                statsChild.setParent(statsNode);
                
                SLLNode<TreeNode<String>> newNode = new SLLNode<>(statsChild, null);
                if (statsChildHead == null) {
                    statsChildHead = newNode;
                    statsChildTail = statsChildHead;
                } 
                else {
                    statsChildTail.setNext(newNode);
                    statsChildTail = statsChildTail.getNext();
                }
            }
            
            childNode = childNode.getNext();
        }
        
        if (statsChildHead != null) {
            statsNode.addChild(statsChildHead.getElement());
            SLLNode<TreeNode<String>> current = statsChildHead.getNext();
            while (current != null) {
                statsNode.addChild(current.getElement());
                current = current.getNext();
            }
        }
        
        return statsNode;
    }
    
    private Stats calculateStats(TreeNode<Integer> node) {
        if (node == null) {
            return new Stats(0, 0);
        }
        
        int count = 1; 
        int sum = node.getElement();
        
        SLLNode<TreeNode<Integer>> childNode = node.getChildren();
        while (childNode != null) {
            TreeNode<Integer> child = childNode.getElement();
            Stats childStats = calculateStats(child);
            count += childStats.count;
            sum += childStats.sum;
            childNode = childNode.getNext();
        }
        
        return new Stats(count, sum);
    }
}
