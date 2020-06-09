package chuyou.jiang.algorithm.leetcode.array;


/**
 * @Author: ranter
 * @Date: 2020/6/10 12:53 上午
 * @Description: 回文数判断
 */
public class Palindrome {

    public static void main(String[] args) {
        System.out.println(isPalindrome2(10000001));
    }

    /**
     * 字符串数组判断回文
     * @param x
     * @return
     */
    public static boolean isPalindrome(int x) {
        String str = String.valueOf(x);
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length - 1; i ++) {
            if (chars[i] != chars[chars.length - i - 1]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 回文数之利用除余计算
     * @param x
     * @return
     */
    public static boolean isPalindrome2(int x) {

        //如果参数为 0，负数，个位数为0 那一定不为回文数
        if (x < 0 || (x%10 == 0 && x > 0)) {
            return false;
        }

        int reverseNumber = 0;
        while (x > reverseNumber) {
            reverseNumber = reverseNumber * 10 + x%10;
            x /= 10;
        }

        return x == reverseNumber || reverseNumber/10 == x;
    }
}
