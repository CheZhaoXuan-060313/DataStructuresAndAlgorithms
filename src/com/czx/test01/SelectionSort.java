package com.czx.test01;

public class SelectionSort {
    public static void selectionSort(int[] arr) {
        if(arr == null || arr.length < 2) {
            return;
        }
        for (int i= 0; i < arr.length; i++) { //i ~ N-1
            int minIndex = i;
            for(int j = i+1; j <arr.length; j++){
                minIndex = arr[j] < arr[minIndex] ? j : minIndex;
            }
            swap(arr, i, minIndex);
        }
    }
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    // 额外空间复杂度O(1)
    //只用来 i j minIndex(释放后再创建) 有限个额外空间
}
