package chuyou.jiang.algorithm.leetcode.array;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: ranter
 * @Date: 2020/6/9 10:07 下午
 */
public class TwoNumSum {

    public static void main(String[] args) {
        int[] nums = new int[] {2,7,11,15};
        //int[] result = twoSum(nums, 9);
        int[] result = twoSum2(nums, 9);
        for (int num : result) {
            System.out.println(num);
        }
    }

    /**
     * 两数之和 - k-v
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> hasMap = new HashMap<>();
        for (int i = 0; i < nums.length; i ++) {
            if (hasMap.containsKey(nums[i])) {
                return new int[]{i, hasMap.get(nums[i])};
            }
            hasMap.put(target - nums[i], i);
        }
        return null;
    }

    /**
     * 两数之和 双重for循环
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum2(int[] nums, int target) {
        for (int i = 0; i < nums.length - 1; i ++) {
            for (int j = i + 1; j < nums.length; j ++) {
                if (nums[i] + nums[j] == target) {
                    return new int[] {i, j};
                }
            }
        }
        return null;
    }
}
