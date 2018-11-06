package src.insert_sort.straight_insertion_Sort;

//直接插入排序
//将一个记录插入到已排序好的有序表中，从而得到一个新，记录数增1的有序表。
// 即：先将序列的第1个记录看成是一个有序的子序列，然后从第2个记录逐个进行插入，直至整个序列有序为止。
//要点：设立哨兵，作为临时存储和判断数组边界之用。


public class straight_insert_sort {
    public static void main(String[] args) {
        int [] arr=new int[]{9,8,7,6,5,4,3,2,1};
        //将第一个记录看成是一个有序的子序列

        int temp=0;//待排序元素


        for (int i=1;i<arr.length;i++){  //这里 i为数组的子序列边界
            temp=arr[i];
            //遍历子序列
            for (int j=0;j<i;j++){

                if (temp<arr[j]){

                    //后面的元素挪动位置
                    for (int p=i;p>j;p--){  //关键点在于元素往后挪 和边界值，因为每次插入的时候，子序列都有一部分元素需要往后挪
                        arr[p]=arr[p-1];
                    }

                    arr[j]=temp;
                    break;
                }

            }



            System.out.print("第"+i+"次排序结果：");
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
