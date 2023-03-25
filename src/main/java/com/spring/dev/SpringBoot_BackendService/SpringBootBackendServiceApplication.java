package com.spring.dev.SpringBoot_BackendService;

import com.spring.dev.dbEntity.LibraryDao;
import com.spring.dev.utility.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;

@SpringBootApplication(scanBasePackages = {"com.spring.dev"})
@EnableJpaRepositories(basePackages = {"com.spring.dev.utility"})
@ComponentScan(basePackages = { "com.*" }) @EntityScan("com.*")
public class SpringBootBackendServiceApplication implements CommandLineRunner {

	@Autowired
	LibraryRepository libraryRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootBackendServiceApplication.class, args);
	}

	@Override
	public void run(String[] args){

		List<LibraryDao> libraryDao =libraryRepository.findAll();
		System.out.println(libraryDao.get(0).getAuthor());
	}

}
