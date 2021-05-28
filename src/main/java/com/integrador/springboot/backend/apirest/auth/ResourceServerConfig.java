	package com.integrador.springboot.backend.apirest.auth;

import java.util.Arrays;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/operarios", "/api/operarios/page/**", "/api/uploads/img/** " , "/images/**" , "api/mantenimientos"  ).hasRole("ROLE_ADMIN")
		.antMatchers("/api/operarios/{id}").hasRole("ROLE_ADMIN")
		.antMatchers("/api/mantenimientos/**").permitAll()
		.antMatchers(HttpMethod.GET, "/api/operarios/{id}").hasRole("ROLE_ADMIN")
		.antMatchers(HttpMethod.POST, "/api/operarios/upload").hasRole("ROLE_ADMIN")
		.antMatchers(HttpMethod.POST, "/api/operarios").hasRole("ROLE_ADMIN")
		.antMatchers("/api/operarios/**").hasRole("ROLE_ADMIN")
		.antMatchers(HttpMethod.DELETE , "/api/operarios/{id}").hasRole("ROLE_ADMIN")
		.anyRequest().permitAll()
		.and().cors().configurationSource(corsConfigurationSource());
	}
	
	
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowedOrigins(Arrays.asList("*"));
		config.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE","OPTIONS"));
		config.setAllowCredentials(true);
		config.setAllowedHeaders(Arrays.asList("Content-Type" , "Authorization"));
		
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config);
		
		return source;
		
		
	}
	
	@Bean
	public FilterRegistrationBean<CorsFilter> corsFilter(){
		FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<CorsFilter>(new CorsFilter(corsConfigurationSource()));
		
		bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return bean;
	}
	
	
	

		
}

	

	