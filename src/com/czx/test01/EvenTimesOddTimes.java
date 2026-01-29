package com.czx.test01;

/**
 * 时 O(n) 空 O(1)
 * (1) 一个数出现了奇数次，其他数出现了偶数次，求出现了奇数次的数
 * (2) 两个数出现了奇数次，其他数出现了偶数次，求这两个数
 */
public class EvenTimesOddTimes {

    // (1) 一个奇数次
    public static void printOddTimes(int[] arr) {
        int eor = 0;
        for (int num : arr) {
            eor ^= num;
        }
        System.out.println(eor);
    }

    // (2) 两个奇数次
    public static void printOddTimes02(int[] arr) {
        int eor = 0;
        for (int num : arr) {
            eor ^= num;
        }

        // eor = a ^ b, a != b → eor != 0
//        int rightOne = eor & (~eor + 1);
        int rightOne = eor & (-eor); // 更简洁的写法：取最右的1

        int onlyOne = 0;
        for (int num : arr) {
            if ((num & rightOne) == 0) { // 分组：该位为0的一组
                onlyOne ^= num;
            }
        }

        int theOther = eor ^ onlyOne;
        System.out.println(onlyOne + " " + theOther);
    }

    // 测试
    public static void main(String[] args) {
        int[] arr1 = {2, 1, 2, 3, 1, 1, 2, 1, 3, 2, 3}; // 奇数次：3（出现3次）
        printOddTimes(arr1); // 输出：3

        int[] arr2 = {1, 2, 3, 1, 2, 4}; // 奇数次：3 和 4
        printOddTimes02(arr2); // 输出：3 4（顺序可能不同）
    }
}