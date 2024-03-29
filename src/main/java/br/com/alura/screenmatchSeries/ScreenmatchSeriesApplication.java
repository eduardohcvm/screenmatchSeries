package br.com.alura.screenmatchSeries;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenmatchSeriesApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchSeriesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// é como se fosse um metodo main
		System.out.println("projeto spring sem web");
	}
}
