package me.sirui.learn.leetcode.difficult;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * https://leetcode-cn.com/problems/longest-valid-parentheses/
 * 
 * @author Administrator
 *
 */
public class LongestValidParentheses {

    public static void main(String[] args) {
        long ts = System.nanoTime();
        String s = "(()";
        System.out.println((longestValidParentheses_1(s) == 2) + " " + (double) (System.nanoTime() - ts) / 10e6);
        ts = System.nanoTime();
        s = ")()())";
        System.out.println((longestValidParentheses_1(s) == 4) + " " + (double) (System.nanoTime() - ts) / 10e6);
        ts = System.nanoTime();
        s = "())()()(())((()(()()(((()))((((())((()(())()())(()((((()))()(()))(())()(())(()(((((())((((((()())())(()(()((())()))(()))))))()(()))((((())()()()))()()()(((()(()())(()()(()(()()(((()))))))()()))())())((()()))))))((()))(((()((())()(()()))((())))()()())))))))()))))(()))))()))()))()((())))((()))(()))))))(((()))))))))()(()()()(())((())()))()()(())))()()))(()())()))(((()())()))((())((((()))(()(()(()()()(((())()(((((()))((()(((((())(()()))((((((((()(()(()(()(())))(())(()())())(()((((()(())((()(())))(())))()(((((()(()()(())))))))())(())(())(()()(((())))((()))(((((()))))())))()((()))()))))())))))((())(((((()()))((((())))(((()(()(())())(((()(()(()()()())))())()))((()((())())()()()(((())(((((()((((((()((()())))((((())((()(((((((()(()((()()()(()(()())(()(()()((((())))()(((()())))(()()))()(()()()()(((((())(()))))((()))())))()((((((()))())))()(()))(())))((((()())(((((()()())(((((())(()())(()))))()(()()))()))))))())))(((())(()(()()))(()))()(((())))())((((()(((()))))))()(()(()))()()(()()))))))))((()))))))(())((()((()))()))((((((()())))))(()((())((((()))))(()(()()()()(()))()()(()(()))(()()(((((((()())(())(()())((())())()(()())((())()())())(()())))())))(())())())(())((()())(((()()))()))()()))()(()(())((((((((())))()((())((()((((((((((()))))(()(((((())(()(()())())))((())())))))()))(()((()()))((()((())()()()((()(())())((())())(()()(((())))))())()()(()))()())(()(()((())))((((()()(())))())(())(()(()(())())())(()()())()(())())))(()()(((())))((()()(((())()()(()())((((()()(()())(()((((()(()()(()(()(((()((()())(()()))(()((((()(((((()))))()()))(((()((((((()(()()()()())()))(()(())))))((()(((()())())))(((()()))(()(()(((((((()()))(()(())))())()(())())(())(()))(())(()))()()(()()())))))()))()((())(((()((((((((())()()))())))((()())(";
        System.out.println((longestValidParentheses_1(s) == 310) + " " + (double) (System.nanoTime() - ts) / 10e6);
    }

    // O(n^n)
    public static int longestValidParentheses(String s) {
        int len = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = s.length() - 1; j > i; j--) {
                String str = s.substring(i, j + 1);
                if (isValid(str) && str.length() > len) {
                    len = str.length();
                }
            }
        }
        return len;
    }

    private static boolean isValid(String str) {
        Deque<Character> deque = new ArrayDeque<>();
        for (char c : str.toCharArray()) {
            if (c == '(') {
                deque.push(')');
            } else if (deque.isEmpty() || c != deque.pop()) {
                return false;
            }
        }
        return deque.isEmpty();
    }

    // O(n)
    public static int longestValidParentheses_1(String s) {
        Deque<Integer> stack = new ArrayDeque<>();
        int len = 0;
        for (int i = 0, j = -1; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.addLast(i);
            } else {
                if (!stack.isEmpty()) {
                    stack.pollLast();
                    int top = j;
                    if (!stack.isEmpty()) {
                        top = stack.peekLast();
                    }
                    len = Math.max(len, i - top);
                } else {
                    j = i;
                }
            }
        }
        return len;
    }

    // O(2n)
    public static int longestValidParentheses_2(String s) {
        char[] chars = s.toCharArray();
        return Math.max(calc(chars, 0, 1, chars.length, '('), calc(chars, chars.length - 1, -1, -1, ')'));
    }

    private static int calc(char[] chars, int i, int flag, int end, char cTem) {
        int max = 0, sum = 0, currLen = 0, validLen = 0;
        for (; i != end; i += flag) {
            sum += (chars[i] == cTem ? 1 : -1);
            currLen++;
            if (sum < 0) {
                max = max > validLen ? max : validLen;
                sum = 0;
                currLen = 0;
                validLen = 0;
            } else if (sum == 0) {
                validLen = currLen;
            }
        }
        return max > validLen ? max : validLen;
    }
}
