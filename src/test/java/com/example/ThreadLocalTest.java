package com.example;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ThreadLocalTest {
    @Test
    public void test() {
        //提供一个ThreadLocal对象
        ThreadLocal tl = new ThreadLocal();
        //开启两个线程
        new Thread(() -> {
            tl.set("hello");
            System.out.println(Thread.currentThread().getName() + ":" +tl.get());
            System.out.println(Thread.currentThread().getName() + ":" +tl.get());
            System.out.println(Thread.currentThread().getName() + ":" +tl.get());
            System.out.println(Thread.currentThread().getName() + ":" +tl.get());
            System.out.println(Thread.currentThread().getName() + ":" +tl.get());
            System.out.println(Thread.currentThread().getName() + ":" +tl.get());
        },"蓝色").start();
        new Thread(() -> {
            tl.set("world");
            System.out.println(Thread.currentThread().getName() + ":" +tl.get());
            System.out.println(Thread.currentThread().getName() + ":" +tl.get());
            System.out.println(Thread.currentThread().getName() + ":" +tl.get());
            System.out.println(Thread.currentThread().getName() + ":" +tl.get());
            System.out.println(Thread.currentThread().getName() + ":" +tl.get());
            System.out.println(Thread.currentThread().getName() + ":" +tl.get());

        },"红色").start();
    }
}
