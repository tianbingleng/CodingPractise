package ABC.stack;

/**
 * Created by tianbingleng on 5/12/2017.
 */
public interface StackInterface<AnyType> {
    void push(AnyType item);
    AnyType pop();
    AnyType peek();
    boolean isEmpty();
}
