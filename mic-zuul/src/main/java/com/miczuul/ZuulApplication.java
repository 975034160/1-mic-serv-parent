package com.miczuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.miczuul.filter.APILoginFilter;

@SpringBootApplication
@EnableZuulProxy
public class ZuulApplication {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpringApplication.run(ZuulApplication.class, args);
	}

	
	@Bean
	public APILoginFilter apiLoginFilter() {
		return new APILoginFilter();
	}
	
}
