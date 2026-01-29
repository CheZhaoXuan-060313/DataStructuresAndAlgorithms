package com.czx.test02;

/**
 * 逆序对计数器（基于归并排序）
 *
 * 【问题定义】
 * 在数组中，若存在下标 i < j 且 arr[i] > arr[j]，则称 (i, j) 为一个逆序对。
 * 本类用于高效计算整个数组的逆序对总数。
 *
 * 【算法思想】
 * 利用归并排序的分治结构，在合并两个有序子数组时，
 * 利用“左子数组和右子数组各自有序”的性质，
 * 一次性统计跨越左右的逆序对数量。
 *
 * 【时间复杂度】O(n log n)
 * 【空间复杂度】O(n)
 * 【稳定性】稳定（相等元素不构成逆序对）
 */
public class InversionCount {

    /**
     * 入口方法：计算整个数组的逆序对总数
     * @param arr 输入数组（会被原地修改以完成排序）
     * @return 逆序对总数（使用 long 防止大数溢出）
     */
    public static long inversionCount(int[] arr) {
        // 安全边界检查：空数组或单元素无逆序对
        if (arr == null || arr.length < 2) {
            return 0L;
        }
        // 启动分治递归，处理整个区间 [0, n-1]
        return process(arr, 0, arr.length - 1);
    }

    /**
     * 递归分治方法：使 arr[l...r] 区间有序，并返回该区间内的逆序对数量
     * @param arr 原数组（会被修改）
     * @param l 当前处理区间的左边界（包含）
     * @param r 当前处理区间的右边界（包含）
     * @return arr[l...r] 中的逆序对总数
     */
    public static long process(int[] arr, int l, int r) {
        // 基线条件：单个元素无法构成逆序对
        if (l == r) {
            return 0L;
        }

        // 计算中点，避免 (l + r) 溢出，使用位运算优化除法
        int m = l + ((r - l) >> 1);

        // 递归处理左半部分 + 右半部分 + 合并时产生的跨区逆序对
        return process(arr, l, m)          // 左子数组 [l, m] 的逆序对
                + process(arr, m + 1, r)      // 右子数组 [m+1, r] 的逆序对
                + merge(arr, l, m, r);        // 跨越左右的逆序对（在 merge 中统计）
    }

    /**
     * 合并两个已有序的子数组 [l, m] 和 [m+1, r]，
     * 并统计本次合并过程中产生的逆序对数量。
     * 前提：arr[l...m] 和 arr[m+1...r] 均为升序。
     *
     * 【核心思想】
     * 当 arr[p1] > arr[p2] 时，由于左子数组有序，
     * arr[p1], arr[p1+1], ..., arr[m] 都 > arr[p2]，
     * 因此共产生 (m - p1 + 1) 个逆序对。
     *
     * @param arr 原数组
     * @param l 左子数组起始索引
     * @param m 左子数组结束索引
     * @param r 右子数组结束索引
     * @return 本次合并产生的逆序对数量
     */
    public static long merge(int[] arr, int l, int m, int r) {
        // 创建辅助数组，用于暂存合并后的有序序列
        int[] help = new int[r - l + 1];
        int i = 0;                // help 数组的写入指针
        int p1 = l;               // 左子数组读取指针，范围 [l, m]
        int p2 = m + 1;           // 右子数组读取指针，范围 [m+1, r]
        long count = 0;           // 逆序对计数器（必须用 long 防止累加溢出）

        // 双指针合并两个有序数组
        while (p1 <= m && p2 <= r) {
            if (arr[p1] <= arr[p2]) {
                // 左 <= 右：不构成逆序对，直接放入 help
                help[i++] = arr[p1++];
            } else {
                // 左 > 右：构成逆序对！
                // 且左子数组从 p1 到 m 的所有元素都 > arr[p2]
                count += (m - p1 + 1); // 关键公式：批量统计逆序对
                help[i++] = arr[p2++];
            }
        }

        // 处理左子数组剩余元素（右子数组已空，不再产生逆序对）
        while (p1 <= m) {
            help[i++] = arr[p1++];
        }

        // 处理右子数组剩余元素（左子数组已空，不再产生逆序对）
        while (p2 <= r) {
            help[i++] = arr[p2++];
        }

        // ⭐ 必须将合并后的有序结果拷贝回原数组
        // 保证上层递归调用时，子数组是有序的（归并排序正确性的基础）
        for (int k = 0; k < help.length; k++) {
            arr[l + k] = help[k];
        }

        return count;
    }
}