package com.tr1nks.config;

import com.tr1nks.model.entities.entityenums.SiteRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * Security config
 * права доступа
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private static String HAS_ROLE = "hasRole('";
    private static String HAS_ANY_ROLE = "hasAnyRole('";
    private static String COMMA = "', '";
    private static String TAIL = "')";

    /**
     * {@inheritDoc}
     *
     * @param security
     * @throws Exception
     */
    @Override
    public void configure(HttpSecurity security) throws Exception {
        security.authorizeRequests()
                .antMatchers("/main/**").access(HAS_ANY_ROLE + SiteRoles.ADMIN.getRoleWithPrefix() + COMMA + SiteRoles.USER.getRoleWithPrefix() + TAIL)
                .antMatchers("/upload/**").access(HAS_ANY_ROLE + SiteRoles.ADMIN.getRoleWithPrefix() + COMMA + SiteRoles.USER.getRoleWithPrefix() + TAIL)
                .antMatchers("/students/**").access(HAS_ANY_ROLE + SiteRoles.ADMIN.getRoleWithPrefix() + COMMA + SiteRoles.USER.getRoleWithPrefix() + TAIL)
                .antMatchers("/teachers/**").access(HAS_ANY_ROLE + SiteRoles.ADMIN.getRoleWithPrefix() + COMMA + SiteRoles.USER.getRoleWithPrefix() + TAIL)
//                .antMatchers("/index").permitAll()
                .and().formLogin().loginPage("/index").defaultSuccessUrl("/main", false).failureUrl("/index?error=true")
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/index")
                .and().csrf().disable();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/emailToOutController");
//        web.ignoring().antMatchers("/theme", "theme");
    }

    @Resource
    DataSource dataSource;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception {//todo in db auth
//        builder.inMemoryAuthentication().withUser("user").password("user").roles(SiteRoles.USER.getRole());
//        builder.inMemoryAuthentication().withUser("admin").password("admin").roles(SiteRoles.ADMIN.getRole());
//        builder.inMemoryAuthentication().withUser("root").password("root").roles(SiteRoles.ADMIN.getRole(), SiteRoles.USER.getRole());
        builder.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select login, password,TRUE  from user where login=?")
                .authoritiesByUsernameQuery("SELECT login, concat('ROLE_',role) FROM  user WHERE login=?");
    }
    /*http.authorizeRequests()А
                .antMatchers('/management/**')
                    .access("hasRole('ADMIN')")
                .antMatchers('/account/**')
                    .access("hasAnyRole('ROLE_STUDENT', 'ADMIN')")
                .and()
                    .formLogin()
                        .loginPage('/login')
                        .defaultSuccessUrl('/account', false)
                        .failureUrl('/login?error=true')
                .and()
                    .logout()
                        .logoutSuccessUrl('/login')
                .and()
                    .addFilterBefore(ssoFilter(), BasicAuthenticationFilter.class)
                        .exceptionHandling()
                            .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint('/'))
                .and()
                    .csrf()
                        .disable()*/
    /*@Override
    protected void configure(HttpSecurity security) throws Exception {
        security.addFilterAfter(new EncodingFilter(), BasicAuthenticationFilter.class);
        String hasRole = "hasRole('", end = "')", pageend = "/**";
        //.access(hasRole + SiteRoles.USER + end)
        security.authorizeRequests().anyRequest().authenticated().and().formLogin().loginPage("/" + PageNames.INDEX).permitAll().defaultSuccessUrl("/" + PageNames.MAIN, true)
                .and().authorizeRequests()
                .antMatchers("theme").permitAll()
                .antMatchers(PageNames.MAIN, PageNames.MAIN_SLH).access(SiteRoles.USER)
                .antMatchers(PageNames.UPLOAD, PageNames.UPLOAD_SLH).access(SiteRoles.USER)
                .antMatchers(PageNames.STUDENT, PageNames.STUDENT_SLH).access(SiteRoles.USER)
                .antMatchers(PageNames.TEACHER, PageNames.TEACHER_SLH).access(SiteRoles.USER)
                .antMatchers(PageNames.DOWNLOAD, PageNames.DOWNLOAD_SLH).access(SiteRoles.USER)
                .and().csrf().disable();
    }



    */
}
