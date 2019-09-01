package com.ydm.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import sun.applet.Main;

@SpringBootApplication
@MapperScan("com.ydm.springboot.mapper")
public class SpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootApplication.class, args);
	}

//	public static void main(String[] args) {
//		String a = "abc";
//		String b = "abc";
//		Integer s = 128;
//		Integer ss = 128;
//		System.out.println(a == b);
//		System.out.println(a.equals(b));
//
//		System.out.println(s == ss);
//		System.out.println(s.equals(ss));
//
//	}

}
