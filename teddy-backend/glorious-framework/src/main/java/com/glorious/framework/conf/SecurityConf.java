package com.glorious.framework.conf;

import com.glorious.common.define.auth.SecurityAuthRouter;
import com.glorious.framework.filter.SecurityAuthRequestFilter;
import com.glorious.framework.handler.auth.SecurityAuthFailureHandier;
import com.glorious.framework.handler.auth.SecurityForbiddenHandier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConf extends WebSecurityConfigurerAdapter {

    /**
    * 白名单
    */
    static final String[] WHITE_LIST = new String[] {
        SecurityAuthRouter.LOGIN,
        SecurityAuthRouter.REGISTER,
            "/test"
    };

    /**
    * 靜態文件夾
    */
    @Value("${spring.mvc.static-path-pattern}")
    private String STATIC_LINK;

    @Autowired
    SecurityAuthRequestFilter securityAuthRequestFilter;
    @Autowired
    SecurityForbiddenHandier forbiddenHandier;
    @Autowired
    SecurityAuthFailureHandier authFailureHandier;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.cors().and().csrf().disable();

        http.authorizeRequests().antMatchers(STATIC_LINK).permitAll();
        http.authorizeRequests().antMatchers(WHITE_LIST).permitAll();

        http.authorizeRequests().anyRequest().authenticated();

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.exceptionHandling().authenticationEntryPoint(authFailureHandier).accessDeniedHandler(forbiddenHandier);

        // 请求的 TOKEN
        http.addFilterAt(securityAuthRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    /**
     * 强散列哈希加密实现
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() { return new BCryptPasswordEncoder(); }

    /**
     * 解决 无法直接注入 AuthenticationManager
     *
     * @return
     * @throws Exception
     */
    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception { return super.authenticationManager(); }
}
