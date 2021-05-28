package com.integrador.springboot.backend.apirest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class BackendProyectoIntegradorApirestApplication implements CommandLineRunner {
	
    
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	
	public static void main(String[] args) {
		SpringApplication.run(BackendProyectoIntegradorApirestApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		String password = "12345";
		
		//Encriptar las contraseñas de los usuarios
		for (int i = 0; i < 9; i++) {
			String passwordBcrypt = passwordEncoder.encode(password);
			System.out.println("Password: " +passwordBcrypt);
			
		}
			
	}

	

}
