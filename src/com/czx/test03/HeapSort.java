package com.czx.test03;

public class HeapSort {



    public static void heapify(int[] arr, int index, int heapSize) {
        int left = index * 2 + 1;
        while(left < heapSize){
            int largest = left + 1 < heapSize &&arr[left + 1] > arr[left]  ? left + 1 : left;

            largest = arr[index] > arr[largest] ? index : largest;

            if(largest == index) {
                break;
            }

            swap(arr, index, largest);
            index = largest;
            left = index * 2 + 1;
        }
    }

    public static void heapInsert(int[] arr, int index){
        while(index > 0 && arr[index] > arr[((index-1) /2)]){
            swap(arr,index,(index-1) / 2);
            index = (index-1) / 2;
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
