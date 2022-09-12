package com.luizmangerotte.tuntschallenge;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@Slf4j
public class TuntsChallengeApplication {

	public static void main(String[] args) {
		log.info("Aplication running");
		SpringApplication.run(TuntsChallengeApplication.class, args);
	}

}
