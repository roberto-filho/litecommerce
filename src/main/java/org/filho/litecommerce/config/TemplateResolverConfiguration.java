package org.filho.litecommerce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

/**
 * Configuração criada para utilizar a pasta '/WEB-INF/views" para
 * armazenar os htmls que serão utilizados na aplicação. Assim os
 * arquivos da nossa aplicação ficam separados melhor.
 * @author Roberto Filho
 *
 */
@Configuration
public class TemplateResolverConfiguration {
  
  @Bean
  public TemplateResolver templateResolveR() {
    TemplateResolver templateResolver = new ServletContextTemplateResolver();
    templateResolver.setPrefix("/WEB-INF/views/");
    templateResolver.setSuffix(".html");
    templateResolver.setTemplateMode("HTML5");
    templateResolver.setCacheable(false);
    return templateResolver;
  }

}
