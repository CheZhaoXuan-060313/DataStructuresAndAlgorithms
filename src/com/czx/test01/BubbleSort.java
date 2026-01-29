package com.czx.test01;

public class BubbleSort {
    public static void bubbleSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = arr.length -1 ; i >= 0; i--){
            for(int j = 0; j < i; j++){
                if(arr[j] > arr[j+1]){
                    swap(arr, j , j + 1);
                }
            }
        }
    }

    /*
    a ^ a = 0     0 ^ a = a
     */
    public static void swap(int[] arr, int a, int b){ //不建议这么写 不能 指向内存的同一个地方
        if(a != b){
            arr[a] = arr[a] ^ arr[b];
            arr[b] = arr[a] ^ arr[b];
            arr[a] = arr[b] ^ arr[a];
        }
    }
}
