package vn.fpt.dbp.vccb.service.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.RestController;

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
	
}
