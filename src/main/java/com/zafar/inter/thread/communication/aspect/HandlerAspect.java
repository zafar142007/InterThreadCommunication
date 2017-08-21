package com.zafar.inter.thread.communication.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class HandlerAspect {
	
	public static boolean stop=false;
	
	
	public static String monitor="";
	
	@Pointcut("withincode(public void com.zafar.inter.thread.communication.runnables.TestRunnable.run()) "
			+ "&& call(* *.*(*)) "
			+ "&& if()")
	public static boolean stopAspect(){
		return stop;
	}
	@Before("stopAspect()") 
    public void beforeAdvice(JoinPoint jp) {
       try {
    	   System.out.println("Waiting");
    	   
    	   synchronized(monitor){
    		   monitor.wait();
    	   }
    	   
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        stop=false;
    }

}
