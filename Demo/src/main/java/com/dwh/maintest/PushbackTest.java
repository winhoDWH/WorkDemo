package com.dwh.maintest;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PushbackReader;

/**
 *使用推回输入流
 * 找到文件内容中的"new PushbackReader"字符串，并将字符串之前的内容打印出来
 * 使用了PushbackReader的unread（数组）方法，把数据内容推回缓冲区
 *
 */
public class PushbackTest {
    public static void main(String[] args){
        try (
                //定义一个PushbackReader，长度为64，读取一个FileReader，可以改成别的Reader流
                PushbackReader pr = new PushbackReader(new FileReader("PushbackTest.java"),64)
                )
        {
            //用来装读取的字符串
            char[] buf = new char[32];
            //上一次读取的字符串
            String lastContent = "";
            int hasRead = 0;

            //循环读取，hasRead是读取了多少字符
            while ((hasRead = pr.read(buf))>0){
                //构造字符串
                String content = new String(buf,0,hasRead);
                int targetIndex = 0;
                //如果组合起来的字符串中有目标字符串，则返回其第一个字符下标
                if ((targetIndex = (lastContent + content).indexOf("new PushbackReader"))>0){
                    //PushbackReader中把内容推回缓冲区中的方法
                    //参数是一个数组
                    pr.unread((lastContent + content).toCharArray());
                    //因为整个字符加起来有64位，所以要看够不够
                    if (targetIndex>32)
                    {
                        buf = new char[targetIndex];
                    }
                    //再把前面内容读取出来
                    pr.read(buf,0,targetIndex);
                    System.exit(0);//退出
                }
                else{
                    System.out.println(lastContent);
                    //把本次内容定位上一次读取内容
                    lastContent = content;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
