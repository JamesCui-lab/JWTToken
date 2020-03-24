package com.example.config;

import com.example.filter.JwtLoginFilter;
import com.example.filter.JwtVerifyFilter;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author john
 * @date 2020/1/12 - 11:04
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserService userService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private RsaKeyProperties prop;

    @Bean
    public BCryptPasswordEncoder myPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*http
                //关闭跨站请求防护
                .cors()
                .and()
                .csrf()
                .disable()
                //允许不登陆就可以访问的方法，多个用逗号分隔
                .authorizeRequests()
                .antMatchers("/product").hasAnyRole("ROLE_USER")
                //其他的需要授权后访问
                .anyRequest().authenticated()
                .and()
                //增加自定义认证过滤器
                .addFilter(new JwtLoginFilter(authenticationManager(), prop))
                //增加自定义验证认证过滤器
                .addFilter(new JwtVerifyFilter(authenticationManager(), prop))
                // 前后端分离是无状态的，不用session了，直接禁用。
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);*/
        
        http.authorizeRequests()
        //所有资源必须授权后访问
        .anyRequest().authenticated()
        .and()
        .formLogin()
        .loginProcessingUrl("/login")
        .permitAll()//指定认证页面可以匿名访问
        
        .and()
        .csrf().disable()
      //关闭跨站请求防护
        //增加自定义认证过滤器
        .addFilter(new JwtLoginFilter(authenticationManager(), prop))
        //增加自定义验证认证过滤器
        .addFilter(new JwtVerifyFilter(authenticationManager(), prop));
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        //UserDetailsService类
        auth.userDetailsService(userService)
                //加密策略
                .passwordEncoder(bCryptPasswordEncoder);
    }
}