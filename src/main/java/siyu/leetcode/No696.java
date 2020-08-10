package siyu.leetcode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class No696 {

    public static void main(String[] args) {
        System.out.println("00110011的答案是：" + new No696().countBinarySubstrings("00110011") + "\n");
        System.out.println("10101的答案是：" + new No696().countBinarySubstrings("10101"));
    }

    public int countBinarySubstrings(String s) {
        char[] strArr = new char[s.length()];
        s.getChars(0, s.length(), strArr, 0);

        int tower0 = 0;
        int tower0max = 0;
        int tower1 = 0;
        int tower1Max = 0;
        Collection<String> result = new ArrayList<>();

        for (int i = 0; i < strArr.length; i++) {
            char item = strArr[i];
            if (item == '1') {
                tower1Max = ++tower1;
                if (tower0 + 1 <= 0) {
                    tower0++;
                    result.add(build0prefixStr(Math.abs(tower0max - tower0)));
                    if (tower0 == 0) {
                        tower0max = 0;
                    }
                }
            } else if (item == '0') {
                tower0max = --tower0;
                if (tower1 - 1 >= 0) {
                    tower1--;
                    result.add(build1prefixStr(Math.abs(tower1Max - tower1)));
                    if (tower1 == 0) {
                        tower1Max = 0;
                    }
                }
            }
        }
        return result.size();
    }

    private String build1prefixStr(int numsOf0) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numsOf0; i++) {
            sb.append('1');
        }
        for (int i = 0; i < numsOf0; i++) {
            sb.append('0');
        }
        String result = sb.toString();
        System.out.println(result);
        return result;
    }

    private String build0prefixStr(int numsOf1) {

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numsOf1; i++) {
            sb.append('0');
        }
        for (int i = 0; i < numsOf1; i++) {
            sb.append('1');
        }
        System.out.println(sb.toString());
        return sb.toString();
    }
}
