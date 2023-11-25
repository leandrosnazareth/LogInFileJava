package br.com.leandronazareth.LogInFileJava;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import br.com.leandronazareth.LogInFileJava.service.SquareRootCalculator;

@Component
public class AppRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(AppRunner.class);

    @Autowired
    private SquareRootCalculator squareRootCalculator;

    @Override
    public void run(String... args) {
        try {
            double result = squareRootCalculator.calculateSquareRoot(25);
            logger.info("Resultado: {}", result);

            // Tentar calcular raiz quadrada de um número negativo para gerar uma exceção
            double negativeResult = squareRootCalculator.calculateSquareRoot(-1);
            logger.info("Resultado: {}", negativeResult); // Nunca será impresso devido à exceção
        } catch (Exception e) {
            logger.error("Exceção: {}", e.getMessage());
        }
    }
}
