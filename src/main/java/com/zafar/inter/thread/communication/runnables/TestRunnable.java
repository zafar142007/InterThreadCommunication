package com.zafar.inter.thread.communication.runnables;


public class TestRunnable implements Runnable{

	public void run() {
		long i=0;
		while(!Thread.interrupted()){
			if(i++%10000000==0)
				System.out.println("Working "+i);
		}
		
	}

}
