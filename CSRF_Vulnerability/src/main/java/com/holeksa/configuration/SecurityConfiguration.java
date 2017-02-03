package com.holeksa.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

/**
 * Created by bourbonkid on 11.01.17.
 *
 * Configuration Spring Security class. Class defines multiple HttpSecurity configuration.
 *
 * MainConfiguration is to main application endpoint. Enable csrf defense.
 * CsrfVulnerabilityConfiguration is to show csrf attack. Endpoint are /csrf/**. Csrf is disable.
 * CsrfDefenseConfiguration is to show defense csrf. Endpoint are /csrf-defense/**. Csrf is enable and
 *      token repository is set to CookieCsrfTokenRepository.
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

    @Configuration
    public static class MainConfiguration extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(final HttpSecurity http) throws Exception {
            http
                    .authorizeRequests().antMatchers("/vuln/","/", "/resources/**").permitAll()
                        .anyRequest().authenticated()
                        .and()
                    .formLogin().loginPage("/login.html").permitAll()
                    .and()
                    .logout().permitAll()
                        .logoutSuccessUrl("/");
        }
    }

    @Configuration
    @Order(2)
    public static class CsrfVulnerabilityConfiguration extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(final HttpSecurity http) throws Exception{
            http
                    .antMatcher("/csrf/**")
                        .authorizeRequests()
                        .antMatchers("/vuln/", "/", "/resources/**").permitAll()
                        .anyRequest().authenticated()
                        .and()
                    .formLogin().loginPage("/login.html").permitAll();

            http.csrf().disable();
            http.headers().frameOptions().disable();
        }
    }

    @Configuration
    @Order(1)
    public static class CsrfDefenseConfiguration extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure (final HttpSecurity http) throws Exception {
            http
                    .antMatcher("/csrf-defense/**")
                        .authorizeRequests()
                        .antMatchers("/vuln/", "/", "/resources/**").permitAll()
                        .anyRequest().authenticated()
                        .and()
                    .formLogin()
                    .loginPage("/login.html").permitAll();

            http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
        }
    }

    @Configuration
    @Order(3)
    public static class XssConfiguration extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure (final HttpSecurity http) throws Exception {
            http
                    .antMatcher("/xss/**")
                        .authorizeRequests()
                        .antMatchers("/vuln/", "/", "/resources/**").permitAll()
                        .anyRequest().authenticated()
                        .and()
                    .formLogin()
                    .loginPage("/login.html").permitAll();
            http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
            http.headers().frameOptions().disable();
            http.headers().xssProtection().disable();
        }
    }

    @Configuration
    @Order(4)
    public static class XssDefenseConfiguration extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure (final HttpSecurity http) throws Exception {
            http
                    .antMatcher("/xss-esapi/**")
                        .authorizeRequests()
                        .antMatchers("/vuln/", "/", "/resources/**").permitAll()
                        .anyRequest().authenticated()
                        .and()
                    .formLogin()
                    .loginPage("/login.html").permitAll();

            http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
            http.headers().frameOptions().disable();
            http.headers().xssProtection().disable();
        }
    }




    @Configuration
    @Order(5)
    public static class XssCspConfiguration extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure (final HttpSecurity http) throws Exception {
            http
                    .antMatcher("/xss-csp/**")
                        .authorizeRequests()
                        .antMatchers("/vuln/", "/", "/resources/**").permitAll()
                        .anyRequest().authenticated()
                        .and()
                    .formLogin()
                    .loginPage("/login.html").permitAll();

            http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
            http.headers().contentSecurityPolicy(
                    "default-src 'self'; script-src 'self' 'sha256-J+Vd8dEAQfb56R3LZeRVkWyubwt0w/YUjsY48xrztDI='; " +
                            "report-uri http://localhost:3000/"
            );
        }
    }
    @Configuration
    @Order(6)
    public static class InjectionConfiguration extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure (final HttpSecurity http) throws Exception {
            http
                    .antMatcher("/injection/**, /injection-defense/**")
                        .authorizeRequests()
                        .antMatchers("/vuln/", "/", "/resources/**").permitAll()
                        .anyRequest().authenticated()
                        .and()
                    .formLogin()
                    .loginPage("/login.html").permitAll();

            http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
            http.headers().contentSecurityPolicy(
                    "default-src 'self'; script-src 'self' 'sha256-J+Vd8dEAQfb56R3LZeRVkWyubwt0w/YUjsY48xrztDI='; " +
                            "report-uri http://localhost:3000/"
            );
        }
    }
}



