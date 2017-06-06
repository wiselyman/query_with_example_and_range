package top.wisely;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import top.wisely.repository.support.WiselyRepositoryImpl;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = WiselyRepositoryImpl.class)
public class QueryWithExampleAndRangeApplication {

	public static void main(String[] args) {
		SpringApplication.run(QueryWithExampleAndRangeApplication.class, args);
	}
}
