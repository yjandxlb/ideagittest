package com.yanjian;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class AirCondition{
    private int num=0;
    private Lock lock=new ReentrantLock();
    private Condition condition=lock.newCondition();
    /*public synchronized  void add() throws InterruptedException {

        while (num!=0){
          this.wait()

        }
        num++;
        System.out.println(Thread.currentThread().getName()+"线程数"+num);
        notifyAll(); //通知其他进程

    }*/
    public void add() throws InterruptedException {
        lock.lock();
        try {
            while(num!=0){
//            this.wait()
              condition.await();
//                this.wait();
            }
            num=num+1;
            System.out.println(num+"+++");
            System.out.println("线程数++"+num);

//        notifyAll(); //通知其他进程
//            this.notifyAll();
//          condition.signal();
          condition.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public   void dec() throws InterruptedException {
        try {
            while (num==0){
//            this.wait()
                condition.await();
            }
            num=num-1;
            System.out.println(num+"-----");
            System.out.println("线程数--"+num);
//        notifyAll(); //通知其他进程
//            condition.signal();
            condition.signalAll();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();

        }
    }
}

/**
 * 1:线程操作资源类
 * 2:判断/干活/通知
 * 3：在多线程交互中，防止多线程的 虚假唤醒，（判断只能用while，不能用if)
 */
public class ThreadTest {
    public static void main(String[] args) {
        AirCondition airCondition=new AirCondition();
        new Thread(() ->{
            for (int i = 0; i < 10; i++) {
                try {
                    System.out.println(i+"a i+++");
                    airCondition.add();


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"a").start();
        new Thread(() ->{for (int i = 0; i < 10; i++) {
            try {
                System.out.println(i+"b  i----");
                airCondition.dec();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }},"B").start();
       /* new Thread(() ->{
            for (int i = 0; i < 10; i++) {
                try {
                    airCondition.add();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"c").start();
        new Thread(() ->{for (int i = 0; i < 10; i++) {
            try {
                airCondition.dec();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }},"d").start();*/
    }
}
