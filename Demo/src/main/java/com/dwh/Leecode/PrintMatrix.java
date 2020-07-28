package com.dwh.Leecode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 矩阵顺时针，从外向内输出
 */
public class PrintMatrix {
    public int[] spiralOrder(int[][] matrix) {

        if(matrix.length==0) return new int[0];
        int b = matrix.length-1;  //下边界
        int r = matrix[0].length-1;   //右边界
        int l = 0;  //左边界
        int t = 0;  //上边界
        int[] res = new int[(b+1)*(r+1)];
        int x = 0;
        while(true){
            for(int i = l; i <= r; i++) res[x++] = matrix[t][i]; // left to right.
            if(++t > b) break;
            for(int i = t; i <= b; i++) res[x++] = matrix[i][r]; // top to bottom.
            if(l > --r) break;
            for(int i = r; i >= l; i--) res[x++] = matrix[b][i]; // right to left.
            if(t > --b) break;
            for(int i = b; i >= t; i--) res[x++] = matrix[i][l]; // bottom to top.
            if(++l > r) break;
        }
        return res;
    }

    public static void main(String[] args){
        int[][] m = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
        PrintMatrix a = new PrintMatrix();
        int[] res = a.spiralOrder(m);
        for(int k:res)
        System.out.println(k);
    }
}

