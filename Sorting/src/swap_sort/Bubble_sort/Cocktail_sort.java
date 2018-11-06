package src.swap_sort.Bubble_sort;

//鸡尾酒排序也就是定向冒泡排序，（本质上还是交换两个相邻的数字的位置）此演算法与冒泡排序的不同处在于排序时是以双向在序列中进行排序
//鸡尾酒排序等于是冒泡排序的轻微变形。
// 不同的地方在于从低到高然后从高到低，而冒泡排序则仅从低到高去比较序列里的每个元素。
// 他可以得到比冒泡排序稍微好一点的效能，原因是冒泡排序只从一个方向进行比对(由低到高)，每次循环只移动一个项目。
//以序列(2,3,4,5,1)为例，鸡尾酒排序只需要访问两次（升序降序各一次 ）次序列就可以完成排序，但如果使用冒泡排序则需要四次。
//鸡尾酒排序最糟或是平均所花费的次数都是O(n²)，但如果序列在一开始已经大部分排序过的话，会接近O(n)。


public class Cocktail_sort {
    public static void main(String[] args) {
        int[] arr = new int[]{ 57,75,52,50,12,3,73,58,80,77,67,11,30,71,53};
        int temp = 0;
        int min = 0;
        int max = arr.length;

        for (int i = 0; i < arr.length / 2; i++) {  //控制比较的轮数，因为双向 所以比较的轮数减半

            //把最大值移到最右边
            for (int n = 0; n < max - 1; n++) {  //从0开始找 把最大值移到右边
                if (arr[n] > arr[n + 1]) {
                    temp = arr[n];
                    arr[n] = arr[n + 1];
                    arr[n+1]=temp;
                }
            }
            max--;
            //把最小值移到最左边
            for (int n = max; n > min; n--) { //从最大值开始 把最小值移动到左边
                if (arr[n-1] > arr[n]) {
                    temp = arr[n];
                    arr[n] = arr[n - 1];
                    arr[n-1]=temp;
                }
            }
            min++;

            System.out.print("第" + i + "次排序结果：");
            printSort(arr);
        }

    }

    public static void printSort(int[] arr) {

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + ",");
        }
        System.out.println();
    }
}
