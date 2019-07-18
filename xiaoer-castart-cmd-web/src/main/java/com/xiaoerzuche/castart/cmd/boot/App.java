package com.xiaoerzuche.castart.cmd.boot;

import java.net.URI;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;

import mousio.etcd4j.EtcdClient;

@Configuration
@ComponentScan(basePackages = { "com" })
@EnableAutoConfiguration
@EnableScheduling
@SpringBootApplication
public class App {
	private static final Logger logger = LoggerFactory.getLogger(App.class);

	public static void main(String[] args) throws Exception {

		SpringApplication.run(App.class, args);
	}

	@Autowired
	private Environment env;

	@Bean
	public EtcdClient initEtcdClient() {
		URI url = URI.create(env.getProperty("etcd.url"));
		return new EtcdClient(url);
	}

}
