package demo;

/*
字符串匹配算法
 */
public class StringKMP {

    //找出从第一个字符开始 子串T在主串S的第一个位置 如果没有则返回-1
    public static int index(String S, String T) {
        int tag = 0;
        int i = 0;
        int j = 0;
        char[] s = S.toCharArray();
        char[] t = T.toCharArray();
        while (i < s.length && j < t.length) {
            tag++;
            if (s[i] == t[j]) {
                i++;
                j++;
            } else {
                i = i - j + 1;
                j = 0; //一旦不等，j就从0开始
            }
        }


        System.out.println("循环了" + tag + "次");
        if (j == t.length) {
            return i - j;
        } else {
            return -1;
        }
    }


    /**
     * 使用kmp算法的情况下
     * kmp原理，就是 每次匹配的时候，如果t[j]和s[i]不相等，j不是从零开始，而是从k开始，k的特点是 t的0到k-1个位置字符和j-1 到 j-k-1个字符相等
     * 比如说
     * ababd在和ababfababde 匹配的时候
     * ababfababde
     * ababd
     * 在j=4的时候不匹配时，也就是t[4]=d  不匹配的时候，j的下一个值不是0 ，而是2， 即t[2]=a ，而且此时i指针也不需要动
     * 此时比对场景为
     * ababfababde
     *   ababd     此时 在t[2]仍然不匹配  j的下一个值是0
     * <p>
     * ababfababde  此时t[0]时候 仍然不匹配 ，则i要向前移动1位
     *     ababd
     * <p>
     * <p>
     * ababfababde   此时匹配成功返回i
     *      ababd
     * <p>
     * <p>
     * <p>
     * 因此 这里会发现，求j的下一个值 next[j] 等同于是在求 第j个字符前面的，最大相同前缀和和后缀的长度
     * 比如说 ababf f前面的最大相同前缀和后缀为ab 和ab  长度为2 因此next[4]=2
     * <p>
     * t=2的时候  也就是aba  第三个a前面的ab ，最大相同前缀和后缀长度为0  因此next[2]=0
     * <p>
     * 最大前缀： 包含第一个字符，不包含最后一个字符
     * 最大后缀：包含最后一个字符，不包含第一个字符
     * <p>
     * 最大相同前后缀，即前缀和后缀相同的部分 最大长度的
     *
     * @param
     */
    public static int KMP(String S, String T) {
        int tag = 0;

        int[] next = next(T);
        int i = 0;
        int j = 0;
        next[0] = -1;

        char[] s = S.toCharArray();
        char[] t = T.toCharArray();
        while (i < s.length && j < t.length) {
            tag++;
            if (j == -1 || s[i] == t[j]) {
                i++;
                j++;
            } else {
                j = next[j];
            }
        }

        System.out.println("循环了" + tag + "次");
        if (j == t.length) {
            return i - j;
        } else {
            return -1;
        }
    }


    public static int[] next(String T) {
        char[] t = T.toCharArray();
        int[] next = new int[t.length];
        next[0] = -1;//-1表示不存在
        int i = 0;
        int j = 1;
        while (j < t.length) {
            if (i == -1 || t[i] == t[j]) {
                i++;
                next[j] = i;
                j++;
            } else {
                i = next[i];
            }
        }

        for (int i1 : next) {
            System.out.print(i1);
        }
        System.out.println();

        return next;
    }


    public static void main(String[] args) {
        System.out.println(index("abcdefabcdefabcdefabcdefabcabcabcabcdeh", "abcdeh")); //循环了68次
        System.out.println(index("abc", "bc")); //循环了3次
        next("abcabc");//-100123
        System.out.println(KMP("abc", "bc")); //循环了4次
        System.out.println(KMP("abcdefabcdefabcdefabcdefabcabcabcabcdeh", "abcdeh"));//循环了50次
        System.out.println(KMP("abcdeiopsfsfasdaffabcdefabcdefabcdefabcabcabcabcdehabcdes", "abcdeh"));//循环了74次

    }


}
