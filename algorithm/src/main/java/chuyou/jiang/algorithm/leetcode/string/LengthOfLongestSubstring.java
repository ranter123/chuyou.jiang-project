package chuyou.jiang.algorithm.leetcode.string;

/**
 * @Author: ranter
 * @Date: 2020/6/21 9:24 下午
 * @Description:
 */
public class LengthOfLongestSubstring {

    public static void main(String[] args) {
        String example1 = "abcabcbb";
        String example2 = "bbbbb";
        String example3 = "pwwkew";
        String example4 = "";
        String example5 = "dvdf";
        String example6 = "abba";
        String example7 = "jbpnbwwd";
        String example8 = "aabaab!bb";
        String example9 = "zwnigfunjwz";
        System.out.println(lengthOfLongestSubstring(example1));
        System.out.println(lengthOfLongestSubstring(example2));
        System.out.println(lengthOfLongestSubstring(example3));
        System.out.println(lengthOfLongestSubstring(example4));
        System.out.println(lengthOfLongestSubstring(example5));
        System.out.println(lengthOfLongestSubstring(example6));
        System.out.println(lengthOfLongestSubstring(example7));
        System.out.println(lengthOfLongestSubstring(example8));
        System.out.println(lengthOfLongestSubstring(example9));
    }

    public static int lengthOfLongestSubstring(String s) {
        //定义最长字符串
        String str = "";
        int cursor = 0;
        String tmpStr = "";
        for (int i = 1; i <= s.length(); i ++) {
            if (!tmpStr.contains(String.valueOf(s.charAt(i - 1)))) {
                tmpStr = s.substring(cursor, i);
            } else {
                int teCursor = s.substring(0, i - 1).lastIndexOf(s.charAt(i - 1)) + 1;
                if (teCursor >= cursor) {
                    cursor = teCursor;
                }
                continue;
            }
            //如果字符串长度大于 存储的字符串长度
            if (!str.equals(tmpStr) && tmpStr.length() >= str.length()) {
                str = tmpStr;
            }
        }
        System.out.println("字符串" + s + "中，最长不重复字符子串为：" + str);
        return str.length();
    }
}
