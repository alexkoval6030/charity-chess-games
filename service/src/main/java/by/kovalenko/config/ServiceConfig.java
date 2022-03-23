package by.kovalenko.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages = "by.kovalenko.service.impl")
@EnableTransactionManagement
@Import(DaoConfig.class)
public class ServiceConfig {
}
