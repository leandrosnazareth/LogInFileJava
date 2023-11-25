package br.com.leandronazareth.LogInFileJava.service;

import org.springframework.stereotype.Service;

@Service
public class SquareRootCalculator {

    public double calculateSquareRoot(double number) {
        if (number < 0) {
            throw new IllegalArgumentException("Não é possível calcular a raiz quadrada de um número negativo.");
        }
        return Math.sqrt(number);
    }
}
