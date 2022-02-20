package datastruct;

import java.util.Arrays;
import java.util.Map;

/**
 * 优先队列
 * <p>
 *     基于二叉堆实现的优先队列
 * </p>
 */
public class PriorityQueue<T extends Comparable<T>> {

    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;
//    java.util.PriorityQueue
    /**
     * 优先级队列表示为一个平衡的二叉堆：queue[n] 的两个孩子是 queue[2*n+1] 和 queue[2*(n+1)]。
     * 优先级队列由比较器或元素的自然顺序排序，如果比较器为空：对于堆中的每个节点 n 和 n 的每个后代 d，n <= d。
     * 假设队列非空，则具有最低值的元素在 queue[0] 中
     */
    private T[] queue;
    /**
     * 实际元素个数
     */
    private int size = 0;
    public PriorityQueue(){

    }

    /**
     * 创建一个最大容量为max的优先对垒
     * @param max
     */
    public PriorityQueue(int max){
        if (max < 1){
            throw new IllegalArgumentException();
        }
        this.queue = (T[])new Comparable[max];
    }

    /**
     * 使用数组创建一个优先队列
     * @param a
     */
    public PriorityQueue(T[] a){
        queue = Arrays.copyOf(a, a.length);
        size = a.length;
        for (int i = (size >>> 1) -1; i >= 0; i--) {
            sink(i);
        }
    }

    /**
     * 向优先队列里插入一个元素
     */
    public void insert(T e){
        if (e == null) throw new IllegalArgumentException();
        //  扩容
        int idx = size;
        if (idx == queue.length){
            int oldLen = queue.length;
            int newLen = oldLen + oldLen >>1;
            if (newLen < 0 || newLen > MAX_ARRAY_SIZE){
                int minLen = idx + 1;
                if (minLen > MAX_ARRAY_SIZE){
                    throw new IllegalArgumentException("太大了");
                }
                newLen = MAX_ARRAY_SIZE;
            }
            Arrays.copyOf(queue, newLen);
        }
        queue[idx] = e;
        swim(idx);
        size++;
    }

    /**
     * 返回最大元素
     * @return
     */
    public T max(){
        if (size == 0) return null;
        return queue[0];
    }

    /**
     * 删除并返回最大值
     * @return
     */
    public T delMax(){
        T max = queue[0];
        int len = size;
        //
        exch(0, len--);
        queue[len] = null;
        sink(0);
        return max;
    }

    /**
     * 优先队列是否为空
     * @return
     */
    public boolean isEmpty(){
        return size == 0;
    }

    /**
     * 元素的个数
     * @return
     */
    public int size(){
        return size;
    }

    /**
     * 上浮
     * 节点上浮到合适的位置（比父节点小）
     * @param k 当前节点
     */
    private void swim(int k){
        // 存在父节点
        while (k > 0){
            int parent = (k-1) >>> 2;
            if (!less(parent, k)){
                // 正确位置：比父节点小
                break;
            }
            // 当前元素比父节点大 交换元素和坐标
            exch(parent,k);
            k = parent;
        }
    }

    /**
     * 下沉<br/>
     * 节点下沉到合适的位置（比两个字节点都大）
     * @param k 当前节点
     */
    private void sink(int k){
        // 如果存在子节点
        while((2*k+1) < size){
            // 较大节点下标设置为左子节点
            int j = 2*k + 1;
            // 右子节点存在，且大于左子节点
            if ((j+1) < size && less(j,j+1)){
                // 设置较大节点下标为右子节点的下标
                j++;
            }
            // 父节点值大于两个子节点
            if (!less(j,k)){
                // 已经下沉到合适位置退出
                break;
            }
            // 交换较大节点值
            exch(j, k);
            k = j;
        }
    }


    /**
     * 下标i的元素小于下标j的元素
     * @param i 第一个下标
     * @param j 第二个下标
     * @return
     */
    private boolean less (int i, int j){
        return queue[i].compareTo(queue[j]) < 0;
    }

    /**
     * 交换元素
     * @param i
     * @param j
     */
    private void exch(int i, int j){
        T t = queue[i];
        queue[i] = queue[j];
        queue[j] = t;
    }
}
