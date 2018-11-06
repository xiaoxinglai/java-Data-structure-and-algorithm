package src.insert_sort.shell_sort;

//希尔排序是把记录按下标的一定增量分组，对每组使用直接插入排序算法排序；
//随着增量逐渐减少，每组包含的关键词越来越多，当增量减至1时，整个文件恰被分成一组，算法便终止。
//希尔排序是基于插入排序的以下两点性质而提出改进方法的：
//插入排序在对几乎已经排好序的数据操作时，效率高，即可以达到线性排序的效率。
//但插入排序一般来说是低效的，因为插入排序每次只能将数据移动一位。


//算法先将要排序的一组数按某个增量d分成若干组，每组中记录的下标相差d.
// 对每组中全部元素进行排序，然后再用一个较小的增量对它进行，在每组中再进行排序。当增量减到1时，整个要排序的数被分成一组，排序完成。
//一般的初次取序列的一半为增量，以后每次减半，直到增量为1。

//由于多次插入排序，我们知道一次插入排序是稳定的，不会改变相同元素的相对顺序，但在不同的插入排序过程中，
//相同的元素可能在各自的插入排序中移动，最后其稳定性就会被打乱，所以shell排序是不稳定的。

//原理：就是在一个数组中，第一次取间隔为d的所有元素进行插入排序，然后再取间隔为d/2的所有元素进行插入排序，依次到d为1时为最后一次
//利用了 插入排序如果对已经排好序的元素进行排序效率高的特点
public class shellSort {
    public static void main(String[] args) {
        int [] arr=new int[]{9,8,7,6,5,4,3,2,1};
        int d=arr.length/2;
        int q=1;
         while(d>0){
            int temp=0;//待排序元素
            for (int i=d;i<arr.length;i=i+d){
                temp=arr[i];
                for (int j=0;j<i;j=j+d){
                    if (temp<arr[j]){
                        //后面的元素挪动位置
                        for (int p=i;p>j;p=p-1){  //关键点在于元素往后挪 和边界值，因为每次插入的时候，子序列都有一部分元素需要往后挪
                            arr[p]=arr[p-1];
                        }
                        arr[j]=temp;
                        break;
                    }

                }

            }


             d=d/2;

             System.out.print("第"+q+"次排序结果：");
             q++;
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
