package tarea4dwes;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.ViewResolver;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;



public class TymeleafConfig implements ApplicationContextAware {

	
	private ApplicationContext applicationContext;
	
	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext=applicationContext;
	}
	
	@Bean
	public SpringResourceTemplateResolver templateResolver() {
		
		SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
	templateResolver.setApplicationContext(this.applicationContext);
	templateResolver.setPrefix("classpath:/templates/");
	templateResolver.setSuffix(".html");
	templateResolver.setTemplateMode(TemplateMode.HTML);
	templateResolver.setCacheable(false);
	return templateResolver;
	}
	
	
	@Bean
	public SpringTemplateEngine templateEngine() {
		
		SpringTemplateEngine engine=new SpringTemplateEngine();
		//engine.setEnableSpringELCompiler(true);
		engine.setTemplateResolver(templateResolver());
	return engine;
	}
	
	@Bean
	public ViewResolver viewResolver() {
		ThymeleafViewResolver resolver=new ThymeleafViewResolver();
		resolver.setTemplateEngine(templateEngine());
		resolver.setCharacterEncoding("UTF-8");
		return resolver;
	}
	 
	
	
	
	
	
	
	
	
}
