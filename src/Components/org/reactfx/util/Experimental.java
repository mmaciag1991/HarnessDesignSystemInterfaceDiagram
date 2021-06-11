package Components.org.reactfx.util;

import java.lang.annotation.*;

/**
 * Used to annotate functionality that is likely to change or be removed in the
 * future.
 */
@Documented
@Retention(RetentionPolicy.CLASS)
@Target({
        ElementType.ANNOTATION_TYPE,
        ElementType.CONSTRUCTOR,
        ElementType.FIELD,
        ElementType.METHOD,
        ElementType.PACKAGE,
        ElementType.TYPE
})
public @interface Experimental {}