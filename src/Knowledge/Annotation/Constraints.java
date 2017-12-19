package Knowledge.Annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author hw
 * 约束注解
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Constraints {
  boolean primaryKey() default false; //判断是否为主键
  boolean allowNull() default false; //判读是否允许为空
  boolean unique() default false; //判断是否唯一
}
