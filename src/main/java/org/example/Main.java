package org.example;

import java.util.*;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
//        int[] prices = {7, 1, 5, 3, 6, 4};
//        int[] nums = {6, 5, 5};
////        maxProfit(prices);
//        //strStr("sadbutsad", "sad");
//        System.out.println(majorityElement(nums));
        isIsomorphic("bbbaaaba", "aaabbbba");
    }

    public static void maxProfit(int[] prices) {
        int min = prices[0];
        int maxProfit = 0;
        for(int i=1; i<prices.length; i++) {
            maxProfit = Math.max(maxProfit, prices[i] - min);
            min = Math.min(min, prices[i]);
        }
    }

    public static int strStr(String haystack, String needle) {
        if (haystack.contains(needle)) {
            return haystack.indexOf(needle);
        }
        else {
            return - 1;
        }
    }

    public static int majorityElement(int[] nums) {
        Map<Integer, Integer> elements = new HashMap<>();
        int max = 0;
        int value = 0;
        for(int i=0; i<nums.length; i++) {
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

    public static boolean isPalindrome(String s) {
        s = s.toLowerCase().replaceAll("[^a-zA-Z0-9]", "");
        StringBuilder s2 = new StringBuilder(s).reverse();
        return s.contentEquals(s2);
    }

    public static boolean canConstruct(String ransomNote, String magazine) {
        if (ransomNote.length() > magazine.length()) return false;
        int[] charactersCounter = new int[26];

        for (char c : magazine.toCharArray()) {
                charactersCounter[c - 'a']++;
        }

        for (char c: ransomNote.toCharArray()) {
            if (charactersCounter[c - 'a'] == 0) {
                return false;
            } else {
                charactersCounter[c - 'a']--;
            }
        }
        return true;
    }

    public static boolean isIsomorphic(String s, String t) {
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

    public static boolean wordPattern(String pattern, String s) {
        String[] words = s.split(" ");
        Map<Character, String> map = new HashMap<>();

        if (pattern.length() != words.length) return false;

        for (int i = 0; i < pattern.length(); i++) {
            if (map.containsKey(pattern.charAt(i))) {
                String value = map.get(pattern.charAt(i));
                if (!value.equals(words[i])) return false;
            } else if (map.containsValue(words[i])){
                return false;
            } else {
                map.put(pattern.charAt(i), words[i]);
            }
        }
        return true;
    }
}