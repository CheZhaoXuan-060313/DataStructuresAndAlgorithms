package com.czx.test02;

/**
 * 归并排序（Merge Sort）实现类
 * 基于分治思想（Divide and Conquer）：
 *   - 分：将数组不断二分，直到子数组长度为1（天然有序）
 *   - 治：通过 merge 操作，将两个有序子数组合并为一个更大的有序数组
 * 时间复杂度：O(n log n)
 * 空间复杂度：O(n)
 * 稳定性：稳定排序（相等元素的相对位置不会改变）
 */
public class MergeSort {

    /**
     * 归并排序入口方法
     * @param arr 待排序的整型数组
     */
    public static void mergeSort(int[] arr) {
        // 边界条件：空数组或只有一个元素，无需排序
        if (arr == null || arr.length < 2) {
            return;
        }
        // 启动递归分治过程，对整个数组 [0, n-1] 进行排序
        process(arr, 0, arr.length - 1);
    }

    /**
     * 递归分治方法：确保 arr[L...R] 区间最终变为有序
     * 采用后序遍历（左 → 右 → 根）方式处理
     * @param arr 原数组（会被原地修改）
     * @param L 当前处理区间的左边界（包含）
     * @param R 当前处理区间的右边界（包含）
     */
    public static void process(int[] arr, int L, int R) {
        // 基线条件：单个元素天然有序，直接返回
        if (L == R) {
            return;
        }

        // 计算中点，避免 (L + R) 可能溢出（使用位运算优化除法）
        int mid = L + ((R - L) >> 1);  // 等价于 (L + R) / 2，但更安全高效

        // 递归处理左半部分 [L, mid]
        process(arr, L, mid);

        // 递归处理右半部分 [mid+1, R]
        process(arr, mid + 1, R);

        // 此时左右两部分均已有序，合并它们
        merge(arr, L, mid, R);
    }

    /**
     * 合并两个有序子数组：[L, M] 和 [M+1, R]
     * 前提：arr[L...M] 和 arr[M+1...R] 都是升序的
     * @param arr 原数组
     * @param L 左子数组起始索引
     * @param M 左子数组结束索引（右子数组从 M+1 开始）
     * @param R 右子数组结束索引
     */
    public static void merge(int[] arr, int L, int M, int R) {
        // 创建辅助数组，大小为合并区间的长度
        int[] help = new int[R - L + 1];

        int i = 0;           // help 数组的写入指针
        int p1 = L;          // 左子数组的读取指针，范围 [L, M]
        int p2 = M + 1;      // 右子数组的读取指针，范围 [M+1, R]

        // 双指针比较，将较小值依次放入 help 数组
        while (p1 <= M && p2 <= R) {
            // 使用 <= 保证稳定性（相等时优先取左边的元素）
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }

        // 若左子数组还有剩余，全部拷贝到 help（右子数组已空）
        while (p1 <= M) {
            help[i++] = arr[p1++];
        }

        // 若右子数组还有剩余，全部拷贝到 help（左子数组已空）
        while (p2 <= R) {
            help[i++] = arr[p2++];
        }

        // 将合并后的有序结果从 help 拷贝回原数组的 [L, R] 区间
        for (int j = 0; j < help.length; j++) {
            arr[L + j] = help[j];
        }
    }
}