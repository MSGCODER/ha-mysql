package lenn.db.mysql.aspect;

import lenn.db.mysql.annotation.DataSource;
import lenn.db.mysql.libs.DynamicDataSource;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * Created by lenn on 16/6/1.
 */
@Aspect
public class DataSourceAspect {
    @Before("execution(* lenn.db.mysql.service.*.*(..))")
    public void selectDataSource(JoinPoint jp){

        DataSource dataSource =((MethodSignature) jp.getSignature()).getMethod().getAnnotation(DataSource.class);

        DynamicDataSource.target = dataSource.value();
    }
}
