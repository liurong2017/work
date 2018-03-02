package cn.work.home;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Controller
@EnableWebMvc
@EnableAsync
@SpringBootApplication
@ComponentScan(basePackages = "cn.work")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
