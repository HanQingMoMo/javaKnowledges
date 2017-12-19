package Knowledge.Annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author hw
 * 表注解
 */

@Target(ElementType.TYPE) //只能应用在类上
@Retention(RetentionPolicy.RUNTIME)//保存到运行时
public @interface DBTable {
  String name() default "";
}
