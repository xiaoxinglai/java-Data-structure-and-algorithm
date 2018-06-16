package IntefaceList;

public interface IList<T> {

    /**
     * 构造一个空的线性表 进行初始化
     */
    void InitList();

    /**
     * 销毁线性表
     */
    IList DestroyList();

    /**
     * 将线性表重置为空表
     */
    IList ClearList();

    /**
     * 判断线性表是否为空
     */
    Boolean ListEmpty();

    /**
     * 返回线性表中元素的个数
     */
    Integer ListLength();

    /**
     * 获取线性表中第i个元素
     */
    T GetElem(Integer i);

    /**
     * 根据元素获取它的位序，如果有多个符合，则返回第一个，如果没有则返回0
     */
    Integer LocateElem(T e);

    /**
     * 获取该元素的上一个元素
     */
    T PriorElem(T e);
    /**
     *获取该元素的下一个元素
     */
    T NextElem(T e);
    /**
     * 在指定位置插入元素
     */
    IList ListInsert(Integer i, T e);

    /**
     *删除指定位置的元素
     */
    IList ListDelete(Integer i);

    /**
     * 遍历所有的元素
     */
    void  ListTraverse();

    /**
     * 合并两个线性表 合并不重复的元素
     */
    IList Union(IList list);

    /**
     * 按照递增顺序合并两个线性表
     */
    IList MergeList(IList list);

    /**
     * 在尾部新增元素
     */
    void add(T e);

}
