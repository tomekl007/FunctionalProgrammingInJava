/**
 * @author Tomasz Lelek
 * @since 2014-06-02
 */
@FunctionalInterface
public interface UseInstance<T, X extends Throwable> {
    void accept(T path) throws X;
}
