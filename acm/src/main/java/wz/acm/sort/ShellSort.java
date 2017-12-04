package wz.acm.sort;

/**
 * Created by wangz on 17-11-15.
 */
public class ShellSort {

    static int count = 1;

    public static void main(String[] args) {
        int[] a = {3,1,5,7,2,4,9,6};
//        int[] a = {3,1};
//        int[] a = {3};
        shellSort(a);
    }


    static void shellSort(int[] a) {
        int len = a.length;

        int step = len / 2;
        while (step >= 1) {  // 步长初始为len/2,每次再/2,直到步长为1
            onceShellSort(a, step);     //每次跨步长的数组进行插入排序
            step /= 2;
        }

    }

    /**
     * 步长固定的多组插入排序
     * @param a
     * @param step
     */
    static void onceShellSort(int[] a, int step) {
        int len = a.length;
        for (int i = 0; i < step; i++) {
            insertSort(a, step, i);
        }
        System.out.println("第" + count++ + "趟: ");
        printArr(a);
    }

    /**
     * 跨步长组成的一个数组进行插入排序
     * @param a
     * @param step
     * @param startIndex
     */
    static void insertSort(int[] a, int step, int startIndex) {
        for (int j = startIndex + step; j < a.length; j += step) {
            if (a[j] < a[j - step]) {
                int temp = a[j];
                while (j - step >= 0 && a[j] < a[j - step]) {
                    a[j] = a[j - step];
                    j = j - step;
                }
                a[j] = temp;
            }
        }
    }

    static void printArr(int[] a) {
        for (int i : a) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
