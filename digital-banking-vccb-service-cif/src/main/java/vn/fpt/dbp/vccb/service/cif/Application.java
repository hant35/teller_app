package vn.fpt.dbp.vccb.service.cif;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RestController;

import vn.fpt.dbp.vccb.core.domain.system.Function;
import vn.fpt.dbp.vccb.core.domain.system.repository.FunctionRepository;
import vn.fpt.dbp.vccb.core.domain.user.User;
import vn.fpt.dbp.vccb.core.domain.user.repository.UserRepository;
import vn.fpt.dbp.vccb.core.rest.ClientService;
import vn.fpt.dbp.vccb.core.util.Permissions;
import vn.fpt.dbp.vccb.core.util.Status;
import vn.fpt.dbp.vccb.service.cif.Application;

/**
 * Spring Boot Application
 * @author Truong Do
 */
@Configuration
@ComponentScan
////@EnableDiscoveryClient
@EnableAutoConfiguration
////@EnableCircuitBreaker
////@EnableHystrixDashboard
//@EnableOAuth2Sso
@RestController
//@EnableResourceServer //see http://stackoverflow.com/questions/35344450/spring-zuul-proxy-not-accepting-bearer-token
//@EnableCasClient
@EnableJpaRepositories("vn.fpt.dbp")
@EntityScan("vn.fpt.dbp.vccb.core.domain")

public class Application 
{
	public static void main(String[] args) {
		//System.setProperty("spring.config.name", "users-server");
		switch (args.length) {
		case 1:
			// Optionally set the HTTP port to listen on, overrides
			// value in the <server-name>-server.yml file
			System.setProperty("server.port", args[0]);
		}		
		
		SpringApplication.run(Application.class, args);
	}
	@Configuration
	class WebSecurityConfiguration extends GlobalAuthenticationConfigurerAdapter {

		@Autowired
		UserRepository userRepository;
		@Autowired
		FunctionRepository functionRepository;

		@Override
		public void init(AuthenticationManagerBuilder auth) throws Exception {
			auth.userDetailsService(userDetailsService());
		}

		@Bean
		UserDetailsService userDetailsService() {
			return new UserDetailsService() {
				@Override
				public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
					User user = userRepository.findByUsernameAndWorkflowStatus(username, Status.APPROVED);
					System.out.println("user: " + user);
					if (user != null) {
						List<String> roleNames = new ArrayList<String>();
						try {
							List<Function> functions = functionRepository.findAll();
							if (functions != null && !functions.isEmpty()) {
								for (Function function : functions) {
									if (user.hasPermission(function.getCode(), Permissions.VIEW)) {
										roleNames.add(function.getCode() + "_" + Permissions.VIEW);
									}
									if (user.hasPermission(function.getCode(), Permissions.ADD)) {
										roleNames.add(function.getCode() + "_" + Permissions.ADD);
									}
									if (user.hasPermission(function.getCode(), Permissions.DELETE)) {
										roleNames.add(function.getCode() + "_" + Permissions.DELETE);
									}
									if (user.hasPermission(function.getCode(), Permissions.UPDATE)) {
										roleNames.add(function.getCode() + "_" + Permissions.UPDATE);
									}
									if (user.hasPermission(function.getCode(), Permissions.APPROVE)) {
										roleNames.add(function.getCode() + "_" + Permissions.APPROVE);
									}
									if (user.hasPermission(function.getCode(), Permissions.PRINT)) {
										roleNames.add(function.getCode() + "_" + Permissions.PRINT);
									}
									if (user.hasPermission(function.getCode(), Permissions.CANCEL)) {
										roleNames.add(function.getCode() + "_" + Permissions.CANCEL);
									}
									if (user.hasPermission(function.getCode(), Permissions.RE_OPEN)) {
										roleNames.add(function.getCode() + "_" + Permissions.RE_OPEN);
									}
									if (user.hasPermission(function.getCode(), Permissions.CLOSE)) {
										roleNames.add(function.getCode() + "_" + Permissions.CLOSE);
									}
									if (user.hasPermission(function.getCode(), Permissions.COPY)) {
										roleNames.add(function.getCode() + "_" + Permissions.COPY);
									}
									if (user.hasPermission(function.getCode(), Permissions.ROLLOVER_COMPONENT)) {
										roleNames.add(function.getCode() + "_" + Permissions.ROLLOVER_COMPONENT);
									}
									if (user.hasPermission(function.getCode(), Permissions.CONFIRM)) {
										roleNames.add(function.getCode() + "_" + Permissions.CONFIRM);
									}
									if (user.hasPermission(function.getCode(), Permissions.PAYMENT)) {
										roleNames.add(function.getCode() + "_" + Permissions.PAYMENT);
									}
									if (user.hasPermission(function.getCode(), Permissions.HOLD)) {
										roleNames.add(function.getCode() + "_" + Permissions.HOLD);
									}
									if (user.hasPermission(function.getCode(), Permissions.TEMPLATE)) {
										roleNames.add(function.getCode() + "_" + Permissions.TEMPLATE);
									}
									if (user.hasPermission(function.getCode(), Permissions.GENERATE)) {
										roleNames.add(function.getCode() + "_" + Permissions.GENERATE);
									}
									if (user.hasPermission(function.getCode(), Permissions.AUTO_AUTHORIZE)) {
										roleNames.add(function.getCode() + "_" + Permissions.AUTO_AUTHORIZE);
									}
								}
							}
						} catch (Exception e) {
							e.printStackTrace();
						}

						System.out.println("roles: " + roleNames);
						System.out.println("username: " + user.getUsername());
						System.out.println("password: " + user.getPassword());
						ClientService.setUser(user.getUsername(), user.getPassword());
						return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), true, true, true, true, AuthorityUtils.createAuthorityList(roleNames.toArray(new String[] {})));
					} else {
						throw new UsernameNotFoundException("could not find the user '" + username + "'");
					}
				}
			};
		}
	}

	@EnableWebSecurity
	@Configuration
	class WebSecurityConfig extends WebSecurityConfigurerAdapter {

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			// http.authorizeRequests().anyRequest().fullyAuthenticated().and().
			// httpBasic().and().
			// csrf().disable();
			http.authorizeRequests()
			/*
			 * .antMatchers("/api/limit/list").hasAnyRole("LIMIT_VIEW")
			 * .antMatchers("/api/limit/detail/**").hasAnyRole("LIMIT_VIEW")
			 * .antMatchers
			 * ("/api/limit/getapprovallist/**").hasAnyRole("LIMIT_VIEW")
			 * .antMatchers
			 * ("/api/limit/getapprovallist/**").hasAnyRole("LIMIT_VIEW")
			 * .antMatchers
			 * ("/api/limit/getapprovallist/**").hasAnyRole("LIMIT_VIEW")
			 * .antMatchers
			 * ("/api/limit/getapprovallist/**").hasAnyRole("LIMIT_VIEW")
			 * .antMatchers
			 * ("/api/limit/getapprovallist/**").hasAnyRole("LIMIT_VIEW")
			 * .antMatchers
			 * ("/api/limit/getapprovallist/**").hasAnyRole("LIMIT_VIEW")
			 * .antMatchers
			 * ("/api/limit/getapprovallist/**").hasAnyRole("LIMIT_VIEW")
			 * .antMatchers
			 * ("/api/limit/getapprovallist/**").hasAnyRole("LIMIT_VIEW")
			 * .antMatchers
			 * ("/api/limit/getapprovallist/**").hasAnyRole("LIMIT_VIEW")
			 * .antMatchers
			 * ("/api/limit/getapprovallist/**").hasAnyRole("LIMIT_VIEW")
			 * .antMatchers
			 * ("/api/limit/getapprovallist/**").hasAnyRole("LIMIT_VIEW")
			 */
			 .antMatchers("/api/customer/**").permitAll()
			.anyRequest().authenticated().and().httpBasic().and().csrf().disable();
		}
	}
}