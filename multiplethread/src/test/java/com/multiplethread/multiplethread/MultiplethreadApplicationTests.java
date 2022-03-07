package com.multiplethread.multiplethread;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayDeque;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ConcurrentHashMap;

@SpringBootTest
class MultiplethreadApplicationTests {

	/**
	 * 软引用 当内存不够用时会被回收 可以做缓存用
	 */
	@Test
	void contextLoads() {
		SoftReference<byte[]> softM=new SoftReference<>(new byte[1024*1024*10]);

	}

	/**
	 * 软引用 当内存不够用时会被回收 可以做缓存用
	 */
	public static void main(String[] args) {
//
//		SoftReference<byte[]> softM=new SoftReference<>(new byte[1024*1024*10]);
//		System.out.println("softM.get()01 = " + softM.get());
//
//		System.gc();
//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//
//
//		//设置对象 12M
//		byte[] byte02=new byte[1024*1024*12];
//		System.out.println("softM.get()02 = " + softM.get());


//		// 弱引用一般用在容器，一Gc 就回收
//		WeakReference<String> weakM=new WeakReference<>(new String("123"));
//		System.out.println("weakM01 = " + weakM.get());
//		System.gc();
//		System.out.println("weakM02 = " + weakM.get());
//
//
//		// 虚引用
//		final ReferenceQueue<Person> queue = new ReferenceQueue<>();
//		PhantomReference<Person> phantM=new PhantomReference<>(new Person(), queue);
//
//		// 写不一定比 collections.syncnoziedHashmap高，但读比其它高很多
//		Map<String,String> mapC=new ConcurrentHashMap<>();


		try {
			ArrayBlockingQueue<String> queuestr=new ArrayBlockingQueue<String>(12);
			queuestr.put("liyanbo");
			queuestr.put("zhangwang");
			queuestr.poll();
			System.out.println("queuestr.size() = " + queuestr.size());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
