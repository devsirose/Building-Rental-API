package com.example.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);

//		String[] beansName = ctx.getBeanDefinitionNames();
//
//		Arrays.sort(beansName);
//		for (String beanName : beansName) {
//			System.out.println(beanName);
//		}
    }

}
