package com.vinhtt.baseProject;

import com.vinhtt.baseProject.config.CommonProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.data.convert.Jsr310Converters;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
@EnableConfigurationProperties
@EntityScan(basePackageClasses = {
		BaseProjectApplication.class,
		CommonProperties.class,
		Jsr310Converters.class
})
public class BaseProjectApplication {

	@PostConstruct
	void init(){
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	}

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(BaseProjectApplication.class, args);
	}

}
