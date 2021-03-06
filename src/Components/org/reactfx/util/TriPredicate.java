package Components.org.reactfx.util;

@FunctionalInterface
public interface TriPredicate<A, B, C> {
    boolean test(A a, B b, C c);
}