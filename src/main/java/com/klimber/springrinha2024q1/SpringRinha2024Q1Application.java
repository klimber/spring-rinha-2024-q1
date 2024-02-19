package com.klimber.springrinha2024q1;

import com.fasterxml.jackson.databind.DeserializationFeature;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringRinha2024Q1Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringRinha2024Q1Application.class, args);
	}

	@Bean
	public Jackson2ObjectMapperBuilderCustomizer jsonCustomizer() {
		return builder -> builder.featuresToDisable(DeserializationFeature.ACCEPT_FLOAT_AS_INT);
	}

}
