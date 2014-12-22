package org.web.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan({ "org.web.data", "org.web.service"})
@ImportResource({ "classpath:hibernateConfig.xml" })
public class HibernateConfig {
}
