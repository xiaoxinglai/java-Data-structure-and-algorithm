package Impl;

import IntefaceList.IList;

//todo 数组型线性表
public class SequenceList<T> implements IList {


    private T[] arr;

    //数组当前容量(里面实际包含的元素个素)
    private Integer size = 0;
    //数组当前大小
    private Integer Length = 4;


    @Override
    public void InitList() {

        this.arr = (T[]) new Object[Length];
    }

    @Override
    public IList DestroyList() {
        arr = null;
        size=0;
        return this;  //这里应该直接让list对象为null
    }

    @Override
    public IList ClearList() {
        this.arr = (T[]) new Object[Length];
        size=0;
        return this;
    }

    @Override
    public Boolean ListEmpty() {
        if (size > 0) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    @Override
    public Integer ListLength() {
        return size;
    }

    @Override
    public Object GetElem(Integer i) {
        if (i > size) {
            return null;
        }
        return arr[i - 1];
    }

    @Override
    public Integer LocateElem(Object e) {

        for (int i = 0; i < arr.length; i++) {
            if (e.equals(arr[i])) {
                return i + 1;
            }
        }

        return -1;
    }

    @Override
    public Object PriorElem(Object e) {
        for (int i = 0; i < arr.length; i++) {
            if (e.equals(arr[i])) {
                return arr[i - 1];
            }
        }

        return -1;
    }

    @Override
    public Object NextElem(Object e) {
        for (int i = 0; i < arr.length; i++) {
            if (e.equals(arr[i])) {
                return arr[i + 1];
            }
        }

        return -1;
    }

    @Override
    public IList ListInsert(Integer i, Object e) {
        if (i>size){
            return null;
        }

        size++;
        addsize();
        for (int j = i-1; j <this.size; j++) {
            arr[size-j]=arr[size-j-1];
            
        }
        arr[i - 1] = (T) e;
        return this;
    }

    @Override
    public IList ListDelete(Integer i) {
        size--;
        for (int i1 = i - 1; i1 + 1 < arr.length; i1++) {
            arr[i1] = arr[i1 + 1];
        }
        return this;
    }

    @Override
    public void ListTraverse() {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != null) {
                System.out.print(arr[i]);
                System.out.print(",");
            }
        }
        System.out.println();
    }

    /**
     * 合并不重复的元素
     *
     * @param list
     * @return
     */
    @Override
    public IList Union(IList list) {

        for (int i = 1; i <= list.ListLength(); i++) {
            if (this.LocateElem(list.GetElem(i)) == -1) {
                this.add(list.GetElem(i));
            }
        }

        return this;
    }

    /**
     * 合并两个列表 从小到大
     *
     * @param list
     * @return
     */
    @Override
    public IList MergeList(IList list) {

        //已知两个表都是递增的顺序
        //借助新的第三张表
        T[] NewArr = (T[]) new Object[Length + list.ListLength()];
        int pa = 1;
        int pb = 1;
        int pc = 0;
        while (pa <= list.ListLength() && pb <= this.ListLength()) {
            if ((int) list.GetElem(pa) <= (int) this.GetElem(pb)) {
                NewArr[pc] = (T) list.GetElem(pa);
                pc++;
                pa++;
            } else {
                NewArr[pc] = (T) this.GetElem(pb);
                pc++;
                pb++;
            }

        }


        while (pa<=list.ListLength()){

            NewArr[pc] = (T) list.GetElem(pa);
            pc++;
            pa++;
        }


        while (pb<=this.ListLength()){
            NewArr[pc] = (T) this.GetElem(pb);
            pc++;
            pb++;

        }

        this.arr=NewArr;

        size=size+ list.ListLength();

        return this;
    }

    @Override
    public void add(Object e) {
        arr[size] = (T) e;
        size++;
        addsize();
    }

    /**
     * 数组扩容
     */
    private void addsize() {
        if (size / (float) arr.length > 0.75) {
            Length = (int) (size * 1.5);
            T[] Newarr = (T[]) new Object[(Length)];

            for (int a = 0; a < arr.length; a++) {
                Newarr[a] = arr[a];
            }

            this.arr = Newarr;
        }
    }
}


/*
* 可以看出来，数组型的列表，时间都花在插入和删除上了，平均每次插入或者删除一个元素，需要移动一半的元素（根据期望值算出）
* 优点是取元素快，只要知道坐标 就可以
* */