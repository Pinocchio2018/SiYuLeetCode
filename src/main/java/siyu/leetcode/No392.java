package siyu.leetcode;

import java.util.Arrays;

public class No392 {
    public static void main(String[] args) {
//        System.out.println(new No392().isSubsequence("acd", "abcde"));
//        System.out.println(new No392().isSubsequence("abc", "ahbgdc"));
//        System.out.println(new No392().isSubsequence("bb", "ahbgdc"));
        System.out.println(new No392().isSubsequence("twn", "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxtxxxxxxxxxxxxxxxxxxxxwxxxxxxxxxxxxxxxxxxxxxxxxxn"));


    }

    public boolean isSubsequence(String s, String t) {
        char[] strs = new char[t.length()];
        t.getChars(0, t.length(), strs, 0);

        char[] subStrs = new char[s.length()];
        s.getChars(0, s.length(), subStrs, 0);
        int index = 0;
        for (int i = 0; i < subStrs.length; i++) {

            index = search(strs, index, strs.length - 1, subStrs[i]);
            if (index < 0) {
                return false;
            }
            index++;

        }
        return true;
    }

    private int search(char[] strs, int startIndex, int endIndex, char targetChar) {
        for (int i = startIndex; i <= endIndex; i++) {
            if (strs[i] == targetChar) {
                return i;
            }
        }
        return -1;
    }
}
