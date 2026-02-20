package com.metacoding.springv2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Boot 애플리케이션 진입점 클래스.
 * @SpringBootApplication을 통해 컴포넌트 스캔, 자동 설정, 설정 클래스 등록을 수행한다.
 */
@SpringBootApplication
public class Springv2Application {

	public static void main(String[] args) {
		SpringApplication.run(Springv2Application.class, args);
	}

}
