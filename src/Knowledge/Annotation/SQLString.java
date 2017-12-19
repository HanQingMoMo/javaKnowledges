package Knowledge.Annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author hw
 * String类型的字段注解
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SQLString {
  String name() default ""; //字段名称
  int value() default 0; //占用的字节数
  Constraints constraint() default @Constraints; //约束
}
