package com.vinhtt.baseProject;

import com.vinhtt.baseProject.config.CommonProperties;
import com.vinhtt.baseProject.model.File;
import com.vinhtt.baseProject.repository.FileRepository;
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
		FileRepository fileRepository = context.getBean(FileRepository.class);
		fileRepository.save(new File(0,"Folder01","root/Folder01","",false));
		fileRepository.save(new File(1,"Folder02","root/Folder02","",false));
		fileRepository.save(new File(1,"Folder03","root/Folder03","",false));
		fileRepository.save(new File(1,"Folder04","root/Folder04","",false));
		fileRepository.save(new File(1,"Folder05","root/Folder05","",false));
		fileRepository.save(new File(2,"Folder021","root/Folder021","",false));
		fileRepository.save(new File(2,"Folder022","root/Folder022","",false));
		fileRepository.save(new File(2,"Folder022","root/Folder022","",false));
	}

}
