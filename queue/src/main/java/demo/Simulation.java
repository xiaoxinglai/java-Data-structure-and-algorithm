package demo;

import impl.LinkedQueue;
import org.omg.CORBA.DynAnyPackage.Invalid;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 队列的应用之离散事件模拟
 * 假设某银行有4个窗口，每个窗口某一时刻只能接待一个客户，因此客户需要在每个窗口前顺次排队
 * 对于刚进入银行的客户 如果某个窗口的业务员正空闲，则可上前办理业务
 * 若4个窗口均有客户，他便会排在人最少的窗口队尾
 * 要求：计算一天中 客户在银行逗留的平均时间
 * 平均时间等于=（每个客户离开银行的时间-每个客户进入银行的时间)之和 / 客户的人数
 * 这里，将客户进入银行 和离开银行 这两个时刻发生的事情称为事件
 * 这一程序也称为 事件驱动
 * <p>
 * 算法：
 * 事件类型：这里只有五种事件类型， 0-客户到达 1-A窗口客户离开 2-B窗口客户离开 3-B窗口客户离开 4-B窗口客户离开
 * 到达的客户事件，包括事件类型,到达时间
 * 离开的客户事件，包括事件类型,离开时间
 * <p>
 * 处理客户到达事件,新来的客户，只会插入到四个窗口队列中，最短的那个。同时 若银行未关门 则生成一个新的客户到达事件，该事件发生在前一个客户到达事件之后
 * 如果插入的队列为空 则再产生一个客户离开事件
 * 队列元素中包含，到达时间，办事所需时间
 * <p>
 * <p>
 * 处理客户离开事件，根据事件类型 从相应的窗口队列头，将事件出队,统计逗留时间,如果队列不为空，则再产生一个客户离开事件
 * <p>
 * 所以 这里使用两个数据结构，事件队列，窗口队列
 */
public class Simulation {

    private static Long TotalTime; //总逗留时间
    private static int CustomerNum;//客户总数
    private static Long CloseTime = 18 * 3600L;//银行关门时间 下午6点时刻的秒数

    static class Event { //事件类 元素
        private int eventType;//事件类型
        private Long time;//事件发生的时间

    }

    static class QEvent { //排队的队列元素
        private Long ArrivalTime; //到达时间
        private int Duration;//办事所需的时间
    }

    static class QueueWindow {
        private int windowNum;//窗口号
        private LinkedQueue<QEvent> WindowQueue;//该窗口的排队队列
    }


    private static LinkedQueue<Event> eventQueues = new LinkedQueue<Event>();//事件队列
    private static LinkedQueue<QEvent>[] queues = new LinkedQueue[3]; //4个窗口的客户队列

    public static void OpenForDay() {
        //初始化客户人数和逗留时间为0
        TotalTime = 0L;
        CustomerNum = 1;
        //生成第一个客户到达事件
        Event event = new Event();
        long rad= (long)( Math.random() * 30 * 60L);
        event.time = 8 * 3600L +rad; //8点开门  30分钟内随机来一名客户
        System.out.println("生成第一个客户到达事件,该客户为8点"+rad/60+"分到来");
        event.eventType = 0;
        eventQueues.InitQueue(); //初始化事件队列
        eventQueues.EnQueue(event); //发生的事件进入事件队列内
        //初始化窗口队列
        for (int i = 0; i < queues.length; i++) {
            LinkedQueue<QEvent> qEventLinkedQueue = new LinkedQueue<QEvent>();
            qEventLinkedQueue.InitQueue();
            queues[i] = qEventLinkedQueue;
        }

    }


    public static char getEventType() {
        if (eventQueues.GetHead().eventType == 0) {
            return 'A';
        } else {
            return 'D';
        }

    }


    /**
     * 找出四个正在排队的队列中 最短的队列
     *
     * @return
     */
    private static QueueWindow getShortestQueue() {
        int max = 0;
        int maxTag = 0;
        for (int i = 0; i < queues.length; i++) {
            if (queues[i].QueueLength() >= max) {
                max = queues[i].QueueLength();
                maxTag = i;
            }
        }
        QueueWindow queueWindow = new QueueWindow();
        queueWindow.windowNum = maxTag;
        queueWindow.WindowQueue = queues[maxTag];
        return queueWindow;
    }

    /**
     * 处理客户到达事件
     * <p>
     * 处理客户到达事件,新来的客户，只会插入到四个窗口队列中，最短的那个。
     * 同时 若银行未关门 则生成一个新的客户到达事件，该事件发生在前一个客户到达事件之后
     * 如果插入的队列为空 则再产生一个客户离开事件
     * 队列元素中包含，到达时间，办事所需时间
     */
    public static void CustomerArrived() {
        //原先事件队列里面的事件出队
        Event event = eventQueues.DeQueue();

        //生成排队事件
        QEvent qEvent = new QEvent();
        qEvent.ArrivalTime = event.time;
        qEvent.Duration = 5 * 60 + (int) (Math.random() * 20 * 60); //办事所需时间 是5~20分钟以内

        //找出最短的窗口队列
        QueueWindow queueWindow = getShortestQueue();
        //判断这个队列是否为空，是的话再产生一个客户离开事件
        if (queueWindow.WindowQueue.isEmpty()) {
            Event leaveEvent = new Event();
            leaveEvent.time = qEvent.ArrivalTime + qEvent.Duration; //客户的离开时间，等于其到达的时间+办事的时间
            leaveEvent.eventType = queueWindow.windowNum;
            eventQueues.EnQueue(leaveEvent);
        }
        //窗口事件入队
        queueWindow.WindowQueue.EnQueue(qEvent);

        //产生新的客户到达事件
        Event newEvent = new Event();
        newEvent.eventType = 0;
        newEvent.time = event.time + 10 * 60L + (long) (Math.random() * 20 * 60L); //前一个客户来到之后  10～30分钟内随机来一名客户
        if (newEvent.time >= CloseTime) {
            return; //终止产生新的客户到达事件
        }
        eventQueues.EnQueue(newEvent); //新的到达事件入队
        CustomerNum++;//客户数+1
    }

    /**
     * *处理客户离开事件，根据事件类型 从相应的窗口队列头，将事件出队,统计逗留时间
     * 如果队列不为空，则再产生一个客户离开事件
     *
     * @param
     */
    private static void CustomerDeparture() {
        Event event = eventQueues.DeQueue();
        QEvent qEvent = queues[event.eventType].DeQueue();//对应窗口队列的排队事件出队
        TotalTime = TotalTime + (event.time - qEvent.ArrivalTime);//逗留时间  等于离开事件发生时间减去到达时间

        //如果该窗口不为空 则继续产生下一个客户离开事件
        if (!queues[event.eventType].isEmpty()) {
            Event leaveEvent = new Event();
            leaveEvent.eventType = event.eventType;
            leaveEvent.time = event.time + queues[event.eventType].GetHead().Duration;// 新的客户离开时间 等于上一个客户离开的时间+该客户自己的办事时间
            eventQueues.EnQueue(leaveEvent);
        }
    }


    public static void main(String[] args) {
        OpenForDay();//初始化 开始一天
        while (!eventQueues.isEmpty()) {   //事件列表不为空时候
            switch (getEventType()) { //事件类型
                case 'A':
                    CustomerArrived();
                    break; //处理客户到达事件
                case 'D':
                    CustomerDeparture();
                    break;//处理客户离开事件
                default:
                    System.out.println("事件类型错误");
                    ;
            }


        }


        System.out.println("今天总共:" + CustomerNum + "个客户，总逗留时间为" + TotalTime + "秒" + "，等于" + TotalTime / 60 + "分");
        System.out.println("每个人平均逗留时间为:" + TotalTime / CustomerNum + "秒， 也就是" + (TotalTime / CustomerNum) / 60 + "分");
    }
}
