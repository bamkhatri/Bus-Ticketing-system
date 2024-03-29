package com.example.demo.Security;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	UserPrincipleDetailsService user;
	
	@Autowired
	private SimpleAuthenticationSuccessHandler successHandler;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.authenticationProvider(daoAuthenticationProvider());
		
		
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
		
		    .antMatchers("/user/assets/**").permitAll()
		    .antMatchers("/img/**").permitAll()
		    .antMatchers("/saveBus").permitAll()
		    .antMatchers("/findall").hasRole("USER")
		    .antMatchers("/home").permitAll()
			.antMatchers("/register/**").permitAll()
			.antMatchers("/registerUser").permitAll()
			.antMatchers("/savepassenger").permitAll()
			.antMatchers("/findbus").permitAll()
			.antMatchers("/bookbus").hasRole("USER")
			.antMatchers("/userpage").hasRole("USER")
			.antMatchers("/admin/**").hasAnyRole("ADMIN")
			.antMatchers("/user/**").hasAnyRole("ADMIN","USER")
			.anyRequest().authenticated()
			.and()
			.csrf().disable()
			.formLogin()
			.successHandler(successHandler)
			.loginPage("/login").permitAll()
			.loginProcessingUrl("/signin")
			.failureUrl("/login?error=true")
			
			
			.and()
			.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.logoutSuccessUrl("/home")
			.and()
			.exceptionHandling().accessDeniedPage("/home");
		
	}
	
	@Bean
	DaoAuthenticationProvider daoAuthenticationProvider() {
		
		DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
		dao.setPasswordEncoder(passwordEncoder());
		dao.setUserDetailsService(user);
		
		return dao;
		
		
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder();
	}

	

	
	
	
	
	
	
	
	
	
	
	
	

}
