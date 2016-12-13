package routing.annotations;

public @interface Resource {
    String name();
    String endpoint();
    String description() default "";
}
