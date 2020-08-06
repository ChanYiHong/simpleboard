package HCY.simpleboard.config.auth;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;

/** 어노테이션이 생성될 수 있는 위치 설정. ElementType.PARAMETER라서 메소드의 파라미터로 선언된 객체만 가능함 **/
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginUser {
}
