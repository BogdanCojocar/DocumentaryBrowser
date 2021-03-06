package org.web.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class WebAppInitConfig implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext)
			throws ServletException {
		
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.register(WebAppConfig.class);
		context.setServletContext(servletContext);
		servletContext.addListener(new ContextLoaderListener(context));
		Dynamic dynamic = servletContext.addServlet("dispatcher",
				new DispatcherServlet(context));
		dynamic.addMapping("/");
		dynamic.setLoadOnStartup(1);

	}

}
