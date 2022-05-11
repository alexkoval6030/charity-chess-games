package by.kovalenko.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Slf4j
@Aspect
public class BeforeAspect {
    @Before("execution(void by.kovalenko.service.impl.*.validate*())")
    public void getNameAdvice() {
        log.debug("Advice: {}, Pointcut: {}", "`@Before`", "any void method `validate*()`");
    }
}
