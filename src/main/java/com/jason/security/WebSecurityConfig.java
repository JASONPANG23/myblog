package com.jason.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

//        X-Content-Type-Options 头设置允许加载静态资源文件
        http.headers()
                .contentTypeOptions().disable()
                .frameOptions().disable();

        http.csrf().disable().authorizeRequests().antMatchers("/static/*",
                "/css/*","/js/*").permitAll();
        http.authorizeRequests()
                .antMatchers("/admin/login").permitAll()
                .antMatchers("/admin/**").hasAuthority("admin");


        http.formLogin()
                .loginPage("/admin/login")
                .loginProcessingUrl("/admin/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .successForwardUrl("/admin/success");
        http.logout()
                .logoutUrl("/admin/logout")
                .logoutSuccessUrl("/admin/login");
        http.rememberMe()
                .rememberMeParameter("remember");

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }


    public static void main(String[] args) {

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encode = bCryptPasswordEncoder.encode("123456");
        System.out.println(encode);
    }

}
