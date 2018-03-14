package com.kodgemisi.assignment.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.kodgemisi.assignment.services.TestBean;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

  @Autowired
  private UserDetailsService userDetailsService;
  
  @Bean
  public BCryptPasswordEncoder bCryptPasswordEncoder() {
      return new BCryptPasswordEncoder();
  }
  
  @Bean
  @Scope(value="prototype")
  public TestBean testBean(){
  	return new TestBean();
  }
  
  @Override
  protected void configure(HttpSecurity http) throws Exception {
  
  	http.authorizeRequests().antMatchers("/css/**", "/img/**", "/login", "/logout", "/accesspoint/register").permitAll();
  	http.authorizeRequests().antMatchers("/homepage").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')");	 
    http.authorizeRequests().antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')");    
    http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/accesspoint/unauthorized");    
    http.authorizeRequests().and().formLogin()
            .loginProcessingUrl("/j_spring_security_check")
            .loginPage("/accesspoint/login")
            .defaultSuccessUrl("/homepage")
            .failureUrl("/accesspoint/login?error=true")
            .usernameParameter("username")
            .passwordParameter("password")
            .and().rememberMe().key("uniqueAndSecret").rememberMeParameter("remember-me").rememberMeCookieName("springboot-remember-me").tokenValiditySeconds(1 * 60 * 60)
            .and().logout().logoutUrl("/accesspoint/logout").logoutSuccessUrl("/logoutSuccessful");
  }
  
  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
  	auth.inMemoryAuthentication().withUser("HRManager").password("123").roles("ADMIN");
    auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
  }
  
}
