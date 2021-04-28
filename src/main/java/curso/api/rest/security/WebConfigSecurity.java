package curso.api.rest.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import curso.api.rest.service.ImplementacaoUserDetailsService;

@Configuration
@EnableWebSecurity
public class WebConfigSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    private ImplementacaoUserDetailsService implementacaoUserDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        /*
         * Ativando a proteção contra usuário que não estão validados por token
         * */
        http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        /*
         * Service que irá consultar o usuário na base
         * */
        auth.userDetailsService(implementacaoUserDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

}
