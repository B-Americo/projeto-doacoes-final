package com.doacoesfinal.doacoesfinal;
import java.security.ProtectionDomain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.tinylog.Logger;

import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Dynamic;

import jakarta.servlet.MultipartConfigElement;
import lombok.extern.log4j.Log4j2;

@SpringBootApplication
public class DoacoesfinalApplication {

	public static void main(String[] args) {
		SpringApplication.run(DoacoesfinalApplication.class, args);
		Logger.info("Iniciando aplicação");
		
		BCryptPasswordEncoder criptografar = new BCryptPasswordEncoder();
		System.out.println(criptografar.encode("123")); 
	}
	
	

}
