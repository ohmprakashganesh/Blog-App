//package com.codewithdurgesh.blog.ConfigBean;
//
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//@Configuration
//public class AppConfig {
//	
//	@Bean
//	public InMemoryUserDetailsManager userDetails() {
//		UserDetails user= User.builder().username("admin").password(passwordEncoder().encode("admin")).roles("admin").build();
//		UserDetails user2= User.builder().username("admin1").password(passwordEncoder().encode("admin")).roles("admin").build();
//			return new InMemoryUserDetailsManager(user, user2);
//			
//		
//		
//	}
//	@Bean
//	PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//		
//	}
//	
//
//}
