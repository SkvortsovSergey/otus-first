package com.example.logging;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import static com.example.configuration.Sl4jCustomConfig.aLog;
import static com.example.configuration.Sl4jCustomConfig.toJson;

@Aspect
@Component
@Slf4j
public class LoggingAspect {
    @Around("@annotation(com.example.logging.LoggerAround)")
    public Object logging(ProceedingJoinPoint joinPoint) throws Throwable {
        aLog(e->log.info("Начало работы метода {}", joinPoint.getSignature().getName()));
        aLog(e->log.info("Аргументы метода = {}", toJson(joinPoint.getArgs())));
        var result = joinPoint.proceed();
        aLog(e->log.info("В результате выполнения метода получаем {} : {}", result.getClass().getName(),toJson(result)));
        return result;
    }
}
