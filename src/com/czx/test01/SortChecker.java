package com.czx.test01;

import java.util.Arrays;
import java.util.Random;

public class SortChecker {  //对数器
    // 1. 生成随机测试数组
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        Random random = new Random();
        int size = random.nextInt(maxSize + 1); // [0, maxSize]
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt(maxValue + 1) - random.nextInt(maxValue + 1); // [-maxValue, maxValue]
        }
        return arr;
    }

    // 2. 复制数组（避免原地修改影响对比）
    public static int[] copyArray(int[] arr) {
        if (arr == null) return null;
        return arr.clone();
    }

    // 3. 使用系统排序作为“标准答案”
    public static void rightSort(int[] arr) {
        Arrays.sort(arr);
    }

    // 4. 比较两个数组是否相等
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if (arr1 == null && arr2 == null) return true;
        if (arr1 == null || arr2 == null) return false;
        if (arr1.length != arr2.length) return false;
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) return false;
        }
        return true;
    }

    // 5. 主测试逻辑
    public static void main(String[] args) {
        int testTime = 10000;      // 测试次数
        int maxSize = 100;         // 最大数组长度
        int maxValue = 100;        // 元素最大绝对值

        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            int[] arr3 = copyArray(arr1);

            InsertSort.insertSort(arr1); // 你的算法
            rightSort(arr2);             // 标准答案

            if (!isEqual(arr1, arr2)) {
                System.out.println("❌ 出错了！");
                System.out.println("原数组: " + Arrays.toString(arr3));
                System.out.println("你的结果: " + Arrays.toString(arr1));
                System.out.println("正确结果: " + Arrays.toString(arr2));
                return;
            }
        }

        System.out.println("✅ 所有测试通过！");
    }
}
