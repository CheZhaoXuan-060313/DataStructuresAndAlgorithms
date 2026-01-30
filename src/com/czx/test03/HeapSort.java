package com.czx.test03;

public class HeapSort {

    /**
     * 堆排序主方法
     * @param arr 待排序的数组
     */
    public static void heapSort(int[] arr) {
        // 如果数组为空或者只有一个元素，无需排序
        if(arr == null || arr.length < 2) {
            return;
        }

        // 1. 建堆阶段：使用插入法构建大根堆
        // 依次将数组中的每个元素插入到堆中，并通过上浮调整维持堆结构
        for(int i = 0; i < arr.length; i++) {
            heapInsert(arr, i);
        }

        // 2. 排序阶段
        // heapSize 表示当前堆的有效范围（未排序部分的长度）
        int heapSize = arr.length;

        // 当堆的大小大于1时，继续排序
        // 注意：这里先执行 heapify，再执行 swap
        while(heapSize > 1) {

            // 先调整堆：确保堆顶（索引0）是当前堆中的最大值
            // （虽然建堆后堆顶已经是最大值，但这一步是为了处理后续交换回来的元素）
            heapify(arr, 0, heapSize);

            // 后交换：将堆顶最大值与堆的最后一个元素交换
            // --heapSize：先将堆的大小减1，表示最大值已经归位（移到数组末尾），不再参与后续调整
            swap(arr, 0, --heapSize);
        }
        // 循环结束时，数组即为有序
    }

    /**
     * 向下调整方法（维护堆的性质）
     * 从指定索引开始，将节点向下调整，使其满足大根堆的性质
     * @param arr 数组
     * @param index 当前需要调整的节点索引
     * @param heapSize 堆的有效大小（边界）
     */
    public static void heapify(int[] arr, int index, int heapSize) {
        // 计算左孩子索引
        int left = index * 2 + 1;

        // 如果左孩子存在（即当前节点不是叶子节点），则循环继续
        while(left < heapSize){

            // 1. 找出左右孩子中的较大值索引
            // 判断右孩子是否存在且大于左孩子
            int largest = left + 1 < heapSize && arr[left + 1] > arr[left]  ? left + 1 : left;

            // 2. 比较父节点和孩子节点
            // 如果父节点比孩子大，则父节点就是最大值；否则最大值是孩子节点
            largest = arr[index] > arr[largest] ? index : largest;

            // 3. 如果父节点就是最大值，堆性质已满足，无需调整，直接跳出
            if(largest == index) {
                break;
            }

            // 4. 如果孩子更大，则交换父节点和孩子节点
            swap(arr, index, largest);

            // 5. 继续向下调整
            // 将 index 指向被交换的孩子位置
            index = largest;
            // 重新计算左孩子索引
            left = index * 2 + 1;
        }
    }

    /**
     * 向上调整方法（构建堆）
     * 将指定位置的元素与其父节点比较，若大于父节点则交换，直到满足堆性质
     * @param arr 数组
     * @param index 待调整元素的索引
     */
    public static void heapInsert(int[] arr, int index){
        // 当前节点大于父节点且不是根节点时，继续向上交换
        while(index > 0 && arr[index] > arr[((index-1) /2)]){
            swap(arr, index, (index-1) / 2);
            // 指针移向父节点
            index = (index-1) / 2;
        }
    }

    /**
     * 交换数组中两个位置的元素
     * @param arr 数组
     * @param i 索引1
     * @param j 索引2
     */
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}