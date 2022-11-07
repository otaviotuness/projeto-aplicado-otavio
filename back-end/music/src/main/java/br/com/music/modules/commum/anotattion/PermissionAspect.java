package br.com.music.modules.commum.anotattion;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.List;

@Aspect
@Component
public class PermissionAspect {

    @Around("@annotation(Permission)")
    public Object validPermission(ProceedingJoinPoint joinPoint) throws Throwable {

        final var authentication = (Authentication) joinPoint.getArgs()[0];

        final var permissionsUser = authentication.getAuthorities().stream().findFirst().get().toString();

        final var permissionsMethod = getPermissionsMethod(joinPoint);

        if(!permissionsMethod.contains(permissionsUser)){
            return new ResponseEntity<>("Forbidden", HttpStatus.FORBIDDEN);
        }

        return joinPoint.proceed();
    }

    private List<String> getPermissionsMethod(ProceedingJoinPoint joinPoint){
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        final var annotation = method.getAnnotation(Permission.class);

        return List.of(annotation.permissions().split(","));
    }

}
