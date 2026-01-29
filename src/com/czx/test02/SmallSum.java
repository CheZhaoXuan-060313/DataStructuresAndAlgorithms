package com.czx.test02;

/**
 * 小和问题（Small Sum）求解器
 *
 * 【问题定义】
 * 对于数组中的每个元素 arr[i]，计算它左边所有比它小的数的总和，
 * 所有元素的小和之和即为整个数组的“小和”。
 *
 * 【示例】
 * arr = [1, 3, 4, 2, 5]
 * - 1 左边无元素 → 0
 * - 3 左边 <3 的有 1 → +1
 * - 4 左边 <4 的有 1,3 → +4
 * - 2 左边 <2 的有 1 → +1
 * - 5 左边 <5 的有 1,3,4,2 → +10
 * 总小和 = 0 + 1 + 4 + 1 + 10 = 16
 *
 * 【解法】
 * 利用归并排序的分治思想，在合并两个有序子数组时，
 * 高效计算跨越左右子数组的小和贡献。
 *
 * 时间复杂度：O(n log n)
 * 空间复杂度：O(n)
 */
public class SmallSum {

    /**
     * 计算整个数组的小和（入口方法）
     * @param arr 输入数组
     * @return 数组的小和
     */
    public static int smallSum(int[] arr) {
        // 边界处理：空数组或单个元素的小和为 0
        if (arr == null || arr.length < 2) {
            return 0;
        }
        // 启动分治递归，处理整个数组 [0, n-1]
        return process(arr, 0, arr.length - 1);
    }

    /**
     * 递归分治方法：计算 arr[l...r] 范围内的小和，并使该区间有序
     * @param arr 原数组（会被原地修改以保证有序性）
     * @param l 当前处理区间的左边界（包含）
     * @param r 当前处理区间的右边界（包含）
     * @return arr[l...r] 区间的小和
     */
    public static int process(int[] arr, int l, int r) {
        // 基线条件：单个元素没有左侧元素，小和为 0
        if (l == r) {
            return 0;
        }

        // 计算中点，避免 (l + r) 溢出，使用位运算优化
        int m = l + ((r - l) >> 1);

        // 递归计算左半部分小和 + 右半部分小和 + 跨越中点的小和
        return process(arr, l, m)          // 左子数组 [l, m] 的小和
                + process(arr, m + 1, r)      // 右子数组 [m+1, r] 的小和
                + merge(arr, l, m, r);        // 跨越左右的小和（在合并时计算）
    }

    /**
     * 合并两个有序子数组 [l, m] 和 [m+1, r]，并计算本次合并产生的小和
     * 前提：arr[l...m] 和 arr[m+1...r] 均已有序
     * @param arr 原数组
     * @param l 左子数组起始索引
     * @param m 左子数组结束索引
     * @param r 右子数组结束索引
     * @return 本次合并过程中产生的小和（即左子数组中元素对右子数组的贡献）
     */
    public static int merge(int[] arr, int l, int m, int r) {
        // 创建辅助数组，用于暂存合并后的有序序列
        int[] help = new int[r - l + 1];
        int i = 0;                // help 数组的写入指针
        int p1 = l;               // 左子数组读取指针，范围 [l, m]
        int p2 = m + 1;           // 右子数组读取指针，范围 [m+1, r]
        int res = 0;              // 本次合并产生的小和

        // 双指针合并，同时计算小和
        while (p1 <= m && p2 <= r) {
            if (arr[p1] < arr[p2]) {
                // arr[p1] 比 arr[p2] 小
                // 由于右子数组有序，arr[p1] 也小于 arr[p2], arr[p2+1], ..., arr[r]
                // 共有 (r - p2 + 1) 个这样的元素
                res += arr[p1] * (r - p2 + 1);
                help[i++] = arr[p1++];
            } else {
                // arr[p1] >= arr[p2]，不产生小和
                help[i++] = arr[p2++];
            }
        }

        // 处理左子数组剩余元素（右子数组已空，不再产生小和）
        while (p1 <= m) {
            help[i++] = arr[p1++];
        }

        // 处理右子数组剩余元素（左子数组已空，不再产生小和）
        while (p2 <= r) {
            help[i++] = arr[p2++];
        }

        // ⭐ 关键：将合并后的有序结果拷贝回原数组
        // 保证上层递归调用时，子数组是有序的（归并排序正确性的基础）
        for (int k = 0; k < help.length; k++) {
            arr[l + k] = help[k];
        }

        return res;
    }
}