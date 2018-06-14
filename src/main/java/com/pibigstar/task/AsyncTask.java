package com.pibigstar.task;

import java.util.concurrent.Future;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
/**
 * 异步任务
 * @author pibigstar
 *
 */
@Component
@EnableAsync //开启异步任务
public class AsyncTask {

	@Async //异步方法注解
	public Future<Boolean> task1() throws InterruptedException {
		long start = System.currentTimeMillis();
		Thread.sleep(1000);
		long end = System.currentTimeMillis();
		System.out.println("=====任务1 耗时："+(end-start)+"======");
		//返回true，告诉此任务已完成
		return new AsyncResult<Boolean>(true);
	}
	
	@Async //异步方法注解
	public Future<Boolean> task2() throws InterruptedException {
		long start = System.currentTimeMillis();
		Thread.sleep(800);
		long end = System.currentTimeMillis();
		System.out.println("=====任务2 耗时："+(end-start)+"======");
		//返回true，告诉此任务已完成
		return new AsyncResult<Boolean>(true);
	}
	
	@Async //异步方法注解
	public Future<Boolean> task3() throws InterruptedException {
		long start = System.currentTimeMillis();
		Thread.sleep(600);
		long end = System.currentTimeMillis();
		System.out.println("=====任务3 耗时："+(end-start)+"======");
		//返回true，告诉此任务已完成
		return new AsyncResult<Boolean>(true);
	}
	

}
