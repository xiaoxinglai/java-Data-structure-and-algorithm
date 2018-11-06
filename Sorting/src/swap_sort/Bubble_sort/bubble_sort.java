package src.swap_sort.Bubble_sort;

//冒泡排序 时间复杂度为O（N*N） 最好为O(N) 这种情况为一开始就排好的情况
//原理：
//比较相邻的元素。如果第一个比第二个大，就交换他们两个。
//对每一对相邻元素做同样的工作，从开始第一对到结尾的最后一对。在这一点，最后的元素应该会是最大的数。
//        针对所有的元素重复以上的步骤，除了最后一个。
//        持续每次对越来越少的元素重复上面的步骤，直到没有任何一对数字需要比较
public class bubble_sort {

    public static void main(String[] args) {

        int [] arr=new int[]{2,9,1,3,4,5,2,12,43,8};//,4,5,2,12,43,8
        int temp=0;
        for (int i=0;i<arr.length;i++){  //最外层循环用于遍历每个数字 都要比较，也就是控制比较的轮数 ，有几个数字 就要比较多少轮
            for (int j=0;j<arr.length-i-1;j++){ //内层循环用于控制比较的数字位数，同时控制一轮比较的次数

                if (arr[j]>arr[j+1]){
                    temp=arr[j+1];
                    arr[j+1]=arr[j];
                    arr[j]=temp;
                }
            }


            System.out.print("第"+i+"次排序结果：");
            printSort(arr);
        }

    }


        public static void printSort( int [] arr) {

            for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + ",");
        }
    }
}
