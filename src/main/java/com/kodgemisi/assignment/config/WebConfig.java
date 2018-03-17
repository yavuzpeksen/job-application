package com.kodgemisi.assignment.config;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter{

	@Bean
	public LocaleResolver localeResolver(){
		SessionLocaleResolver lr = new SessionLocaleResolver();
		lr.setDefaultLocale(Locale.ITALY);
		return lr;
	}
	
	
}
