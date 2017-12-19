package Knowledge.Annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author hw
 * Integer类型的字段注解
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SQLInteger {
  //该字段对应的表列名
  String name() default "";
  //嵌套注解 约束
  Constraints constraint() default @Constraints;
}
