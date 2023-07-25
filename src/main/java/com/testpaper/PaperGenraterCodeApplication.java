package com.testpaper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The PaperGenraterCodeApplication class is the entry point for the application.
 * It configures and starts the Spring Boot application.
 * 
 * @see SpringApplication
 * @author TPG team
 * @since 21-06-2023
 * @version 17 
*/
@SpringBootApplication
public class PaperGenraterCodeApplication {

	/**
	 * The main method is the entry point for the application.
	 *
	 * @param args The command line arguments passed to the application.
	 */
	public static void main(String[] args) {
		SpringApplication.run(PaperGenraterCodeApplication.class, args);
	}

}
