package src.merge_sort;

//归并排序
//归并排序（MERGE-SORT）是利用归并的思想实现的排序方法，
// 该算法采用经典的分治（divide-and-conquer）策略（分治法将问题分(divide)成一些小的问题然后递归求解，
// 而治(conquer)的阶段则将分的阶段得到的各答案"修补"在一起，即分而治之)。

//有点像快速排序，但是快速排序是在分的时候进行排序，归并排序是在合并的时候进行排序，快速排序核心在于交换那块，归并排序在在于合并那块

//归并排序是稳定排序，它也是一种十分高效的排序，能利用完全二叉树特性的排序一般性能都不会太差。
// java中Arrays.sort()采用了一种名为TimSort的排序算法，就是归并排序的优化版本。从上文的图中可看出，
// 每次合并操作的平均时间复杂度为O(n)，而完全二叉树的深度为|log2n|。总的平均时间复杂度为O(nlogn)。
// 而且，归并排序的最好，最坏，平均时间复杂度均为O(nlogn)。
public class merge_sort {
    public static void main(String[] args) {
        int[] arr = new int[]{57, 75, 52, 50, 12, 3, 73, 58, 80, 77, 67, 11, 30, 71, 53};
        int[] temp = new int[arr.length];
        mergeSort(arr, temp, 0, arr.length - 1);
    }

    //
    public static void mergeSort(int arr[], int temp[], int left, int right) {
        //将数组对半分成两部分  mid为分开的数组中点坐标
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, temp, left, mid);
            mergeSort(arr, temp, mid + 1, right);
            merge(arr, temp, mid, left, right);


            System.out.print("排序结果:");
            printSort(arr);
            System.out.println();
        }

    }

    public static void merge(int arr[], int temp[], int mid, int left, int right) {

        int i = left;//左序列指针
        int j = mid+1;//右序列指针
        int t = 0;//临时数组指针


        while(left<mid){

        }

         while(mid+1<right){
             if (arr[mid+1] < arr[left]) {
                 temp[i] = arr[mid + i + 1];
             } else {
                 temp[i] = arr[left];
                 left++;
             }
         }


//        for (int i : temp) {
//            arr[i] = temp[i];
//        }


    }


    public static void printSort(int[] arr) {

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + ",");
        }
    }
}
