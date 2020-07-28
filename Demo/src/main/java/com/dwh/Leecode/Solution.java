package com.dwh.Leecode;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Solution {
    static int minnumber;

    /**
     * 回溯法 超时
     * @param coins 硬币面额
     * @param amount 总金额
     * @return
     */
    public int coinChange(int[] coins, int amount) {
        if(coins.length==0) return -1;
        if(amount==0) return 0;
        int k = 0;
        for(int i= 1;i<coins.length;i++){//插入排序,从大到小排列
            int j = i;
            k = coins[i];
            while(j>0&&coins[j-1]<k){
                coins[j] = coins[j-1];
                j--;
            }
            coins[j] = k;
        }
        minnumber = -1;
        dfs(coins,0,0,amount);
        return minnumber;
    }
    private static void dfs(int[] a,int low,int number,int amount){
        if(amount==0&&number>0){
            if(minnumber>0) minnumber = number<minnumber?number:minnumber;
            else if(minnumber==-1) minnumber = number;
        }
        for(int i = low;i<a.length;i++){
            dfs(a,i+1,number,amount);
            if(amount-a[i]>=0){
                while(amount-a[i]>=0){
                    amount = amount-a[i];
                    number++;
                    dfs(a,i+1,number,amount);
                }
            }
        }
    }

    /**
     * 动态规划
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange_2(int[] coins, int amount) {
        if(coins.length==0) return -1;
        int[] mun = new int[amount+1];
        //return sum_1(coins,amount,mun);
        return sum_2(coins,amount);
    }

    /**
     * 自顶向下
     * @param coins 面值数组
     * @param amount    总金额
     * @param minnum    记录最少硬币次数，长度为总金额+1
     * @return
     */
    private int sum_1(int[] coins,int amount,int[] minnum){
        if(amount==0) return 0;
        if(amount<0) return -1;
        if(minnum[amount]!=0) return minnum[amount];
        int min = Integer.MAX_VALUE;//方便识别特殊情况
        for(int key : coins){
            int val = sum_1(coins,amount-key,minnum);
            if(val>=0 && min>val) min = val;
        }
        minnum[amount] = (min==Integer.MAX_VALUE)?-1: (min+1);
        return minnum[amount];
    }

    /**
     *自底向上的解答
     * @param coins 面值数组
     * @param amount 总金额
     * @return
     */
    private int sum_2(int[] coins,int amount){
        if(amount==0) return 0;
        if(amount<0) return -1;
        int[] minnum = new int[amount+1];
        int max = amount+1;
        Arrays.fill(minnum,max);//填充数组，方便判断特殊情况。
        minnum[0] = 0;
        for (int i = 1;i<max;i++){
            for(int key: coins){
                if(i>=key){  //要大于等于
                    if(minnum[i-key]>=0)
                    minnum[i] = Math.min(minnum[i],minnum[i-key]+1);
                }
            }
        }
        return minnum[amount]>amount?-1:minnum[amount];
    }

}
