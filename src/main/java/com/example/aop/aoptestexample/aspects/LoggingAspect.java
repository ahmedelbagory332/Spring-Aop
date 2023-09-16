package com.example.aop.aoptestexample.aspects;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
@Aspect
public class LoggingAspect  {
    private Logger logger = LoggerFactory.getLogger(getClass());

    //Pointcut - When?
    /*
    Pointcut which decide when intercept and  @Before is type of pointcut
    */
    // execution(* PACKAGE.*.*(..))
    @Before("execution(* com.example.aop.aoptestexample.*.*(..))")
    //WHEN?
    // before any execution in package I need logg data
  public void  logMethodCallBefore(JoinPoint joinPoint){
        //WHAT code u need to execute before any execution in package
        // joinPoint give u details about what pointCut intercepted
        logger.info("Before Aspect - {} is called with arguments: {}"
                ,  joinPoint, joinPoint.getArgs());
  }

    @After("execution(* com.example.aop.aoptestexample.*.*(..))")
    public void  logMethodCallAfter(JoinPoint joinPoint){
        logger.info("After Aspect - {} has executed",  joinPoint);
    }

    @AfterThrowing(
            pointcut = "execution(* com.example.aop.aoptestexample.*.*(..))",
            throwing = "exception"
    )
    public void logMethodCallAfterException(JoinPoint joinPoint, Exception exception) {
        logger.info("AfterThrowing Aspect - {} has thrown an exception {}"
                ,  joinPoint, exception.getLocalizedMessage());
    }

    @AfterReturning(
            pointcut = "execution(* com.example.aop.aoptestexample.*.*(..))",
            returning = "resultValue"
    )
    public void logMethodCallAfterSuccessfulExecution(JoinPoint joinPoint,
                                                      Object resultValue) {
        logger.info("AfterReturning Aspect - {} has returned {}"
                ,  joinPoint, resultValue);
    }


    @Around("execution(* com.example.aop.aoptestexample.*.*(..))")
    public Object findExecutionTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        //Start a timer
        long startTimeMillis = System.currentTimeMillis();

        //Execute the method
        // joinPoint give u details about what pointCut intercepted
        // ProceedingJoinPoint run the execution or the method
        Object returnValue = proceedingJoinPoint.proceed();

        //Stop the timer
        long stopTimeMillis = System.currentTimeMillis();

        long executionDuration = stopTimeMillis - startTimeMillis;

        logger.info("Around Aspect - {} Method executed in {} ms"
                ,proceedingJoinPoint, executionDuration);

        return returnValue;
    }

}
