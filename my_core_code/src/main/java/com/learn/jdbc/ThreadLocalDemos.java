package com.learn.jdbc;

import java.util.concurrent.atomic.AtomicInteger;
/**
 * ThreadLocal
 * ThreadLocal是解决线程安全问题一个很好的思路，它通过为每个线程提供一个独立的变量副本解决了变量并发访问的冲突问题。
 * 在很多情况下，ThreadLocal比直接使用synchronized同步机制解决线程安全问题更简单，更方便，且结果程序拥有更高的并发性。
 *
 * @author Double
 *
 */
public class ThreadLocalDemos {
	 private static final AtomicInteger uniqueId = new AtomicInteger(0);

     private static final ThreadLocal < Integer > uniqueNum =
         new ThreadLocal < Integer > () {
             @Override protected Integer initialValue() {
                 return uniqueId.getAndIncrement();
         }
     };

     public static int getCurrentThreadId() {
         return uniqueId.get();
     }// UniqueThreadIdGenerator
}
