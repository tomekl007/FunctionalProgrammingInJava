@FunctionalInterface
public interface UseInstanceWithReturnType<T, X extends Throwable> {
    T accept(T path) throws X;
}