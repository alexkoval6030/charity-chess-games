package by.kovalenko.scheduled;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class ScheduledCondition implements Condition {

    private static final String TRUE = "true";
    private static final String SCHEDULE_PROPERTY = "app.scheduler.enable";

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        String property = context.getEnvironment().getProperty(SCHEDULE_PROPERTY);
        return TRUE.equalsIgnoreCase(property);
    }
}
