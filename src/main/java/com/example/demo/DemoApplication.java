package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.example.demo.exception.DemoException;
import com.example.demo.service.IEncriptService;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	Logger log = LoggerFactory.getLogger(DemoApplication.class);

	@Autowired
	private ApplicationContext appContext;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if (args != null && args.length != 1) {
			throw new DemoException("Error en argumentos");
		}
		String s = args[1];
		String encrypted;
		String decrypted;

		IEncriptService encriptServiceAes = appContext.getBean("encriptServiceAes", IEncriptService.class);

		encrypted = encriptServiceAes.encript(s);
		decrypted = encriptServiceAes.decript(encrypted);

		log.info("===================================");
		log.info("encriptServiceAes");
		log.info("String: {}", s);
		log.info("encrypted: {}", encrypted);
		log.info("decrypted: {}", decrypted);
		log.info("===================================");

		IEncriptService encriptServiceAesEcb = appContext.getBean("encriptServiceAesEcb", IEncriptService.class);

		encrypted = encriptServiceAesEcb.encript(s);
		decrypted = encriptServiceAesEcb.decript(encrypted);

		log.info("===================================");
		log.info("encriptServiceAesDcb");
		log.info("String: {}", s);
		log.info("encrypted: {}", encrypted);
		log.info("decrypted: {}", decrypted);
		log.info("===================================");

		IEncriptService encriptServiceAesCbc = appContext.getBean("encriptServiceAesCbc", IEncriptService.class);

		encrypted = encriptServiceAesCbc.encript(s);
		decrypted = encriptServiceAesCbc.decript(encrypted);

		log.info("===================================");
		log.info("encriptServiceAesDcb");
		log.info("String: {}", s);
		log.info("encrypted: {}", encrypted);
		log.info("decrypted: {}", decrypted);
		log.info("===================================");
	}

}
