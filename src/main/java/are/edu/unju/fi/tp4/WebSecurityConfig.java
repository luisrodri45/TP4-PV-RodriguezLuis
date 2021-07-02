package are.edu.unju.fi.tp4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import are.edu.unju.fi.tp4.service.imp.LoginClienteServiceImp;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	private AutenticationHandler autenticacion;
	
	String[] resources = new String[] {
			"/include/**","/css/**","/img/**","/js/**","/layer/**","/webjars/**"
	};
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http
			.authorizeRequests()
				.antMatchers(resources).permitAll()
				.antMatchers("/","/home","/index").permitAll()
				.anyRequest().authenticated()
				.and().formLogin()				
				.loginPage("/login").permitAll()
				.successHandler(autenticacion)
				//.defaultSuccessUrl("/cliente/mostrar")
				.failureUrl("/login?error=true")
				.usernameParameter("correo")
				.passwordParameter("password")
				.and()
				.logout()
				.permitAll();
		http.csrf().disable();
	}
	@Bean
    public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
        return bCryptPasswordEncoder;
    }
	@Autowired
    LoginClienteServiceImp userDetailsService;
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    	
    	auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    
    }
}
