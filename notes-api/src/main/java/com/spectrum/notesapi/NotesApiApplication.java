package com.spectrum.notesapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@SpringBootApplication
@EnableSwagger2
//@EnableSwagger2WebMvc
public class NotesApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotesApiApplication.class, args);
	}

}
