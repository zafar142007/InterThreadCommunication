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
	
	public static int waitTime=0;
	
	
	@Pointcut("withincode(public void com.zafar.inter.thread.communication.runnables.TestRunnable.run()) "
			+ "&& call(* *.*(*)) "
			+ "&& if()")
	public static boolean stopAspect(){
		return stop;
	}
	@Before("stopAspect()") 
    public void beforeAdvice(JoinPoint jp) {
       try {
    	   System.out.println("Sleeping for "+waitTime);
    	   Thread.sleep(waitTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        stop=false;
    }

}
