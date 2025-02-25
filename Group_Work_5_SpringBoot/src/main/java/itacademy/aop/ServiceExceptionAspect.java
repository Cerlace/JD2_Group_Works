package itacademy.aop;

import itacademy.exception.ConstraintViolationException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.SQLIntegrityConstraintViolationException;

@Aspect
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ServiceExceptionAspect {

    private static final Log log = LogFactory.getLog(ServiceExceptionAspect.class);
    public static final String SQL_STATUS = "Sql status: ";
    public static final String SQL_ERROR_CODE = "Sql error code: ";

    @Pointcut("execution(* itacademy.service..*(..))")
    public void serviceMethodExecution() {
    }

    @Around("serviceMethodExecution()")
    public Object handleConstraintViolationException(ProceedingJoinPoint joinPoint) {
        try {
            return joinPoint.proceed();
        } catch (Throwable throwable) {
            if (NestedExceptionUtils.getRootCause(throwable)
                    instanceof SQLIntegrityConstraintViolationException exception) {
                log.warn(SQL_STATUS + exception.getSQLState());
                log.warn(SQL_ERROR_CODE + exception.getErrorCode());
                throw new ConstraintViolationException(
                        exception.getMessage(),
                        exception.getSQLState(),
                        exception.getErrorCode());
            }
            return null;
        }
    }

}