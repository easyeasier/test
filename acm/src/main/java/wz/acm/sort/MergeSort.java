package wz.acm.sort;

import java.util.Arrays;

/**
 * Created by wangz on 17-11-15.
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] a = {3,1,5,7,2,4,9,6};
//        int[] a = {3,1};
        mergeSort(a);
        printArr(a);
    }

    /**
     * 合并算法 : 分而治之,注意递归的界限,和回溯
     * @param arr
     */
    static void mergeSort(int[] arr) {
        int middle = arr.length / 2;
        if(middle >= 1){
            int[] left = Arrays.copyOfRange(arr, 0, middle);
            int[] right = Arrays.copyOfRange(arr, middle, arr.length);

            mergeSort(left);
            mergeSort(right);
            merge(arr, left, right);
        }
    }

    /**
     * 分治后的合并
     * @param arr
     * @param left
     * @param right
     */
    static void merge(int[] arr, int[] left, int[] right) {
        int arrIndex = 0;
        int leftIndex = 0;
        int rightIndex = 0;

        while (leftIndex < left.length && rightIndex < right.length) {
            if (left[leftIndex] <= right[rightIndex]) {
                arr[arrIndex] = left[leftIndex];
                arrIndex++;
                leftIndex++;
            } else {
                arr[arrIndex] = right[rightIndex];
                arrIndex++;
                rightIndex++;
            }
        }

        while(leftIndex >= left.length && rightIndex < right.length){
            arr[arrIndex] = right[rightIndex];
            arrIndex++;
            rightIndex++;
        }

        while(rightIndex >= right.length && leftIndex < left.length){
            arr[arrIndex] = left[leftIndex];
            arrIndex++;
            leftIndex++;
        }
    }

    static void printArr(int[] a) {
        for (int i : a) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
