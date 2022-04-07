package com.jx.springCloud;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author LDW
 * @date 2022/4/1 14:54
 * CAS理论以及自旋锁的代码实现
 */
public class MyTest {

    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public void myLock() {
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName() + "\t come in");
        while (!atomicReference.compareAndSet(null, thread)) {
        }
    }

    public void myUnLock() {
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread, null);
        System.out.println(Thread.currentThread().getName() + "\t invoke myUnlock");
    }

    public static void main(String[] args) {
        MyTest myTest = new MyTest();
        //线程AA 首先拿到锁，然后暂停5秒，之后释放锁
        new Thread(() -> {
            myTest.myLock();
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myTest.myUnLock();
        }, "AA").start();

        //主线程暂停1秒保证线程A先执行
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //线程BB 尝试拿到锁，但是由于AA线程没有释放则会发生自旋，直到AA释放后才能拿到锁
        new Thread(() -> {
            myTest.myLock();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myTest.myUnLock();
        }, "BB").start();
    }
}