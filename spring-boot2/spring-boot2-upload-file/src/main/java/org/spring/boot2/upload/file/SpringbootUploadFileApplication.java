package org.spring.boot2.upload.file;

import org.spring.boot2.upload.file.storage.StorageProperties;
import org.spring.boot2.upload.file.storage.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class SpringbootUploadFileApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootUploadFileApplication.class, args);
	}

	@Bean
	CommandLineRunner init(StorageService storageService) {
		return (args) -> {
			storageService.deleteAll();
			storageService.init();
		};
	}
}