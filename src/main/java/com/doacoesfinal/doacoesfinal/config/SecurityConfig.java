package com.doacoesfinal.doacoesfinal.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.boot.autoconfigure.session.RedisSessionProperties.ConfigureAction;
import org.springframework.boot.web.server.Http2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;

import com.doacoesfinal.doacoesfinal.security.AppUserDetailsService;

@EnableWebSecurity
@Configuration
@ComponentScan(basePackageClasses = AppUserDetailsService.class)
public class SecurityConfig {

	@Autowired
	private UserDetailsService userDetailsService;
	
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		//auth.inMemoryAuthentication().withUser("admin").password("admin").roles("CADASTRO_CLIENTES");
//	}
	
	
	 
	  @Bean 
	  public SecurityFilterChain filterChain(HttpSecurity http) throws
	  Exception {
	  
	  
	 
	  
	  http 
	  .authorizeHttpRequests()
	  .requestMatchers("/css/style.css").permitAll()
	  .requestMatchers("/imagens/**").permitAll()
	  .requestMatchers("/js/**").permitAll()
	  .requestMatchers("/templates/**").permitAll()
	  .requestMatchers("/auth/usuario-donate").permitAll()
	  .requestMatchers("/api/usuario").permitAll()
	  .requestMatchers("/api/usuario/novo").permitAll()
	  .requestMatchers("/login/novo").permitAll()
	  //.requestMatchers("/api/donate").hasAuthority("CADASTRAR_DONATE")
	  .anyRequest().authenticated()	  
	  
	  .and() 
	  .formLogin() 
	  .loginPage("/login").permitAll()
	  .defaultSuccessUrl("/api/donate", true)
	  .and()
	  .csrf().disable();
	 
	  
	 
	 
	 
	  return http.build();
	 
	  }

	 @Bean
	 public PasswordEncoder passwordEncoder() {
	 return new BCryptPasswordEncoder();
	 }
	 
	 
	 
	 protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
	 
	 
	 
	 
	 
	 
	 
	 /*@Bean
	    public UserDetailsService userDetailsService() {
	        UserDetails user = User
	                .withDefaultPasswordEncoder()
	                .username("admin")
	                .password("admin")
	                .roles("CADASTRO_CLIENTES")
	                .build();

	        return new InMemoryUserDetailsManager(user);
	    }
	 */
	 
	 
	 
	 
	 

}
