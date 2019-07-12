package com.hong.service;

public class calculateMD5 implements Runnable{
    private int taskNum;
    calculateMD5(int num) {
       this.taskNum = num;
   }
	@Override
	public void run() {
		System.out.println("正在执行task " + taskNum);
		
		
		System.out.println("task " + taskNum + "执行完毕");
		
	}

}
