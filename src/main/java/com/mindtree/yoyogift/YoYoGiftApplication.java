package com.mindtree.yoyogift;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class YoYoGiftApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(YoYoGiftApplication.class, args);
	}
	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(YoYoGiftApplication.class);
    }

}
 