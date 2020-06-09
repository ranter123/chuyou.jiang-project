package chuyou.jiang.spring.annotation;

/**
 * aop - 实现方法日志注解
 */
public @interface MerthodLogInfo {

    /**
     * 默认值
     * @return
     */
    String values() default "";

    /**
     * 描述
     * @return
     */
    String description() default "";
}
