package com.dwh.maintest;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * 读写其他进程的数据
 */
public class WriteToProcess {
    public static void main(String[] args) throws IOException {
        Process p = Runtime.getRuntime().exec("java ReadStandard");
        try (PrintStream ps = new PrintStream(p.getOutputStream()))
        {
            ps.println("普通字符串");
            ps.println(new WriteToProcess());
        }
    }
}

class ReadStandard{
    public static void main(String[] args){
        try (
                Scanner sc = new Scanner(System.in);
                PrintStream ps = new PrintStream(new FileOutputStream("out.txt"))
                )
        {
            sc.useDelimiter("\n");
            while (sc.hasNext()){
                ps.println("键盘输入："+sc.next());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}