package ru.xakaton.bimit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class XakatonBimitEdgeApplication extends SpringBootServletInitializer {

	// for war
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(XakatonBimitEdgeApplication.class);
	}

	// for jar
	public static void main(String[] args) {
		SpringApplication.run(XakatonBimitEdgeApplication.class, args);
	}
}
