package com.example.demo.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, proxyTargetClass = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
 
    @Autowired
    private UserDetailsService userDetailsService;
 
    @Autowired
    private DataSource dataSource;
 
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
 
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
         .userDetailsService(userDetailsService)
         .passwordEncoder(passwordEncoder());
    }
 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
		/*
		 * http .headers() .frameOptions().sameOrigin() .and() .authorizeRequests()
		 * .antMatchers("/resources/**", "/webjars/**","/assets/**").permitAll()
		 * .antMatchers("/").permitAll() .antMatchers("/admin/**").hasRole("ADMIN")
		 * .anyRequest().authenticated() .and() .formLogin() .loginPage("/login")
		 * .defaultSuccessUrl("/home") .failureUrl("/login?error") .permitAll() .and()
		 * .logout() .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		 * .logoutSuccessUrl("/login?logout") .deleteCookies("my-remember-me-cookie")
		 * .permitAll() .and() .rememberMe() //.key("my-secure-key")
		 * .rememberMeCookieName("my-remember-me-cookie")
		 * .tokenRepository(persistentTokenRepository()) .tokenValiditySeconds(24 * 60 *
		 * 60) .and() .exceptionHandling() ;
		 */
    	http
        .authorizeRequests()
	        .antMatchers(
	            "/registration**",
	            "/js/**",
	            "/css/**",
	            "/images/**",
	            "/webjars/**").permitAll()
//	        .antMatchers("/admin/**").hasRole("ADMIN")
//	        .antMatchers("/").permitAll()
	        .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
//	        .antMatchers("/admin/invoices/**").access("hasRole('ROLE_ADMIN')")
//	        .antMatchers("/admin/authors/**").access("hasRole('ROLE_ADMIN')")
//	        .antMatchers("/admin/authors/**").access("hasRole('ROLE_ADMIN')")
//	        .antMatchers("/admin/statistic/**").access("hasRole('ROLE_ADMIN')")
//	        .antMatchers("/admin/users/**").access("hasRole('ROLE_ADMIN')")
//	        .antMatchers("/admin/categories/**").access("hasRole('ROLE_ADMIN')")
//	        .antMatchers("/admin/orders/**").access("hasAnyRole('ROLE_ADMIN', 'ROLE_SELLER')")
	        .antMatchers("/").permitAll()
	        .antMatchers("/home").permitAll()
	        .antMatchers("/index").permitAll()
	        .antMatchers("/**").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN', 'ROLE_SELLER')")
//	        .antMatchers("/").permitAll()
	        .anyRequest().authenticated()
	    .and()
	        .formLogin()
	        .loginPage("/login")
	        .usernameParameter("username")
	        .passwordParameter("password")
	        .permitAll()
	        .failureUrl("/login?error")
	    .and()
	    	.exceptionHandling().accessDeniedPage("/403")
	    .and()
        	.logout()
		        .invalidateHttpSession(true)
		        .clearAuthentication(true)
		        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		        .logoutSuccessUrl("/index")
		        .permitAll()
      	.and().rememberMe()
      			.key("rememberKey")
      			.rememberMeParameter("remember-me")
      			.tokenRepository(persistentTokenRepository())
      			.tokenValiditySeconds(86400)
	  	.and()
	  		.csrf().disable();
//	  			.rememberMe().tokenRepository(this.persistentTokenRepository()).key("uniqueAndSecret")
//	  			.tokenValiditySeconds(1209600);
    }
    
    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
	    JdbcTokenRepositoryImpl tokenRepositoryImpl = new JdbcTokenRepositoryImpl();
	    tokenRepositoryImpl.setDataSource(dataSource);
    return tokenRepositoryImpl;
    }
//    @Bean
//    public PersistentTokenRepository persistentTokenRepository() {
//        InMemoryTokenRepositoryImpl memory = new InMemoryTokenRepositoryImpl();
//        return memory;
//    }


    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userDetailsService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception { 	
        auth.authenticationProvider(authenticationProvider());
    }
}