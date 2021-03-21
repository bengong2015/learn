package me.sirui.learn.leetcode.difficult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/substring-with-concatenation-of-all-words/
 * 
 * @author Administrator
 *
 */
public class SubstringWithConcatenationOfAllWords {

    public static void main(String[] args) {
        String s = "barfoothefoobarman";
        String[] words = new String[] { "foo", "bar" };
        long ts = System.nanoTime();
        // expect [0,9] or [9,0]
        System.out.println(findSubstring(s, words) + " " + (double) (System.nanoTime() - ts) / 10e6);
        s = "wordgoodgoodgoodbestword";
        words = new String[] { "word", "good", "best", "word" };
        ts = System.nanoTime();
        // expect []
        System.out.println(findSubstring(s, words) + " " + (double) (System.nanoTime() - ts) / 10e6);
        s = "wordgoodgoodgoodbestword";
        words = new String[] { "word", "good", "best", "good" };
        ts = System.nanoTime();
        // expect [8]
        System.out.println(findSubstring(s, words) + " " + (double) (System.nanoTime() - ts) / 10e6);
        s = "ababaab";
        words = new String[] { "ab", "ba", "ba" };
        ts = System.nanoTime();
        // expect [1]
        System.out.println(findSubstring(s, words) + " " + (double) (System.nanoTime() - ts) / 10e6);
    }

    public static List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        int len = 0;
        Map<String, Integer> dict = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            String key = words[i];
            Integer count = dict.get(key);
            if (count == null) {
                dict.put(key, 1);
            } else {
                dict.put(key, count + 1);
            }
            len += key.length();
        }
        int max = s.length() - len + 1;
        for (int j = 0; j < max; j++) {
            String temp = s.substring(j, j + len);
            boolean flag = true;
            Iterator<String> iter = dict.keySet().iterator();
            while (iter.hasNext()) {
                String key = iter.next();
                if (getMatchedCount(key, temp) != dict.get(key)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                res.add(j);
            }
        }
        return res;
    }

    private static int getMatchedCount(String key, String str) {
        int count = 0;
        for (int i = 0; i < str.length() - key.length() + 1; i += key.length()) {
            String temp = str.substring(i, i + key.length());
            if (temp.equals(key)) {
                count++;
            }
        }
        return count;
    }
}
