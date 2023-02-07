package com.dataservice.fileService.logging;

import com.dataservice.fileService.controller.FileServiceController;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Aspect
@Component
public class FileControllerLogging {
    private static final Logger logger = LoggerFactory.getLogger(FileServiceController.class);

    @AfterThrowing(pointcut = "execution(* com.dataservice.fileService.controller.*.*(..))", throwing = "error")
    public void logException(JoinPoint joinPoint, Throwable error) {
        logger.error("Method is " + joinPoint.getSignature());
        logger.error("Exception is " + error);
    }

    @Around("execution(* com.dataservice.fileService.controller.*.*(..))")
    public void logMethodExecutionDetails(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        LocalDate startTime = LocalDate.now();
        logger.debug("Entered Method " + proceedingJoinPoint.getSignature()+" at "+startTime);
        proceedingJoinPoint.proceed();
        LocalDate endTime = LocalDate.now();
        logger.debug("Exited Method at" + proceedingJoinPoint.getSignature()+" at "+endTime);
        //logger.debug("Total time for Method at" + proceedingJoinPoint.getSignature()+" execution is "+ ChronoUnit.SECONDS.between(startTime,endTime));

    }
}
