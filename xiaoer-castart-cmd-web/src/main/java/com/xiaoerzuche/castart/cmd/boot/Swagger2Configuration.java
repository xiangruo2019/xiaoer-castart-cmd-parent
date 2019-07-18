package com.xiaoerzuche.castart.cmd.boot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Configuration {

	@Bean
	public Docket buildDocket() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(buildApiInf()).select()
						.apis(RequestHandlerSelectors.basePackage("com.xiaoerzuche.member.web"))
						.paths(PathSelectors.any()).build();
	}

	private ApiInfo buildApiInf() {
		return new ApiInfoBuilder().title("大标题").description("springboot swagger2")
						.termsOfServiceUrl("http://wiki.95071222.net/")
						.contact(new Contact("jl-zhou2", "http://wiki.95071222.net/", "hb.huang3@hnair.com"))
						.build();

	}

}