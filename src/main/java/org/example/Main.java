package org.example;

import java.util.*;

public class Main {
    public static void main(String[] args) {
    }

    public void maxProfit(int[] prices) {
        int min = prices[0];
        int maxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            maxProfit = Math.max(maxProfit, prices[i] - min);
            min = Math.min(min, prices[i]);
        }
    }

    public int strStr(String haystack, String needle) {
        if (haystack.contains(needle)) {
            return haystack.indexOf(needle);
        } else {
            return -1;
        }
    }

    public int majorityElement(int[] nums) {
        Map<Integer, Integer> elements = new HashMap<>();
        int max = 0;
        int value = 0;
        for (int i = 0; i < nums.length; i++) {
            if (!elements.containsKey(nums[i])) {
                elements.put(nums[i], 1);
            } else {
                elements.put(nums[i], elements.get(nums[i]) + 1);
            }
            if (elements.get(nums[i]) > max) {
                max = elements.get(nums[i]);
                value = nums[i];
            }
        }
        return value;
    }

    public boolean isPalindrome(String s) {
        s = s.toLowerCase().replaceAll("[^a-zA-Z0-9]", "");
        StringBuilder s2 = new StringBuilder(s).reverse();
        return s.contentEquals(s2);
    }

    public boolean canConstruct(String ransomNote, String magazine) {
        if (ransomNote.length() > magazine.length()) return false;
        int[] charactersCounter = new int[26];

        for (char c : magazine.toCharArray()) {
            charactersCounter[c - 'a']++;
        }

        for (char c : ransomNote.toCharArray()) {
            if (charactersCounter[c - 'a'] == 0) {
                return false;
            } else {
                charactersCounter[c - 'a']--;
            }
        }
        return true;
    }

    public boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) return false;

        int[] indexesS = new int[150];
        int[] indexesT = new int[150];

        for (int i = 0; i < s.length(); i++) {
            System.out.println("s: " + indexesS[s.charAt(i)] + ", t: " + indexesT[t.charAt(i)]);
            if (indexesS[s.charAt(i)] != indexesT[t.charAt(i)]) return false;

            indexesS[s.charAt(i)] = i + 1;
            indexesT[t.charAt(i)] = i + 1;

        }
        return true;
    }

    public boolean wordPattern(String pattern, String s) {
        String[] words = s.split(" ");
        Map<Character, String> map = new HashMap<>();

        if (pattern.length() != words.length) return false;

        for (int i = 0; i < pattern.length(); i++) {
            if (map.containsKey(pattern.charAt(i))) {
                String value = map.get(pattern.charAt(i));
                if (!value.equals(words[i])) return false;
            } else if (map.containsValue(words[i])) {
                return false;
            } else {
                map.put(pattern.charAt(i), words[i]);
            }
        }
        return true;
    }

    public boolean isAnagram(String s, String t) {
        char[] arrayS = s.toCharArray();
        char[] arrayT = t.toCharArray();

        Arrays.sort(arrayS);
        Arrays.sort(arrayT);

        return Arrays.equals(arrayT, arrayS);
    }

    public boolean isHappy(int n) {
        HashSet<Integer> nSet = new HashSet<>();

        while (nSet.add(n)) {
            int sum = 0;

            while (n > 0) {
                sum += (n % 10) * (n % 10);
                n /= 10;
            }

            if (sum == 1) {
                return true;
            } else {
                n = sum;
            }
        }
        return false;
    }

    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> numsMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (numsMap.containsKey(nums[i]) && Math.abs(numsMap.get(nums[i]) - i) <= k) {
                return true;
            }
            numsMap.put(nums[i], i);
        }
        return false;
    }

    public List<String> summaryRanges(int[] nums) {
        List<String> intervals = new ArrayList<>();
        if (nums.length == 1) {
            intervals.add(nums[0] + "");
            return intervals;
        }
        for (int i = 0; i < nums.length; i++) {
            int a = nums[i];

            while (i + 1 < nums.length && (nums[i + 1] - nums[i]) == 1) {
                i++;
            }
            if (a != nums[i]) {
                intervals.add(a + "->" + nums[i]);
            } else {
                intervals.add(a + "");
            }
        }
        return intervals;
    }

    public boolean isValid(String s) {
        Stack<Character> sStack = new Stack<>();

        for (char c: s.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                sStack.push(c);
            } else {
                if (sStack.isEmpty()) return false;
            }

            char top = sStack.peek();
            if ((c == ')' && top == '(') || (c == ']' && top == '[') || (c == '}' && top == '{')) {
                sStack.pop();
            } else {
                return false;
            }
        }
        return sStack.isEmpty();
    }

    public boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    public int maxDepth(TreeNode root) {
        if (root == null) return 0;

        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;

        if (p != null && q != null && p.val == q.val) {
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        }
        return false;
    }

    public TreeNode invertTree(TreeNode root) {
        if (root == null) return root;

        invertTree(root.left);
        invertTree(root.right);

        TreeNode current = root.left;
        root.left = root.right;
        root.right = current;

        return root;
    }

    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;

        return isMirror(root.left, root.right);
    }

    boolean isMirror(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) return true;
        if (root1 == null || root2 == null) return false;
        if (root1.val != root2.val) return false;

        return isMirror(root1.left, root2.right) && isMirror(root1.right, root2.left);
    }

    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false;

        if (root.left == null && root.right == null) {
            return root.val == targetSum;
        }

        boolean leftSum = hasPathSum(root.left, targetSum - root.val);
        boolean rightSum = hasPathSum(root.right, targetSum - root.val);

        return leftSum || rightSum;
    }

    int height(TreeNode root) {
        return root == null ? -1 : 1 + height(root.left);
    }
    public int countNodes(TreeNode root) {
        int h = height(root);
        return h < 0 ? 0 :
                height(root.right) == h-1 ? (1 << h) + countNodes(root.right)
                        : (1 << h-1) + countNodes(root.left);
    }
}