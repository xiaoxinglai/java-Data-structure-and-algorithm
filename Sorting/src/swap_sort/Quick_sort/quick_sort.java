package src.swap_sort.Quick_sort;

import java.util.Arrays;
import java.util.List;

//快速排序  原理是分治法的应用
//是对冒泡排序的一种改进
//原理
//通过一趟排序将要排序的数据分割成独立的两部分，其中一部分的所有数据都比另外一部分的所有数据都要小，
// 然后再按此方法对这两部分数据分别进行快速排序，整个排序过程可以递归进行，以此达到整个数据变成有序序列。
//可以达到二分法的效率 logN  最差情况仍然是n*n
//从小到大的排序 第一步都是从right指针开始
public class quick_sort {

    public static void main(String[] args) {
        List<Integer> arr = Arrays.asList(57, 75, 52, 50, 12, 3, 73, 58, 80, 77, 67, 11, 30, 71, 53);
        quickSort(arr, 0, arr.size() - 1);
    }


    //思路：
    //两个哨兵指针left 和right，一个基准值pivot 一个基准值下标index
    //大于基准值的放一边，小于基准值的放一边，哨兵指针指向同一个位置时候，就是该轮排序结束

    public static void quickSort(List<Integer> arr, Integer start, Integer end) {
        if (start >= end) { //当哨兵指向同一个位置 该轮递归结束
            return;
        }
        //定位基准值 (核心 除了定位基准值 还要进行排序)
       //   Integer index = getPartition(arr, start, end); //用的填坑法
        Integer index = getPartitionForPointer(arr, start, end);
        quickSort(arr, start, index - 1);
        quickSort(arr, index + 1, end);

    }


    //填坑法 两个哨兵指针left 和right，一个基准值pivot 一个基准值下标index
    public static Integer getPartition(List<Integer> arr, Integer start, Integer end) {

        //先让第一个数组元素作为基准值
        Integer pivot = arr.get(start);
        //新的基准值
        Integer index = start;
        //两端的哨兵指针
        Integer left = start;
        Integer right = end;
        while (right >= left) {

            while (right >= left) {
                if (arr.get(right) > pivot) { //右边若是比基准值大 则向左移动一位  //todo 这边也是先比较的右边
                    right--;
                } else {
                    //如果比基准值小，则填到left位置上，且left向右移动一位
                    arr.set(left, arr.get(right));
                    index = right;
                    left++;
                    break; //填完之后 切换到另一个哨兵
                }

            }


            while (right >= left) {
                if (arr.get(left) < pivot) { //左边若是比基准值小 则向右移动一位
                    left++;
                } else {
                    //如果比基准值大，则填到right位置上，且right向左移动一位
                    arr.set(right, arr.get(left));
                    index = left;
                    right--;
                    break; //填完之后 切换到另一个哨兵
                }

            }


        }


        arr.set(index, pivot);//把基准值放回到新的index中
        System.out.println("排序结果:" + arr.toString());
        return index;
    }

    //指针交换法   两个哨兵指针left 和right，一个基准值pivot  当left=right的时候 这个位置实际上就是index 把这个和一开始的pivot进行交换
    //返回的就是新的index 也就是left=right的时候的值
    public static Integer getPartitionForPointer(List<Integer> arr, Integer start, Integer end)
    {
        Integer pivot = arr.get(start);
        Integer left = start;
        Integer right = end;
        while (left != right) {  //57, 75, 52, 50

            //右边元素大于基准值 右指针左移
            while (left < right && arr.get(right) >= pivot) {  //todo 万分注意！ 先移动右边的哨兵 不然结果是错的！
                right--;
            }

            //左边元素小于基准值 左指针右移
            while (left < right && arr.get(left) <= pivot) {
                left++;//1 1
            }

            //当遇到第一个左边元素大于基准值且右边元素小于基准值 此时交换其两个位置
            if (left < right) {
                Integer temp = arr.get(left);
                arr.set(left, arr.get(right));
                arr.set(right, temp);
            }
        }


        //此时left=right 返回其就是新的基准值位置
        //当left和right指针重合之时，我们让pivot元素和left与right重合点的元素进行交换
        int temp = arr.get(left);
        arr.set(left, pivot);
        arr.set(start, temp);

        System.out.println("排序结果:" + arr.toString());
        return left;
    }

}
