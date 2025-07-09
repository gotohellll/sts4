package com.example.swagger;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@Component //메모리에 띄우는것 (controller, repository 들이 속해있음)
@OpenAPIDefinition(info=@Info(title="스웨거 제목", version="버전"))
public class SwaggerConfig {

	@Bean
	public GroupedOpenApi chatOpenApi() {
		String [] paths = {"/test/**","/auth/**"};
		
		return GroupedOpenApi
				.builder()
				.group("API test")
				.pathsToMatch(paths)
				.build();
	}
}
