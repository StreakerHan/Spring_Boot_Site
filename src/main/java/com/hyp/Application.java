package com.hyp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/** 

* @Title: Application  

* @Description:   

* @author HanYupeng  

* @date 2018-06-13 08:17

*/
@SpringBootApplication
@EnableCaching /*此注解用来开启缓存*/
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
