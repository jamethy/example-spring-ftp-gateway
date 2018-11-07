package com.james.ftpexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:ftp-example-context.xml")
@EnableConfigurationProperties({FtpExampleProperties.class})
public class FtpExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(FtpExampleApplication.class, args);
	}
}
