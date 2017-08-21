package com.zafar.inter.thread.communication;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zafar.inter.thread.communication.aspect.HandlerAspect;
import com.zafar.inter.thread.communication.runnables.TestRunnable;
import javax.annotation.*;
@Service
public class Driver {
	@Autowired
	private HandlerAspect aspect;
	
	@PostConstruct
	public void init(){
		Scanner scanner = new Scanner(System.in);
		int i=-1;
		TestRunnable runnable = new TestRunnable();
		Thread thread= new Thread(runnable);
		thread.start();
		aspect.monitor="MONITOR";
		while((i=scanner.nextInt())!=0){
			switch(i){
				case 1:	
					aspect.stop=true;
					break;
				case 2:
					
					aspect.stop=false;
					
					synchronized(aspect.monitor){
						aspect.monitor.notify();
					}
					break;				
			}
		}
		thread.interrupt();
		
	}
}
