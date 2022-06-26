package com.openclassrooms.PayMyBuddy.configuration;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.ui.DefaultLoginPageGeneratingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.filter.GenericFilterBean;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{


    @Autowired
    UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }


    public PasswordEncoder getPasswordEncoder() {
    	return new BCryptPasswordEncoder();
    }
    
	class LoginPageFilter extends GenericFilterBean {
		
		@Override
		public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
			if (SecurityContextHolder.getContext().getAuthentication() != null
				  && SecurityContextHolder.getContext().getAuthentication().isAuthenticated()
				  && ((HttpServletRequest)request).getRequestURI().equals("/login")) {
				System.out.println("user is authenticated but trying to access login page, redirecting to /");
				((HttpServletResponse)response).sendRedirect("/");
			}
			chain.doFilter(request, response);
		}
		
	}
    
    @Override
	public void configure(HttpSecurity http) throws Exception
	{
    	http.addFilterBefore(
    			new LoginPageFilter(), DefaultLoginPageGeneratingFilter.class);
    	
		http.csrf().disable()
			.authorizeRequests().antMatchers("/","/registration", "/js/**", "/css/**", "/img/**").permitAll()
			.antMatchers("/profile").authenticated()
			.and().formLogin().loginPage("/login").permitAll()
			.and()
			.authorizeRequests().antMatchers("/login").not().authenticated()
			.and().authorizeRequests()
			.anyRequest().authenticated()
			.and()
			.logout()
			.invalidateHttpSession(true).clearAuthentication(true)
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.logoutSuccessUrl("/login?logout")
			.permitAll();		
	}
    
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userDetailsService);
        auth.setPasswordEncoder(getPasswordEncoder());
        return auth;
    }


}
