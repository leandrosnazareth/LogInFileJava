package br.com.leandronazareth.LogInFileJava.log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    // Log antes da execução do método
    @Before("execution(* br.com.leandronazareth..*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().toShortString();
        String className = joinPoint.getTarget().getClass().getName();
        Object[] args = joinPoint.getArgs();

        String logMessage = String.format("Método %s está prestes a ser executado. Parâmetros: %s", methodName, Arrays.toString(args));

        logger.info(logMessage);
    }

    // Log após o retorno bem-sucedido do método
    @AfterReturning(pointcut = "execution(* br.com.leandronazareth..*.*(..))", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().toShortString();
        String className = joinPoint.getTarget().getClass().getName();

        String logMessage = String.format("Método %s retornou: %s", methodName, result);

        logger.info(logMessage);
    }

    // Log após uma exceção ser lançada
    @AfterThrowing(pointcut = "execution(* br.com.leandronazareth..*.*(..))", throwing = "ex")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable ex) {
        String methodName = joinPoint.getSignature().toShortString();
        String className = joinPoint.getTarget().getClass().getName();

        String logMessage = String.format("Método %s lançou uma exceção: %s", methodName, ex.getMessage());

        // Aqui você pode decidir se deseja registrar a mensagem de exceção ou não
        if (!"Não é possível calcular a raiz quadrada de um número negativo.".equals(ex.getMessage())) {
            logger.error(logMessage, ex);
        }
    }

}
