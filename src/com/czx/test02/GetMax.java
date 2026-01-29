package com.czx.test02;

/**
 * 使用分治法（Divide and Conquer）递归查找数组中的最大值
 */
public class GetMax {

    /**
     * 对外公开的接口方法：获取整个数组的最大值
     * @param arr 输入的整型数组（要求非空）
     * @return 数组中的最大值
     */
    public static int GetMax(int[] arr) {
        // 从数组的第一个元素（索引0）到最后一个元素（索引arr.length-1）开始递归处理
        return process(arr, 0, arr.length - 1);
    }

    /**
     * 递归核心方法：在子数组 arr[L...R] 范围内查找最大值
     * @param arr 原始数组（不被修改，只读）
     * @param L 当前处理区间的左边界（包含）
     * @param R 当前处理区间的右边界（包含）
     * @return arr[L] 到 arr[R] 中的最大值
     */
    public static int process(int[] arr, int L, int R) {
        // 【递归终止条件】当区间只有一个元素时，该元素就是最大值
        if (L == R) {
            return arr[L];
        }

        /*
         * 分治策略示意图（以长度为8的数组为例）：
         *
         *                           process(0,7)
         *                         /            \
         *                 process(0,3)        process(4,7)
         *                /          \         /           \
         *         process(0,1)   process(2,3)  process(4,5)  process(6,7)
         *         /        \      /        \    /        \    /        \
         *    (0,0)=5   (1,1)=12 (2,2)=3 (3,3)=9 (4,4)=15 (5,5)=6 (6,6)=11 (7,7)=8

         * 每次将当前区间 [L, R] 二分为 [L, mid] 和 [mid+1, R]，
         * 递归求解左右两部分的最大值，再取两者较大者作为当前区间的最大值。
         */

        // 计算中点 mid，避免 (L + R) 可能导致的整数溢出
        // (R - L) >> 1 等价于 (R - L) / 2（使用位运算效率更高）
        // 所以 mid = L + (R - L) / 2，确保 mid 始终在 [L, R) 范围内
        int mid = L + ((R - L) >> 1);

        // 递归求解左半部分 [L, mid] 的最大值
        int leftMax = process(arr, L, mid);

        // 递归求解右半部分 [mid + 1, R] 的最大值
        int rightMax = process(arr, mid + 1, R);

        // 合并结果：当前区间 [L, R] 的最大值 = 左右最大值中的较大者
        return Math.max(leftMax, rightMax);
    }
}