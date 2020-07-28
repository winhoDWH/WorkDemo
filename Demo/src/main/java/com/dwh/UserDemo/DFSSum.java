package com.dwh.UserDemo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * DFS深度优先遍历，回溯法
 * 一个整型数组可重复元素，计算其中三个数加起来为0的组合
 */
public class DFSSum {
    /**
     *
     * @param a ：目标数组
     * @param length ：目标序列的长度
     * @param target：目标序列总和
     * @return ：目标序列
     */
    public static List<List<Integer>> Sum(int[] a,int length,int target){
        List<List<Integer>> list = new ArrayList<>();
        if(a==null&&a.length==0){
            return list;
        }
        Arrays.sort(a);
        dfs(a,list,new ArrayList<Integer>(),0,0,3,0);
        return list;
    }

    /**
     *
     * @param a:目标数组
     * @param result：结果序列
     * @param res：待完成序列
     * @param start：节点起点
     * @param count：待完成序列长度
     * @param length：目标长度
     * @param target：目标和
     */
    private static void dfs(int[] a, List<List<Integer>> result,List<Integer> res,int start,int count,int length,int target){
        if(count==length&&target==0&&!result.contains(res)){
            result.add(res);
            return;
        }
        for(int i =start;i<a.length;i++){
            if(target-a[i]<a[i]*(length-count-1)){
                break;
            }
            res.add(a[i]);
            dfs(a,result,res,i+1,count+1,length,target-a[i]);
            res.remove(res.size()-1);
        }
    }
}
