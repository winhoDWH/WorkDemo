package com.dwh.Leecode;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 获取一组序列中前K个最小的值
 * https://leetcode-cn.com/problems/zui-xiao-de-kge-shu-lcof/solution/3chong-jie-fa-miao-sha-topkkuai-pai-dui-er-cha-sou/
 */
public class GetSmallK {
    /**
     * 快排实现
     * 时间复杂度分析：根据K和下标J的对比，每次最多只需要对一边的元素进行遍历，所以每次划分最多只需要遍历区间（s,e）元素的一半，所以时间复杂度为O(N)
     * @param a
     * @param K
     */
    public int[] getSmallKNumber(int[] a,int K){
        if(K==0||a.length==0) return new int[0];
        return quick(a,0,a.length-1,K);
    }

    //递归进行判断
    //根据快排返回值，如果基准值下标j在k左边，则向右继续进行快排，反之亦然；
    //如果相同，则直接返回k个数据，因为已经有序了
    private int[] quick(int[] a,int start,int end,int k){
        int j = quickSort(a,start,end)+1;
        if(j==k) return Arrays.copyOf(a,k);
        return j>k?quick(a,start,j-2,k):quick(a,j,end,k);
    }

    //只进行一次快排，让其返回中间值的位置
    private int quickSort(int[] a, int start, int end){
        int mid = a[(start + end)/2];
        int L = start, R = end;
        while(L<=R){
            while(a[L]<mid) L++;
            while(a[R]>mid) R--;
            if(L<=R){
                int temp = a[L];
                a[L] = a[R];
                a[R--] = a[L++];
            }
        }
        return R+1;
    }

    /**
     * 使用堆
     * 时间复杂度：O(nlogk),由于需要维护K个元素的堆，所以堆的插入删除都是O(logK)
     * @param arr
     * @param k
     * @return
     */

    public int[] getLeastNumbers(int[] arr, int k) {
        long a = 10L;
        if (k == 0) {
            return new int[0];
        }
        // 使用一个最大堆（大顶堆）
        // Java 的 PriorityQueue 默认是小顶堆，添加 comparator 参数使其变成最大堆
        Queue<Integer> heap = new PriorityQueue<>(k, (i1, i2) -> Integer.compare(i2, i1));

        for (int e : arr) {
            // 当前数字小于堆顶元素才会入堆
            if (heap.isEmpty() || heap.size() < k || e < heap.peek()) {
                heap.offer(e);
            }
            if (heap.size() > k) {
                heap.poll(); // 删除堆顶最大元素
            }
        }

        // 将堆中的元素存入数组
        int[] res = new int[heap.size()];
        int j = 0;
        for (int e : heap) {
            res[j++] = e;
        }
        return res;
    }

    public String convert (String number) {
        // write code here
        char c;
        int count;
        for(int i = 0;i<number.length();i++){
            c = number.charAt(i);
            int a=0;
            switch (c){
                case '一':
                    a = 1;
                    break;
                case '二':
                    a=2;break;
                case '三':
                    a=3;break;
                case '四':
                    a=4;break;
                case '五':
                    a=5;break;
                case '六':
                    a=6;break;
                case '七':
                    a=7;break;
                case '八':
                    a=8;break;
                case '九':
                    a=9;break;
                case '十':
            }
        }
        return "";
    }

}
