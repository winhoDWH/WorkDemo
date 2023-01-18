package com.dwh.redis;

import java.awt.print.Book;
import java.io.IOException;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.net.Socket;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 测试线程的blocked状态以及waiting、time waiting状态
 * @author dengwenhao
 * data 2022-11-28
 */
public class ThreadBlockWaitingStatus implements Runnable{

    public static void main(String[] args) throws InterruptedException, IOException {
        /*Object ob = new Object();
        ob.wait();*/

        //使用同一个runnable对象，因为syn方法是实例方法
        /*ThreadBlockWaitingStatus runnable = new ThreadBlockWaitingStatus();
        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);
        //初始化状态
        System.out.println(thread1.getState());
        thread1.start();
        //分别查看线程1 2的状态
        Thread.sleep(10);
        System.out.println(thread1.getState());
        thread2.start();
        Thread.sleep(10);
        System.out.println(thread2.getState());

        //测试执行到waiting状态
        Thread.sleep(1300);
        System.out.println(thread1.getState());*/
    }

    @Override
    public void run() {
        //syn();
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过控制两个线程去调用竞争synchronized锁，从而检测是否会进入blocked状态
     * 同时获取到锁的线程由于调用了sleep(time)方法，会进入time waiting状态
     */
    private synchronized void syn(){
        try {
            Thread.sleep(1000);
            //测试转换到waiting状态
            wait();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
