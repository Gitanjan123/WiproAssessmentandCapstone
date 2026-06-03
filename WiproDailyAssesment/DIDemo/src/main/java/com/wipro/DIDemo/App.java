package com.wipro.DIDemo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//
//public class App {
//    public static void main(String[] args) {
//        ApplicationContext context = 
//            new ClassPathXmlApplicationContext("myconfig.xml");
//
//        Student student = (Student) context.getBean("stuobj");
//        student.show();
//    }
//}


public class App
{
	public static void main(String[] args)
	{
		ApplicationContext context=new AnnotationConfigApplicationContext(AppConfig.class);
		Person obj=context.getBean(Person.class);
		obj.travel();
	}
}