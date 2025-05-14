package com.sec06;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class GreetMain {
	public static void main(String args[]) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext06.xml");
		
		GreetService greetService = (GreetService) ctx.getBean("greetService");
		
		greetService.sayHello();
	}
}
