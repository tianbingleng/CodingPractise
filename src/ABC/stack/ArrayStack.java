package ABC.stack;

/**
 * Created by tianbingleng on 5/12/2017.
 */
class ArrayStack<AnyType> implements StackInterface<AnyType>{

    private static final int DEFAULT_CAPACITY = 10;

    private Object[] elements;

    private int top;

    public ArrayStack(int capacity) {
        if (capacity <= 0) {
            elements = new Object[DEFAULT_CAPACITY];
        } else {
            elements = new Object[capacity];
        }
        top = -1;
    }

    public ArrayStack() {
        this(DEFAULT_CAPACITY);
    }

    @Override
    public void push(AnyType item) {
        if (top == elements.length - 1) {
            throw new RuntimeException("Stack is full.");
        }
        elements[++top] = item;
    }

    @Override
    public AnyType pop() {
        if (this.isEmpty()) {
            throw new RuntimeException("Stack is empty.");
        }
        AnyType item = (AnyType)elements[top];
        top--;
        return item;
    }

    @Override
    public AnyType peek() {
        if (this.isEmpty()) {
            throw new RuntimeException("Stack is empty.");
        }
        return (AnyType)elements[top];
    }

    @Override
    public boolean isEmpty() {
        return top == -1;
    }
}
