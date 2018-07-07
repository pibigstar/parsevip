package com.pibigstar.parsevip.web;

import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pibigstar.parsevip.task.AsyncTask;

@RestController
@RequestMapping("/task")
public class AsyncTaskController {

	@Autowired
	private AsyncTask asyncTask;
	
	@GetMapping("test")
	public String test() throws InterruptedException {
		long start = System.currentTimeMillis();
		Future<Boolean> a = asyncTask.task1();
		Future<Boolean> b = asyncTask.task2();
		Future<Boolean> c = asyncTask.task3();
		
		//循环到三个任务全部完成
		while (!a.isDone()||!b.isDone()||!c.isDone()) {
			if (a.isDone()&&b.isDone()&&c.isDone()) {
				break;
			}
		}
		long end = System.currentTimeMillis();
		String result = "任务完成，一共用时为："+(end-start)+"毫秒";
		System.out.println(result);
		return result;
		
	}
	
}
