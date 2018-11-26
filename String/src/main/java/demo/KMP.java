package demo;

public class KMP {

//    private static int[] getnext(String str) {
//        return null;
//    }

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



    private static int indeof(String S, String T) {
        int flag = 0;
        char[] s = S.toCharArray();
        char[] t = T.toCharArray();


        for (int i = 0; i < s.length; i++) {
            int tag = 0;
            for (int j = 0; j < t.length && i + j < s.length; j++) {
                flag++;
                if (s[i + j] != t[j]) {
                    tag = 1;
                }
            }
            if (tag == 0) {
                System.out.println("循环了" + flag + "次");
                return i;
            }
        }
        System.out.println("循环了" + flag + "次");
        return -1;
    }


    private static int indeofTwo(String S, String T) {
        char[] s = S.toCharArray();
        char[] t = T.toCharArray();
        int i = 0;
        int j = 0;
        int flag = 0;
        while (i < s.length && j < t.length) {
            flag++;
            if (s[i] == t[j]) {
                i++;
                j++;
            } else {
                i = i - j + 1;
                j = 0;
            }
        }
        System.out.println("循环了" + flag + "次");
        if (j == t.length) {
            return (i-j);
        } else return 0;

    }

    private static int indeofKMP(String S, String T) {
        int[] next=getNext(T) ;
        char[] s = S.toCharArray();
        char[] t = T.toCharArray();
        int i = 0;
        int j = 0;
        int flag = 0;
        while (i < s.length && j < t.length) {
            flag++;
            if (j==-1||s[i] == t[j]) {
                i++;
                j++;
            } else {
                j = next[j];
            }
        }
        System.out.println("循环了" + flag + "次");
        if (j == t.length) {
            return (i-j);
        } else return 0;

    }



    public static void main(String[] args) {

//        System.out.println(indeof("abcdefabcdeh", "abcdeh")); //循环了42次
//        System.out.println(indeofTwo("abcdefabcdeh", "abcdeh"));//循环了17次
//
//        System.out.println(indeofKMP("abcdefabcdeh", "abcdeh"));//循环了14次
//
//        System.out.println(indeof("abcdefabcdefabcdefabcdefabcabcabcabcdeh", "abcdeh")); //循环了204次
//        System.out.println(indeofTwo("abcdefabcdefabcdefabcdefabcabcabcabcdeh", "abcdeh"));//循环了68次
//        System.out.println(indeofKMP("abcdefabcdefabcdefabcdefabcabcabcabcdeh", "abcdeh"));//循环了50次

        System.out.println(indeof("abcdefabcdefabcdefabcdefabcabcabcabcdeh", "abcdehg")); //循环了204次
        System.out.println(indeofTwo("abcdefabcdefabcdefabcdefabcabcabcabcdeh", "abcdehg"));//循环了68次
        System.out.println(indeofKMP("abcdefabcdefabcdefabcdefabcabcabcabcdeh", "abcdehg"));//循环了50次
    }


}
