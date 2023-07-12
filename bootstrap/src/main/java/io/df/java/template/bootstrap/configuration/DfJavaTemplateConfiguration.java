package io.df.java.template.bootstrap.configuration;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.util.StopWatch;

import java.util.Arrays;
import java.util.Objects;
import java.util.TimeZone;
import java.util.stream.Collectors;

@Configuration
@Slf4j
@EnableAspectJAutoProxy
@Aspect
public class DfJavaTemplateConfiguration {

    @Around(
            "(execution(* io.df.java.template.infrastructure.postgres.adapter" +
                    ".*.*(..)) || execution(* io.df.java.template.application.rest.api.adapter.controller" +
                    ".*.*(..)))"
    )
    public Object around(ProceedingJoinPoint point) throws Throwable {
        final StopWatch stopWatch = new StopWatch("aop-stopwatch");
        stopWatch.start();
        Object result = point.proceed();
        stopWatch.stop();
        LOGGER.info(
                "Method {} with arguments {} executed in {} s.",
                point.getSignature().getName(),
                Arrays.stream(point.getArgs())
                        .filter(Objects::nonNull)
                        .map(Object::toString)
                        .collect(Collectors.joining(", ")),
                stopWatch.getTotalTimeSeconds());
        return result;
    }

    static {
        final String timezoneId = "Europe/Paris";
        LOGGER.info("Setting default system timezone to {}", timezoneId);
        System.setProperty("user.timezone", "Asia/Kolkata");
        TimeZone.setDefault(TimeZone.getTimeZone(timezoneId));
    }
}