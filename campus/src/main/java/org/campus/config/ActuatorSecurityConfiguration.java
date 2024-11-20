package org.campus.config;


import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


// 放开所有验证
@EnableWebSecurity
@Configuration(proxyBeanMethods = false)
public class ActuatorSecurityConfiguration extends WebSecurityConfigurerAdapter {
    /**
     * http验证配置.
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.requestMatcher(EndpointRequest.toAnyEndpoint())
                // 放开所有验证
                .authorizeRequests((requests) -> requests.anyRequest().permitAll());
    }
}
