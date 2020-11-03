package com.udacity.jwdnd.course1.cloudstorage.config;

import com.udacity.jwdnd.course1.cloudstorage.services.AuthenticationService;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sound.midi.Track;
import javax.swing.table.TableRowSorter;

public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // initial definition
    private AuthenticationService authenticationService;

    // Constructor
    public SecurityConfig(AuthenticationService authenticationService){
        this.authenticationService = authenticationService;
    }

    // 認証方法の実装方法の設定
    // AuthenticationProvider which authorizes user logins by matching their credentials against those stored in the database.
    @Override
    protected void configure(AuthenticationManagerBuilder auth){
        auth.authenticationProvider(this.authenticationService);
    }

    // URL毎のセキュリティ設定
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests().antMatchers("/signup", "/css/**", "/js/**")
                .permitAll().anyRequest().authenticated();

        http.formLogin().loginPage("login")
                .permitAll();

        http.formLogin()
                .defaultSuccessUrl("/home", true);
    }

}
