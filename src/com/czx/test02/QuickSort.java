package com.czx.test02;

public class QuickSort {

    /**
     * 快速排序入口方法
     * 对外提供简洁的排序接口
     * @param arr 待排序的整型数组
     */
    public static void quickSort(int[] arr){
        // 边界检查：如果数组为空或只有一个元素，则无需排序
        if(arr == null || arr.length < 2){
            return;
        }
        // 调用递归方法，对整个数组进行排序
        quickSort(arr, 0, arr.length - 1);
    }

    /**
     * 快速排序递归方法
     * 核心逻辑：分治法。将数组分为两部分，分别排序。
     * @param arr 待排序的数组
     * @param left 当前处理的左边界索引
     * @param right 当前处理的右边界索引
     */
    public static void quickSort(int[] arr, int left, int right) {
        // 递归终止条件：当左边界大于等于右边界时，说明子数组长度 <= 1，已有序
        if (left >= right) {
            return;
        }

        // 1. 随机化分区：获取基准点（Pivot）的最终位置
        int randomPivot = randomizedPartition(arr, left, right);

        // 2. 递归排序基准点左边的子数组 [left, randomPivot - 1]
        quickSort(arr, left, randomPivot - 1);

        // 3. 递归排序基准点右边的子数组 [randomPivot + 1, right]
        quickSort(arr, randomPivot + 1, right);
    }

    /**
     * 随机化分区方法
     * 随机选择一个元素与末尾元素交换，然后进行分区。
     * 这种做法可以避免在已排序数组上出现最坏时间复杂度 O(n²)。
     * @param arr 数组
     * @param left 左边界
     * @param right 右边界
     * @return 基准元素排序后所在的索引位置
     */
    public static int randomizedPartition(int[] arr, int left, int right){
        // 生成 [left, right] 范围内的随机索引
        int randomIndex = left + (int)(Math.random() * (right - left + 1));

        // 将随机选中的元素与右边界元素交换
        // 这样后续的 partition 方法依然可以简单地取 arr[right] 作为基准
        swap(arr, randomIndex, right);

        // 执行标准的分区操作
        return partition(arr, left, right);
    }

    /**
     * 分区方法
     * 核心逻辑：将小于基准的元素移到左边，大于基准的元素移到右边。
     * @param arr 数组
     * @param left 左边界
     * @param right 右边界
     * @return 基准元素最终应处的位置索引
     */
    public static int partition(int[] arr, int left, int right) {
        // 选取最右边的元素作为基准值 (Pivot)
        int pivot = arr[right];

        // i 指针指向“小于 pivot 区域”的最后一个位置
        // 初始时，该区域为空，所以 i 从 left-1 开始
        int i = left - 1;

        // j 指针用于遍历数组，扫描范围 [left, right-1]
        // 注意：不包含 right，因为 right 是基准自己，不需要比较
        for(int j = left; j < right; j++){
            // 如果当前元素小于基准值
            if(arr[j] < pivot){
                // 扩展“小于区域”，i 向右移动一位
                i++;
                // 将该元素交换到“小于区域”中
                swap(arr, i, j);
            }
            // 如果 arr[j] >= pivot，j 继续向右移动，该元素属于“大于等于区域”
        }

        // 循环结束后：
        // i 指向最后一个比 pivot 小的元素
        // i+1 位置是 pivot 应该在的正确位置（右边都是 >= pivot 的数）

        // 将基准元素（当前在 right 位置）放到正确位置 i+1
        swap(arr, i + 1, right);

        // 返回基准元素的索引
        return i + 1;
    }

    /**
     * 交换数组中两个位置的元素
     * 使用异或（XOR）技巧实现交换，无需额外的临时变量。
     * @param arr 数组
     * @param i 索引1
     * @param j 索引2
     */
    public static void swap(int[] arr, int i, int j) {
        // 防止 i 和 j 是同一个位置，否则异或会导致数据清零
        if (i != j) {
            arr[i] = arr[i] ^ arr[j];
            arr[j] = arr[i] ^ arr[j];
            arr[i] = arr[i] ^ arr[j];
        }
    }

    // --- 测试主方法 ---
    public static void main(String[] args) {
        int[] arr = {3, 1, 4, 1, 5, 9, 2, 6};
        System.out.println("排序前: " + java.util.Arrays.toString(arr));

        quickSort(arr);

        System.out.println("排序后: " + java.util.Arrays.toString(arr));
    }
}