package wz.acm.sort;

/**
 * Created by wangz on 17-11-17.
 */

import java.util.Arrays;
import java.util.Random;

/**
 * 两个子节点 2i+1, 2i+2
 * 父节点 (i-1)/2
 */
public class HeapSort {
    public static void main(String[] args) {
//        int[] array = {1, 3, 4, 5, 2, 6, 9, 7, 8, 0};
//        heapSort(array);

        getMinNumbers();// 求100个数中10个最小值
    }

    static void heapSort(int[] arr) {
        int len = arr.length;
        /**
         * 初始建堆过程,从第一个有子节点的父节点开始调整,直到堆顶调整完毕
         */
        for (int i = (len - 2) / 2; i >= 0; i--) {
            heapAdjust(arr, i, len);
        }
        System.out.print("初始堆 : ");
        print(arr);

        /**
         * 将堆顶最大值放到最后(和最后一个值交换,重新调整堆顶)
         */
        for (int i = len - 1; i > 0; i--) {
            swap(arr, 0, i);
            System.out.print("第" + (len - i) + "趟 : ");
            print(arr);
            heapAdjust(arr, 0, i);

        }

        System.out.print("RESULT : ");
        print(arr);
    }

    /**
     * 一次堆调整,从上到最底
     *
     * @param arr
     * @param parent
     * @param len
     */
    static void heapAdjust(int[] arr, int parent, int len) {
        int child = 2 * parent + 1;

        /**
         * 从上到下调整堆,直到所有的父节点都大于子节点的值
         */
        while (child < len) {
            //在有右子节点的情况下,且右子节点 > 左子节点
            if (child + 1 < len && arr[child + 1] > arr[child]) {
                child++;
            }

            //当parent的值大于子节点的值时,就是堆了,不用调整了
            if (arr[parent] >= arr[child]) {
                break;
            }

            //当父节点值小于子节点时,交换父子节点值,并继续向下调整
            swap(arr, parent, child);
            parent = child;
            child = 2 * parent + 1;
        }
    }

    static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    static void print(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }


    /**
     * 应用: 求100个数中的最小的10个数
     */
    static void getMinNumbers() {
        int[] nums = generateNums();
        int[] heap = Arrays.copyOfRange(nums, 0, 10); //存放10个最小的数

        /**
         * 初始建堆过程,求出10个数的堆顶最大值
         */
        int len = heap.length;
        for (int i = (len - 2) / 2; i >= 0; i--) {
            heapAdjust(heap, i, len);
        }


        /**
         * 将最大值和外部剩余值比较,将小的值交换进来,再调整堆
         */
        for(int i=10;i<100;i++){
            if(heap[0] > nums[i]){
                heap[0] = nums[i];
                heapAdjust(heap,0,10);
            }
        }

        print(nums);
        print(heap);
    }

    static int[] generateNums(){
        int[] nums = new int[100];
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            nums[i] = random.nextInt(100);
        }

        return nums;
    }
}
