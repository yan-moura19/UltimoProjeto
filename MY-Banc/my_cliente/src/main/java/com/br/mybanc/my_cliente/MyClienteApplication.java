package com.br.mybanc.my_cliente;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class MyClienteApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyClienteApplication.class, args);
	}
	@Bean
	   public RestTemplate restTemplate(RestTemplateBuilder builder) {
	      return builder.build();
	   }

}
