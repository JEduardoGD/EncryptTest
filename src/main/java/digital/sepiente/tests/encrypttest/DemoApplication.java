package digital.sepiente.tests.encrypttest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import digital.sepiente.tests.encrypttest.exception.DemoException;
import digital.sepiente.tests.encrypttest.service.IEncriptService;

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
		if (args == null) {
			throw new DemoException("Error en argumentos nulos");
		}
		if ( args.length != 1) {
			throw new DemoException("Error en argumentos, se requiere exactamente un argumento");
		}
		String s = args[0];
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
		log.info("encriptServiceAesEcb");
		log.info("String: {}", s);
		log.info("encrypted: {}", encrypted);
		log.info("decrypted: {}", decrypted);
		log.info("===================================");
		

		IEncriptService encriptServiceAesCbc = appContext.getBean("encriptServiceAesCbc", IEncriptService.class);
		encrypted = encriptServiceAesCbc.encript(s);
		decrypted = encriptServiceAesCbc.decript(encrypted);
		log.info("===================================");
		log.info("encriptServiceAesCbc");
		log.info("String: {}", s);
		log.info("encrypted: {}", encrypted);
		log.info("decrypted: {}", decrypted);
		log.info("===================================");
		

		IEncriptService encriptServiceAesGsm = appContext.getBean("encriptServiceAesGsm", IEncriptService.class);
		encrypted = encriptServiceAesGsm.encript(s);
		decrypted = encriptServiceAesGsm.decript(encrypted);
		log.info("===================================");
		log.info("encriptServiceAesGsm");
		log.info("String: {}", s);
		log.info("encrypted: {}", encrypted);
		log.info("decrypted: {}", decrypted);
		log.info("===================================");
	}

}
