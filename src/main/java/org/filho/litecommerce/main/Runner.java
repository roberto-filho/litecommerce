package org.filho.litecommerce.main;

import org.filho.litecommerce.controllers.GreetingController;
import org.filho.litecommerce.controllers.HelloController;
import org.filho.litecommerce.service.ProdutoRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

@SpringBootApplication
public class Runner extends SpringBootServletInitializer {
	
	private static Object[] applicationClasses = new Object[]{HelloController.class, GreetingController.class, ProdutoRepository.class};

	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(applicationClasses);
    }
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(applicationClasses, args);
    }
}
