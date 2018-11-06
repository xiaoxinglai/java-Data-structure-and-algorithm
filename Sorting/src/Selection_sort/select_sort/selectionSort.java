package src.Selection_sort.select_sort;

//选择排序（Selection sort）
//        是一种简单直观的排序算法。它的工作原理是每一次从待排序的数据元素中选出最小（或最大）的一个元素，
//        存放在序列的起始位置，直到全部待排序的数据元素排完。 选择排序是不稳定的排序方法。
//        已经排好顺序的情况下 交换0次；最坏情况交换n-1次，逆序交换n/2次。交换次数比冒泡排序少多了，
//        由于交换所需CPU时间比比较所需的CPU时间多，n值较小时，选择排序比冒泡排序快。
public class selectionSort {
    public static void main(String[] args) {
        int[] arr = new int[]{ 57,75,52,50,12,3,73,58,80,77,67,11,30,71,53};
        int index=0;
        for (int i=0;i<arr.length-1;i++){
            int max=0;
            for (int j=0;j<arr.length-1-i;j++){
                if (arr[j]>max){
                    max=arr[j];
                    index=j;
                }
            }
            //进行交换
            int temp=arr[arr.length-1-i];
            arr[arr.length-1-i]=max;
            arr[index]=temp;


            System.out.print("第"+i+"次排序:");
            printSort(arr);
            System.out.println();
        }



    }
    public static void printSort( int [] arr) {

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + ",");
        }
    }


}
