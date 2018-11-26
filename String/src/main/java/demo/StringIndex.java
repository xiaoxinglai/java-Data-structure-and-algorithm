package demo;

/**
 * 串的模式匹配算法
 */
public class StringIndex {

    /**
     * 求子串的定位函数，返回从pos位置起的第一个子串的位置
     * 返回子串T在主串S中第pos个字符之后第位置，若不存在 则函数值为0
     *
     * @return
     */
    public static int index(String S, String T, int pos) {

        //思路： 从S的第pos个字符开始 和T从第一个字符开始的每个字符进行匹配，如果全部匹配成功，则说明找到了子串
        //否则，就从S的下一个字符开始 继续和T从第一个字符开始进行匹配 直到S的所有的字符都匹配完毕
        //双循环 时间复杂度是n*n  最坏情况下 每次匹配到最后一个字符时候不等，这样遍历了两层循环  ，最优情况下是n+m
        char[] s = S.toCharArray();
        char[] t = T.toCharArray();
        for (int j = pos; j < S.length(); j++) {
            int tag = 0;
            for (int i = j; i - j < t.length; i++) {
                if (s[i] != t[i - j]) {
                    tag = 1;
                    break;
                }
            }
            if (tag == 0) {
                return j;
            }
        }

        return 0;
    }

    /**
     * 求子串的定位函数，返回从pos位置起的第一个子串的位置
     * 返回子串T在主串S中第pos个字符之后第位置，若不存在 则函数值为0
     *
     * @return
     */
    public static int indextwo(String S, String T, int pos) {
        //思路： 从S的第pos个字符开始 和T从第一个字符开始的每个字符进行匹配，如果全部匹配成功，则说明找到了子串
        //否则，就从S的下一个字符开始 继续和T从第一个字符开始进行匹配 直到S的所有的字符都匹配完毕

        int i = pos;
        int j = 0;
        char[] s = S.toCharArray();
        char[] t = T.toCharArray();
        while (i < s.length && j < t.length) {
            if (s[i] == t[j]) {
                i++;
                j++;
            } else {
                //重置指针，从新匹配
                i = i - j + 1;
                j = 0;
            }
        }
        if (j == t.length) {
            return (i - j);
        } else {
            return 0;
        }
        //这个时间复杂度 最差仍然为n*n 等于每次快循环结束时候都要重置指针
    }


    //kmp算法

    /**
     * 特点，主串的指针不再回溯，而是让子串向前滑动一段距离 重新匹配
     * 比如
     * 主串
     * abcdabd  i为指向当前主串匹配元素位置的指针
     * abd      j为指向当前子串匹配元素位置的指针
     * 子串
     * 当i=3时，j=3 主串元素为a  子串元素为d  此时不等
     * 传统的方法是此时重新置i为2，j为1   如下
     * abcdabd
     * abd
     * kmp算法考虑到，因i=1 和 i=2之前已经匹配过 是已知的，如果错开肯定就不相等了，因此直接i仍为3 j=1 比较， 不回溯指针
     * abcdabd
     * abd
     * <p>
     * 总结：尽可能错开所有之前已经匹配过的子串部分，继续匹配
     * j要移动的下一个位置k。存在着这样的性质：最前面的k个字符和j之前的最后k个字符是一样的
     * 即：k的位置=前一个元素所在的最大相同前缀集合和后缀集合的长度+1
     * <p>
     * 比如 abcabd   在d位置匹配失败，则k的位置为3 值为c。 是d位置前一个元素的最大相同前缀集合和后缀集合的长度+1
     * <p>
     * 这样就转化成了求某个字符串的最大前缀集合和后缀集合的长度问题
     * 前缀集合 比如说 abcd  那么 a  ab  abc都是它的前缀集合  ，前缀集合必须包含第一个元素，不包含最后一个元素
     * 后缀集合 比如说 abcd 那么  d cd bcd都是它的后缀集合，后缀集合，比如包含最后一个元素，不包含第一个元素
     * 此时他们的最大相同前后缀集合为0，因为没有相同的前后缀。
     * <p>
     * <p>
     * 我们事先求出一个部分匹配表 next数组，就可以根据这个数组 随时找到任意字符不匹配的时候，下一个要对齐的位置是什么
     * next数组的含义就是一个固定字符串的前缀集合和后缀集合中相同的最长长度
     *
     * @param
     */

    public static int[] getNext(String ps) {

        char[] p = ps.toCharArray();

        int[] next = new int[p.length];

        next[0] = -1;

        int j = 0; //j为next的下标

        int k = -1;//k为数组p的下标

        while (j < p.length - 1) {

            if (k == -1 || p[j] == p[k]) {  // p[j] == p[k] 等同于两个数组在比较，假设p[j] p[k] 为两个数组j k，则 j数组是从第一个元素到倒数第二个元素组成的数组，k数组是从第二个元素到最后一个元素组成的数组

                next[++j] = ++k;

            } else {  //如果匹配不上，则查部分匹配表，从k位置不匹配，k数组下一个位置应该挪动到哪里

                k = next[k];

            }

        }
        return next;
    }

    //char[] c = new char[]{'a', 'a', 'b', 'c', 'a', 'a', 'b', 'f', 'f', 'a', 'c', 'a', 'a', 'b', 'c', 'a'};
    //求某个字符串的最大前缀集合和后缀集合的长度
    static int getMaxprefixSuffix(char[] c, int len) {
        int i = 0;
        int j = 1;
        int k = 0;
        while (j < len) {
            if (c[i] == c[j]) { //// 等同于两个数组在比较，假设c[i] c[k] 为两个数组i k，则 i数组是从第一个元素到倒数第二个元素组成的数组，k数组是从第二个元素到最后一个元素组成的数组
                i++;
                j++;
                k++;
            } else {
                k = 0;
                j++;
                i = 0;
            }
        }

        return k;
    }


    //求next数组
    public static int[] getNextTwo(String ps) {
        char[] t = ps.toCharArray();
        int[] next = new int[t.length];
        for (int i =0; i <t.length; i++) {
            next[i] = getMaxprefixSuffix(t, i+1);// len是指字符串的长度
        }

        return next;
    }

    //char[] c = new char[]{'a', 'a', 'b', 'c', 'a', 'a', 'b', 'f', 'f', 'a', 'c', 'a', 'a', 'b', 'c', 'a'};
    //整合 getNextTwo 和 getMaxprefixSuffix 可以优化成getnext
    public static int[] getNextTwoAndGetMaxprefixSuffix(String ps) {
        char[] t = ps.toCharArray();
        int[] next = new int[t.length];
        int k = 0;
        int j = 1;

        next[0]=0;
        while (j < t.length) {
            if (k==0||t[k] == t[j]) {
                if (t[k]==t[j]){
                    k++;
                }
                next[j]=k;
                j++;
            }else {
                k=next[k-1];
            }
        }
        return next;
    }


    public static void main(String[] args) {
//        String s = "ab12cde";
//        String t = "12";
//        System.out.println(index(s, t, 2));
//        System.out.println(indextwo(s,t,2));

//        String str="abcaab";
//
//
//        //-1 0 0 0 1 1
//
//        String str="abcaab";
//
//        getNext("abaabcac"); //01122312   -10011201

////
//        String str2="abbcabbde";  //-1 0 0 0 0 1 2 3 0
//        for (int i : getNext(str2)) {
//            System.out.print(i);
//        }
//
//        //规律 滑动的规律
        //就是 假设在第j位不相等，那么下一个位置 ，必须要满足，j之前的k个位置的字符等于从1到k-1之间的字符，且k字符不能等于j字符
        //原理，即求出P0···Pi的最大相同前后缀长度k


        //求某个字符串的最大前缀集合和后缀集合的长度
//        char[] c = new char[]{'a', 'a', 'b', 'c', 'a', 'a', 'b', 'f', 'f', 'a', 'c', 'a', 'a', 'b', 'c', 'a'};
//        System.out.println(getMaxprefixSuffix(c, c.length));

        char[] c = new char[]{'a', 'a', 'b', 'a', 'a', 'b'};
        System.out.println(getMaxprefixSuffix(c, c.length));


//
  //      String str2 = "abbcabbde";  //-1 0 0 0 0 1 2 3 0 // 实际 000001230
//        for (int i : getNextTwo(str2)) {
//            System.out.print(i);
//        }
        for (int i : getNextTwo(String.valueOf(c))) {
            System.out.print(i);
        }
        System.out.println(" getNextTwo");

        for (int i : getNextTwoAndGetMaxprefixSuffix(String.valueOf(c))) {
            System.out.print(i);
        }
        System.out.println(" getNextTwoAndGetMaxprefixSuffix");
        for (int i : getNext(String.valueOf(c))) {
            System.out.print(i);
        }
        System.out.println(" getNext");


        //ABCDABD

        for (int i : getNextTwoAndGetMaxprefixSuffix("ABCDABD")) {
            System.out.print(i);
        }
        System.out.println(" getNextTwoAndGetMaxprefixSuffix"+"ABCDABD");


    }
}
