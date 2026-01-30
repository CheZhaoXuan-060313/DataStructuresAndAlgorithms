package com.czx.test03;

import java.util.*;

public class RadixSort {

    public static void radixSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }

        // 1. 找出数组中的最大值，确定需要循环的次数（位数）
        int max = Arrays.stream(arr).max().getAsInt();
        
        // 2. 从个位开始，对每一位进行计数排序
        // exp (exponent) 表示 10 的幂次，用来提取指定位上的数字
        // 个位: exp=1 (10^0), 十位: exp=10 (10^1)...
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingSortByDigit(arr, exp);
        }
    }

    /**
     * 按照指定位（个位、十位...）进行计数排序
     */
    private static void countingSortByDigit(int[] arr, int exp) {
        int n = arr.length;
        // 创建输出数组，用于暂存排序结果
        int[] output = new int[n];
        // 创建计数器数组（0-9），用于统计每个数字出现的次数
        int[] count = new int[10];

        // 1. 统计：统计当前位上每个数字出现的次数
        for (int j : arr) {
            int digit = (j / exp) % 10; // 提取当前位的数字
            count[digit]++;
        }

        // 2. 累加：将 count[i] 变为小于等于数字 i 的元素个数
        // 这一步是为了确定每个数字在 output 中的最终位置
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        // 3. 放置：从后往前遍历原数组，将元素放到 output 的正确位置
        // 从后往前是为了保证稳定性（相同数字的相对顺序不变）
        for (int i = n - 1; i >= 0; i--) {
            int digit = (arr[i] / exp) % 10;
            output[count[digit] - 1] = arr[i]; // count[digit]-1 就是该数字应放的位置
            count[digit]--; // 该位置数字计数减一
        }

        // 4. 将本轮排序结果复制回原数组
        System.arraycopy(output, 0, arr, 0, n);
    }

    // --- 测试 ---
    public static void main(String[] args) {
        int[] arr = {73, 22, 93, 43, 55, 14, 28, 65, 39, 81};
        
        System.out.println("排序前: " + Arrays.toString(arr));
        radixSort(arr);
        System.out.println("排序后: " + Arrays.toString(arr));
    }
}