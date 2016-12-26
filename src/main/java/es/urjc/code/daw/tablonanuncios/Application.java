package es.urjc.code.daw.tablonanuncios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.aws.jdbc.config.annotation.EnableRdsInstance;

@SpringBootApplication
@EnableRdsInstance(dbInstanceIdentifier="${cloud.aws.rds.dbInstanceIdentifier}", password="${cloud.aws.rds.springaws.password}")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}